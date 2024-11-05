package JinjiKanri.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.math.BigDecimal;
import java.util.ArrayList;

import JinjiKanri.model.Taishoku;
import jdbc.JdbcUtil;

public class TaishokuDao {
	
    private static TaishokuDao instance = new TaishokuDao();

    private TaishokuDao() {}
    
    public static TaishokuDao getInstance() {
        return instance;
    }

	// 김현서 金現徐
	// p.2 특정 사원의 퇴직 정보를 조회하는 메소드
	// p.2 人事記録カード_特定社員の退職情報を照会するメソッド
	public ArrayList<Taishoku> getTaishokuByShainId(Connection conn, Integer shain_id) throws SQLException {

		ArrayList<Taishoku> taishokuList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String query = "SELECT taishokuKubun, taishoku_bi, riyuu, taishoku_kin, taishokugoRenrakusaki "
					+ "FROM Taishoku " + "WHERE shain_id = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, shain_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Taishoku taishoku = new Taishoku(rs.getString("taishokuKubun"), rs.getDate("taishoku_bi"),
						rs.getString("riyuu"), rs.getBigDecimal("taishoku_kin"), rs.getString("taishokugoRenrakusaki"));
				taishokuList.add(taishoku);
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return taishokuList;
	}

	
	// 김현서 金現徐
	// p.3 퇴직사유 출력 / 퇴직증명서에서 사용
	// p.3 退職理由 /退職証明書で使用
	public ArrayList<Taishoku> getTaishokuJouhouByShoumeisho(Connection conn, Integer shain_id) throws SQLException {

		ArrayList<Taishoku> shainList = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			String query = "SELECT riyuu FROM Taishoku WHERE shain_id = ?";

			stmt = conn.prepareStatement(query);
			stmt.setInt(1, shain_id);

			rs = stmt.executeQuery();

			while (rs.next()) {
				Taishoku taishoku = new Taishoku(rs.getString("riyuu"));

				shainList.add(taishoku); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
		return shainList;
	}

}
