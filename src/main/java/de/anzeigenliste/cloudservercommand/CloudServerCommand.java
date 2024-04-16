package de.anzeigenliste.cloudservercommand;

import de.anzeigenliste.cloudservercommand.commands.ServicesCommand;
import dev.derklaro.aerogel.Inject;
import dev.derklaro.aerogel.Singleton;
import eu.cloudnetservice.ext.platforminject.api.PlatformEntrypoint;
import eu.cloudnetservice.ext.platforminject.api.stereotype.Command;
import eu.cloudnetservice.ext.platforminject.api.stereotype.Dependency;
import eu.cloudnetservice.ext.platforminject.api.stereotype.PlatformPlugin;
import lombok.NonNull;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;


@Singleton
@PlatformPlugin(
        platform = "bukkit",
        pluginFileNames = "plugin.yml",
        name = "CloudServerCommand",
        version = "1.0",
        description = "Sends Players to a Server",
        authors = "Anzeigenliste",
        dependencies = {@Dependency(name = "CloudNet-Bridge")},
        commands = {
                @Command(
                        name = "services",
                        description = "See the services",
                        usage = "/services"
                )
        }
)
public class CloudServerCommand implements PlatformEntrypoint {

    private final Plugin plugin;
    private final ServicesCommand servicesCommand;

    @Inject
    public CloudServerCommand(
            @NonNull Plugin plugin,
            @NonNull ServicesCommand servicesCommand
    ) {
        this.plugin = plugin;
        this.servicesCommand = servicesCommand;
    }


    @Override
    public void onLoad() {
        plugin.getLogger().info("Loaded CloudServerCommand Plugin");
        registerCommands();
    }

    @Override
    public void onDisable() {
        plugin.getLogger().info("Disabled CloudServerCommand Plugin");
    }

    private void registerCommands() {
        plugin.getLogger().info("Registering Commands");
        PluginCommand servicesCommandPluginCommand = plugin.getServer().getPluginCommand("services");
        if (servicesCommandPluginCommand != null) {
            servicesCommandPluginCommand.setExecutor(servicesCommand);
        }
        plugin.getLogger().info("Successfully Commands");
    }
}
