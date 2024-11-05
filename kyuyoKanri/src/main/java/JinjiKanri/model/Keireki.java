package JinjiKanri.model;

import java.sql.Date;

import oracle.sql.DATE;

public class Keireki {

	private Integer keireki_id;
	private Integer shain_id;
	private String kaishaNama;
	private Date nyusha_bi;
	private Date taisha_bi;
	private String kinmuKikan;
	private String saigoShokui;
	private String tantouShigoto;
	private String taishoRiyuuCode;
	public Keireki(Integer keireki_id, Integer shain_id, String kaishaNama, Date nyusha_bi, Date taisha_bi, String kinmuKikan,
			String saigoShokui, String tantouShigoto, String taishoRiyuuCode) {
		super();
		this.keireki_id = keireki_id;
		this.shain_id = shain_id;
		this.kaishaNama = kaishaNama;
		this.nyusha_bi = nyusha_bi;
		this.taisha_bi = taisha_bi;
		this.kinmuKikan = kinmuKikan;
		this.saigoShokui = saigoShokui;
		this.tantouShigoto = tantouShigoto;
		this.taishoRiyuuCode = taishoRiyuuCode;
	}

	// 김현서 金現徐
    // p.2 인사기록카드 특정 사원의 경력 정보를 조회하는 메소드
	// p.2 人事記録カード_特定社員の経歴情報を照会するメソッド
    public Keireki(String kaishaNama, Date nyusha_bi, Date taisha_bi, String saigoShokui, String tantouShigoto) {
        this.kaishaNama = kaishaNama;
        this.nyusha_bi = nyusha_bi;
        this.taisha_bi = taisha_bi;
        this.saigoShokui = saigoShokui;
        this.tantouShigoto = tantouShigoto;
    }
	
	public Integer getKeireki_id() {
		return keireki_id;
	}
	public void setKeireki_id(Integer keireki_id) {
		this.keireki_id = keireki_id;
	}
	public Integer getShain_id() {
		return shain_id;
	}
	public void setShain_id(Integer shain_id) {
		this.shain_id = shain_id;
	}
	public String getKaishaNama() {
		return kaishaNama;
	}
	public void setKaishaNama(String kaishaNama) {
		this.kaishaNama = kaishaNama;
	}

	
	public Date getNyusha_bi() {
		return nyusha_bi;
	}

	public void setNyusha_bi(Date nyusha_bi) {
		this.nyusha_bi = nyusha_bi;
	}

	public Date getTaisha_bi() {
		return taisha_bi;
	}

	public void setTaisha_bi(Date taisha_bi) {
		this.taisha_bi = taisha_bi;
	}

	public String getKinmuKikan() {
		return kinmuKikan;
	}
	public void setKinmuKikan(String kinmuKikan) {
		this.kinmuKikan = kinmuKikan;
	}
	public String getSaigoShokui() {
		return saigoShokui;
	}
	public void setSaigoShokui(String saigoShokui) {
		this.saigoShokui = saigoShokui;
	}
	public String getTantouShigoto() {
		return tantouShigoto;
	}
	public void setTantouShigoto(String tantouShigoto) {
		this.tantouShigoto = tantouShigoto;
	}
	public String getTaishoRiyuuCode() {
		return taishoRiyuuCode;
	}
	public void setTaishoRiyuuCode(String taishoRiyuuCode) {
		this.taishoRiyuuCode = taishoRiyuuCode;
	}
	
	
}
