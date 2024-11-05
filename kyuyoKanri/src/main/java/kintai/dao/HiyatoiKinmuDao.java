package kintai.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import kintai.model.HiyatoiKinmuKiroku;

//최동주 崔東周
public class HiyatoiKinmuDao {
    
	private static HiyatoiKinmuDao instance = new HiyatoiKinmuDao();
	private HiyatoiKinmuDao() {}
    public static HiyatoiKinmuDao getInstance() {
        return instance;
    }

    
    // 일용직 근무기록 저장 메서드 (日雇い勤務記録を保存するメソッド)
    public void saveHiyatoiRecords(Connection conn, List<HiyatoiKinmuKiroku> records, BigDecimal teate) throws SQLException {
    	// SQL 쿼리 작성 (SQLクエリ作成)
        String sql = "INSERT INTO HiyatoiKinmuKiroku (shain_id, genba_project_id, kinmu_nengappi, "
                   + "shiharai_ritsu, shotokuzei, chihozei, sougaku, jissai_shikyuu, touroku_nengappi) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        	// 리스트에 있는 모든 근무 기록을 저장 (リストにあるすべての勤務記録を保存)
            for (HiyatoiKinmuKiroku record : records) {
                pstmt.setInt(1, record.getShain_id()); // 사원 ID 설정 (社員ID設定)
                pstmt.setInt(2, record.getGenba_project_id()); // 현장/프로젝트 ID 설정 (現場・プロジェクトID設定)
                pstmt.setDate(3, new java.sql.Date(record.getKinmu_nengappi().getTime())); // 근무 날짜 설정 (勤務日設定)

                // 지급율과 teate 값을 사용하여 sougaku와 jissai_shikyuu 계산 (支給率とteateを使用してsougakuと実際支給額を計算)
                BigDecimal shiharai_ritsu = record.getShiharai_ritsu() != null ? record.getShiharai_ritsu() : BigDecimal.valueOf(1.0);
                BigDecimal sougaku = teate.multiply(shiharai_ritsu); // 지급액 계산 (支給額計算)
                BigDecimal shotokuzei = BigDecimal.ZERO; // 소득세 기본값 (所得税のデフォルト値)
                BigDecimal chihozei = BigDecimal.ZERO; // 지방세 기본값 (地方税のデフォルト値)
                BigDecimal jissai_shikyuu = sougaku.subtract(shotokuzei).subtract(chihozei); // 실 지급액 계산 (実際支給額計算)

                pstmt.setBigDecimal(4, shiharai_ritsu); // 지급율 설정 (支給率設定)
                pstmt.setBigDecimal(5, shotokuzei); // 소득세 설정 (所得税設定)
                pstmt.setBigDecimal(6, chihozei); // 지방세 설정 (地方税設定)
                pstmt.setBigDecimal(7, sougaku); // 총액 설정 (総額設定)
                pstmt.setBigDecimal(8, jissai_shikyuu); // 실 지급액 설정 (実際支給額設定)
                pstmt.setDate(9, new java.sql.Date(System.currentTimeMillis())); // 등록 날짜 설정 (登録日設定)

                pstmt.addBatch(); // 다중선택가능
            }
            pstmt.executeBatch(); // 배치 실행 (バッチを実行)
        } catch (SQLException e) { 
            e.printStackTrace(); // 예외 발생 시 로그 출력 (例外発生時にログ出力)
            throw e;
        }
    }
    // KintaiKiroku 테이블에 teate 값을 저장하는 메서드
    /*public void saveKintaiKirokuTeate(Connection conn, Integer shain_id, Date kinmu_nengappi, BigDecimal teate) throws SQLException {
    	String sql = "INSERT INTO KintaiKiroku (shain_id, nyuuryoku_bi, teate) VALUES (?, ?, ?)";
    	
    	try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
    		pstmt.setInt(1, shain_id);
    		pstmt.setDate(2, new java.sql.Date(kinmu_nengappi.getTime()));
    		pstmt.setBigDecimal(3, teate); // teate 값 설정
    		pstmt.executeUpdate();
    	} catch (SQLException e) {
    		e.printStackTrace();
    		throw e;
    	}
    } */
}

