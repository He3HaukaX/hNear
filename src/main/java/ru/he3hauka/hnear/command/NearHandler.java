package ru.he3hauka.hnear.command;

import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;
import ru.he3hauka.hnear.actions.ActionExecutor;
import ru.he3hauka.hnear.config.Config;
import ru.he3hauka.hnear.manager.CooldownManager;
import ru.he3hauka.hnear.service.NearbyService;
import ru.he3hauka.hnear.service.PermissionService;

import java.util.*;
import java.util.logging.Level;

import static ru.he3hauka.hnear.utils.HexSupport.format;

public class NearHandler implements CommandExecutor {

    private final ActionExecutor actionExecutor;
    private final CooldownManager cooldownManager;
    private final PermissionService permissionService;
    private final NearbyService nearbyService;
    private final Config config;
    private final JavaPlugin plugin;
    private final Map<UUID, BukkitTask> autoTasks = new HashMap<>();
    private final Map<UUID, BossBar> bossBars = new HashMap<>();

    public NearHandler(JavaPlugin plugin, Config config, LuckPerms luckPerms) {
        this.plugin = plugin;
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

        if (args.length == 0) {
            actionExecutor.execute(player, config.help_actions, player.getLocation(), Collections.emptyList(), 0, "0", "N/A", "hnear.auto");
            return true;
        }

        if (args[0].equalsIgnoreCase("auto")) {
            if (!player.hasPermission("hnear.auto")) {
                actionExecutor.execute(player, config.noperms_actions, player.getLocation(), Collections.emptyList(), 0, "0", "N/A", "hnear.auto");
                return true;
            }
            handleAutoCommand(player);
            return true;
        }

        if (args[0].equalsIgnoreCase("radius") || args[0].equalsIgnoreCase("maxradius")) {
            if (!player.hasPermission("hnear.use")) {
                int maxRadius = permissionService.getMaxRadius(player.getUniqueId());
                actionExecutor.execute(player, config.noperms_actions, player.getLocation(), Collections.emptyList(), maxRadius, "0", "N/A", "hnear.use");
                return true;
            }
            return handleMaxRadius(player);
        }

        actionExecutor.execute(player, config.help_actions, player.getLocation(), Collections.emptyList(), 0, "0", "N/A", "hnear.auto");
        return true;
    }

    private boolean handleMaxRadius(Player player) {
        try {
            int maxRadius = permissionService.getMaxRadius(player.getUniqueId());

            if (cooldownManager.isCooldown(player.getUniqueId())) {
                long remaining = cooldownManager.getRemainingTime(player.getUniqueId()) / 1000;
                actionExecutor.execute(player, config.cooldown_actions, player.getLocation(),
                        Collections.emptyList(), 0, String.valueOf(remaining), "N/A", "N/A");
                return true;
            }

            cooldownManager.setCooldown(player.getUniqueId());
            List<Player> nearby = nearbyService.findPlayers(player, maxRadius);
            List<String> actions = nearby.isEmpty() ? config.empty_actions : config.near_actions;
            actionExecutor.execute(player, actions, player.getLocation(), nearby, maxRadius, "0", "N/A", "N/A");

            return true;
        } catch (Exception e) {
            player.sendMessage(format("&cПроизошла ошибка при выполнении команды"));
            plugin.getLogger().log(Level.SEVERE, "Ошибка в handleMaxRadius", e);
            return false;
        }
    }

    private void handleAutoCommand(Player player) {
        UUID uuid = player.getUniqueId();

        if (autoTasks.containsKey(uuid)) {
            cancelAutoTask(player);
            actionExecutor.execute(player, config.autotoggle_actions, player.getLocation(), Collections.emptyList(), 0, "0", config.toggle_off, "N/A");
            return;
        }

        BossBar bossBar = Bukkit.createBossBar(
                format(config.bossbar_title),
                getBarColor(config.bossbar_color),
                getBarStyle(config.bossbar_style)
        );

        bossBar.addPlayer(player);
        bossBar.setVisible(true);
        bossBars.put(uuid, bossBar);

        BukkitTask task = new BukkitRunnable() {
            private int ticks = 0;
            private int secondsPassed = 0;

            @Override
            public void run() {
                float progress = (float) (Math.sin(ticks * 0.1) + 1) / 2;
                bossBar.setProgress(progress);
                ticks++;

                executeAutoNear(player);

                secondsPassed += config.bossbar_refresh;
                if (secondsPassed >= config.bossbar_time) {
                    cancelAutoTask(player);
                    actionExecutor.execute(player, config.autotoggle_actions, player.getLocation(), Collections.emptyList(), 0, "0", config.toggle_off, "N/A");
                }
            }
        }.runTaskTimer(plugin, 0, config.bossbar_refresh * 20L);

        autoTasks.put(uuid, task);
        actionExecutor.execute(player, config.autotoggle_actions, player.getLocation(), Collections.emptyList(), 0, "0", config.toggle_on, "N/A");
    }

    private void executeAutoNear(Player player) {
        int maxRadius = permissionService.getMaxRadius(player.getUniqueId());

        List<Player> nearby = nearbyService.findPlayers(player, maxRadius);
        List<String> actions = nearby.isEmpty() ? config.autoempty_actions : config.autonear_actions;
        actionExecutor.execute(player, actions, player.getLocation(), nearby, maxRadius, "0", "N/A", "N/A");
    }

    private void cancelAutoTask(Player player) {
        UUID uuid = player.getUniqueId();

        if (autoTasks.containsKey(uuid)) {
            autoTasks.get(uuid).cancel();
            autoTasks.remove(uuid);
        }

        if (bossBars.containsKey(uuid)) {
            bossBars.get(uuid).removeAll();
            bossBars.remove(uuid);
        }
    }

    private BarColor getBarColor(String colorName) {
        if (colorName == null) return BarColor.RED;

        try {
            return BarColor.valueOf(colorName.toUpperCase());
        } catch (IllegalArgumentException e) {
            return BarColor.RED;
        }
    }

    private BarStyle getBarStyle(String styleName) {
        if (styleName == null) return BarStyle.SEGMENTED_12;

        try {
            return BarStyle.valueOf(styleName.toUpperCase());
        } catch (IllegalArgumentException e) {
            return BarStyle.SEGMENTED_12;
        }
    }
}