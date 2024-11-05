package JinjiKanri.model;

import java.util.Date;

public class ShoumeishoJouhou {
	
	private Integer hakkouBangou;
	private Integer shain_id;
	private String kubun;
	private Date hakkou_bi;
	private String hakkouYouto;
	public ShoumeishoJouhou(Integer hakkouBangou, Integer shain_id, String kubun, Date hakkou_bi, String hakkouYouto) {
		super();
		this.hakkouBangou = hakkouBangou;
		this.shain_id = shain_id;
		this.kubun = kubun;
		this.hakkou_bi = hakkou_bi;
		this.hakkouYouto = hakkouYouto;
	}
	
	// 김현서 金賢徐
	// p.3 발행번호, 구분, 발급용도, 발행일 출력 / 증명서 정보 조회에서 사용
	// p.3 発行番号、区分、発行用途、発行日の出力/証明書情報の照会で使用
	public ShoumeishoJouhou(Integer hakkouBangou, String kubun, String hakkouYouto, Date hakkou_bi) {
		this.hakkouBangou = hakkouBangou;
		this.kubun = kubun;
		this.hakkouYouto = hakkouYouto;
		this.hakkou_bi = hakkou_bi;
	}
	public Integer getHakkouBangou() {
		return hakkouBangou;
	}
	public void setHakkouBangou(Integer hakkouBangou) {
		this.hakkouBangou = hakkouBangou;
	}
	public Integer getShain_id() {
		return shain_id;
	}
	public void setShain_id(Integer shain_id) {
		this.shain_id = shain_id;
	}
	public String getKubun() {
		return kubun;
	}
	public void setKubun(String kubun) {
		this.kubun = kubun;
	}

	public Date getHakkou_bi() {
		return hakkou_bi;
	}
	public void setHakkou_bi(Date hakkou_bi) {
		this.hakkou_bi = hakkou_bi;
	}
	public String getHakkouYouto() {
		return hakkouYouto;
	}
	public void setHakkouYouto(String hakkouYouto) {
		this.hakkouYouto = hakkouYouto;
	}
	
	

}
