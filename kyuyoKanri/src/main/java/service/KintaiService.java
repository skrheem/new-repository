package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import kintai.dao.HiyatoiDao;
import kintai.dao.KintaiKoumokuDao;
import kintai.model.KintaiKiroku;
//최동주 崔東周
public class KintaiService {

    private KintaiKoumokuDao kintaiDao = KintaiKoumokuDao.getInstance();

    // 생성자에서 DAO 인스턴스를 전달받음 (コンストラクタでDAOインスタンスを受け取る)
    public KintaiService(KintaiKoumokuDao kintaiDao) {
        this.kintaiDao = kintaiDao;
    }

    // JDBC를 이용한 데이터베이스 연결 메서드 (JDBCを使用したデータベース接続メソッド)
    private Connection getConnection() throws SQLException {
        String url = "jdbc:oracle:thin:@localhost:1521:xe";  // JDBC URL (예시)
        String username = "system";  // DB 사용자명
        String password = "1234";  // DB 비밀번호
        
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");  // JDBC 드라이버 로드
        } catch (ClassNotFoundException e) {
            throw new SQLException("JDBC 드라이버 로드 실패", e);
        }

        // 데이터베이스에 연결
        return DriverManager.getConnection(url, username, password);
    }

    // 근태 기록을 저장하는 메서드 (勤怠記録を保存するメソッド)
    public void saveKintaiRecords(ArrayList<KintaiKiroku> records) throws Exception {
        if (records == null || records.isEmpty()) {
            throw new Exception("사원을 선택하세요.");
        }
        
        // JDBC 연결을 사용하여 DAO 호출 (JDBC接続を使用してDAOを呼び出す)
        try (Connection conn = getConnection()) {
            kintaiDao.saveKintaiRecords(conn, records);
        } catch (SQLException e) {
            throw new Exception("근태 기록 저장 중 오류가 발생했습니다.", e);
        }
    }
}
