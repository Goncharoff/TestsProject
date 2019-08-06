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

public abstract class FrontCommand implements OnIntercept {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

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

    public void process() throws ServletException, IOException {
        FilterManager.process(request, response, this);
    }

    void forward(String target) throws ServletException, IOException {
        if (intercept) return;

        target = String.format("/jsp/%s.jsp", target);
        RequestDispatcher dispatcher = context.getRequestDispatcher(request.getContextPath() + target);
        dispatcher.forward(request, response);
    }

    void redirect(String target) throws ServletException, IOException {
        logger.info(String.format("Redirecting from %s", request.getContextPath()));
        logger.info(String.format("to %s", request.getContextPath()));
        response.sendRedirect(context.getContextPath() + target);
    }

    private String requestToJsonString() {
        StringBuilder jb = new StringBuilder();
        String line;

        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                jb.append(line);
            }
        } catch (Exception e) {
            logger.error("Can not convert to json =(");
        }
        logger.info("got request " + jb.toString() + " with length" + jb.toString().length());
        return jb.toString();
    }

    <T> Optional<T> convertStringToJsonObject(Class<T> classToConvert) {
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
