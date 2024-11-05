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
public class KyuukaSyoukaiDao {

    private static KyuukaSyoukaiDao instance = new KyuukaSyoukaiDao();
    private KyuukaSyoukaiDao() {}
    public static KyuukaSyoukaiDao getInstance() {
        return instance;
    }

    // 휴가 기록을 가져오는 메서드 (休暇記録を取得するメソッド)
    public ArrayList<KintaiKiroku> getKyuukaList(Connection conn) throws SQLException {
        ArrayList<KintaiKiroku> kyuukaList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

     // SQL 쿼리 작성 (SQLクエリ作成)
        String sql = "SELECT kk.kyuukashurui, s.kubun, s.shain_id, s.namae_kana, b.busho_mei, y.yakushoku_mei, "
                + "COALESCE(skn.kyuukaNissuu, 0) AS total_kyuuka, "
                + "COALESCE(SUM(k.kintai_nissuu), 0) AS used_kyuuka, "
                + "COALESCE(skn.kyuukaNissuu, 0) - COALESCE(SUM(k.kintai_nissuu), 0) AS remaining_kyuuka "
                + "FROM Shain s "
                + "JOIN Busho b ON s.busho_id = b.busho_id "
                + "JOIN Yakushoku y ON s.yakushoku_id = y.yakushoku_id "
                + "JOIN KintaiKiroku k ON s.shain_id = k.shain_id "
                + "JOIN ShainKyuukaNissuu skn ON s.shain_id = skn.shain_id "
                + "JOIN KyuukaKoumoku kk ON skn.kyuukaKoumoku_id = kk.kyuukaKoumoku_id "
                + "GROUP BY kk.kyuukashurui, s.kubun, s.shain_id, s.namae_kana, b.busho_mei, y.yakushoku_mei, skn.kyuukaNissuu "
                + "ORDER BY s.shain_id";

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                String kubun = rs.getString("kubun");    // 구분 (区分)
                Integer shain_id = rs.getInt("shain_id"); // 사원 ID (社員ID)
                String namae_kana = rs.getString("namae_kana"); // 성명 (氏名)
                String busho_mei = rs.getString("busho_mei");   // 부서명 (部署名)
                String yakushoku_mei = rs.getString("yakushoku_mei"); // 직위명 (役職名)
                String kyuukaShurui = rs.getString("kyuukashurui"); // 휴가 항목 (休暇項目)
                Integer total_kyuuka = rs.getInt("total_kyuuka");   // 전체 휴가 (総休暇)
                Integer used_kyuuka = rs.getInt("used_kyuuka");     // 사용한 휴가 (使用済み休暇)
                Integer remaining_kyuuka = rs.getInt("remaining_kyuuka"); // 잔여 휴가 (残りの休暇)

                KintaiKiroku kyuuka = new KintaiKiroku(
                		kubun,            // 구분 (区分)
                        shain_id,        // 사원 ID (社員ID)
                        namae_kana,      // 성명 (氏名)
                        busho_mei,       // 부서명 (部署名)
                        yakushoku_mei,   // 직위명 (役職名)
                        kyuukaShurui,    // 휴가 항목 (休暇項目)
                        total_kyuuka,    // 총 휴가 (総休暇)
                        used_kyuuka,     // 사용한 휴가 (使用済み休暇)
                        remaining_kyuuka  // 잔여 휴가 (残りの休暇)
                    );
                    kyuukaList.add(kyuuka); // 리스트에 추가 (リストに追加)
            } 
        } catch (SQLException e) {
        	e.printStackTrace();
        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(ps);
        }

        return kyuukaList; // 휴가 기록 리스트 반환 (休暇記録リストを返す)
    }
}
