package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dtos.Credentials;
import com.revature.hibernate.users.UserService;
import com.revature.models.User;
import com.revature.util.JwtGenerator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "session", urlPatterns = {"/session"})
/**
 *  here for JSON be we didnt have time to implement
 */
public class SessionServlet extends HttpServlet {

    // TODO: try to avoid direct instantiation of service object here
    private final UserService userService = new UserService();

    /**
     *
     * @param req gets the information from the user
     * @param resp can give something back to the user for example a status code
     * @throws IOException error if something goes wrong with writting or getting information
     * @throws ServletException error if something goes wrong with the servlet
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        String ssn = (String) req.getSession().getAttribute("ssn");
        resp.getWriter().write(req.getSession().getId());
    }

    /**
     *
     * @param req gets the information from the user
     * @param resp can give something back to the user for example a status code
     * @throws IOException error if something goes wrong with writting or getting information
     * @throws ServletException error if something goes wrong with the servlet
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        PrintWriter respWriter = resp.getWriter();
        resp.setContentType("application/json");

        try {

            Credentials creds = mapper.readValue(req.getInputStream(), Credentials.class);
            System.out.printf("Attempting to authenticate user, %s, with provided credentials", creds.getUsername());

            User authUser = userService.authenticate(creds.getUsername(), creds.getPassword());
            System.out.println("User succesffuly authenticated!");
            respWriter.write(mapper.writeValueAsString(authUser));

            String token = JwtGenerator.createJwt(authUser);

            Cookie tokenCookie = new Cookie("my-token", token);
            tokenCookie.setHttpOnly(true);
            resp.addCookie(tokenCookie);

//            System.out.printf("Establishing a HTTP session for user, %s", authUser.getUsername());
//            req.getSession().setAttribute("this-user", authUser);


        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500);
        }

    }

    /**
     *
     * @param req gets the information from the user
     * @param resp can give something back to the user for example a status code
     * @throws IOException error if something goes wrong with writting or getting information
     * @throws ServletException error if something goes wrong with the servlet
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
    }
}
