package command;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kintai.model.Shain;
import mvc.command.CommandHandler;
import service.ShainService;

// 최동주 崔東周
public class ShainHandler extends HttpServlet implements CommandHandler {
	// 근태기록/관리 페이지에서 사원 리스트를 처리하는 핸들러 (勤怠記録・管理ページで社員リストを処理するハンドラー)
    private static final long serialVersionUID = 1L;
    private ShainService shainService = new ShainService();

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
        	// 사원 목록을 가져오기 (社員リストを取得する)
            ArrayList<Shain> shainList = shainService.getShainList();
            // JSP에 사원 목록 전달 (JSPに社員リストを渡す)
            request.setAttribute("shainList", shainList);
            // JSP 페이지 경로 반환 (JSPページのパスを返す)
            return "/WEB-INF/view/KintaiKirokuKanri.jsp";
        } catch (SQLException e) {
            e.printStackTrace();
            // 데이터베이스 오류 시 500 에러 반환 (データベースエラー時、500エラーを返す)
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "데이터베이스 오류 발생");
            return null;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	System.out.println("Get 요청"); // 로그: GET 요청 확인 (ログ: GETリクエスト確認)
        try {
        	// process 호출 후 반환된 경로로 포워딩 (processメソッドを呼び出し、返されたパスにフォワード)
            String view = process(request, response);
            if (view != null) {
                request.getRequestDispatcher(view).forward(request, response);
            }
        } catch (Exception e) {
        	// 예외 처리 및 서블릿 예외로 변환 (例外処理とサーブレット例外に変換)
            throw new ServletException(e);
        }
    }
}



