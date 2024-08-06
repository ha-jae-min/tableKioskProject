package org.example.tablekioskproject.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.example.tablekioskproject.dao.KioskDAO;
import org.example.tablekioskproject.vo.MenuVO;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/search")
@Log4j2
public class SearchController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("SearchController doGet");

        // doGet에서 폼을 렌더링할 때는 비어 있는 검색 결과를 설정
        req.setAttribute("searchResults", null);

        req.getRequestDispatcher("/WEB-INF/kiosk/search.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("SearchController doPost");

        try {
            // 폼에서 전달된 검색 키워드를 가져옴
            String keyword = req.getParameter("keyword");
            log.info("Search keyword: " + keyword);

            // 검색 결과를 가져옴
            List<MenuVO> searchResults = KioskDAO.INSTANCE.getSearchMenus(keyword);

            // 검색 결과를 request 속성에 설정
            req.setAttribute("searchResults", searchResults);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 검색 결과를 보여주는 JSP로 포워딩
        req.getRequestDispatcher("/WEB-INF/kiosk/search.jsp").forward(req, resp);
    }
}
