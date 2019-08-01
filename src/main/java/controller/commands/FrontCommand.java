package controller.commands;

import filter.FilterManager;
import filter.OnIntercept;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class FrontCommand implements OnIntercept {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

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
    logger.info("context path = " + request.getContextPath());
    logger.info("target = " + target);
    response.sendRedirect(request.getContextPath() + target);
  }

  @Override
  public void intercept() {
    intercept = true;
  }
}
