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
        List<MenuVO> menuList = new ArrayList<>();
        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement("SELECT *\n" +
                "FROM tbl_k_menu\n" +
                "WHERE is_sold_out = FALSE;");
        @Cleanup ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            MenuVO menu = MenuVO.builder()
                    .mno(rs.get)
                    .name()
                    .price()
                    .build();
        }
        return menuList;
    }
}
