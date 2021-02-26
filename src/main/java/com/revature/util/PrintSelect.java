package com.revature.util;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

/**
 * This classes is used for Printing the Select Statements
 * Every row will be in rows instead all in one single line
 */
public class PrintSelect {

    /**
     * Prints the rows of the entity list
     * @param session
     */
    public void printRow(List<?> session, PrintWriter pt) {
        Iterator<?> i = session.iterator();
        while (i.hasNext()) {
            pt.println("<p>"+i.next()+"</p>");
        }
    }

    public void printRowWithOut(List session, PrintWriter pt) {
        Iterator<?> i = session.iterator();
        while (i.hasNext()) {
            pt.println("<p>"+i.next()+"</p>");
        }
    }
}
