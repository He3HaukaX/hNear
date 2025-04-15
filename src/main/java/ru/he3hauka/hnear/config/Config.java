package ru.he3hauka.hnear.config;

import lombok.Getter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class Config {

    public Map<String, String> arrows;
    public Map<String, String> directions;
    public List<String> near_actions;
    public List<String> empty_actions;
    public List<String> cooldown_actions;
    public List<String> integer_actions;
    public List<String> radius_actions;
    public int cooldown_time;
    public Boolean hide_invisible;
    public Map<String, Integer> radius;
    public String up;
    public String down;
    public String same;
    public Boolean hide_ops;
    private final JavaPlugin plugin;
    public Config(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void init() {
        File configFile = new File(plugin.getDataFolder(), "config.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);

        this.radius = new HashMap<>();
        ConfigurationSection radiusSection = config.getConfigurationSection("settings.radius");
        if (radiusSection != null) {
            for (String key : radiusSection.getKeys(false)) {
                radius.put(key, radiusSection.getInt(key));
            }
        }

        this.arrows = new HashMap<>();
        ConfigurationSection arrowSection = config.getConfigurationSection("settings.arrows");
        if (arrowSection != null) {
            for (String key : arrowSection.getKeys(false)) {
                arrows.put(key, arrowSection.getString(key));
            }
        }

        this.directions = new HashMap<>();
        ConfigurationSection directionSection = config.getConfigurationSection("settings.directions");
        if (directionSection != null) {
            for (String key : directionSection.getKeys(false)) {
                directions.put(key, directionSection.getString(key));
            }
        }

        this.near_actions = config.getStringList("near.actions");
        this.empty_actions = config.getStringList("empty.actions");
        this.cooldown_actions = config.getStringList("cooldown.actions");
        this.radius_actions = config.getStringList("radius.actions");
        this.integer_actions = config.getStringList("integer.actions");

        this.cooldown_time = config.getInt("settings.cooldown", 3000);

        this.hide_invisible = config.getBoolean("settings.hide-invisible", true);
        this.hide_ops = config.getBoolean("settings.hide-ops", true);

        this.up = config.getString("settings.up", "ниже вас");
        this.down = config.getString("settings.down", "выше вас");
        this.same = config.getString("settings.same", "одна высота");
    }
}

