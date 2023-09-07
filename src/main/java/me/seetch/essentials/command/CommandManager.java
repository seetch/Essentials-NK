package me.seetch.essentials.command;

import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandMap;
import cn.nukkit.command.SimpleCommandMap;
import me.seetch.essentials.EssentialsAPI;
import me.seetch.essentials.command.defaults.SetSpawnCommand;
import me.seetch.essentials.command.defaults.SpawnCommand;
import me.seetch.essentials.command.defaults.WorldCommand;
import me.seetch.essentials.command.defaults.home.DelHomeCommand;
import me.seetch.essentials.command.defaults.home.HomeCommand;
import me.seetch.essentials.command.defaults.home.SetHomeCommand;
import me.seetch.essentials.command.defaults.teleport.*;

public class CommandManager {

    public static void registerAll(EssentialsAPI api) {
        CommandMap map = api.getServer().getCommandMap();
        map.register("EssentialsNK", new WorldCommand(api));

        map.register("EssentialsNK", new DelHomeCommand(api));
        map.register("EssentialsNK", new HomeCommand(api));
        map.register("EssentialsNK", new SetHomeCommand(api));

        map.register("EssentialsNK", new TPACommand(api));
        map.register("EssentialsNK", new TPAcceptCommand(api));
        map.register("EssentialsNK", new TPAHereCommand(api));
        map.register("EssentialsNK", new TPAllCommand(api));
        map.register("EssentialsNK", new TPDenyCommand(api));
        map.register("EssentialsNK", new TPHereCommand(api));

        map.register("EssentialsNK", new SetSpawnCommand(api));
        map.register("EssentialsNK", new SpawnCommand(api));
    }

    public static void unregister(String name) {
        SimpleCommandMap commandMap = Server.getInstance().getCommandMap();
        Command command = commandMap.getCommand(name);
        command.setLabel("_disabled");
        command.unregister(commandMap);
    }
}