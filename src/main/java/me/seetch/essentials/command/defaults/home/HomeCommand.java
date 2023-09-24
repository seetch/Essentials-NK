package me.seetch.essentials.command.defaults.home;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.level.Location;
import me.seetch.essentials.EssentialsAPI;
import me.seetch.essentials.command.CommandBase;
import me.seetch.format.Format;

public class HomeCommand extends CommandBase {

    public HomeCommand(EssentialsAPI api) {
        super("home", "§r§dТелепортирует Вас к точке дома.", api);
        this.commandParameters.clear();
        this.commandParameters.put("default", new CommandParameter[]{CommandParameter.newType("name", true, CommandParamType.STRING)});
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
                sender.sendMessage(Format.RED.colorize("\uE112", "У Вас еще нет установленных точек дома."));
                return false;
            }
            sender.sendMessage(Format.GREEN.colorize("\uE111", "Ваши точки дома: %0.", String.join("§7, §a", list)));
            return true;
        }
        Location home = api.getHome(player, args[0].toLowerCase());
        if (home == null) {
            sender.sendMessage(Format.RED.colorize("\uE112", "Точки дома %0 не существует.", args[0]));
            return false;
        }
        player.teleport(home);
        sender.sendMessage(Format.GREEN.colorize("\uE111", "Вы были телепортированы к точке дома %0.", args[0]));
        return true;
    }
}
