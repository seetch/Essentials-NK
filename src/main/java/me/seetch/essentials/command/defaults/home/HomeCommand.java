package me.seetch.essentials.command.defaults.home;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Location;
import cn.nukkit.utils.TextFormat;
import me.seetch.essentials.EssentialsAPI;
import me.seetch.essentials.command.CommandBase;
import me.seetch.format.Format;

public class HomeCommand extends CommandBase {

    public HomeCommand(EssentialsAPI api) {
        super("home", "§r§qТелепортирует Вас к точке дома.", api);
    }

    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            this.sendPermissionMessage(sender);
            return false;
        }
        if (!this.testIngame(sender)) {
            return false;
        }
        if (args.length > 1) {
            this.sendUsage(sender, "<название>");
            return false;
        }
        Player player = (Player) sender;
        if (args.length == 0) {
            String[] list = api.getHomesList(player);
            if (list.length == 0) {
                sender.sendMessage(Format.MATERIAL_REDSTONE.colorize("У Вас еще нет установленных точек дома."));
                return false;
            }
            sender.sendMessage(Format.MATERIAL_EMERALD.colorize("Ваши точки дома: %0.", String.join("§7, §q", list)));
            return true;
        }
        Location home = api.getHome(player, args[0].toLowerCase());
        if (home == null) {
            sender.sendMessage(Format.MATERIAL_REDSTONE.colorize("Точки дома %0 не существует.", args[0]));
            return false;
        }
        player.teleport(home);
        sender.sendMessage(Format.MATERIAL_EMERALD.colorize("Вы были телепортированы к точке дома %0.", args[0]));
        return true;
    }
}
