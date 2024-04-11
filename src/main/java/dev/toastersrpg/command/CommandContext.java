package dev.toastersrpg.command;

import org.bukkit.command.CommandSender;

public record CommandContext(CommandSender sender, Command command, String[] args) {}
