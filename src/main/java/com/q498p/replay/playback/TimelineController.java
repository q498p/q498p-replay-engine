package com.q498p.replay.playback;

import com.q498p.replay.buffer.ReplayFrame;
import java.util.List;

public class TimelineController {
    private final List<ReplayFrame> frames; private int i=0; private double speed=1.0; private boolean play=true;
    public TimelineController(List<ReplayFrame> frames){ this.frames=frames; }
    public ReplayFrame next(){ if(!play||i>=frames.size()) return null; ReplayFrame f=frames.get(i); i+=Math.max(1,(int)speed); return f; }
    public void speed(double s){ speed=Math.max(0.25,Math.min(4.0,s)); }
    public void pause(){ play=false; } public void resume(){ play=true; }
}
