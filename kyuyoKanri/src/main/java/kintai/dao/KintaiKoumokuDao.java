package kintai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import jdbc.connection.ConnectionProvider;
import kintai.model.KintaiKiroku;

// 최동주 崔東周
public class KintaiKoumokuDao {
	
	private static KintaiKoumokuDao instance = new KintaiKoumokuDao();
	private KintaiKoumokuDao() {}
    public static KintaiKoumokuDao getInstance() {
        return instance;
    }
	
    // 근태기록관리 페이지에서 사원의 근태기록을 저장하는 메서드 (勤怠記録管理ページで社員の勤怠記録を保存するメソッド)
	public void saveKintaiRecords(Connection conn, ArrayList<KintaiKiroku> records) throws SQLException {

		// SQL 쿼리 작성 (SQLクエリ作成)
	    String sql = "INSERT INTO KintaiKiroku (KINTAI_KIROKU_ID, shain_id, nyuuryoku_bi, kintai_id, kintai_kaishi, kintai_shuuryou, kintai_nissuu, teate, tekiyou, touroku_nengappi) " +
	                 "VALUES (KINTAIKIROKU_SEQUENCE.nextval, ?, TO_DATE(?, 'YYYY-MM-DD'), ?, TO_DATE(?, 'YYYY-MM-DD'), TO_DATE(?, 'YYYY-MM-DD'), ?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'))";
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        
	    	// 모든 근태 기록에 대해 반복 처리 (すべての勤怠記録に対して繰り返し処理)
	        for (KintaiKiroku kintai : records) {
	            pstmt.setInt(1, kintai.getShain_id());
	            pstmt.setDate(2, (java.sql.Date) kintai.getNyuuryoku_bi());
	            pstmt.setInt(3, kintai.getKintai_id());
	            pstmt.setDate(4, (java.sql.Date) kintai.getKintai_kaishi());
	            pstmt.setDate(5, (java.sql.Date) kintai.getKintai_shuuryou());
	            pstmt.setInt(6, kintai.getKintai_nissuu());
	            pstmt.setInt(7, kintai.getTeate());
	            pstmt.setString(8, kintai.getTekiyou());
	            pstmt.setDate(9, (java.sql.Date) kintai.getTouroku_nengappi());

	            pstmt.addBatch(); // 여러 근태 기록을 한 번에 처리하기 위해 batch 사용 (複数の勤怠記録を一度に処理するためにバッチを使用)
	        }
	        
	        // Batch 실행 (バッチを実行)
	        pstmt.executeBatch();
	        
	    } catch (SQLException e) {
	        throw e;
	    }
	}
}
