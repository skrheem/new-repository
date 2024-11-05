package JinjiKanri.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import JinjiKanri.model.Heieki;
import jdbc.JdbcUtil;

public class HeiekiDao {
	
    private static HeiekiDao instance = new HeiekiDao();

    private HeiekiDao() {}
    
    public static HeiekiDao getInstance() {
        return instance;
    }
	

	// 김현서 金賢徐
    // p.2 인사기록카드_ 특정 사원의 병역 정보를 조회하는 메소드
	// p.2 人事記録カード_特定社員の兵役情報を照会するメソッド
    public ArrayList<Heieki> getHeiekiByShainId(Connection conn, Integer shain_id) throws SQLException {
        ArrayList<Heieki> heiekiList = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String query = "SELECT heiekiKubun, mirikouRiyuuCode, gunBetsu, Kaikyuu, heika, fukumukiKaishi, fukumukiShuuryou " +
                           "FROM Heieki " +
                           "WHERE shain_id = ?";
            
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, shain_id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Heieki heieki = new Heieki(
                    rs.getString("heiekiKubun"),
                    rs.getString("mirikouRiyuuCode"),
                    rs.getString("gunBetsu"),
                    rs.getString("Kaikyuu"),
                    rs.getString("heika"),
                    rs.getDate("fukumukiKaishi"),
                    rs.getDate("fukumukiShuuryou")
                );
                heiekiList.add(heieki);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(stmt);
        }

        return heiekiList;
    }
}
