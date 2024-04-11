package dev.toastersrpg.command.core;

import org.bukkit.command.CommandSender;

public record CommandContext(CommandSender sender, Command command, String[] args) {}
