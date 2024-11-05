package command;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.connection.ConnectionProvider;
import mvc.command.CommandHandler;
import service.GetPopupShainJyouho;
import service.GetPopupShainJyouho.PopupShainInfo;

//김현서 金賢徐
//p.1 사원현황/관리 사원리스트를 선택하면 나타나는 미니팝업창 리스트 정보를 내보내는 handler
//p.1社員現況/管理社員リストを選択すると現れるミニポップアップウィンドウリスト情報を流すhandler
public class MiniPopupShainHandler implements CommandHandler {

	private GetPopupShainJyouho getPopupShainService = new GetPopupShainJyouho();

	
	
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int shainId = Integer.parseInt(request.getParameter("shainId"));
        PopupShainInfo popupInfo = null;

        try (Connection conn = ConnectionProvider.getConnection()) {
            popupInfo = getPopupShainService.getPopupShainInfo(shainId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        
        request.setAttribute("popupInfo", popupInfo);
        return "/view/popupShainInfo.jsp";
    }
}
