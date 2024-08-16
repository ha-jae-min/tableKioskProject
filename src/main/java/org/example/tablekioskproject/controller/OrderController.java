package org.example.tablekioskproject.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.example.tablekioskproject.common.CookieUtil;
import org.example.tablekioskproject.common.CookieOrderUtil;
import org.example.tablekioskproject.dao.CustomerDAO;
import org.example.tablekioskproject.vo.OrderDetailVO;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/order")
@Log4j2
public class OrderController extends HttpServlet {
    private final CustomerDAO customerDAO = CustomerDAO.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // 여기서도 쿠키로 뿌려줘야함
            // 모든 주문 상세 정보를 조회

            // OrderUtil을 사용하여 쿠키에서 주문 정보 가져오기
            List<OrderDetailVO> orderDetails = CookieOrderUtil.getCookies(req);
            BigDecimal totalSum = CookieOrderUtil.calculateTotalSum(orderDetails);

            req.setAttribute("totalSum", totalSum);
            req.setAttribute("orderDetails", orderDetails);
            
            req.getRequestDispatcher("/WEB-INF/kiosk/orderDetails.jsp").forward(req, resp);
        } catch (Exception e) {
            log.error("Error processing request", e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "비상 서버오류!!");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            // 여기서는 쿠키 저장 하기
            // HTTP 요청에서 전달된 "table_number"라는 이름의 파라미터 값
            String menuName = req.getParameter("menu_name");
            int tableNumber = Integer.parseInt(req.getParameter("table_number"));
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            int mno = Integer.parseInt(req.getParameter("mno"));
            BigDecimal price = new BigDecimal(req.getParameter("price"));

            BigDecimal totalPrice = price.multiply(new BigDecimal(quantity));

            // 이걸 쿠키에 저장하는 걸로 바꿔야함
            // 쿠키에 주문 정보 추가
            CookieUtil.addOrderToCookie(req, resp, mno, menuName, tableNumber, price, quantity, totalPrice);

            resp.sendRedirect("/order");
        } catch (Exception e) {
            log.error("Error processing order", e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing order");
        }
    }
}
