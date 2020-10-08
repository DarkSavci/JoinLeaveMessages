package me.darksavci.joinleavemessages;

import net.md_5.bungee.api.ChatColor;

public class utils {
// makes utils.chat() into ChatColor.translateAlternateColorCodes()
	public static String Chat (String s) {
	
		return ChatColor.translateAlternateColorCodes('&', s);
	}
}