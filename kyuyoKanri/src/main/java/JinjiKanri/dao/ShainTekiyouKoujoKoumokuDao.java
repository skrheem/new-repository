package JinjiKanri.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import JinjiKanri.model.ShainTekiyouKoujoKoumoku;
import jdbc.JdbcUtil;

public class ShainTekiyouKoujoKoumokuDao {
	
    private static ShainTekiyouKoujoKoumokuDao instance = new ShainTekiyouKoujoKoumokuDao();

    private ShainTekiyouKoujoKoumokuDao() {}
    
    public static ShainTekiyouKoujoKoumokuDao getInstance() {
        return instance;
    }
    
	// 김현서 金賢徐
	// p.1 미니팝업 (사원이미지, 이름, 주소, 갑근세_명)
	// p.1 ミニポップアップ(社員画像、名前、住所、甲勤税_名)
	public ArrayList<ShainTekiyouKoujoKoumoku> getShainListMiniPopupOne(Connection conn, Integer shain_id, String koujoKoumokuKubun) throws SQLException {
	    
	    ArrayList<ShainTekiyouKoujoKoumoku> resultList = new ArrayList<>();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {
	        String query = "SELECT s.shain_image_keiro, s.namae_kana, s.nyuusha_nengappi, s.juusho, k.koujoKoumoku_mei " +
	                       "FROM ShainTekiyouKoujoKoumoku st " +
	                       "JOIN KoujoKoumoku k ON st.koujoKoumoku_id = k.koujoKoumoku_id " +
	                       "JOIN Shain s ON st.shain_id = s.shain_id " +
	                       "WHERE st.shain_id = ? AND k.koujoKoumokuKubun = ?";

	        pstmt = conn.prepareStatement(query);
	        pstmt.setInt(1, shain_id);
	        pstmt.setString(2, koujoKoumokuKubun);

	        rs = pstmt.executeQuery();

	        while (rs.next()) {
	            ShainTekiyouKoujoKoumoku record = new ShainTekiyouKoujoKoumoku();
	            record.setShainImageKeiro(rs.getString("shain_image_keiro"));
	            record.setNamaeKana(rs.getString("namae_kana"));
	            record.setNyuushaNengappi(rs.getDate("nyuusha_nengappi"));
	            record.setJuusho(rs.getString("juusho"));
	            record.setKoujoKoumokuMei(rs.getString("koujoKoumoku_mei"));

	            resultList.add(record);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw e;
	    } finally {
	        JdbcUtil.close(rs);
	        JdbcUtil.close(pstmt);
	    }

	    return resultList;
	}

	
	
	
	// 김현서 金賢徐
	// p.1 미니팝업의 두번째 정보 / 사대보험(국민연금, 건강보험, 고용보험)의 id가 있는지 확인
	// p.1 ミニポップアップの2番目の情報/事大保険(国民年金、健康保険、雇用保険)のidがあるか確認
	public String getShainListMiniPopupTwo(Connection conn, Integer shain_id, String koujoKoumoku_id) throws SQLException {
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {
	        String query = 
	            "SELECT koujokoumoku_id " +
	            "FROM ShainTekiyouKoujoKoumoku " +
	            "WHERE shain_id = ? AND koujokoumoku_id = ?";

	        pstmt = conn.prepareStatement(query);
	        pstmt.setInt(1, shain_id); 
	        pstmt.setString(2, koujoKoumoku_id); 

	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	            // 값이 있으면 "控除" 반환
	        	// 値があれば"控除"返却
	            return "控除";
	        } else {
	            // 값이 없으면 "非控除" 반환
	        	// 値がなければ"非控除"返還
	            return "非控除";
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw e;
	    } finally {
	        JdbcUtil.close(rs);
	        JdbcUtil.close(pstmt);
	    }
	}


}
