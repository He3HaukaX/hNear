package ru.he3hauka.hnear;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.plugin.java.JavaPlugin;
import ru.he3hauka.hnear.command.NearHandler;
import ru.he3hauka.hnear.config.Config;
import ru.he3hauka.hnear.utils.Metrics;

import java.io.File;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        createFiles("config.yml");
        Config config = new Config(this);
        config.init();

        LuckPerms luckPerms = LuckPermsProvider.get();

        getCommand("near").setExecutor(new NearHandler(config, luckPerms));

        new Metrics(this, 25436);
    }

    private void createFiles(String fileName) {
        File file = new File(getDataFolder(), fileName);
        if (!file.exists()) {
            saveResource(fileName, false);
        }
    }
}
