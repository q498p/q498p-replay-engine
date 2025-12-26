package com.q498p.replay.listeners;

import com.q498p.replay.q498p;
import org.bukkit.event.*;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitListener implements Listener {
    @EventHandler public void onQuit(PlayerQuitEvent e){
        q498p.get().getReplayManager().remove(e.getPlayer().getUniqueId());
    }
}
