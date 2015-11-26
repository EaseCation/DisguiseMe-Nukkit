package com.fcmcpe.disguiseme;

import cn.nukkit.plugin.PluginBase;

/**
 * Created by funcraft on 2015/11/7.
 */
public class DisguiseMePlugin extends PluginBase {

    @Override
    public void onLoad() {
        getLogger().notice("DisguiseMe in Nukkit, rewritten by FunCraft Team, origin written by Falk.");
        getLogger().notice("For further information, visit https://github.com/Falkirks/DisguiseMe/");
    }

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new DisguiseListener(this), this);
    }

    @Override
    public void onDisable() {

    }

}
