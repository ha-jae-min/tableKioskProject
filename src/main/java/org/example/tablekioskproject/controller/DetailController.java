package org.example.tablekioskproject.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.example.tablekioskproject.common.CookieOrderUtil;
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

            // OrderUtil을 사용하여 쿠키에서 주문 정보 가져오기
            List<OrderDetailVO> orderDetails = CookieOrderUtil.getCookies(req);
            BigDecimal totalSum = CookieOrderUtil.calculateTotalSum(orderDetails);

            // 여기서 쿠키에 있는 걸 DB로 넣는 거 하자



            req.setAttribute("totalSum", totalSum);
            req.setAttribute("orderDetails", orderDetails);

            req.getRequestDispatcher("/WEB-INF/kiosk/orderDetails.jsp").forward(req, resp);
        } catch (Exception e) {
            log.error("Error fetching order details", e);
            throw new ServletException(e);
        }
    }
}
