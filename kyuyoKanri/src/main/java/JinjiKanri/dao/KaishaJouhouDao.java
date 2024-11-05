package JinjiKanri.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import JinjiKanri.model.KaishaJouhou;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;



public class KaishaJouhouDao {


	private static KaishaJouhouDao instance = new KaishaJouhouDao();

	private KaishaJouhouDao() {}
	
	public static KaishaJouhouDao getInstance() {
		return instance;
	}

	// 김현서 金現徐
    // p.2 인사기록카드_ 대표자명, 인감도장 이미지 경로를 가져오는 메소드
	// p.2 人事記録カード_ 代表者名、印鑑 イメージ パスを取得するメソッド
    public KaishaJouhou getKaishaJouhou(Connection conn) throws SQLException {
        KaishaJouhou kaishaJouhou = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String query = "SELECT Kaihyousha_mei, inkan_image_keiro FROM KaishaJouhou";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                kaishaJouhou = new KaishaJouhou(
                    rs.getString("kaihyousha_mei"),
                    rs.getString("inkan_image_keiro")
                );
            }
        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(pstmt);
        }
        return kaishaJouhou;
    }
    
    
    // 김현서 金賢徐
    // p.3 회사명, 사업자번호를 출력 / 제증명서발급_ 재직,퇴직 증명서에서 사용
    // p.3 会社名、事業者番号を出力/諸証明書発行_在職、退職証明書で使用
    public KaishaJouhou getKaishaJouhouByShoumeisho(Connection conn) throws SQLException {
        KaishaJouhou kaishaJouhou = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String query = "SELECT kaisha_mei, jigyousha_touroku_bango FROM KaishaJouhou";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                kaishaJouhou = new KaishaJouhou(
                    rs.getString("kaisha_mei"),
                    rs.getString("jigyousha_touroku_bango")
                );
            }
        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(pstmt);
        }
        return kaishaJouhou;
    }

    

    
    
}
