package JinjiKanri.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;

import JinjiKanri.model.Gakureki;
import jdbc.JdbcUtil;

public class GakurekiDao {

    private static GakurekiDao instance = new GakurekiDao();
    
    
    private GakurekiDao() {}
    
    
    public static GakurekiDao getInstance() {
        return instance;
    }
	
	// 김현서 金賢徐
    // p.2 인사기록카드_ 특정 사원의 학력 정보를 조회하는 메소드
	// p.2 人事記録カード_特定社員の学歴情報を照会するメソッド
    public ArrayList<Gakureki> getGakurekiList(Connection conn, Integer shain_id) throws SQLException {
        ArrayList<Gakureki> gakurekiList = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String query = "SELECT gakkoNama, nyugakuNengatsu, sotsugyoNengatsu, senmon, shuuryouJyoutai " +
                           "FROM Gakureki " +
                           "WHERE shain_id = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, shain_id);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Gakureki gakureki = new Gakureki(
                    rs.getString("gakkoNama"),
                    rs.getDate("nyugakuNengatsu"),
                    rs.getDate("sotsugyoNengatsu"),
                    rs.getString("senmon"),
                    rs.getString("shuuryouJyoutai")
                );
                gakurekiList.add(gakureki);
            }
        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(pstmt);
        }

        return gakurekiList;
    }
}
