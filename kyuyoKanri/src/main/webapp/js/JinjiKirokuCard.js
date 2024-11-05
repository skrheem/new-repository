let allEmployees = []; // 전체 사원 데이터 저장

// 페이지 로드 시 전체 데이터를 가져오기
document.addEventListener("DOMContentLoaded", () => {
    fetch('/kyuyoKanri/getAllShainList.do')
    .then(response => response.json())
    .then(data => {
        allEmployees = data; // 전체 데이터 저장
        displayEmployees(allEmployees); // 처음에는 전체 데이터 표시
    })
    .catch(error => console.error("데이터 불러오기 실패:", error));
});

// 필터링 후 결과 표시
function filterEmployees() {
    const department = document.getElementById("departmentSelect").value;
    const position = document.getElementById("positionSelect").value;
    const searchTerm = document.getElementById("searchInput").value.toLowerCase();

    const filteredEmployees = allEmployees.filter(employee => {
        const matchesDepartment = department === "" || employee.busho_mei === department;
        const matchesPosition = position === "" || employee.yakushoku_mei === position;
        const matchesSearch = employee.name.toLowerCase().includes(searchTerm) || 
                              employee.shain_id.toString().includes(searchTerm);
        
        return matchesDepartment && matchesPosition && matchesSearch;
    });

    displayEmployees(filteredEmployees);
}

// 직원 목록을 테이블에 표시하는 함수
function displayEmployees(employeeList) {
    const employeeTableBody = document.getElementById("employeeList");
    employeeTableBody.innerHTML = ""; // 기존 내용 초기화

    employeeList.forEach(employee => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${employee.kubun}</td>
            <td>${employee.shain_id}</td>
            <td>${employee.name}</td>
            <td>${employee.busho_mei}</td>
            <td>${employee.yakushoku_mei}</td>
            <td>${employee.jyoutai}</td>
        `;
        employeeTableBody.appendChild(row);
    });
}
// 필터링 후 팝업 열기
function openPopupWithFilteredResults() {
    filterEmployees(); // 필터링된 결과 표시
    openManagePopup(); // 팝업창 열기
}

// 팝업 열기 함수
function openManagePopup() {
    document.getElementById('managePopupOverlay').style.display = 'flex';
}

// 팝업 닫기 함수
function closeManagePopup() {
    document.getElementById('managePopupOverlay').style.display = 'none';
}



function viewAll() {
	// 전체 보기 기능 구현
	alert("전체 보기 기능이 실행되었습니다."); // 디버깅용 알림
}


function printPage() {
	window.print();
}



function downloadExcel() {
	// 엑셀 다운로드 기능 구현
	alert("엑셀 다운로드 기능이 실행되었습니다."); // 디버깅용 알림
}

/*사원선택 팝업*/
let selectedShainId = null;

function openManagePopup() {
	const overlay = document.getElementById('managePopupOverlay');
	if (overlay) {
		overlay.style.display = 'flex';
		loadEmployeeList(); // 전체 사원 목록을 불러옵니다
	}
}

function closeManagePopup() {
	const overlay = document.getElementById('managePopupOverlay');
	if (overlay) {
		overlay.style.display = 'none';
	}
}

function loadEmployeeList() {
	fetch('/kyuyoKanri/cardShainChoose.do')
		.then(response => response.json())
		.then(data => {
			const employeeList = document.getElementById("employeeList");
			employeeList.innerHTML = ''; // 기존 목록 초기화

			data.forEach(employee => {
				const row = document.createElement("tr");

				// 각 필드에 맞는 열을 추가
				row.innerHTML = `
                    <td>${employee.name}</td>
                    <td>No-${employee.shain_id}</td>
                    <td>${employee.kubun}</td>
                    <td>${employee.busho_mei}</td>
                    <td>${employee.yakushoku_mei}</td>
                    <td>${employee.jyoutai}</td>
                `;

				// 클릭 이벤트: 선택된 사원의 ID 저장
				row.onclick = function() {
					selectedShainId = employee.shain_id;
					document.querySelectorAll('#employeeList tr').forEach(item => item.classList.remove('selected'));
					row.classList.add('selected');
				};

				// 마우스오버 효과
				row.onmouseover = function() {
					if (!row.classList.contains('selected')) {
						row.style.backgroundColor = "#E0FFFF";
					}
				};
				row.onmouseout = function() {
					if (!row.classList.contains('selected')) {
						row.style.backgroundColor = "";
					}
				};

				employeeList.appendChild(row);
			});
		})
		.catch(error => console.error("사원 목록을 불러오는 중 오류 발생:", error));
}

// 선택 버튼 클릭 시: 선택된 사원의 ID가 있다면 핸들러로 이동
function confirmSelection() {
	console.log("Selected shain_id before redirect: " + selectedShainId);
	if (selectedShainId) {
		window.location.href = `/kyuyoKanri/jinjiKirokuCard.do?shain_id=${selectedShainId}`;
	} else {
		alert("사원을 선택해 주세요.");
	}
	closeManagePopup();
}

function exportToExcel(shainId) {
	const content = document.getElementById('content').innerHTML;
	const table = `<table><tr><td>${content.replace(/\n/g, "</td></tr><tr><td>")}</td></tr></table>`;

	// 엑셀 워크북 생성
	const workbook = XLSX.utils.book_new();
	const worksheet = XLSX.utils.table_to_sheet(document.createElement('div').innerHTML = table);
	XLSX.utils.book_append_sheet(workbook, worksheet, 'Sheet1');

	// shain_id를 파일 이름에 포함하여 저장
	XLSX.writeFile(workbook, `人事記録カード_${shainId}.xlsx`);
}

function toggleRepresentative() {
	const checkBox = document.getElementById("showRepresentative");
	const representativeName = document
		.getElementById("representativeName");
	if (checkBox.checked) {
		representativeName.style.display = "inline";
	} else {
		representativeName.style.display = "none";
	}
}



