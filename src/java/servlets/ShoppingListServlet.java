/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author khanhhoanguyen
 */
public class ShoppingListServlet extends HttpServlet {


  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String username = null;
        
        if (session != null){
            username = (String)session.getAttribute("username");
        }
        
        if(username != null){
            String action = request.getParameter("action");
            
            if(action.equals("logout")){ 
                session.invalidate();
            }
            else {
                request.setAttribute("username", username);
                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp" )
                    .forward(request, response); 
                return;
            }
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/register.jsp" )
            .forward(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //gets value of hidden input tag named action
        String action = request.getParameter("action");
        
        //only instantiates if object exists otherwise null
        HttpSession session = request.getSession(false);
        
        switch (action){
            case "register":
                //creation of Session, String, and ArrayList object
                session = request.getSession();
                String username = request.getParameter("username");
                ArrayList<String> items = new ArrayList<>();
                
                //storing String and ArrayList object within session
                session.setAttribute("username", username);
                session.setAttribute("items", items);
                break;
                
            case "add":
                //gets value of input tag with name item
                String item = request.getParameter("item");
                //gets ArrayList stored within session
                items = (ArrayList<String>)session.getAttribute("items"); 
                //add item to array and setting it once again in the session object
                items.add(item);
                session.setAttribute("items", items);
                break;  
                
            case "delete":
                items = (ArrayList<String>)session.getAttribute("items");
                
                for (int i = 0; i < items.size(); i++) {
                    String elem = items.get(i);
                    
                    //if radio button is checked, value of tag is returned otherwise null
                    String checked = request.getParameter(elem);
                    
                    if(elem.equals(checked)) {
                        items.remove(i--);// decrement i to compensate
                    }
                }
                session.setAttribute("items", items);
                break;   
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp" )
                    .forward(request, response); 
    }

    
}
