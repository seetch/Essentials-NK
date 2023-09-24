package me.seetch.essentials.command.defaults.teleport;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import me.seetch.essentials.EssentialsAPI;
import me.seetch.essentials.command.CommandBase;
import me.seetch.format.Format;

public class TPAllCommand extends CommandBase {

    public TPAllCommand(EssentialsAPI api) {
        super("tpall", "Телепортирует всех игроков к Вам.", api);
    }

    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            this.sendPermissionMessage(sender);
            return false;
        }
        Player player;
        if (args.length == 0) {
            if (!this.testIngame(sender)) {
                return false;
            }
            player = (Player) sender;
        } else if (args.length == 1) {
            player = api.getServer().getPlayer(args[0]);
            if (player == null) {
                sender.sendMessage(Format.RED.colorize("", "Указанный игрок не найден."));
                return false;
            }
        } else {
            this.sendUsage(sender, "");
            return false;
        }
        for (Player p : api.getServer().getOnlinePlayers().values()) {
            if (p != player) {
                p.teleport(player);
                p.sendMessage(Format.YELLOW.colorize("", "Вы были телепортированы к %0.", player.getName()));
            }
        }
        player.sendMessage(Format.GREEN.colorize("", "Вы успешно телепортировали всех к себе."));
        return true;
    }
}
