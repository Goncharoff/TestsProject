package controller.commands;

import java.io.IOException;
import javax.servlet.ServletException;

/**
 * If command is not defined but user try to made it, then redirect to 404 page
 */
class UnknownCommand extends FrontCommand {
  private static final String NOT_FOUND_ERROR_JSP = "404";
  @Override
  public void process() throws ServletException, IOException {
    forward(NOT_FOUND_ERROR_JSP);
  }
}
