package controller.commands;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

/**
 * Logout from session command.
 */
public class LogoutCommand extends FrontCommand {
    private static final String LOGIN_JSP = "/login";

    /**
     * Logout user from session and redirect to login page.
     *
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void process() throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null) {
            request.getSession().invalidate();
        }

        response.sendRedirect(request.getContextPath() + LOGIN_JSP);
    }
}
