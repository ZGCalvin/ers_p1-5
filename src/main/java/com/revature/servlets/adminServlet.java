package com.revature.servlets;

import com.revature.hibernate.users.UserService;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.util.PrintSelect;
import com.revature.util.UserSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        name="adminServlet",
        description= "Admin page",
        urlPatterns = {"/adminServlet"}
)

/**
 * a servlet class that has a doGet and a doPost in it
 * the get can view all the Users and format it
 * the post can add a new user, update an existing user's fields and delete will "delete" the user
 */
public class adminServlet extends HttpServlet {

    private PrintSelect printSelect = new PrintSelect();

    /**
     *
     * @param request gets the information from the user
     * @param response can give something back to the user for example a status code
     * @throws IOException error if something goes wrong with writting or getting information
     * @throws ServletException error if something goes wrong with the servlet
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter pt = response.getWriter();
        String method = request.getParameter("method");
        if(UserSession.getUserSession().getSession().getAttribute("role").equals(1)) {
            UserService userService = new UserService();
            switch(method) {
                case "ViewAll":
                    //pt.println(userService.viewAllUsers());
                    printSelect.printRowWithOut(userService.viewAllUsers(),pt);
                default:
                    response.setStatus(404);
            }


        }
        else{
            response.setStatus(403);
        }
    }

    /**
     *
     * @param request gets the information from the user
     * @param response can give something back to the user for example a status code
     * @throws IOException error if something goes wrong with writting or getting information
     * @throws ServletException error if something goes wrong with the servlet
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter pt = response.getWriter();
        String method = request.getParameter("method");

       if(UserSession.getUserSession().getSession().getAttribute("role").equals(1)){
           UserService userService = new UserService();
           String id;
           String email;
           String lastname;
           String firstname;

           switch (method){

               case "Add":
                   String username = request.getParameter("username");
                   String password = request.getParameter("password");
                   firstname = request.getParameter("firstname");
                   lastname = request.getParameter("lastname");
                   email = request.getParameter("email");
                   String role = request.getParameter("role");
                   User newUser = new User(username,password,firstname,lastname,email,Role.getByName(role).ordinal()+1);
                   userService.AddUser(newUser);
                   pt.println("Added User" + firstname + lastname);
                   break;
               case "Delete" :
                    id = request.getParameter("id");
                   userService.deleteUser(Integer.valueOf(id));
                            break;
               case "UpdatePassword":
                   String UPid = request.getParameter("id");
                   String UPpassword = request.getParameter("password");
                   userService.updatePassword(Integer.valueOf(UPid),UPpassword);
                   pt.println("Updated Password");
                            break;
               case "UpdateEmail":
                   id = request.getParameter("id");
                   email = request.getParameter("email");
                   userService.updateEmail(Integer.valueOf(id),email);
                   pt.println("Updated Email");
                   break;
               case "UpdateName":
                   id = request.getParameter("id");
                   firstname = request.getParameter("firstname");
                   lastname = request.getParameter("lastname");
                   userService.updateFirstLast(Integer.valueOf(id),firstname,lastname);
                   pt.println("Updated Full Name");
                   break;
               case "UpdateRole":
                   String Roleid = request.getParameter("id");
                   String Rolerole = request.getParameter("role");

                   userService.updateRole(Integer.valueOf(Roleid),Role.getByName(Rolerole).ordinal()+1);
                   pt.println("Updated User Role");
                   break;

               default:
                   response.setStatus(404);
           }

       }
       else{
           response.setStatus(403);
       }


    }
}
