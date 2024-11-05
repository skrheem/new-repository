package kintai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import jdbc.connection.ConnectionProvider;
import kintai.model.KintaiKiroku;
import util.ObjectFormatter;

// 최동주 崔東周
public class ShainKintaiKirokuDao {
	
	private static ShainKintaiKirokuDao instance = new ShainKintaiKirokuDao();
	private ShainKintaiKirokuDao() {}
	public static ShainKintaiKirokuDao getInstance() {
		return instance;
	}

	// 특정 사원의 근태 기록을 조회하는 메서드 (特定社員の勤怠記録を取得するメソッド)
	public ArrayList<KintaiKiroku> getKintaiKirokuList(Connection conn, Integer shain_id, String date) throws SQLException {
	    ArrayList<KintaiKiroku> kirokuList = new ArrayList<>();
	    PreparedStatement pstmt = null;

	    // SQL 쿼리 작성 (SQLクエリ作成)
	    String sql = "SELECT k.kintai_kiroku_id, k.nyuuryoku_bi, kk.kintai_mei, k.kintai_kaishi, k.kintai_shuuryou, k.kintai_nissuu, k.teate, k.tekiyou " +
	                 "FROM kintaiKiroku k JOIN kintaiKoumoku kk ON k.kintai_id = kk.kintai_id " +
	                 "WHERE k.shain_id = ? " +
	                 "AND k.nyuuryoku_bi BETWEEN " +
	                 "TRUNC(TO_DATE(?, 'YYYY-MM-DD'), 'MM') " +
	                 "AND LAST_DAY(TO_DATE(?, 'YYYY-MM-DD'))";

	    try {
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, shain_id); // 사원 ID 설정 (社員ID設定)
	        pstmt.setString(2, date); // 날짜 설정 (日付設定)
	        pstmt.setString(3, date); // 날짜 설정 (日付設定)
	        ResultSet rs = pstmt.executeQuery();

	        while (rs.next()) {
	            Integer kintai_kiroku_id = rs.getInt("kintai_kiroku_id");
	            Date nyuuryoku_bi = rs.getDate("nyuuryoku_bi");
	            String kintai_mei = rs.getString("kintai_mei");
	            Date kintai_kaishi = rs.getDate("kintai_kaishi");
	            Date kintai_shuuryou = rs.getDate("kintai_shuuryou");
	            Integer kintai_nissuu = rs.getInt("kintai_nissuu");
	            Integer teate = rs.getInt("teate");
	            String tekiyou = rs.getString("tekiyou");

	            // KintaiKiroku 객체 생성 (KintaiKirokuオブジェクトを生成)
	            KintaiKiroku kintai = new KintaiKiroku(kintai_kiroku_id, nyuuryoku_bi, kintai_mei, kintai_kaishi,
	                                                   kintai_shuuryou, kintai_nissuu, teate, tekiyou);
	            kirokuList.add(kintai);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return kirokuList; // 근태 기록 리스트 반환 (勤怠記録リストを返す)
	}

	// 근태 기록 수정 메서드 (勤怠記録を更新するメソッド)
	public void updateKintaiRecord(Connection conn, KintaiKiroku kintai) throws SQLException {
	    String sql = "UPDATE kintaiKiroku SET "
	                + "nyuuryoku_bi = TO_DATE(?, 'YYYY-MM-DD'), "
	                + "kintai_id = ?, "
	                + "kintai_kaishi = TO_DATE(?, 'YYYY-MM-DD'), "
	                + "kintai_shuuryou = TO_DATE(?, 'YYYY-MM-DD'), "
	                + "kintai_nissuu = ?, "
	                + "teate = ?, "
	                + "tekiyou = ? "
	                + "WHERE kintai_kiroku_id = ?";

	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, new SimpleDateFormat("yyyy-MM-dd").format(kintai.getNyuuryoku_bi()));
	        pstmt.setInt(2, kintai.getKintai_id());
	        pstmt.setString(3, new SimpleDateFormat("yyyy-MM-dd").format(kintai.getKintai_kaishi()));
	        pstmt.setString(4, new SimpleDateFormat("yyyy-MM-dd").format(kintai.getKintai_shuuryou()));
	        pstmt.setInt(5, kintai.getKintai_nissuu());
	        pstmt.setInt(6, kintai.getTeate());
	        pstmt.setString(7, kintai.getTekiyou());
	        pstmt.setInt(8, kintai.getKintai_kiroku_id()); // ID 설정

	        pstmt.executeUpdate();
	    }
	}

	// 근태 기록 삭제 메서드 (勤怠記録を削除するメソッド)
	public void deleteKintaiRecord(Connection conn, int kintaiKirokuId) throws SQLException {
	    String sql = "DELETE FROM kintaiKiroku WHERE kintai_kiroku_id = ?";

	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, kintaiKirokuId);
	        pstmt.executeUpdate();
	    }
	}

}
