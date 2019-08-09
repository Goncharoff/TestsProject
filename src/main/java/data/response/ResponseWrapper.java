package data.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ResponseWrapper<T> {
    private static final int SUCCESS_CODE = 200;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private T inputModel;
    private HttpServletResponse httpServletResponse;
    private int statusCode;
    private ObjectMapper mapper = new ObjectMapper();
    private static final String contentType = "application/json";

    public ResponseWrapper(T inputModel, HttpServletResponse httpServletResponse, int statusCode) {
        this.inputModel = inputModel;
        this.httpServletResponse = httpServletResponse;
        this.statusCode = statusCode;
        settingResponse();
    }

    public ResponseWrapper(T inputModel, HttpServletResponse httpServletResponse) {
        this.inputModel = inputModel;
        this.httpServletResponse = httpServletResponse;
        this.statusCode = SUCCESS_CODE;
        settingResponse();
    }

    private void settingResponse() {
        httpServletResponse.setStatus(statusCode);
        try {
            PrintWriter out = httpServletResponse.getWriter();
            httpServletResponse.setContentType(contentType);
            out.print(mapper.writeValueAsString(inputModel));
        } catch (IOException e) {
            logger.error("Error on writing response", e);
        }
    }

}
