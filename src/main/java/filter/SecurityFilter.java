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

@WebFilter("/*")
public class SecurityFilter implements Filter {
  private static final String COMMAND_REGISTER = "command=Register";
  private static final String COMMAND_LOGIN = "command=Login";
  private static final String LOGIN_PAGE_URL = "/login";

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  private OnIntercept callback;

  public SecurityFilter() {
  }

  public SecurityFilter(OnIntercept callback) {
    this.callback = callback;
  }

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
          throws IOException, ServletException {

    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    HttpServletResponse httpServletResponse = (HttpServletResponse) response;
    HttpSession session = httpServletRequest.getSession(false);
    String queryCommand = httpServletRequest.getQueryString();


    boolean notLogedin = session == null || session.getAttribute("username") == null;
    boolean isRegisterCommand = queryCommand != null && queryCommand.equals(COMMAND_REGISTER);
    boolean isLoginCommand = queryCommand != null && queryCommand.equals(COMMAND_LOGIN);
    boolean isLoginPage = httpServletRequest.getRequestURI().equals(LOGIN_PAGE_URL);

    if (notLogedin && !isLoginPage && !isLoginCommand && !isRegisterCommand) {

      httpServletResponse.sendRedirect(LOGIN_PAGE_URL);

    } else {
      chain.doFilter(request, response);
    }
  }

  @Override
  public void destroy() {

  }
}
