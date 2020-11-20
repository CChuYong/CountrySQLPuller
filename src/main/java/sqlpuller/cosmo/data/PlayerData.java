package sqlpuller.cosmo.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sqlpuller.cosmo.CountrySQLPuller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Getter
@Setter
@ToString
public class PlayerData {
    private UUID uuid;
    private int chatmode,countryid,rank,lastrank;
    private boolean chatspy, waitcreate = false;
    private String countryname = null;
    private long countrycreateuntil = 0L, unregister = 0L;
    public PlayerData(UUID uuid){
        this.uuid = uuid;
    }
    public static void loadTableA(String tablename, ResultSet rs) throws SQLException {
        while(rs.next()){
            PlayerData pd = CountrySQLPuller.playerData.computeIfAbsent(CountrySQLPuller.playerKey.get(rs.getInt("id")), PlayerData::new);
            switch(tablename){
                case "country_chat":
                    pd.setChatmode(rs.getInt("mode"));
                    pd.setChatspy(rs.getInt("chatspy")==1);
                    break;
                case "country_create":
                    pd.setWaitcreate(true);
                    pd.setCountryname(rs.getString("name"));
                    pd.setCountrycreateuntil(rs.getLong("time"));
                    break;
                case "country_player":
                    pd.setCountryid(rs.getInt("country"));
                    pd.setUnregister(rs.getLong("unregister"));
                    break;
                case "country_rank":
                    pd.setRank(rs.getInt("rankid"));
                    pd.setLastrank(rs.getInt("lastrankid"));
                    break;
            }
        }
    }

}
