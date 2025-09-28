package dao;

import model.Member;
import util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
    public void addMember (Member member) {
        String sql = "INSERT INTO members (member_id, name, email) VALUES (?, ?, ? )";

        try (
                Connection conn = DbUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {

            ps.setInt(1, member.getMemberId());
            ps.setString(2, member.getName());
            ps.setString(3, member.getEmail());

            ps.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Member> getAllMembers () {
        List<Member> members = new ArrayList<>();
        String sql = "SELECT member_id, name, email FROM members";

        try (
                Connection conn = DbUtil.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
        ) {
            while (rs.next()) {
                members.add(new Member(rs.getInt("member_id"), rs.getString("name"), rs.getString("email")));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return members;
    }

}
