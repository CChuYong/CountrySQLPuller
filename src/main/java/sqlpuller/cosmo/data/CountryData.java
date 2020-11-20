package sqlpuller.cosmo.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sqlpuller.cosmo.CountrySQLPuller;
import sqlpuller.cosmo.data.countrydata.CastleData;
import sqlpuller.cosmo.data.countrydata.OutpostData;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

@Getter
@Setter
@ToString
public class CountryData {
    private int id=-1,allianceid=-1,level=1,point=0,surrenderCount=-1,tablesize=0;
    private String name = null;
    private UUID owner = null;
    private  boolean pvp = false;
    private  Date lastSurrenderDate = null;
    private double balance = 0.0D;
    private long tax =0L,newbieprotect=0L,countrywar=0L,lastKingMove=0L,lastSubKingMove=0L;
    private ArrayList<CastleData> castles = new ArrayList<CastleData>();
    private ArrayList<OutpostData> outpostes = new ArrayList<OutpostData>();
    public CountryData(int i) {
        this.id = i;
    }
    public void increase(){
        tablesize++;
    }
    public static void loadTableA(String tablename, ResultSet rs) throws SQLException {
        while(rs.next()){
        CountryData cd = CountrySQLPuller.countryData.computeIfAbsent(rs.getInt("id"), CountryData::new);
        switch(tablename){
            case "country_country":
                cd.setName(rs.getString("name"));
                cd.setOwner(CountrySQLPuller.playerKey.get(rs.getInt("owner")));
                cd.setAllianceid(rs.getInt("alliance"));
                cd.setLevel(rs.getInt("level"));
                cd.setBalance(rs.getDouble("coffer"));
                cd.setPvp(rs.getInt("pvp")==1);
                cd.setNewbieprotect(rs.getLong("protect"));
                cd.setTax(rs.getLong("tax"));
                break;
            case "country_point":
                cd.setPoint(rs.getInt("point"));
                break;
            case "country_surrender":
                cd.setSurrenderCount(rs.getInt("collect"));
                cd.setLastSurrenderDate(rs.getDate("day"));
                break;
            case "country_yangdo":
                cd.setLastKingMove(rs.getLong("lastyangdo"));
                break;
            case "country_viceking":
                cd.setLastSubKingMove(rs.getLong("cooldown"));
                break;
            case "country_war":
                cd.setCountrywar(rs.getLong("time"));
                break;
        }
        cd.increase();
    }
    }
}
