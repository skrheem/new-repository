<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>人事記録カード</title>
<link rel="stylesheet" href="<c:url value='/css/JinjiKirokuCard.css'/>">

<script src="<c:url value='/js/JinjiKirokuCard.js'/>"></script>

</head>
<body>
	<h1>人事記録カード</h1>

	<div class="filter-section">
		<select id="departmentSelect">
			<option value="">全部署</option>
			<option value="content">コンテンツ</option>
			<option value="accounting">会計</option>
			<option value="management">マネジメント</option>
			<option value="business">経営</option>
			<option value="design">デザイン</option>
		</select> <select id="positionSelect">
			<option value="">全職位</option>
			<option value="employee">社員</option>
			<option value="manager">部長</option>
			<option value="chief">課長</option>
			<option value="ceo">社長</option>
		</select> <input type="text"  id="searchInput" placeholder="検索語入力">
		<button type="button" onclick="openPopupWithFilteredResults()">検索</button>

		<!-- 社員選択ポップアップウィンドウ -->
		<button type="button" onclick="openManagePopup()">全体表示</button>

		<div id="managePopupOverlay" class="popup-overlay"
			style="display: none;">
			<div class="popup-content">
				<h2>人事記録カードの社員選択</h2>
				<table id="employeeTable" class="employee-table">
					<thead>
						<tr>
							<th>区分</th>
							<th>社員番号</th>
							<th>名前</th>
							<th>部署</th>
							<th>役職</th>
							<th>状態</th>
						</tr>
					</thead>
					<tbody id="employeeList">
						<!-- 社員リストはJavaScriptを通じて動的に追加 -->
					</tbody>
				</table>
				<div class="popup-buttons">
					<button onclick="confirmSelection()">選択</button>
					<button onclick="closeManagePopup()">キャンセル</button>
				</div>
			</div>
		</div>


	</div>


	<form id="inquiryForm">
		<div id="content">
			<div class="container">
				<!-- 人事記録カード -->
				<div class="left-card">

					<table summary="人事記録カード" border="0" cellspacing="0" cellpadding="0"
						class="personnelCard01">
						<tbody>
							<tr>
								<th rowspan="8" class="pcPic"><img
									src="${pageContext.request.contextPath}${recordCardInfo.shainInfo[0].shain_image_keiro}"
									alt="社員写真" style="width: 100px; height: 100px;"></th>
								<th rowspan="3" colspan="15" class="pcTit01">人事記録カード</th>
								<td colspan="4" class="pcTd01">社員番号</td>
								<td colspan="5" class="pcTd02" style="width: 100px;">${recordCardInfo.shainInfo[0].shain_id}</td>
							</tr>
							<tr>
								<td colspan="4" class="pcTd01">入社日</td>
								<td colspan="5" class="pcTd02">${recordCardInfo.shainInfo[0].nyuusha_nengappi}</td>
							</tr>
							<tr>
								<td colspan="4" class="pcTd03">退社日</td>
								<td colspan="5">${recordCardInfo.shainInfo[0].taisha_nengappi}</td>
							</tr>
							<tr>
								<td colspan="4" class="pcTd04 b_blue">名前(カナ)</td>
								<td colspan="8" class="pcTd04" style="width: 100px;">${recordCardInfo.shainInfo[0].namae_kana}</td>
								<td colspan="4" class="pcTd04 b_blue">名前(英語)</td>
								<td colspan="8" class="pcTd05" style="width: 100px;">${recordCardInfo.shainInfo[0].namae_eigo}</td>
							</tr>
							<tr>
								<td colspan="4" class="pcTd06 b_blue">住民番号</td>
								<td colspan="8" class="pcTd06">${recordCardInfo.shainInfo[0].jumin_bangou}</td>
								<td colspan="4" class="pcTd06 b_blue">社員区分</td>
								<td colspan="8" class="pcTd07">${recordCardInfo.shainInfo[0].kubun}</td>
							</tr>
							<tr>
								<td colspan="4" class="pcTd06 b_blue">住所</td>
								<td colspan="20" class="pcAddress01">${recordCardInfo.shainInfo[0].juusho}</td>
							</tr>
							<tr>
								<td colspan="4" class="pcTd06 b_blue">携帯番号</td>
								<td colspan="8" class="pcTd06">${recordCardInfo.shainInfo[0].denwa_keitai}</td>
								<td colspan="4" class="pcTd06 b_blue">連絡先</td>
								<td colspan="8" class="pcTd07">${recordCardInfo.shainInfo[0].denwa_uchi}</td>
							</tr>
							<tr>
								<td colspan="4" class="pcTd06 b_blue">メール</td>
								<td colspan="20" class="pcTd06">${recordCardInfo.shainInfo[0].meeru}</td>
							</tr>
						</tbody>
					</table>

					<table summary="人事記録カード" border="0" cellspacing="0" cellpadding="0"
						class="personnelCard02">
						<tbody>
							<tr>
								<th rowspan="8" class="pcSubtit">家<br>族<br>事<br>項
								</th>
								<td class="pcTd08 tdColor">関係</td>
								<td class="pcTd08 tdColor">名前</td>
								<td class="pcTd08 tdColor" style="width: 100px;">住民番号</td>
								<td class="pcTd08 tdColor">同居可否</td>
								<td rowspan="2" class="pcTd09 ">国民<br>年金
								</td>
								<td class="pcTd08 tdColor" style="width: 50px;">記号番号</td>
								<td colspan="3" class="pcTd08">${recordCardInfo.yondaihokenList[0].kigouBangou}</td>
							</tr>
							<tr>
								<td class="pcTd08">${recordCardInfo.fuyouKazokuList[0].kankei}</td>
								<td class="pcTd08">${recordCardInfo.fuyouKazokuList[0].namae}</td>
								<td class="pcTd08">${recordCardInfo.fuyouKazokuList[0].jumin_bangou}</td>
								<td class="pcTd08">${recordCardInfo.fuyouKazokuList[0].doukyoUmu}</td>
								<td class="pcTd08">取得日</td>
								<td class="pcTd08" style="width: 70px;">${recordCardInfo.yondaihokenList[0].shutoku_bi}</td>
								<td class="pcTd08">喪失日</td>
								<td class="pcTd08" style="width: 70px;">${recordCardInfo.yondaihokenList[0].soshitsu_bi}</td>
							</tr>
							<tr>
								<td class="pcTd08">${recordCardInfo.fuyouKazokuList[1].kankei}</td>
								<td class="pcTd08">${recordCardInfo.fuyouKazokuList[1].namae}</td>
								<td class="pcTd08">${recordCardInfo.fuyouKazokuList[1].jumin_bangou}</td>
								<td class="pcTd08">${recordCardInfo.fuyouKazokuList[1].doukyoUmu}</td>
								<td rowspan="2" class="pcTd09">健康<br>保険
								</td>
								<td class="pcTd08 tdColor">記号番号</td>
								<td colspan="3" class="pcTd08">${recordCardInfo.yondaihokenList[1].kigouBangou}</td>
							</tr>
							<tr>
								<td class="pcTd08">${recordCardInfo.fuyouKazokuList[2].kankei}</td>
								<td class="pcTd08">${recordCardInfo.fuyouKazokuList[2].namae}</td>
								<td class="pcTd08">${recordCardInfo.fuyouKazokuList[2].jumin_bangou}</td>
								<td class="pcTd08">${recordCardInfo.fuyouKazokuList[2].doukyoUmu}</td>
								<td class="pcTd08">所得日</td>
								<td class="pcTd08" style="width: 60px;">${recordCardInfo.yondaihokenList[1].shutoku_bi}</td>
								<td class="pcTd08">消失日</td>
								<td class="pcTd08" style="width: 60px;">${recordCardInfo.yondaihokenList[1].soshitsu_bi}</td>
							</tr>
							<tr>
								<td class="pcTd08">${recordCardInfo.fuyouKazokuList[3].kankei}</td>
								<td class="pcTd08">${recordCardInfo.fuyouKazokuList[3].namae}</td>
								<td class="pcTd08">${recordCardInfo.fuyouKazokuList[3].jumin_bangou}</td>
								<td class="pcTd08">${recordCardInfo.fuyouKazokuList[3].doukyoUmu}</td>
								<td rowspan="2" class="pcTd09">雇用<br>保険
								</td>
								<td class="pcTd08 tdColor">記号番号</td>
								<td colspan="3" class="pcTd08">${recordCardInfo.yondaihokenList[2].kigouBangou}</td>
							</tr>
							<tr>
								<td class="pcTd08">${recordCardInfo.fuyouKazokuList[4].kankei}</td>
								<td class="pcTd08">${recordCardInfo.fuyouKazokuList[4].namae}</td>
								<td class="pcTd08">${recordCardInfo.fuyouKazokuList[4].jumin_bangou}</td>
								<td class="pcTd08">${recordCardInfo.fuyouKazokuList[4].doukyoUmu}</td>
								<td class="pcTd08">所得日</td>
								<td class="pcTd08" style="width: 60px;">${recordCardInfo.yondaihokenList[2].shutoku_bi}</td>
								<td class="pcTd08">消失日</td>
								<td class="pcTd08" style="width: 60px;">${recordCardInfo.yondaihokenList[2].soshitsu_bi}</td>
							</tr>
							<tr>
								<td class="pcTd08">${recordCardInfo.fuyouKazokuList[5].kankei}</td>
								<td class="pcTd08">${recordCardInfo.fuyouKazokuList[5].namae}</td>
								<td class="pcTd08">${recordCardInfo.fuyouKazokuList[5].jumin_bangou}</td>
								<td class="pcTd08">${recordCardInfo.fuyouKazokuList[5].doukyoUmu}</td>
								<td rowspan="2" class="pcTd11">産災<br>保険
								</td>
								<td class="pcTd08 tdColor">記号番号</td>
								<td colspan="3" class="pcTd08">${recordCardInfo.yondaihokenList[3].kigouBangou}</td>
							</tr>
							<tr>
								<td class="pcTd08">${recordCardInfo.fuyouKazokuList[6].kankei}</td>
								<td class="pcTd08">${recordCardInfo.fuyouKazokuList[6].namae}</td>
								<td class="pcTd08">${recordCardInfo.fuyouKazokuList[6].jumin_bangou}</td>
								<td class="pcTd08">${recordCardInfo.fuyouKazokuList[6].doukyoUmu}</td>
								<td class="pcTd10">所得日</td>
								<td class="pcTd08" style="width: 60px;">${recordCardInfo.yondaihokenList[3].shutoku_bi}</td>
								<td class="pcTd08">消失日</td>
								<td class="pcTd08" style="width: 60px;">${recordCardInfo.yondaihokenList[3].soshitsu_bi}</td>
							</tr>
						</tbody>
					</table>

					<table summary="人事記録カード" border="0" cellspacing="0" cellpadding="0"
						class="personnelCard02">
						<tbody>
							<tr>
								<th rowspan="6" class="pcSubtit">学<br>歴
								</th>
								<td class="pcTd08 tdColor">学校名</td>
								<td class="pcTd08 tdColor">入学年月</td>
								<td class="pcTd08 tdColor">卒業年月</td>
								<td class="pcTd08 tdColor">専門</td>
								<td class="pcTd08 tdColor">履行</td>
							</tr>
							<tr>
								<td class="pcTd08">${recordCardInfo.gakurekiList[0].gakkoNama}</td>
								<td class="pcTd08">${recordCardInfo.gakurekiList[0].nyugakuNengatsu}</td>
								<td class="pcTd08">${recordCardInfo.gakurekiList[0].sotsugyoNengatsu}</td>
								<td class="pcTd08">${recordCardInfo.gakurekiList[0].senmon}</td>
								<td class="pcTd08">${recordCardInfo.gakurekiList[0].shuuryouJyoutai}</td>
							</tr>
							<tr>
								<td class="pcTd08">${recordCardInfo.gakurekiList[1].gakkoNama}</td>
								<td class="pcTd08">${recordCardInfo.gakurekiList[1].nyugakuNengatsu}</td>
								<td class="pcTd08">${recordCardInfo.gakurekiList[1].sotsugyoNengatsu}</td>
								<td class="pcTd08">${recordCardInfo.gakurekiList[1].senmon}</td>
								<td class="pcTd08">${recordCardInfo.gakurekiList[1].shuuryouJyoutai}</td>
							</tr>
							<tr>
								<td class="pcTd08">${recordCardInfo.gakurekiList[2].gakkoNama}</td>
								<td class="pcTd08">${recordCardInfo.gakurekiList[2].nyugakuNengatsu}</td>
								<td class="pcTd08">${recordCardInfo.gakurekiList[2].sotsugyoNengatsu}</td>
								<td class="pcTd08">${recordCardInfo.gakurekiList[2].senmon}</td>
								<td class="pcTd08">${recordCardInfo.gakurekiList[2].shuuryouJyoutai}</td>
							</tr>
							<tr>
								<td class="pcTd08">${recordCardInfo.gakurekiList[3].gakkoNama}</td>
								<td class="pcTd08">${recordCardInfo.gakurekiList[3].nyugakuNengatsu}</td>
								<td class="pcTd08">${recordCardInfo.gakurekiList[3].sotsugyoNengatsu}</td>
								<td class="pcTd08">${recordCardInfo.gakurekiList[3].senmon}</td>
								<td class="pcTd08">${recordCardInfo.gakurekiList[3].shuuryouJyoutai}</td>
							</tr>
							<tr>
								<td class="pcTd08">${recordCardInfo.gakurekiList[4].gakkoNama}</td>
								<td class="pcTd08">${recordCardInfo.gakurekiList[4].nyugakuNengatsu}</td>
								<td class="pcTd08">${recordCardInfo.gakurekiList[4].sotsugyoNengatsu}</td>
								<td class="pcTd08">${recordCardInfo.gakurekiList[4].senmon}</td>
								<td class="pcTd08">${recordCardInfo.gakurekiList[4].shuuryouJyoutai}</td>
							</tr>
						</tbody>
					</table>

					<table summary="人事記録カード" border="0" cellspacing="0" cellpadding="0"
						class="personnelCard02">
						<tbody>
							<tr>
								<th rowspan="3" class="pcSubtit">兵<br>役
								</th>
								<td colspan="3" class="pcTd08 tdColor">除隊区分</td>
								<td colspan="5" class="pcTd08" style="width: 79px">${recordCardInfo.heiekiList[0].heiekiKubun}</td>
								<td colspan="4" class="pcTd08 tdColor">未了事由</td>
								<td colspan="16" class="pcTd08">${recordCardInfo.heiekiList[0].mirikouRiyuuCode}</td>
							</tr>
							<tr class="tdColor">
								<td colspan="6" class="pcTd08">軍別</td>
								<td colspan="4" class="pcTd08">最終階級</td>
								<td colspan="4" class="pcTd08">兵科</td>
								<td colspan="14" class="pcTd08">服務期間</td>
							</tr>
							<tr>
								<td colspan="6" class="pcTd10">${recordCardInfo.heiekiList[0].gunBetsu}</td>
								<td colspan="4" class="pcTd10"></td>
								<td colspan="4" class="pcTd10">${recordCardInfo.heiekiList[0].heika}</td>
								<td colspan="14" class="pcTd10">${recordCardInfo.heiekiList[0].fukumukiKaishi}-${recordCardInfo.heiekiList[0].fukumukiShuuryou}</td>
							</tr>
						</tbody>
					</table>

					<table summary="人事記録カード" border="0" cellspacing="0" cellpadding="0"
						class="personnelCard02">
						<tbody>
							<tr>
								<th rowspan="6" class="pcSubtit">経<br>歴
								</th>
								<td class="pcTd08 tdColor">社名</td>
								<td class="pcTd08 tdColor">入社日</td>
								<td class="pcTd08 tdColor">退社日</td>
								<td class="pcTd08 tdColor">最終職位</td>
								<td class="pcTd08 tdColor">担当業務</td>
							</tr>
							<tr>
								<td class="pcTd08">${recordCardInfo.keirekiList[0].kaishaNama}</td>
								<td class="pcTd08">${recordCardInfo.keirekiList[0].nyusha_bi}</td>
								<td class="pcTd08">${recordCardInfo.keirekiList[0].taisha_bi}</td>
								<td class="pcTd08">${recordCardInfo.keirekiList[0].saigoShokui}</td>
								<td class="pcTd08">${recordCardInfo.keirekiList[0].tantouShigoto}</td>
							</tr>
							<tr>
								<td class="pcTd08">${recordCardInfo.keirekiList[1].kaishaNama}</td>
								<td class="pcTd08">${recordCardInfo.keirekiList[1].nyusha_bi}</td>
								<td class="pcTd08">${recordCardInfo.keirekiList[1].taisha_bi}</td>
								<td class="pcTd08">${recordCardInfo.keirekiList[1].saigoShokui}</td>
								<td class="pcTd08">${recordCardInfo.keirekiList[1].tantouShigoto}</td>
							</tr>
							<tr>
								<td class="pcTd08">${recordCardInfo.keirekiList[2].kaishaNama}</td>
								<td class="pcTd08">${recordCardInfo.keirekiList[2].nyusha_bi}</td>
								<td class="pcTd08">${recordCardInfo.keirekiList[2].taisha_bi}</td>
								<td class="pcTd08">${recordCardInfo.keirekiList[2].saigoShokui}</td>
								<td class="pcTd08">${recordCardInfo.keirekiList[2].tantouShigoto}</td>
							</tr>
							<tr>
								<td class="pcTd08">${recordCardInfo.keirekiList[3].kaishaNama}</td>
								<td class="pcTd08">${recordCardInfo.keirekiList[3].nyusha_bi}</td>
								<td class="pcTd08">${recordCardInfo.keirekiList[3].taisha_bi}</td>
								<td class="pcTd08">${recordCardInfo.keirekiList[3].saigoShokui}</td>
								<td class="pcTd08">${recordCardInfo.keirekiList[3].tantouShigoto}</td>
							</tr>
							<tr>
								<td class="pcTd08">${recordCardInfo.keirekiList[4].kaishaNama}</td>
								<td class="pcTd08">${recordCardInfo.keirekiList[4].nyusha_bi}</td>
								<td class="pcTd08">${recordCardInfo.keirekiList[4].taisha_bi}</td>
								<td class="pcTd08">${recordCardInfo.keirekiList[4].saigoShokui}</td>
								<td class="pcTd08">${recordCardInfo.keirekiList[4].tantouShigoto}</td>
							</tr>
						</tbody>
					</table>

					<table summary="人事記録カード" border="0" cellspacing="0" cellpadding="0"
						class="personnelCard02">
						<tbody>
							<tr>
								<th rowspan="6" class="pcSubtit">資<br>格<br>/<br>免<br>許
								</th>
								<td class="pcTd08 tdColor">種類</td>
								<td class="pcTd08 tdColor">取得日</td>
								<td class="pcTd08 tdColor">発行機関</td>
								<td class="pcTd08 tdColor">備考</td>
							</tr>
							<tr>
								<td class="pcTd08">${recordCardInfo.shikakuList[0].shikaku_mei}</td>
								<td class="pcTd08">${recordCardInfo.shikakuList[0].shutoku_bi}</td>
								<td class="pcTd08">${recordCardInfo.shikakuList[0].hakkou_kikan}</td>
								<td class="pcTd08">${recordCardInfo.shikakuList[0].bikou}</td>
							</tr>
							<tr>
								<td class="pcTd08">${recordCardInfo.shikakuList[1].shikaku_mei}</td>
								<td class="pcTd08">${recordCardInfo.shikakuList[1].shutoku_bi}</td>
								<td class="pcTd08">${recordCardInfo.shikakuList[1].hakkou_kikan}</td>
								<td class="pcTd08">${recordCardInfo.shikakuList[1].bikou}</td>
							</tr>
							<tr>
								<td class="pcTd08">${recordCardInfo.shikakuList[2].shikaku_mei}</td>
								<td class="pcTd08">${recordCardInfo.shikakuList[2].shutoku_bi}</td>
								<td class="pcTd08">${recordCardInfo.shikakuList[2].hakkou_kikan}</td>
								<td class="pcTd08">${recordCardInfo.shikakuList[2].bikou}</td>
							</tr>
							<tr>
								<td class="pcTd08">${recordCardInfo.shikakuList[3].shikaku_mei}</td>
								<td class="pcTd08">${recordCardInfo.shikakuList[3].shutoku_bi}</td>
								<td class="pcTd08">${recordCardInfo.shikakuList[3].hakkou_kikan}</td>
								<td class="pcTd08">${recordCardInfo.shikakuList[3].bikou}</td>
							</tr>
							<tr>
								<td class="pcTd08">${recordCardInfo.shikakuList[4].shikaku_mei}</td>
								<td class="pcTd08">${recordCardInfo.shikakuList[4].shutoku_bi}</td>
								<td class="pcTd08">${recordCardInfo.shikakuList[4].hakkou_kikan}</td>
								<td class="pcTd08">${recordCardInfo.shikakuList[4].bikou}</td>
							</tr>
						</tbody>
					</table>

					<table summary="人事記録カード" border="0" cellspacing="0" cellpadding="0"
						class="personnelCard03">
						<tbody>
							<tr>
								<th rowspan="4" class="pcSubtit">語<br>学<br>能<br>力
								</th>
								<td class="pcTd08 tdColor">外国語名</td>
								<td class="pcTd08 tdColor">試験</td>
								<td class="pcTd08 tdColor">公認点数</td>
								<td class="pcTd08 tdColor">取得日</td>
								<td class="pcTd08 tdColor">読解</td>
								<td class="pcTd08 tdColor">作文</td>
								<td class="pcTd08 tdColor">会話</td>
							</tr>
							<tr>
								<td class="pcTd08">${recordCardInfo.gogakuList[0].gengo}</td>
								<td class="pcTd08">${recordCardInfo.gogakuList[0].shiken_mei}</td>
								<td class="pcTd08">${recordCardInfo.gogakuList[0].tensuu}</td>
								<td class="pcTd08">${recordCardInfo.gogakuList[0].shutoku_bi}</td>
								<td class="pcTd08">${recordCardInfo.gogakuList[0].dokkaiNouryoku}</td>
								<td class="pcTd08">${recordCardInfo.gogakuList[0].sakubunNouryoku}</td>
								<td class="pcTd08">${recordCardInfo.gogakuList[0].kaiwaNouryoku}</td>
							</tr>
							<tr>
								<td class="pcTd08">${recordCardInfo.gogakuList[1].gengo}</td>
								<td class="pcTd08">${recordCardInfo.gogakuList[1].shiken_mei}</td>
								<td class="pcTd08">${recordCardInfo.gogakuList[1].tensuu}</td>
								<td class="pcTd08">${recordCardInfo.gogakuList[1].shutoku_bi}</td>
								<td class="pcTd08">${recordCardInfo.gogakuList[1].dokkaiNouryoku}</td>
								<td class="pcTd08">${recordCardInfo.gogakuList[1].sakubunNouryoku}</td>
								<td class="pcTd08">${recordCardInfo.gogakuList[1].kaiwaNouryoku}</td>
							</tr>
							<tr>
								<td class="pcTd08">${recordCardInfo.gogakuList[2].gengo}</td>
								<td class="pcTd08">${recordCardInfo.gogakuList[2].shiken_mei}</td>
								<td class="pcTd08">${recordCardInfo.gogakuList[2].tensuu}</td>
								<td class="pcTd08">${recordCardInfo.gogakuList[2].shutoku_bi}</td>
								<td class="pcTd08">${recordCardInfo.gogakuList[2].dokkaiNouryoku}</td>
								<td class="pcTd08">${recordCardInfo.gogakuList[2].sakubunNouryoku}</td>
								<td class="pcTd08">${recordCardInfo.gogakuList[2].kaiwaNouryoku}</td>
							</tr>
						</tbody>
					</table>
				</div>





				<!-- 2ページ-->
				<div class="right-card">
					<table summary="人事記録カード" border="0" cellspacing="0" cellpadding="0"
						class="personnelCard01">
						<tbody>
							<tr>
								<th class="pcTit02">人事記録カード</th>
							</tr>
						</tbody>
					</table>
					<table summary="人事記録カード" border="0" cellspacing="0" cellpadding="0"
						class="personnelCard02">
						<tbody>
							<tr>
								<th rowspan="11" class="pcSubtit">教<br>育<br>事<br>項<br></th>
								<td class="pcTd08 tdColor">教育区分</td>
								<td class="pcTd08 tdColor">教育名</td>
								<td class="pcTd08 tdColor">期間(から)</td>
								<td class="pcTd08 tdColor">期間(まで)</td>
								<td class="pcTd08 tdColor">教育機関</td>
								<td class="pcTd08 tdColor">教育費</td>
								<td class="pcTd08 tdColor">還付教育費</td>
							</tr>
							<tr>
								<td class="pcTd08">${recordCardInfo.kyouikuList[0].kunren_shubetsu}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[0].kyouiku_mei}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[0].kaishi_bi}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[0].shuuryou_bi}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[0].kikan}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[0].kyouiku_hiyou}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[0].kunren_shubetsu}</td>
							</tr>
							<tr>
								<td class="pcTd08">${recordCardInfo.kyouikuList[1].kunren_shubetsu}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[1].kyouiku_mei}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[1].kaishi_bi}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[1].shuuryou_bi}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[1].kikan}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[1].kyouiku_hiyou}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[1].kunren_shubetsu}</td>
							</tr>
							<tr>
								<td class="pcTd08">${recordCardInfo.kyouikuList[2].kunren_shubetsu}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[2].kyouiku_mei}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[2].kaishi_bi}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[2].shuuryou_bi}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[2].kikan}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[2].kyouiku_hiyou}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[2].kunren_shubetsu}</td>
							</tr>
							<tr>
								<td class="pcTd08">${recordCardInfo.kyouikuList[3].kunren_shubetsu}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[3].kyouiku_mei}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[3].kaishi_bi}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[3].shuuryou_bi}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[3].kikan}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[3].kyouiku_hiyou}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[3].kunren_shubetsu}</td>
							</tr>
							<tr>
								<td class="pcTd08">${recordCardInfo.kyouikuList[4].kunren_shubetsu}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[4].kyouiku_mei}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[4].kaishi_bi}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[4].shuuryou_bi}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[4].kikan}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[4].kyouiku_hiyou}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[4].kunren_shubetsu}</td>
							</tr>
							<tr>
								<td class="pcTd08">${recordCardInfo.kyouikuList[5].kunren_shubetsu}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[5].kyouiku_mei}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[5].kaishi_bi}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[5].shuuryou_bi}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[5].kikan}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[5].kyouiku_hiyou}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[5].kunren_shubetsu}</td>
							</tr>
							<tr>
								<td class="pcTd08">${recordCardInfo.kyouikuList[6].kunren_shubetsu}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[6].kyouiku_mei}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[6].kaishi_bi}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[6].shuuryou_bi}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[6].kikan}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[6].kyouiku_hiyou}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[6].kunren_shubetsu}</td>
							</tr>
							<tr>
								<td class="pcTd08">${recordCardInfo.kyouikuList[7].kunren_shubetsu}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[7].kyouiku_mei}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[7].kaishi_bi}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[7].shuuryou_bi}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[7].kikan}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[7].kyouiku_hiyou}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[7].kunren_shubetsu}</td>
							</tr>
							<tr>
								<td class="pcTd08">${recordCardInfo.kyouikuList[8].kunren_shubetsu}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[8].kyouiku_mei}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[8].kaishi_bi}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[8].shuuryou_bi}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[8].kikan}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[8].kyouiku_hiyou}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[8].kunren_shubetsu}</td>
							</tr>
							<tr>
								<td class="pcTd08">${recordCardInfo.kyouikuList[9].kunren_shubetsu}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[9].kyouiku_mei}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[9].kaishi_bi}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[9].shuuryou_bi}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[9].kikan}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[9].kyouiku_hiyou}</td>
								<td class="pcTd08">${recordCardInfo.kyouikuList[9].kunren_shubetsu}</td>
							</tr>
						</tbody>
					</table>

					<table summary="人事記録カード" border="0" cellspacing="0" cellpadding="0"
						class="personnelCard02">
						<tbody>
							<tr>
								<th rowspan="10" class="pcSubtit">賞<br>罰<br>事<br>項<br></th>
								<td class="pcTd08 tdColor">区分</td>
								<td class="pcTd08 tdColor">賞罰名</td>
								<td class="pcTd08 tdColor">賞罰権者</td>
								<td class="pcTd08 tdColor">賞罰日付</td>
								<td class="pcTd08 tdColor">賞罰内容</td>
								<td class="pcTd08 tdColor">備考</td>
							</tr>
							<tr>
								<td class="pcTd08">${recordCardInfo.shoubatsuList[0].shouBatsuKubun}</td>
								<td class="pcTd08">${recordCardInfo.shoubatsuList[0].shouBatsuMei}</td>
								<td class="pcTd08">${recordCardInfo.shoubatsuList[0].shouBatsuSha}</td>
								<td class="pcTd08">${recordCardInfo.shoubatsuList[0].shouBatsu_bi}</td>
								<td class="pcTd08">${recordCardInfo.shoubatsuList[0].shouBatsuNaiyou}</td>
								<td class="pcTd08">${recordCardInfo.shoubatsuList[0].bikou}</td>
							</tr>
							<tr>
								<td class="pcTd08">${recordCardInfo.shoubatsuList[1].shouBatsuKubun}</td>
								<td class="pcTd08">${recordCardInfo.shoubatsuList[1].shouBatsuMei}</td>
								<td class="pcTd08">${recordCardInfo.shoubatsuList[1].shouBatsuSha}</td>
								<td class="pcTd08">${recordCardInfo.shoubatsuList[1].shouBatsu_bi}</td>
								<td class="pcTd08">${recordCardInfo.shoubatsuList[1].shouBatsuNaiyou}</td>
								<td class="pcTd08">${recordCardInfo.shoubatsuList[1].bikou}</td>
							</tr>
							<tr>
								<td class="pcTd08">${recordCardInfo.shoubatsuList[2].shouBatsuKubun}</td>
								<td class="pcTd08">${recordCardInfo.shoubatsuList[2].shouBatsuMei}</td>
								<td class="pcTd08">${recordCardInfo.shoubatsuList[2].shouBatsuSha}</td>
								<td class="pcTd08">${recordCardInfo.shoubatsuList[2].shouBatsu_bi}</td>
								<td class="pcTd08">${recordCardInfo.shoubatsuList[2].shouBatsuNaiyou}</td>
								<td class="pcTd08">${recordCardInfo.shoubatsuList[2].bikou}</td>
							</tr>
							<tr>
								<td class="pcTd08">${recordCardInfo.shoubatsuList[3].shouBatsuKubun}</td>
								<td class="pcTd08">${recordCardInfo.shoubatsuList[3].shouBatsuMei}</td>
								<td class="pcTd08">${recordCardInfo.shoubatsuList[3].shouBatsuSha}</td>
								<td class="pcTd08">${recordCardInfo.shoubatsuList[3].shouBatsu_bi}</td>
								<td class="pcTd08">${recordCardInfo.shoubatsuList[3].shouBatsuNaiyou}</td>
								<td class="pcTd08">${recordCardInfo.shoubatsuList[3].bikou}</td>
							</tr>
							<tr>
								<td class="pcTd08">${recordCardInfo.shoubatsuList[4].shouBatsuKubun}</td>
								<td class="pcTd08">${recordCardInfo.shoubatsuList[4].shouBatsuMei}</td>
								<td class="pcTd08">${recordCardInfo.shoubatsuList[4].shouBatsuSha}</td>
								<td class="pcTd08">${recordCardInfo.shoubatsuList[4].shouBatsu_bi}</td>
								<td class="pcTd08">${recordCardInfo.shoubatsuList[4].shouBatsuNaiyou}</td>
								<td class="pcTd08">${recordCardInfo.shoubatsuList[4].bikou}</td>
							</tr>
							<tr>
								<td class="pcTd08">${recordCardInfo.shoubatsuList[5].shouBatsuKubun}</td>
								<td class="pcTd08">${recordCardInfo.shoubatsuList[5].shouBatsuMei}</td>
								<td class="pcTd08">${recordCardInfo.shoubatsuList[5].shouBatsuSha}</td>
								<td class="pcTd08">${recordCardInfo.shoubatsuList[5].shouBatsu_bi}</td>
								<td class="pcTd08">${recordCardInfo.shoubatsuList[5].shouBatsuNaiyou}</td>
								<td class="pcTd08">${recordCardInfo.shoubatsuList[5].bikou}</td>
							</tr>
							<tr>
								<td class="pcTd08">${recordCardInfo.shoubatsuList[6].shouBatsuKubun}</td>
								<td class="pcTd08">${recordCardInfo.shoubatsuList[6].shouBatsuMei}</td>
								<td class="pcTd08">${recordCardInfo.shoubatsuList[6].shouBatsuSha}</td>
								<td class="pcTd08">${recordCardInfo.shoubatsuList[6].shouBatsu_bi}</td>
								<td class="pcTd08">${recordCardInfo.shoubatsuList[6].shouBatsuNaiyou}</td>
								<td class="pcTd08">${recordCardInfo.shoubatsuList[6].bikou}</td>
							</tr>
							<tr>
								<td class="pcTd08">${recordCardInfo.shoubatsuList[7].shouBatsuKubun}</td>
								<td class="pcTd08">${recordCardInfo.shoubatsuList[7].shouBatsuMei}</td>
								<td class="pcTd08">${recordCardInfo.shoubatsuList[7].shouBatsuSha}</td>
								<td class="pcTd08">${recordCardInfo.shoubatsuList[7].shouBatsu_bi}</td>
								<td class="pcTd08">${recordCardInfo.shoubatsuList[7].shouBatsuNaiyou}</td>
								<td class="pcTd08">${recordCardInfo.shoubatsuList[7].bikou}</td>
							</tr>
							<tr>
								<td class="pcTd08">${recordCardInfo.shoubatsuList[8].shouBatsuKubun}</td>
								<td class="pcTd08">${recordCardInfo.shoubatsuList[8].shouBatsuMei}</td>
								<td class="pcTd08">${recordCardInfo.shoubatsuList[8].shouBatsuSha}</td>
								<td class="pcTd08">${recordCardInfo.shoubatsuList[8].shouBatsu_bi}</td>
								<td class="pcTd08">${recordCardInfo.shoubatsuList[8].shouBatsuNaiyou}</td>
								<td class="pcTd08">${recordCardInfo.shoubatsuList[8].bikou}</td>
							</tr>
						</tbody>
					</table>

					<table summary="人事記録カード" border="0" cellspacing="0" cellpadding="0"
						class="personnelCard02">
						<tbody>
							<tr>
								<th rowspan="11" class="pcSubtit">人<br>事<br>発<br>令<br></th>
								<td class="pcTd08 tdColor">発令区分</td>
								<td class="pcTd08 tdColor">発令日</td>
								<td class="pcTd08 tdColor">部署</td>
								<td class="pcTd08 tdColor">職位</td>
								<td class="pcTd08 tdColor">役職及び担当業務</td>
								<td class="pcTd08 tdColor">備考</td>
							</tr>
							<tr>
								<td class="pcTd08">${recordCardInfo.hatsureiList[0].hatsureiKubun}</td>
								<td class="pcTd08">${recordCardInfo.hatsureiList[0].hatsureiNichi}</td>
								<td class="pcTd08">${recordCardInfo.hatsureiList[0].busho}</td>
								<td class="pcTd08">${recordCardInfo.hatsureiList[0].yakushoku}</td>
								<td class="pcTd08">${recordCardInfo.hatsureiList[0].shokuseki}</td>
								<td class="pcTd08">${recordCardInfo.hatsureiList[0].bikou}</td>
							</tr>
							<tr>
								<td class="pcTd08">${recordCardInfo.hatsureiList[1].hatsureiKubun}</td>
								<td class="pcTd08">${recordCardInfo.hatsureiList[1].hatsureiNichi}</td>
								<td class="pcTd08">${recordCardInfo.hatsureiList[1].busho}</td>
								<td class="pcTd08">${recordCardInfo.hatsureiList[1].yakushoku}</td>
								<td class="pcTd08">${recordCardInfo.hatsureiList[1].shokuseki}</td>
								<td class="pcTd08">${recordCardInfo.hatsureiList[1].bikou}</td>
							</tr>
							<tr>
								<td class="pcTd08">${recordCardInfo.hatsureiList[2].hatsureiKubun}</td>
								<td class="pcTd08">${recordCardInfo.hatsureiList[2].hatsureiNichi}</td>
								<td class="pcTd08">${recordCardInfo.hatsureiList[2].busho}</td>
								<td class="pcTd08">${recordCardInfo.hatsureiList[2].yakushoku}</td>
								<td class="pcTd08">${recordCardInfo.hatsureiList[2].shokuseki}</td>
								<td class="pcTd08">${recordCardInfo.hatsureiList[2].bikou}</td>
							</tr>
							<tr>
								<td class="pcTd08">${recordCardInfo.hatsureiList[3].hatsureiKubun}</td>
								<td class="pcTd08">${recordCardInfo.hatsureiList[3].hatsureiNichi}</td>
								<td class="pcTd08">${recordCardInfo.hatsureiList[3].busho}</td>
								<td class="pcTd08">${recordCardInfo.hatsureiList[3].yakushoku}</td>
								<td class="pcTd08">${recordCardInfo.hatsureiList[3].shokuseki}</td>
								<td class="pcTd08">${recordCardInfo.hatsureiList[3].bikou}</td>
							</tr>
							<tr>
								<td class="pcTd08">${recordCardInfo.hatsureiList[4].hatsureiKubun}</td>
								<td class="pcTd08">${recordCardInfo.hatsureiList[4].hatsureiNichi}</td>
								<td class="pcTd08">${recordCardInfo.hatsureiList[4].busho}</td>
								<td class="pcTd08">${recordCardInfo.hatsureiList[4].yakushoku}</td>
								<td class="pcTd08">${recordCardInfo.hatsureiList[4].shokuseki}</td>
								<td class="pcTd08">${recordCardInfo.hatsureiList[4].bikou}</td>
							</tr>
							<tr>
								<td class="pcTd08">${recordCardInfo.hatsureiList[5].hatsureiKubun}</td>
								<td class="pcTd08">${recordCardInfo.hatsureiList[5].hatsureiNichi}</td>
								<td class="pcTd08">${recordCardInfo.hatsureiList[5].busho}</td>
								<td class="pcTd08">${recordCardInfo.hatsureiList[5].yakushoku}</td>
								<td class="pcTd08">${recordCardInfo.hatsureiList[5].shokuseki}</td>
								<td class="pcTd08">${recordCardInfo.hatsureiList[5].bikou}</td>
							</tr>
							<tr>
								<td class="pcTd08">${recordCardInfo.hatsureiList[6].hatsureiKubun}</td>
								<td class="pcTd08">${recordCardInfo.hatsureiList[6].hatsureiNichi}</td>
								<td class="pcTd08">${recordCardInfo.hatsureiList[6].busho}</td>
								<td class="pcTd08">${recordCardInfo.hatsureiList[6].yakushoku}</td>
								<td class="pcTd08">${recordCardInfo.hatsureiList[6].shokuseki}</td>
								<td class="pcTd08">${recordCardInfo.hatsureiList[6].bikou}</td>
							</tr>
							<tr>
								<td class="pcTd08">${recordCardInfo.hatsureiList[7].hatsureiKubun}</td>
								<td class="pcTd08">${recordCardInfo.hatsureiList[7].hatsureiNichi}</td>
								<td class="pcTd08">${recordCardInfo.hatsureiList[7].busho}</td>
								<td class="pcTd08">${recordCardInfo.hatsureiList[7].yakushoku}</td>
								<td class="pcTd08">${recordCardInfo.hatsureiList[7].shokuseki}</td>
								<td class="pcTd08">${recordCardInfo.hatsureiList[7].bikou}</td>
							</tr>
							<tr>
								<td class="pcTd08">${recordCardInfo.hatsureiList[8].hatsureiKubun}</td>
								<td class="pcTd08">${recordCardInfo.hatsureiList[8].hatsureiNichi}</td>
								<td class="pcTd08">${recordCardInfo.hatsureiList[8].busho}</td>
								<td class="pcTd08">${recordCardInfo.hatsureiList[8].yakushoku}</td>
								<td class="pcTd08">${recordCardInfo.hatsureiList[8].shokuseki}</td>
								<td class="pcTd08">${recordCardInfo.hatsureiList[8].bikou}</td>
							</tr>
							<tr>
								<td class="pcTd08">${recordCardInfo.hatsureiList[9].hatsureiKubun}</td>
								<td class="pcTd08">${recordCardInfo.hatsureiList[9].hatsureiNichi}</td>
								<td class="pcTd08">${recordCardInfo.hatsureiList[9].busho}</td>
								<td class="pcTd08">${recordCardInfo.hatsureiList[9].yakushoku}</td>
								<td class="pcTd08">${recordCardInfo.hatsureiList[9].shokuseki}</td>
								<td class="pcTd08">${recordCardInfo.hatsureiList[9].bikou}</td>
							</tr>
						</tbody>
					</table>

					<table summary="人事記録カード" border="0" cellspacing="0" cellpadding="0"
						class="personnelCard02">
						<tbody>
							<tr>
								<th rowspan="2" class="pcSubtit ">退職<br>事項
								</th>
								<td class="pcTd08 tdColor">退職区分</td>
								<td class="pcTd08 tdColor">退職日</td>
								<td class="pcTd08 tdColor">退職事由</td>
								<td class="pcTd08 tdColor">退職金</td>
								<td class="pcTd08 tdColor">退職後の連絡先</td>
							</tr>
							<tr>
								<td class="pcTd10">${recordCardInfo.taishokuList[0].taishokuKubun}</td>
								<td class="pcTd10">${recordCardInfo.taishokuList[0].taishoku_bi}</td>
								<td class="pcTd10">${recordCardInfo.taishokuList[0].riyuu}</td>
								<td class="pcTd10">${recordCardInfo.taishokuList[0].taishoku_kin}</td>
								<td class="pcTd10">${recordCardInfo.taishokuList[0].taishokugoRenrakusaki}</td>
							</tr>
						</tbody>
					</table>

					<table summary="人事記録カード" border="0" cellspacing="0" cellpadding="0"
						class="personnelCard03">
						<tbody>
							<tr>
								<th class="pcTit03">
									<div style="position: relative;">
										<label
											style="position: absolute; top: 10px; left: 10px; font-size: 11px;"><input
											type="checkbox" id="chkCmpnCeoV" name="chkCmpnCeoV" value="1"
											checked=""> <span
											style="display: inline-block; margin-top: -7px; vertical-align: middle;">代表者
												表記</span></label>
										<div
											style="position: absolute; top: -25px; left: 330px; height: 86px; display: table;">
											<!-- width:87px; -->
											<span
												style="display: table-cell; text-align: center; vertical-align: middle;">
												<div id="disStampImgTrue" class="disHide">
											</span>
										</div>

										<div id="disStampImgFalse" class="">
											<span id="btnStampView" class="anchor"> <img
												id="disStampDefaultImg"
												src="https://img.payzon.co.kr/_commonImg/cert_stamp.gif"
												alt="会社のハンコを入れてください。" title="会社のハンコを入れてください。" height="86"
												style="width: 65px; height: 64px;">
											</span>
										</div>
										</span>
									
									</div>(株)イエスフォーム<br>&nbsp;<!-- 대표이사 --> <span id="disCmpnCeoA"
									class="disShow">代表取締役 イ·ウンヨル</span>
								</th>
							</tr>
						</tbody>
					</table>

				</div>
			</div>
		</div>

		<div class="button-container">
			<button type="button" onclick="printPage()">印刷</button>
			<button type="button" onclick="downloadExcel()">エクセル·ダウンロード</button>
		</div>

	</form>

	<script>
		
	</script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.17.0/xlsx.full.min.js"></script>

</body>
</html>
