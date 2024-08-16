package org.example.tablekioskproject.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import lombok.extern.log4j.Log4j2;
import org.example.tablekioskproject.dao.MemberDAO;
import org.example.tablekioskproject.vo.MemberVO;

import java.io.IOException;
import java.util.Optional;

@WebServlet(value = "/login")
@Log4j2
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("LoginController doGet");

        req.getRequestDispatcher("/WEB-INF/kiosk/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uid = req.getParameter("uid");
        String upw = req.getParameter("upw");

        log.info("--------------");
        log.info(uid);
        log.info(upw);

        // JSESSION_ID가 세션저장소에 있으면 반환, 없으면 생성
        HttpSession session = req.getSession();

        // DB에서 해당 사용자 정보를 확인해서 사용자 정보 얻어온다
        try {
            Optional<MemberVO> result = MemberDAO.INSTANCE.get(uid, upw);
            result.ifPresentOrElse(memberVO -> {

                Cookie loginCookie = new Cookie("member", uid);
                loginCookie.setPath("/");
                loginCookie.setMaxAge(60 * 60 * 24);

                resp.addCookie(loginCookie);
                try {
                    resp.sendRedirect("/manage");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }, () -> {
                try {
                    resp.sendRedirect("/login");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}