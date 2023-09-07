package me.seetch.essentials.command.defaults.home;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import me.seetch.essentials.EssentialsAPI;
import me.seetch.essentials.command.CommandBase;
import me.seetch.format.Format;

public class DelHomeCommand extends CommandBase {

    public DelHomeCommand(EssentialsAPI api) {
        super("delhome", "§r§qУдаляет точку дома.", api);
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
        if (!api.isHomeExists((Player) sender, args[0].toLowerCase())) {
            sender.sendMessage(Format.MATERIAL_REDSTONE.colorize("Точки дома %0 не существует.", args[0]));
            return false;
        }
        api.removeHome((Player) sender, args[0].toLowerCase());
        sender.sendMessage(Format.MATERIAL_EMERALD.colorize("Точка дома %0 удалена.", args[0]));
        return true;
    }
}
