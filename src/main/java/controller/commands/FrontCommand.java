package controller.commands;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import filter.FilterManager;
import filter.OnIntercept;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Optional;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract class for Commands to implement FrontPattern;
 */
public abstract class FrontCommand implements OnIntercept {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final  String JSP_PATH_PATTERN = "/jsp/%s.jsp";
    protected ServletContext context;
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    private boolean intercept;

    public void init(ServletContext context,
                     HttpServletResponse response,
                     HttpServletRequest request) {

        this.context = context;
        this.request = request;
        this.response = response;
    }

    public void init(HttpServletResponse response,
                     HttpServletRequest request) {
        this.request = request;
        this.response = response;
    }

    /**
     * Process filter chain.
     *
     * @throws ServletException
     * @throws IOException
     */
    public void process() throws ServletException, IOException {
        FilterManager.process(request, response, this);
    }

    /**
     * Goes forward from current request;
     * If filters doesn't processed then doesn't do actions.
     *
     * @param target path to jsp to go forward;
     * @throws ServletException
     * @throws IOException
     */
    void forward(String target) throws ServletException, IOException {
        if (intercept) return;

        target = String.format(JSP_PATH_PATTERN, target);
        RequestDispatcher dispatcher = context.getRequestDispatcher(request.getContextPath() + target);
        logger.info("Forwarding from {} to ({})", request.getContextPath(), target);
        dispatcher.forward(request, response);
    }

    /**
     * Redirecting from current page;
     *
     * @param target - url to go redirect
     * @throws ServletException
     * @throws IOException
     */
    void redirect(String target) throws ServletException, IOException {
        logger.info("Redirecting from {} to ({})", request.getContextPath(), target);
        response.sendRedirect(context.getContextPath() + target);
    }

    /**
     * Get request and map it to Json;
     *
     * @return Request as json
     */
    private String requestToJsonString() {
        StringBuilder jb = new StringBuilder();
        String line;

        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                jb.append(line);
            }
        } catch (Exception e) {
            logger.error("Error during converting request to json");
        }

        return jb.toString();
    }

    /**
     * Map json string to java object
     *
     * @param classToConvert Class to map json.
     * @param <T>            Class of object to map.
     * @return Java object mapped from json.
     */
    <T> Optional<T> convertRequestToJsonObject(Class<T> classToConvert) {
        ObjectMapper mapper = new ObjectMapper();
        Optional<T> resultObject = Optional.empty();

        try {
            resultObject = Optional.of(mapper.readValue(requestToJsonString(), classToConvert));
        } catch (JsonParseException ex) {
            logger.error("Error during parsing input = ", ex);
        } catch (JsonMappingException ex) {
            logger.error("Error during mapping ex", ex);
        } catch (IOException e) {
            logger.error(String.format("Can not convert class %s to Json String", classToConvert.getName()));
        }

        return resultObject;
    }


    @Override
    public void intercept() {
        intercept = true;
    }
}
