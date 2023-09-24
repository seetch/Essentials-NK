package me.seetch.essentials.command.defaults.teleport;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import me.seetch.essentials.EssentialsAPI;
import me.seetch.essentials.command.CommandBase;
import me.seetch.format.Format;

public class TPAHereCommand extends CommandBase {

    public TPAHereCommand(EssentialsAPI api) {
        super("tpahere", "§r§dОтправляет запрос игроку на телепортацию к Вам.", api);
        this.commandParameters.clear();
        this.commandParameters.put("default", new CommandParameter[]{CommandParameter.newType("player", false, CommandParamType.TARGET)});
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
            sender.sendMessage(Format.RED.colorize("", "Указанный игрок не найден."));
            return false;
        }
        if (sender == player) {
            sender.sendMessage(Format.RED.colorize("", "Вы не можете отправить запрос самому себе."));
            return false;
        }
        api.requestTP((Player) sender, player, false);
        player.sendMessage(Format.AQUA.colorize("\uE110", "Игрок %0 хочет, чтобы Вы телепортировались к нему, используйте %1, чтобы принять, %2, чтобы отклонить запрос.", ((Player) sender).getName(), "/tpaccept", "/tpdeny"));
        sender.sendMessage(Format.GREEN.colorize("", "Запрос на телепортацию к %0 отправлен.", player.getName()));
        return true;
    }
}
