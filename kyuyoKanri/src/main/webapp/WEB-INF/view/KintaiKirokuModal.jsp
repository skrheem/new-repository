<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!-- 최동주 崔東周 -->
<!DOCTYPE html>
<html lang="ko">
<!-- 관리버튼 클릭시 생성되는 모달 창 -->
<head>
    <meta charset="UTF-8">
    <title>社員別勤怠記録</title>
</head>
<body>
    <table>
        <thead>
            <tr>
                <th>番号</th>
                <th>入力日</th>
                <th>勤怠項目</th>
                <th>勤怠機関</th>
                <th>勤怠日数</th>
                <th>金額</th>
                <th>摘要</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="record" items="${kintaiList}">
                <tr>
                    <td>${record.kintai_kiroku_id}</td>
                    <td><fmt:formatDate value="${record.nyuuryoku_bi}" pattern="yyyy-MM-dd" /></td>
                    <td>${record.kintai_mei}</td>
                    <td><fmt:formatDate value="${record.kintai_kaishi}" pattern="yyyy-MM-dd" /> ~ <fmt:formatDate value="${record.kintai_shuuryou}" pattern="yyyy-MM-dd" /></td>
                    <td>${record.kintai_nissuu}</td>
                    <td>${record.teate}</td>
                    <td>${record.tekiyou}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>

