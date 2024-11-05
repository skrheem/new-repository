package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import JinjiKanri.dao.FuyouKazokuDao;
import JinjiKanri.dao.GakurekiDao;
import JinjiKanri.dao.GogakuNouryokuDao;
import JinjiKanri.dao.HatsureiDao;
import JinjiKanri.dao.HeiekiDao;
import JinjiKanri.dao.KeirekiDao;
import JinjiKanri.dao.KyouikuKunrenDao;
import JinjiKanri.dao.ShainDao;
import JinjiKanri.dao.ShikakuMenkyouDao;
import JinjiKanri.dao.ShoubatsuDao;
import JinjiKanri.dao.TaishokuDao;
import JinjiKanri.dao.YondaihokenJouhouDao;
import JinjiKanri.model.FuyouKazoku;
import JinjiKanri.model.Gakureki;
import JinjiKanri.model.GogakuNouryoku;
import JinjiKanri.model.Hatsurei;
import JinjiKanri.model.Heieki;
import JinjiKanri.model.Keireki;
import JinjiKanri.model.KyouikuKunren;
import JinjiKanri.model.Shain;
import JinjiKanri.model.ShikakuMenkyou;
import JinjiKanri.model.Shoubatsu;
import JinjiKanri.model.Taishoku;
import JinjiKanri.model.YondaihokenJouhou;
import jdbc.connection.ConnectionProvider;

// 김현서 金現徐
// p.2 인사기록카드 form 내에 채워질 사원 정보를 불러오는 서비스
// p.2人事記録カードform内に埋め込まれる社員情報を読み込むサービス
public class ShainRecordCardService {

    private ShainDao shainDao = ShainDao.getInstance();
    private FuyouKazokuDao fuyouKazokuDao = FuyouKazokuDao.getInstance();
    private YondaihokenJouhouDao yondaihokenDao = YondaihokenJouhouDao.getInstance();
    private GakurekiDao gakurekiDao = GakurekiDao.getInstance();
    private HeiekiDao heiekiDao = HeiekiDao.getInstance();
    private KeirekiDao keirekiDao = KeirekiDao.getInstance();
    private ShikakuMenkyouDao shikakuMenkyouDao = ShikakuMenkyouDao.getInstance();
    private GogakuNouryokuDao gogakuDao = GogakuNouryokuDao.getInstance();
    private KyouikuKunrenDao kyouikuKunrenDao = KyouikuKunrenDao.getInstance();
    private ShoubatsuDao shoubatsuDao = ShoubatsuDao.getInstance();
    private HatsureiDao hatsureiDao = HatsureiDao.getInstance();
    private TaishokuDao taishokuDao = TaishokuDao.getInstance();

    public ShainRecordCardInfo getShainRecordCardInfo(Integer shain_id) {
        Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();

            
            ArrayList<Shain> shainInfo = shainDao.getShainKihonJyouhou(conn, shain_id);
            ArrayList<FuyouKazoku> fuyouKazokuList = fuyouKazokuDao.getFuyouKazokuByShainId(conn, shain_id);
            List<YondaihokenJouhou> yondaihokenList = yondaihokenDao.getYondaihokenInfo(conn, shain_id);
            ArrayList<Gakureki> gakurekiList = gakurekiDao.getGakurekiList(conn, shain_id);
            ArrayList<Heieki> heiekiList = heiekiDao.getHeiekiByShainId(conn, shain_id);
            ArrayList<Keireki> keirekiList = keirekiDao.getKeirekiByShainId(conn, shain_id);
            ArrayList<ShikakuMenkyou> shikakuList = shikakuMenkyouDao.getShikakuMenkyouByShainId(conn, shain_id);
            ArrayList<GogakuNouryoku> gogakuList = GogakuNouryokuDao.getGogakuByShainId(conn, shain_id);
            ArrayList<KyouikuKunren> kyouikuList = kyouikuKunrenDao.getKyouikuKunrenByShainId(conn, shain_id);
            ArrayList<Shoubatsu> shoubatsuList = shoubatsuDao.getShobatsuByShainId(conn, shain_id);
            ArrayList<Hatsurei> hatsureiList = hatsureiDao.getHatsureiByShainId(conn, shain_id);
            ArrayList<Taishoku> taishokuList = taishokuDao.getTaishokuByShainId(conn, shain_id);

            
            ShainRecordCardInfo recordCardInfo = new ShainRecordCardInfo(shainInfo, fuyouKazokuList, yondaihokenList,
                    gakurekiList, heiekiList, keirekiList, shikakuList, gogakuList, kyouikuList, shoubatsuList,
                    hatsureiList, taishokuList);

            return recordCardInfo;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

   
    public static class ShainRecordCardInfo {
        private ArrayList<Shain> shainInfo;
        private ArrayList<FuyouKazoku> fuyouKazokuList;
        private List<YondaihokenJouhou> yondaihokenList;
        private ArrayList<Gakureki> gakurekiList;
        private ArrayList<Heieki> heiekiList;
        private ArrayList<Keireki> keirekiList;
        private ArrayList<ShikakuMenkyou> shikakuList;
        private ArrayList<GogakuNouryoku> gogakuList;
        private ArrayList<KyouikuKunren> kyouikuList;
        private ArrayList<Shoubatsu> shoubatsuList;
        private ArrayList<Hatsurei> hatsureiList;
        private ArrayList<Taishoku> taishokuList;

        public ShainRecordCardInfo(ArrayList<Shain> shainInfo, ArrayList<FuyouKazoku> fuyouKazokuList,
                                   List<YondaihokenJouhou> yondaihokenList, ArrayList<Gakureki> gakurekiList,
                                   ArrayList<Heieki> heiekiList, ArrayList<Keireki> keirekiList,
                                   ArrayList<ShikakuMenkyou> shikakuList, ArrayList<GogakuNouryoku> gogakuList,
                                   ArrayList<KyouikuKunren> kyouikuList, ArrayList<Shoubatsu> shoubatsuList,
                                   ArrayList<Hatsurei> hatsureiList, ArrayList<Taishoku> taishokuList) {
            this.shainInfo = shainInfo;
            this.fuyouKazokuList = fuyouKazokuList;
            this.yondaihokenList = yondaihokenList;
            this.gakurekiList = gakurekiList;
            this.heiekiList = heiekiList;
            this.keirekiList = keirekiList;
            this.shikakuList = shikakuList;
            this.gogakuList = gogakuList;
            this.kyouikuList = kyouikuList;
            this.shoubatsuList = shoubatsuList;
            this.hatsureiList = hatsureiList;
            this.taishokuList = taishokuList;
        }

        public ArrayList<Shain> getShainInfo() {
            return shainInfo;
        }

        public ArrayList<FuyouKazoku> getFuyouKazokuList() {
            return fuyouKazokuList;
        }

        public List<YondaihokenJouhou> getYondaihokenList() {
            return yondaihokenList;
        }

        public ArrayList<Gakureki> getGakurekiList() {
            return gakurekiList;
        }

        public ArrayList<Heieki> getHeiekiList() {
            return heiekiList;
        }

        public ArrayList<Keireki> getKeirekiList() {
            return keirekiList;
        }

        public ArrayList<ShikakuMenkyou> getShikakuList() {
            return shikakuList;
        }

        public ArrayList<GogakuNouryoku> getGogakuList() {
            return gogakuList;
        }

        public ArrayList<KyouikuKunren> getKyouikuList() {
            return kyouikuList;
        }

        public ArrayList<Shoubatsu> getShoubatsuList() {
            return shoubatsuList;
        }

        public ArrayList<Hatsurei> getHatsureiList() {
            return hatsureiList;
        }

        public ArrayList<Taishoku> getTaishokuList() {
            return taishokuList;
        }
    }

    
}
