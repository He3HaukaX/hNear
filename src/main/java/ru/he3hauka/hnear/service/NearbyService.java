package ru.he3hauka.hnear.service;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class NearbyService {

    private final boolean hideInvisible;
    private final boolean hideOps;

    public NearbyService(boolean hideInvisible, boolean hideOps) {
        this.hideInvisible = hideInvisible;
        this.hideOps = hideOps;
    }

    public List<Player> findPlayers(Player source, int radius) {
        Location origin = source.getLocation();

        return Bukkit.getOnlinePlayers().stream()
                .filter(p -> !p.equals(source))
                .filter(p -> p.getWorld().equals(origin.getWorld()))
                .filter(p -> p.getLocation().distance(origin) <= radius)
                .filter(p -> {
                    if (hideOps && p.isOp()) return false;

                    if (!hideInvisible) return true;

                    boolean hasInvisibility = p.hasPotionEffect(PotionEffectType.INVISIBILITY);
                    boolean isHiddenByServer = !source.canSee(p);

                    return !hasInvisibility && !isHiddenByServer;
                })
                .sorted(Comparator.comparingDouble(p -> p.getLocation().distance(origin)))
                .collect(Collectors.toList());
    }
}

