package com.q498p.replay.commands;

import com.q498p.replay.replay.ReplayController; import org.bukkit.command.*; import org.bukkit.entity.Player;

public class ReplayControlCommand implements CommandExecutor{
    private final ReplayController c=new ReplayController();
    @Override public boolean onCommand(CommandSender s, Command cmd, String l, String[] a){
        if(!(s instanceof Player p)) return true;
        if(a.length<1) return true;
        switch(a[0].toLowerCase()){
            case "stop" -> c.stop(p);
            case "pause" -> c.pause(p);
            case "resume" -> c.resume(p);
            case "speed" -> { if(a.length==2) c.speed(p, Double.parseDouble(a[1])); }
        }
        return true;
    }
}
