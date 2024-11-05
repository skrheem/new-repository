package JinjiKanri.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import JinjiKanri.model.GogakuNouryoku;
import jdbc.JdbcUtil;

public class GogakuNouryokuDao {
	
    private static GogakuNouryokuDao instance = new GogakuNouryokuDao();

    private GogakuNouryokuDao() {}
    
    public static GogakuNouryokuDao getInstance() {
        return instance;
    }
	
	
	// 김현서 金現徐
    // p.2 인사기록카드 특정 사원의 어학 정보를 조회하는 메소드
	// p.2 人事記録カード 人事記録カード_特定社員の語学情報を照会するメソッド
    public static ArrayList<GogakuNouryoku> getGogakuByShainId(Connection conn, Integer shain_id) throws SQLException {

        ArrayList<GogakuNouryoku> gogakuList = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String query = "SELECT gengo, shiken_mei, tensuu, shutoku_bi, dokkaiNouryoku, sakubunNouryoku, kaiwaNouryoku " +
                           "FROM GogakuNouryoku " +
                           "WHERE shain_id = ?";

            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, shain_id);

            rs = pstmt.executeQuery();

            while (rs.next()) {
            	GogakuNouryoku gogaku = new GogakuNouryoku(
                    rs.getString("gengo"),
                    rs.getString("shiken_mei"),
                    rs.getInt("tensuu"),
                    rs.getDate("shutoku_bi"),
                    rs.getString("dokkaiNouryoku"),
                    rs.getString("sakubunNouryoku"),
                    rs.getString("kaiwaNouryoku")
                );
                gogakuList.add(gogaku);
            }
        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(pstmt);
        }
        return gogakuList;
    }
}
