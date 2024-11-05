package service;

import java.sql.Connection;
import java.sql.SQLException;

import JinjiKanri.dao.ShainDao;
import jdbc.connection.ConnectionProvider;

// 김현서 金現徐
// p.1 특정 shainId로 사원을 삭제하는 메서드
// p.1 特定のshainIdで社員を削除するメソッド
public class DeleteShain {

	private ShainDao shainDao = ShainDao.getInstance();

	public boolean deleteById(int shainId) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			return shainDao.deleteShainById(conn, shainId);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
