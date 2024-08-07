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

@WebServlet(value = "/remove")
@Log4j2
public class OrderRemoveController extends HttpServlet {
    private CustomerDAO customerDAO;

    @Override
    public void init() throws ServletException {
        customerDAO = CustomerDAO.INSTANCE;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int ono = Integer.parseInt(req.getParameter("ono"));
        int mno = Integer.parseInt(req.getParameter("mno"));

        try {
            customerDAO.deleteOrderDetail(ono, mno);

            BigDecimal totalSum = customerDAO.getTotalPriceSum();

            // 새로고침 용도
            List<OrderDetailVO> orderDetails = customerDAO.getAllOrderDetails();
            req.setAttribute("orderDetails", orderDetails);
            req.setAttribute("totalSum", totalSum);
            req.getRequestDispatcher("/WEB-INF/kiosk/orderDetails.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
