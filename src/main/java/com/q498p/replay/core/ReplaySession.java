package com.q498p.replay.core;

import com.q498p.replay.buffer.ReplayFrame;
import com.q498p.replay.buffer.RingBuffer;
import java.util.*;

public class ReplaySession {
    private final UUID owner;
    private final RingBuffer<ReplayFrame> buffer;
    private ReplayState state = ReplayState.RECORDING;

    public ReplaySession(UUID owner, int maxFrames){
        this.owner = owner;
        this.buffer = new RingBuffer<>(maxFrames);
    }
    public synchronized void record(ReplayFrame f){
        if(state==ReplayState.RECORDING) buffer.add(f);
    }
    public synchronized List<ReplayFrame> frames(){ return buffer.getAll(); }
    public UUID owner(){ return owner; }
    public ReplayState state(){ return state; }
    public void setState(ReplayState s){ state=s; }
}
