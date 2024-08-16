package org.example.tablekioskproject.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.example.tablekioskproject.common.CookieOrderUtil;
import org.example.tablekioskproject.common.CookieUtil;
import org.example.tablekioskproject.dao.CustomerDAO;
import org.example.tablekioskproject.vo.OrderDetailVO;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet(value = "/detail")
@Log4j2
public class DetailController extends HttpServlet {
    private final CustomerDAO customerDAO = CustomerDAO.INSTANCE;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // 1. 쿠키에 있는 주문 정보를 DB로 저장
            customerDAO.createOrdersFromCookie(req);  // 쿠키에서 주문 정보를 가져와서 DB에 저장

            // 주문 상세 정보를 가져옴
            List<OrderDetailVO> orderDetails = customerDAO.getAllOrderDetailsFromDB();

            BigDecimal totalSum = customerDAO.getTotalPriceSum();  // 필요한 경우 수정 가능

            // 3. JSP에 데이터 전달 및 포워딩
            req.setAttribute("orderDetails", orderDetails);
            req.setAttribute("totalSum", totalSum);

            CookieUtil.removeAllOrderCookies(req, resp);

            req.getRequestDispatcher("/WEB-INF/kiosk/detail.jsp").forward(req, resp);

        } catch (Exception e) {
            log.error("Error saving order details", e);
            throw new ServletException(e);
        }
    }
}
