package me.darksavci.joinleavemessages;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {  
	
	// When plugin is first enabled
    @Override
    public void onEnable() {
    	
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            /*
             * We register the EventListener here, when PlaceholderAPI is installed.
             * Since all events are in the main class (this class), we simply use "this"
             */
            Bukkit.getPluginManager().registerEvents(this, this);
            
        	saveDefaultConfig();
        	new JoinListener(this);
        	
          	 this.getCommand("jm-reload").setExecutor(new CommandReload());
        	
        	ConsoleCommandSender console = getServer().getConsoleSender();
        	console.sendMessage(utils.Chat("&8[&eJoinLeaveMessages&8] &aPlaceHolderAPI Found Plugin Starting!"));
        	console.sendMessage(utils.Chat("&8[&eJoinLeaveMessages&8] &aStarting"));
        }
        else {
            /*
             * We inform about the fact that PlaceholderAPI isn't installed and then
             * disable this plugin to prevent issues.
             */
            getLogger().warning(utils.Chat("[JoinLeaveMessages] Could not find PlaceholderAPI! This plugin is required."));
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }
    
    public class CommandReload implements CommandExecutor {
    	
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            
            reloadConfig();
            player.sendMessage(utils.Chat("&8[&eJoinLeaveMessages&8] &aPlugin Succesfully Reloaded"));
        }
        else {
        	Player player = (Player) sender;
        	player.sendMessage(utils.Chat("&8[&eJoinLeaveMessages&8 &cYou don't have permission!"));	
        }
       if (sender instanceof ConsoleCommandSender) {
        	ConsoleCommandSender console = getServer().getConsoleSender();
        	
        	reloadConfig();
            console.sendMessage(utils.Chat("&8[&eJoinLeaveMessages&8] &aPlugin Succesfully Reloaded"));
        }
        return true;
    }
   }
    
    // When plugin is disabled
    @Override
    public void onDisable() {
    	saveDefaultConfig();
    	
    	ConsoleCommandSender console = getServer().getConsoleSender();
    	console.sendMessage(utils.Chat("&8[&eJoinLeaveMessages&8] &cDisabling!"));
    }
}