/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reklamtabela;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 *
 * @author khagani
 */
public class DB_conection extends javax.swing.JFrame {
   
    private static final String Driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"; 
    private static final String url="jdbc:sqlserver://DESKTOP-1GLIV89\\SQL;databaseName=bmok_DB";
    private static final String username="khagani";
    private static final String password="Eldeyme01";

    static Statement stmt=null;
    static ResultSet rs = null;
     static Connection con=null;       
        
    public static void sqlconnection(){
        try{
          Class.forName(Driver);
         con =DriverManager.getConnection(url, username, password);
         System.out.println("Connected");
     
        } catch(Exception e){
            
        
        }
    }
    public static ArrayList giris(String mail,String password) throws SQLException{

        ArrayList <login> mylist = new ArrayList<login>();
        stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        rs=stmt.executeQuery("select isim,soyad from kullanicibilgiler_T  where mail = '"+mail+"' and sifre ='"+password+"'");
        if(rs.next()){
            mylist.add(new login(rs.getString("isim"), rs.getString("soyad")));
            System.out.println(mylist.get(0).getAd()+" "+mylist.get(0).getSoyad());
   
            return mylist;
        }           
            mylist.add(new login("bos","bos"));
            return mylist;

        
    }
 

    public static int kayit(String ad, String soyad, String email,String Sifre) throws SQLException{
        stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        rs=stmt.executeQuery("select isim,soyad from kullanicibilgiler_T  where isim  = '"+ad+"' and soyad ='"+soyad+"'");
        if(!rs.next()){
            stmt.executeUpdate("  insert into kullanicibilgiler_T(isim,soyad,mail,Sifre,Bütce) values('"+ad+"','"+soyad+"','"+email+"','"+Sifre+"',0)");
            return 1;
            }
    return 0;
    
    }
    
       public static String[] ilbulma() throws SQLException{
        String il[] =new String[81];
        int i=0;
        stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        rs=stmt.executeQuery("select isim from iller");
        while(rs.next()){
            
            il[i]=rs.getString("isim");
            i++;
        
        }
        return il;
    }
       public static ArrayList ilceler(String il) throws SQLException{
       ArrayList<String>ilce=new ArrayList<String>();
       int i=0;
       System.out.println(il);
        stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        rs=stmt.executeQuery("select il_no from iller where  isim = '"+il+"'");
        if(rs.next()){
          String str=rs.getString("il_no");
          i=Integer.parseInt(str);
          rs=stmt.executeQuery("select isim from ilceler where il_no ="+i);
          while(rs.next())
              ilce.add(rs.getString("isim"));
         
          return ilce;
        }  
         
        return null;
       }
       public static ArrayList tabelala(String ilce) throws SQLException{
       ArrayList<String>tabela=new ArrayList<String>();
       int i=0;
       System.out.println(ilce);
        stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        rs=stmt.executeQuery("select ilce_no from ilceler where  isim = '"+ilce+"'");
        if(rs.next()){
          String str=rs.getString("ilce_no");
          i=Integer.parseInt(str);
          rs=stmt.executeQuery("select isim from tabelalar where ilce_no ="+i);
          while(rs.next())
              tabela.add(rs.getString("isim"));        
        }  
       return tabela;
       
       
       }
       
       public static ArrayList AdminD() throws SQLException{
       ArrayList<Rapor> rapor=new ArrayList<Rapor>();
       stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
       rs=stmt.executeQuery("select * from rapor_T where  kalansaat !=0 ");
       while(rs.next()){
           rapor.add(new Rapor(rs.getString("isim"),rs.getString("soyad"),rs.getString("reklamismi"),rs.getString("konum"),rs.getString("baslangiczamani"),rs.getString("bitiszamani"),Integer.parseInt(rs.getString("kalansaat")),Integer.parseInt(rs.getString("kalabalikbilgisi")),Integer.parseInt(rs.getString("butce"))));
  
        }
         return rapor;
        
       }
       public static ArrayList AdminB() throws SQLException{
       ArrayList<Rapor> rapor=new ArrayList<Rapor>();
       DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss"); 
        stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        rs=stmt.executeQuery("select * from rapor_T where  kalansaat =0 ");
        while(rs.next()){
           rapor.add(new Rapor(rs.getString("isim"),rs.getString("soyad"),rs.getString("reklamismi"),rs.getString("konum"),rs.getString("baslangiczamani"),rs.getString("bitiszamani"),Integer.parseInt(rs.getString("kalansaat")),Integer.parseInt(rs.getString("kalabalikbilgisi")),Integer.parseInt(rs.getString("butce"))));
  
        }
         return rapor;
        
       }
       public static ArrayList KullaniciD(String ad,String Soyad)  throws SQLException{
       ArrayList<Rapor> rapor=new ArrayList<Rapor>();
       DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss"); 
        stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        rs=stmt.executeQuery("select * from rapor_T where  isim = '"+ad+"' and soyad='"+Soyad+"' and kalansaat!=0");
        while(rs.next()){
           rapor.add(new Rapor(rs.getString("isim"),rs.getString("soyad"),rs.getString("reklamismi"),rs.getString("konum"),rs.getString("baslangiczamani"),rs.getString("bitiszamani"),Integer.parseInt(rs.getString("kalansaat")),Integer.parseInt(rs.getString("kalabalikbilgisi")),Integer.parseInt(rs.getString("butce"))));
  
        }
        
         return rapor;
       }
       public static ArrayList KullaniciB(String ad,String Soyad)  throws SQLException{
       ArrayList<Rapor> rapor=new ArrayList<Rapor>();
       DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss"); 
        stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        rs=stmt.executeQuery("select * from rapor_T where  isim = '"+ad+"' and soyad='"+Soyad+"' and kalansaat=0");
        while(rs.next()){
           rapor.add(new Rapor(rs.getString("isim"),rs.getString("soyad"),rs.getString("reklamismi"),rs.getString("konum"),rs.getString("baslangiczamani"),rs.getString("bitiszamani"),Integer.parseInt(rs.getString("kalansaat")),Integer.parseInt(rs.getString("kalabalikbilgisi")),Integer.parseInt(rs.getString("butce"))));
  
        }
        
         return rapor;
       }
       public static void ReklamVer(String ad,String soyad,String reklamismi,String konum,int saat ,int kalabalik ) throws SQLException{
        Date currentDate =new Date();
        SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd");
        String baslangiczamani=dateFormat.format(currentDate);
        baslangiczamani+="T";
        SimpleDateFormat timeFormat =new SimpleDateFormat("hh:mm:ss");
        String str1=timeFormat.format(currentDate);
        baslangiczamani+=str1;
        System.out.println(baslangiczamani);
        stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        stmt.executeUpdate("insert into rapor_T(isim,soyad,reklamismi,konum,baslangiczamani,bitiszamani,kalansaat,kalabalikbilgisi,butce) values('"+ad+"','"+soyad+"','"+reklamismi+"','"+konum+"','"+baslangiczamani+"',NULL,"+saat+" ,"+kalabalik+" ,"+50+")");
       }
      public static int butcesorgulama(String ad, String soyad) throws SQLException{
          int i=0;
          stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
          rs=stmt.executeQuery("select Bütce from kullanicibilgiler_T where  isim = '"+ad+"' and soyad='"+soyad+"'");
          if(rs.next()){
            String str=rs.getString("Bütce");
             i=Integer.parseInt(str);          
         }
          System.out.println(i);
          return i;
      }
      public static void butceartir(String ad,String soyad,int butce)throws SQLException{
          int i=0;
          System.out.println(ad);
          System.out.println(soyad);
          stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
          System.out.println("butce artilir ="+butce);
        
          stmt.executeUpdate("  update  kullanicibilgiler_T set Bütce="+butce+"where  isim ='"+ad+"' and soyad='"+soyad+"'");
    
      }
       
      public static int adminkazanc(String baslangic, String bitiszamani) throws SQLException{
      int kazanc=0;
      stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
      rs=stmt.executeQuery("select Butce from rapor_T where bitiszamani BETWEEN '"+baslangic+"' and '"+bitiszamani+"' ;");
      while(rs.next()){
          kazanc+=Integer.parseInt(rs.getString("butce"));
          
     }
      System.out.println(kazanc+"kazanc");
      return kazanc;
      }

       public static void yayinlama(ArrayList list,int i ) throws SQLException{
        ArrayList <Rapor> mylist = new ArrayList<>(list);  
         stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
         stmt.executeUpdate("  update rapor_T set kalansaat = "+mylist.get(i).getKalansure()+",butce="+mylist.get(i).getButce()+" where baslangiczamani = '"+mylist.get(i).getbaslamazamani()+"'and reklamismi='"+mylist.get(i).getReklaminismi()+"'");
         
       }      
       public static void reklambitir(ArrayList list,int i, String bitiszamani) throws SQLException{
        ArrayList <Rapor> mylist = new ArrayList<>(list);  
         stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
         stmt.executeUpdate("  update rapor_T set kalansaat = "+mylist.get(i).getKalansure()+",butce="+mylist.get(i).getButce()+",bitiszamani='"+bitiszamani+"' where baslangiczamani = '"+mylist.get(i).getbaslamazamani()+"'and reklamismi='"+mylist.get(i).getReklaminismi()+"'");
         
       }
       public static void delay(ArrayList list,int i ) throws SQLException {
         ArrayList <Rapor> mylist = new ArrayList<>(list);  
         stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
         stmt.executeUpdate("update rapor_T set gecikme = "+mylist.get(i).getDelay()+" where baslangiczamani ='"+mylist.get(i).getbaslamazamani()+"'and reklamismi='"+mylist.get(i).getReklaminismi()+"'");
       
       
       }
}
