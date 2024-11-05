package JinjiKanri.model;

import java.sql.Date;

import oracle.sql.DATE;

public class Gakureki {

	private Integer gakureki_id;
	private Integer shain_id;
	private String gakurekiKubun;
	private Date nyugakuNengatsu;
	private Date sotsugyoNengatsu;
	private String gakkoNama;
	private String senmon;
	private String shuuryouJyoutai;
	public Gakureki(Integer gakureki_id, Integer shain_id, String gakurekiKubun, Date nyugakuNengatsu, Date sotsugyoNengatsu,
			String gakkoNama, String senmon, String shuuryouJyoutai) {
		super();
		this.gakureki_id = gakureki_id;
		this.shain_id = shain_id;
		this.gakurekiKubun = gakurekiKubun;
		this.nyugakuNengatsu = nyugakuNengatsu;
		this.sotsugyoNengatsu = sotsugyoNengatsu;
		this.gakkoNama = gakkoNama;
		this.senmon = senmon;
		this.shuuryouJyoutai = shuuryouJyoutai;
	}

	
	// 김현서 金賢徐
    // p.2 인사기록카드_ 특정 사원의 학력 정보를 조회하는 메소드
	// p.2 人事記録カード_特定社員の学歴情報を照会するメソッド
	public Gakureki(String gakkoNama, Date nyugakuNengatsu, Date sotsugyoNengatsu, String senmon, String shuuryouJyoutai) {
	    this.gakkoNama = gakkoNama;
	    this.nyugakuNengatsu = nyugakuNengatsu;
	    this.sotsugyoNengatsu = sotsugyoNengatsu;
	    this.senmon = senmon;
	    this.shuuryouJyoutai = shuuryouJyoutai;
	}

	
	public Integer getGakureki_id() {
		return gakureki_id;
	}
	public void setGakureki_id(Integer gakureki_id) {
		this.gakureki_id = gakureki_id;
	}
	public Integer getShain_id() {
		return shain_id;
	}
	public void setShain_id(Integer shain_id) {
		this.shain_id = shain_id;
	}
	public String getGakurekiKubun() {
		return gakurekiKubun;
	}
	public void setGakurekiKubun(String gakurekiKubun) {
		this.gakurekiKubun = gakurekiKubun;
	}
	
	
	public Date getNyugakuNengatsu() {
		return nyugakuNengatsu;
	}

	public void setNyugakuNengatsu(Date nyugakuNengatsu) {
		this.nyugakuNengatsu = nyugakuNengatsu;
	}

	public Date getSotsugyoNengatsu() {
		return sotsugyoNengatsu;
	}

	public void setSotsugyoNengatsu(Date sotsugyoNengatsu) {
		this.sotsugyoNengatsu = sotsugyoNengatsu;
	}

	public void setGakkoNama(String gakkoNama) {
		this.gakkoNama = gakkoNama;
	}

	public String getGakkoNama() {
		return gakkoNama;
	}
	public void setGakkoName(String gakkoNama) {
		this.gakkoNama = gakkoNama;
	}
	public String getSenmon() {
		return senmon;
	}
	public void setSenmon(String senmon) {
		this.senmon = senmon;
	}
	public String getShuuryouJyoutai() {
		return shuuryouJyoutai;
	}
	public void setShuuryouJyoutai(String shuuryouJyoutai) {
		this.shuuryouJyoutai = shuuryouJyoutai;
	}
	
	
}
