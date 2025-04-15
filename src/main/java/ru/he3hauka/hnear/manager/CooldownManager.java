package ru.he3hauka.hnear.manager;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownManager {

    private final Map<UUID, Long> cooldowns = new HashMap<>();
    private final long cooldownTimeMillis;

    public CooldownManager(long cooldownTimeMillis) {
        this.cooldownTimeMillis = cooldownTimeMillis;
    }

    public boolean isCooldown(UUID playerId) {
        return getRemainingTime(playerId) > 0;
    }

    public long getRemainingTime(UUID playerId) {
        long now = System.currentTimeMillis();
        return Math.max(0, cooldownTimeMillis - (now - cooldowns.getOrDefault(playerId, 0L)));
    }

    public void setCooldown(UUID playerId) {
        cooldowns.put(playerId, System.currentTimeMillis());
    }
}
