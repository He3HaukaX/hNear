package ru.he3hauka.hnear;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.plugin.java.JavaPlugin;
import ru.he3hauka.hnear.command.NearHandler;
import ru.he3hauka.hnear.config.Config;
import ru.he3hauka.hnear.update.UpdateChecker;
import ru.he3hauka.hnear.utils.Metrics;

import java.io.File;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        createFiles();
        Config config = new Config(this);
        config.init();

        LuckPerms luckPerms = LuckPermsProvider.get();

        getCommand("near").setExecutor(new NearHandler(this, config, luckPerms));

        if (getConfig().getBoolean("settings.update")) {
            new UpdateChecker(this).checkForUpdates();
        }

        new Metrics(this, 25436);
    }

    private void createFiles() {
        File file = new File(getDataFolder(), "config.yml");
        if (!file.exists()) {
            saveResource("config.yml", false);
        }
    }
}
