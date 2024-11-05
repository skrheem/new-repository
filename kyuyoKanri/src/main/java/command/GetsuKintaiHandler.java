package command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kintai.model.KintaiKiroku;
import mvc.command.CommandHandler;
import service.GetsuKintaiService;

// 최동주 崔東周
public class GetsuKintaiHandler implements CommandHandler {
	/* 근태조회(월별조회) 페이지에서 사원들의 월별 근무 기록을 처리하는 핸들러 
	　(勤怠照会（月別照会）ページで社員の月別勤務記録を処理するハンドラー)*/
    private GetsuKintaiService kintaiService = new GetsuKintaiService();

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	// 파라미터 가져오기 (パラメータを取得する)
        String yearParam = request.getParameter("year");
        String monthParam = request.getParameter("month");
        String selectedJyoutai = request.getParameter("jyoutai");
        String selectedKubun = request.getParameter("kubun");
        String selectedBusho = request.getParameter("busho");
        String selectedYakushoku = request.getParameter("yakushoku");
        
        // year와 month 파라미터가 null인 경우 기본값 대신 null로 처리 (yearとmonthパラメータがnullの場合、デフォルトではなくnullで処理)
        Integer year = (yearParam != null && !yearParam.isEmpty()) ? Integer.parseInt(yearParam) : null;
        Integer month = (monthParam != null && !monthParam.isEmpty()) ? Integer.parseInt(monthParam) : null;

        // 월별 근태 목록 조회 (月別の勤怠リストを取得)
        List<KintaiKiroku> kintaiList = kintaiService.getKintaiList(year, month, selectedJyoutai, selectedKubun, selectedBusho, selectedYakushoku);
        
        // 조회 결과를 request에 저장하여 JSP로 전달 (取得結果をrequestに保存し、JSPに渡す)
        request.setAttribute("kintaiList", kintaiList);

        return "/WEB-INF/view/GetsuSyoukai.jsp"; // JSP 페이지 경로
    }
}