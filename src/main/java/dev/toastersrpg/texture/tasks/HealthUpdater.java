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
        Component neg3 = Component.text(Textures.Codes.NEG_3.getCode()).font(Textures.spacesKey);
        Component neg20 = Component.text(Textures.Codes.NEG_20.getCode()).font(Textures.spacesKey);

        Component healthHud = Component.text(Textures.Codes.HEALTH_BAR_PROGRESS.getCode()).font(Textures.hudKey); // not positioned
        Component backgroundHud = Component.text(Textures.Codes.HEALTH_BAR_BACKGROUND.getCode()).font(Textures.hudKey); // not positioned

        Component background = Component.text(""); // positioned
        for (int i = 0; i < 19; i++) background = background.append(neg20);
        background = background.append(backgroundHud);

        Component healthCells = Component.text("");
        for(int i = 0; i < player.getHealth() / 2; i ++) healthCells = healthCells.append(neg3).append(healthHud);

        player.sendActionBar(background.append(healthCells));
    }

    public static HealthUpdater getInstance() {
        return instance == null ? instance = new HealthUpdater() : instance;
    }
}
