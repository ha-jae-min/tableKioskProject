package org.example.tablekioskproject.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.example.tablekioskproject.common.OrderCalculator;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(value = "/order")
@Log4j2
public class OrderController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/kiosk/detail.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // 폼 데이터에서 수량과 가격 가져오기
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            BigDecimal price = new BigDecimal(req.getParameter("price"));

            // OrderCalculator 객체 생성하여 총 가격 계산
            OrderCalculator calculator = new OrderCalculator(quantity, price);
            BigDecimal totalPrice = calculator.calculateTotalPrice();

            // 계산된 총 가격을 요청 속성에 저장
            req.setAttribute("quantity", quantity);
            req.setAttribute("price", price);
            req.setAttribute("totalPrice", totalPrice);

            req.getRequestDispatcher("/WEB-INF/kiosk/detail.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
