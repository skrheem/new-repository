package JinjiKanri.model;

import java.sql.Date;

import oracle.sql.DATE;

public class YondaihokenJouhou {

	private Integer shain_id;
	private String hokenKubun;
	private String kigouBangou;
	private Date shutoku_bi;
	private Date soshitsu_bi;
	public YondaihokenJouhou(Integer shain_id, String hokenKubun, String kigouBangou, Date shutoku_bi, Date soshitsu_bi) {
		super();
		this.shain_id = shain_id;
		this.hokenKubun = hokenKubun;
		this.kigouBangou = kigouBangou;
		this.shutoku_bi = shutoku_bi;
		this.soshitsu_bi = soshitsu_bi;
	}
	
	// 김현서 金賢徐
    // p.2 인사기록카드_ 특정 사원의 사대보험 정보를 조회하는 메소드
	// p.2 人事記録カード_特定社員の四大保険情報を照会するメソッド
	public YondaihokenJouhou(String kigouBangou, Date shutoku_bi, Date soshitsu_bi, String hokenKubun) {
		this.kigouBangou = kigouBangou;
		this.shutoku_bi = shutoku_bi;
		this.soshitsu_bi = soshitsu_bi;
		this.hokenKubun = hokenKubun;
		
	}
	public Integer getShain_id() {
		return shain_id;
	}
	public void setShain_id(Integer shain_id) {
		this.shain_id = shain_id;
	}
	public String getHokenKubun() {
		return hokenKubun;
	}
	public void setHokenKubun(String hokenKubun) {
		this.hokenKubun = hokenKubun;
	}
	public String getKigouBangou() {
		return kigouBangou;
	}
	public void setKigouBangou(String kigouBangou) {
		this.kigouBangou = kigouBangou;
	}
	public Date getShutoku_bi() {
		return shutoku_bi;
	}
	public void setShutoku_bi(Date shutoku_bi) {
		this.shutoku_bi = shutoku_bi;
	}
	public Date getSoshitsu_bi() {
		return soshitsu_bi;
	}
	public void setSoshitsu_bi(Date soshitsu_bi) {
		this.soshitsu_bi = soshitsu_bi;
	}

	
	
}
