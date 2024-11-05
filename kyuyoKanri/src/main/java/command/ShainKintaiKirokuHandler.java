package command;

import service.ShainKintaiKirokuService;
import mvc.command.CommandHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kintai.model.KintaiKiroku;

import java.util.List;

// 최동주 崔東周
public class ShainKintaiKirokuHandler implements CommandHandler {
	/* 근태기록/관리 페이지에서 관리버튼 압하 시 사원의 근태기록을 처리하는 핸들러
	 (勤怠記録/管理ページで管理ボタンを押した際、社員の勤怠記録を処理するハンドラー) */
    private ShainKintaiKirokuService shainKintaiKirokuService = new ShainKintaiKirokuService();

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 사원 ID와 날짜 파라미터를 요청에서 받아옴 (社員IDと日付のパラメータをリクエストから取得)
        String shainIdParam = request.getParameter("shainId");
        String dateParam = request.getParameter("date");

        // 사원 ID가 없으면 400 에러 반환 (社員IDがない場合、400エラーを返す)
        if (shainIdParam == null || shainIdParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "사원 ID가 필요합니다.");
            return null;
        }

        // 사원 ID와 날짜를 처리 (社員IDと日付を処理する)
        Integer shainId = Integer.parseInt(shainIdParam);
        String date = (dateParam != null && !dateParam.isEmpty()) ? dateParam : null; // 날짜가 없을 경우 null 처리 (日付がない場合はnullに処理)
        // 서비스 호출하여 근태 기록을 조회 (サービスを呼び出し、勤怠記録を取得)
        List<KintaiKiroku> kirokuList = shainKintaiKirokuService.getKintaiKirokuList(shainId, date);

        // 조회 결과를 JSP로 전달 (取得結果をJSPに渡す)
        request.setAttribute("kintaiList", kirokuList); // 근태 기록 리스트 (勤怠記録リスト)
        request.setAttribute("shainId", shainId); // 사원 ID (社員ID)
        request.setAttribute("date", date); // 날짜 (日付)

        // JSP로 포워딩할 경로 반환 (모달 창용 JSP) (JSPにフォワードするパスを返す (モーダル用JSP))
        return "/WEB-INF/view/KintaiKirokuModal.jsp";
    }
}
