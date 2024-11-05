package kintai.dao;
import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import kintai.model.HiyatoiKinmuKiroku;
import util.ObjectFormatter;

//최동주 崔東周
public class HiyatoiGetsuDao {
    
    private static HiyatoiGetsuDao instance = new HiyatoiGetsuDao();
    private HiyatoiGetsuDao() {}
    public static HiyatoiGetsuDao getInstance() {
        return instance;
    }

    /* 특정 연도, 월, 부서, 직급에 따른 일용직 근무 기록을 가져오는 메서드 
       (特定の年度、月、部署、役職に基づく日雇い勤務記録を取得するメソッド) */
    public ArrayList<HiyatoiKinmuKiroku> getHiyatoiGetsuList(Connection conn, Integer year, Integer month, 
                                        String selectedBusho, String selectedYakushoku) throws SQLException {
        ArrayList<HiyatoiKinmuKiroku> hiyatoiKirokuList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        // SQL 쿼리 작성 (SQLクエリ作成)
        String sql = "SELECT s.kubun, s.shain_id, s.namae_kana, b.busho_mei, y.yakushoku_mei, "
                   + "COUNT(h.kinmu_nengappi) AS total_days, "         // 총 근무 일수 (総勤務日数)
                   + "COALESCE(SUM(h.shotokuzei), 0) AS total_shotokuzei, " // 총 소득세 (総所得税)
                   + "COALESCE(SUM(h.chihozei), 0) AS total_chihozei, "     // 총 지방세 (総地方税)
                   + "COALESCE(SUM(k.teate * h.shiharai_ritsu) - SUM(h.shotokuzei) - SUM(h.chihozei), 0) AS total_jissai_shikyuu " // 총 실지급액 (総実支給額)
                   + "FROM Shain s "
                   + "JOIN Busho b ON s.busho_id = b.busho_id "
                   + "JOIN Yakushoku y ON s.yakushoku_id = y.yakushoku_id "
                   + "JOIN HiyatoiKinmuKiroku h ON s.shain_id = h.shain_id "
                   + "JOIN KintaiKiroku k ON s.shain_id = k.shain_id "
                   + "WHERE s.kubun = '日雇い' " // (日雇い社員のみを取得)
                   + "AND EXTRACT(YEAR FROM h.kinmu_nengappi) = ? " // 연도 필터링 (年度フィルタリング)
                   + "AND EXTRACT(MONTH FROM h.kinmu_nengappi) = ? " // 월 필터링 (月フィルタリング)
                   + "AND (? IS NULL OR b.busho_mei = ?) "  // 부서 필터링 (部署フィルタリング)
                   + "AND (? IS NULL OR y.yakushoku_mei = ?) "  // 직급 필터링 (役職フィルタリング)
                   + "GROUP BY s.kubun, s.shain_id, s.namae_kana, b.busho_mei, y.yakushoku_mei "
                   + "ORDER BY s.shain_id";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, year); // 연도 설정 (年度設定)
            ps.setInt(2, month); // 월 설정 (月設定)
            ps.setString(3, selectedBusho); // 부서 설정 (部署設定)
            ps.setString(4, selectedBusho); // 부서 설정 (部署設定)
            ps.setString(5, selectedYakushoku); // 직급 설정 (役職設定)
            ps.setString(6, selectedYakushoku); // 직급 설정 (役職設定)

            rs = ps.executeQuery(); // 쿼리 실행 (クエリ実行)

            while (rs.next()) {
                // 각 컬럼에 대한 데이터 추출
                String kubun = rs.getString("kubun"); // 구분 (区分)
                Integer shain_id = rs.getInt("shain_id"); // 사원 ID (社員ID)
                String namae_kana = rs.getString("namae_kana"); // 이름 (名前)
                String busho_mei = rs.getString("busho_mei"); // 부서 명 (部署名)
                String yakushoku_mei = rs.getString("yakushoku_mei"); // 직위 명 (役職名)
                Integer total_days = rs.getInt("total_days"); // 총 근무 일수 (総勤務日数)
                BigDecimal total_shotokuzei = rs.getBigDecimal("total_shotokuzei"); // 총 소득세 (総所得税)
                BigDecimal total_chihozei = rs.getBigDecimal("total_chihozei"); // 총 지방소득세 (総地方税)
                BigDecimal total_jissai_shikyuu = rs.getBigDecimal("total_jissai_shikyuu"); // 총 실 지급액 (総実支給額)

                // HiyatoiKinmuKiroku 객체 생성 (HiyatoiKinmuKirokuオブジェクトを生成)
                HiyatoiKinmuKiroku record = new HiyatoiKinmuKiroku(
                        kubun,
                        shain_id,
                        namae_kana,
                        busho_mei,
                        yakushoku_mei,
                        total_days,
                        total_shotokuzei,
                        total_chihozei,
                        total_jissai_shikyuu
                );

                // 리스트에 추가 (リストに追加)
                hiyatoiKirokuList.add(record);
            }
        } finally {
            JdbcUtil.close(ps);
            JdbcUtil.close(rs);
        }

        return hiyatoiKirokuList; // 결과 반환 (結果を返す)
    }
}






