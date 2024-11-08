package JinjiKanri.model;

import java.util.Date;

public class ShainTekiyouKoujoKoumoku {
	private Integer shain_id;
	private Integer koujoKoumoku_id;
	private Date koujoKoumoku_tekiyoubi;
    private String shainImageKeiro;  
    private String namaeKana;       
    private Date nyuushaNengappi;  
    private String juusho;           
    private String koujoKoumokuMei;
	
	
	public ShainTekiyouKoujoKoumoku(Integer shain_id, Integer koujoKoumoku_id, Date koujoKoumoku_tekiyoubi) {
		super();
		this.shain_id = shain_id;
		this.koujoKoumoku_id = koujoKoumoku_id;
		this.koujoKoumoku_tekiyoubi = koujoKoumoku_tekiyoubi;
	}
	public Integer getShain_id() {
		return shain_id;
	}
	public void setShain_id(Integer shain_id) {
		this.shain_id = shain_id;
	}
	public Integer getKoujoKoumoku_id() {
		return koujoKoumoku_id;
	}
	public void setKoujoKoumoku_id(Integer koujoKoumoku_id) {
		this.koujoKoumoku_id = koujoKoumoku_id;
	}
	public Date getKoujoKoumoku_tekiyoubi() {
		return koujoKoumoku_tekiyoubi;
	}
	public void setKoujoKoumoku_tekiyoubi(Date koujoKoumoku_tekiyoubi) {
		this.koujoKoumoku_tekiyoubi = koujoKoumoku_tekiyoubi;
	}
	public String getShainImageKeiro() {
		return shainImageKeiro;
	}
	public void setShainImageKeiro(String shainImageKeiro) {
		this.shainImageKeiro = shainImageKeiro;
	}
	public String getNamaeKana() {
		return namaeKana;
	}
	public void setNamaeKana(String namaeKana) {
		this.namaeKana = namaeKana;
	}
	public Date getNyuushaNengappi() {
		return nyuushaNengappi;
	}
	public void setNyuushaNengappi(Date nyuushaNengappi) {
		this.nyuushaNengappi = nyuushaNengappi;
	}
	public String getJuusho() {
		return juusho;
	}
	public void setJuusho(String juusho) {
		this.juusho = juusho;
	}
	public String getKoujoKoumokuMei() {
		return koujoKoumokuMei;
	}
	public void setKoujoKoumokuMei(String koujoKoumokuMei) {
		this.koujoKoumokuMei = koujoKoumokuMei;
	}
	
	// 김현서 金賢徐
	// p.1 미니팝업 (사원이미지, 이름, 주소, 갑근세_명)
	// p.1 ミニポップアップ(社員画像、名前、住所、甲勤税_名)
	public ShainTekiyouKoujoKoumoku(Integer shain_id, Integer koujoKoumoku_id, Date koujoKoumoku_tekiyoubi,
			String shainImageKeiro, String namaeKana, Date nyuushaNengappi, String juusho, String koujoKoumokuMei) {
		super();
		this.shain_id = shain_id;
		this.koujoKoumoku_id = koujoKoumoku_id;
		this.koujoKoumoku_tekiyoubi = koujoKoumoku_tekiyoubi;
		this.shainImageKeiro = shainImageKeiro;
		this.namaeKana = namaeKana;
		this.nyuushaNengappi = nyuushaNengappi;
		this.juusho = juusho;
		this.koujoKoumokuMei = koujoKoumokuMei;
	}
	
    public ShainTekiyouKoujoKoumoku() {}

}
