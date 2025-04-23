package ru.he3hauka.hnear.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.he3hauka.hnear.config.Config;
import ru.he3hauka.hnear.utils.Localization;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.he3hauka.hnear.utils.HexSupport.format;

public class CommandHandler implements CommandExecutor, TabCompleter {
    private final Config config;
    private String locale;
    private Localization localization;

    public CommandHandler(Config config, String locale) {
        this.config = config;
        this.locale = locale;
        this.localization = new Localization(locale);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (args.length == 0) {
            sendMessage(sender, "§6/" + label + " reload");
            return true;
        }

        if (sender.hasPermission("hnear.admin") || sender.hasPermission("*") || sender.isOp()) {
            sendMessage(sender, localization.get("no_permission"));
            return false;
        }

        String arg = args[0].toLowerCase();
        switch (arg) {
            case "reload":
            case "reboot": {
                this.locale = config.locale;
                this.localization = new Localization(this.locale);
                sendMessage(sender, localization.get("reloading_plugin"));
                long startTime = System.currentTimeMillis();
                config.init();
                long reloadTime = System.currentTimeMillis() - startTime;
                sendMessage(sender, localization.get("plugin_reloaded", reloadTime));
                return true;
            }
            default:
                sendMessage(sender, "§6/" + label + " reload");
                return true;
        }
    }

    private void sendMessage(CommandSender sender, String message) {
        String prefixMessage = config.prefix.isEmpty() ? message : format(config.prefix + (config.prefix.endsWith(" ") ? "" : " ") + message);
        sender.sendMessage(prefixMessage);
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
