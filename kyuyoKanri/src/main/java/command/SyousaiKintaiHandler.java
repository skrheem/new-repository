package command;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kintai.model.KintaiKiroku;
import mvc.command.CommandHandler;
import service.SyousaiKintaiService;

// 최동주 崔東周
public class SyousaiKintaiHandler implements CommandHandler {
	/* 근태조회(상세조회) 페이지에서 사원들의 상세 근무 기록을 처리하는 핸들러 
	   (勤怠照会（詳細照会）ページで社員の詳細勤務記録を処理するハンドラー) */
    private SyousaiKintaiService syousaiSyoukaiService = new SyousaiKintaiService();

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // GET 파라미터 가져오기 (GETパラメータを取得する)
        String kintaiKaishi = request.getParameter("kintaiKaishi"); // 근태 시작 날짜 (勤怠開始日)
        String kintaiShuuryou = request.getParameter("kintaiShuuryou"); // 근태 종료 날짜 (勤怠終了日)

        // 상세 근태 기록 조회 (詳細勤怠記録を取得する)
        List<KintaiKiroku> kintaiRecords = syousaiSyoukaiService.getKintaiRecords(kintaiKaishi, kintaiShuuryou);

        // 조회 결과를 request에 저장하여 JSP로 전달 (取得結果をrequestに保存し、JSPに渡す)
        request.setAttribute("kintaiRecords", kintaiRecords);

        return "/WEB-INF/view/SyousaiSyoukai.jsp"; // JSP 페이지 경로 (JSPページのパス)
    }
}
