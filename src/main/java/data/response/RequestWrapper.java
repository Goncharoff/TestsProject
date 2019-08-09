package data.response;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestWrapper {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final HttpServletRequest request;

    public RequestWrapper(HttpServletRequest request) {
        this.request = request;
    }


}
