package me.seetch.essentials.command.defaults.home;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import me.seetch.essentials.EssentialsAPI;
import me.seetch.essentials.command.CommandBase;
import me.seetch.format.Format;

public class SetHomeCommand extends CommandBase {

    public SetHomeCommand(EssentialsAPI api) {
        super("sethome", "§r§dУстанавливает точку дома.", api);
        this.commandParameters.clear();
        this.commandParameters.put("default", new CommandParameter[]{CommandParameter.newType("name", false, CommandParamType.STRING)});
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
            sender.sendMessage(Format.RED.colorize("\uE112", "Вы можете установить точку дома %0, только поспав на кровати.", "bed"));
            return false;
        } else if (args[0].trim().equals("")) {
            sender.sendMessage(Format.RED.colorize("\uE112", "Название точки дома не может быть пустым."));
            return false;
        }
        // TODO: Implements permission manager.
        if (api.getHomesList((Player) sender).length >= 2) {
            sender.sendMessage(Format.RED.colorize("\uE112", "Вы можете создать только %0 точки дома.", "2"));
            return false;
        }
        sender.sendMessage(api.setHome((Player) sender, args[0].toLowerCase(), (Player) sender) ? Format.YELLOW.colorize("\uE110", "Точка дома %0 обновлена.", args[0]) : Format.GREEN.colorize("\uE111", "Точка дома %0 установлена.", args[0]));
        return true;
    }
}
