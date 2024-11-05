package taishoku.handler;
//김영민金榮珉

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import taishoku.model.Shain;
import taishoku.service.ShainTaishokuService;

public class ShainTaishokuHandler implements CommandHandler {

	private ShainTaishokuService shainTaishokuService = new ShainTaishokuService();

	// 서비스 메서드 호출하여 데이터 가져오기
	// サービスメソッドを呼び出してデータを取得
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// 파라미터 값을 받아올 때 null 검사와 기본값 설정  
		// パラメーターの値を受け取る際に、null チェックとデフォルト値の設定
		String pageParam = req.getParameter("page");
		String pageSizeParam = req.getParameter("pageSize");

		int page = (pageParam != null) ? Integer.parseInt(pageParam) : 1;
		int pageSize = (pageSizeParam != null) ? Integer.parseInt(pageSizeParam) : 10;

		// 서비스 메서드 호출하여 데이터 가져오기  サービスメソッドを呼び出してデータを取得
		ArrayList<Shain> shainTaishokuList = shainTaishokuService.getShainTaishokuInfoByPage(page, pageSize);
		int totalShainCount = shainTaishokuService.getTotalShainCount();
		int totalPages = (int) Math.ceil((double) totalShainCount / pageSize);

		if (shainTaishokuList.isEmpty()) {
			// 빈 결과일 경우에 대한 처리  空の結果の場合の処理
			res.setStatus(HttpServletResponse.SC_NO_CONTENT);
			return null;
		}

		// JSP에서 사용할 수 있도록 요청 객체에 데이터 설정
		// JSPで使用できるようにリクエストオブジェクトにデータを設定
		req.setAttribute("shainTaishokuList", shainTaishokuList);
		req.setAttribute("totalPages", totalPages); 
		req.setAttribute("page", page); 
		req.setAttribute("pageSize", pageSize); 

		return "/WEB-INF/view/ShainTaishokuShori.jsp";
	}
}
