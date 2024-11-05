package kintai.model;

import java.util.ArrayList;
import java.util.Date;
// 최동주 崔東周
public class KintaiKiroku {
	
	public KintaiKiroku() {
		super();
		// TODO Auto-generated constructor stub
	}
	private Integer Kintai_kiroku_id;
	private Integer shain_id;
	private Integer Kintai_id;
	private String Kintai_mei;
	private Date Kintai_kaishi;
	private Date Kintai_shuuryou;
	private Integer Kintai_nissuu;
	private Integer teate;
	private String tekiyou;
	private Date touroku_nengappi;
	private Date nyuuryoku_bi;
	// GetsuSyoukaiDao.java 추가
	private String kubun;
	private String namae_kana;
    private String busho_mei;
    private String yakushoku_mei;
    private String kintaiUnit;
    private ArrayList<Date> workDates;
    // SyousaiSyoukaiDao.java 추가
    private String kintai_kikan;
    // ShainKyuukaSyoukaiDao.java 추가
    private String kyuukaKoumoku;
    // KyuukaSyoukaiDao.java에서 추가
    private String kyuukaShurui;
    private Integer total_kyuuka;
    private Integer used_kyuuka;
    private Integer remaining_kyuuka;
     
    
	
	public KintaiKiroku(Integer kintai_kiroku_id, Integer shain_id, Integer kintai_id, String kintai_mei, Date kintai_kaishi,
			Date kintai_shuuryou, Integer kintai_nissuu, Integer teate, String tekiyou, Date touroku_nengappi,
			Date nyuuryoku_bi) {
		super();
		this.Kintai_kiroku_id = kintai_kiroku_id;
		this.shain_id = shain_id;
		this.Kintai_id = kintai_id;
		this.Kintai_mei = kintai_mei;
		this.Kintai_kaishi = kintai_kaishi;
		this.Kintai_shuuryou = kintai_shuuryou;
		this.Kintai_nissuu = kintai_nissuu;
		this.teate = teate;
		this.tekiyou = tekiyou;
		this.touroku_nengappi = touroku_nengappi;
		this.nyuuryoku_bi = nyuuryoku_bi;
	}
	

	public Integer getKintai_kiroku_id() {
		return Kintai_kiroku_id;
	}
	public void setKintai_kiroku_id(Integer kintai_kiroku_id) {
		Kintai_kiroku_id = kintai_kiroku_id;
	}
	public Integer getShain_id() {
		return shain_id;
	}
	public void setShain_id(Integer shain_id) {
		this.shain_id = shain_id;
	}
	public Integer getKintai_id() {
		return Kintai_id;
	}
	public void setKintai_id(Integer kintai_id) {
		Kintai_id = kintai_id;
	}
	//KintaiDao.java에서 추가
	public String getKintai_mei() {
		// TODO Auto-generated method stub
		return Kintai_mei;
	}
	public void setKintai_mei(String kintai_mei) {
		Kintai_mei = kintai_mei;
	}
	public Date getKintai_kaishi() {
		return Kintai_kaishi;
	}
	public void setKintai_kaishi(Date kintai_kaishi) {
		Kintai_kaishi = kintai_kaishi;
	}
	public Date getKintai_shuuryou() {
		return Kintai_shuuryou;
	}
	public void setKintai_shuuryou(Date kintai_shuuryou) {
		Kintai_shuuryou = kintai_shuuryou;
	}
	public Integer getKintai_nissuu() {
		return Kintai_nissuu;
	}
	public void setKintai_nissuu(Integer kintai_nissuu) {
		Kintai_nissuu = kintai_nissuu;
	}
	public Integer getTeate() {
		return teate;
	}
	public void setTeate(Integer teate) {
		this.teate = teate;
	}
	public String getTekiyou() {
		return tekiyou;
	}
	public void setTekiyou(String tekiyou) {
		this.tekiyou = tekiyou;
	}
	public Date getTouroku_nengappi() {
		return touroku_nengappi;
	}
	public void setTouroku_nengappi(Date touroku_nengappi) {
		this.touroku_nengappi = touroku_nengappi;
	}
	public Date getNyuuryoku_bi() {
		return nyuuryoku_bi;
	}
	public void setNyuuryoku_bi(Date nyuuryoku_bi) {
		this.nyuuryoku_bi = nyuuryoku_bi;
	}
	
	//ShainKintaiKirokuDao.java에서 추가
	public KintaiKiroku(Integer kintai_kiroku_id2, Date nyuuryoku_bi2, String kintai_mei2, Date kintai_kaishi2,
			Date kintai_shuuryou2, Integer kintai_nissuu2, Integer teate2, String tekiyou2) {
		// TODO Auto-generated constructor stub
		this.Kintai_kiroku_id = kintai_kiroku_id2;
		this.nyuuryoku_bi = nyuuryoku_bi2;
		this.Kintai_mei = kintai_mei2;
		this.Kintai_kaishi = kintai_kaishi2;
		this.Kintai_shuuryou = kintai_shuuryou2;
		this.Kintai_nissuu = kintai_nissuu2;
		this.teate = teate2;
		this.tekiyou = tekiyou2;
	}
	// GetsuSyoukaiDao.java 추가
	public KintaiKiroku(String kubun2, Integer shain_id2, String namae_kana2, String busho_mei2, String yakushoku_mei2,
			ArrayList<Date> workDates, String kintaiUnit) {
		// TODO Auto-generated constructor stub
		this.kubun = kubun2;
		this.shain_id = shain_id2;
		this.namae_kana = namae_kana2;
		this.busho_mei = busho_mei2;
		this.yakushoku_mei = yakushoku_mei2;
		this.workDates = (workDates != null) ? new ArrayList<>(workDates) : new ArrayList<>();
		this.kintaiUnit = kintaiUnit;
	}

	// GetsuSyoukaiDao.java 추가
	public KintaiKiroku(String kubun, Integer shain_id2, String namae_kana2, String busho_mei2, String yakushoku_mei2,
			Date kintai_kaishi2, Date kintai_shuuryou2, String kintaiUnit) {
		this.kubun = kubun;
		this.shain_id = shain_id2;
		this.namae_kana = namae_kana2;
		this.busho_mei = busho_mei2;
		this.yakushoku_mei = yakushoku_mei2;
		this.Kintai_kaishi = kintai_kaishi2;
		this.Kintai_shuuryou = kintai_shuuryou2;
		this.kintaiUnit = kintaiUnit;
	}


	// SyousaiSyoukaiDao.java 추가
	public KintaiKiroku(java.sql.Date nyuuryoku_bi2, String kintai_mei2, String namae_kana2, String busho_mei2, String yakushoku_mei2,
			String kubun2, String kintai_kikan, Integer kintai_nissuu2, Integer teate2, String tekiyou2) {
		this.nyuuryoku_bi = nyuuryoku_bi2;
		this.Kintai_mei = kintai_mei2;
		this.namae_kana = namae_kana2;
		this.busho_mei = busho_mei2;
		this.yakushoku_mei = yakushoku_mei2;
		this.kubun = kubun2;
		this.kintai_kikan = kintai_kikan;
		this.Kintai_nissuu = kintai_nissuu2;
		this.teate = teate2;
		this.tekiyou = tekiyou2;
	}

	public KintaiKiroku(String namae_kana2, String kyuukaKoumoku, Integer shain_id2, Date nyuuryoku_bi2, String kintai_kikan2,
					String tekiyou2, Integer kintai_nissuu2) {
		this.namae_kana = namae_kana2;
		this.kyuukaKoumoku = kyuukaKoumoku;
		this.shain_id = shain_id2;
		this.nyuuryoku_bi = nyuuryoku_bi2;
		this.kintai_kikan = kintai_kikan2;
		this.tekiyou = tekiyou2;
		this.Kintai_nissuu = kintai_nissuu2;
	}

	// KyuukaSyoukaiDao.java에서 추가
	public KintaiKiroku(String kubun2, Integer shain_id2, String namae_kana2, String busho_mei2, String yakushoku_mei2,
			String kyuukaShurui, Integer total_kyuuka, Integer used_kyuuka, Integer remaining_kyuuka) {
		this.kubun = kubun2;
		this.shain_id = shain_id2;
		this.namae_kana = namae_kana2;
		this.busho_mei = busho_mei2;
		this.yakushoku_mei = yakushoku_mei2;
		this.kyuukaShurui = kyuukaShurui;
		this.total_kyuuka = total_kyuuka;
		this.used_kyuuka = used_kyuuka;
		this.remaining_kyuuka = remaining_kyuuka;
	}






	// 추가한 부분 게터세터
	public String getKubun() {
		return kubun;
	}
	public void setKubun(String kubun) {
		this.kubun = kubun;
	}
	public String getNamae_kana() {
		return namae_kana;
	}
	public void setNamae_kana(String namae_kana) {
		this.namae_kana = namae_kana;
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
	public String getKintai_kikan() {
		return kintai_kikan;
	}
	public void setKintai_kikan(String kintai_kikan) {
		this.kintai_kikan = kintai_kikan;
	}
	public String getKyuukaKoumoku() {
		return kyuukaKoumoku;
	}
	public void setKyuukaKoumoku(String kyuukaKoumoku) {
		this.kyuukaKoumoku = kyuukaKoumoku;
	}


	public String getKyuukaShurui() {
		return kyuukaShurui;
	}
	public void setKyuukaShurui(String kyuukaShurui) {
		this.kyuukaShurui = kyuukaShurui;
	}
	public Integer getTotal_kyuuka() {
		return total_kyuuka;
	}
	public void setTotal_kyuuka(Integer total_kyuuka) {
		this.total_kyuuka = total_kyuuka;
	}
	public Integer getUsed_kyuuka() {
		return used_kyuuka;
	}
	public void setUsed_kyuuka(Integer used_kyuuka) {
		this.used_kyuuka = used_kyuuka;
	}
	public Integer getRemaining_kyuuka() {
		return remaining_kyuuka;
	}
	public void setRemaining_kyuuka(Integer remaining_kyuuka) {
		this.remaining_kyuuka = remaining_kyuuka;
	}


	public String getKintaiUnit() {
		return kintaiUnit;
	}
	public void setKintaiUnit(String kintaiUnit) {
		this.kintaiUnit = kintaiUnit;
	}


	public ArrayList<Date> getWorkDates() {
		return workDates;
	}
	public void setWorkDates(ArrayList<Date> workDates) {
		this.workDates = workDates;
	}

		

}








