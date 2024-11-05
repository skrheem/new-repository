package JinjiKanri.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KoukinzeiDao {

	
    private static KoukinzeiDao instance = new KoukinzeiDao();

    private KoukinzeiDao() {}
    
    public static KoukinzeiDao getInstance() {
        return instance;
    }
    
    // 김현서 金現徐
    // p.1 인사관리 사원정보 미리보기 팝업 / 갑근세 명
	// p.1 人事管理 社員情報プレビューポップアップ/甲勤三人
    public String getPopupShainList(Connection conn, int shainId) {
        String query = "SELECT koukinzei_kubun FROM koukinzei WHERE shain_id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, shainId); // shain_id 값을 설정
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString(1); // 첫 번째 컬럼의 값을 반환
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // 조회된 결과가 없을 경우 0 반환
    }
    
    
}
