package command;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import service.DeleteShain;

//김현서 金賢徐
//p.1 선택된 사원의 데이터를 삭제하는 handler
//p.1 選択された社員のデータを削除するhandler
public class DeleteShainHandler implements CommandHandler {

    private DeleteShain deleteShainService = new DeleteShain();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
     
        if (req.getMethod().equalsIgnoreCase("POST")) {
            return processPost(req, res);
        } else {
            res.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            return null;
        }
    }

    private String processPost(HttpServletRequest req, HttpServletResponse res) throws Exception {
  
    	

    	
    	req.getParameterMap().forEach((key, values) -> {
    	    System.out.print("파라미터 이름: " + key + ", 값: ");
    	    for (String value : values) {
    	        System.out.print(value + " ");
    	    }
    	    System.out.println();
    	});

    	
    	String shainIdsParam = req.getParameter("shainIds");
    	System.out.println("shain_id: " + shainIdsParam);
    	
    	req.getParameterMap().forEach((key, value) -> {
    	    System.out.println("파라미터 이름: " + key + ", 값: " + Arrays.toString(value));
    	});
    	
        if (shainIdsParam == null || shainIdsParam.isEmpty()) {
            return "/view/JinjiGenkyouKanri.jsp";
        }

        String[] shainIdsArray = shainIdsParam.split(",");
        boolean deleteSuccess = true;

       
        for (String shainIdStr : shainIdsArray) {
            try {
                int shainId = Integer.parseInt(shainIdStr.trim());
                boolean result = deleteShainService.deleteById(shainId);
                if (!result) {
                    deleteSuccess = false;
                }
            } catch (NumberFormatException e) {
                req.setAttribute("errorMessage", "error");
                deleteSuccess = false;
                break;
            }
        }

        return "/view/JinjiGenkyouKanri.jsp";
    }
}
