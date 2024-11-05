package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.connection.ConnectionProvider;
import kintai.dao.HiyatoiDao;
import kintai.model.Shain;

//최동주 崔東周
public class HiyatoiService {

	// 일용직 사원 목록을 가져오는 메서드 (日雇い社員リストを取得するメソッド)
    private HiyatoiDao hiyatoiDao = HiyatoiDao.getInstance();

    public ArrayList<Shain> getHiyatoiList(String jyoutai) {
        ArrayList<Shain> hiyatoiList = new ArrayList<>();
        
        try (Connection conn = ConnectionProvider.getConnection()) {
            // DAO를 호출하여 일용직 사원 목록을 가져옴
            hiyatoiList = hiyatoiDao.getHiyatoiList(conn, jyoutai);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("일용직 사원 목록을 가져오는 중 오류가 발생했습니다.", e);
        }
        
        return hiyatoiList;
    }
}