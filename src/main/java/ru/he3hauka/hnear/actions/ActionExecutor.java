package ru.he3hauka.hnear.actions;

import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.title.Title;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import ru.he3hauka.hnear.config.Config;
import ru.he3hauka.hnear.service.PermissionService;
import ru.he3hauka.hnear.utils.Direction;
import ru.he3hauka.hnear.utils.Papi;

import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActionExecutor {

    private final Config config;
    private final PermissionService permissionService;
    public ActionExecutor(Config config, PermissionService permissionService) {
        this.config = config;
        this.permissionService = permissionService;
    }

    public void execute(Player player,
                        List<String> actions,
                        Location origin,
                        List<? extends Player> nearbyPlayers,
                        int radius,
                        String cooldown,
                        String toggle,
                        String perms) {
        for (String action : actions) {
            String actionType = action.split(" ")[0];
            String actionContent = action.substring(actionType.length()).trim();

            actionContent = actionContent
                    .replace("%cooldown%", cooldown)
                    .replace("%radius%", String.valueOf(radius))
                    .replace("%toggle%", toggle)
                    .replace("%perms%", perms);

            switch (actionType) {
                case "[Message]" -> handleMessage(player, actionContent);
                case "[Sound]" -> handleSound(player, actionContent);
                case "[Title]" -> handleTitle(player, actionContent);
                case "[PlayerList]" -> handlePlayerList(player, actionContent, nearbyPlayers, origin);
                default -> player.sendMessage("Неизвестное действие: " + actionType);
            }
        }
    }

    private void handleMessage(Player player, String content) {
        content = Papi.process(player, content);

        int maxRadius = permissionService.getMaxRadius(player.getUniqueId());
        content = content.replace("%max-radius%", String.valueOf(maxRadius));

        Matcher matcher = Pattern.compile("\\{HoverText:cmd (.*?), text: (.*?)}").matcher(content);
        if (matcher.find()) {
            String cmd = matcher.group(1).trim();
            String hoverText = matcher.group(2).trim();
            content = content.substring(0, matcher.start()) + content.substring(matcher.end());

            TextComponent message = LegacyComponentSerializer.legacySection().deserialize(content)
                    .hoverEvent(HoverEvent.showText(LegacyComponentSerializer.legacySection().deserialize(hoverText)))
                    .clickEvent(ClickEvent.runCommand(cmd));

            player.sendMessage(message);
        } else {
            player.sendMessage(LegacyComponentSerializer.legacySection().deserialize(content));
        }
    }

    private void handleSound(Player player, String content) {
        try {
            String[] parts = content.split(":");
            Sound sound = Sound.valueOf(parts[0].trim());
            float volume = parts.length > 1 ? Float.parseFloat(parts[1].trim()) : 1.0f;
            float pitch = parts.length > 2 ? Float.parseFloat(parts[2].trim()) : 1.0f;
            player.playSound(player.getLocation(), sound, volume, pitch);
        } catch (Exception e) {
            player.sendMessage("Ошибка при воспроизведении звука");
        }
    }

    private void handleTitle(Player player, String content) {
        content = Papi.process(player, content);

        int maxRadius = permissionService.getMaxRadius(player.getUniqueId());
        content = content.replace("%max-radius%", String.valueOf(maxRadius));

        String[] parts = content.split("&&");
        String title = parts.length > 0 ? parts[0].trim() : "";
        String subtitle = parts.length > 1 ? parts[1].trim() : "";

        player.showTitle(Title.title(
                LegacyComponentSerializer.legacySection().deserialize(title),
                LegacyComponentSerializer.legacySection().deserialize(subtitle),
                Title.Times.of(Duration.ofMillis(500), Duration.ofMillis(2000), Duration.ofMillis(1000))
        ));
    }

    private void handlePlayerList(Player player, String content, List<? extends Player> targets, Location origin) {
        for (Player target : targets) {
            double distance = target.getLocation().distance(origin);
            String dirCode = Direction.getRelativeDirection(origin, target.getLocation());
            String arrow = config.arrows.getOrDefault(dirCode, dirCode);
            String direction = config.directions.getOrDefault(dirCode, dirCode);

            String heightDiff = getHeightDiff(origin.getY(), target.getLocation().getY());

            String parsed = content
                    .replace("%player_name%", target.getName())
                    .replace("%target%", target.getName())
                    .replace("%arrow%", arrow)
                    .replace("%direction%", direction)
                    .replace("%p_radius%", String.format("%.1f", distance))
                    .replace("%height_diff%", heightDiff);

            parsed = Papi.process(target, parsed);

            Matcher matcher = Pattern.compile("\\{HoverText:cmd (.*?), text: (.*?)}").matcher(parsed);
            if (matcher.find()) {
                String cmd = matcher.group(1).trim();
                String hoverText = matcher.group(2).trim();
                parsed = parsed.substring(0, matcher.start()) + parsed.substring(matcher.end());

                TextComponent message = LegacyComponentSerializer.legacySection().deserialize(parsed)
                        .hoverEvent(HoverEvent.showText(LegacyComponentSerializer.legacySection().deserialize(hoverText)))
                        .clickEvent(ClickEvent.runCommand(cmd));

                player.sendMessage(message);
            } else {
                player.sendMessage(LegacyComponentSerializer.legacySection().deserialize(parsed));
            }
        }
    }

    private String getHeightDiff(double y1, double y2) {
        double delta = y2 - y1;
        if (delta > 2) return config.up;
        if (delta < -2) return config.down;
        return config.same;
    }
}
