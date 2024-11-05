package taishoku.dao;
//김영민金榮珉

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;

import jdbc.JdbcUtil;
import taishoku.model.Shain;

public class ShainTaishokuDao {

	// 인스턴스를 저장하는 정적 변수
	// インスタンスを保存する静的変数
	private static ShainTaishokuDao instance;

	// 싱글톤 인스턴스를 반환하는 메서드
	// シングルトンインスタンスを返すメソッド
	private static ShainTaishokuDao getInstance() {
		if (instance == null) { // 인스턴스가 없을 때만 생성  // インスタンスがない場合のみ生成
			instance = new ShainTaishokuDao();
		}
		return instance; // 인스턴스가 존재하면 기존 인스턴스 반환  // インスタンスが存在する場合は既存のインスタンスを返す
	}
	
	public int getTotalShainCount(Connection conn) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Shain";
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                return 0;
            }
        }
    }
	
	// 사원의 퇴직 정보를 조회하는 메서드  社員の退職情報を取得するメソッド
	public ArrayList<Shain> selectShainTaishokuInfo(Connection conn, int offset, int limit) throws SQLException {
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    ArrayList<Shain> shainTaishokuList = new ArrayList<>();

	    String sql = "SELECT * FROM ( " 
	    		+ "  SELECT ROWNUM AS rnum, a.* FROM ( " 
	    		+ "  SELECT DISTINCT s.jyoutai, s.shain_id, s.namae_kana, s.nyuusha_nengappi, s.taisha_nengappi, " 
	    		+ "  b.busho_mei, y.yakushoku_mei, t.taishokuseisankubun " 
	    		+ "  FROM Shain s " 
	    		+ "  JOIN Busho b ON s.busho_id = b.busho_id " 
	    		+ "  JOIN Yakushoku y ON s.yakushoku_id = y.yakushoku_id " 
	    		+ "  LEFT JOIN taishoku t ON s.shain_id = t.shain_id " 
	    		+ "  ORDER BY s.shain_id " 
	    		+ "  ) a " 
	    		+ "  WHERE ROWNUM <= ? " 
	    		+ ") " 
	    		+ "WHERE rnum > ?";

	    try {
	        pstmt = conn.prepareStatement(sql);  // SQL 준비  SQL準備
	        pstmt.setInt(1, limit); 
	        pstmt.setInt(2, offset);  

	        rs = pstmt.executeQuery();  // SQL 실행  SQL実行

	        while (rs.next()) {
	        	// 입사일과 퇴사일을 Date로 변환 入社日と退職日をDate型に変換
	            Date nyuushaNengappi = rs.getDate("nyuusha_nengappi");
	            Date taishaNengappi = (rs.getDate("taisha_nengappi") != null)
	                    ? new Date(rs.getDate("taisha_nengappi").getTime())
	                    : new Date(); // 퇴사일이 없으면 현재 날짜로 설정  退職日がない場合は、現在の日付に設定
	            
	            // 근속년수 계산  勤続年数の計算
	            int kinzokunensu = calculateYears(nyuushaNengappi, taishaNengappi);
	            
	            // Shain 객체 생성하여 리스트에 추가  Shainオブジェクトを生成して、リストに追加
	            Shain shain = new Shain(
	                rs.getInt("shain_id"),
	                rs.getString("namae_kana"),
	                rs.getDate("nyuusha_nengappi"),
	                rs.getDate("taisha_nengappi"),
	                rs.getString("jyoutai"),
	                rs.getString("busho_mei"),
	                rs.getString("yakushoku_mei"),
	                kinzokunensu,
	                rs.getString("taishokuseisankubun")
	            );
	            
	            // 생성한 Shain 객체를 리스트에 추가 生成したShainオブジェクトをリストに追加
	            shainTaishokuList.add(shain);
	        }
	    } finally {
	        JdbcUtil.close(rs);
	        JdbcUtil.close(pstmt);
	    }
	    return shainTaishokuList;
	}
	
	// 근속년수를 계산하는 메서드  勤続年数を計算するメソッド
	private int calculateYears(Date startDate, Date endDate) {
		LocalDate start = new java.sql.Date(startDate.getTime()).toLocalDate();
		LocalDate end = new java.sql.Date(endDate.getTime()).toLocalDate();
		// 시작일과 종료일의 연수 차이를 반환  開始日と終了日の年数差を返す
		return Period.between(start, end).getYears();
	}
}
