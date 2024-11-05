package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kintai.model.KintaiKiroku;
import service.KyuukaSyoukaiService;
import java.util.ArrayList;

import mvc.command.CommandHandler;

// 최동주 崔東周
public class KyuukaSyoukaiHandler implements CommandHandler {
	// 휴가조회 페이지에서 휴가 리스트를 처리하는 핸들러 (休暇照会ページで休暇リストを処理するハンドラー)
    private KyuukaSyoukaiService kyuukaSyoukaiService = new KyuukaSyoukaiService();

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 서비스로부터 휴가 리스트 가져오기 (サービスから休暇リストを取得する)
        ArrayList<KintaiKiroku> kyuukaList = kyuukaSyoukaiService.getKyuukaList();
        request.setAttribute("kyuukaList", kyuukaList);

        // JSP로 포워드 (JSPにフォワード)
        return "/WEB-INF/view/KyuukaSyoukai.jsp";
    }
    
}

