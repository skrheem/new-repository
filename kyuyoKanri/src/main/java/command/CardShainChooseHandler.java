package command;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JinjiKanri.model.Shain;
import mvc.command.CommandHandler;
import service.ShainKihonService;

//김현서 金賢徐
//p.2 인사기록카드의 팝업창에서 전체사원 리스트를 출력하고, 선택된 사원의 shain_id를 가져오는 handler
//p.2 人事記録カードのポップアップウィンドウで全社員リストを出力し、選択された社員のshain_id を取得するhandler
public class CardShainChooseHandler implements CommandHandler {
    
    private ShainKihonService shainKihonService = new ShainKihonService();

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
      
        ArrayList<Shain> shainKihonJyouhou = shainKihonService.getAllShainKihonJyouhou();

     
        if (shainKihonJyouhou == null || shainKihonJyouhou.isEmpty()) {
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().write("[]");
            return null;
        }
  
        StringBuilder json = new StringBuilder();
        json.append("[");
        
        for (int i = 0; i < shainKihonJyouhou.size(); i++) {
            Shain shain = shainKihonJyouhou.get(i);
            json.append("{");
            json.append("\"kubun\":\"").append(shain.getKubun() != null ? shain.getKubun() : "").append("\",");
            json.append("\"shain_id\":").append(shain.getShain_id() != null ? shain.getShain_id() : "null").append(",");
            json.append("\"name\":\"").append(shain.getNamae_kana() != null ? shain.getNamae_kana() : "").append("\",");
            json.append("\"busho_mei\":\"").append(shain.getBusho_mei() != null ? shain.getBusho_mei() : "").append("\",");
            json.append("\"yakushoku_mei\":\"").append(shain.getYakushoku_mei() != null ? shain.getYakushoku_mei() : "").append("\",");
            json.append("\"jyoutai\":\"").append(shain.getJyoutai() != null ? shain.getJyoutai() : "").append("\"");

            json.append("}");
           

            
            if (i < shainKihonJyouhou.size() - 1) {
                json.append(",");
            }
        }
        
        json.append("]");

        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(json.toString());
        out.flush();
        
        return null; 
    }
}
