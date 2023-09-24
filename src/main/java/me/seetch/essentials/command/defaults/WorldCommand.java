package me.seetch.essentials.command.defaults;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import me.seetch.essentials.EssentialsAPI;
import me.seetch.essentials.command.CommandBase;
import me.seetch.format.Format;

public class WorldCommand extends CommandBase {

    public WorldCommand(EssentialsAPI api) {
        super("world", "Телепортирует Вас между мирами.", api);
    }

    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            this.sendPermissionMessage(sender);
            return false;
        }
        if (!this.testIngame(sender)) {
            return false;
        }
        if (args.length != 1) {
            this.sendUsage(sender, "<название>");
            return false;
        }
        if (!api.getServer().isLevelGenerated(args[0])) {
            sender.sendMessage(Format.RED.colorize("", "Мир %0 не найден.", args[0]));
            return false;
        } else if (!api.getServer().isLevelLoaded(args[0])) {
            sender.sendMessage(Format.YELLOW.colorize("", "Мир еще не загружен. %0", "Загрузка..."));
            if (!api.getServer().loadLevel(args[0])) {
                sender.sendMessage(Format.RED.colorize("", "Не удалось загрузить мир."));
                return false;
            }
        }
        ((Player) sender).teleport(api.getServer().getLevelByName(args[0]).getSpawnLocation());
        sender.sendMessage(Format.GREEN.colorize("", "Вы были телепортированы в мир %0.", args[0]));
        return true;
    }
}
