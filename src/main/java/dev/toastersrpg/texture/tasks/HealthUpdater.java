package dev.toastersrpg.texture.tasks;

import dev.toastersrpg.texture.Textures;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class HealthUpdater extends BukkitRunnable {
    private static HealthUpdater instance;

    @Override
    public void run() {
        Bukkit.getOnlinePlayers().forEach(HealthUpdater::sendHealthUpdate);
    }

    private static void sendHealthUpdate(Player player) {
        StringBuilder cellBuilder = new StringBuilder();

        for(int i = 0; i < player.getHealth() / 2; i++) {
            cellBuilder.append(Textures.HEALTH_BAR_PROGRESS.code);
            if(i < player.getHealth() / 2) cellBuilder.append(Textures.NEG_4.code);
        }

        Component healthBar = Component
                .text(String.valueOf(Textures.NEG_20.code).repeat(20))
                .append(Component.text(Textures.HEALTH_BAR_BACKGROUND.code))
                .append(Component.text(Textures.NEG_20.code.repeat(5)))
                .append(Component.text(Textures.NEG_7.code))
                .append(Component.text(cellBuilder.toString()))
                .font(Textures.generalKey);

        player.sendActionBar(healthBar);
    }

    public static HealthUpdater getInstance() {
        return instance == null ? instance = new HealthUpdater() : instance;
    }
}
