/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reklamtabela;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author khagani
 */
public class API extends TimerTask {
   public void run(){
       int butce=0;
        try {
            Random rand = new Random();
            ArrayList <Rapor> mylist=new ArrayList<Rapor>();
            
            mylist=DB_conection.AdminD();
            if(!mylist.isEmpty()){
                for(int i=0; i<mylist.size(); i++){
                    int kalabalik=rand.nextInt(101);
                    butce=DB_conection.butcesorgulama(mylist.get(i).getAd(),mylist.get(i).getSoyad());
                   // System.out.println("kalabalik="+kalabalik);
                    if(kalabalik>=mylist.get(i).getKalabalik() && (butce-5)>=0){
                    //    System.out.println("asdasd");   
                        DB_conection.butceartir(mylist.get(i).getAd(),mylist.get(i).getSoyad(), butce-5);
                        mylist.get(i).setKalansure(mylist.get(i).getKalansure()-1);
                        mylist.get(i).setButce(mylist.get(i).getButce()+5);
                        //System.out.println("kalansure"+mylist.get(i).getKalansure());
                            if(mylist.get(i).getKalansure()==0){
                                Date currentDate =new Date();
                                SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd");
                                String bitiszamani=dateFormat.format(currentDate);
                                bitiszamani+="T";
                                SimpleDateFormat timeFormat =new SimpleDateFormat("hh:mm:ss");
                                String str1=timeFormat.format(currentDate);
                                bitiszamani+=str1;
                                DB_conection.reklambitir(mylist,i,bitiszamani);
                            }
                        else
                            DB_conection.yayinlama(mylist, i);
                   }
                else{
                    mylist.get(i).setDelay(mylist.get(i).getDelay()+1);
                    DB_conection.delay(mylist,i);
                  }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(API.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }
}
