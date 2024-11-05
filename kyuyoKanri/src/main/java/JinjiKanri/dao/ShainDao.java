package JinjiKanri.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import JinjiKanri.model.Shain;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import util.ObjectFormatter;

public class ShainDao {
	
    private static ShainDao instance = new ShainDao();
    
    
    private ShainDao() {}
    
    
    public static ShainDao getInstance() {
        return instance;
    }

	// 김현서 金現徐
	// p.1 총 사원수 카운트
	// p.1 総社員数カウント
	public int getShainCountAll(Connection conn) {
		String query = "SELECT COUNT(*) FROM Shain";
		try (PreparedStatement pstmt = conn.prepareStatement(query); ResultSet rs = pstmt.executeQuery()) {

			if (rs.next()) {
				return rs.getInt(1); // 첫 번째 컬럼의 값을 반환
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	// 김현서 金現徐
	// p.1 사원 상태별 카운트 (재직, 퇴직)
	// p.1 社員の状態別カウント(在職、退職)
	public int[] getShainJyoutaiCNT(Connection conn) throws SQLException {
		String query = "SELECT " + "COUNT(CASE WHEN jyoutai = '在職' THEN 1 END) AS zaishoku_count, "
				+ "COUNT(CASE WHEN jyoutai = '退職' THEN 1 END) AS taishoku_count " + "FROM Shain";

		try (PreparedStatement pstmt = conn.prepareStatement(query); ResultSet rs = pstmt.executeQuery()) {

			if (rs.next()) {
				int zaishokuCount = rs.getInt("zaishoku_count");
				int taishokuCount = rs.getInt("taishoku_count");

				return new int[] { zaishokuCount, taishokuCount };
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		// 결과가 없을 경우 0을 반환
		return new int[] { 0, 0 };
	}

	// 김현서 金現徐
	// p.1 사원 구분별 카운트 (정규직, 계약직, 임시직, 파견직, 위촉직, 일용직)
	// p.1 社員区分別カウント(正規職、契約職、臨時職、派遣職、委嘱職、日雇い)
	public int[] getShainKubunCNT(Connection conn) throws SQLException {
		String query = "SELECT " + "COUNT(CASE WHEN kubun = '正規職' THEN 1 END) AS seiki_count, "
				+ "COUNT(CASE WHEN kubun = '契約職' THEN 1 END) AS keiyaku_count, "
				+ "COUNT(CASE WHEN kubun = '臨時職' THEN 1 END) AS ranji_count, "
				+ "COUNT(CASE WHEN kubun = '派遣職' THEN 1 END) AS haken_count, "
				+ "COUNT(CASE WHEN kubun = '委嘱職' THEN 1 END) AS ishoku_count, "
				+ "COUNT(CASE WHEN kubun = '日雇い' THEN 1 END) AS hiyatoi_count " + "FROM Shain";

		try (PreparedStatement pstmt = conn.prepareStatement(query); ResultSet rs = pstmt.executeQuery()) {

			if (rs.next()) {
				int seikiCount = rs.getInt("seiki_count");
				int keiyakuCount = rs.getInt("keiyaku_count");
				int ranjiCount = rs.getInt("ranji_count");
				int hakenCount = rs.getInt("haken_count");
				int ishokuCount = rs.getInt("ishoku_count");
				int hiyatoiCount = rs.getInt("hiyatoi_count");

				return new int[] { seikiCount, keiyakuCount, ranjiCount, hakenCount, ishokuCount, hiyatoiCount };
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}

		// 결과가 없을 경우 0을 반환
		return new int[] { 0, 0 };
	}


	// 김현서 金現徐
	// p.1 전체 사원 리스트 표시
	// p.1 全社員リスト表示
	public ArrayList<Shain> getAllShains(Connection conn) throws SQLException {

		ArrayList<Shain> shainList = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			String query = "SELECT s.kubun, s.nyuusha_nengappi, s.shain_id, s.namae_kana, s.namae_eigo, b.busho_mei, "
					+ "y.yakushoku_mei, s.jumin_bangou, s.kokuseki, s.juusho, s.denwa_uchi, s.denwa_keitai, "
					+ "s.meeru, s.SNS, s.taisha_nengappi, s.jyoutai, sk.kouza_bangou " + "FROM Shain s "
					+ "LEFT JOIN ShainKihonKyuu sk ON s.shain_id = sk.shain_id "
					+ "JOIN busho b ON s.busho_id = b.busho_id "
					+ "JOIN yakushoku y ON s.yakushoku_id = y.yakushoku_id";

			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Shain shain = new Shain(rs.getInt("shain_id"), rs.getString("namae_kana"), rs.getString("namae_eigo"),
						rs.getDate("nyuusha_nengappi"), rs.getDate("taisha_nengappi"), rs.getString("busho_mei"),
						rs.getString("yakushoku_mei"), rs.getString("kokuseki"), rs.getString("jumin_bangou"),
						rs.getString("juusho"), rs.getString("denwa_uchi"), rs.getString("denwa_keitai"),
						rs.getString("meeru"), rs.getString("SNS"), rs.getString("kubun"), rs.getString("jyoutai"),
						rs.getString("kouza_bangou"));
				shainList.add(shain); // 리스트에 추가
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
		return shainList;
	}



	// 김현서 金賢徐
	// p.1 특정 사원의 정보를 삭제하는 메서드
	// p.1 特定社員の情報を削除するメソッド
	public boolean deleteShainById(Connection conn, int shainId) throws SQLException {
		String query = "DELETE FROM Shain WHERE shain_id = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setInt(1, shainId);
			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} 
	}

	// 김현서 金現徐
	// p.1 사원정보 미리보기 미니팝업 / (사원사진 경로, 이름, 입사일, 주소)
	// p.1 社員情報プレビューミニポップアップ/（社員写真経路、氏名、入社日、住所）
	public ArrayList<Shain> getPopupShainList(Connection conn, int shainId) throws SQLException {

	    ArrayList<Shain> shainList = new ArrayList<>();
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    try {
	        String query = "SELECT shain_image_keiro, namae_kana, nyuusha_nengappi, juusho "
	                     + "FROM Shain s "
	                     + "WHERE shain_id = ?";

	        stmt = conn.prepareStatement(query);
	        stmt.setInt(1, shainId); // shain_id 값을 설정
	        rs = stmt.executeQuery();

	        while (rs.next()) {
	            Shain shain = new Shain(
	                    rs.getString("shain_image_keiro"),
	                    rs.getString("namae_kana"),
	                    rs.getDate("nyuusha_nengappi"),
	                    rs.getString("juusho")
	            );
	            shainList.add(shain); // 리스트에 추가
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        JdbcUtil.close(rs);
	        JdbcUtil.close(stmt);
	    }
	    return shainList;
	}


	// 김현서 金賢徐
	// p.2 인사기록카드_사원기본정보 리스트
	// p.2 人事記録カード_社員基本情報リスト
	public ArrayList<Shain> getShainKihonJyouhou(Connection conn, Integer shain_id) throws SQLException {
		ArrayList<Shain> shainList = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			String query = "SELECT shain_id, nyuusha_nengappi, taisha_nengappi, namae_kana, namae_eigo, "
					+ "jumin_bangou, kubun, juusho, denwa_uchi, denwa_keitai, meeru " + "FROM Shain WHERE shain_id = ?";

			stmt = conn.prepareStatement(query);
			stmt.setInt(1, shain_id); // 첫 번째 ?에 shain_id 값을 설정

			rs = stmt.executeQuery();

			while (rs.next()) {
				Shain shain = new Shain(rs.getInt("shain_id"), rs.getDate("nyuusha_nengappi"),
						rs.getDate("taisha_nengappi"), rs.getString("namae_kana"), rs.getString("namae_eigo"),
						rs.getString("jumin_bangou"), rs.getString("kubun"), rs.getString("juusho"),
						rs.getString("denwa_uchi"), rs.getString("denwa_keitai"), rs.getString("meeru"));
				shainList.add(shain);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}

		return shainList;
	}


	// 김현서 金賢徐
	// p.3 구분, 사원번호, 성명, 부서, 직위, 상태
	// p.3 私の証明書発行_社員選択リスト/区分、名前、部署、役職、状態
	public static ArrayList<Shain> getAllShainKihonJyouhou(Connection conn) throws SQLException {

	    ArrayList<Shain> shainList = new ArrayList<>();
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    try {
	        String query = "SELECT s.kubun, s.shain_id, s.namae_kana, b.busho_mei, y.yakushoku_mei, s.jyoutai "
	                     + "FROM Shain s "
	                     + "JOIN Busho b ON s.busho_id = b.busho_id "
	                     + "JOIN Yakushoku y ON s.yakushoku_id = y.yakushoku_id";

	        stmt = conn.prepareStatement(query);
	        
	        rs = stmt.executeQuery();

	        while (rs.next()) {
	            Shain shain = new Shain(
	                rs.getString("kubun"),
	                rs.getInt("shain_id"),
	                rs.getString("namae_kana"),
	                rs.getString("busho_mei"),
	                rs.getString("yakushoku_mei"),
	                rs.getString("jyoutai")
	            );

	            shainList.add(shain); // 리스트에 추가
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        JdbcUtil.close(rs);
	        JdbcUtil.close(stmt);
	    }
	    return shainList;
	}

	
	
	// 김현서 金賢徐
	// p.3 제 증명서 발급_ 사원선택 리스트 / 구분, 성명, 부서, 직위, 상태
	// p.3 証明書発行_社員選択リスト/区分、名前、部署、役職、状態
	public ArrayList<Shain> getShainJouHouShoumeiSho(Connection conn, Integer shain_id) throws SQLException {

		ArrayList<Shain> shainList = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			String query = "SELECT s.kubun, s.namae_kana, b.busho_mei, y.yakushoku_mei, s.jyoutai " + "FROM Shain s "
					+ "JOIN Busho b ON s.busho_id = b.busho_id "
					+ "JOIN Yakushoku y ON s.yakushoku_id = y.yakushoku_id " + "WHERE shain_id = ?";

			stmt = conn.prepareStatement(query);
			stmt.setInt(1, shain_id);
			
			rs = stmt.executeQuery();

			while (rs.next()) {
				Shain shain = new Shain(
				rs.getString("kubun"),
				rs.getString("namae_kana"),
				rs.getString("busho_mei"),
				rs.getString("yakushoku_mei"),
				rs.getString("jyoutai")
				);

				shainList.add(shain); // 리스트에 추가
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
		return shainList;
	}
	
	// 김현서 金賢徐
	// p.3 제 증명서 발급_ 사원 리스트 검색기능 / 구분, 성명, 부서, 직위, 상태 중 (구분, 성명, 부서, 직위)로 검색 가능
	// p.3 諸証明書発行_社員リスト検索機能/区分、氏名、部署、職位、状態のうち(区分、氏名、部署、職位)で検索可能
	public ArrayList<Shain> searchShainJouHouShoumeiSho(Connection conn, String kubun, String namae_kana, String busho_mei, String yakushoku_mei) throws SQLException {
	    ArrayList<Shain> shainList = new ArrayList<>();
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    try {
	        StringBuilder query = new StringBuilder(
	            "SELECT s.kubun, s.namae_kana, b.busho_mei, y.yakushoku_mei, s.jyoutai " +
	            "FROM Shain s " +
	            "JOIN Busho b ON s.busho_id = b.busho_id " +
	            "JOIN Yakushoku y ON s.yakushoku_id = y.yakushoku_id " +
	            "WHERE 1=1"
	        );

	        if (kubun != null) {
	            query.append(" AND s.kubun = ?");
	        }
	        if (namae_kana != null) {
	            query.append(" AND s.namae_kana = ?");
	        }
	        if (busho_mei != null) {
	            query.append(" AND b.busho_mei = ?");
	        }
	        if (yakushoku_mei != null) {
	            query.append(" AND y.yakushoku_mei = ?");
	        }

	        stmt = conn.prepareStatement(query.toString());

	        int paramIndex = 1;
	        if (kubun != null) {
	            stmt.setString(paramIndex++, kubun);
	        }
	        if (namae_kana != null) {
	            stmt.setString(paramIndex++, namae_kana);
	        }
	        if (busho_mei != null) {
	            stmt.setString(paramIndex++, busho_mei);
	        }
	        if (yakushoku_mei != null) {
	            stmt.setString(paramIndex++, yakushoku_mei);
	        }

	        rs = stmt.executeQuery();

	        while (rs.next()) {
	            Shain shain = new Shain(
	                rs.getString("kubun"),
	                rs.getString("namae_kana"),
	                rs.getString("busho_mei"),
	                rs.getString("yakushoku_mei"),
	                rs.getString("jyoutai")
	            );
	            shainList.add(shain);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        JdbcUtil.close(rs);
	        JdbcUtil.close(stmt);
	    }
	    return shainList;
	}

	

	
	// 김현서 金賢徐
	// p.3 제증명서발급_인적사항 (재직, 경력, 퇴직 증명서 공통)
	// p.3 証明書＿人的事項　（在職、経歴、退職証明書共通）
	public ArrayList<Shain> getShomeishoJintekikou(Connection conn, Integer shain_id) throws SQLException {

		ArrayList<Shain> shainList = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			String query = "SELECT namae_kana, jumin_bangou, juusho FROM Shain WHERE shain_id = ?";

			stmt = conn.prepareStatement(query);
			stmt.setInt(1, shain_id);
			
			rs = stmt.executeQuery();

			while (rs.next()) {
				Shain shain = new Shain(
				rs.getString("namae_kana"),
				rs.getString("jumin_bangou"),
				rs.getString("juusho")
				);

				shainList.add(shain); // 리스트에 추가
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
		return shainList;
	}
	
	// 김현서 金賢徐
	// p.3 제증명서발급_인적사항(재직, 경력, 퇴직 증명서 공통)/ 주민번호 뒷자리 비표시
	// p.3 諸証明書発行_人的事項（在職、経歴、退職証明書共通）/ 住民番号の後ろの数字は非表示
	public ArrayList<Shain> getJuminBangouMasked(Connection conn, Integer shain_id) throws SQLException {

		ArrayList<Shain> shainList = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			String query = "SELECT namae_kana, jumin_bangou, juusho FROM Shain WHERE shain_id = ?";

			stmt = conn.prepareStatement(query);
			stmt.setInt(1, shain_id);
			
			rs = stmt.executeQuery();

			while (rs.next()) {
				
				String fullJuminBangou = rs.getString("jumin_bangou");
				String maskedJuminBangou = fullJuminBangou.substring(0,6)+"*******";
				Shain shain = new Shain(
				rs.getString("namae_kana"),
				maskedJuminBangou,
				rs.getString("juusho")
				);

				shainList.add(shain); // 리스트에 추가
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
		return shainList;
	}
	

	// 김현서 金賢徐
	// p.3 부서, 직위, 입사일, 근속기간 출력 / 재직증명서에서 사용
	// p.3 部署、職位、入社日、勤続期間の出力/在職証明書で使用
	public ArrayList<Shain> getZaishokuJouhouByShoumeisho(Connection conn, Integer shain_id) throws SQLException {

		ArrayList<Shain> shainList = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			String query = "SELECT b.busho_mei, y.yakushoku_mei, s.nyuusha_nengappi " + "FROM Shain s "
					+ "JOIN Busho b ON s.busho_id = b.busho_id "
					+ "JOIN Yakushoku y ON s.yakushoku_id = y.yakushoku_id " + "WHERE shain_id = ?";

			stmt = conn.prepareStatement(query);
			stmt.setInt(1, shain_id);
			
			rs = stmt.executeQuery();

			while (rs.next()) {
				Shain shain = new Shain(
				rs.getString("busho_mei"),
				rs.getString("yakushoku_mei"),
				rs.getDate("nyuusha_nengappi")
				);

				shainList.add(shain); // 리스트에 추가
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
		return shainList;
	}
	
	
	
	// 김현서 金賢徐
	// p.3 근무기간, 근무부서, 직위 출력(근무기간을 이용해 근무연한도 계산) / 경력증명서&퇴직증명서에서 사용
	// p.3 勤務期間、勤務部署、職位の出力(勤務期間を利用して勤務年数を計算)/経歴証明書&退職証明書で使用
	public ArrayList<Shain> getTaishokuJouhouByShoumeisho(Connection conn, Integer shain_id) throws SQLException {

		ArrayList<Shain> shainList = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			String query = "SELECT s.nyuusha_negappi, s.taisha_nengappi, b.busho_mei, y.yakushokku_mei " 
					+ "FROM Shain s "
					+ "JOIN Busho b ON s.busho_id = b.busho_id "
					+ "JOIN Yakushoku y ON s.yakushoku_id = y.yakushoku_id " + "WHERE shain_id = ?";

			stmt = conn.prepareStatement(query);
			stmt.setInt(1, shain_id);
			
			rs = stmt.executeQuery();

			while (rs.next()) {
				Shain shain = new Shain(
				rs.getDate("nyuusha_nengappi"),
				rs.getDate("taisha_nengappi"),
				rs.getString("busho_mei"),
				rs.getString("yakushoku_mei")
				);

				shainList.add(shain); // 리스트에 추가
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
		return shainList;
	}
	
	
	
	// 김현서 金賢徐
	// p.4 구분, 이름, 부서, 직위 출력 / 제 증명서 발급대장, 증명서정보 리스트와 함께 사용
	// p.4 区分、氏名、部署、職位出力/諸証明書発行台帳、証明書情報リストと一緒に使用
	public ArrayList<Shain> getShoumeishoJouhou(Connection conn, Integer shain_id) throws SQLException {

		ArrayList<Shain> shainList = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			String query = "SELECT s.kubun, s.namae_kana, b.busho_mei, y.yakushokku_mei " 
					+ "FROM Shain s "
					+ "JOIN Busho b ON s.busho_id = b.busho_id "
					+ "JOIN Yakushoku y ON s.yakushoku_id = y.yakushoku_id " + "WHERE shain_id = ?";

			stmt = conn.prepareStatement(query);
			stmt.setInt(1, shain_id);
			
			rs = stmt.executeQuery();

			while (rs.next()) {
				Shain shain = new Shain(
				rs.getString("kubun"),
				rs.getString("namae_kana"),
				rs.getString("busho_mei"),
				rs.getString("yakushoku_mei")
				);

				shainList.add(shain); // 리스트에 추가
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
		return shainList;
	}
	
	
	

}
	
