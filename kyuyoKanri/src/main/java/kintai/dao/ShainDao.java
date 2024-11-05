package kintai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import kintai.model.Shain;
import util.ObjectFormatter;

// 최동주 崔東周
public class ShainDao {
	
	private static ShainDao instance = new ShainDao();
	private ShainDao() {}
	public static ShainDao getInstance() {
		return instance;
	}

	// 근태기록관리 페이지에서 사원 목록을 DB에서 가져오는 메서드 (勤怠記録管理ページで社員リストをDBから取得するメソッド)
	public ArrayList<Shain> getShainList(Connection conn, String selectedJyoutai) throws SQLException {
	    ArrayList<Shain> shainList = new ArrayList<>();
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    // SQL 쿼리 작성 (SQLクエリ作成)
	    StringBuilder sql = new StringBuilder("SELECT s.kubun, s.shain_id, s.namae_kana, b.busho_mei, y.yakushoku_mei FROM Shain s "
	            + "JOIN Busho b ON s.busho_id = b.busho_id "
	            + "JOIN Yakushoku y ON s.yakushoku_id = y.yakushoku_id "
	            + "WHERE (? IS NULL OR s.jyoutai = ?) ");

	    // 초기값 설정: 재직 상태 (初期値設定: 在職状態)
	    if (selectedJyoutai == null) {
	        selectedJyoutai = "在職"; // 기본값으로 '재직' 상태를 사용 (デフォルト値として「在職」状態を使用)
	    }

	    try {
	        ps = conn.prepareStatement(sql.toString());
	        ps.setString(1, selectedJyoutai); // 선택한 상태 설정 (選択した状態を設定)
	        ps.setString(2, selectedJyoutai); // 선택한 상태 설정 (選択した状態を設定)

	        rs = ps.executeQuery();

	        // 쿼리 결과를 Shain 객체로 변환하여 리스트에 추가 (クエリ結果をShainオブジェクトに変換してリストに追加)
	        while (rs.next()) {
	            String kubun = rs.getString("kubun");
	            Integer shain_id = rs.getInt("shain_id");
	            String namae_kana = rs.getString("namae_kana");
	            String busho_mei = rs.getString("busho_mei");
	            String yakushoku_mei = rs.getString("yakushoku_mei");

	            // Shain 객체 생성 및 리스트에 추가 (Shainオブジェクトを生成してリストに追加)
	            Shain shain = new Shain(kubun, shain_id, namae_kana, busho_mei, yakushoku_mei);
	            shainList.add(shain);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        JdbcUtil.close(ps);
	        JdbcUtil.close(rs);
	    }
	    return shainList; // 사원 목록 반환 (社員リストを返す)
	}
	
}	
	
	
	


