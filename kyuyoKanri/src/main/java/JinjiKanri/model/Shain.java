package JinjiKanri.model;

import java.util.Date;

public class Shain {

	private Integer shain_id;
	private String namae_kana;
	private String namae_eigo;
	private Date nyuusha_nengappi;
	private Date taisha_nengappi;
	private Integer busho_id;
	private Integer yakushoku_id;
	private String kokuseki;
	private String jumin_bangou;
	private String juusho;
	private String denwa_uchi;
	private String denwa_keitai;
	private String meeru;
	private String bikou;
	private String kubun;
	private Date tanjyoubi;
	private String jyoutai;
	private String SNS;
	private String kouza_bangou;
	private String busho_mei;
	private String yakushoku_mei;
	private String shain_image_keiro;
	private String yearsOfService;

	public Integer getShain_id() {
		return shain_id;
	}

	public void setShain_id(Integer shain_id) {
		this.shain_id = shain_id;
	}

	public String getNamae_kana() {
		return namae_kana;
	}

	public void setNamae_kana(String namae_kana) {
		this.namae_kana = namae_kana;
	}

	public String getNamae_eigo() {
		return namae_eigo;
	}

	public void setNamae_eigo(String namae_eigo) {
		this.namae_eigo = namae_eigo;
	}

	public Date getNyuusha_nengappi() {
		return nyuusha_nengappi;
	}

	public void setNyuusha_nengappi(Date nyuusha_nengappi) {
		this.nyuusha_nengappi = nyuusha_nengappi;
	}

	public Date getTaisha_nengappi() {
		return taisha_nengappi;
	}

	public void setTaisha_nengappi(Date taisha_nengappi) {
		this.taisha_nengappi = taisha_nengappi;
	}

	public Integer getBusho_id() {
		return busho_id;
	}

	public void setBusho_id(Integer busho_id) {
		this.busho_id = busho_id;
	}

	public Integer getYakushoku_id() {
		return yakushoku_id;
	}

	public void setYakushoku_id(Integer yakushoku_id) {
		this.yakushoku_id = yakushoku_id;
	}

	public String getKokuseki() {
		return kokuseki;
	}

	public void setKokuseki(String kokuseki) {
		this.kokuseki = kokuseki;
	}

	public String getJumin_bangou() {
		return jumin_bangou;
	}

	public void setJumin_bangou(String jumin_bangou) {
		this.jumin_bangou = jumin_bangou;
	}

	public String getJuusho() {
		return juusho;
	}

	public void setJuusho(String juusho) {
		this.juusho = juusho;
	}

	public String getDenwa_uchi() {
		return denwa_uchi;
	}

	public void setDenwa_uchi(String denwa_uchi) {
		this.denwa_uchi = denwa_uchi;
	}

	public String getDenwa_keitai() {
		return denwa_keitai;
	}

	public void setDenwa_keitai(String denwa_keitai) {
		this.denwa_keitai = denwa_keitai;
	}

	public String getMeeru() {
		return meeru;
	}

	public void setMeeru(String meeru) {
		this.meeru = meeru;
	}

	public String getBikou() {
		return bikou;
	}

	public void setBikou(String bikou) {
		this.bikou = bikou;
	}

	public String getKubun() {
		return kubun;
	}

	public void setKubun(String kubun) {
		this.kubun = kubun;
	}

	public Date getTanjyoubi() {
		return tanjyoubi;
	}

	public void setTanjyoubi(Date tanjyoubi) {
		this.tanjyoubi = tanjyoubi;
	}

	public String getJyoutai() {
		return jyoutai;
	}

	public void setJyoutai(String jyoutai) {
		this.jyoutai = jyoutai;
	}

	public String getSNS() {
		return SNS;
	}

	public void setSNS(String SNS) {
		this.SNS = SNS;
	}

	// 김현서 金賢徐
	// p.1 사원관리 전체 사원 리스트
	// p.1 社員管理 全社員リスト
	public Shain(Integer shain_id, String namae_kana, String namae_eigo, Date nyuusha_nengappi, Date taisha_nengappi,
			String busho_mei, String yakushoku_mei, String kokuseki, String jumin_bangou, String juusho,
			String denwa_uchi, String denwa_keitai, String meeru, String SNS, String kubun, String jyoutai,
			String kouza_bangou) {
		this.shain_id = shain_id;
		this.namae_kana = namae_kana;
		this.namae_eigo = namae_eigo;
		this.nyuusha_nengappi = nyuusha_nengappi;
		this.taisha_nengappi = taisha_nengappi;
		this.busho_mei = busho_mei;
		this.yakushoku_mei = yakushoku_mei;
		this.kokuseki = kokuseki;
		this.jumin_bangou = jumin_bangou;
		this.juusho = juusho;
		this.denwa_uchi = denwa_uchi;
		this.denwa_keitai = denwa_keitai;
		this.meeru = meeru;
		this.SNS = SNS;
		this.kubun = kubun;
		this.jyoutai = jyoutai;
		this.kouza_bangou = kouza_bangou;
	}

	// 김현서 金賢徐
	// 인사관리 p.1 사원정보 미리보기 팝업 리스트
	// 人事管理p.1社員情報プレビューポップアップリスト
	public Shain(String shain_image_keiro, String namae_kana, Date nyuusha_nengappi, String juusho) {
		this.shain_image_keiro = shain_image_keiro;
		this.namae_kana = namae_kana;
		this.nyuusha_nengappi = nyuusha_nengappi;
		this.juusho = juusho;
	}

	// 김현서 金賢徐
	// p.2 인사기록카드 사원 선택 리스트
	// p.2 人事記録カード　社員選択リスト
	public Shain(String namae_kana, Integer shain_id, String kubun, String busho_mei, String yakushoku_mei,
			String jyoutai) {
		this.shain_id = shain_id;
		this.namae_kana = namae_kana;
		this.kubun = kubun;
		this.busho_mei = busho_mei;
		this.yakushoku_mei = yakushoku_mei;
		this.jyoutai = jyoutai;

	}

	// 김현서 金賢徐
	// p.2 인사기록카드 사원 기본정보
	// p.2 人事記録カード 社員基本情報
	public Shain(Integer shain_id, java.util.Date nyuusha_nengappi, java.util.Date taisha_nengappi, String namae_kana,
			String namae_eigo, String jumin_bangou, String kubun, String juusho, String denwa_uchi, String denwa_keitai,
			String meeru) {
		this.shain_id = shain_id;
		this.nyuusha_nengappi = nyuusha_nengappi;
		this.taisha_nengappi = taisha_nengappi;
		this.namae_kana = namae_kana;
		this.namae_eigo = namae_eigo;
		this.jumin_bangou = jumin_bangou;
		this.kubun = kubun;
		this.juusho = juusho;
		this.denwa_uchi = denwa_uchi;
		this.denwa_keitai = denwa_keitai;
		this.meeru = meeru;
	}

	// 김현서 金賢徐
	// 제 증명서 발급_ 사원선택 리스트
	// 私の証明書発行_社員選択リスト
	public Shain(String kubun, String namae_kana, String busho_mei, String yakushoku_mei, String jyoutai) {
		this.kubun = kubun;
		this.namae_kana = namae_kana;
		this.busho_mei = busho_mei;
		this.yakushoku_mei = yakushoku_mei;
		this.jyoutai = jyoutai;
	}

	// 김현서 金賢徐
	// 제증명서발급_인적사항 (재직, 경력, 퇴직 증명서 공통)
	// 証明書＿人的事項 （在職、経歴、退職証明書共通）
	public Shain(String namae_kana, String jumin_bangou, String juusho) {
		this.namae_kana = namae_kana;
		this.jumin_bangou = jumin_bangou;
		this.juusho = juusho;
	}

	// 김현서 金賢徐
	// 부서, 직위, 입사일, 근속기간 출력 / 재직증명서에서 사용
	// 部署、職位、入社日、勤続期間の出力/在職証明書で使用
	public Shain(String busho_mei, String yakushoku_mei, Date nyuusha_nengappi) {
		this.busho_mei = busho_mei;
		this.yakushoku_mei = yakushoku_mei;
		this.nyuusha_nengappi = nyuusha_nengappi;
	}

	// 김현서 金賢徐
	// 근무기간, 근무부서, 직위 출력 / 퇴직증명서에서 사용
	// 勤務期間、勤務部署、職位の出力/退職証明書で使用
	public Shain(Date nyuusha_nengappi, Date taisha_nengappi, String busho_mei, String yakushoku_mei) {
		this.nyuusha_nengappi = nyuusha_nengappi;
		this.taisha_nengappi = taisha_nengappi;
		this.busho_mei = busho_mei;
		this.yakushoku_mei = yakushoku_mei;
	}

	// 김현서 金賢徐
	// p.4 구분, 이름, 부서, 직위 출력 / 제 증명서 발급대장, 증명서정보 리스트와 함께 사용
	// p.4 区分、氏名、部署、職位出力/諸証明書発行台帳、証明書情報リストと一緒に使用
	public Shain(String kubun, String namae_kana, String busho_mei, String yakushoku_mei) {
		this.kubun = kubun;
		this.namae_kana = namae_kana;
		this.busho_mei = busho_mei;
		this.yakushoku_mei = yakushoku_mei;
	}

	public String getKouza_bangou() {
		return kouza_bangou;
	}

	public void setKouza_bangou(String kouza_bangou) {
		this.kouza_bangou = kouza_bangou;
	}

	public String getBusho_mei() {
		return busho_mei;
	}

	public void setBusho_mei(String busho_mei) {
		this.busho_mei = busho_mei;
	}

	public String getYakushoku_mei() {
		return yakushoku_mei;
	}

	public void setYakushoku_mei(String yakushoku_mei) {
		this.yakushoku_mei = yakushoku_mei;
	}

	public String getShain_image_keiro() {
		return shain_image_keiro;
	}

	public void setShain_image_keiro(String shain_image_keiro) {
		this.shain_image_keiro = shain_image_keiro;
	}

	public void setYearsOfService(String yearsOfService) {
		this.yearsOfService = yearsOfService;
	}

	public String getYearsOfService() {
		return yearsOfService;
	}

}