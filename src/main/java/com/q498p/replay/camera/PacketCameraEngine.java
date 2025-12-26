package com.q498p.replay.camera;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import com.comphenix.protocol.wrappers.WrappedDataWatcher.Registry;
import com.q498p.replay.q498p;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.*;

import static com.comphenix.protocol.PacketType.Play.Server.*;

public class PacketCameraEngine {

    private final ProtocolManager protocol = ProtocolLibrary.getProtocolManager();
    private final Map<UUID, Integer> cameraEntityIds = new HashMap<>();
    private final q498p plugin;
    private int nextEntityId = 900000;

    public PacketCameraEngine(q498p plugin){ this.plugin = plugin; }

    public void startCamera(Player viewer, Location start){
        int id = nextEntityId++;
        cameraEntityIds.put(viewer.getUniqueId(), id);

        PacketContainer spawn = protocol.createPacket(SPAWN_ENTITY);
        spawn.getIntegers().write(0, id);
        spawn.getEntityTypeModifier().write(0, EntityType.ARMOR_STAND);
        spawn.getDoubles().write(0, start.getX()).write(1, start.getY()).write(2, start.getZ());
        protocol.sendServerPacket(viewer, spawn);

        PacketContainer cam = protocol.createPacket(CAMERA);
        cam.getIntegers().write(0, id);
        protocol.sendServerPacket(viewer, cam);
    }

    public void move(Player viewer, Location loc){
        Integer id = cameraEntityIds.get(viewer.getUniqueId());
        if(id==null) return;
        PacketContainer move = protocol.createPacket(ENTITY_TELEPORT);
        move.getIntegers().write(0, id);
        move.getDoubles().write(0, loc.getX()).write(1, loc.getY()).write(2, loc.getZ());
        move.getBytes().write(0, (byte)((loc.getYaw()%360)*256/360)).write(1, (byte)((loc.getPitch()%360)*256/360));
        protocol.sendServerPacket(viewer, move);
    }

    public void stop(Player viewer){
        Integer id = cameraEntityIds.remove(viewer.getUniqueId());
        if(id==null) return;
        PacketContainer cam = protocol.createPacket(CAMERA);
        cam.getIntegers().write(0, viewer.getEntityId());
        protocol.sendServerPacket(viewer, cam);

        PacketContainer destroy = protocol.createPacket(ENTITY_DESTROY);
        destroy.getIntLists().write(0, List.of(id));
        protocol.sendServerPacket(viewer, destroy);
    }

    public void shutdown(){ cameraEntityIds.clear(); }
}
