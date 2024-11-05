package taishoku.dao;
//김영민金榮珉

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.JdbcUtil;
import taishoku.model.Shain;
import taishoku.model.Taishoku;

public class TaishokuDao {
	// 인스턴스를 저장하는 정적 변수  インスタンスを保存する静的変数
	private static TaishokuDao instance;
	// 외부에서 객체 생성 불가능  外部からのオブジェクト生成を禁止
	private TaishokuDao() {
	}
	// 싱글톤 인스턴스를 반환하는 메서드  シングルトンインスタンスを返すメソッド
	public static TaishokuDao getInstance() {
		if (instance == null) { // 인스턴스가 없을 때만 생성  インスタンスがない場合のみ生成
			instance = new TaishokuDao();
		}
		return instance; // 인스턴스가 존재하면 기존 인스턴스 반환  インスタンスが存在する場合、既存インスタンスを返す
	}
	
	// 퇴직 정보를 삽입하는 메서드와 사원 상태 업데이트 메서드를 통합  退職情報挿入および社員状態更新メソッドの統合
	public void insertTaishokuAndUpdateShain(Connection conn, Taishoku taishoku, Shain shain) throws SQLException {
	    String sql = "INSERT INTO taishoku (taishoku_id, shain_id, taishokuKubun, taishoku_bi, riyuu, taishoku_kin, taishokugoRenrakusaki, taishokukinmeisaisho, taishokuseisankubun) VALUES (taishoku_sequence.nextval, ?, ?, ?, ?, ?, ?, ?, ?)";
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, taishoku.getShain_id());
	        pstmt.setString(2, taishoku.getTaishokuKubun());
	        pstmt.setDate(3, new java.sql.Date(taishoku.getTaishoku_bi().getTime()));
	        pstmt.setString(4, taishoku.getRiyuu());
	        pstmt.setBigDecimal(5, taishoku.getTaishoku_kin());
	        pstmt.setString(6, taishoku.getTaishokugoRenrakusaki());
	        pstmt.setString(7, taishoku.getTaishokuKinMeisaisho());
	        pstmt.setString(8, taishoku.getTaishokuSeisanKubun());
	        pstmt.executeUpdate();
	    }

	    // 사원 테이블 업데이트  社員テーブルの更新
	    String updateShainSql = "UPDATE shain SET jyoutai = ?, taisha_nengappi = ? WHERE shain_id = ?";
	    try (PreparedStatement pstmt = conn.prepareStatement(updateShainSql)) {
	        pstmt.setString(1, shain.getJyoutai()); // 상태 설정  状態の設定
	        pstmt.setDate(2, new java.sql.Date(shain.getTaisha_nengappi().getTime())); // 퇴사 날짜 설정
	        pstmt.setInt(3, shain.getShain_id()); // 사원 ID 설정  社員IDの設定
	        pstmt.executeUpdate();
	    }
	}

	// 퇴직구분, 퇴직일, 퇴직사유, 퇴직후 연락처를 출력하는 메소드  退職区分、退職日、退職理由、退職後連絡先を取得するメソッド
	public ArrayList<Taishoku> selectTaishokuInfo(Connection conn, int shainId) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Taishoku> TaishokuList = new ArrayList<>();
		
		String sql = "SELECT taishokuKubun, taishoku_bi, riyuu, taishokugoRenrakusaki FROM taishoku WHERE shain_id = ? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, shainId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// 퇴직일을 Date로 변환  退職日をDate型に変換
				Taishoku taishoku = new Taishoku(rs.getString("taishokukubun"), rs.getDate("taishoku_bi"), rs.getString("riyuu"), rs.getString("taishokugorenrakusaki"));
				TaishokuList.add(taishoku);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return TaishokuList;
	}

	// 퇴직구분, 퇴직일, 퇴직사유, 퇴직후 연락처를 삭제하는 메서드  退職区分、退職日、退職理由、退職後連絡先を削除するメソッド
	public void deleteByShainId(Connection conn, int shainId) throws SQLException {
		System.out.println("削除機能 (삭제 기능)");
		String sql = "DELETE FROM taishoku WHERE shain_id = ?";
		PreparedStatement pstmt = null;
	    try  {
	    	pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, shainId); 
	    } catch (SQLException e) {
            throw new SQLException("退職情報削除中にエラー発生(퇴직 정보 삭제 중 오류 발생): " + e.getMessage(), e);
        }
	}
}