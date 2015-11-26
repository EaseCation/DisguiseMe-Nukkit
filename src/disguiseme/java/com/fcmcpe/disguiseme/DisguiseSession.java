package com.fcmcpe.disguiseme;

import cn.nukkit.Player;
import cn.nukkit.network.protocol.AddEntityPacket;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by funcraft on 2015/11/7.
 */
class DisguiseSession {

    private Player player;
    private int type;

    public DisguiseSession(Player player, int type) {
        this.player = player;
        this.type = type;
        this.startDisguise();
    }

    public void startDisguise() {
        this.player.despawnFromAll();
        AddEntityPacket pk = new AddEntityPacket();
        pk.eid = this.player.getId();
        pk.type = this.type;
        pk.x = (float) this.player.getX();
        pk.y = (float) this.player.getY();
        pk.z = (float) this.player.getZ();
        pk.speedX = (float) this.player.motionX;
        pk.speedY = (float) this.player.motionY;
        pk.speedZ = (float) this.player.motionZ;
        pk.pitch = (float) this.player.pitch;
        pk.yaw = (float) this.player.yaw;
        pk.metadata = new HashMap<>();

        for (Player p: this.player.getLevel().getPlayers().values()) {
            if (p.canSee(this.player) && !p.getName().equals(this.player.getName())) {
                p.dataPacket(pk);
            }
        }
    }

    public void despawnDisguise() {
        this.player.despawnFromAll();;
    }

    public int getType() {
        return type;
    }

    public Player getPlayer() {
        return player;
    }
}
