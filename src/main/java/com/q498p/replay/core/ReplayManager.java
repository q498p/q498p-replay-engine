package com.q498p.replay.core;

import com.q498p.replay.q498p;
import com.q498p.replay.record.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;

public class ReplayManager {
    private final q498p plugin;
    private final Map<UUID, ReplaySession> sessions = new HashMap<>();
    private final MovementRecorder move = new MovementRecorder();
    private final VelocityRecorder vel = new VelocityRecorder();
    private BukkitTask tick;
    private final int maxFrames;

    public ReplayManager(q498p plugin){
        this.plugin = plugin;
        int seconds = plugin.getConfig().getInt("replay.seconds", 120);
        this.maxFrames = seconds * 20;
        start();
    }

    private void start(){
        tick = Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            for(Player p: Bukkit.getOnlinePlayers()){
                ReplaySession s = getOrCreate(p.getUniqueId());
                move.record(p, s);
                vel.record(p, s);
            }
        },1L,1L);
    }

    public synchronized ReplaySession getOrCreate(UUID id){
        return sessions.computeIfAbsent(id, k -> new ReplaySession(k, maxFrames));
    }
    public synchronized ReplaySession get(UUID id){ return sessions.get(id); }
    public synchronized void remove(UUID id){ sessions.remove(id); }
    public void shutdown(){ if(tick!=null) tick.cancel(); sessions.clear(); }
}
