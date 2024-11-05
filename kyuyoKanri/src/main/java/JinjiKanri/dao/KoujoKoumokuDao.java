package JinjiKanri.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import JinjiKanri.model.KoujoKoumoku;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class KoujoKoumokuDao {

    private static KoujoKoumokuDao instance = new KoujoKoumokuDao();

    private KoujoKoumokuDao() {}
    
    public static KoujoKoumokuDao getInstance() {
        return instance;
    }


	// 김현서 金賢徐
	// p.1 인사관리 사원정보 미리보기 팝업 리스트 / 4대보험 공제여부
	// p.1 人事管理 社員情報プレビューポップアップリスト/4大保険の控除可否
	public ArrayList<KoujoKoumoku> getPopupShainList(Connection conn, int shainId) throws SQLException {
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    ArrayList<KoujoKoumoku> koujoKoumokuList = new ArrayList<>();

	    try {
	        String query = "SELECT k.koujokoumoku_mei AS koujokoumoku_mei, "
	                     + "CASE WHEN st.shain_id IS NOT NULL THEN '控除' ELSE '非控除' END AS tekiyoukahi "
	                     + "FROM koujokoumoku k "
	                     + "LEFT JOIN ShainTekiyouKoujoKoumoku st "
	                     + "ON st.koujokoumoku_id = k.koujokoumoku_id "
	                     + "AND st.shain_id = ? "
	                     + "WHERE k.koujokoumoku_mei IN ('国民年金', '健康保険', '雇用保険')";

	        stmt = conn.prepareStatement(query);
	        stmt.setInt(1, shainId);
	        rs = stmt.executeQuery();

	        while (rs.next()) {
	            KoujoKoumoku koujoKoumoku = new KoujoKoumoku(
	                    rs.getString("koujokoumoku_mei"),
	                    rs.getString("tekiyoukahi")
	            );
	            koujoKoumokuList.add(koujoKoumoku); // 결과를 리스트에 추가
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        JdbcUtil.close(rs);
	        JdbcUtil.close(stmt);
	    }
	    return koujoKoumokuList;
	}

}
