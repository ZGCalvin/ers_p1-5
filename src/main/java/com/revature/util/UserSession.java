package com.revature.util;

import com.revature.models.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

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
