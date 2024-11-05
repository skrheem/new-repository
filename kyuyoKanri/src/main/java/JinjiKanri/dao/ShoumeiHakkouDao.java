package JinjiKanri.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class ShoumeiHakkouDao {

    private static ShoumeiHakkouDao instance = new ShoumeiHakkouDao();

    private ShoumeiHakkouDao() {}
    
    public static ShoumeiHakkouDao getInstance() {
        return instance;
    }
	
	// 김현서 金賢徐
    // p.4 증명서 파일 경로 조회 메소드
	// p.4 証明書ファイル経路照会メソッド
    public String getFileKeiro(Connection conn, int shoumeisho_id) throws SQLException {
        String query = "SELECT file_keiro FROM ShoumeiHakkou WHERE shoumeisho_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, shoumeisho_id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("file_keiro");
                }
            }
        }
        return null; // 경로가 없으면 null 반환
    }

    
    // 김현서 金賢徐
    // p.3 증명서발행 테이블에 증명서의 경로 주소와 기타 정보 저장
    // p.3 証明書発行テーブルに証明書の経路アドレスとその他の情報を保存
    public int saveShoumeishoJouHou(Connection conn, Integer shoumeishoId, Integer shain_id, String hakkouType, String fileKeiro, java.util.Date hakkouBi) throws SQLException {
        String query = "INSERT INTO ShoumeishoHakkou (shoumeisho_id, shain_id, hakkou_type, file_keiro, hakkou_bi) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, shoumeishoId);               // 1번 인덱스
            pstmt.setInt(2, shain_id);                   // 2번 인덱스
            pstmt.setString(3, hakkouType);              // 3번 인덱스
            pstmt.setString(4, fileKeiro);               // 4번 인덱스
            pstmt.setDate(5, new java.sql.Date(hakkouBi.getTime())); // 5번 인덱스

            return pstmt.executeUpdate(); // 삽입 성공 시 1 반환
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // 예외를 호출한 곳으로 던짐
        } finally {
            JdbcUtil.close(pstmt); // 리소스 해제
        }
    }

    
    
    

}
