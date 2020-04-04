/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reklamtabela;

/**
 *
 * @author khagani
 */
public class login {
    private String ad;
    private String soyad;

    public login(String ad, String soyad) {
        this.ad = ad;
        this.soyad = soyad;
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

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }
    
    
}
