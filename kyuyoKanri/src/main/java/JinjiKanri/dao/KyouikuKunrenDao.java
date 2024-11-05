package JinjiKanri.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import JinjiKanri.model.KyouikuKunren;

import java.math.BigDecimal;

import jdbc.JdbcUtil;

public class KyouikuKunrenDao {

	
    private static KyouikuKunrenDao instance = new KyouikuKunrenDao();

    private KyouikuKunrenDao() {}
    
    public static KyouikuKunrenDao getInstance() {
        return instance;
    }
	
	// 김현서 金現徐
    // p.2 인사기록카드 특정 사원의 교육훈련 정보를 조회하는 메소드
	// p.2 人事記録カード_特定社員の教育訓練情報を照会するメソッド
    public ArrayList<KyouikuKunren> getKyouikuKunrenByShainId(Connection conn, Integer shain_id) throws SQLException {

        ArrayList<KyouikuKunren> kyouikuList = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String query = "SELECT kunren_shubetsu, kyouiku_mei, kaishi_bi, shuuryou_bi, kikan, kyouiku_hiyou, henkin_hiyou " +
                           "FROM KyouikuKunren " +
                           "WHERE shain_id = ?";

            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, shain_id);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                KyouikuKunren kyoiku = new KyouikuKunren(
                    rs.getString("kunren_shubetsu"),
                    rs.getString("kyouiku_mei"),
                    rs.getDate("kaishi_bi"),
                    rs.getDate("shuuryou_bi"),
                    rs.getString("kikan"),
                    rs.getBigDecimal("kyouiku_hiyou"),
                    rs.getBigDecimal("henkin_hiyou")
                );
                kyouikuList.add(kyoiku);
            }
        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(pstmt);
        }
        return kyouikuList;
    }
}

