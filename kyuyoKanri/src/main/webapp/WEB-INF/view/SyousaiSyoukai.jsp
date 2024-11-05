<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!-- 최동주 崔東周 -->
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>詳細勤怠照会</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/SyousaiSyoukai.css">
</head>
<body>
    <h2>勤怠照会</h2>
    
    <!-- 월별조회/상세조회 버튼 -->
    <div class="filter-section">
        <button type="button" onclick="location.href='getsuSyoukai.do'">月別照会</button>
        <button type="button" onclick="location.href='syousaiSyoukai.do'">詳細照会</button>
    </div>

    <!-- 필터링 폼 -->
    <form method="get" action="syousaiSyoukai.do">
        <label>入力日:
            <input type="date" name="kintaiKaishi" value="${param.kintaiKaishi}" required />
            ~
            <input type="date" name="kintaiShuuryou" value="${param.kintaiShuuryou}" required />
        </label>
        <button type="submit">検索</button>
        <button type="button" onclick="location.href='syousaiSyoukai.do'">全体表示</button>
    </form>

    <table>
        <thead>
            <tr>
                <th>入力日</th>
                <th>区分</th>
                <th>名前</th>
                <th>部署</th>
                <th>役職</th>
                <th>勤怠項目</th>
                <th>勤怠期間</th>
                <th>勤怠日数・時間</th>
                <th>金額</th>
                <th>摘要</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="kintai" items="${kintaiRecords}">
                <tr>
                    <td><fmt:formatDate value="${kintai.nyuuryoku_bi}" pattern="yyyy-MM-dd" /></td>
                    <td>${kintai.kubun}</td>
                    <td>${kintai.namae_kana}</td>
                    <td>${kintai.busho_mei}</td>
                    <td>${kintai.yakushoku_mei}</td>
                    <td>${kintai.kintai_mei}</td>
                    <td>${kintai.kintai_kaishi}</td>
                    <td>${kintai.kintai_nissuu}</td>
                    <td>${kintai.teate}</td>
                    <td>${kintai.tekiyou}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
    <div style="margin-top:20px; text-align: center;">
    <button type="button" onclick="window.print()">印刷</button>
    <button type="button" onclick="downloadExcel()">エクセルダウンロード</button>
</div>

</body>
</html>
