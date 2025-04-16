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
    public List<String> autonear_actions;
    public List<String> autoempty_actions;
    public List<String> empty_actions;
    public List<String> cooldown_actions;
    public List<String> integer_actions;
    public List<String> radius_actions;
    public List<String> autotoggle_actions;
    public List<String> noperms_actions;
    public List<String> help_actions;
    public int cooldown_time;
    public Boolean hide_invisible;
    public Map<String, Integer> radius;
    public String up;
    public String down;
    public String same;
    public String toggle_on;
    public String toggle_off;
    public Boolean hide_ops;
    public String bossbar_title;
    public String bossbar_color;
    public String bossbar_style;
    public int bossbar_time;
    public int bossbar_refresh;
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
        this.autonear_actions = config.getStringList("autonear.actions");
        this.autoempty_actions = config.getStringList("autoempty.actions");
        this.autotoggle_actions = config.getStringList("autotoggle.actions");
        this.noperms_actions = config.getStringList("noperms.actions");
        this.help_actions = config.getStringList("help.actions");

        this.cooldown_time = config.getInt("settings.cooldown", 3000);

        this.hide_invisible = config.getBoolean("settings.hide-invisible", true);
        this.hide_ops = config.getBoolean("settings.hide-ops", true);

        this.toggle_on = config.getString("settings.toggle.on", "включен");
        this.toggle_off = config.getString("settings.toggle.off", "выключен");

        this.up = config.getString("settings.up", "ниже вас");
        this.down = config.getString("settings.down", "выше вас");
        this.same = config.getString("settings.same", "одна высота");

        this.bossbar_title = config.getString("settings.bossbar.title", "&fАвтоматический §x§F§B§9§C§0§8поиск &fигроков...");
        this.bossbar_color = config.getString("settings.bossbar.color", "RED");
        this.bossbar_style = config.getString("settings.bossbar.style", "SEGMENTED_10");
        this.bossbar_time = config.getInt("settings.bossbar.time", 30);
        this.bossbar_refresh = config.getInt("settings.bossbar.refresh", 1);
    }
}

