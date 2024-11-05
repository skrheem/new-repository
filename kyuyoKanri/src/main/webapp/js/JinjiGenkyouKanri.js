    // JavaScript 함수는 추후 구현
    function toggleAll(source) {
        const checkboxes = document.querySelectorAll('input[name="employeeCheckbox"]');
        checkboxes.forEach(checkbox => checkbox.checked = source.checked);
    }
 	// 팝업 열기
    function openPopup() {
        document.getElementById("popupOverlay").style.display = "flex";
    }

    // 팝업 닫기
    function closePopup() {
        document.getElementById("popupOverlay").style.display = "none";
    }

 // 표시항목 설정 저장 버튼 클릭 시 실행되는 함수
    function applySettings() {
        // 모든 체크박스에 대해 반복
        const checkboxes = document.querySelectorAll(".column-toggle");
        checkboxes.forEach(checkbox => {
            const columnClass = checkbox.getAttribute("data-column");
            const cells = document.querySelectorAll("." + columnClass);

            // 체크된 상태인지 확인하여 표시/숨김 설정
            if (checkbox.checked) {
                cells.forEach(cell => cell.style.display = "");
            } else {
                cells.forEach(cell => cell.style.display = "none");
            }
        });
        
        // 안내 메시지 표시
        alert("保存されました。");

        // 팝업 닫기
        closePopup();
    }
 	// 팝업 외부 클릭 시 닫기
    window.onclick = function(event) {
        const popupOverlay = document.getElementById("popupOverlay");
        if (event.target == popupOverlay) {
            closePopup();
        }
    };
    
    document.querySelectorAll('.column-toggle').forEach(checkbox => {
        checkbox.addEventListener('click', (event) => {
            const column = checkbox.getAttribute('data-column');
            
            // 고정된 항목에 대해 클릭 시 경고 메시지를 띄우고 상태를 원래대로 유지
            if (column === 'kubun' || column === 'nyuusha_nengappi' || column === 'namae_kana') {
                alert("区分、入社日、名前（カナ）は固定です。");
                
                // 고정 항목의 상태를 강제로 원래대로 복구
                checkbox.checked = !checkbox.checked;
            }
        });
    });
    
    function searchEmployees() {
        const condition = document.getElementById("searchCondition").value;
        const searchTerm = document.getElementById("searchInput").value.trim().toLowerCase();
        const rows = document.querySelectorAll(".scrollable-table tbody tr");

        rows.forEach(row => {
            let match = false;

            // 조건에 따라 비교할 셀을 선택
            switch (condition) {
                case "名前":
                    match = row.querySelector(".namae_kana").textContent.toLowerCase().includes(searchTerm) || 
                            row.querySelector(".namae_eigo").textContent.toLowerCase().includes(searchTerm);
                    break;
                case "部署":
                    match = row.querySelector(".busho_mei").textContent.toLowerCase().includes(searchTerm);
                    break;
                case "役職":
                    match = row.querySelector(".yakushoku_mei").textContent.toLowerCase().includes(searchTerm);
                    break;
                case "社員番号":
                    match = row.querySelector(".shain_id").textContent.toLowerCase().includes(searchTerm);
                    break;
                default:
                    // 全体の場合 모든 열에서 검색
                    match = row.innerText.toLowerCase().includes(searchTerm);
                    break;
            }

            // 검색어와 일치하지 않으면 해당 행 숨기기
            row.style.display = match ? "" : "none";
        });
    }

    function viewAll() {
        location.reload();
    }
    
    function filterByKubun() {
        const selectedKubun = document.getElementById("kubunFilter").value;
        const rows = document.querySelectorAll(".scrollable-table tbody tr");

        rows.forEach(row => {
            const kubunCell = row.querySelector(".kubun");
            if (selectedKubun === "" || kubunCell.textContent.trim() === selectedKubun) {
                row.style.display = "";  // 보여줌
            } else {
                row.style.display = "none";  // 숨김
            }
        });
    }

    function clearFilter() {
        document.getElementById("kubunFilter").value = "";
        filterByKubun();  // 필터 초기화
    }
    
    function filterByjyoutai() {
        const selectedjyoutai = document.getElementById("jyoutaiFilter").value;
        const rows = document.querySelectorAll(".scrollable-table tbody tr");

        rows.forEach(row => {
            const jyoutaiCell = row.querySelector(".jyoutai");
            if (selectedjyoutai === "" || jyoutaiCell.textContent.trim() === selectedjyoutai) {
                row.style.display = "";  // 보여줌
            } else {
                row.style.display = "none";  // 숨김
            }
        });
    }

    function clearFilter() {
        document.getElementById("jyoutaiFilter").value = "";
        filterByjyoutai();  // 필터 초기화
    }


    let currentPage = 1;
    let rowsPerPage = parseInt(document.getElementById("rowsPerPage").value);

    function updateRowsPerPage() {
        rowsPerPage = parseInt(document.getElementById("rowsPerPage").value);
        currentPage = 1; // 페이지를 처음으로 초기화
        displayTableRows();
    }

    function displayTableRows() {
        const rows = document.querySelectorAll(".scrollable-table tbody tr");
        const totalRows = rows.length;
        const totalPages = Math.ceil(totalRows / rowsPerPage);

        // 모든 행 숨기기
        rows.forEach(row => row.style.display = "none");

        // 현재 페이지의 행만 표시
        const start = (currentPage - 1) * rowsPerPage;
        const end = start + rowsPerPage;
        for (let i = start; i < end && i < totalRows; i++) {
            rows[i].style.display = "";
        }

        // 현재 페이지와 총 페이지 수를 표시
        document.getElementById("currentPageDisplay").textContent = `ページ ${currentPage} / ${totalPages}`;

        // 다음/이전 페이지 버튼 활성화/비활성화 설정
        document.querySelector("[onclick='goToNextPage()']").disabled = currentPage >= totalPages;
        document.querySelector("[onclick='goToPreviousPage()']").disabled = currentPage <= 1;
    }

    function goToNextPage() {
        const rows = document.querySelectorAll(".scrollable-table tbody tr");
        const totalPages = Math.ceil(rows.length / rowsPerPage);
        if (currentPage < totalPages) {
            currentPage++;
            displayTableRows();
        }
    }

    function goToPreviousPage() {
        if (currentPage > 1) {
            currentPage--;
            displayTableRows();
        }
    }

    // 페이지 로드 시 초기화
    window.onload = displayTableRows;
    


    // 선택사원 삭제
	function deleteSelected() {
    	
	console.log("삭제 버튼 클릭됨"); // 버튼이 눌렸는지 확인
    const checkboxes = document.querySelectorAll('input[name="employeeCheckbox"]:checked');
    if (checkboxes.length === 0) {
        alert("選択された社員がいません。");
        return;
    }

    const selectedIds = Array.from(checkboxes).map(checkbox => checkbox.value).join(',');
    const encodedSelectedIds = encodeURIComponent(selectedIds);
    
    console.log("선택된 체크박스:", checkboxes); 
    
    console.log("선택된 사원 ID:", selectedIds);
    
    // AJAX 요청으로 삭제 처리
    fetch('/kyuyoKanri/deleteShain.do', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: `shainIds=${encodeURIComponent(selectedIds)}`, // 인코딩 없이 전달
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("サーバーエラーが発生しました。");
        }
        return response.text();
    })
    .then(data => {
        if (data.includes("成功")) {
            alert("選択された社員が削除されました。");
            location.reload(); // 페이지 새로고침
        } else {
            alert("社員の削除に失敗しました。");
        }
    })
    .catch(error => {
        console.error("削除エラー:", error);
        alert("社員の削除に失敗しました。エラーが発生しました。");
    });
    location.reload();
}


        function printPage() {
            window.print();
        }

        function exportToExcel() {
            // 엑셀로 저장할 데이터를 <table>로 변환
            const content = document.getElementById('content').innerHTML;
            const table = `<table><tr><td>${content.replace(/\n/g, "</td></tr><tr><td>")}</td></tr></table>`;

            // 엑셀 워크북 생성
            const workbook = XLSX.utils.book_new();
            const worksheet = XLSX.utils.table_to_sheet(document.createElement('div').innerHTML = table);

            XLSX.utils.book_append_sheet(workbook, worksheet, 'Sheet1');

            // 엑셀 파일 다운로드
            XLSX.writeFile(workbook, '社員リスト.xlsx');
        }
  