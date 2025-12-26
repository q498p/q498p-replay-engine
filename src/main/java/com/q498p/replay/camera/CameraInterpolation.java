package com.q498p.replay.camera;
import com.q498p.replay.buffer.ReplayFrame; import org.bukkit.util.Vector;
public class CameraInterpolation{
    public static Vector pos(ReplayFrame a, ReplayFrame b, double t){ return CameraMath.lerp(a.pos(), b.pos(), t); }
    public static float yaw(ReplayFrame a, ReplayFrame b, double t){ return CameraMath.lerpA(a.yaw(), b.yaw(), (float)t); }
    public static float pitch(ReplayFrame a, ReplayFrame b, double t){ return CameraMath.lerpA(a.pitch(), b.pitch(), (float)t); }
}
