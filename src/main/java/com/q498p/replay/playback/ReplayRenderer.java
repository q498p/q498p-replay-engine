package com.q498p.replay.playback;

import com.q498p.replay.buffer.ReplayFrame; import com.q498p.replay.camera.CameraController;
import com.q498p.replay.q498p; import org.bukkit.*; import org.bukkit.entity.Player; import org.bukkit.scheduler.BukkitTask;

public class ReplayRenderer{
    private final Player viewer; private final CameraController cam; private final TimelineController tl; private BukkitTask task;
    public ReplayRenderer(Player v, TimelineController tl){ this.viewer=v; this.tl=tl; this.cam=new CameraController(v); }
    public void start(){
        task=Bukkit.getScheduler().runTaskTimer(q498p.get(),()->{
            ReplayFrame f=tl.next(); if(f==null){ stop(); return; } cam.render(f);
        },1L,1L);
    }
    public void stop(){ if(task!=null) task.cancel(); }
}
