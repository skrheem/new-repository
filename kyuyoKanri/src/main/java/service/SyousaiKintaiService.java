package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import kintai.dao.SyousaiSyoukaiDao;
import kintai.model.KintaiKiroku;

//최동주 崔東周
public class SyousaiKintaiService {

    private SyousaiSyoukaiDao syousaiSyoukaiDao = SyousaiSyoukaiDao.getInstance();

	// 상세 근태 기록 조회 메서드 (詳細勤怠記録を取得するメソッド)
    public List<KintaiKiroku> getKintaiRecords(String kintaiKaishi, String kintaiShuuryou) {
        List<KintaiKiroku> kintaiRecords = new ArrayList<>();
        try (Connection conn = ConnectionProvider.getConnection()) {
            kintaiRecords = syousaiSyoukaiDao.getKintaiRecords(conn, kintaiKaishi, kintaiShuuryou);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kintaiRecords;
    }
}
