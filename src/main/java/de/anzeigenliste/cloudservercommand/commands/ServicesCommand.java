package de.anzeigenliste.cloudservercommand.commands;

import dev.derklaro.aerogel.Inject;
import dev.derklaro.aerogel.Singleton;
import eu.cloudnetservice.driver.provider.CloudServiceProvider;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Collectors;

@Singleton
public class ServicesCommand implements CommandExecutor {

    private final CloudServiceProvider cloudServiceProvider;

    @Inject
    public ServicesCommand(CloudServiceProvider cloudServiceProvider) {
        this.cloudServiceProvider = cloudServiceProvider;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            if (args.length != 0) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d&lGalaxyMining &8• &7/join"));
                return false;
            }
            String services = cloudServiceProvider.services().stream()
                    .map(serviceInfoSnapshot -> serviceInfoSnapshot.serviceId().name())
                    .collect(Collectors.joining(", "));

            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d&lGalaxyMining &8• &eServices&8: &7" + services));
            return true;
        }
        return false;
    }
}
