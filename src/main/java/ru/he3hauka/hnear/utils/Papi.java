package ru.he3hauka.hnear.utils;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Papi {

    public static String process(Player player, String text) {
        try {
            if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
                return HexSupport.format(PlaceholderAPI.setPlaceholders(player, text));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return HexSupport.format(text);
    }
}
