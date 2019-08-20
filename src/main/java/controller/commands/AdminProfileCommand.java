package controller.commands;

import data.response.ResponseWrapper;
import java.io.IOException;
import javax.servlet.ServletException;
import service.AdminService;
import service.ServiceFactory;


public class AdminProfileCommand extends FrontCommand {
    private AdminService adminService = ServiceFactory.getAdminService();

    /**
     * Get info for admin profile.
     *
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void process() throws ServletException, IOException {
        super.process();

        long adminId = (long) request.getSession().getAttribute("userId");

        new ResponseWrapper<>(adminService.getAdminInfo(adminId), response);
    }


}
