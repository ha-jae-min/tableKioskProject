package org.example.tablekioskproject.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.example.tablekioskproject.vo.MenuVO;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/menu")
@Log4j2
public class MenuListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("MenuListController doGet");

        try {
            List<MenuVO> menuList = KioskDAO.INSTANCE.getAllMenus();

            log.info(menuList);

            req.setAttribute("menuList", menuList);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        req.getRequestDispatcher("/WEB-INF/kiosk/menu.jsp").forward(req, resp);
    }
}
