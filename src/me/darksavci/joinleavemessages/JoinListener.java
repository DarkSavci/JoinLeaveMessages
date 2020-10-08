package me.darksavci.joinleavemessages;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinListener implements Listener{
	
	private static Main plugin;
	
	@SuppressWarnings("static-access")
	public JoinListener(Main plugin) {
		this.plugin = plugin;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
    //This is Join and First Join Message
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		//Removes Join Message
		e.setJoinMessage(null);
		//Checks Player has played before and Vanish Permission
		if ((!p.hasPlayedBefore() && !p.hasPermission("joinmessage.vanish"))) {
			/*if player does not played before this works
			FirstJoinMessage String*/
			String FirstJoinMessage = utils.Chat(plugin.getConfig().getString("PlayerFirstJoin"));
			//First Join Message PlaceHolderAPI Hook
			FirstJoinMessage = PlaceholderAPI.setPlaceholders(e.getPlayer(), FirstJoinMessage);
			//Sends First Join Message
			Bukkit.broadcastMessage(FirstJoinMessage);
		}
		// Checks Vanish Permission
		else if (!p.hasPermission("joinmessage.vanish") && p.hasPlayedBefore()) {
			//Join Message String
			String JoinMessage = utils.Chat(plugin.getConfig().getString("PlayerJoin"));
			//Join Message PlaceHolderAPI Hook
			JoinMessage = PlaceholderAPI.setPlaceholders(e.getPlayer(), JoinMessage);
			//Sends Join Message
			Bukkit.broadcastMessage(JoinMessage);
		}
	}
		//This is Quit Message
    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
	    Player p = e.getPlayer();
	    //Removes Quit Message
		 e.setQuitMessage(null);
		 //Checks the Vanish permission
	    if (!(p.hasPermission("joinmessage.vanish"))) {	
	    	//Quit Message String
	    	String QuitMessage = utils.Chat(plugin.getConfig().getString("PlayerLeave"));
	    	//Quit Message PlaceHolderAPI Hook
	    	QuitMessage = PlaceholderAPI.setPlaceholders(e.getPlayer(), QuitMessage);
	    	//Sends Quit Message
			Bukkit.broadcastMessage(QuitMessage);
			}		

	}
}
