package sqlpuller.cosmo.sql;

import lombok.SneakyThrows;
import sqlpuller.cosmo.data.CountryData;
import sqlpuller.cosmo.data.PlayerData;
import sqlpuller.cosmo.data.countrydata.CastleData;
import sqlpuller.cosmo.data.countrydata.OutpostData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SQLManager {
    public static Connection con;
    @SneakyThrows
    public SQLManager(){
        String address = "127.0.0.1";
        String id = "root";
        String pw = "thddudals7565#";
        con = DriverManager.getConnection("jdbc:mysql://"+address+":3306/plugin57data?useSSL=false", id, pw);
    }
    public static void pullCountry(String tablename){
        try(PreparedStatement stmt = con.prepareStatement("SELECT * FROM "+tablename); ResultSet rs = stmt.executeQuery()){
            CountryData.loadTableA(tablename, rs);
        }catch(Exception ex){
            System.out.println("CountryPull Exception on "+tablename);
            ex.printStackTrace();
        }
    }
    public static void pullPlayer(String tablename){
        try(PreparedStatement stmt = con.prepareStatement("SELECT * FROM "+tablename); ResultSet rs = stmt.executeQuery()){
            PlayerData.loadTableA(tablename, rs);
        }catch(Exception ex){
            System.out.println("PlayerPull Exception on "+tablename);
            ex.printStackTrace();
        }
    }
    public static void pullOutpost(){
        try(PreparedStatement stmt = con.prepareStatement("SELECT * FROM country_outpost"); ResultSet rs = stmt.executeQuery()){
            OutpostData.loadTable(rs);
        }catch(Exception ex){
            System.out.println("OutpostPull Exception");
            ex.printStackTrace();
        }
    }
    public static void pullCastle(){
        try(PreparedStatement stmt = con.prepareStatement("SELECT * FROM country_castle"); ResultSet rs = stmt.executeQuery()){
            CastleData.loadTable(rs);
        }catch(Exception ex){
            System.out.println("CastlePull Exception");
            ex.printStackTrace();
        }
    }
}
