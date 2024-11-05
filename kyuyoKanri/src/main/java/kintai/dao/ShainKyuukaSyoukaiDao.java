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
import util.ObjectFormatter;

// 최동주 崔東周
public class ShainKyuukaSyoukaiDao {
	
	private static ShainKyuukaSyoukaiDao instance = new ShainKyuukaSyoukaiDao();
	private ShainKyuukaSyoukaiDao() {}
	public static ShainKyuukaSyoukaiDao getInstance() {
		return instance;
	}

	// 특정 사원의 휴가 기록을 조회하는 메서드 (特定社員の休暇記録を取得するメソッド)
    public ArrayList<KintaiKiroku> getShainKyuukaList(Connection conn, Integer shain_id) throws SQLException {
        ArrayList<KintaiKiroku> shainKyuukaList = new ArrayList<>();
        
        PreparedStatement ps = null;
        ResultSet rs = null;

        // SQL 쿼리 작성 (SQLクエリ作成)
        String sql = "SELECT s.namae_kana, k.nyuuryoku_bi, kk.kyuukashurui AS kintai_mei, "
                + "k.kintai_kaishi || ' ~ ' || k.kintai_shuuryou AS kintai_kikan, "
                + "COALESCE(k.kintai_nissuu, 0) AS kintai_nissuu, "
                + "k.tekiyou "
                + "FROM Shain s "
                + "JOIN KintaiKiroku k ON s.shain_id = k.shain_id "
                + "JOIN KyuukaKoumoku kk ON k.kintai_id = kk.kyuukakoumoku_id "
                + "WHERE k.shain_id = ? "
                + "ORDER BY k.nyuuryoku_bi";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, shain_id); // 사원 ID 설정
            rs = ps.executeQuery();

            while (rs.next()) {
                String namae_kana = rs.getString("namae_kana"); // 성명 (氏名)
                Date nyuuryoku_bi = rs.getDate("nyuuryoku_bi"); // 입력일자 (入力日)
                String kyuukaKoumoku = rs.getString("kintai_mei"); // 휴가 항목 (休暇項目)
                String kintai_kikan = rs.getString("kintai_kikan"); // 근태 기간 (勤怠期間)
                Integer kintai_nissuu = rs.getInt("kintai_nissuu"); // 근태 일수 (勤怠日数)
                String tekiyou = rs.getString("tekiyou"); // 적요 (摘要)

                // KintaiKiroku 객체 생성 (KintaiKirokuオブジェクトを生成)
                KintaiKiroku kyuuka = new KintaiKiroku(
                		namae_kana,        // 성명
                        kyuukaKoumoku,     // 휴가 항목
                        shain_id,          // 사원 ID
                        nyuuryoku_bi,      // 입력일자
                        kintai_kikan,      // 근태 기간
                        tekiyou,           // 적요
                        kintai_nissuu      // 근태 일수
                    );

                shainKyuukaList.add(kyuuka); // 리스트에 추가
            }
        } finally {
            JdbcUtil.close(ps);
            JdbcUtil.close(rs);
        }

        return shainKyuukaList; // 휴가 기록 리스트 반환 (休暇記録リストを返す)
    }
}