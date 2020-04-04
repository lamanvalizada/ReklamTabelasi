/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reklamtabela;

/**
 *
 * @author huseynvaliyev
 */
public class Rapor {
    private String ad;
    private String soyad;
    private String reklaminismi;
    private String konum;
    private String baslamazamani;
    private String bitmezamani;
    private int kalansure;
    private int kalabalik;
    private int delay;
    private int butce;

    public Rapor(String ad, String soyad, String reklaminismi, String konum, String baslamazamani, String bitmezamani ,int kalansure, int kalabalik,int butce) {
        this.ad = ad;
        this.soyad = soyad;
        this.reklaminismi = reklaminismi;
        this.konum = konum;
        this.baslamazamani = baslamazamani;
        this.bitmezamani = bitmezamani;
        this.kalansure=kalansure;
        this.kalabalik=kalabalik;
        this.butce = butce;
    }


    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public int getKalansure() {
        return kalansure;
    }

    public void setKalansure(int kalansure) {
        this.kalansure = kalansure;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getReklaminismi() {
        return reklaminismi;
    }

    public void setReklaminismi(String reklaminismi) {
        this.reklaminismi = reklaminismi;
    }

    public String getKonum() {
        return konum;
    }

    public void setKonum(String konum) {
        this.konum = konum;
    }

    public String getbaslamazamani() {
        return baslamazamani;
    }

    public void setbaslamazamani(String baslamazamani) {
        this.baslamazamani = baslamazamani;
    }

    public String getbitmezamani() {
        return bitmezamani;
    }

    public void setbitmezamani(String bitmezamani) {
        this.bitmezamani = bitmezamani;
    }

    public int getKalabalik() {
        return kalabalik;
    }

    public void setKalabalik(int kalabalik) {
        this.kalabalik = kalabalik;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getButce() {
        return butce;
    }

    public void setButce(int butce) {
        this.butce = butce;
    }

   
    
}
