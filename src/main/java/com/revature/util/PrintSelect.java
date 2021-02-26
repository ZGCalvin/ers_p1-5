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
     *  formats a select query so that it looks a little cleaner
     * @param session a List to be iterated through
     * @param pt pass in the printWriter to format
     */
    public void printRow(List<?> session, PrintWriter pt) {
        Iterator<?> i = session.iterator();
        while (i.hasNext()) {
            pt.println("<p>"+i.next()+"</p>");
        }
    }

    /**
     *  formats a select query so that it looks a little cleaner
     * @param session a List to be iterated through
     * @param pt pass in the printWriter to format
     */
    public void printRowWithOut(List session, PrintWriter pt) {
        Iterator<?> i = session.iterator();
        while (i.hasNext()) {
            pt.println("<p>"+i.next()+"</p>");
        }
    }
}
