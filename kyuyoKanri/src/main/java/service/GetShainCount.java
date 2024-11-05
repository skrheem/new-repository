package service;

import java.sql.Connection;
import java.sql.SQLException;

import JinjiKanri.dao.ShainDao;
import jdbc.connection.ConnectionProvider;


//김현서 金現徐
//p.1 항목별 사원 count값을 가져오는 서비스
//p.1 項目別社員カウント値を取得するサービス
public class GetShainCount {

	private ShainDao shainDao = ShainDao.getInstance();

    // 김현서 金現徐
    // p.1 전체 사원 수를 가져오는 메서드
	// p.1 全社員数を取得するメソッド
    public int getTotalShainCount() {
        try (Connection conn = ConnectionProvider.getConnection()) {
            return shainDao.getShainCountAll(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    
    

    // 김현서 金現徐
    // p.1 상태별(재직, 퇴직) 사원 수를 가져오는 메서드
    // p.1 ステータス別（在職·退職）社員数を取得するメソッド
    public int[] getShainJyoutaiCount() {
        try (Connection conn = ConnectionProvider.getConnection()) {
            return shainDao.getShainJyoutaiCNT(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            return new int[]{0, 0};
        }
    }

    // 김현서 金現徐
    // p.1 고용 형태별(정규직, 계약직, 임시직 등) 사원 수를 가져오는 메서드
    // p.1 雇用形態別（正社員、契約社員、臨時社員など）の数を取得するメソッド
    public int[] getShainKubunCount() {
        try (Connection conn = ConnectionProvider.getConnection()) {
            return shainDao.getShainKubunCNT(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            return new int[]{0, 0, 0, 0, 0, 0};
        }
    }
}
