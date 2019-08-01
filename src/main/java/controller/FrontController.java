package controller;

import controller.commands.FrontCommand;
import controller.commands.UnknownCommand;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/")
public class FrontController extends HttpServlet {
  final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    process(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    process(req, resp);
  }

  private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    FrontCommand command = getCommand(req);
    command.init(getServletContext(), resp, req);
    command.process();
  }

  private FrontCommand getCommand(HttpServletRequest request) {
    try {
      Class type = Class.forName(
              String.format(
                      "controller.commands.%sCommand",
                      request.getParameter("command")
              )
      );

      return (FrontCommand) type.asSubclass(FrontCommand.class).newInstance();
    } catch (Exception ex) {
      logger.error(ex.getMessage());
      return new UnknownCommand();
    }
  }


}
