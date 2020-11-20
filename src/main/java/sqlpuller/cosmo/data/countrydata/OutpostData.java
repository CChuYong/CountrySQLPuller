package sqlpuller.cosmo.data.countrydata;

import lombok.Getter;
import lombok.Setter;
import sqlpuller.cosmo.CountrySQLPuller;
import sqlpuller.cosmo.abs.BuildingAdapter;
import sqlpuller.cosmo.data.CountryData;

import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Setter
public class OutpostData extends BuildingAdapter {
    private int level,hp,first_y;
    public static void loadTable(ResultSet rs) throws SQLException {
        while(rs.next()){
            int id = rs.getInt("country");
            CountryData cd = CountrySQLPuller.countryData.getOrDefault(id, null);
            if(cd==null){
                System.out.println("Outpost Load Failed. id:"+id);
                return;
            }
            OutpostData csd = new OutpostData();
            csd.setId(rs.getInt("id"));
            csd.setServer(rs.getInt("server"));
            csd.setWorldname(rs.getString("world"));
            csd.setLevel(rs.getInt("level"));
            csd.setHp(rs.getInt("heart"));
            csd.setHeart_x(rs.getInt("heart_x"));
            csd.setHeart_y(rs.getInt("heart_y"));
            csd.setHeart_z(rs.getInt("heart_z"));
            csd.setSpawn_x(rs.getInt("spawn_x"));
            csd.setSpawn_y(rs.getInt("spawn_y"));
            csd.setSpawn_z(rs.getInt("spawn_z"));
            csd.setFirst_x(rs.getInt("first_x"));
            csd.setFirst_y(rs.getInt("first_y"));
            csd.setFirst_z(rs.getInt("first_z"));
            csd.setEnd_x(rs.getInt("end_x"));
            csd.setEnd_y(rs.getInt("end_y"));
            csd.setEnd_z(rs.getInt("end_z"));
            cd.getOutpostes().add(csd);
        }

    }
}
