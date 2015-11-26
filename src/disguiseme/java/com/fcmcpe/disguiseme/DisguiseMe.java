package com.fcmcpe.disguiseme;

import cn.nukkit.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2015/11/26 by xtypr.
 * Package com.fcmcpe.disguiseme in project CodeFunCore .
 */
public enum DisguiseMe {
    //单例大法无限好
    INSTANCE;

    private Map<Player, DisguiseSession> e = new HashMap<>();

    private DisguiseMe(){

    }

    public boolean disguise(Player player, int eid) {
        if (player.isOnline()) {
            if (this.isDisguised(player)) {
                this.destroyDisguise(player);
            }
            DisguiseSession s = new DisguiseSession(player, eid);
            this.e.put(player, s);
            return true;
        }
        return false;
    }

    public void destroyDisguise(Player player) {
        if (this.isDisguised(player)) {
            DisguiseSession session = this.e.get(player);
            session.despawnDisguise();
            this.e.remove(player);
            player.spawnToAll();
        }
    }

    public DisguiseSession getDisguiseSessionByEID(long eid) {
        final DisguiseSession[] result = {null};
        this.e.forEach((p, s)->{
            if(p.getId() == eid) result[0] = s;
        });
        return result[0];
    }

    public boolean isDisguised(Player player) {
        return (this.e.containsKey(player));
    }

    public boolean isDisguised(long eid) {
        final boolean[] result = {false};
        this.e.forEach((p, s)->{
            if(p.getId() == eid) result[0] =true;
        });
        return result[0];
    }
}
