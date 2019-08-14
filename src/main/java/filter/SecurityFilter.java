package filter;

import data.response.RedirectResponse;
import data.response.ResponseWrapper;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Filter for authentication.
 */
public class SecurityFilter extends BaseFilter implements Filter {
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
     * Checks if session for user active and if not sends JSON with 401 error
     * else - continue chain.
     *
     *
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
        HttpSession session = req.getSession(false);
        String loginUri = req.getContextPath() + "/login";
        boolean loggedIn = session != null && session.getAttribute("userId") != null;

        if (loggedIn) {
            chain.doFilter(request, response);
        } else {
            new ResponseWrapper<>(new RedirectResponse(loginUri), resp, 401);
        }

    }


    @Override
    public void destroy() {
        super.destroy();
    }
}
