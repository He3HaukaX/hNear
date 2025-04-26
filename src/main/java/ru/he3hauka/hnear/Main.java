package ru.he3hauka.hnear;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.plugin.java.JavaPlugin;
import ru.he3hauka.hnear.command.CommandHandler;
import ru.he3hauka.hnear.command.NearHandler;
import ru.he3hauka.hnear.config.Config;
import ru.he3hauka.hnear.update.UpdateChecker;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        authorInfo();

        saveDefaultConfig();
        Config config = new Config(this);
        config.init();

        LuckPerms luckPerms = LuckPermsProvider.get();

        getCommand("near").setExecutor(new NearHandler(this, config, luckPerms));

        getCommand("hnear").setExecutor(new CommandHandler(config));
        getCommand("hnear").setTabCompleter(new CommandHandler(config));

        if (getConfig().getBoolean("settings.update", true)) {
            new UpdateChecker(this).checkForUpdates();
        }

    }

    public void authorInfo(){
        File file = new File(getDataFolder(), "info.txt");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            Files.copy(getResource("info.txt"), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
