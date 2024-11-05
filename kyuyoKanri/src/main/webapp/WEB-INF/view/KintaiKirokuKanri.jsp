<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!-- 최동주 崔東周 -->
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>勤怠記録・管理</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/KintaiKirokuKanri.css">
</head>
<body>
<h2>勤怠記録・管理</h2>
<div class="container">
    <div class="left-section">
    <div class="filter">    
        <input type="text" placeholder="検索語入力" />
        <button class="btn">🔍</button>
        <button class="btn">全体表示</button>
        <span style="color: red; margin-left: 7px;">* 複数選択時に勤怠記録を一括適用することができます。</span>
        <select name="statusFilter">
            <option value="在職">在職</option>
            <option value="退職">退職</option>
        </select>
    </div>

    <div class="table-container">
        <table>
            <thead>
                <tr>
                    <th><input type="checkbox" id="selectAll" onclick="selectAllShain(this)" /></th>
                    <th>区分</th>
                    <th>社員番号</th>
                    <th>名前</th>
                    <th>部署</th>
                    <th>役職</th>
                    <th>勤怠記録
                        <button class="small-btn" onclick="showTip()">?</button>
                    </th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="shain" items="${shainList}">
                    <tr>
                        <td><input type="checkbox" class="shainCheckbox" name="selectedShain" value="${shain.shain_id}" /></td>
                        <td>${shain.kubun}</td>
                        <td>No-${shain.shain_id}</td>
                        <td>${shain.namae_kana}</td>
                        <td>${shain.busho_mei}</td>
                        <td>${shain.yakushoku_mei}</td>
                        <td><button class="btn" onclick="openKintaiForm(${shain.shain_id})">管理</button></td>
                        
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>

    <!-- 근태 기록 관리 (오른쪽 부분) -->

    <div class="right-section">
    <button class="btn" onclick="showTip()">? Tip</button>

    <!-- 에러 메시지 또는 성공 메시지를 보여줍니다 -->
    <c:if test="${not empty errorMessage}">
        <div style="color:red;">${errorMessage}</div>
    </c:if>
    <c:if test="${not empty successMessage}">
        <div style="color:green;">${successMessage}</div>
    </c:if>

	<div class="table-container2">
    <!-- 사원의 근태 기록을 저장하는 폼 -->
    <form action="${pageContext.request.contextPath}/saveKintaiRecord.do" method="post" onsubmit="return validateSelectedShain();" style = "width: 500px;  border-top: 3px solid #000000;">
	
        <input type="hidden" name="shainId" id="shainId" />

        <!-- 입력일자 -->
        <label>入力日:</label>
        <input type="date" name="nyuuryoku_bi" id="nyuuryoku_bi" required />
        <br><br>

        <!-- 근태항목 -->
        <label>勤怠項目:</label>
        <select name="kintaiId" id="kintaiId" onchange="updateFormFields()" required>
            <option value="">選択</option>
            <option value="1">年次休暇</option>
			<option value="2">半日休暇</option>
			<option value="3">遅刻</option>
			<option value="4">早退</option>
			<option value="5">外勤</option>
			<option value="6">休日出勤</option>
			<option value="7">残業</option>
			<option value="8">褒賞休暇</option>
			<option value="9">夜勤</option>
			<option value="10">請願休暇</option>
        </select>
        <br><br>

        <!-- 휴가 적용 기간 (근태항목 선택 시 표시) -->
        <div id="leavePeriodSection" style="display: none;">
            <label>休暇適用期間:</label>
            <input type="date" name="kintaiKaishi" id="kintaiKaishi" />
            ~ 
            <input type="date" name="kintaiShuuryou" id="kintaiShuuryou" />
            <br><br>
        </div>

        <!-- 기간 및 휴가일수 현황 버튼 -->
        <label class="date-input-container" onclick="focusInput('kintaiKaishi')">
            期間: <input type="date" name="kintaiKaishi" id="kintaiKaishi" required />
        </label>
        ~ 
        <label class="date-input-container" onclick="focusInput('kintaiShuuryou')">
            <input type="date" name="kintaiShuuryou" id="kintaiShuuryou" required />
        </label>
        <!-- 기간 옆에 휴가일수 현황 버튼 배치 -->
        <button type="button" class="btn">休暇日数現況</button>
        <br><br>

        <!-- 근태일수 (연차, 반차, 포상휴가 또는 청원휴가 선택 시만 표시) -->
        <div id="workDaysLabel" style="display: none;">
            <label>勤怠日数:</label>
            <input type="text" name="kintaiNissuu" placeholder="勤怠日数" />
            <br><br>
        </div>

        <!-- 근태시간 (지각, 조퇴, 외근, 휴일근무, 연장근무, 야간근무 선택 시만 표시) -->
        <div id="workHoursLabel" style="display: none;">
            <label>勤怠時間:</label>
            <input type="text" name="kintaiJikan" placeholder="勤怠時間" />
            <br><br>
        </div>

        <!-- 금액(수당) -->
        <label>金額(手当):</label>
        <input type="text" name="teate" id="teate" />
        <br><br>

        <!-- 적요 -->
        <label>摘要:</label>
        <input type="text" name="tekiyou" id="tekiyou" />
        <br><br>

        <!-- 저장 버튼 -->
        <button type="submit" class="btn">保存</button>
        <button type="reset" class="btn" style="background-color: #ccc;">内容削除</button>
    </form>
</div>

<!-- 모달 -->
<div id="kintaiModal" class="modal">
    <div class="modal-content">
        <span class="close" onclick="closeModal()">&times;</span>
        <h2>社員別勤怠記録</h2>
        <div id="kintaiData">
            <!-- 사원 근태 기록이 여기에 표시됩니다. -->
        </div>
    </div>
</div>

<script>
	//모달 열기 함수 (モーダルを開く関数)
	function openKintaiForm(shainId) {
    	document.getElementById("kintaiModal").style.display = "flex";
		const url = "/kyuyoKanri/shainKintaiKiroku.do?shainId=" + shainId;
    // AJAX 요청을 보내서 특정 사원의 근태 기록 가져오기 (AJAXリクエストで特定社員の勤怠記録を取得)
    fetch(url)
        .then(response => response.text())
        .then(data => {
            document.getElementById("kintaiData").innerHTML = data;
        })
        .catch(error => {
            console.error("Error fetching data:", error);
            document.getElementById("kintaiData").innerHTML = "データの読み込み中にエラーが発生しました。";
        });
	}

	// 모달 닫기 함수 (モーダルを閉じる関数)
	function closeModal() {
    	document.getElementById("kintaiModal").style.display = "none";
	}
</script>
<script src="${pageContext.request.contextPath}/js/KintaiKirokuKanri.js"></script>


</body>
</html>
