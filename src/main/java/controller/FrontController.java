package controller;

import controller.commands.CommandFactory;
import controller.commands.FrontCommand;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of front controller pattern;
 *
 */
@WebServlet("/app/*")
public class FrontController extends HttpServlet {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


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

    /**
     * Looks for command in commands folder and then doing them.
     *
     * @param request http request with params
     * @return Command to make or 404 page if command not found.
     */
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
            return CommandFactory.getUnknownCommand();
        }
    }


}
