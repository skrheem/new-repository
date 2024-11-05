<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- 최동주 崔東周 -->
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>日雇い勤務記録管理</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/HiyatoiKinmuKiroku.css">
</head>
<body>
<h2>日雇い勤務記録管理</h2>
<div class="container">
    <!-- 왼쪽 섹션: 일용직 사원 목록 -->
    <div class="left-section">
        <div class="filter">
            <input type="text" placeholder="検索語入力" />
            <button class="btn">🔍</button>
            <button class="btn">全体表示</button>
            <span style="color: red; margin-left: 7px;">* 複数選択時に日雇い勤務記録を一括適用することができます。</span>
            <select name="statusFilter">
                <option value="在職">在職</option>
                <option value="退職">退職</option>
            </select>
        </div>

        <div class="table-container">
            <table>
                <thead>
                    <tr>
                        <th style="width: 5%;"><input type="checkbox" id="selectAll" onclick="selectAllShain(this)" /></th>
                        <th style="width: 10%;">区分</th>
                        <th style="width: 15%;">社員番号</th>
                        <th style="width: 20%;">名前</th>
                        <th style="width: 20%;">部署</th>
                        <th style="width: 10%;">勤務記録
                        <button class="small-btn" onclick="showTip()">?</button>
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="shain" items="${hiyatoiList}">
                        <tr>
                            <td><input type="checkbox" class="shainCheckbox" name="selectedShain" value="${shain.shain_id}" /></td>
                            <td>${shain.kubun}</td>
                            <td>No-${shain.shain_id}</td>
                            <td>${shain.namae_kana}</td>
                            <td>${shain.busho_mei}</td>
                            <td><button class="btn">管理</button></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <!-- 오른쪽 섹션: 근무 기록 관리 -->
    <div class="right-section">
    <button class="btn" onclick="showTip()">? Tip</button>
        <div class="record-form">
            <label class="date-input-container" onclick="focusInput('kinmu_nengappi')">
            勤務日: <input type="date" name="kinmu_nengappi" id="kinmu_nengappi" required />
            </label><br><br>

            <label>現場・プロジェクト:</label>
            <select name="genba_project_id" required>
                <option value="">選択</option>
                <option value="1">現場1</option>
                <option value="2">現場2</option>
                <option value="3">第一工場</option>
                <option value="4">第二工場</option>
                <option value="5">研究所</option>
            </select>
            <button class="btn">リスト管理</button>
            <br><br>

            <label>手当:</label>
            <input type="text" name="teate" placeholder="手当を入力してください。" /> ￥
            <br><br>

            <label>支給率:</label>
            <span>1.0</span>
            <br><br>

            <div class="tax-fields">
                <label>所得税:</label>
                <input type="text" name="shotokuzei" placeholder="自動計算されます。" />
                <br><br>

                <label>地方税:</label>
                <input type="text" name="chihozei" placeholder="自動計算されます。" />
                <br><br>

                <label>実際支給値:</label>
                <input type="text" name="jissai_shikyuu" placeholder="自動計算されます。" />
                <br><br>
            </div>

            <button type="submit" class="btn">保存</button>
            <button type="reset" class="btn" style="background-color: #ccc;">内容削除</button>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/js/HiyatoiKinmuKiroku.js"></script>

</body>
</html>
