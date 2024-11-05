package taishoku.service;
//김영민金榮珉

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.connection.ConnectionProvider;
import taishoku.dao.ShainTaishokuDao;
import taishoku.model.Shain;

public class ShainTaishokuService {
	
	private ShainTaishokuDao shainTaishokuDao = new ShainTaishokuDao();
	// 사원 정보와 퇴직 정보를 조회하는 메서드
    // 社員情報と退職情報を照会するメソッド
    public ArrayList<Shain> getShainTaishokuInfoByPage(int page, int pageSize) throws SQLException {
        try (Connection conn = ConnectionProvider.getConnection()) {
            int totalShainCount = shainTaishokuDao.getTotalShainCount(conn);
            int totalPages = (int) Math.ceil((double) totalShainCount / pageSize);

            // 요청한 페이지가 유효한지 확인  リクエストされたページが有効か確認
            if (page < 1 || page > totalPages) {
                return new ArrayList<>(); // 유효하지 않은 페이지 요청 시 빈 리스트 반환  無効なページがリクエストされた場合、空のリストを返却
            }

            int offset = (page - 1) * pageSize;
            return shainTaishokuDao.selectShainTaishokuInfo(conn, offset, pageSize);
        }
    }
    public int getTotalShainCount() throws SQLException {
        try (Connection conn = ConnectionProvider.getConnection()) {
            return shainTaishokuDao.getTotalShainCount(conn);
        }
    }
}
