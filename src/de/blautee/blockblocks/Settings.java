package de.blautee.blockblocks;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

public class Settings {

	public static String perm_admin;
	public static String perm_bypass;

	public static String prefix;
	public static String no_perms;
	public static String world_added;
	public static String world_removed;
	public static String reload_done;
	public static String unable_to_place;

	public static List<String> blacklist_blocks;
	public static List<Material> blacklist_materials;
	public static List<String> blacklist_worlds;

	public static void reloadConfig() {
		FileConfiguration cfg = Main.getPlugin().getConfig();
		perm_admin = cfg.getString("config.permission.admin");
		perm_bypass = cfg.getString("config.permission.bypass");
	}

	public static void reloadLang() {
		prefix = colorMe("config.lang.prefix");
		no_perms = colorMe("config.lang.no_perms");
		world_added = colorMe("config.lang.world_added");
		world_removed = colorMe("config.lang.world_removed");
		reload_done = colorMe("config.lang.reload_done");
		unable_to_place = colorMe("config.lang.unable_to_place");
	}
	
	public static void reloadWorldsList() {
		FileConfiguration cfg = Main.getPlugin().getConfig();
		try {
			blacklist_worlds = cfg.getStringList("config.blacklist_worlds");
		} catch (Exception ex) {
			blacklist_worlds = new ArrayList<String>();
		}
	}

	public static void reloadMaterialLists() {
		FileConfiguration cfg = Main.getPlugin().getConfig();
		try {
			blacklist_blocks = cfg.getStringList("config.blacklist_blocks");
		} catch (Exception ex) {
			blacklist_blocks = new ArrayList<String>();
		}
		
		blacklist_materials = new ArrayList<Material>();
		
		for (String s : blacklist_blocks) {
			try {
				blacklist_materials.add(Material.valueOf(s));
			} catch (Exception ex) {
				Bukkit.getLogger().log(Level.WARNING, "Material " + s + " could not be loaded:\n" + ex);
			}
		}
	}

	public static String colorMe(String s) {
		FileConfiguration cfg = Main.getPlugin().getConfig();
		String out;
		try {
			out = ChatColor.translateAlternateColorCodes('&', cfg.getString(s));
			if (out == null) {
				out = "Configuration Error!";
			}
			return out;
		} catch (Exception e) {
			out = cfg.getString(s);
			if (out == null) {
				out = "Configuration Error!";
			}
			return out;
		}
	}

}
