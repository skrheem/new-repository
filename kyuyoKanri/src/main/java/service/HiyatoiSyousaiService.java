package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import kintai.dao.HiyatoiDao;
import kintai.dao.HiyatoiSyousaiDao;
import kintai.model.HiyatoiKinmuKiroku;

//최동주 崔東周
public class HiyatoiSyousaiService {
	// 특정 사원의 상세 근무 기록을 조회하는 메서드 (特定社員の詳細勤務記録を取得するメソッド)
    private HiyatoiSyousaiDao hiyatoiSyousaiDao = HiyatoiSyousaiDao.getInstance();

    public List<HiyatoiKinmuKiroku> getHiyatoiSyousaiList(int shainId) {
        List<HiyatoiKinmuKiroku> hiyatoiKirokuList = new ArrayList<>();
        try (Connection conn = ConnectionProvider.getConnection()) {
            hiyatoiKirokuList = hiyatoiSyousaiDao.getHiyatoiSyousaiList(conn, shainId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hiyatoiKirokuList;
    }
}
