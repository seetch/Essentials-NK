package me.seetch.essentials.command;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import me.seetch.essentials.EssentialsAPI;
import me.seetch.format.Format;

public abstract class CommandBase extends Command {

    public final EssentialsAPI api;

    public CommandBase(String name, String description, EssentialsAPI api) {
        super(name, description);
        this.api = api;
        this.setPermission("essentialsnk." + name);
    }

    protected EssentialsAPI getAPI() {
        return api;
    }

    protected void sendUsage(CommandSender sender, String args) {
        sender.sendMessage(Format.YELLOW.colorize("\uE113", "Используйте: /%0 %1", this.getName(), args));
    }

    protected boolean testIngame(CommandSender sender) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Format.RED.colorize("\uE112", "Эта команда должна быть выполнена из консоли сервера."));
            return false;
        }
        return true;
    }

    protected void sendPermissionMessage(CommandSender sender) {
        sender.sendMessage(Format.RED.colorize("\uE113", "У Вас недостаточно прав для выполнения этой команды."));
    }
}