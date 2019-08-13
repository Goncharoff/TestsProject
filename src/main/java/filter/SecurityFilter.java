package filter;


import controller.commands.FrontCommand;
import controller.commands.LoginCommand;
import controller.commands.LogoutCommand;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Filter for authentication.
 */
@WebFilter(servletNames = {"/admin/*", "/user/*"})
public class SecurityFilter extends BaseFilter implements Filter {
    private static final String COMMAND_REGISTER = "command=Register";
    private static final String COMMAND_LOGIN = "command=Login";
    private static final String LOGIN_PAGE_URL = "/login";
    private static final String REGISTRATION_PAGE_URL = "/registration";
    private static final Logger logger = LoggerFactory.getLogger(SecurityFilter.class);

    private OnIntercept callback;

    public SecurityFilter() {
    }

    public SecurityFilter(OnIntercept callback) {
        this.callback = callback;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        super.init(filterConfig);
    }

    /**
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        logger.info( req.getQueryString() + " current query");
        logger.info(checkValidAuthenticationUrls(req) + " - current");
        if (checkValidAuthenticationUrls(req)) {
            logger.info("In redirect");
            LogoutCommand logout = new LogoutCommand();
            logout.init(resp, req);
            logout.process();
        } else {
            logger.info("In process");
            chain.doFilter(request, response);
        }

    }

    /**
     * Checks urls for valid status to access by undefined user.
     *
     * @param httpServletRequest current request
     * @return true is user authenticated other - false
     */
    //TODO this is kinda cringe, should be refactored
    private boolean checkValidAuthenticationUrls(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession(false);

        boolean haveSession = session == null || session.getAttribute("userId") == null;


        return haveSession;
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
