package me.seetch.essentials.command.defaults.home;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import me.seetch.essentials.EssentialsAPI;
import me.seetch.essentials.command.CommandBase;
import me.seetch.format.Format;

public class DelHomeCommand extends CommandBase {

    public DelHomeCommand(EssentialsAPI api) {
        super("delhome", "§r§dУдаляет точку дома.", api);
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
        if (!api.isHomeExists((Player) sender, args[0].toLowerCase())) {
            sender.sendMessage(Format.RED.colorize("\uE112", "Точки дома %0 не существует.", args[0]));
            return false;
        }
        api.removeHome((Player) sender, args[0].toLowerCase());
        sender.sendMessage(Format.GREEN.colorize("\uE111", "Точка дома %0 удалена.", args[0]));
        return true;
    }
}
