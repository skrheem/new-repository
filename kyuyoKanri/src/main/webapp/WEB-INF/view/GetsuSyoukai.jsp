<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!-- 최동주 崔東周 -->
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>月別勤怠照会</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/GetsuSyoukai.css">
</head>
<body>
    <h2>勤怠照会</h2>

    <!-- 월별조회/상세조회 버튼 -->
    <div class="filter-section">
        <button type="button" onclick="location.href='getsuSyoukai.do'">月別照会</button>
        <button type="button" onclick="location.href='syousaiSyoukai.do'">詳細照会</button>
    </div>

    <!-- 필터링 폼 -->
    <form method="get" action="getsuSyoukai.do">
        <label>年度:
            <select name="year">
                <option value="">全体</option> <!-- 전체 선택 옵션 추가 -->
                <c:forEach var="y" begin="2013" end="2025">
                    <option value="${y}" ${param.year == y ? 'selected' : ''}>${y}</option>
                </c:forEach>
            </select>
        </label>
        <label>月:
            <select name="month">
                <option value="">全体</option> <!-- 전체 선택 옵션 추가 -->
                <c:forEach var="m" begin="1" end="12">
                    <option value="${m}" ${param.month == m ? 'selected' : ''}>${m}月</option>
                </c:forEach>
            </select>
        </label>
        <label>状態:
            <select name="jyoutai">
                <option value="">全体</option>
                <option value="在職" ${param.jyoutai == '在職' ? 'selected' : ''}>在職</option>
                <option value="退職" ${param.jyoutai == '退職' ? 'selected' : ''}>退職</option>
            </select>
        </label>
        <label>区分:
            <select name="kubun">
                <option value="">全体</option>
                <!-- 구분 옵션들 -->
                <option value="正規職" ${param.kubun == '正規職' ? 'selected' : ''}>正規職</option>
                <option value="契約職" ${param.kubun == '契約職' ? 'selected' : ''}>契約職</option>
                <option value="臨時職" ${param.kubun == '臨時職' ? 'selected' : ''}>臨時職</option>
                <option value="派遣職" ${param.kubun == '派遣職' ? 'selected' : ''}>派遣職</option>
                <option value="委嘱職" ${param.kubun == '委嘱職' ? 'selected' : ''}>委嘱職</option>
                <option value="日雇い" ${param.kubun == '日雇い' ? 'selected' : ''}>日雇い</option>
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

	<div>
    <table>
        <thead>
            <tr class="table-container">
                <th>区分</th>
                <th>社員番号</th>
                <th>名前</th>
                <th>部署</th>
                <th>役職</th>
                <th>勤怠開始日</th>
                <th>勤怠終了日</th>
                <th>勤怠日数・時間</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="kintai" items="${kintaiList}">
                <tr>
                    <td>${kintai.kubun}</td>
                    <td>No-${kintai.shain_id}</td>
                    <td>${kintai.namae_kana}</td>
                    <td>${kintai.busho_mei}</td>
                    <td>${kintai.yakushoku_mei}</td>
                    <td><fmt:formatDate value="${kintai.kintai_kaishi}" pattern="yyyy-MM-dd" /></td>
                    <td><fmt:formatDate value="${kintai.kintai_shuuryou}" pattern="yyyy-MM-dd" /></td>
                    <td>${kintai.kintaiUnit}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    </div>
    
    <div style="margin-top:20px; text-align: center;">
    <button type="button" onclick="window.print()">印刷</button>
    <button type="button" onclick="downloadExcel()">エクセルダウンロード</button>
</div>
    
</body>
</html>