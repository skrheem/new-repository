// 검색 필터링 함수  検索フィルタリング関数
function filterBySearch() {
	const searchField = document.getElementById("searchField").value;
	const searchValue = document.getElementById("searchInput").value.trim().toLowerCase();

	const rows = document.querySelectorAll("#shainTable tbody tr");

	rows.forEach(row => {
		let cellValue = "";
		if (searchField === "shainId") {
			cellValue = row.querySelector("td[data-shainId]").textContent.trim().toLowerCase();
		} else if (searchField === "namae") {
			cellValue = row.querySelector("td[data-namae]").textContent.trim().toLowerCase();
		} else if (searchField === "busho") {
			cellValue = row.querySelector("td[data-busho]").textContent.trim().toLowerCase();
		}
		if (cellValue.includes(searchValue)) {
			row.style.display = "";
		} else {
			row.style.display = "none";
		}
	});
}
// 전체 행을 표시하는 함수  全ての行を表示する関数
function showAllRows() {
	const rows = document.querySelectorAll("#shainTable tbody tr");

	rows.forEach(row => {
		row.style.display = "";
	});
	document.getElementById("searchInput").value = "";
}

// 자바스크립트 필터링 함수  JavaScriptフィルタリング関数
function toggleDropdown() {
	document.getElementById("dropdownContent").classList.toggle("show");
}
// 드롭다운 외부 클릭 시 드롭다운 닫기  ドロップダウンの外部をクリックした際にドロップダウンを閉じる
window.onclick = function(event) {
	if (!event.target.matches('.dropbtn')) {
		var dropdowns = document.getElementsByClassName("dropdown-content");
		for (var i = 0; i < dropdowns.length; i++) {
			var openDropdown = dropdowns[i];
			if (openDropdown.classList.contains('show')) {
				openDropdown.classList.remove('show');
			}
		}
	}
}
// 테이블 필터링 기능  テーブルフィルタリング機能
function filterTable(selectedStatus) {
	var rows = document.querySelectorAll("#shainTable tbody tr");
	let index = 1;
	rows.forEach(row => {
		var status = row.querySelector("td[data-status]").getAttribute("data-status");
		if (selectedStatus === "all" || status === selectedStatus) {
			row.style.display = "";
			row.querySelector("td.number").textContent = index++;
		} else {
			row.style.display = "none";
		}
	});
}
function selectFilter(status) {
	filterTable(status);
	toggleDropdown();
}
// 페이지 이동 함수
// ページ移動関数
function goToPage(page) {
	const url = `?page=${page}&pageSize=${pageSize}`;
	window.location.href = url;
}
// 모달 열기 함수
// モーダルを開く関数
function openModal(shainId) {
	document.getElementById("shainIdInput").value = shainId;
	document.getElementById("modalShainId").textContent = shainId;
	document.getElementById("taishokuModal").style.display = "block";
}
// 모달 닫기 함수
// モーダルを閉じる関数
function closeModal() {
	document.getElementById("taishokuModal").style.display = "none";
}
// 모달 외부 클릭 시 닫기
// モーダル外をクリックして閉じる
window.onclick = function(event) {
	const modal = document.getElementById("taishokuModal");
	if (event.target === modal) {
		closeModal();
	}
}
// 테이블 행 클릭 시 모달 열기 설정
// テーブル行クリックでモーダルを開く設定
document.addEventListener("DOMContentLoaded", function() {
	var rows = document.querySelectorAll("#shainTable tbody tr");
	rows.forEach(row => {
		row.addEventListener("click", function() {
			var shainId = row.querySelector("td[data-shainId]").textContent.trim();
			openModal(shainId); // 모달 열기 함수에 사원 ID 전달  モーダルを開く関数に社員IDを渡す
		});
	});
});
