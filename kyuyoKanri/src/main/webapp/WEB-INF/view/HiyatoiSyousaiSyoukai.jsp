<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- 최동주 崔東周 -->
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>日雇い詳細照会</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/HiyatoiSyousaiSyoukai.css">
</head>
<body>
	<h2>日雇い勤務照会</h2>

	<!-- 월별조회/상세조회 버튼 -->
	<div class="filter-section">
		<button type="button" onclick="location.href='hiyatoiGetsuSyoukai.do'">月別照会</button>
		<button type="button"
			onclick="location.href='hiyatoiSyousaiSyoukai.do'">詳細照会</button>
	</div>

	<div class="container">
		<!-- 필터링 섹션 -->
		<div class="filter-section">
			<!-- 필터링 폼 -->
			<form method="get" action="hiyatoiSyousaiSyoukai.do">


				<!-- 근무일자 필터 -->
				<div class="filter-item">
					<label for="kintaiKaishi">勤務日: </label> <input type="date"
						name="kintaiKaishi" id="kintaiKaishi" required /> <span>~</span>
					<input type="date" name="kintaiShuuryou" id="kintaiShuuryou"
						required />
				</div>
				<br>

				<!-- 이름 필터 -->
				<label for="shain_namae">名前: <input type="text"
					name="shain_namae" id="shain_namae" value="${param.shain_namae}"
					required />
				</label> <br>

				<!-- 부서 필터 -->
				<label>部署:</label> <select name="bushoMei" id="kintaiId"
					onchange="updateFormFields()" required>
					<option value="">選択</option>
					<option value="1">経営</option>
					<option value="2">開発</option>
					<option value="3">コンテンツ</option>
					<option value="4">業務支援</option>
					<option value="5">デザイン</option>
					<option value="6">管理</option>
					<option value="7">企画戦略</option>
				</select> <br>

				<!-- 프로젝트 필터 -->
				<label>現場・プロジェクト</label> <select name="project" id="project">
					<option value="">選択</option>
					<option value="1">現場1</option>
					<option value="2">現場2</option>
					<option value="3">第一工場</option>
					<option value="4">第二工場</option>
					<option value="5">研究所</option>
				</select> <br>

				<!-- 검색 및 전체보기 버튼 -->
				<div class="button-group">
					<button type="submit">検索</button>
					<button type="button">全体表示</button>
				</div>


			</form>
		</div>

		<!-- 테이블 섹션 -->
		<div class="table-section">
			<table>
				<thead id="cell">
					<tr>
						<th>勤務日</th>
						<th>社員番号</th>
						<th>名前</th>
						<th>部署</th>
						<th>現場・プロジェクト</th>
						<th>手当</th>
						<th>支給率</th>
						<th>所得税</th>
						<th>地方税</th>
						<th>実際支給値</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="record" items="${hiyatoiKirokuList}">
						<tr>
							<td><fmt:formatDate value="${record.kinmu_nengappi}"
									pattern="yyyy-MM-dd" /></td>
							<td>No-${record.shain_id}</td>
							<td>${record.namae_kana}</td>
							<td>${record.busho_mei}</td>
							<td>${record.genba_project_mei}</td>
							<td><fmt:formatNumber value="${record.teate}" /></td>
							<td><fmt:formatNumber value="${record.shiharai_ritsu}"
									minFractionDigits="2" maxFractionDigits="2" /></td>
							<td><fmt:formatNumber value="${record.shotokuzei}" /></td>
							<td><fmt:formatNumber value="${record.chihozei}" /></td>
							<td><fmt:formatNumber value="${record.jissai_shikyuu}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<div style="margin-top: 20px; text-align: center;">
		<button type="button" onclick="window.print()">印刷</button>
		<button type="button" onclick="downloadExcel()">エクセルダウンロード</button>
	</div>
</body>
</html>
