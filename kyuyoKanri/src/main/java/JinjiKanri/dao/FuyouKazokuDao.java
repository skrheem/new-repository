package JinjiKanri.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import JinjiKanri.model.FuyouKazoku;
import jdbc.JdbcUtil;

public class FuyouKazokuDao {
	
    private static FuyouKazokuDao instance = new FuyouKazokuDao();
    

    private FuyouKazokuDao() {}

    public static FuyouKazokuDao getInstance() {
        return instance;
    }
    
	// 김현서 金賢徐
    // p.2 인사기록카드_ 특정 사원의 부양가족 정보를 조회하는 메소드
	// p.2 人事記録カード_特定社員の扶養家族情報を照会するメソッド
	public ArrayList<FuyouKazoku> getFuyouKazokuByShainId(Connection conn, Integer shain_id) throws SQLException {
	    ArrayList<FuyouKazoku> kazokuList = new ArrayList<>();
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    try {
	        String query = "SELECT kankei, namae, jumin_bangou, doukyoUmu " +
	                       "FROM FuyouKazoku " +
	                       "WHERE shain_id = ?";
	        stmt = conn.prepareStatement(query);
	        stmt.setInt(1, shain_id);

	        rs = stmt.executeQuery();

	        while (rs.next()) {
	            FuyouKazoku kazoku = new FuyouKazoku(
	                rs.getString("kankei"),
	                rs.getString("namae"),
	                rs.getString("jumin_bangou"),
	                rs.getString("doukyoUmu").charAt(0)
	            );

	            kazokuList.add(kazoku);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw e;
	    } finally {
	        JdbcUtil.close(rs);
	        JdbcUtil.close(stmt);
	    }

	    return kazokuList;
	}


}
