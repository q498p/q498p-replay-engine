package com.q498p.replay.camera;
import org.bukkit.util.Vector;
public class CameraMath{
    public static Vector lerp(Vector a, Vector b, double t){ return a.clone().multiply(1-t).add(b.clone().multiply(t)); }
    public static float lerpA(float a,float b,float t){ return a+(b-a)*t; }
}
