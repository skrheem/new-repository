package JinjiKanri.model;

import java.sql.Date;

import oracle.sql.DATE;

public class Heieki {
	
	private Integer heieki_id;
	private Integer shain_id;
	private String heiekiKubun;
	private String gunBetsu;
	private Date fukumukiKaishi;
	private Date fukumukiShuuryou;
	private String Kaikyuu;
	private String heika;
	private String mirikouRiyuuCode;
	public Heieki(Integer heieki_id, Integer shain_id, String heiekiKubun, String gunBetsu, Date fukumukiKaishi,
			Date fukumukiShuuryou, String kaikyuu, String heika, String mirikouRiyuuCode) {
		super();
		this.heieki_id = heieki_id;
		this.shain_id = shain_id;
		this.heiekiKubun = heiekiKubun;
		this.gunBetsu = gunBetsu;
		this.fukumukiKaishi = fukumukiKaishi;
		this.fukumukiShuuryou = fukumukiShuuryou;
		Kaikyuu = kaikyuu;
		this.heika = heika;
		this.mirikouRiyuuCode = mirikouRiyuuCode;
	}

	// 김현서 金賢徐
    // p.2 인사기록카드_ 특정 사원의 병역 정보를 조회하는 메소드의 매개변수
	// p.2 人事記録カード_特定社員の兵役情報を照会するメソッドの媒介変数
    public Heieki(String heiekiKubun, String mirikouRiyuuCode, String gunBetsu, String Kaikyuu, String heika, Date fukumukiKaishi, Date fukumukiShuuryou) {
        this.heiekiKubun = heiekiKubun;
        this.mirikouRiyuuCode = mirikouRiyuuCode;
        this.gunBetsu = gunBetsu;
        this.Kaikyuu = Kaikyuu;
        this.heika = heika;
        this.fukumukiKaishi = fukumukiKaishi;
        this.fukumukiShuuryou = fukumukiShuuryou;
    }
	
	
	
	public Integer getHeieki_id() {
		return heieki_id;
	}
	public void setHeieki_id(Integer heieki_id) {
		this.heieki_id = heieki_id;
	}
	public Integer getShain_id() {
		return shain_id;
	}
	public void setShain_id(Integer shain_id) {
		this.shain_id = shain_id;
	}
	public String getHeiekiKubun() {
		return heiekiKubun;
	}
	public void setHeiekiKubun(String heiekiKubun) {
		this.heiekiKubun = heiekiKubun;
	}
	public String getGunBetsu() {
		return gunBetsu;
	}
	public void setGunBetsu(String gunBetsu) {
		this.gunBetsu = gunBetsu;
	}

	
	public Date getFukumukiKaishi() {
		return fukumukiKaishi;
	}

	public void setFukumukiKaishi(Date fukumukiKaishi) {
		this.fukumukiKaishi = fukumukiKaishi;
	}

	public Date getFukumukiShuuryou() {
		return fukumukiShuuryou;
	}

	public void setFukumukiShuuryou(Date fukumukiShuuryou) {
		this.fukumukiShuuryou = fukumukiShuuryou;
	}

	public String getKaikyuu() {
		return Kaikyuu;
	}
	public void setKaikyuu(String kaikyuu) {
		Kaikyuu = kaikyuu;
	}
	public String getHeika() {
		return heika;
	}
	public void setHeika(String heika) {
		this.heika = heika;
	}
	public String getMirikouRiyuuCode() {
		return mirikouRiyuuCode;
	}
	public void setMirikouRiyuuCode(String mirikouRiyuuCode) {
		this.mirikouRiyuuCode = mirikouRiyuuCode;
	}
	
	

}
