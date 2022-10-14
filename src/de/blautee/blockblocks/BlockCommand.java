package de.blautee.blockblocks;

import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BlockCommand implements CommandExecutor {

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
					}
					
					if (args[0].equalsIgnoreCase("reload")) {
						Main.getPlugin().reloadConfig();
						Settings.reloadConfig();
						Settings.reloadLang();
						Settings.reloadWorldsList();
						Settings.reloadMaterialLists();
						p.sendMessage(Settings.prefix + Settings.reload_done);;
					}
				} else {
					// TODO Out "help" or "wrong usage"
				}

			} else {
				p.sendMessage(Settings.prefix + Settings.no_perms);
			}

		} else {
			sender.sendMessage(Settings.prefix + "This only works as a player");
			return false;
		}
		return false;
	}

}
