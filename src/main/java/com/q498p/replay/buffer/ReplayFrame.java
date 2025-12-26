package com.q498p.replay.buffer;

import org.bukkit.util.Vector;
import java.util.UUID;

public class ReplayFrame {
    private final long time;
    private final Vector pos;
    private final float yaw,pitch;
    private final Vector vel;
    private final boolean hit;
    private final UUID target;

    public ReplayFrame(long time, Vector pos, float yaw, float pitch, Vector vel, boolean hit, UUID target){
        this.time=time; this.pos=pos; this.yaw=yaw; this.pitch=pitch; this.vel=vel; this.hit=hit; this.target=target;
    }
    public long time(){return time;} public Vector pos(){return pos;}
    public float yaw(){return yaw;} public float pitch(){return pitch;}
    public Vector vel(){return vel;} public boolean hit(){return hit;} public UUID target(){return target;}
}
