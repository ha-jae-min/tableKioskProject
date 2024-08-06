package org.example.tablekioskproject.dao;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.example.tablekioskproject.common.ConnectionUtil;
import org.example.tablekioskproject.vo.MenuVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public enum KioskDAO {

    INSTANCE;

    KioskDAO(){}

    //모든메뉴 조회
    public List<MenuVO> getAllMenus() throws Exception{
        String query = """
                SELECT mno, name, price, description
                FROM tbl_k_menu
                WHERE is_sold_out = FALSE
                """;

        List<MenuVO> menuList = new ArrayList<>();
        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(query);
        @Cleanup ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            MenuVO menu = MenuVO.builder()
                    .mno(rs.getInt("mno"))
                    .name(rs.getString("name"))
                    .price(rs.getBigDecimal("price"))
                    .description(rs.getString("description"))
                    .build();

            menuList.add(menu);
        }
        return menuList;
    }

    //카테고리별 조회
    public List<MenuVO> getCategoryMenus() throws Exception{
        String query = """
            select
                m.name, m.price, m.is_recommend, m.description, c.name
            from
                tbl_k_menu m
                inner join
                    tbl_k_category c ON m.category_id = c.cno
            order by m.category_id
            """;

        List<MenuVO> menuList = new ArrayList<>();
        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(query);
        @Cleanup ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            MenuVO menu = MenuVO.builder()
                    .name(rs.getString("m.name"))
                    .price(rs.getBigDecimal("m.price"))
                    .isRecommend(rs.getBoolean("m.is_recommend"))
                    .description(rs.getString("m.description"))
                    .build();

            menuList.add(menu);
        }
        return menuList;
    }

    //추천메뉴 조회
    public List<MenuVO> getRecommendMenus() throws Exception{
        String query = """
            select name, price, description
            from tbl_k_menu
            where is_recommend = TRUE
            """;

        List<MenuVO> menuList = new ArrayList<>();
        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(query);
        @Cleanup ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            MenuVO menu = MenuVO.builder()
                    .name(rs.getString("name"))
                    .price(rs.getBigDecimal("price"))
                    .description(rs.getString("description"))
                    .build();

            menuList.add(menu);
        }
        return menuList;
    }

    //메뉴 검색
    public List<MenuVO> getSearchMenus(String keyword) throws Exception{
        String query = """
            SELECT name, price, is_recommend, description
            FROM tbl_k_menu
            WHERE name LIKE ?
            """;

        List<MenuVO> menuList = new ArrayList<>();
        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, "%" + keyword + "%");

        @Cleanup ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            MenuVO menu = MenuVO.builder()
                    .name(rs.getString("name"))
                    .price(rs.getBigDecimal("price"))
                    .description(rs.getString("description"))
                    .build();

            menuList.add(menu);
        }
        return menuList;
    }

}
