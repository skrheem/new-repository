package kintai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import kintai.model.KintaiKiroku;
import kintai.model.Shain;
import util.ObjectFormatter;

// 최동주 崔東周
public class GetsuSyoukaiDao {	
	
	private static GetsuSyoukaiDao instance = new GetsuSyoukaiDao();
	private GetsuSyoukaiDao() {}
	public static GetsuSyoukaiDao getInstance() {
		return instance;
	}

    /* 근태조회-월별조회 페이지에서 사원 목록, 근태기록을 DB에서 가져와서 출력
	   (勤怠照会-月別照会ページで社員リストと勤怠記録をDBから取得して表示) */
	public ArrayList<KintaiKiroku> getKintaiList(Connection conn, Integer year, Integer month, String selectedJyoutai, String selectedKubun, String selectedBusho, String selectedYakushoku) throws SQLException {
	    ArrayList<KintaiKiroku> kintaiList = new ArrayList<>();
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    // SQL 쿼리 작성 (SQLクエリの作成)
	    String sql = "SELECT s.kubun, s.shain_id, s.namae_kana, b.busho_mei, y.yakushoku_mei, "
	               + "k.kintai_kaishi, k.kintai_shuuryou, "
	               + "COALESCE(k.kintai_nissuu, 0) AS kintai_nissuu, kk.tani "
	               + "FROM Shain s "
	               + "JOIN Busho b ON s.busho_id = b.busho_id "
	               + "JOIN Yakushoku y ON s.yakushoku_id = y.yakushoku_id "
	               + "JOIN KintaiKiroku k ON s.shain_id = k.shain_id "
	               + "JOIN KintaiKoumoku kk ON k.kintai_id = kk.kintai_id "
	               + "WHERE ( ? IS NULL OR EXTRACT(YEAR FROM k.kintai_kaishi) = ?) "
	               + "AND ( ? IS NULL OR EXTRACT(MONTH FROM k.kintai_kaishi) = ?) "
	               + "AND (? IS NULL OR s.jyoutai = ?) "
	               + "AND (? IS NULL OR s.kubun = ?) "
	               + "AND (? IS NULL OR b.busho_mei = ?) "
	               + "AND (? IS NULL OR y.yakushoku_mei = ?) "
	               + "ORDER BY s.shain_id";

	    try {
	        ps = conn.prepareStatement(sql);
	        // 연도와 월에 null이 전달될 경우 조건을 무시 (年と月にnullが渡された場合、条件を無視)
	        if (year != null) {
	            ps.setInt(1, year);
	            ps.setInt(2, year);
	        } else {
	            ps.setNull(1, java.sql.Types.INTEGER);
	            ps.setNull(2, java.sql.Types.INTEGER);
	        }

	        if (month != null) {
	            ps.setInt(3, month);
	            ps.setInt(4, month);
	        } else {
	            ps.setNull(3, java.sql.Types.INTEGER);
	            ps.setNull(4, java.sql.Types.INTEGER);
	        }

	        // 상태, 구분, 부서, 직위로 필터링 (状態、区分、部署、役職でフィルタリング)
	        ps.setString(5, selectedJyoutai);
	        ps.setString(6, selectedJyoutai);
	        ps.setString(7, selectedKubun);
	        ps.setString(8, selectedKubun);
	        ps.setString(9, selectedBusho);
	        ps.setString(10, selectedBusho);
	        ps.setString(11, selectedYakushoku);
	        ps.setString(12, selectedYakushoku);

	        // 쿼리 실행 및 결과 처리 (クエリの実行と結果処理)
	        rs = ps.executeQuery();

	        while (rs.next()) {
	            String kubun = rs.getString("kubun");
	            Integer shain_id = rs.getInt("shain_id");
	            String namae_kana = rs.getString("namae_kana");
	            String busho_mei = rs.getString("busho_mei");
	            String yakushoku_mei = rs.getString("yakushoku_mei");
	            Date kintai_kaishi = rs.getDate("kintai_kaishi");
	            Date kintai_shuuryou = rs.getDate("kintai_shuuryou");
	            Integer kintai_nissuu = rs.getInt("kintai_nissuu");
	            String tani = rs.getString("tani");

	            // 단위에 따라 근태 일수를 조정 (単位によって勤怠日数を調整)
	            String kintaiUnit = (tani != null && tani.equals("d")) ? kintai_nissuu + "d" : kintai_nissuu + "h";

	            // KintaiKiroku 객체 생성 및 리스트에 추가 (KintaiKirokuオブジェクトを生成し、リストに追加)
	            KintaiKiroku record = new KintaiKiroku(
	                kubun, 
	                shain_id, 
	                namae_kana, 
	                busho_mei, 
	                yakushoku_mei, 
	                kintai_kaishi, 
	                kintai_shuuryou, 
	                kintaiUnit
	            );
	            kintaiList.add(record);
	        }
	    } catch (SQLException e) {
	    	// 예외 발생 시 처리 (例外が発生した際の処理)
	        throw e;
	    } finally {
	        JdbcUtil.close(ps);
	        JdbcUtil.close(rs);
	    }
	    // 근태 기록 리스트 반환 (勤怠記録リストを返す)
	    return kintaiList;
	}

}