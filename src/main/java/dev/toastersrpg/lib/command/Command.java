package dev.toastersrpg.lib.command;

import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

public class Command extends org.bukkit.command.Command {

    private final HashMap<String, Method> cachedCommandMethods;
    private final HashMap<String, Method> cachedTabMethods;

    public Command(@NotNull String name) {
        this(name,"_","_", List.of());

    }
    public Command(@NotNull String name, @NotNull String description, @NotNull String usageMessage, @NotNull List<String> aliases) {
        super(name, description, usageMessage, aliases);
        cachedCommandMethods = new HashMap<>();
        cachedTabMethods = new HashMap<>();
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        CommandContext context = new CommandContext(sender, this, args);
        if(args.length == 0) {
            Method method = this.cachedCommandMethods.get("noArgs");
            if(method == null) return true;
            try {
                method.invoke(this, context);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            return true;
        }
        String name = args[0].toLowerCase();
        Method method = this.cachedCommandMethods.get(name);
        if(method == null) return true;

        if(method.getParameterCount() > 1) return true;
        ICommand annotation = method.getAnnotation(ICommand.class);
        String permission = null;
        if(annotation != null) {
            if(!annotation.permission().isEmpty() || !annotation.permission().isBlank()) permission = annotation.permission();
        }

        if(permission != null) {
            if(!sender.hasPermission(permission)) return true;
        }
        try {
            method.invoke(this, context);
        } catch (IllegalAccessException | InvocationTargetException |
                 CommandException | IllegalArgumentException ignored) {}
        return true;
    }

    @Override
    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
        CommandContext context = new CommandContext(sender, this, args);

        if(args.length == 1) {
            return this.cachedCommandMethods.keySet()
                    .stream()
                    .filter(name -> !name.equals("noArgs"))
                    .filter(name -> {
                        Method method = this.cachedCommandMethods.get(name);
                        ICommand annotation = method.getAnnotation(ICommand.class);
                        if (annotation == null) return false;
                        String permission = annotation.permission();
                        return permission.isEmpty() || sender.hasPermission(permission);
                    })
                    .filter(s -> s.toLowerCase().startsWith(args[0].toLowerCase()))
                    .toList();
        }

        String subcommand = args[0].toLowerCase();
        Method method;

        Method commandMethod = this.cachedCommandMethods.get(subcommand);
        if(commandMethod == null) return List.of();

        ICommand annotation = commandMethod.getAnnotation(ICommand.class);
        if (annotation == null) return List.of();

        if(!annotation.tabCompleter().isEmpty() || !annotation.tabCompleter().isBlank()) {
            String tabCompleter = annotation.tabCompleter().toLowerCase();

            Method tabMethod = this.cachedTabMethods.get(tabCompleter);
            if(tabMethod == null) return List.of();

            method = tabMethod;
        }else {
            Method tabMethod = this.cachedTabMethods.get(subcommand);
            if(tabMethod == null) return List.of();
            method = tabMethod;
        }

        if (!method.getReturnType().equals(List.class) || !method.getGenericReturnType().getTypeName().equals("java.util.List<java.lang.String>")) return List.of();
        try {
            @SuppressWarnings("unchecked")
            List<String> completions = (List<String>) method.invoke(this, context);
            return completions;
        } catch (IllegalAccessException | InvocationTargetException |
                 CommandException | IllegalArgumentException ignored) {}
        return List.of();
    }

    private void cacheMethods() {
        Method[] methods = this.getClass().getDeclaredMethods();
        for(Method method : methods) {
            if(method.isAnnotationPresent(ICommand.class)) {
                method.setAccessible(true);
                ICommand annotation = method.getAnnotation(ICommand.class);
                if(annotation == null) continue;

                if (cachedCommandMethods.containsKey(annotation.name())) {
                    // DEBUG MESSAGE HERE (Duplicate command)
                    continue;
                }
                if (annotation.noArgs()) {
                    cachedCommandMethods.put("noArgs", method);
                    continue;
                }
                if(annotation.name() == null || annotation.name().isEmpty() || annotation.name().isBlank()) {
                    if(cachedCommandMethods.containsKey(method.getName())) {
                        // DEBUG MESSAGE HERE (Duplicate command)
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
                if(annotation == null) continue;

                if(cachedTabMethods.containsKey(annotation.name())) {
                    // DEBUG MESSAGE HERE (Duplicate tab completer)
                    continue;
                }
                cachedTabMethods.put(annotation.name().toLowerCase(), method);
            }
        }
    }

    public static void register(JavaPlugin plugin, Command... cmds) {
        for (Command cmd : cmds) {
            plugin.getServer().getCommandMap().register("", cmd);
            cmd.cacheMethods();
        }
    }
}
