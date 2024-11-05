package kintai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import kintai.model.Shain;
import util.ObjectFormatter;

// 최동주 崔東周
public class HiyatoiDao {
	
	private static HiyatoiDao instance = new HiyatoiDao();
	private HiyatoiDao() {}
	public static HiyatoiDao getInstance() {
		return instance;
	}

    /* 일용직 근무기록/관리 페이지에서 왼쪽 부분, 일용직 사원 목록을 DB에서 가져와서 출력
	   (日雇い勤務記録/管理ページで左側の部分、日雇い社員リストをDBから取得して表示) */
	public ArrayList<Shain> getHiyatoiList(Connection conn, String jyoutai) throws SQLException{
	    ArrayList<Shain> shainList = new ArrayList<>();
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    // SQL 쿼리 작성 (SQLクエリの作成)
	    String sql = "SELECT s.kubun, s.shain_id, s.namae_kana, b.busho_mei, y.yakushoku_mei FROM Shain s "
	            + "LEFT JOIN busho b ON s.busho_id = b.busho_id "
	            + "LEFT JOIN yakushoku y ON s.yakushoku_id = y.yakushoku_id "
	            + "WHERE s.kubun = '日雇い'"; // 일용직 사원만 가져옴 (日雇い社員のみを取得)

	    try {
	        ps = conn.prepareStatement(sql); // SQL 문 실행 준비 (SQL文を実行準備)
	        rs = ps.executeQuery(); // 쿼리 실행 (クエリを実行)

	        while (rs.next()) {
	            String kubun = rs.getString("kubun"); // 구분 정보 (区分情報)
	            Integer shain_id = rs.getInt("shain_id"); // 사원 ID (社員ID)
	            String namae_kana = rs.getString("namae_kana"); // 사원 이름 (社員の名前)
	            String busho_mei = rs.getString("busho_mei"); // 부서 명칭 (部署名)
	            String yakushoku_mei = rs.getString("yakushoku_mei"); // 직위 명칭 (役職名)
 
	            // Shain 객체 생성 및 리스트에 추가 (Shainオブジェクトを生成してリストに追加)
	            Shain shain = new Shain(kubun, shain_id, namae_kana, busho_mei, yakushoku_mei);
	            shainList.add(shain);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        JdbcUtil.close(ps);
	        JdbcUtil.close(rs);
	    }
	    return shainList; // 결과 리스트 반환 (結果リストを返す)
	}

	
}

