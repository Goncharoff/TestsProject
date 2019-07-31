package controller.commands;

import filter.FilterManager;
import filter.OnIntercept;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class FrontCommand implements OnIntercept {

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

  protected void forward(String target) throws ServletException, IOException {
    if (intercept) return;

    target = String.format("/jsp/%s.jsp", target);
    RequestDispatcher dispatcher = context.getRequestDispatcher(target);
    dispatcher.forward(request, response);
  }

  @Override
  public void intercept() {
    intercept = true;
  }
}
