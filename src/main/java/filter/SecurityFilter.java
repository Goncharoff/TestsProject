package filter;

import controller.commands.FrontCommand;
import controller.commands.LoginCommand;
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

@WebFilter("/*")
public class SecurityFilter implements Filter {

  private OnIntercept callback;

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

    if (session == null || session.getAttribute("username") == null) {
      FrontCommand command = new LoginCommand();
      command.init(httpServletResponse, httpServletRequest);
      command.process();
    } else {
      chain.doFilter(request, response);
    }
  }

  @Override
  public void destroy() {

  }
}
