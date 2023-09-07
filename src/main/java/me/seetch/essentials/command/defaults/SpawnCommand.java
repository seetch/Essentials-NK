package me.seetch.essentials.command.defaults;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import me.seetch.essentials.EssentialsAPI;
import me.seetch.essentials.command.CommandBase;
import me.seetch.format.Format;

public class SpawnCommand extends CommandBase {

    public SpawnCommand(EssentialsAPI api) {
        super("spawn", "§r§qТелепортирует Вас на спавн.", api);
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            this.sendPermissionMessage(sender);
            return false;
        }

        if (args.length == 0 && !this.testIngame(sender)) {
            sender.sendMessage(Format.MATERIAL_REDSTONE.colorize("Эта команда должна быть выполнена в игре."));
            return false;
        }

        if (args.length == 1 && !sender.hasPermission("essentialsnk.spawn.others")) {
            this.sendPermissionMessage(sender);
            return false;
        }

        if (args.length > 1) {
            this.sendUsage(sender, "[игрок]");
            return false;
        }

        Player p;

        if (args.length == 0) {
            p = (Player) sender;
        } else {
            p = getAPI().getServer().getPlayer(args[0]);
        }

        if (p == null || !p.isOnline()) {
            sender.sendMessage(Format.MATERIAL_REDSTONE.colorize("Указанный игрок не найден."));
            return false;
        }

        p.teleport(getAPI().getServer().getDefaultLevel().getSpawnLocation());
        p.sendMessage(Format.MATERIAL_EMERALD.colorize("Вы были телепортированы на %0.", "спавн"));
        return true;
    }
}
