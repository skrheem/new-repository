package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.connection.ConnectionProvider;
import kintai.dao.ShainDao;
import kintai.model.Shain;

//최동주 崔東周
public class ShainService {
    private ShainDao shainDao = ShainDao.getInstance();

    // 사원 목록을 가져오는 메서드 (社員リストを取得するメソッド)
    public ArrayList<Shain> getShainList() throws SQLException {
        try (Connection conn = ConnectionProvider.getConnection()) {
            return shainDao.getShainList(conn, null);
        }
    }
}

