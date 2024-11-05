package JinjiKanri.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import JinjiKanri.model.ShikakuMenkyou;
import jdbc.JdbcUtil;

public class ShikakuMenkyouDao {

    private static ShikakuMenkyouDao instance = new ShikakuMenkyouDao();

    private ShikakuMenkyouDao() {}
    
    public static ShikakuMenkyouDao getInstance() {
        return instance;
    }
	
	// 김현서 金現徐
    // p.2 특정 사원의 자격증,면허 정보를 조회하는 메소드
	// p.2 人事記録カード_特定社員の資格免許情報を照会するメソッド
    public ArrayList<ShikakuMenkyou> getShikakuMenkyouByShainId(Connection conn, Integer shain_id) throws SQLException {
        ArrayList<ShikakuMenkyou> shikakuList = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String query = "SELECT shikaku_mei, shutoku_bi, hakkou_kikan, bikou " +
                           "FROM ShikakuMenkyou " +
                           "WHERE shain_id = ?";

            stmt = conn.prepareStatement(query);
            stmt.setInt(1, shain_id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                ShikakuMenkyou shikaku = new ShikakuMenkyou(
                    rs.getString("shikaku_mei"),
                    rs.getDate("shutoku_bi"),
                    rs.getString("hakkou_kikan"),
                    rs.getString("bikou")
                );
                shikakuList.add(shikaku);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(stmt);
        }

        return shikakuList;
    }
}
