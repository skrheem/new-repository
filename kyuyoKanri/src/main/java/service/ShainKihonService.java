package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import JinjiKanri.dao.ShainDao;
import JinjiKanri.model.Shain;
import jdbc.connection.ConnectionProvider;

// 김현서 金現徐
// p.2 인사기록카드 사원 선택 팝업창에 표시 될 전체 사원 리스트
// p.2人事記録カード社員選択ポップアップウィンドウに表示される全社員リスト
public class ShainKihonService {

	public ArrayList<Shain> getAllShainKihonJyouhou() {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			return ShainDao.getAllShainKihonJyouhou(conn);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}



}
