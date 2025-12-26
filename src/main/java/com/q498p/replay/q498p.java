package com.q498p.replay;

import com.q498p.replay.camera.PacketCameraEngine;
import com.q498p.replay.core.ReplayManager;
import com.q498p.replay.listeners.CombatListener;
import com.q498p.replay.listeners.JoinQuitListener;
import com.q498p.replay.commands.ReplayCommand;
import com.q498p.replay.commands.ReplayCtlCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class q498p extends JavaPlugin {

    private static q498p instance;
    private ReplayManager replayManager;
    private PacketCameraEngine cameraEngine;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        replayManager = new ReplayManager(this);
        cameraEngine = new PacketCameraEngine(this);

        Bukkit.getPluginManager().registerEvents(new CombatListener(), this);
        Bukkit.getPluginManager().registerEvents(new JoinQuitListener(), this);

        getCommand("replay").setExecutor(new ReplayCommand());
        getCommand("replayctl").setExecutor(new ReplayCtlCommand());

        getLogger().info("q498p FINAL Replay Engine enabled");
    }

    @Override
    public void onDisable() {
        if (replayManager != null) replayManager.shutdown();
        if (cameraEngine != null) cameraEngine.shutdown();
    }

    public static q498p get() { return instance; }
    public ReplayManager getReplayManager() { return replayManager; }
    public PacketCameraEngine getCameraEngine() { return cameraEngine; }
}
