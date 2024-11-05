package service;

import jdbc.connection.ConnectionProvider;
import kintai.dao.ShainKintaiKirokuDao;
import kintai.model.KintaiKiroku;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

//최동주 崔東周
public class ShainKintaiKirokuService {
    
    private ShainKintaiKirokuDao shainKintaiKirokuDao = ShainKintaiKirokuDao.getInstance();

    // 특정 사원의 근태 기록을 조회하는 메서드
    public List<KintaiKiroku> getKintaiKirokuList(Integer shainId, String date) {
        try (Connection conn = ConnectionProvider.getConnection()) {
            // DAO의 getKintaiKirokuList 메서드에 사원 ID와 날짜 전달
            return shainKintaiKirokuDao.getKintaiKirokuList(conn, shainId, date);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;  // 오류 발생 시 null 반환
        }
    }

    // 근태 기록을 수정하는 메서드
    public void updateKintaiRecord(KintaiKiroku kintai) {
        try (Connection conn = ConnectionProvider.getConnection()) {
            // DAO의 updateKintaiRecord 메서드를 호출하여 기록 업데이트
            shainKintaiKirokuDao.updateKintaiRecord(conn, kintai);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 근태 기록을 삭제하는 메서드
    public void deleteKintaiRecord(int kintaiKirokuId) {
        try (Connection conn = ConnectionProvider.getConnection()) {
            // DAO의 deleteKintaiRecord 메서드를 호출하여 기록 삭제
            shainKintaiKirokuDao.deleteKintaiRecord(conn, kintaiKirokuId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
