package sqlpuller.cosmo.abs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BuildingAdapter {
    private int id,server,heart_x,heart_y,heart_z,spawn_x,spawn_y,spawn_z,first_x,first_z,end_x,end_y,end_z;
    private String worldname;
}
