package me.seetch.essentials.command.defaults.home;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import me.seetch.essentials.EssentialsAPI;
import me.seetch.essentials.command.CommandBase;
import me.seetch.format.Format;

public class SetHomeCommand extends CommandBase {

    public SetHomeCommand(EssentialsAPI api) {
        super("sethome", "§r§qУстанавливает точку дома.", api);
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
        if (args[0].equalsIgnoreCase("bed")) {
            sender.sendMessage(Format.MATERIAL_REDSTONE.colorize("Вы можете установить точку дома %0, только поспав на кровати.", "bed"));
            return false;
        } else if (args[0].trim().equals("")) {
            sender.sendMessage(Format.MATERIAL_REDSTONE.colorize("Название точки дома не может быть пустым."));
            return false;
        }
        // TODO: Implements permission manager.
        if (api.getHomesList((Player) sender).length >= 2) {
            sender.sendMessage(Format.MATERIAL_REDSTONE.colorize("Вы можете создать только %0 точки дома.", "2"));
            return false;
        }
        sender.sendMessage(api.setHome((Player) sender, args[0].toLowerCase(), (Player) sender) ? Format.YELLOW.colorize("Точка дома %0 обновлена.", args[0]) : Format.MATERIAL_EMERALD.colorize("Точка дома %0 установлена.", args[0]));
        return true;
    }
}
