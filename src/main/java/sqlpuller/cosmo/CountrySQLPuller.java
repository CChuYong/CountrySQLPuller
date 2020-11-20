package sqlpuller.cosmo;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import sqlpuller.cosmo.data.CountryData;
import sqlpuller.cosmo.data.PlayerData;
import sqlpuller.cosmo.sql.SQLManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.UUID;

public class CountrySQLPuller extends JavaPlugin {
    boolean DEBUG =true;
    public static HashMap<Integer, CountryData> countryData = new HashMap<>();
    public static HashMap<UUID, PlayerData> playerData = new HashMap<>();
    public static HashMap<Integer, UUID> playerKey = new HashMap<>();
public void onEnable(){
    System.out.println("SQL Connection Start ->");
    new SQLManager();
    System.out.println("SQL Pulling Start ->");
    try(PreparedStatement stmt = SQLManager.con.prepareStatement("SELECT * FROM playerkey"); ResultSet rs = stmt.executeQuery()){
        while(rs.next()){
            playerKey.put(rs.getInt("id"), UUID.fromString(rs.getString("uuid")));
        }
        System.out.println("총 "+playerKey.size()+"개의 playerKey 불러옴");
    }catch(Exception ex){
        ex.printStackTrace();
    }
    System.out.println("Country Pulling Start ->");
    pullCountry();
    System.out.println("PlayerData Pulling Start ->");
    pullPlayer();
    System.out.println("Country Castle Pulling Start ->");
    pullBuilding();
    if(DEBUG){
        Bukkit.getScheduler().runTaskLater(this, ()->{
            System.out.println("SQL Pulling Succed");
            System.out.println("Loaded country count: "+countryData.size());
            System.out.println("Loaded playerdata count: "+playerData.size());

            for(CountryData cd: countryData.values()){
                System.out.println(cd.toString());
            }
        }, 30L);
    }
}
public void pullCountry(){
    SQLManager.pullCountry("country_country");
    SQLManager.pullCountry("country_point");
    SQLManager.pullCountry("country_surrender");
    SQLManager.pullCountry("country_yangdo");
    SQLManager.pullCountry("country_viceking");
    SQLManager.pullCountry("country_war");
}
public void pullPlayer(){
    SQLManager.pullPlayer("country_chat");
    SQLManager.pullPlayer("country_create");
    SQLManager.pullPlayer("country_player");
    SQLManager.pullPlayer("country_rank");
}
private void pullBuilding(){
    SQLManager.pullCastle();
    SQLManager.pullOutpost();
}


}
