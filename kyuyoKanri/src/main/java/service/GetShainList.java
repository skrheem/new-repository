package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import JinjiKanri.dao.ShainDao;
import JinjiKanri.model.Shain;
import jdbc.connection.ConnectionProvider;

// 김현서 金現徐
// p.1 전체 사원 리스트를 가져오는 서비스
// p.1 全社員リストを取得するサービス
public class GetShainList {

	private ShainDao shainDao = ShainDao.getInstance();

	// 김현서 金現徐
	// p.1 전체 사원 리스트를 가져오는 메소드
	// p.1 全社員リストを取得するメソッド
	public ArrayList<Shain> getAllShains() {
		ArrayList<Shain> shainList = new ArrayList<>();
		try (Connection conn = ConnectionProvider.getConnection()) {
			shainList = shainDao.getAllShains(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return shainList;
	}

}
