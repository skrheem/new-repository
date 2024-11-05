package taishoku.service;
//김영민金榮珉

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import jdbc.connection.ConnectionProvider;
import taishoku.dao.TaishokuDao;
import taishoku.model.Shain;
import taishoku.model.Taishoku;

public class TaishokuService {
	private TaishokuDao taishokuDao = TaishokuDao.getInstance();
	// 퇴직 정보를 삽입하고 사원의 상태를 업데이트하는 메서드  退職情報を挿入し、社員の状態を更新するメソッド 
	public void insertTaishokuAndUpdateShain(int shainId, String taishokuKubun, Date taishokuBi, String riyuu, BigDecimal taishokuKin, String taishokugoRenrakusaki, String taishokuKinMeisaisho, String taishokuSeisanKubun) throws SQLException {
	    Connection conn = null;
	    try {
	        conn = ConnectionProvider.getConnection();
	        conn.setAutoCommit(false);

	        // 퇴직 정보와 사원 상태 객체 생성  退職情報と社員状態オブジェクトの作成
	        Taishoku taishoku = new Taishoku(null, shainId, taishokuKubun, taishokuBi, riyuu, taishokuKin, taishokugoRenrakusaki, taishokuKinMeisaisho, taishokuSeisanKubun);
	        Shain shain = new Shain("退職", taishokuBi); // 상태와 퇴사일 설정　　状態と退職日を設定 
	        shain.setShain_id(shainId);

	        // DAO 호출로 퇴직 정보 삽입 및 사원 상태 업데이트　　DAOを呼び出して退職情報を挿入し、社員の状態を更新
	        taishokuDao.insertTaishokuAndUpdateShain(conn, taishoku, shain);
	        
	        conn.commit();
	    } catch (SQLException e) { 
	        if (conn != null) conn.rollback();  // 오류 시 롤백  エラー発生時にロールバック
	        throw e;
	    } finally {
	        if (conn != null) conn.close();
	    }
	}

	// 퇴직 정보를 조회하는 메서드  退職情報を取得するメソッド 
	public ArrayList<Taishoku> getTaishokuInfo(int shainId) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			return taishokuDao.selectTaishokuInfo(conn, shainId);
		} catch (SQLException e) {
			throw new RuntimeException("退職情報の取得中にエラーが発生しました(퇴직 정보 조회 중 오류 발생)");
		}
	}

	// 퇴직 정보를 삭제하는 메서드  退職情報を削除するメソッド
	public void deleteTaishoku(int shainId) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			taishokuDao.deleteByShainId(conn, shainId);
			conn.commit();
			System.out.println("退職情報の削除が完了しました(퇴직 정보 삭제 완료): shain_id = " + shainId);
		} catch (SQLException e) {
			if (conn != null)
				conn.rollback(); // 오류 시 롤백  エラー発生時にロールバック
			throw e;
		} finally {
			if (conn != null)
				conn.close();
		}
	}
}
