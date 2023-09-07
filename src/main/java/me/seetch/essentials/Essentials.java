package me.seetch.essentials;

import cn.nukkit.plugin.PluginBase;
import lombok.Getter;
import me.seetch.essentials.command.CommandManager;
import me.seetch.essentials.listener.EventListener;

@Getter
public class Essentials extends PluginBase {

    private EssentialsAPI api;

    @Override
    public void onEnable() {
        this.api = new EssentialsAPI(this);
        this.getServer().getPluginManager().registerEvents(new EventListener(this.api), this);
        CommandManager.registerAll(this.api);
    }
}