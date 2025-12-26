package com.q498p.replay.playback;

import com.q498p.replay.buffer.ReplayFrame;
import com.q498p.replay.q498p;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class ReplayPlayer {

    private final Player viewer;
    private TimelineController tl;

    public ReplayPlayer(Player viewer){ this.viewer=viewer; }

    public void play(List<ReplayFrame> frames){
        viewer.setGameMode(GameMode.SPECTATOR);
        tl = new TimelineController(frames);

        if(frames.isEmpty()) return;
        q498p.get().getCameraEngine().startCamera(viewer, toLoc(frames.get(0)));

        new BukkitRunnable(){
            @Override public void run(){
                ReplayFrame f = tl.next();
                if(f==null){ stop(); cancel(); return; }
                q498p.get().getCameraEngine().move(viewer, toLoc(f));
            }
        }.runTaskTimer(q498p.get(),1L,1L);
    }

    private Location toLoc(ReplayFrame f){
        return new Location(viewer.getWorld(), f.pos().getX(), f.pos().getY(), f.pos().getZ(), f.yaw(), f.pitch());
    }

    public void speed(double s){ if(tl!=null) tl.speed(s); }
    public void pause(){ if(tl!=null) tl.pause(); }
    public void resume(){ if(tl!=null) tl.resume(); }
    public void stop(){
        q498p.get().getCameraEngine().stop(viewer);
        viewer.setGameMode(GameMode.SURVIVAL);
    }
}
