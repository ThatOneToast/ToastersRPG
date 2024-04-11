package dev.toastersrpg.command;

import dev.toastersrpg.ToastRpg;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public abstract class Command extends org.bukkit.command.Command {

    private static final HashSet<Command> commands = new HashSet<>();
    private final HashMap<String, Method> cachedCommandMethods;
    private final HashMap<String, Method> cachedTabMethods;

    protected Command(@NotNull String name, @NotNull String description, @NotNull String usage, @NotNull List<String> aliases) {
        super(name, description, usage, aliases);
        cachedCommandMethods = new HashMap<>();
        cachedTabMethods = new HashMap<>();
    }

    protected Command(String name) {
        this(name, "", "", List.of());
    }

    static
    {
        Bukkit.getScheduler().runTaskLater(ToastRpg.getPlugin(), () -> commands.forEach(Command::cacheMethods), 1);
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        CommandContext context = new CommandContext(sender, this, args);
        if(args.length == 0) {
            Method method = cachedCommandMethods.get("noArgs");
            if(method == null) return true;
            try {
                method.invoke(this, context);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            return true;
        }
        String name = args[0].toLowerCase();
        Method method = cachedCommandMethods.get(name);
        if(method == null) return true;

        if(method.getParameterCount() > 1) return true;

        // TODO: HANDLE PERMISSION AND COOLDOWN
        //
        //
        // TODO: END

        try {
            method.invoke(this, context);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
        CommandContext context = new CommandContext(sender, this, args);
        if(args.length == 1) return cachedCommandMethods.keySet()
                .stream()
                .filter(name -> !name.equals("noArgs"))
                .filter(s -> s.toLowerCase().startsWith(args[0].toLowerCase()))
                .toList();

        String name = args[0].toLowerCase();
        Method tabMethod = cachedTabMethods.get(name);
        if (tabMethod == null) return List.of();
        if(cachedCommandMethods.get(name) == null) {
            Bukkit.getLogger().warning("Tab completion found for non existing command");
            return List.of();
        }
        if (!tabMethod.getReturnType().equals(List.class) || !tabMethod.getGenericReturnType().getTypeName().equals("java.util.List<java.lang.String>")) return List.of();
        try {
            @SuppressWarnings("unchecked")
            List<String> completions = (List<String>) tabMethod.invoke(this, context);
            return completions;
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private void cacheMethods() {
        for(Command command : commands) {
            Method[] methods = command.getClass().getDeclaredMethods();
            for(Method method : methods) {
                if(method.isAnnotationPresent(ICommand.class)) {
                    method.setAccessible(true);
                    ICommand annotation = method.getAnnotation(ICommand.class);

                    if (cachedCommandMethods.containsKey(annotation.name())) {
                        Bukkit.getLogger().warning("Duplicate command name");
                        continue;
                    }
                    if (annotation.noArgs()) {
                        cachedCommandMethods.put("noArgs", method);
                        continue;
                    }
                    if(annotation.name() == null || annotation.name().isEmpty() || annotation.name().isBlank()) {
                        if(cachedCommandMethods.containsKey(method.getName())) {
                            Bukkit.getLogger().warning("Duplicate command name");
                            continue;
                        }
                        cachedCommandMethods.put(method.getName().toLowerCase(), method);
                        continue;
                    }
                    cachedCommandMethods.put(annotation.name().toLowerCase(), method);
                }
                if(method.isAnnotationPresent(ITabComplete.class)) {
                    method.setAccessible(true);
                    ITabComplete annotation = method.getAnnotation(ITabComplete.class);

                    if(cachedTabMethods.containsKey(annotation.name())) {
                        Bukkit.getLogger().warning("Duplicate tab name");
                        continue;
                    }
                    if(annotation.name() == null || annotation.name().isEmpty() || annotation.name().isBlank()) {
                        if(cachedTabMethods.containsKey(method.getName())) {
                            Bukkit.getLogger().warning("Duplicate tab name");
                            continue;
                        }
                        cachedTabMethods.put(method.getName().toLowerCase(), method);
                        continue;
                    }
                    cachedTabMethods.put(annotation.name().toLowerCase(), method);
                }
            }
        }
    }

    public static void register(JavaPlugin plugin, Command... cmds) {
        for (Command cmd : cmds) {
            plugin.getServer().getCommandMap().register(cmd.getName(), cmd);
            commands.add(cmd);
        }
    }
}
