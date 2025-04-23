package ru.he3hauka.hnear.update;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import ru.he3hauka.hnear.utils.Localization;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class UpdateChecker {

    private final JavaPlugin plugin;
    private final String repo = "He3HaukaX/hNear";
    private final Logger logger;
    private final Localization localization;

    public UpdateChecker(JavaPlugin plugin, String locale) {
        this.plugin = plugin;
        this.logger = plugin.getLogger();
        this.localization = new Localization(locale);
    }

    public void checkForUpdates() {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            try {
                URL url = new URL("https://api.github.com/repos/" + repo + "/releases/latest");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.addRequestProperty("User-Agent", "Mozilla/5.0");

                if (conn.getResponseCode() == 200) {
                    JSONParser parser = new JSONParser();
                    JSONObject json = (JSONObject) parser.parse(new InputStreamReader(conn.getInputStream()));

                    String latestVersion = (String) json.get("tag_name");
                    String currentVersion = plugin.getDescription().getVersion();

                    if (!currentVersion.equalsIgnoreCase(latestVersion)) {
                        logger.warning(localization.get("update_available", latestVersion, currentVersion));
                        logger.warning(localization.get("download_here", "https://github.com/" + repo + "/releases/tag/" + latestVersion));
                    } else {
                        logger.info(localization.get("up_to_date", currentVersion));
                    }
                } else {
                    logger.warning(localization.get("update_check_failed_code", conn.getResponseCode()));
                }

                conn.disconnect();
            } catch (Exception e) {
                logger.warning(localization.get("update_check_failed_error", e.getMessage()));
            }
        });
    }
}
