<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!-- 최동주 崔東周 -->
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>日雇い月別照会</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/HiyatoiGetsuSyoukai.css">
</head>
<body>
    <h2>日雇い勤務照会</h2>

	<!-- 월별조회/상세조회 버튼 -->
    <div class="filter-section">
        <button type="button" onclick="location.href='hiyatoiGetsuSyoukai.do'">月別照会</button>
        <button type="button" onclick="location.href='hiyatoiSyousaiSyoukai.do'">詳細照会</button>
    </div>

    <!-- 필터링 폼 -->
    <form method="get" action="hiyatoiGetsuSyoukai.do">
        <label>年度:
            <select name="year">
            	<option value="">全体</option> <!-- 전체 선택 옵션 추가 -->
                <c:forEach begin="2013" end="2025" var="yr">
                    <option value="${yr}" ${yr == param.year ? 'selected' : ''}>${yr}</option>
                </c:forEach>
            </select>
        </label>
        <label>月:
            <select name="month">
            	<option value="">全体</option> <!-- 전체 선택 옵션 추가 -->
                <c:forEach begin="1" end="12" var="mn">
                    <option value="${mn}" ${mn == param.month ? 'selected' : ''}>${mn}月</option>
                </c:forEach>
            </select>
        </label>
        <label>部署:
            <select name="busho">
                <option value="">全体</option>
                <option value="経営" ${param.busho == '経営' ? 'selected' : ''}>経営</option>
                <option value="開発" ${param.busho == '開発' ? 'selected' : ''}>開発</option>
                <option value="コンテンツ" ${param.busho == 'コンテンツ' ? 'selected' : ''}>コンテンツ</option>
                <option value="業務支援" ${param.busho == '業務支援' ? 'selected' : ''}>業務支援</option>
                <option value="デザイン" ${param.busho == 'デザイン' ? 'selected' : ''}>デザイン</option>
                <option value="管理" ${param.busho == '管理' ? 'selected' : ''}>管理</option>
                <option value="企画戦略" ${param.busho == '企画戦略' ? 'selected' : ''}>企画戦略</option>
            </select>
        </label>
        <label>役職:
            <select name="yakushoku">
                <option value="">全体</option>
                <option value="理事" ${param.yakushoku == '理事' ? 'selected' : ''}>理事</option>
                <option value="次長" ${param.yakushoku == '次長' ? 'selected' : ''}>次長</option>
                <option value="社長" ${param.yakushoku == '社長' ? 'selected' : ''}>社長</option>
                <option value="部長" ${param.yakushoku == '部長' ? 'selected' : ''}>部長</option>
                <option value="課長" ${param.yakushoku == '課長' ? 'selected' : ''}>課長</option>
                <option value="代理" ${param.yakushoku == '代理' ? 'selected' : ''}>代理</option>
                <option value="主任" ${param.yakushoku == '主任' ? 'selected' : ''}>主任</option>
                <option value="社員" ${param.yakushoku == '社員' ? 'selected' : ''}>社員</option>
                <option value="室長" ${param.yakushoku == '室長' ? 'selected' : ''}>室長</option>
            </select>
        </label>
        <button type="submit">並べ替え基準設定</button>
    </form>

    <!-- 결과 테이블 -->
    <table>
        <thead>
            <tr>
                <th>区分</th>
                <th>社員番号</th>
                <th>名前</th>
                <th>部署</th>
                <th>役職</th>
                <th>勤務日数</th>
                <th>所得税</th>
                <th>地方税</th>
                <th>実際支給値</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="record" items="${hiyatoiList}">
                <tr>
                    <td>${record.kubun}</td>
                    <td>No-${record.shain_id}</td>
                    <td>${record.namae_kana}</td>
                    <td>${record.busho_mei}</td>
                    <td>${record.yakushoku_mei}</td>
                    <td>${record.total_days}</td>
                    <td><fmt:formatNumber value="${record.total_shotokuzei}" /></td>
                    <td><fmt:formatNumber value="${record.total_chihozei}" /></td>
                    <td><fmt:formatNumber value="${record.total_jissai_shikyuu}" /></td>
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
