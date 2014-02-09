package com.playtika.calculator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Calculator extends HttpServlet{

    private boolean validate(String first, String second, String action, HttpServletResponse response) throws IOException {
        boolean result = true;
        Set<String> actions = new HashSet<String>();
        actions.add("plus"); actions.add("minus"); actions.add("multiply"); actions.add("divide"); actions.add("square root");
        if ((first == null) || (first.isEmpty())) {
            result = false;
            response.setStatus(400);
            response.getWriter().println("Please, enter first number.<br/>");
        } else {
            try {
                Double.valueOf(first);
            } catch (NumberFormatException e) {
                result = false;
                response.setStatus(400);
                response.getWriter().println("Please, enter correct first number.<br/>");
            }
        }
        if ((action == null) || (action.isEmpty())) {
            result = false;
            response.setStatus(400);
            response.getWriter().println("Please, enter an action.<br/>");
        } else {
            if (!actions.contains(action)) {
                result = false;
                response.setStatus(400);
                response.getWriter().println("Please, enter correct action.<br/>");
            } else {
                if (((second == null) || (second.isEmpty())) && !(action.equals("square root"))) {
                    result = false;
                    response.setStatus(400);
                    response.getWriter().println("Please, enter second number.<br/>");
                } else {
                    if (!(action.equals("square root"))) {
                        try {
                            Double.valueOf(second);
                        } catch (NumberFormatException e) {
                            result = false;
                            response.setStatus(400);
                            response.getWriter().println("Please, enter correct second number.<br/>");
                        }
                    }
                }
            }
        }
        if (!(second == null) && !(action == null) && second.equals("0") && action.equals("/")) {
            result = false;
            response.setStatus(400);
            response.getWriter().println("You can't divide by zero!<br/>");
        }
        if (!((first == null) || (first.isEmpty())) && !(action == null) && (first.charAt(0) == '-') && action.equals("square root")) {
            result = false;
            response.setStatus(400);
            response.getWriter().println("You can't get square root from negative number!<br/>");
        }
        return result;
    }

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String firstNumber = request.getParameter("first");
        String action = request.getParameter("action");
        String secondNumber = request.getParameter("second");
        Double result = null;
        if (validate(firstNumber, secondNumber, action, response)) {
            if (action.equals("plus")) {
                response.setStatus(200);
                result = Double.valueOf(firstNumber) + Double.valueOf(secondNumber);
            } else {
                if (action.equals("minus")) {
                    response.setStatus(200);
                    result = Double.valueOf(firstNumber) - Double.valueOf(secondNumber);
                } else {
                    if (action.equals("multiply")) {
                        response.setStatus(200);
                        result = Double.valueOf(firstNumber) * Double.valueOf(secondNumber);
                    } else {
                        if (action.equals("divide")) {
                            response.setStatus(200);
                            result = Double.valueOf(firstNumber) / Double.valueOf(secondNumber);
                        } else {
                            if (action.equals("square root")) {
                                response.setStatus(200);
                                result = Math.sqrt(Double.valueOf(firstNumber));
                            }
                        }
                    }
                }
            }
        }
        if (result != null) {
            response.getWriter().write(result.toString());
        }
    }
}
