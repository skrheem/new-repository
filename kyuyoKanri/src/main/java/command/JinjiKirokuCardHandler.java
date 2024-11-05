package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import service.ShainRecordCardService;
import service.ShainRecordCardService.ShainRecordCardInfo;

//김현서 金賢徐
//p.2 인사기록카드 jsp에서 shain_id를 받아와 해당 사원의 데이터를 다시 반환하는 handler
//p.2人事記録カードjspからshain_idを受け取り、該当社員のデータを再度返却するhandler
public class JinjiKirokuCardHandler implements CommandHandler {

    private ShainRecordCardService shainRecordService = new ShainRecordCardService();

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
      
        request.getParameterNames().asIterator().forEachRemaining(name -> {
            System.out.println("Parameter: " + name + " = " + request.getParameter(name));
        });
    	
    	

        String shainIdStr = request.getParameter("shain_id");
        System.out.println("Received shain_id: " + shainIdStr);

        
        if (shainIdStr == null || shainIdStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "사원 ID가 필요합니다.");
            return null;
        }
        
        Integer shainId = Integer.parseInt(shainIdStr);
        

        ShainRecordCardInfo recordCardInfo = shainRecordService.getShainRecordCardInfo(shainId);
        if (recordCardInfo == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "사원 정보를 찾을 수 없습니다.");
            return null;
        }
        
        request.setAttribute("recordCardInfo", recordCardInfo);
        System.out.println("recordCardInfo 데이터: " + recordCardInfo);
        return "/view/JinjiKirokuCard.jsp";
    }
}
