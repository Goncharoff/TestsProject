package controller;

import data.business.User;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserService;

@WebServlet(name = "LoginController", urlPatterns = "/LoginController")
public class LoginController extends HttpServlet {

    private UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = userService.checkAndGetUser(email, password);

        if (user != null) {
            if (user.getUserRole().getRoleName().equals("ADMIN"))
                request.getRequestDispatcher("jsp/admin_info.jsp").forward(request, response);

            else request.getRequestDispatcher("jsp/user_info.jsp").forward(request, response);
        } else {

        }

    }

}
