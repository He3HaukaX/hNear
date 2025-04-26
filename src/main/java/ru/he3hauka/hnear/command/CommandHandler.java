package ru.he3hauka.hnear.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.he3hauka.hnear.config.Config;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommandHandler implements CommandExecutor, TabCompleter {
    private final Config config;

    public CommandHandler(Config config) {
        this.config = config;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!sender.hasPermission("hnear.admin") || !sender.hasPermission("*") || !sender.isOp()) {
            sender.sendMessage("§7[§x§F§B§9§C§0§8hNear§7] §fYou don't have enough rights!");
            return false;
        }

        if (args.length == 0) {
            sender.sendMessage("§x§F§B§9§C§0§8╔");
            sender.sendMessage("§x§F§B§9§C§0§8╠ §f/" + label + " reload §7(§x§F§B§9§C§0§8Plugin reload§7)");
            sender.sendMessage("§x§F§B§9§C§0§8╚");
            return true;
        }

        String arg = args[0].toLowerCase();
        switch (arg) {
            case "reload":
            case "reboot": {
                sender.sendMessage("§7[§x§F§B§9§C§0§8hNear§7] §fAn attempt to §x§F§B§9§C§0§8restart§f the plugin...!");
                long startTime = System.currentTimeMillis();
                config.init();
                long reloadTime = System.currentTimeMillis() - startTime;
                sender.sendMessage("§7[§x§F§B§9§C§0§8hNear§7] §fPlugin refreshed in §x§F§B§9§C§0§8" + reloadTime + "§f ms!");
                return true;
            }
            default:
                sender.sendMessage("§x§F§B§9§C§0§8╔");
                sender.sendMessage("§x§F§B§9§C§0§8╠ §f/" + label + " reload §7(§x§F§B§9§C§0§8Plugin reload§7)");
                sender.sendMessage("§x§F§B§9§C§0§8╚");
                return true;
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (args.length == 1) {
            return Stream.of("reload", "reboot")
                    .filter(s -> s.toLowerCase().startsWith(args[0].toLowerCase()))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
