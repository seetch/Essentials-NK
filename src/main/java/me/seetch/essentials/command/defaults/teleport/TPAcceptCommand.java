package me.seetch.essentials.command.defaults.teleport;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import me.seetch.essentials.EssentialsAPI;
import me.seetch.essentials.command.CommandBase;
import me.seetch.essentials.util.TPRequest;
import me.seetch.format.Format;

public class TPAcceptCommand extends CommandBase {

    public TPAcceptCommand(EssentialsAPI api) {
        super("tpaccept", "§r§dОтправляет запрос игроку на телепортацию к нему.", api);
        this.commandParameters.clear();
        this.commandParameters.put("default", new CommandParameter[]{CommandParameter.newType("player", true, CommandParamType.TARGET)});
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
            this.sendUsage(sender, "[игрок]");
            return false;
        }
        Player to = (Player) sender;
        if (api.getLatestTPRequestTo(to) == null) {
            sender.sendMessage(Format.RED.colorize("", "У Вас нет запросов на телепортацию."));
            return false;
        }
        TPRequest request;
        Player from;
        if (args.length == 0) {
            if ((request = api.getLatestTPRequestTo(to)) == null) {
                sender.sendMessage(Format.RED.colorize("", "Запрос на телепортацию недоступен."));
                return false;
            }
            from = request.from();
        } else {
            from = api.getServer().getPlayer(args[0]);
            if (from == null) {
                sender.sendMessage(Format.RED.colorize("", "Указанный игрок не найден."));
                return false;
            }
            if ((request = api.getTPRequestBetween(from, to)) != null) {
                sender.sendMessage(Format.RED.colorize("", "У Вас нет запросов на телепортацию от %0.", from.getName()));
                return false;
            }
        }
        from.sendMessage(Format.GREEN.colorize("", "Игрок %0 принял Ваш запрос на телепортацию.", to.getName()));
        if (request.isTo()) {
            from.teleport(to);
            sender.sendMessage(Format.AQUA.colorize("\uE110", "Игрок %0 телепортировался к Вам.", from.getName()));
        } else {
            to.teleport(from);
            sender.sendMessage(Format.AQUA.colorize("\uE110", "Вы телепортировались к игроку %0.", from.getName()));
        }
        api.removeTPRequestBetween(from, to);
        return true;
    }
}
