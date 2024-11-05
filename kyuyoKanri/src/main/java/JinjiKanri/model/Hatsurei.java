package JinjiKanri.model;

import java.util.Date;

public class Hatsurei {

	private Integer hatsurei_id;
	private Integer shain_id;
	private String hatsureiKubun;
	private Date hatsureiNichi;
	private String busho;
	private String yakushoku;
	private String shokuseki;
	private String bikou;

	public Hatsurei(Integer hatsurei_id, Integer shain_id, String hatsureiKubun, Date hatsureiNichi, String busho,
			String yakushoku, String shokuseki, String bikou) {
		super();
		this.hatsurei_id = hatsurei_id;
		this.shain_id = shain_id;
		this.hatsureiKubun = hatsureiKubun;
		this.hatsureiNichi = hatsureiNichi;
		this.busho = busho;
		this.yakushoku = yakushoku;
		this.shokuseki = shokuseki;
		this.bikou = bikou;
	}

	
	// 김현서 金現徐
    // p.2 인사기록카드 특정 사원의 발령 정보를 조회하는 메소드
	// p.2 人事記録カード_特定社員の発令情報を照会するメソッド
    public Hatsurei(String hatsureiKubun, Date hatsureiNichi, String busho, String yakushoku, String shokuseki, String bikou) {
        this.hatsureiKubun = hatsureiKubun;
        this.hatsureiNichi = hatsureiNichi;
        this.busho = busho;
        this.yakushoku = yakushoku;
        this.shokuseki = shokuseki;
        this.bikou = bikou;
    }

	public Integer getHatsurei_id() {
		return hatsurei_id;
	}

	public void setHatsurei_id(Integer hatsurei_id) {
		this.hatsurei_id = hatsurei_id;
	}

	public Integer getShain_id() {
		return shain_id;
	}

	public void setShain_id(Integer shain_id) {
		this.shain_id = shain_id;
	}

	public String getHatsureiKubun() {
		return hatsureiKubun;
	}

	public void setHatsureiKubun(String hatsureiKubun) {
		this.hatsureiKubun = hatsureiKubun;
	}

	public Date getHatsureiNichi() {
		return hatsureiNichi;
	}

	public void setHatsureiNichi(Date hatsureiNichi) {
		this.hatsureiNichi = hatsureiNichi;
	}

	public String getBusho() {
		return busho;
	}

	public void setBusho(String busho) {
		this.busho = busho;
	}

	public String getYakushoku() {
		return yakushoku;
	}

	public void setYakushoku(String yakushoku) {
		this.yakushoku = yakushoku;
	}

	public String getShokuseki() {
		return shokuseki;
	}

	public void setShokuseki(String shokuseki) {
		this.shokuseki = shokuseki;
	}

	public String getBikou() {
		return bikou;
	}

	public void setBikou(String bikou) {
		this.bikou = bikou;
	}

}
