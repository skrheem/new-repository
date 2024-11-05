package JinjiKanri.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import JinjiKanri.model.Hatsurei;
import jdbc.JdbcUtil;

public class HatsureiDao {

    private static HatsureiDao instance = new HatsureiDao();

    private HatsureiDao() {}
    
    public static HatsureiDao getInstance() {
        return instance;
    }
	
	// 김현서 金現徐
    // p.2 인사기록카드 특정 사원의 발령 정보를 조회하는 메소드
	// p.2 人事記録カード 人事記録カード_特定社員の発令情報を照会するメソッド
    public ArrayList<Hatsurei> getHatsureiByShainId(Connection conn, Integer shain_id) throws SQLException {

        ArrayList<Hatsurei> hatsureiList = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String query = "SELECT hatsureiKubun, hatsureiNichi, busho, yakushoku, shokuseki, bikou " +
                           "FROM Hatsurei " +
                           "WHERE shain_id = ?";

            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, shain_id);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Hatsurei hatsurei = new Hatsurei(
                    rs.getString("hatsureiKubun"),
                    rs.getDate("hatsureiNichi"),
                    rs.getString("busho"),
                    rs.getString("yakushoku"),
                    rs.getString("shokuseki"),
                    rs.getString("bikou")
                );
                hatsureiList.add(hatsurei);
            }
        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(pstmt);
        }
        return hatsureiList;
    }
}
