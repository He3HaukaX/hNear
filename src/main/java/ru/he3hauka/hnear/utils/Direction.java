package ru.he3hauka.hnear.utils;

import org.bukkit.Location;

public class Direction {
    private static final String[] arrows = {"↑", "↗", "→", "↘", "↓", "↙", "←", "↖"};

    public static String getRelativeDirection(Location viewer, Location target) {
        double dx = target.getX() - viewer.getX();
        double dz = target.getZ() - viewer.getZ();

        double angleToTarget = Math.toDegrees(Math.atan2(dz, dx));

        double viewerYaw = normalizeYaw(viewer.getYaw());

        double relativeAngle = angleToTarget - viewerYaw;
        if (relativeAngle < 0) relativeAngle += 360;
        if (relativeAngle >= 360) relativeAngle -= 360;

        int index = ((int)Math.round(relativeAngle / 45.0)) % 8;
        if (index < 0) index += 8;

        return arrows[index];
    }

    private static double normalizeYaw(float yaw) {
        return (yaw + 360 + 90) % 360;
    }
}
