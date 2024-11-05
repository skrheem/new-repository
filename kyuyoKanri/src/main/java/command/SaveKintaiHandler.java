package command;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kintai.dao.KintaiKoumokuDao;
import kintai.model.KintaiKiroku;
import mvc.command.CommandHandler;
import service.KintaiService;

// 최동주 崔東周
public class SaveKintaiHandler implements CommandHandler {
    private KintaiService kintaiService;
    /* 근태기록/관리 페이지에서 사원을 선택하여 근태기록을 처리하는 핸들러 
       (勤怠記録・管理ページで社員を選択して勤怠記録を処理するハンドラー) */
    public SaveKintaiHandler() {
        KintaiKoumokuDao kintaiDao = KintaiKoumokuDao.getInstance();
        kintaiService = new KintaiService(kintaiDao);
    }

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 사원 선택 확인 (社員の選択を確認)
        String[] selectedShainIds = request.getParameterValues("selectedShain");
        if (selectedShainIds == null || selectedShainIds.length == 0) {
            request.setAttribute("errorMessage", "사원을 선택하세요.");
            return "/WEB-INF/views/KintaiKirokuKanri.jsp";
        }

        // 날짜 형식을 위한 SimpleDateFormat 객체 생성 (日付形式用のSimpleDateFormatオブジェクトを生成)
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // 선택한 사원들의 근태 기록을 생성 (選択した社員の勤怠記録を生成)
        ArrayList<KintaiKiroku> records = new ArrayList<>();
        try {
            for (String shainId : selectedShainIds) {
                KintaiKiroku kintaiKiroku = new KintaiKiroku();
                kintaiKiroku.setShain_id(Integer.parseInt(shainId));

                // 입력된 문자열을 java.sql.Date로 변환 (入力された文字列をjava.sql.Dateに変換)
                try {
                    kintaiKiroku.setNyuuryoku_bi(new Date(sdf.parse(request.getParameter("nyuuryoku_bi")).getTime()));
                    kintaiKiroku.setKintai_kaishi(new Date(sdf.parse(request.getParameter("kintaiKaishi")).getTime()));
                    kintaiKiroku.setKintai_shuuryou(new Date(sdf.parse(request.getParameter("kintaiShuuryou")).getTime()));
                } catch (ParseException e) {
                    request.setAttribute("errorMessage", "날짜 형식이 올바르지 않습니다.");
                    return "/WEB-INF/views/KintaiKirokuKanri.jsp";  // 날짜 형식 오류 시 바로 반환 (日付形式が正しくない場合、すぐに返す)
                }

                // 근태항목 설정 (勤怠項目を設定)
                kintaiKiroku.setKintai_id(Integer.parseInt(request.getParameter("kintaiId")));

                // 금액(수당) 필드 처리 (金額(手当)フィールドの処理)
                String teateStr = request.getParameter("teate");
                if (teateStr != null && !teateStr.isEmpty()) {
                    try {
                        kintaiKiroku.setTeate(Integer.parseInt(teateStr));
                    } catch (NumberFormatException e) {
                        request.setAttribute("errorMessage", "금액(수당)의 형식이 올바르지 않습니다.");
                        return "/WEB-INF/views/KintaiKirokuKanri.jsp";  // 금액 형식 오류 시 반환 (金額形式が正しくない場合、返す)
                    }
                } else {
                    kintaiKiroku.setTeate(0);  // 금액 입력이 없을 시 기본값 설정 (金額入力がない場合、デフォルト値を設定)
                }

                // 근태일수 설정 (勤怠日数を設定)
                kintaiKiroku.setKintai_nissuu(Integer.parseInt(request.getParameter("kintaiNissuu")));

                // 적요 설정 (摘要を設定)
                kintaiKiroku.setTekiyou(request.getParameter("tekiyou"));

                // 등록 날짜는 현재 날짜로 설정 (登録日は現在の日付に設定)
                kintaiKiroku.setTouroku_nengappi(new Date(System.currentTimeMillis()));

                // 레코드를 리스트에 추가 (レコードをリストに追加)
                records.add(kintaiKiroku);
            }

            // 로그: 기록된 데이터 확인 (ログ: 記録されたデータを確認)
            System.out.println("근태 기록 저장 중, 총 " + records.size() + "개의 레코드가 있습니다.");

            // 서비스 레이어에서 저장 처리 (サービスレイヤーで保存処理)
            kintaiService.saveKintaiRecords(records);
            request.setAttribute("successMessage", "근태 기록이 성공적으로 저장되었습니다.");

        } catch (Exception e) {
            // 예외 처리 및 로그 출력 (例外処理とログ出力)
            e.printStackTrace();
            request.setAttribute("errorMessage", "근태 기록 저장 중 오류가 발생했습니다: " + e.getMessage());
        }

        // 저장 완료 후 JSP로 이동 (保存完了後、JSPに移動)
        return "/WEB-INF/views/KintaiKirokuKanri.jsp";
    }
}
