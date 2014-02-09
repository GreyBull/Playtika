package com.playtika.calculator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Calculator extends HttpServlet{

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String firstNumber = request.getParameter("first");
        String action = request.getParameter("action");
        String secondNumber = request.getParameter("second");
        Double result = null;
        if (action.equals("plus")) {
            result = Double.valueOf(firstNumber) + Double.valueOf(secondNumber);
        } else {
            if (action.equals("minus")) {
                result = Double.valueOf(firstNumber) - Double.valueOf(secondNumber);
            } else {
                if (action.equals("multiply")) {
                    result = Double.valueOf(firstNumber) * Double.valueOf(secondNumber);
                } else {
                    if (action.equals("divide")) {
                        result = Double.valueOf(firstNumber) / Double.valueOf(secondNumber);
                    } else {
                        if (action.equals("square root")) {
                            result = Math.sqrt(Double.valueOf(firstNumber));
                        } else {

                        }
                    }
                }
            }
        }
        response.setStatus(200);
        response.getWriter().write(result.toString());
    }
}
