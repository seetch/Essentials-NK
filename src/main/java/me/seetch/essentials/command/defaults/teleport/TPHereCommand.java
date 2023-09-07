package me.seetch.essentials.command.defaults.teleport;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import me.seetch.essentials.EssentialsAPI;
import me.seetch.essentials.command.CommandBase;
import me.seetch.format.Format;

public class TPHereCommand extends CommandBase {

    public TPHereCommand(EssentialsAPI api) {
        super("tphere", "Телепортирует игрока к Вам.", api);
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
            this.sendUsage(sender, "<игрок>");
            return false;
        }
        Player player = api.getServer().getPlayer(args[0]);
        if (player == null) {
            sender.sendMessage(Format.MATERIAL_REDSTONE.colorize("Указанный игрок не найден."));
            return false;
        }
        player.teleport((Player) sender);
        player.sendMessage(Format.YELLOW.colorize("Вы были телепортированы к %0.", sender.getName()));
        player.sendMessage(Format.MATERIAL_EMERALD.colorize("Вы успешно телепортировали %0 к себе.", player.getName()));
        return true;
    }
}
