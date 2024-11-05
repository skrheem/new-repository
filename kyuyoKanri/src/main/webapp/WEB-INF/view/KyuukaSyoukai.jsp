<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 최동주 崔東周 -->
<html>
<head>
    <title>休暇照会</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/KyuukaSyoukai.css">
</head>
<body>
    <h2>休暇照会</h2>
    <form method="get" action="kyuukaSyoukai.do">
        <div class="filter-section">
            <!-- 휴가항목 선택 -->
            <label for="year">休暇項目選択:</label>
            <select id="year" name="year">
                <option value="none">休暇設定</option>
                <option value="2024_年次有給休暇">2024_年次有給休暇</option>
                <option value="2024_報奨休暇">2024_報奨休暇</option>
            </select>

            <!-- 검색어 입력 -->
            <input type="text" name="searchKeyword" placeholder="検索語入力" value="${param.searchKeyword}">
            <button type="submit">🔍</button>

            <!-- 전체보기 버튼 -->
            <button type="button" onclick="showAll()">全体表示</button>

            <!-- 필터 드롭다운 -->
            <select id="employmentStatus" name="employmentStatus">
                <option value="active">状態別</option>
                <option value="all">在職</option>
                <option value="inactive">退職</option>
            </select>

            <select id="category" name="category">
                <option value="all">区分別</option>
                <option value="正規職">正規職</option>
                <option value="契約職">契約職</option>
                <option value="臨時職">臨時職</option>
                <option value="派遣職">派遣職</option>
                <option value="委嘱職">委嘱職</option>
                <option value="日雇い">日雇い</option>
            </select>

            <select id="department" name="department">
                <option value="all">部署別</option>
                <option value="経営">経営</option>
                <option value="開発">開発</option>
                <option value="コンテンツ">コンテンツ</option>
                <option value="業務支援">業務支援</option>
                <option value="デザイン">デザイン</option>
                <option value="管理">管理</option>
                <option value="企画戦略">企画戦略</option>
            </select>

            <select id="position" name="position">
                <option value="all">役職別</option>
                <option value="理事">理事</option>
                <option value="次長">次長</option>
                <option value="社長">社長</option>
                <option value="部長">部長</option>
                <option value="課長">課長</option>
                <option value="代理">代理</option>
                <option value="主任">主任</option>
                <option value="社員">社員</option>
                <option value="室長">室長</option>
            </select>

            <!-- 보기 개수 선택 -->
            <select id="itemsPerPage" name="itemsPerPage">
                <option value="10">10個出力</option>
                <option value="30">30個出力</option>
                <option value="50">50個出力</option>
                <option value="100">100個出力</option>
            </select>

            <!-- 정렬 기준 버튼 -->
            <button type="button" onclick="sortCriteria()">並べ替え基準設定</button>
        </div>
    </form>

	<div class="table-container">
    <table>
        <tr>
            <th>区分</th>
            <th>社員番号</th>
            <th>名前</th>
            <th>部署</th>
            <th>役職</th>
            <th>休暇項目</th>
            <th>全体</th>
            <th>使用</th>
            <th>残余</th>
        </tr>
        <c:forEach var="kyuuka" items="${kyuukaList}">
            <tr>
                <td>${kyuuka.kubun}</td>
                <td>No-${kyuuka.shain_id}</td>
                <td>${kyuuka.namae_kana}</td>
                <td>${kyuuka.busho_mei}</td>
                <td>${kyuuka.yakushoku_mei}</td>
                <td>${kyuuka.kyuukaShurui}</td>
                <td>${kyuuka.total_kyuuka}</td>
                <td>${kyuuka.used_kyuuka}</td>
                <td>${kyuuka.remaining_kyuuka}</td>
            </tr>
        </c:forEach>
    </table>
    </div>

    <div style="margin-top:20px; text-align: center;">
    <button type="button" onclick="window.print()">印刷</button>
    <button type="button" onclick="downloadExcel()">エクセルダウンロード</button>
</div>

</body>
</html>
