package kintai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import kintai.model.KintaiKiroku;
import util.ObjectFormatter;

// 최동주 崔東周
public class SyousaiSyoukaiDao {
    
    private static SyousaiSyoukaiDao instance = new SyousaiSyoukaiDao();
    private SyousaiSyoukaiDao() {}
    public static SyousaiSyoukaiDao getInstance() {
        return instance;
    }
    
 // 근태 조회 - 상세 조회 페이지에서 사원 목록 및 근태 기록을 DB에서 가져오는 메서드 (勤怠照会-詳細照会ページで社員リストと勤怠記録をDBから取得するメソッド)
    public ArrayList<KintaiKiroku> getKintaiRecords(Connection conn, String Kintai_kaishi, String Kintai_shuuryou) throws SQLException {
        ArrayList<KintaiKiroku> kintaiRecords = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        // SQL 쿼리 작성 (SQLクエリ作成)
        String sql = "SELECT "
                   + "k.nyuuryoku_bi, kk.kintai_mei, s.namae_kana, s.kubun, "
                   + "b.busho_mei, y.yakushoku_mei, "
                   + "k.kintai_kaishi, k.kintai_shuuryou, "
                   + "COALESCE(k.kintai_nissuu, 0) AS kintai_nissuu, "
                   + "COALESCE(k.teate, 0) AS teate, "
                   + "k.tekiyou "
                   + "FROM KintaiKiroku k "
                   + "JOIN Shain s ON k.shain_id = s.shain_id "
                   + "JOIN Busho b ON s.busho_id = b.busho_id "
                   + "JOIN Yakushoku y ON s.yakushoku_id = y.yakushoku_id "
                   + "JOIN KintaiKoumoku kk ON k.kintai_id = kk.kintai_id "
                   + (Kintai_kaishi != null && Kintai_shuuryou != null ? "WHERE k.nyuuryoku_bi BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(?, 'YYYY-MM-DD') " : "")
                   + "ORDER BY k.nyuuryoku_bi";

        try {
            ps = conn.prepareStatement(sql);
            // 기간이 설정된 경우 쿼리에 날짜 설정 (期間が設定されている場合、クエリに日付を設定)
            if (Kintai_kaishi != null && Kintai_shuuryou != null) {
                ps.setString(1, Kintai_kaishi);
                ps.setString(2, Kintai_shuuryou);
            }
            rs = ps.executeQuery();

            // 결과를 KintaiKiroku 객체로 변환하여 리스트에 추가 (結果をKintaiKirokuオブジェクトに変換してリストに追加)
            while (rs.next()) {
                KintaiKiroku record = new KintaiKiroku(
                    rs.getDate("nyuuryoku_bi"),
                    rs.getString("kintai_mei"),
                    rs.getString("namae_kana"),
                    rs.getString("busho_mei"),
                    rs.getString("yakushoku_mei"),
                    rs.getString("kubun"),
                    rs.getDate("kintai_kaishi") + " ~ " + rs.getDate("kintai_shuuryou"), // 기간을 문자열로 변환
                    rs.getInt("kintai_nissuu"),
                    rs.getInt("teate"),
                    rs.getString("tekiyou")
                );
            
                kintaiRecords.add(record); // 리스트에 추가 (リストに追加)
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(ps);
            JdbcUtil.close(rs);
        }
        return kintaiRecords; // 근태 기록 리스트 반환 (勤怠記録リストを返す)
    }
}
	
	


