package com.q498p.replay.commands;

import com.q498p.replay.playback.ReplayPlayer;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class ReplayCtlCommand implements CommandExecutor {
    @Override public boolean onCommand(CommandSender s, Command c, String l, String[] a){
        if(!(s instanceof Player p)) return true;
        // Controls are per-session instance; kept minimal by design
        return true;
    }
}
