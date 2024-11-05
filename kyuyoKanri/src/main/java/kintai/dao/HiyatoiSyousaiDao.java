package kintai.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import kintai.model.HiyatoiKinmuKiroku;
import util.ObjectFormatter;

//최동주 崔東周
public class HiyatoiSyousaiDao {
    
    private static HiyatoiSyousaiDao instance = new HiyatoiSyousaiDao();
    private HiyatoiSyousaiDao() {}
    public static HiyatoiSyousaiDao getInstance() {
        return instance;
    }

    // 특정 사원의 근무 기록 상세 조회 메서드 (特定社員の勤務記録詳細を取得するメソッド)
    public ArrayList<HiyatoiKinmuKiroku> getHiyatoiSyousaiList(Connection conn, Integer shain_id) throws SQLException {
        ArrayList<HiyatoiKinmuKiroku> hiyatoiKirokuList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        // SQL 쿼리 작성 (SQLクエリ作成)
        String sql = "SELECT h.kinmu_nengappi, s.shain_id, s.namae_kana, b.busho_mei, g.genba_project_mei, "
                + "k.teate, h.shiharai_ritsu, "
                + "k.kintai_nissuu, "
                + "COALESCE(h.shotokuzei, 0) AS shotokuzei, "
                + "COALESCE(h.chihozei, 0) AS chihozei, "
                + "(k.teate * h.shiharai_ritsu) AS shikyuu_gaku, "
                + "((k.teate * h.shiharai_ritsu) - COALESCE(h.shotokuzei, 0) + COALESCE(h.chihozei, 0)) AS jissai_shikyuu "
                + "FROM KintaiKiroku k "
                + "JOIN HiyatoiKinmuKiroku h ON k.shain_id = h.shain_id "
                + "JOIN GenbaProject g ON h.genba_project_id = g.genba_project_id "
                + "JOIN Shain s ON k.shain_id = s.shain_id "
                + "JOIN Busho b ON s.busho_id = b.busho_id "
                + "WHERE k.shain_id = ? " // 사원 ID로 필터링 (社員IDでフィルタリング)
                + "ORDER BY h.kinmu_nengappi";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, shain_id); // 사원 ID 설정 (社員ID設定)
            rs = ps.executeQuery();

            while (rs.next()) {
                Date kinmu_nengappi = rs.getDate("kinmu_nengappi"); // 근무일자 (勤務日)
                Integer shain_id2 = rs.getInt("shain_id"); // 사원 ID (社員ID)
                String namae_kana = rs.getString("namae_kana"); // 성명 (名前)
                String busho_mei = rs.getString("busho_mei"); // 부서명 (部署名)
                String genba_project_mei = rs.getString("genba_project_mei"); // 현장/프로젝트 (現場・プロジェクト名)
                Integer teate = rs.getInt("teate"); // 일당 (手当)
                BigDecimal shiharai_ritsu = rs.getBigDecimal("shiharai_ritsu"); // 지급율 (支給率)
                BigDecimal shotokuzei = rs.getBigDecimal("shotokuzei"); // 소득세 (所得税)
                BigDecimal chihozei = rs.getBigDecimal("chihozei"); // 지방소득세 (地方税)
                BigDecimal jissai_shikyuu = rs.getBigDecimal("jissai_shikyuu"); // 실 지급액 (実支給額)

                // HiyatoiKinmuKiroku 객체 생성 (HiyatoiKinmuKirokuオブジェクトを生成)
                HiyatoiKinmuKiroku record = new HiyatoiKinmuKiroku(
                    kinmu_nengappi,
                    shain_id2,
                    namae_kana,
                    busho_mei,
                    genba_project_mei,
                    teate,
                    shiharai_ritsu,
                    shotokuzei,
                    chihozei,
                    jissai_shikyuu
                );

                // 리스트에 추가 (リストに追加)
                hiyatoiKirokuList.add(record);
            }
        } finally {
            JdbcUtil.close(ps);
            JdbcUtil.close(rs);
        }

        return hiyatoiKirokuList; // 근무 기록 리스트 반환 (勤務記録リストを返す)
    }
}


