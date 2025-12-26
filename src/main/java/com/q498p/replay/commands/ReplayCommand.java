package com.q498p.replay.commands;

import com.q498p.replay.q498p;
import com.q498p.replay.playback.ReplayPlayer;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class ReplayCommand implements CommandExecutor {
    @Override public boolean onCommand(CommandSender s, Command c, String l, String[] a){
        if(!(s instanceof Player p)) return true;
        if(!p.hasPermission("q498p.staff")) return true;
        if(a.length!=1) return true;
        Player t=p.getServer().getPlayer(a[0]); if(t==null) return true;
        var sess=q498p.get().getReplayManager().get(t.getUniqueId()); if(sess==null) return true;
        new ReplayPlayer(p).play(sess.frames());
        return true;
    }
}
