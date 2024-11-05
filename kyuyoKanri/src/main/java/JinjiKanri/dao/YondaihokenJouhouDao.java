package JinjiKanri.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import JinjiKanri.model.YondaihokenJouhou;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class YondaihokenJouhouDao {

    private static YondaihokenJouhouDao instance = new YondaihokenJouhouDao();

    private YondaihokenJouhouDao() {}
    
    public static YondaihokenJouhouDao getInstance() {
        return instance;
    }
	
	// 김현서 金現徐
	// p.2 4대보험 별 정보 반환 / (기호번호, 취득일, 상실일)
	// p.2 4大保険別情報返還 / (記号番号、取得日、喪失日)
    public List<YondaihokenJouhou> getYondaihokenInfo(Connection conn, Integer shain_id) throws SQLException {
    	List<YondaihokenJouhou> hokenList = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
     
        String[] hokenKubuns = {"国民年金", "健康保険", "雇用保険", "労災保険"};
        
        try {
            String query = "SELECT kigouBangou, shutoku_bi, soshitsu_bi, hokenKubun " +
                           "FROM YondaihokenJouhou " +
                           "WHERE hokenKubun = ? AND shain_id = ?";

            pstmt = conn.prepareStatement(query);
            
       
            for (String hokenKubun : hokenKubuns) {
                pstmt.setString(1, hokenKubun);
                pstmt.setInt(2, shain_id); // shain_id 설정
                rs = pstmt.executeQuery();
                
                while (rs.next()) {
                    YondaihokenJouhou hoken = new YondaihokenJouhou(
                        rs.getString("kigouBangou"),
                        rs.getDate("shutoku_bi"),
                        rs.getDate("soshitsu_bi"),
                        hokenKubun
                    );
                    hokenList.add(hoken);
                }
                rs.close();
            }
        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(pstmt);
        }
        
        return hokenList;
    }

    
}
