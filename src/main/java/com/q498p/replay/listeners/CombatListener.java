package com.q498p.replay.listeners;

import com.q498p.replay.q498p;
import com.q498p.replay.record.CombatRecorder;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class CombatListener implements Listener {
    private final CombatRecorder rec = new CombatRecorder();
    @EventHandler public void onHit(EntityDamageByEntityEvent e){
        if(!(e.getDamager() instanceof Player a)) return;
        if(!(e.getEntity() instanceof Player v)) return;
        rec.hit(a,v, q498p.get().getReplayManager().getOrCreate(a.getUniqueId()));
    }
}
