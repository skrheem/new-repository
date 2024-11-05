package JinjiKanri.model;


import java.util.Date;

public class GogakuNouryoku {
	
	private Integer gogaku_id;
	private Integer shain_id;
	private String gengo;
	private String shiken_mei;
	private Integer tensuu;
	private Date shutoku_bi;
	private String dokkaiNouryoku;
	private String sakubunNouryoku;
	private String kaiwaNouryoku;
	public GogakuNouryoku(Integer gogaku_id, Integer shain_id, String gengo, String shiken_mei, Integer tensuu, Date shutoku_bi,
			String dokkaiNouryoku, String sakubunNouryoku, String kaiwaNouryoku) {
		super();
		this.gogaku_id = gogaku_id;
		this.shain_id = shain_id;
		this.gengo = gengo;
		this.shiken_mei = shiken_mei;
		this.tensuu = tensuu;
		this.shutoku_bi = shutoku_bi;
		this.dokkaiNouryoku = dokkaiNouryoku;
		this.sakubunNouryoku = sakubunNouryoku;
		this.kaiwaNouryoku = kaiwaNouryoku;
	}

	
	// 김현서 金現徐
    // p.2 인사기록카드 특정 사원의 어학 정보를 조회하는 메소드
	// p.2 人事記録カード 人事記録カード_特定社員の語学情報を照会するメソッド
    public GogakuNouryoku(String gengo, String shiken_mei, Integer tensuu, Date shutoku_bi, String dokkaiNouryoku, String sakubunNouryoku, String kaiwaNouryoku) {
        this.gengo = gengo;
        this.shiken_mei = shiken_mei;
        this.tensuu = tensuu;
        this.shutoku_bi = shutoku_bi;
        this.dokkaiNouryoku = dokkaiNouryoku;
        this.sakubunNouryoku = sakubunNouryoku;
        this.kaiwaNouryoku = kaiwaNouryoku;
    }
	
	public Integer getGogaku_id() {
		return gogaku_id;
	}
	public void setGogaku_id(Integer gogaku_id) {
		this.gogaku_id = gogaku_id;
	}
	public Integer getShain_id() {
		return shain_id;
	}
	public void setShain_id(Integer shain_id) {
		this.shain_id = shain_id;
	}
	public String getGengo() {
		return gengo;
	}
	public void setGengo(String gengo) {
		this.gengo = gengo;
	}
	public String getShiken_mei() {
		return shiken_mei;
	}
	public void setShiken_mei(String shiken_mei) {
		this.shiken_mei = shiken_mei;
	}
	public Integer getTensuu() {
		return tensuu;
	}
	public void setTensuu(Integer tensuu) {
		this.tensuu = tensuu;
	}
	public Date getShutoku_bi() {
		return shutoku_bi;
	}
	public void setShutoku_bi(Date shutoku_bi) {
		this.shutoku_bi = shutoku_bi;
	}
	public String getDokkaiNouryoku() {
		return dokkaiNouryoku;
	}
	public void setDokkaiNouryoku(String dokkaiNouryoku) {
		this.dokkaiNouryoku = dokkaiNouryoku;
	}
	public String getSakubunNouryoku() {
		return sakubunNouryoku;
	}
	public void setSakubunNouryoku(String sakubunNouryoku) {
		this.sakubunNouryoku = sakubunNouryoku;
	}
	public String getKaiwaNouryoku() {
		return kaiwaNouryoku;
	}
	public void setKaiwaNouryoku(String kaiwaNouryoku) {
		this.kaiwaNouryoku = kaiwaNouryoku;
	}

}
