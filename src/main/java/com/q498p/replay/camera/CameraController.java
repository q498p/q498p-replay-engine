package com.q498p.replay.camera;

import com.q498p.replay.buffer.ReplayFrame;
import org.bukkit.Location; import org.bukkit.entity.Player;

public class CameraController {
    private final Player viewer; private ReplayFrame last;
    public CameraController(Player viewer){ this.viewer=viewer; }
    public void render(ReplayFrame f){
        if(last==null){ tp(f); last=f; return; }
        var p=CameraInterpolation.pos(last,f,0.7);
        float y=CameraInterpolation.yaw(last,f,0.7);
        float pi=CameraInterpolation.pitch(last,f,0.7);
        viewer.teleport(new Location(viewer.getWorld(), p.getX(),p.getY(),p.getZ(), y, pi));
        last=f;
    }
    private void tp(ReplayFrame f){
        var p=f.pos();
        viewer.teleport(new Location(viewer.getWorld(), p.getX(),p.getY(),p.getZ(), f.yaw(), f.pitch()));
    }
}
