package me.seetch.essentials.command.defaults.teleport;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import me.seetch.essentials.EssentialsAPI;
import me.seetch.essentials.command.CommandBase;
import me.seetch.essentials.util.TPRequest;
import me.seetch.format.Format;

public class TPAcceptCommand extends CommandBase {

    public TPAcceptCommand(EssentialsAPI api) {
        super("tpaccept", "§r§qОтправляет запрос игроку на телепортацию к нему.", api);
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
            sender.sendMessage(Format.MATERIAL_REDSTONE.colorize("У Вас нет запросов на телепортацию."));
            return false;
        }
        TPRequest request;
        Player from;
        if (args.length == 0) {
            if ((request = api.getLatestTPRequestTo(to)) == null) {
                sender.sendMessage(Format.MATERIAL_REDSTONE.colorize("Запрос на телепортацию недоступен."));
                return false;
            }
            from = request.from();
        } else {
            from = api.getServer().getPlayer(args[0]);
            if (from == null) {
                sender.sendMessage(Format.MATERIAL_REDSTONE.colorize("Указанный игрок не найден."));
                return false;
            }
            if ((request = api.getTPRequestBetween(from, to)) != null) {
                sender.sendMessage(Format.MATERIAL_REDSTONE.colorize("У Вас нет запросов на телепортацию от %0.", from.getName()));
                return false;
            }
        }
        from.sendMessage(Format.MATERIAL_EMERALD.colorize("Игрок %0 принял Ваш запрос на телепортацию.", to.getName()));
        if (request.isTo()) {
            from.teleport(to);
            sender.sendMessage(Format.YELLOW.colorize("Игрок %0 телепортировался к Вам.", from.getName()));
        } else {
            to.teleport(from);
            sender.sendMessage(Format.YELLOW.colorize("Вы телепортировались к игроку %0.", from.getName()));
        }
        api.removeTPRequestBetween(from, to);
        return true;
    }
}
