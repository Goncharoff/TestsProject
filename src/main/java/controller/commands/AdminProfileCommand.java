package controller.commands;

import data.responses.AdminInfoResponse;
import data.responses.ResponseWrapper;
import java.io.IOException;
import javax.servlet.ServletException;
import service.AdminService;
import service.ServiceFactory;

public class AdminProfileCommand extends FrontCommand {
    private AdminService adminService = ServiceFactory.getAdminService();

    @Override
    public void process() throws ServletException, IOException {
        //long adminId = (long) request.getSession().getAttribute("username");
        long adminId = 6;
        convertStringToJsonObject(AdminInfoResponse.class);
        new ResponseWrapper<>(adminService.getAdminInfo(adminId), response, 200);
    }


}
