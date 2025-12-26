package com.q498p.replay.record;

import com.q498p.replay.buffer.ReplayFrame;
import com.q498p.replay.core.ReplaySession;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class VelocityRecorder {
    public void record(Player p, ReplaySession s){
        Vector v=p.getVelocity();
        if(v.lengthSquared()>0.01){
            s.record(new ReplayFrame(System.currentTimeMillis(), p.getLocation().toVector(),
                    p.getLocation().getYaw(), p.getLocation().getPitch(), v, false, null));
        }
    }
}
