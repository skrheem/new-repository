package command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kintai.model.Shain;
import mvc.command.CommandHandler;
import service.HiyatoiService;

// 최동주 崔東周
public class HiyatoiHandler implements CommandHandler {
	/* 일용직 근무기록/관리 페이지에서 일용직 사원의 리스트를 처리하는 핸들러 
	　(日雇い勤務記録・管理ページで日雇い社員のリストを処理するハンドラー) */
    private HiyatoiService hiyatoiService = new HiyatoiService();

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	// 클라이언트에서 'jyoutai' 파라미터를 받아옴 (없을 경우 기본값은 '在職')
        // クライアントから 'jyoutai' パラメータを受け取る (ない場合はデフォルト値は '在職')
        String jyoutai = request.getParameter("jyoutai");
        if (jyoutai == null) {
            jyoutai = "在職"; // 기본값으로 '在職'을 사용 (デフォルト値として '在職' を使用)
        }

        // 서비스에서 일용직 사원 목록을 가져옴 (サービスから日雇い社員のリストを取得する)
        ArrayList<Shain> hiyatoiList = hiyatoiService.getHiyatoiList(jyoutai);

        // 가져온 사원 목록을 request 객체에 저장하여 JSP에 전달 (取得した社員のリストをrequestオブジェクトに保存し、JSPに渡す)
        request.setAttribute("hiyatoiList", hiyatoiList);

        // 결과를 보여줄 JSP 페이지로 이동 (結果を表示するJSPページに移動)
        return "/WEB-INF/view/HiyatoiKinmuKiroku.jsp";
    }
}