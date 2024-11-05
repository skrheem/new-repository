package JinjiKanri.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import JinjiKanri.model.ShoumeishoJouhou;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class ShoumeishoJouHouDao {

    private static ShoumeishoJouHouDao instance = new ShoumeishoJouHouDao();

    private ShoumeishoJouHouDao() {}
    
    public static ShoumeishoJouHouDao getInstance() {
        return instance;
    }
	
	// 김현서 金賢徐
	// p.3 증명서의 정보를 insert 하는 메소드
	// p.3 証明書の情報をinsertするメソッド
	public int saveShoumeishoJouhou(Connection conn, Integer hakkouBangou, Integer shainId, String kubun, java.util.Date hakkou_bi, String hakkouYouto) throws SQLException {
	    String query = "INSERT INTO ShoumeishoJouHou (hakkouBangou, shain_id, kubun, hakkou_bi, hakkouYouto) VALUES (?, ?, ?, ?, ?)";
	    PreparedStatement pstmt = null;

	    try {
	        pstmt = conn.prepareStatement(query);
	        pstmt.setInt(1, hakkouBangou);
	        pstmt.setInt(2, shainId);
	        pstmt.setString(3, kubun);
	        pstmt.setDate(4, new java.sql.Date(hakkou_bi.getTime()));
	        pstmt.setString(5, hakkouYouto);

	        return pstmt.executeUpdate(); 
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw e; 
	    } finally {
	        JdbcUtil.close(pstmt); 
	    }
	}
	
	
	// 김현서 金賢徐
	// p.3 발행번호, 구분, 발급용도, 발행일 출력 / 증명서 정보 조회에서 사용
	// p.3 発行番号、区分、発行用途、発行日の出力/証明書情報の照会で使用
	public ArrayList<ShoumeishoJouhou> getShoumeishoJouhou(Connection conn, Integer shain_id) throws SQLException {

		ArrayList<ShoumeishoJouhou> shoumeishoList = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			String query = "SELECT hakkouBangou, kubun, hakkouYouto, hakkou_bi FROM ShoumeishoJouhou WHERE shain_id = ?";

			stmt = conn.prepareStatement(query);
			stmt.setInt(1, shain_id);
			
			rs = stmt.executeQuery();

			while (rs.next()) {
				ShoumeishoJouhou shoumeisho = new ShoumeishoJouhou(
					rs.getInt("hakkouBangou"),
					rs.getString("kubun"),
					rs.getString("hakkouYouto"),
					rs.getDate("hakkou_bi")
				);

				shoumeishoList.add(shoumeisho); // 리스트에 추가
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
		return shoumeishoList;
	}
	


}