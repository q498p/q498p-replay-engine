package com.q498p.replay.replay;

import com.q498p.replay.core.ReplaySession; import com.q498p.replay.playback.ReplayPlayer;
import org.bukkit.entity.Player; import java.util.*;

public class ReplayController{
    private final Map<UUID, ReplayPlayer> active=new HashMap<>();
    public void play(Player staff, ReplaySession s){
        ReplayPlayer p=new ReplayPlayer(staff); active.put(staff.getUniqueId(),p); p.play(s.frames());
    }
    public void stop(Player staff){
        ReplayPlayer p=active.remove(staff.getUniqueId()); if(p!=null) p.stop();
    }
    public void speed(Player staff,double sp){ ReplayPlayer p=active.get(staff.getUniqueId()); if(p!=null) p.speed(sp); }
    public void pause(Player staff){ ReplayPlayer p=active.get(staff.getUniqueId()); if(p!=null) p.pause(); }
    public void resume(Player staff){ ReplayPlayer p=active.get(staff.getUniqueId()); if(p!=null) p.resume(); }
}
