package command;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kintai.model.HiyatoiKinmuKiroku;
import mvc.command.CommandHandler;
import service.HiyatoiGetsuService;

// 최동주 崔東周
public class HiyatoiGetsuHandler implements CommandHandler {
	/* 일용직 근무조회(월별조회) 페이지에서 일용직 사원의 월별 근무 기록을 처리하는 핸들러 
		(日雇い勤務照会（月別照会）ページで日雇い社員の月別勤務記録を処理するハンドラー) */
    private HiyatoiGetsuService hiyatoiMonthlyService = new HiyatoiGetsuService();

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	// 파라미터 가져오기 (パラメータを取得する)
        String yearParam = request.getParameter("year");
        String monthParam = request.getParameter("month");
        String selectedBusho = request.getParameter("busho");
        String selectedYakushoku = request.getParameter("yakushoku");

        // 기본값 설정 (デフォルト値を設定)
        int year = (yearParam != null) ? Integer.parseInt(yearParam) : 2024; // 연도 기본값 설정 (年のデフォルト値設定)
        int month = (monthParam != null) ? Integer.parseInt(monthParam) : 11; // 월 기본값 설정 (月のデフォルト値設定)

        // 일용직 사원의 월별 근무 기록 조회 (日雇い社員の月別勤務記録を取得)
        List<HiyatoiKinmuKiroku> hiyatoiList = hiyatoiMonthlyService.getHiyatoiGetsuList(year, month, selectedBusho, selectedYakushoku);

        // 조회 결과를 request에 저장하여 JSP로 전달 (取得結果をrequestに保存し、JSPに渡す)
        request.setAttribute("hiyatoiList", hiyatoiList);

        return "/WEB-INF/view/HiyatoiGetsuSyoukai.jsp";
    }
}
