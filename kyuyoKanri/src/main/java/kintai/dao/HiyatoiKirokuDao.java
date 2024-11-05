package kintai.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import jdbc.JdbcUtil;
import kintai.model.HiyatoiKinmuKiroku;

//최동주 崔東周
public class HiyatoiKirokuDao {
	
	private static HiyatoiKirokuDao instance = new HiyatoiKirokuDao();
	private HiyatoiKirokuDao() {}
    public static HiyatoiKirokuDao getInstance() {
        return instance;
    }

    // 일용직 사원의 근무 기록을 가져오는 메서드 (日雇い社員の勤務記録を取得するメソッド)
    public ArrayList<HiyatoiKinmuKiroku> getHiyatoiKiroku(Connection conn, Integer shain_id, int year, int month) throws SQLException {
        ArrayList<HiyatoiKinmuKiroku> hiyatoiKiroku = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        // SQL 쿼리 작성 (SQLクエリ作成)
        String sql = "SELECT k.kintai_kaishi, k.kintai_shuuryou, g.genba_project_mei, "
                + "k.kintai_nissuu, k.teate, h.shiharai_ritsu, h.shotokuzei, h.chihozei, "
                + "(k.teate * h.shiharai_ritsu), "	// 지급액 계산 (支給額計算)
                + "((k.teate * h.shiharai_ritsu) - h.shotokuzei + h.chihozei) "		
                // 실지급액 계산 (일당 * 지급율) - (소득세 + 지방세)
                // 実支給額計算 (手当 * 支給率) - (所得税 + 地方税)
                + "FROM KintaiKiroku k "
                + "JOIN HiyatoiKinmuKiroku h ON k.shain_id = h.shain_id "
                + "JOIN GenbaProject g ON h.genba_project_id = g.genba_project_id "
                + "WHERE k.shain_id = ? "
                + "AND EXTRACT(YEAR FROM k.kintai_kaishi) = ? " // 연도 필터링 (年度フィルタリング)
                + "AND EXTRACT(MONTH FROM k.kintai_kaishi) = ? " // 월 필터링 (月フィルタリング)
                + "ORDER BY k.kintai_kaishi";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, shain_id); // 사원 ID 설정 (社員ID設定)
            ps.setInt(2, year); // 연도 설정 (年度設定)
            ps.setInt(3, month); // 월 설정 (月設定)
            rs = ps.executeQuery();

            while (rs.next()) {
                Date kinmu_nengappi= rs.getDate("kinmu_nengappi"); // 근무 시작일 (勤務開始日)
                String genba_project_mei = rs.getString("genba_project_mei"); // 현장/프로젝트 (現場・プロジェクト名)
                Integer teate = rs.getInt("teate"); // 일당 (手当)
                BigDecimal shiharai_ritsu = rs.getBigDecimal("shiharai_ritsu"); // 지급율 (支給率)
                BigDecimal shotokuzei = rs.getBigDecimal("shotokuzei"); // 소득세 (所得税)
                BigDecimal chihozei = rs.getBigDecimal("chihozei"); // 지방소득세 (地方税)
                BigDecimal shikyuu_gaku = rs.getBigDecimal("shikyuu_gaku"); // 지급액 (支給額)
                BigDecimal jissai_shikyuu = rs.getBigDecimal("jissai_shikyuu"); // 실 지급액 (実支給額)

                // HiyatoiKinmuKiroku 객체 생성 및 리스트에 추가 (HiyatoiKinmuKirokuオブジェクトを生成してリストに追加)
                HiyatoiKinmuKiroku kiroku = new HiyatoiKinmuKiroku(
                	kinmu_nengappi,
                    genba_project_mei,
                    teate,
                    shiharai_ritsu,
                    shotokuzei,
                    chihozei,
                    shikyuu_gaku,
                    jissai_shikyuu
                );

                hiyatoiKiroku.add(kiroku); // 리스트에 추가 (リストに追加)
            }
        } finally {
            JdbcUtil.close(ps);
            JdbcUtil.close(rs);
        }

        return hiyatoiKiroku; // 근무 기록 리스트 반환 (勤務記録リストを返す)
    }

    
}
