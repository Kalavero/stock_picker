package controller;

import dao.UserDAO;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "UsersController")
public class UsersController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<User> users = new UserDAO().all();
        PrintWriter out = response.getWriter();
        for(int i = 0; i < users.size(); i++){
            out.println(users.get(i).getId());
            out.println(users.get(i).getName());
            out.println(users.get(i).getEmail());
            out.println(users.get(i).getCpf());
            out.println(users.get(i).getBirthday());
        }
    }
}
