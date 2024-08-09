package org.example.tablekioskproject.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.example.tablekioskproject.dao.CustomerDAO;
import org.example.tablekioskproject.vo.OrderDetailVO;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/order")
@Log4j2
public class OrderController extends HttpServlet {
    private CustomerDAO customerDAO;

    @Override
    public void init() throws ServletException {
        customerDAO = CustomerDAO.INSTANCE;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // 모든 주문 상세 정보를 조회
            List<OrderDetailVO> orderDetails = customerDAO.getAllOrderDetails();
            BigDecimal totalSum = customerDAO.getTotalPriceSum();
            req.setAttribute("orderDetails", orderDetails);
            req.setAttribute("totalSum", totalSum);
            
            req.getRequestDispatcher("/WEB-INF/kiosk/orderDetails.jsp").forward(req, resp);
        } catch (Exception e) {
            log.error("Error processing request", e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "비상 서버오류!!");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int tableNumber = Integer.parseInt(req.getParameter("table_number"));
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            int mno = Integer.parseInt(req.getParameter("mno"));

            BigDecimal price = customerDAO.getMenuPriceById(mno);
            BigDecimal totalPrice = price.multiply(new BigDecimal(quantity));

            customerDAO.createOrderWithDetail(tableNumber, mno, quantity, totalPrice);

            List<OrderDetailVO> orderDetails = customerDAO.getAllOrderDetails();
            BigDecimal totalSum = customerDAO.getTotalPriceSum();
            req.setAttribute("totalSum", totalSum);
            req.setAttribute("orderDetails", orderDetails);

            req.getRequestDispatcher("/WEB-INF/kiosk/orderDetails.jsp").forward(req, resp);
        } catch (Exception e) {
            log.error("Error processing order", e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing order");
        }
    }
}
