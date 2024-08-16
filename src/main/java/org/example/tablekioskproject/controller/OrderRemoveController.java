package org.example.tablekioskproject.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
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

@WebServlet(value = "/remove")
@Log4j2
public class OrderRemoveController extends HttpServlet {
    private final CustomerDAO customerDAO = CustomerDAO.INSTANCE;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int mno = Integer.parseInt(req.getParameter("mno"));  // 메뉴 번호 (mno)

        try {
            // 쿠키에서 해당 mno에 해당하는 항목을 삭제
            CookieUtil.removeOrderFromCookie(req, resp, mno);

            // 새로고침 용도로 리다이렉트
            resp.sendRedirect("/order");
        } catch (Exception e) {
            log.error("Error removing order", e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error removing order");
        }
    }
}
