<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>社員現況・管理</title>
<link rel="stylesheet"
	href="<c:url value='/css/JinjiGenkyouKanri.css' />">
	
</head>
<body>

	<div class="header-text">
		社員現況・管理
		<div class="header-text2">
			全社員の現況を見ることができ、選択すると該当社員登録(情報)ページに移動します。</div>
	</div>

	<!-- 상단 통계 표시 -->
	<div class="flex-container">
		<div class="label-box selected">
			<div>在職者</div>
			<div>${jyoutaiCounts[0]}</div>
			<!-- 유동적인 값 -->
		</div>
		<div class="label-box default-blue">
			<div>正社員</div>
			<div>${kubunCounts[0]}</div>
			<!-- 정규직 값 -->
		</div>
		<div class="label-box default-blue">
			<div>契約職</div>
			<div>${kubunCounts[1]}</div>
			<!-- 계약직 값 -->
		</div>
		<div class="label-box default-blue">
			<div>臨時職</div>
			<div>${kubunCounts[2]}</div>
			<!-- 임시직 값 -->
		</div>
		<div class="label-box default-blue">
			<div>派遣職</div>
			<div>${kubunCounts[3]}</div>
			<!-- 파견직 값 -->
		</div>
		<div class="label-box default-blue">
			<div>委嘱職</div>
			<div>${kubunCounts[4]}</div>
			<!-- 위촉직 값 -->
		</div>
		<div class="label-box default-blue">
			<div>日雇い</div>
			<div>${kubunCounts[5]}</div>
			<!-- 일용직 값 -->
		</div>

		<div class="label-box default-gray">
			<div>退社者</div>
			<div>${jyoutaiCounts[1]}</div>
			<!-- 유동적인 값 -->
		</div>
		<div class="label-box" style="background-color: #333; color: #fff;">
			<div>全体</div>
			<div>${totalShainCount}</div>
			<!-- 유동적인 값 -->
		</div>
	</div>

	<!-- 검색 및 필터 옵션 -->
	<div class="flex-container2">
		<div>
			<select id="searchCondition">
				<option value="">全体</option>
				<option value="名前">名前</option>
				<option value="部署">部署</option>
				<option value="役職">役職</option>
				<option value="社員番号">社員番号</option>
			</select> <input type="text" id="searchInput" placeholder="検索語入力">
			<button onclick="searchEmployees()">検索</button>
			<button onclick="viewAll()">全体を見る</button>
		</div>
		<div>
			<!-- 区分 필터 드롭다운 -->
			<select id="kubunFilter" onchange="filterByKubun()">
				<option value="">全体</option>
				<option value="正規職">正規職</option>
				<option value="契約職">契約職</option>
				<option value="臨時職">臨時職</option>
				<option value="派遣職">派遣職</option>
				<option value="委嘱職">委嘱職</option>
				<option value="日雇い">日雇い</option>
			</select> <select id="jyoutaiFilter" onchange="filterByjyoutai()">
				<option value="">状態別</option>
				<option value="在職">在職</option>
				<option value="退職">退職</option>
			</select> <select id="rowsPerPage" onchange="updateRowsPerPage()">
				<option value="10">10個ずつ見る</option>
				<option value="30" selected>30個ずつ見る</option>
				<option value="50">50個ずつ見る</option>
				<option value="100">100個ずつ見る</option>
			</select>

			<button onclick="setSortCriteria()">ソート基準を設定する</button>
			<button onclick="openPopup()">表示項目設定</button>
		</div>
	</div>

	<!-- 팝업 오버레이 -->
	<div class="popup-overlay" id="popupOverlay">
		<div class="popup-content">
			<h2>表示項目設定</h2>
			<ul>
				<!-- 고정된 항목 -->
				<li><input type="checkbox" class="column-toggle"
					data-column="kubun" checked> 区分</li>
				<li><input type="checkbox" class="column-toggle"
					data-column="nyuusha_nengappi" checked> 入社日</li>
				<li><input type="checkbox" class="column-toggle"
					data-column="namae_kana" checked> 名前（カナ）</li>

				<!-- 선택 가능한 항목 -->
				<li><input type="checkbox" class="column-toggle"
					data-column="shain_id" checked> 社員番号</li>
				<li><input type="checkbox" class="column-toggle"
					data-column="namae_eigo" checked> 名前（英語）</li>
				<li><input type="checkbox" class="column-toggle"
					data-column="busho_mei" checked> 部署</li>
				<li><input type="checkbox" class="column-toggle"
					data-column="yakushoku_mei" checked> 役職</li>
				<li><input type="checkbox" class="column-toggle"
					data-column="jumin_bangou" checked> 住民番号</li>
				<li><input type="checkbox" class="column-toggle"
					data-column="kokuseki" checked> 内・外国人</li>
				<li><input type="checkbox" class="column-toggle"
					data-column="juusho" checked> 住所</li>
				<li><input type="checkbox" class="column-toggle"
					data-column="denwa_uchi" checked> 連絡先</li>
				<li><input type="checkbox" class="column-toggle"
					data-column="denwa_keitai" checked> 携帯番号</li>
				<li><input type="checkbox" class="column-toggle"
					data-column="meeru" checked> メール</li>
				<li><input type="checkbox" class="column-toggle"
					data-column="SNS" checked> SNS</li>
				<li><input type="checkbox" class="column-toggle"
					data-column="taisha_nengappi" checked> 退社日</li>
				<li><input type="checkbox" class="column-toggle"
					data-column="jyoutai" checked> 状態</li>
				<li><input type="checkbox" class="column-toggle"
					data-column="kouza_bangou" checked> 口座番号</li>
			</ul>
			<button onclick="applySettings()">表示項目を保存</button>
		</div>
	</div>

	<div id="content">
		<!-- 사원 리스트 테이블 -->
		<div class="scrollable-container">
			<div class="scrollable-table">
				<table>
					<thead>
						<tr>
							<th><input type="checkbox" onclick="toggleAll(this)"></th>
							<th class="kubun">区分</th>
							<th class="nyuusha_nengappi">入社日</th>
							<th class="shain_id">社員番号</th>
							<th class="namae_kana">名前（カナ）</th>
							<th class="namae_eigo">名前（英語）</th>
							<th class="busho_mei">部署</th>
							<th class="yakushoku_mei">役職</th>
							<th class="jumin_bangou">住民番号</th>
							<th class="kokuseki">内・外国人</th>
							<th class="juusho">住所</th>
							<th class="denwa_uchi">連絡先</th>
							<th class="denwa_keitai">携帯番号</th>
							<th class="meeru">メール</th>
							<th class="SNS">SNS</th>
							<th class="taisha_nengappi">退社日</th>
							<th class="jyoutai">状態</th>
							<th class="kouza_bangou">口座番号</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="employee" items="${shainList}">
							<tr data-shain-id="${employee.shain_id}">
								<td><input type="checkbox" name="employeeCheckbox"
									value="${employee.shain_id}"></td>
								<td class="kubun">${employee.kubun}</td>
								<td class="nyuusha_nengappi">${employee.nyuusha_nengappi}</td>
								<td class="shain_id">${employee.shain_id}</td>
								<td class="namae_kana">${employee.namae_kana}</td>
								<td class="namae_eigo">${employee.namae_eigo}</td>
								<td class="busho_mei">${employee.busho_mei}</td>
								<td class="yakushoku_mei">${employee.yakushoku_mei}</td>
								<td class="jumin_bangou">${employee.jumin_bangou}</td>
								<td class="kokuseki">${employee.kokuseki}</td>
								<td class="juusho">${employee.juusho}</td>
								<td class="denwa_uchi">${employee.denwa_uchi}</td>
								<td class="denwa_keitai">${employee.denwa_keitai}</td>
								<td class="meeru">${employee.meeru}</td>
								<td class="SNS">${employee.SNS}</td>
								<td class="taisha_nengappi">${employee.taisha_nengappi}</td>
								<td class="jyoutai">${employee.jyoutai}</td>
								<td class="kouza_bangou">${employee.kouza_bangou}</td>
							</tr>
						</c:forEach>
					</tbody>

				</table>
			</div>
		</div>
	</div>


	<!-- 페이징 및 버튼 -->
	<div class="page-container">
		<div>
			<button onclick="goToPreviousPage()">前のページ</button>
			<span id="currentPageDisplay"></span>
			<button onclick="goToNextPage()">次ページ</button>
		</div>
	</div>
	<div class="setting-container">
		<div>
			<button onclick="registerNewEmployee()">新規社員登録</button>
			<button onclick="registerEmployeeBatch()">新規社員一括登録</button>
			<button onclick="deleteSelected()">選択削除</button>
			<button onclick="exportToExcel()">エクセル·ダウンロード</button>
		</div>
	</div>


	<script>
    const contextPath = "<%=request.getContextPath()%>
		";
	</script>
	<script
		src="${pageContext.request.contextPath}/js/JinjiGenkyouKanri.js">
		</script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.17.0/xlsx.full.min.js"></script>



</body>
</html>
