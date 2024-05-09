package dev.toastersrpg.command.faceless;

import dev.toastersrpg.lib.command.Command;
import dev.toastersrpg.lib.command.CommandContext;
import dev.toastersrpg.lib.command.ICommand;
import dev.toastersrpg.lib.command.ITabComplete;
import dev.toastersrpg.texture.Textures;
import net.kyori.adventure.text.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class HudCommand extends Command {

    public HudCommand() {
        super("hud");
    }

    public void noArgs(CommandContext context) {
        context.sender().sendMessage("invalid args");
    }

    @ICommand
    public void sendActionBar(CommandContext context) {
        List<Component> components = new ArrayList<>();

        for (String arg : context.args()) {
            try {
                Textures code = Textures.valueOf(arg);
                Component component = Component.text(code.code).font(code.key);
                components.add(component);
            } catch (IllegalArgumentException ignored) {}
        }

        if (!components.isEmpty()) {
            Component actionBar = Component.empty();
            for (Component component : components) {
                actionBar = actionBar.append(component);
            }
            context.sender().sendActionBar(actionBar);
        } else {
            context.sender().sendMessage(Component.text("No valid codes provided"));
        }
    }

    @ICommand
    public void sendChat(CommandContext context) {
        List<Component> components = new ArrayList<>();

        for (String arg : context.args()) {
            try {
                Textures code = Textures.valueOf(arg);
                Component component = Component.text(code.code).font(code.key);
                components.add(component);
            } catch (IllegalArgumentException ignored) {}
        }

        if (!components.isEmpty()) {
            Component actionBar = Component.empty();
            for (Component component : components) {
                actionBar = actionBar.append(component);
            }
            context.sender().sendMessage(actionBar);
        } else {
            context.sender().sendMessage(Component.text("No valid codes provided"));
        }
    }

    @ITabComplete(name = "sendChat")
    public List<String> sendChatTab(CommandContext context) {
        String[] args = context.args();

        return Arrays.stream(Textures.values())
                .map(Textures::toString)
                .filter(name -> {
                    for (String arg : args) {
                        if (name.toLowerCase().startsWith(arg.toLowerCase())) return true;
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }

    @ITabComplete(name = "sendActionBar")
    public List<String> sendActionBarTab(CommandContext context) {
        String[] args = context.args();

        return Arrays.stream(Textures.values())
                .map(Textures::toString)
                .filter(name -> {
                    for (String arg : args) {
                        if (name.toLowerCase().startsWith(arg.toLowerCase())) return true;
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }
}
