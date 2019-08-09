package controller.commands;

import java.io.IOException;
import javax.servlet.ServletException;

/**
 * If command is not defined but user try to made it, then redirect to 404 page
 */
class UnknownCommand extends FrontCommand {
  @Override
  public void process() throws ServletException, IOException {
    forward("404");
  }
}
