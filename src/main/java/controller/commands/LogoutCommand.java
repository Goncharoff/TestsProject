package controller.commands;

import java.io.IOException;
import javax.servlet.ServletException;

public class LogoutCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect("login");
    }
}
