package com.revature.util;

import com.revature.models.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * This gets a persistent session that can keep information even when on servlet goes to another
 */
public class UserSession {

        private static final UserSession userSession = new UserSession();
        public static HttpSession session;

        private UserSession(){

        }

        public static UserSession getUserSession(){
            return userSession;
        }

        public HttpSession getSession(){
                return session;
        }

    /**
     *
     * @param request  take in the user request information
     * @param loginuser take in the User object with the information and set the attribute of the session to its fields
     * @return  returns a HttpSession so that we can get the session back and still use it later
     */
        public HttpSession createSession(HttpServletRequest request, User loginuser){
            session = request.getSession();
            session.setAttribute("user_id",loginuser.getUserId());
            session.setAttribute("username",loginuser.getUsername());
            session.setAttribute("fullname",loginuser.getFirstname() + " " + loginuser.getLastname());
            session.setAttribute("email",loginuser.getEmail());
            session.setAttribute("role",loginuser.getUserRole());
            return session;
        }


    }
