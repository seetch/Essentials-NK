package me.seetch.essentials.command.override;

import me.seetch.essentials.EssentialsAPI;
import me.seetch.essentials.command.CommandBase;
import me.seetch.essentials.command.CommandManager;

public abstract class OverrideBaseCommand extends CommandBase {

    public OverrideBaseCommand(String name, String description, EssentialsAPI api) {
        super(name, description, api);
        CommandManager.unregister(name);
    }
}
