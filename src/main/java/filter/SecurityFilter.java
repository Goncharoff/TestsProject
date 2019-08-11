package filter;


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
//@WebFilter("/*")
public class SecurityFilter extends BaseFilter implements Filter {
    private static final String COMMAND_REGISTER = "command=Register";
    private static final String COMMAND_LOGIN = "command=Login";
    private static final String LOGIN_PAGE_URL = "/login";
    private static final String REGISTRATION_PAGE_URL = "/registration";

    private OnIntercept callback;

    public SecurityFilter() {
    }

    public SecurityFilter(OnIntercept callback) {
        this.callback = callback;
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        if (checkValidAuthentificationUrls(req, resp)) {
            resp.sendRedirect(LOGIN_PAGE_URL);
        } else {
            chain.doFilter(request, response);
        }
    }

    /**
     * Checks urls for valid status to access by undefined user.
     *
     * @param httpServletRequest current request
     * @param httpServletResponse current response
     * @return
     */
    //TODO this is kinda cringe, should be refactored
    private boolean checkValidAuthentificationUrls(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        HttpSession session = httpServletRequest.getSession(false);
        String queryCommand = httpServletRequest.getQueryString();

        boolean notLogedin = session == null || session.getAttribute("userId") == null;
        boolean isRegisterCommand = queryCommand != null && queryCommand.equals(COMMAND_REGISTER);
        boolean isLoginCommand = queryCommand != null && queryCommand.equals(COMMAND_LOGIN);
        boolean isLoginPage = httpServletRequest.getRequestURI().equals(LOGIN_PAGE_URL);
        boolean isRegistrationPage = httpServletRequest.getRequestURI().equals(REGISTRATION_PAGE_URL);

        return notLogedin && !isLoginPage && !isLoginCommand && !isRegisterCommand && !isRegistrationPage;
    }

}
