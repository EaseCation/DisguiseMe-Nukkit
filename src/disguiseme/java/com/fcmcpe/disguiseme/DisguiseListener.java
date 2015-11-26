package com.fcmcpe.disguiseme;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.server.DataPacketSendEvent;
import cn.nukkit.network.protocol.*;

import java.util.HashMap;

/**
 * Created by funcraft on 2015/11/6.
 */
class DisguiseListener implements Listener {

    public DisguiseMePlugin main;

    public DisguiseListener(DisguiseMePlugin main) {
        this.main = main;
    }

    @EventHandler
    public void onPacketSend(DataPacketSendEvent event) {
        DataPacket pk = event.getPacket();
        if (pk instanceof MoveEntityPacket) {
            MoveEntityPacket pk0 = (MoveEntityPacket)pk;
            for (MoveEntityPacket.Entry entry : pk0.entities) {
                long eid = entry.eid;
                if (DisguiseMe.INSTANCE.isDisguised(eid)) {
                    entry.y -= 1.62;
                }
            }
        } else if (pk instanceof AddPlayerPacket) {
            AddPlayerPacket pk0 = (AddPlayerPacket) pk;
            AddEntityPacket newPk = new AddEntityPacket();
            newPk.eid = pk0.eid;
            DisguiseSession session = DisguiseMe.INSTANCE.getDisguiseSessionByEID(pk0.eid);
            if (session != null) {
                newPk.type = session.getType();
            } else {
                return;
            }
            newPk.x = pk0.x;
            newPk.y = pk0.y;
            newPk.z = pk0.z;
            newPk.pitch = pk0.pitch;
            newPk.yaw = pk0.yaw;
            newPk.metadata = new HashMap<>();

            event.getPlayer().dataPacket(newPk);
            event.setCancelled();
        }
        else if (pk instanceof RemovePlayerPacket) {
            RemovePlayerPacket pk0 = (RemovePlayerPacket) pk;
            RemoveEntityPacket newPk = new RemoveEntityPacket();
            newPk.eid = pk0.eid;

            event.getPlayer().dataPacket(newPk);
            event.setCancelled();
        }

    }

}
