package org.example.tablekioskproject.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.example.tablekioskproject.dao.CustomerDAO;
import org.example.tablekioskproject.vo.DetailVO;
import org.example.tablekioskproject.vo.OrderDetailVO;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet(value = "/detail")
@Log4j2
public class DetailController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // 주문 상세 정보를 가져옴
            List<OrderDetailVO> orderDetails = CustomerDAO.INSTANCE.getAllOrderDetails();
            // 총합을 가져옴
            BigDecimal totalSum = CustomerDAO.INSTANCE.getTotalPriceSum();

            // 요청 속성에 주문 상세 정보와 총합을 설정
            req.setAttribute("orderDetails", orderDetails);
            req.setAttribute("totalSum", totalSum);

            // JSP 페이지로 포워딩
            req.getRequestDispatcher("/WEB-INF/kiosk/detail.jsp").forward(req, resp);
        } catch (Exception e) {
            log.error("Error fetching order details", e);
            throw new ServletException(e);
        }
    }
}
