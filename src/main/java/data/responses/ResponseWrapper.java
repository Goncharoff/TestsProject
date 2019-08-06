package data.responses;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResponseWrapper<T> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private T inputModel;
    private HttpServletResponse httpServletResponse;
    private int statusCode;
    private ObjectMapper mapper = new ObjectMapper();

    public ResponseWrapper(T inputModel, HttpServletResponse httpServletResponse, int statusCode) {
        this.inputModel = inputModel;
        this.httpServletResponse = httpServletResponse;
        this.statusCode = statusCode;
        settingResponse();
    }

    private void settingResponse() {
        try {
            PrintWriter out = httpServletResponse.getWriter();
            httpServletResponse.setContentType("application/json");
            out.print(mapper.writeValueAsString(inputModel));
        } catch (IOException e) {
            logger.error("Error on writing response", e);
        }
    }

}
