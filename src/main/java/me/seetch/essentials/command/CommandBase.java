package me.seetch.essentials.command;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.lang.TranslationContainer;
import cn.nukkit.utils.TextFormat;
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
        sender.sendMessage(Format.YELLOW.colorize("Используйте: /%0 %1", this.getName(), args));
    }

    protected boolean testIngame(CommandSender sender) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Format.MATERIAL_REDSTONE.colorize("Эта команда должна быть выполнена из консоли сервера."));
            return false;
        }
        return true;
    }

    protected void sendPermissionMessage(CommandSender sender) {
        sender.sendMessage(Format.MATERIAL_REDSTONE.colorize("У Вас недостаточно прав для выполнения этой команды."));
    }
}