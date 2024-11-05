package command;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JinjiKanri.model.Shain;
import mvc.command.CommandHandler;
import service.GetShainCount;
import service.GetShainList;

//김현서 金賢徐
//p.1 사원현황/관리 사원 수를 count한 데이터와, 전체 사원 리스트를 가져오는 handler
//p.1 社員現況/管理社員数をカウントしたデータと、全社員リストを読み込むhandler
public class JinjiGenkyouKanriHandler implements CommandHandler {

    private GetShainCount shainCountService = new GetShainCount();
    private GetShainList shainListService = new GetShainList();
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        int[] jyoutaiCounts = shainCountService.getShainJyoutaiCount();
        request.setAttribute("jyoutaiCounts", jyoutaiCounts);
        
       
        int[] kubunCounts = shainCountService.getShainKubunCount();
        request.setAttribute("kubunCounts", kubunCounts);

        
        int totalShainCount = shainCountService.getTotalShainCount();
        request.setAttribute("totalShainCount", totalShainCount);

        

        List<Shain> shainList = shainListService.getAllShains();
        request.setAttribute("shainList", shainList);

        
        return "/view/JinjiGenkyouKanri.jsp";
    }
}
