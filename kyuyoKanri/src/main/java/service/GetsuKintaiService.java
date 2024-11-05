package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import kintai.dao.GetsuSyoukaiDao;
import kintai.model.KintaiKiroku;

// 최동주 崔東周
public class GetsuKintaiService {
    private GetsuSyoukaiDao getsuSyoukaiDao = GetsuSyoukaiDao.getInstance();

    // 월별 근태 목록 조회 메서드 (月別勤怠リストを取得するメソッド)
    public List<KintaiKiroku> getKintaiList(Integer year, Integer month, String selectedJyoutai, String selectedKubun, String selectedBusho, String selectedYakushoku) {
        List<KintaiKiroku> kintaiList = new ArrayList<>();
        try (Connection conn = ConnectionProvider.getConnection()) {
            kintaiList = getsuSyoukaiDao.getKintaiList(conn, year, month, selectedJyoutai, selectedKubun, selectedBusho, selectedYakushoku);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kintaiList;
    }
}
