package me.seetch.essentials.command.defaults.teleport;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import me.seetch.essentials.EssentialsAPI;
import me.seetch.essentials.command.CommandBase;
import me.seetch.format.Format;

public class TPAHereCommand extends CommandBase {

    public TPAHereCommand(EssentialsAPI api) {
        super("tpahere", "§r§qОтправляет запрос игроку на телепортацию к Вам.", api);
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
        if (sender == player) {
            sender.sendMessage(Format.MATERIAL_REDSTONE.colorize("Вы не можете отправить запрос самому себе."));
            return false;
        }
        api.requestTP((Player) sender, player, false);
        player.sendMessage(Format.YELLOW.colorize("Игрок %0 хочет, чтобы Вы телепортировались к нему, используйте %1, чтобы принять, %2, чтобы отклонить запрос.", ((Player) sender).getName(), "/tpaccept", "/tpdeny"));
        sender.sendMessage(Format.MATERIAL_EMERALD.colorize("Запрос на телепортацию к %0 отправлен.", player.getName()));
        return true;
    }
}
