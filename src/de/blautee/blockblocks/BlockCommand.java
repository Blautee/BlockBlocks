package de.blautee.blockblocks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

public class BlockCommand implements CommandExecutor, TabCompleter {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (p.hasPermission(Main.getPlugin().getConfig().getString("config.permission.admin"))) {
				if (args.length == 1) {
					if (args[0].equalsIgnoreCase("toggle")) {
						
						List<String> worldList = Settings.blacklist_worlds;
						String worldName = p.getWorld().getName();

						if (!worldList.contains(worldName)) {
							worldList.add(worldName);
							p.sendMessage(Settings.prefix + Settings.world_added);
						} else {
							worldList.remove(worldName);
							p.sendMessage(Settings.prefix + Settings.world_removed);
						}
						
						Main.getPlugin().getConfig().set("config.blacklist_worlds", worldList);
						Main.getPlugin().saveConfig();
						Settings.reloadWorldsList();
						return true;
					} else if (args[0].equalsIgnoreCase("reload")) {
						Main.getPlugin().reloadConfig();
						Settings.reloadConfig();
						Settings.reloadLang();
						Settings.reloadWorldsList();
						Settings.reloadMaterialLists();
						p.sendMessage(Settings.prefix + Settings.reload_done);
						return true;
					} else {
						p.sendMessage(Settings.prefix + Settings.wrong_use);
						return false;
					}
				} else {
					p.sendMessage(Settings.prefix + Settings.wrong_use);
					return false;
				}
			} else {
				p.sendMessage(Settings.prefix + Settings.no_perms);
				return false;
			}
		} else {
			sender.sendMessage(Settings.prefix + "This only works as a player");
			return false;
		}
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		if (sender.hasPermission(Settings.perm_admin)) {
			List<String> completions = new ArrayList<String>();
			List<String> commands = new ArrayList<String>();
			
			if (args.length == 1) {
				commands.add("toggle");
				commands.add("reload");
			}
			
			StringUtil.copyPartialMatches(args[0], commands, completions);
			Collections.sort(completions);
			return completions;
		} else {
			return null;
		}
	}
}
