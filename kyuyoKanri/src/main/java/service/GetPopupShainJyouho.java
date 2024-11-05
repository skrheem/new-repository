package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import JinjiKanri.dao.KoujoKoumokuDao;
import JinjiKanri.dao.KoukinzeiDao;
import JinjiKanri.dao.ShainDao;
import JinjiKanri.model.KoujoKoumoku;
import JinjiKanri.model.Shain;
import jdbc.connection.ConnectionProvider;

// 김현서 金現徐
// p.1 미니 팝업창에 표시 될 사원 정보 리스트
// p.1 ミニポップアップウィンドウに表示される社員情報リスト
public class GetPopupShainJyouho {

	private ShainDao shainDao = ShainDao.getInstance();
	private KoujoKoumokuDao koujoKoumokuDao = KoujoKoumokuDao.getInstance();
	private KoukinzeiDao koukinzeiDao = KoukinzeiDao.getInstance();

	public GetPopupShainJyouho(ShainDao shainDao, KoujoKoumokuDao koujoKoumokuDao, KoukinzeiDao koukinzeiDao) {
		this.shainDao = shainDao;
		this.koujoKoumokuDao = koujoKoumokuDao;
		this.koukinzeiDao = koukinzeiDao;
	}

	public GetPopupShainJyouho() {

	}

	public PopupShainInfo getPopupShainInfo(int shainId) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();

			ArrayList<Shain> shainList = shainDao.getPopupShainList(conn, shainId);

			for (Shain shain : shainList) {
				Date nyuushaNengappi = shain.getNyuusha_nengappi();
				int yearsOfService = calculateYearsOfService(nyuushaNengappi);
				shain.setYearsOfService(yearsOfService + "년"); // 근속년수 설정
			}

			ArrayList<KoujoKoumoku> koujoKoumokuList = koujoKoumokuDao.getPopupShainList(conn, shainId);

			String koukinzeiKubun = koukinzeiDao.getPopupShainList(conn, shainId);

			PopupShainInfo popupInfo = new PopupShainInfo(shainList, koujoKoumokuList, koukinzeiKubun);
			return popupInfo;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 김현서 金現徐
	// p.1 미니 팝업창에 표시 될 사원 근속년도
	// p.1 ミニポップアップウィンドウに表示される社員勤続年度
	private int calculateYearsOfService(Date nyuushaNengappi) {
		Calendar startDate = Calendar.getInstance();
		startDate.setTime(nyuushaNengappi);
		Calendar currentDate = Calendar.getInstance();
		int years = currentDate.get(Calendar.YEAR) - startDate.get(Calendar.YEAR);

		
		if (currentDate.get(Calendar.DAY_OF_YEAR) < startDate.get(Calendar.DAY_OF_YEAR)) {
			years--;
		}
		return years;
	}

	// 김현서 金現徐
	// p.1 미니 팝업창에 표시 될 사원 정보 리스트
	// p.1 ミニポップアップウィンドウに表示される社員情報リスト
	public static class PopupShainInfo {
		private ArrayList<Shain> shainList;
		private ArrayList<KoujoKoumoku> koujoKoumokuList;
		private String koukinzeiKubun;

		public PopupShainInfo(ArrayList<Shain> shainList, ArrayList<KoujoKoumoku> koujoKoumokuList,
				String koukinzeiKubun) {
			this.shainList = shainList;
			this.koujoKoumokuList = koujoKoumokuList;
			this.koukinzeiKubun = koukinzeiKubun;
		}

		public ArrayList<Shain> getShainList() {
			return shainList;
		}

		public ArrayList<KoujoKoumoku> getKoujoKoumokuList() {
			return koujoKoumokuList;
		}

		public String getKoukinzeiKubun() {
			return koukinzeiKubun;
		}
	}
}
