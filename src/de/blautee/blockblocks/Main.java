package de.blautee.blockblocks;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	public static Main plugin;
	
	public void onEnable() {
		
		plugin = this;
		
		saveDefaultConfig();
		
		Settings.reloadConfig();
		Settings.reloadLang();
		Settings.reloadMaterialLists();
		Settings.reloadWorldsList();
		
		
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new PlaceListener(), plugin);
		
		getCommand("blockblocks").setExecutor(new BlockCommand());
	}
	
	public static Main getPlugin() {
		return plugin;
	}

}
