package org.example.tablekioskproject.dao;

import lombok.Cleanup;
import org.example.tablekioskproject.common.ConnectionUtil;
import org.example.tablekioskproject.vo.MemberVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

public enum MemberDAO {
    INSTANCE;

    public int checkExist(String uid, String email) throws Exception {

        String query = "SELECT COUNT(*) FROM tbl_member WHERE (uid = ? or email=?)";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, uid);
        ps.setString(2, email);
        @Cleanup ResultSet rs = ps.executeQuery();

        rs.next();

        return rs.getInt(1);

    }


    // 아이디 중복 확인
    public boolean isUidDuplicate(String uid) throws Exception {
        String query = "SELECT COUNT(*) FROM tbl_member WHERE uid = ?";

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, uid);

        @Cleanup ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0;
        }
        return false;
    }

    public Optional<MemberVO> get(String word, String pw) throws Exception{

        String query = """
                select * from tbl_member
                where
                    (uid = ? or email = ?)
                  and
                    upw = ?
                and
                    delflag = false
                """;

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, word);
        ps.setString(2, word);
        ps.setString(3, pw);

        @Cleanup ResultSet rs = ps.executeQuery();
        if ( !rs.next() ) {
            return Optional.empty(); //optional은 null 대신 사용
        }
        MemberVO member = MemberVO.builder()
                .mno(rs.getInt("mno"))
                .uid(rs.getString("uid"))
                .upw(rs.getString("upw"))
                .email(rs.getString("email"))
                .delFlag(rs.getBoolean("delflag"))
                .build();

        return Optional.of(member);

    }

}