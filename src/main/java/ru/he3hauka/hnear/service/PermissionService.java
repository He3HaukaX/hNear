package ru.he3hauka.hnear.service;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;

import java.util.Map;
import java.util.UUID;

public class PermissionService {

    private final LuckPerms luckPerms;
    private final Map<String, Integer> radiusByGroup;

    public PermissionService(LuckPerms luckPerms, Map<String, Integer> radiusByGroup) {
        this.luckPerms = luckPerms;
        this.radiusByGroup = radiusByGroup;
    }

    public int getMaxRadius(UUID playerId) {
        User user = luckPerms.getUserManager().getUser(playerId);
        if (user == null) return radiusByGroup.getOrDefault("default", 20);
        String group = user.getPrimaryGroup();
        return radiusByGroup.getOrDefault(group, radiusByGroup.getOrDefault("default", 20));
    }
}
