package com.q498p.replay.record;

import com.q498p.replay.buffer.ReplayFrame;
import com.q498p.replay.core.ReplaySession;
import org.bukkit.entity.Player;

public class MovementRecorder {
    public void record(Player p, ReplaySession s){
        s.record(new ReplayFrame(System.currentTimeMillis(), p.getLocation().toVector(),
                p.getLocation().getYaw(), p.getLocation().getPitch(), p.getVelocity(), false, null));
    }
}
