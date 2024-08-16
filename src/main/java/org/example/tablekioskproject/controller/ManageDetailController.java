package org.example.tablekioskproject.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.example.tablekioskproject.common.CookieUtil;
import org.example.tablekioskproject.dao.CustomerDAO;
import org.example.tablekioskproject.vo.OrderDetailVO;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet(value = "/manageDetails")
@Log4j2
public class ManageDetailController extends HttpServlet {

    private final CustomerDAO customerDAO = CustomerDAO.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            log.info("나오고 있어요");
            int table_number = Integer.parseInt(req.getParameter("table_number"));

            // 주문 상세 정보를 가져옴
            List<OrderDetailVO> orderDetails = customerDAO.getAllOrderDetailsFromDB(table_number);

            BigDecimal totalSum = customerDAO.getTotalPriceSum(table_number);

            // 3. JSP에 데이터 전달 및 포워딩
            req.setAttribute("table_number", table_number);
            req.setAttribute("orderDetails", orderDetails);
            req.setAttribute("totalSum", totalSum);


            req.getRequestDispatcher("/WEB-INF/kiosk/manageDetails.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            int ono = Integer.parseInt(req.getParameter("ono"));
            customerDAO.updateOrderStatus(ono, "주문 완료", 1, 1);

            resp.sendRedirect("/manageDetails?table_number=" + req.getParameter("table_number"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
