package kintai.model;

import java.math.BigDecimal;
import java.util.Date;

public class HiyatoiKinmuKiroku {
	private Integer kinmuKiroku_id;
	private Integer shain_id;
	private Integer genba_project_id;
	private Date kinmu_nengappi;
	private BigDecimal shiharai_ritsu;
	private BigDecimal sougaku;
	private BigDecimal shotokuzei;
	private BigDecimal chihozei;
	private BigDecimal jissai_shikyuu;
	private Date touroku_nengappi;
	// HiyatoiKirokuDao.java 추가
	private String kintai_kaishi;
	private String kintai_shuuryou;
	private String genba_project_mei;
	private Integer kintai_nissuu;
	private Integer teate;
	private BigDecimal shikyuu_gaku;
	// HiyatoiSyousaiDao.java 추가
	private String namae_kana;
	private String busho_mei;
	// HiyatoiSyousaiDao.java 추가
	private String kubun;
	private String yakushoku_mei;
	private Integer total_days;
	private BigDecimal total_shotokuzei;
	private BigDecimal total_chihozei;
	private BigDecimal total_jissai_shikyuu;
	
	public HiyatoiKinmuKiroku(Integer kinmuKiroku_id, Integer shain_id, Integer genba_project_id, Date kinmu_nengappi,
			BigDecimal shiharai_ritsu, BigDecimal sougaku, BigDecimal shotokuzei, BigDecimal chihozei,
			BigDecimal jissai_shikyuu, Date touroku_nengappi) {
		super();
		this.kinmuKiroku_id = kinmuKiroku_id;
		this.shain_id = shain_id;
		this.genba_project_id = genba_project_id;
		this.kinmu_nengappi = kinmu_nengappi;
		this.shiharai_ritsu = shiharai_ritsu;
		this.sougaku = sougaku;
		this.shotokuzei = shotokuzei;
		this.chihozei = chihozei;
		this.jissai_shikyuu = jissai_shikyuu;
		this.touroku_nengappi = touroku_nengappi;
	}
	public Integer getKinmuKiroku_id() {
		return kinmuKiroku_id;
	}
	public void setKinmuKiroku_id(Integer kinmuKiroku_id) {
		this.kinmuKiroku_id = kinmuKiroku_id;
	}
	public Integer getShain_id() {
		return shain_id;
	}
	public void setShain_id(Integer shain_id) {
		this.shain_id = shain_id;
	}
	public Integer getGenba_project_id() {
		return genba_project_id;
	}
	public void setGenba_project_id(Integer genba_project_id) {
		this.genba_project_id = genba_project_id;
	}
	public Date getKinmu_nengappi() {
		return kinmu_nengappi;
	}
	public void setKinmu_nengappi(Date kinmu_nengappi) {
		this.kinmu_nengappi = kinmu_nengappi;
	}
	public BigDecimal getShiharai_ritsu() {
		return shiharai_ritsu;
	}
	public void setShiharai_ritsu(BigDecimal shiharai_ritsu) {
		this.shiharai_ritsu = shiharai_ritsu;
	}
	public BigDecimal getSougaku() {
		return sougaku;
	}
	public void setSougaku(BigDecimal sougaku) {
		this.sougaku = sougaku;
	}
	public BigDecimal getShotokuzei() {
		return shotokuzei;
	}
	public void setShotokuzei(BigDecimal shotokuzei) {
		this.shotokuzei = shotokuzei;
	}
	public BigDecimal getChihozei() {
		return chihozei;
	}
	public void setChihozei(BigDecimal chihozei) {
		this.chihozei = chihozei;
	}
	public BigDecimal getJissai_shikyuu() {
		return jissai_shikyuu;
	}
	public void setJissai_shikyuu(BigDecimal jissai_shikyuu) {
		this.jissai_shikyuu = jissai_shikyuu;
	}
	public Date getTouroku_nengappi() {
		return touroku_nengappi;
	}
	public void setTouroku_nengappi(Date touroku_nengappi) {
		this.touroku_nengappi = touroku_nengappi;
	}
	
	
	// HiyatoiKirokuDao.java 추가
	public HiyatoiKinmuKiroku(Date kinmu_nengappi2, String genba_project_mei,
			Integer teate, BigDecimal shiharai_ritsu2, BigDecimal shotokuzei2,
			BigDecimal chihozei2, BigDecimal shikyuu_gaku, BigDecimal jissai_shikyuu2) {
		this.kinmu_nengappi = kinmu_nengappi2;
		this.genba_project_mei = genba_project_mei;
		this.teate = teate;
		this.shiharai_ritsu = shiharai_ritsu2;
		this.shotokuzei = shotokuzei2;
		this.chihozei = chihozei2;
		this.shikyuu_gaku = shikyuu_gaku;
		this.jissai_shikyuu = jissai_shikyuu2;
	}
		
	
		// HiyatoiSyousaiDao.java 추가
		public HiyatoiKinmuKiroku(java.sql.Date kinmu_nengappi2, Integer shain_id2, String namae_kana, String busho_mei,
				String genba_project_mei2, Integer teate2, BigDecimal shiharai_ritsu2, BigDecimal shotokuzei2,
				BigDecimal chihozei2, BigDecimal jissai_shikyuu2) {
			this.kinmu_nengappi = kinmu_nengappi2;	
			this.shain_id = shain_id2;
			this.namae_kana = namae_kana;
			this.busho_mei = busho_mei;
			this.genba_project_mei = genba_project_mei2;
			this.teate = teate2;
			this.shiharai_ritsu = shiharai_ritsu2;
			this.shotokuzei = shotokuzei2;
			this.chihozei = chihozei2;
			this.jissai_shikyuu = jissai_shikyuu2;
		}
		
		
		// HiyatoiGetsuDao.java 추가
		public HiyatoiKinmuKiroku(String kubun, Integer shain_id2, String namae_kana2, String busho_mei2,
				String yakushoku_mei, Integer total_days, BigDecimal total_shotokuzei, BigDecimal total_chihozei,
				BigDecimal total_jissai_shikyuu) {
			this.kubun = kubun;
			this.shain_id = shain_id2;
			this.namae_kana = namae_kana2;
			this.busho_mei = busho_mei2;
			this.yakushoku_mei = yakushoku_mei;
			this.total_days = total_days;
			this.total_shotokuzei = total_shotokuzei;
			this.total_chihozei = total_chihozei;
			this.total_jissai_shikyuu = total_jissai_shikyuu;
		}
		public HiyatoiKinmuKiroku(String kinmu_nengappi2, String genba_project_mei2, Integer kintai_nissuu2, Integer teate2,
				BigDecimal shiharai_ritsu2, BigDecimal shotokuzei2, BigDecimal chihozei2, BigDecimal shikyuu_gaku2,
				BigDecimal jissai_shikyuu2) {
			// TODO Auto-generated constructor stub
		}
		
		
		
		
		
	// 추가 게터세터
	public HiyatoiKinmuKiroku() {
		// TODO Auto-generated constructor stub
	}
	public String getKintai_kaishi() {
		return kintai_kaishi;
	}
	public void setKintai_kaishi(String kintai_kaishi) {
		this.kintai_kaishi = kintai_kaishi;
	}
	public String getKintai_shuuryou() {
		return kintai_shuuryou;
	}
	public void setKintai_shuuryou(String kintai_shuuryou) {
		this.kintai_shuuryou = kintai_shuuryou;
	}
	public String getGenba_project_mei() {
		return genba_project_mei;
	}
	public void setGenba_project_mei(String genba_project_mei) {
		this.genba_project_mei = genba_project_mei;
	}
	public Integer getKintai_nissuu() {
		return kintai_nissuu;
	}
	public void setKintai_nissuu(Integer kintai_nissuu) {
		this.kintai_nissuu = kintai_nissuu;
	}
	public Integer getTeate() {
		return teate;
	}
	public void setTeate(Integer teate) {
		this.teate = teate;
	}
	public BigDecimal getShikyuu_gaku() {
		return shikyuu_gaku;
	}
	public void setShikyuu_gaku(BigDecimal shikyuu_gaku) {
		this.shikyuu_gaku = shikyuu_gaku;
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
	public String getKubun() {
		return kubun;
	}
	public void setKubun(String kubun) {
		this.kubun = kubun;
	}
	public String getYakushoku_mei() {
		return yakushoku_mei;
	}
	public void setYakushoku_mei(String yakushoku_mei) {
		this.yakushoku_mei = yakushoku_mei;
	}
	public Integer getTotal_days() {
		return total_days;
	}
	public void setTotal_days(Integer total_days) {
		this.total_days = total_days;
	}
	public BigDecimal getTotal_shotokuzei() {
		return total_shotokuzei;
	}
	public void setTotal_shotokuzei(BigDecimal total_shotokuzei) {
		this.total_shotokuzei = total_shotokuzei;
	}
	public BigDecimal getTotal_chihozei() {
		return total_chihozei;
	}
	public void setTotal_chihozei(BigDecimal total_chihozei) {
		this.total_chihozei = total_chihozei;
	}
	public BigDecimal getTotal_jissai_shikyuu() {
		return total_jissai_shikyuu;
	}
	public void setTotal_jissai_shikyuu(BigDecimal total_jissai_shikyuu) {
		this.total_jissai_shikyuu = total_jissai_shikyuu;
	}

}
