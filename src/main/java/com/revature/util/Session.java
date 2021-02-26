package com.revature.util;

import com.revature.dtos.Principal;
import com.revature.hibernate.users.UserService;
import com.revature.models.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class Session {

    private static final Session session = new Session();
    private ArrayList<HttpSession> sessionList = new ArrayList<>();


    private Session(){

    }

    public static Session getUserSession(){
        return session;
    }
    public ArrayList<HttpSession> getSessionList(){
        return sessionList;
    }

    public void createSession(HttpServletRequest request, User user){
        HttpSession httpSession = request.getSession();

        httpSession.setAttribute("userId", user.getUserId());
        httpSession.setAttribute("username", user.getUsername());
        httpSession.setAttribute("userRole", user.getUserRole());
        sessionList.add(httpSession);

    }

    public void validUser(HttpServletRequest request){

        HttpSession httpSession = null;
        Principal principal = null;
        Cookie[] cookies = request.getCookies();

        if (httpSession!=null){
            principal = new Principal();
            principal.setId((Integer) httpSession.getAttribute("userId"));
            principal.setUsername((String) httpSession.getAttribute("username"));
            principal.setRole((String) httpSession.getAttribute("userRole"));
        }
        request.setAttribute("principal",principal);
    }

    public void signout(HttpServletRequest request){
        request.getSession().invalidate();
    }

    public void delete(int id){
        for (HttpSession hs: sessionList){
            if((Integer) hs.getAttribute("userId")==id){
                hs.invalidate();
                sessionList.remove(hs);
                break;
            }
        }
    }
}
