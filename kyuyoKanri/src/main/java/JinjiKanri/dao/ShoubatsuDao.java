package JinjiKanri.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import JinjiKanri.model.Shoubatsu;
import jdbc.JdbcUtil;

public class ShoubatsuDao {
	
    private static ShoubatsuDao instance = new ShoubatsuDao();

    private ShoubatsuDao() {}
    
    public static ShoubatsuDao getInstance() {
        return instance;
    }

	// 김현서 金現徐
    // p.2 특정 사원의 상벌 정보를 조회하는 메소드
	// p.2 人事記録カード_特定社員の賞罰情報を照会するメソッド
    public ArrayList<Shoubatsu> getShobatsuByShainId(Connection conn, Integer shain_id) throws SQLException {

        ArrayList<Shoubatsu> shoubatsuList = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String query = "SELECT shouBatsuKubun, shouBatsuMei, shouBatsuSha, shouBatsu_bi, shouBatsuNaiyou, bikou " +
                           "FROM Shoubatsu " +
                           "WHERE shain_id = ?";

            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, shain_id);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Shoubatsu shoubatsu = new Shoubatsu(
                    rs.getString("shouBatsuKubun"),
                    rs.getString("shouBatsuMei"),
                    rs.getString("shouBatsuSha"),
                    rs.getDate("shouBatsu_bi"),
                    rs.getString("shouBatsuNaiyou"),
                    rs.getString("bikou")
                );
                shoubatsuList.add(shoubatsu);
            }
        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(pstmt);
        }
        return shoubatsuList;
    }
}
