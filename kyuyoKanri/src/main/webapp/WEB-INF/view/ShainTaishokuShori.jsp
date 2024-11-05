<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<!-- 김영민金榮珉 -->
<html>
<head>
<meta charset="UTF-8">
<title>社員退職処理</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/ShainTaishokuShori.css">
<script type="text/javascript">
    const pageSize = ${pageSize};
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/ShainTaishokuShori.js"></script>
</head>
<body>
	<header>
		<h2>社員退職処理</h2>
		<h4>退職対象の社員を選択し、退職処理に応じた情報を入力できます。退職社員として分類し、すべての退職社員を確認することができます。</h4>
	</header>
	<main>
		<!-- 드롭다운 메뉴, 검색 입력 필드, 검색 버튼, 전체 보기 버튼 -->
		<!-- ドロップダウンメニュー、検索入力フィールド、検索ボタン、全体表示ボタン -->
		<div style="margin-bottom: 10px; display: flex; align-items: center;">
			<select id="searchField" style="margin-left: 5px;">
				<option value="shainId">社員番号</option>
				<option value="namae">名前</option>
				<option value="busho">部署</option>
			</select> 
			<input type="text" id="searchInput" placeholder="入力" style="margin-left: 5px;">
			<button onclick="filterBySearch()" class="button-search" style="margin-left: 5px;">検索</button>
			<button onclick="showAllRows()" class="button-search" style="margin-left: 5px;">全体</button>
		</div>
		<!-- 필터링 드롭다운 메뉴 -->
		<!-- フィルタリングドロップダウンメニュー -->
		<div style="margin-bottom: 10px;">
			<div class="custom-dropdown" style="float: right;">
				<button onclick="toggleDropdown()" class="dropbtn">状態選択</button>
				<div id="dropdownContent" class="dropdown-content">
					<a href="#" onclick="selectFilter('all')">全体</a> 
					<a href="#" onclick="selectFilter('在職')">在職</a> 
					<a href="#" onclick="selectFilter('退職')">退職</a>
				</div>
			</div>
		</div>
		<!-- 사원 퇴직처리 테이블 -->
		<!-- 社員退職処理テーブル -->
		<table id="shainTable" border="1" style="width: 100%;">
			<thead style="background-color: #F6FDFF;">
				<tr>
					<th>番号</th>
					<th>状態</th>
					<th>社員番号</th>
					<th>名前</th>
					<th>部署</th>
					<th>職位</th>
					<th>入社日</th>
					<th>退職日</th>
					<th>勤続年数</th>
					<th>中間精算</th>
					<th>退職精算</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="shainTaishoku" items="${shainTaishokuList}"
					varStatus="status">
					<tr onclick="openModal(${shainTaishoku.getShain_id()})">
						<td class="number">${status.index + 1}</td>
						<!-- 번호 설정 -->
						<!-- 番号設定 -->
						<td data-status="${shainTaishoku.getJyoutai()}">${shainTaishoku.getJyoutai()}</td>
						<td data-shainId="${shainTaishoku.getShain_id()}">${shainTaishoku.getShain_id()}</td>
						<td data-namae="${shainTaishoku.getNamae_kana()}">${shainTaishoku.getNamae_kana()}</td>
						<td data-busho="${shainTaishoku.getBusho_mei()}">${shainTaishoku.getBusho_mei()}</td>
						<td>${shainTaishoku.getYakushoku_mei()}</td>
						<td>${shainTaishoku.getNyuusha_nengappi()}</td>
						<td>${shainTaishoku.getTaisha_nengappi()}</td>
						<td>${shainTaishoku.getKinzokunensu()}年</td>
						<td>${shainTaishoku.getChuukanseisan()}</td>
						<td>${shainTaishoku.getTaishokuseisan()}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<!-- 테이블 아래에 페이징 UI 추가 -->
		<!-- テーブルの下にページネーションUIを追加 -->
		<div class="pagination">
			<button onclick="goToPage(currentPage - 1)" ${page == 1 ? 'disabled' : ''}>❮ 前に</button>
			<c:forEach var="i" begin="1" end="${totalPages}">
				<span class="page-number ${page == i ? 'selected' : ''}" onclick="goToPage(${i})">${i}</span>
			</c:forEach>
			<button onclick="goToPage(currentPage + 1)" ${page == totalPages ? 'disabled' : ''}>後ろに ❯</button>
		</div>
	</main>
	<!-- 퇴직처리 모달 -->
	<!-- 退職処理モーダル -->
	<div id="taishokuModal" class="modal">
		<div class="modal-content">
			<span class="close" onclick="closeModal()">&times;</span>
			<h3>
				退職処理 <span id="modalShainId"></span>
			</h3>
			<form action="taishokuInsert.do" method="post" style= "border: 1px solid #aaa; padding: 10px;">
				<input type="hidden" name="action" value="insert"> <input type="hidden" id="shainIdInput" name="shainId">
				<div class="form-group" style="display: flex; justify-content: space-between; align-items: center; border-top: 2px solid black; padding: 5px;">
        			<label for="taishokuKubun" style="flex: 1;">退職区分</label>
        			<select name="taishokuKubun" id="taishokuKubun" style="flex: 2; width: 100%;">
            			<option value="selected">選択</option>
            			<option value="定年退職">定年退職</option>
            			<option value="整理解雇">整理解雇</option>
           				<option value="自発的退職">自発的退職</option>
            			<option value="役員退職">役員退職</option>
            			<option value="中間精算">中間精算</option>
            			<option value="その他">その他</option>
        			</select>
    			</div>
				<div class="form-group" style="display: flex; justify-content: space-between; align-items: center; border-top: 1px solid #aaa; padding: 5px;">
					<label for="taishokuBi" style="flex: 1;">退職日</label> 
					<input type="date" name="taishokuBi" id="taishokuBi" style="flex: 2; width: 100%;">
				</div>
				<div class="form-group" style="display: flex; justify-content: space-between; align-items: center; border-top: 1px solid #aaa; padding: 5px;">
					<label for="riyuu" style="flex: 1;">退職理由</label> 
					<input type="text" name="riyuu" id="riyuu" style="flex: 2; width: 100%;">
				</div>
				<div class="form-group" style="display: flex; justify-content: space-between; align-items: center; border-top: 1px solid #aaa; border-bottom: 1px solid #aaa; padding: 5px;">
					<label for="taishokugoRenrakusaki" style="flex: 1;">退職後の連絡先</label>
					<input type="text" name="taishokugoRenrakusaki" id="taishokugoRenrakusaki" style="flex: 2; width: 100%; ">
				</div>
				<div style="text-align: center; margin-top: 10px;">
					<button type="submit" style="padding: 8px 16px; background-color: #4294F1; border: none; border-radius: 4px;">保存</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
