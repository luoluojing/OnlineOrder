package com.laioffer.onlineOrder;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import org.json.JSONObject;
import org.apache.commons.io.IOUtils;
import com.laioffer.onlineOrder.entity.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebServlet(name = "helloServlet", value = "/hello-servlet") // resource path
public class HelloServlet extends HttpServlet { //HttpServlet： abstract class
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //response.setContentType("text/html");
        /*
        response.setContentType("application/json");
        JSONObject customer = new JSONObject();
        customer.put("email", "jinggelaioffer@gmail.com");
        customer.put("first_name", "jing");
        customer.put("last_name", "ge");
        customer.put("age", 21);
        response.getWriter().println(customer);
         */

        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        Customer customer = new Customer();
        customer.setEmail("jingge@laioffer.com");
        customer.setPassword("123456");
        customer.setFirstName("jing");
        customer.setLastName("ge");
        customer.setEnabled(true);

        response.getWriter().print(mapper.writeValueAsString(customer));

        /*
        String customer = request.getParameter("customer");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Hello " + customer + "</h1>");
        out.println("</body></html>");
        */

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject jsonRequest = new JSONObject(IOUtils.toString(request.getReader()));
        String email = jsonRequest.getString("email");
        String firstName = jsonRequest.getString("first_name");
        String lastName = jsonRequest.getString("last_name");
        int age = jsonRequest.getInt("age");

        System.out.println("Email is: " + email);
        System.out.println("First name is: " + firstName);
        System.out.println("Last name is: " + lastName);
        System.out.println("Age is " + age);

        response.setContentType("application/json");
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("status", "ok");
        response.getWriter().print(jsonResponse);
    }


    public void destroy() {
    }
}