package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import kintai.dao.GetsuSyoukaiDao;
import kintai.dao.HiyatoiGetsuDao;
import kintai.model.HiyatoiKinmuKiroku;

//최동주 崔東周
public class HiyatoiGetsuService {

    private HiyatoiGetsuDao hiyatoiMonthlyDao = HiyatoiGetsuDao.getInstance();

    // 일용직 사원의 월별 근무 기록을 조회하는 메서드 (日雇い社員の月別勤務記録を取得するメソッド)
    public List<HiyatoiKinmuKiroku> getHiyatoiGetsuList(int year, int month, String selectedBusho, String selectedYakushoku) {
        List<HiyatoiKinmuKiroku> hiyatoiList = new ArrayList<>();
        try (Connection conn = ConnectionProvider.getConnection()) {
            hiyatoiList = hiyatoiMonthlyDao.getHiyatoiGetsuList(conn, year, month, selectedBusho, selectedYakushoku);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hiyatoiList;
    }
}
