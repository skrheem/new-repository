package service;

import jdbc.connection.ConnectionProvider;
import kintai.dao.KyuukaSyoukaiDao;
import kintai.model.KintaiKiroku;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

//최동주 崔東周
public class KyuukaSyoukaiService {
	// 휴가 목록을 조회하는 메서드 (休暇リストを照会するメソッド)
	private KyuukaSyoukaiDao kyuukaDao = KyuukaSyoukaiDao.getInstance();

    public ArrayList<KintaiKiroku> getKyuukaList() {
        ArrayList<KintaiKiroku> kyuukaList = new ArrayList<>();
        try (Connection conn = ConnectionProvider.getConnection()) {
            kyuukaList = KyuukaSyoukaiDao.getInstance().getKyuukaList(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kyuukaList;
    }
}
