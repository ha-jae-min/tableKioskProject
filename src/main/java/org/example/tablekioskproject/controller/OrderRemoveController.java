package org.example.tablekioskproject.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
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

@WebServlet(value = "/remove")
@Log4j2
public class OrderRemoveController extends HttpServlet {
    private final CustomerDAO customerDAO = CustomerDAO.INSTANCE;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int ono = Integer.parseInt(req.getParameter("ono"));
        int mno = Integer.parseInt(req.getParameter("mno"));

        try {
            // 쿠키 삭제로 바꿔야 함
            Cookie[] cookies = req.getCookies();

            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    // 쿠키의 만료 시간을 0으로 설정하여 삭제
                    cookie.setValue("");
                    cookie.setPath("/");  // 모든 경로에서 쿠키 삭제
                    cookie.setMaxAge(0);  // 즉시 만료
                    resp.addCookie(cookie);
                }
            }

            // 여기서 쿠키로 뿌려줘야지 이제
            // 새로고침 용도
            // OrderUtil을 사용하여 쿠키에서 주문 정보 가져오기
            List<OrderDetailVO> orderDetails = CookieOrderUtil.getCookies(req);
            BigDecimal totalSum = CookieOrderUtil.calculateTotalSum(orderDetails);

            req.setAttribute("totalSum", totalSum);
            req.setAttribute("orderDetails", orderDetails);

            req.getRequestDispatcher("/WEB-INF/kiosk/orderDetails.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
