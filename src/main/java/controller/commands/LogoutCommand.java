package controller.commands;

import java.io.IOException;
import javax.servlet.ServletException;

/**
 * Logout from session command.
 */
public class LogoutCommand extends FrontCommand {
    private static final String LOGIN_JSP = "login";

    /**
     * Logout user from session and redirect to login page.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void process() throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect(LOGIN_JSP);
    }
}
