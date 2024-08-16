package org.example.tablekioskproject.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.example.tablekioskproject.dao.CustomerDAO;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@WebServlet(value = "/manage")
@Log4j2
public class ManageController extends HttpServlet {
    private final CustomerDAO customerDAO = CustomerDAO.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // 각 테이블 번호별로 totalSum을 계산하여 Map에 저장
            int[] tableNumbers = {1, 2, 3, 4, 5, 6};
            Map<Integer, BigDecimal> tableTotalSums = new HashMap<>();
            for (int tableNumber : tableNumbers) {
                BigDecimal totalSum = customerDAO.getTotalPriceSum(tableNumber);
                tableTotalSums.put(tableNumber, totalSum);
            }

            req.setAttribute("tableTotalSums", tableTotalSums);
            req.getRequestDispatcher("/WEB-INF/kiosk/manage.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
