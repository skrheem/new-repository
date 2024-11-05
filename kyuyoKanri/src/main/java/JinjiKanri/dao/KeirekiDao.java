package JinjiKanri.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import JinjiKanri.model.Keireki;
import jdbc.JdbcUtil;

public class KeirekiDao {

    private static KeirekiDao instance = new KeirekiDao();

    private KeirekiDao() {}
    
    public static KeirekiDao getInstance() {
        return instance;
    }

	// 김현서 金現徐
    // p.2 인사기록카드 특정 사원의 경력 정보를 조회하는 메소드
	// p.2 人事記録カード 人事記録カード_特定社員の経歴情報を照会するメソッド
    public ArrayList<Keireki> getKeirekiByShainId(Connection conn, Integer shain_id) throws SQLException {
        ArrayList<Keireki> keirekiList = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String query = "SELECT kaishaNama, nyusha_bi, taisha_bi, saigoShokui, tantouShigoto " +
                           "FROM Keireki " +
                           "WHERE shain_id = ?";

            stmt = conn.prepareStatement(query);
            stmt.setInt(1, shain_id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Keireki keireki = new Keireki(
                    rs.getString("kaishaNama"),
                    rs.getDate("nyusha_bi"),
                    rs.getDate("taisha_bi"),
                    rs.getString("saigoShokui"),
                    rs.getString("tantouShigoto")
                );
                keirekiList.add(keireki);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(stmt);
        }

        return keirekiList;
    }
}
