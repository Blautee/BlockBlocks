package de.blautee.blockblocks;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class PlaceListener implements Listener {

	@EventHandler
	public void blockBlockPlacement(BlockPlaceEvent e) {
		
		if (!e.getPlayer().hasPermission(Settings.perm_bypass_blacklist)) {
			if (Settings.blacklist_worlds.contains(e.getBlock().getWorld().getName())) {
				if (Settings.blacklist_materials.contains(e.getBlock().getType())) {
					e.setCancelled(true);
					e.getPlayer().sendMessage(Settings.prefix + Settings.unable_to_place);
				}
			}
		}
		
		if (!e.getPlayer().hasPermission(Settings.perm_bypass_global)) {
			if (Settings.global_material_blacklist.contains(e.getBlock().getType())) {
				e.setCancelled(true);
				e.getPlayer().sendMessage(Settings.prefix + Settings.unable_to_place);
			}
		}
	}
}
