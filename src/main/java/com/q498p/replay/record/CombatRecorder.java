package com.q498p.replay.record;

import com.q498p.replay.buffer.ReplayFrame;
import com.q498p.replay.core.ReplaySession;
import org.bukkit.entity.Player;

public class CombatRecorder {
    public void hit(Player a, Player v, ReplaySession s){
        s.record(new ReplayFrame(System.currentTimeMillis(), a.getLocation().toVector(),
                a.getLocation().getYaw(), a.getLocation().getPitch(), a.getVelocity(), true, v.getUniqueId()));
    }
}
