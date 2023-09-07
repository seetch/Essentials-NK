package me.seetch.essentials.command.defaults;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import me.seetch.essentials.EssentialsAPI;
import me.seetch.essentials.command.CommandBase;
import me.seetch.format.Format;

public class SetSpawnCommand extends CommandBase {

    public SetSpawnCommand(EssentialsAPI api) {
        super("setspawn", "Устанавливает спавн.", api);
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            this.sendPermissionMessage(sender);
            return false;
        }

        if (!this.testIngame(sender)) {
            sender.sendMessage(Format.MATERIAL_REDSTONE.colorize("Эта команда должна быть выполнена в игре."));
            return false;
        }

        if (args.length != 0) {
            this.sendUsage(sender, "");
            return false;
        }

        Player p = (Player) sender;
        getAPI().getServer().setDefaultLevel(p.getLevel());
        p.getLevel().setSpawnLocation(p);

        p.sendMessage(Format.YELLOW.colorize("Спавн сервера установлен."));
        getAPI().getLogger().info(TextFormat.YELLOW + "Спавн сервера установлена на " + TextFormat.AQUA + p.getLevel().getName() + TextFormat.YELLOW + " игроком " + TextFormat.GREEN + p.getName());
        return true;
    }
}
