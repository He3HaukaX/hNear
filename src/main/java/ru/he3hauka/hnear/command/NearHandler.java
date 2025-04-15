package ru.he3hauka.hnear.command;

import net.luckperms.api.LuckPerms;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ru.he3hauka.hnear.actions.ActionExecutor;
import ru.he3hauka.hnear.config.Config;
import ru.he3hauka.hnear.manager.CooldownManager;
import ru.he3hauka.hnear.service.NearbyService;
import ru.he3hauka.hnear.service.PermissionService;

import java.util.Collections;
import java.util.List;

public class NearHandler implements CommandExecutor {

    private final ActionExecutor actionExecutor;
    private final CooldownManager cooldownManager;
    private final PermissionService permissionService;
    private final NearbyService nearbyService;
    private final Config config;
    public NearHandler(Config config, LuckPerms luckPerms) {
        this.cooldownManager = new CooldownManager(config.cooldown_time);
        this.permissionService = new PermissionService(luckPerms, config.radius);
        this.actionExecutor = new ActionExecutor(config, permissionService);
        this.nearbyService = new NearbyService(config.hide_invisible, config.hide_ops);
        this.config = config;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(ChatColor.RED + "Только для игроков!");
            return true;
        }

        int maxRadius = permissionService.getMaxRadius(player.getUniqueId());

        if (cooldownManager.isCooldown(player.getUniqueId())) {
            long remaining = cooldownManager.getRemainingTime(player.getUniqueId()) / 1000;
            actionExecutor.execute(player, config.cooldown_actions, player.getLocation(), Collections.emptyList(), 0, String.valueOf(remaining));
            return true;
        }

        int radius = parseRadius(args, player, maxRadius);
        if (radius == -1) return true;

        if (radius > maxRadius) {
            actionExecutor.execute(player, config.radius_actions, player.getLocation(), Collections.emptyList(), radius, "0");
            return true;
        }

        cooldownManager.setCooldown(player.getUniqueId());

        List<Player> nearby = nearbyService.findPlayers(player, radius);

        List<String> actions = nearby.isEmpty() ? config.empty_actions : config.near_actions;
        actionExecutor.execute(player, actions, player.getLocation(), nearby, radius, "0");

        return true;
    }

    private int parseRadius(String[] args, Player player, int maxRadius) {
        if (args.length == 0) return maxRadius;

        try {
            return Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            actionExecutor.execute(player, config.integer_actions, player.getLocation(), Collections.emptyList(), 0, "0");
            return -1;
        }
    }
}
