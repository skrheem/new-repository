package taishoku.handler;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import taishoku.model.Taishoku;
import taishoku.service.TaishokuService;

public class TaishokuHandler implements CommandHandler {

    private TaishokuService taishokuService = new TaishokuService();
    // 메인 프로세스 메서드. 요청에 따라 액션을 판별하고 해당 처리를 호출합니다.
    // メインのプロセスメソッド。リクエストに応じてアクションを判別し、対応する処理を呼び出す
    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("挿入機能 삽입 기능");
    	String action = req.getParameter("action");
    	// 요청 처리  リクエストの処理
        if ("insert".equalsIgnoreCase(action)) {
            return handleInsertRequest(req, res);
        } else if ("delete".equalsIgnoreCase(action)) {
            return handleDeleteRequest(req, res);
        } else if ("get".equalsIgnoreCase(action)) {
            return handleGetRequest(req, res);
        } else if ("update".equalsIgnoreCase(action)) {
        	return handleDeleteRequest(req, res);
        } else {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
            return null;
        }
    }
    // 삽입 요청을 처리하는 메서드. 퇴직 정보와 사원 상태를 삽입하고 업데이트합니다.
    // 挿入リクエストを処理するメソッド。退職情報と社員状態を挿入し、更新します。
    private String handleInsertRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	try {
    		int shainId = Integer.parseInt(req.getParameter("shainId"));
    		String taishokuKubun = req.getParameter("taishokuKubun");
    		String taishokuBiStr = req.getParameter("taishokuBi");
    		String riyuu = req.getParameter("riyuu");
    		String taishokugoRenrakusaki = req.getParameter("taishokugoRenrakusaki");
    		
    		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    		Date taishokuBi = dateFormat.parse(taishokuBiStr);
    		
    		// 서비스 호출로 삽입 및 상태 업데이트 처리  サービスを呼び出して挿入および状態更新を処理 
    		taishokuService.insertTaishokuAndUpdateShain(shainId, taishokuKubun, taishokuBi, riyuu, null, taishokugoRenrakusaki, null, null);
    		
    		req.setAttribute("message", "退職情報が正常に保存されました。(퇴직 정보가 성공적으로 저장되었습니다.)");
    		res.sendRedirect("taishoku.do");
    		return null;
    	} catch (Exception e) {
    		e.printStackTrace();
    		req.setAttribute("message", "退職情報の保存中にエラーが発生しました。  (퇴직 정보 저장 중 오류가 발생했습니다.)");
    		return "taishoku.do";
    	}
    }    
    
    // GET 요청을 처리하는 메서드. 사원 ID를 조회하여 퇴직 정보를 표시합니다.
    // GETリクエストを処理するメソッド。社員IDを取得して退職情報を表示します。
    private String handleGetRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            String shainIdParam = req.getParameter("shainId");
            if (shainIdParam != null) {
                int shainId = Integer.parseInt(shainIdParam);
                ArrayList<Taishoku> taishokuList = taishokuService.getTaishokuInfo(shainId);
                req.setAttribute("taishokuList", taishokuList);
            }
            return "/WEB-INF/view/ShainTaishokuShori.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("message", "社員および退職情報の取得中にエラーが発生しました。(사원 및 퇴직 정보 조회 중 오류가 발생했습니다.)");
            return "/WEB-INF/view/ShainTaishokuShori.jsp";
        }
    }

    // DELETE 요청을 처리하는 메서드. 퇴직 정보를 삭제합니다.
    // DELETEリクエストを処理するメソッド。退職情報を削除します。
    private String handleDeleteRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            int shainId = Integer.parseInt(req.getParameter("shainId"));
            taishokuService.deleteTaishoku(shainId);

            req.setAttribute("message", "退職情報が正常に削除されました。(퇴직 정보가 성공적으로 삭제되었습니다.)");
            return "/WEB-INF/view/ShainTaishokuShori.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("message", "退職情報の削除中にエラーが発生しました。(퇴직 정보 삭제 중 오류가 발생했습니다.)");
            return "/WEB-INF/view/ShainTaishokuShori.jsp";
        }
    }
}
