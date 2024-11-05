package command;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kintai.model.HiyatoiKinmuKiroku;
import mvc.command.CommandHandler;
import service.HiyatoiSyousaiService;

// 최동주 崔東周
public class HiyatoiSyousaiHandler implements CommandHandler {
	// 일용직 근무조회(상세조회) 페이지에서 근무기록을 처리하는 핸들러 (日雇い勤務照会（詳細照会）ページで勤務記録を処理するハンドラー)
    private HiyatoiSyousaiService hiyatoiSyousaiService = new HiyatoiSyousaiService();

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 사원 ID 가져오기 (社員IDを取得する)
        String shainIdParam = request.getParameter("shain_id");
        int shainId = (shainIdParam != null) ? Integer.parseInt(shainIdParam) : 0;

        // 상세 근무 기록 조회 (詳細勤務記録を取得する)
        List<HiyatoiKinmuKiroku> hiyatoiList = hiyatoiSyousaiService.getHiyatoiSyousaiList(shainId);

        // 조회 결과를 request에 저장하여 JSP로 전달 (取得結果をrequestに保存し、JSPに渡す)
        request.setAttribute("hiyatoiList", hiyatoiList);

        return "/WEB-INF/view/HiyatoiSyousaiSyoukai.jsp"; // JSP 페이지 경로 (JSPページのパス)
    }
}
