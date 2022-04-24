package me.tWizT3d_dreamr.SQL;


import java.sql.SQLException;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;



public class main extends JavaPlugin {
	public static SQLExe sql;
public void onEnable()  {


    	getConfig().addDefault("Logging.PageSize", 5);
        getConfig().addDefault("Logging.SQL.host", "localhost");
        getConfig().addDefault("Logging.SQL.port", 3306);
        getConfig().addDefault("Logging.SQL.database", "database");
        getConfig().addDefault("Logging.SQL.username", "username");
        getConfig().addDefault("Logging.SQL.password", "password");
    	getConfig().options().copyDefaults(true);
    	saveConfig();
    	try {
			{	
				ConfigurationSection s=getConfig().getConfigurationSection("Logging.SQL");
				sql =new SQLExe(s.getString("host"),s.getInt("port"),s.getString("database"),s.getString("username"),s.getString("password"));
				
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
}

public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
	if(command.getName().equalsIgnoreCase("sql")) {

		  if(sender instanceof Player) {
			  sender.sendMessage(ChatColor.RED+"Send through console");
			return true;  
		  }
		  else {
			  sender.sendMessage("Attemting to run command");
			  String comm="";
			  for(String s:args)
				  comm=comm+" "+ s;
			  try {
				sql.run(comm);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
		  }
	return false;
	}
}