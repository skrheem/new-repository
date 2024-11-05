/**
 * 
 */
 
 // JSP에서 contextPath를 JavaScript로 전달 (JSPからcontextPathをJavaScriptに渡す)
    var contextPath = "${pageContext.request.contextPath}";

    // 전체 선택/해제 기능 (全体選択/解除機能)
    function selectAllShain(selectAll) {
        const checkboxes = document.querySelectorAll('.shainCheckbox');
        checkboxes.forEach((checkbox) => {
            checkbox.checked = selectAll.checked;
        });
    }
 
    // Tip 버튼을 클릭했을 때 도움말을 표시하는 함수 (Tipボタンをクリックした際にヘルプを表示する関数)
    function showTip() {
        alert("勤怠記録の管理に関するヘルプです。");
    }

    
    // 선택한 사원이 있는지 확인하는 함수 (選択された社員がいるか確認する関数)
    function validateSelectedShain() {
        const selectedShain = document.querySelectorAll('.shainCheckbox:checked');
        if (selectedShain.length === 0) {
            alert("社員を選択ください。");
            return false;
        }
        return true;
    }

    // 금액 필드의 숫자 유효성 검증 함수 (金額フィールドの数値バリデーション関数)
    function validateTeate() {
        const teateValue = document.getElementById("teate").value;
        if (teateValue && isNaN(teateValue)) {
            alert("金額（手当）は数字で入力してください。");
            return false;
        }
        return true;
    }

    // 휴가일수 현황 모달 표시 함수 (休暇日数の状況モーダルを表示する関数)
    function showLeaveStatus() {
        const selectedShain = document.querySelectorAll('.shainCheckbox:checked');
        if (selectedShain.length === 0) {
            alert("社員を選択ください。");
            return;
        }

        const shainId = selectedShain[0].value; // 선택된 첫 번째 사원의 ID 가져오기 (選択された最初の社員IDを取得)
        fetch(`${contextPath}/getLeaveStatus.do?shainId=${shainId}`)
            .then(response => response.text())
            .then(data => {
                document.getElementById("kintaiData").innerHTML = data; // 휴가 현황을 모달에 표시 (休暇状況をモーダルに表示)
                document.getElementById("kintaiModal").style.display = "flex"; // 모달 열기 (モーダルを開く)
            })
            .catch(error => {
                console.error("Error fetching leave status:", error);
            });
    }

    // 근태항목에 따라 폼을 업데이트하는 함수 (勤怠項目に基づいてフォームを更新する関数)
    function updateFormFields() {
        var kintaiId = document.getElementById("kintaiId").value;
        var leavePeriodSection = document.getElementById("leavePeriodSection");
        var workDaysLabel = document.getElementById("workDaysLabel");
        var workHoursLabel = document.getElementById("workHoursLabel");

        // 연차, 반차, 포상휴가 선택 시 (年次休暇、半休、褒賞休暇が選択された場合)
        if (kintaiId === "1" || kintaiId === "2" || kintaiId === "8") {
            leavePeriodSection.style.display = "block"; // 휴가 적용 기간 표시 (休暇期間を表示)
            workDaysLabel.style.display = "block"; // 근태일수 표시 (勤怠日数を表示)
            workHoursLabel.style.display = "none";  // 근태시간 숨김 (勤怠時間を非表示)
        }
        // 지각, 조퇴, 외근, 휴일근무, 연장근무, 야간근무 선택 시 (遅刻、早退、外勤、休日出勤、延長勤務、夜勤が選択された場合)
        else if (kintaiId === "3" || kintaiId === "4" || kintaiId === "5" || kintaiId === "6" || kintaiId === "7" || kintaiId === "9") {
            leavePeriodSection.style.display = "none";  // 휴가 적용 기간 숨김 (休暇期間を非表示)
            workDaysLabel.style.display = "none";  // 근태일수 숨김 (勤怠日数を非表示)
            workHoursLabel.style.display = "block"; // 근태시간 표시 (勤怠時間を表示)
        }
        // 청원휴가 선택 시 (請願休暇が選択された場合)
        else if (kintaiId === "10") {
            leavePeriodSection.style.display = "none";  // 휴가 적용 기간 숨김 (休暇期間を非表示)
            workDaysLabel.style.display = "block"; // 근태일수 표시 (勤怠日数を表示)
            workHoursLabel.style.display = "none";  // 근태시간 숨김 (勤怠時間を非表示)
        } else {
            leavePeriodSection.style.display = "none"; // 휴가 기간 숨김 (休暇期間を非表示)
            workDaysLabel.style.display = "none"; // 근태일수 숨김 (勤怠日数を非表示)
            workHoursLabel.style.display = "none"; // 근태시간 숨김 (勤怠時間を非表示)
        }
    }