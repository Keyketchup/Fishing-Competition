package main;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

class FishData {
	public String name;
	public float minSize;
	public float maxSize;
	public Material fishMaterial;
	public ChatColor chatColor;
	
	public FishData(String _name, float _minSize, float _maxSize, Material _fishMaterial, ChatColor _chatColor) {
		name = _name;
		minSize = _minSize;
		maxSize = _maxSize;
		fishMaterial = _fishMaterial;
		chatColor = _chatColor;
	}
}

public class Main extends JavaPlugin implements Listener{
	
	Constants ConstData = new Constants();
	Fishing fishing = new Fishing();
	
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "The Fishing Competition Plugin Started");
		
		FileController.CreateDefaultData();
	}
	
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "The Fishing Competition Plugin Ended");
	}
	
	@EventHandler
	public void PlayerJoin(PlayerJoinEvent ev) {
		ev.getPlayer().sendMessage("Welcome to the Fishing Competition, a place to enjoy and relax also with competing with the others\n(Rejoin the game if you lost your fishing rod)");
		Bukkit.broadcastMessage(ChatColor.AQUA + ev.getPlayer().getName() + " joined the Fishing Competition!");
		
		FileController.CreateUserData(ev.getPlayer());
		
		if (!ev.getPlayer().getInventory().contains(Material.FISHING_ROD)) {
			ItemStack Rod = new ItemStack(Material.FISHING_ROD, 1);
			ItemMeta RodMeta = Rod.getItemMeta();
			RodMeta.setUnbreakable(true);
			RodMeta.setDisplayName(ChatColor.WHITE + ev.getPlayer().getName() + "'s Fishing Rod");
			Rod.setItemMeta(RodMeta);
			ev.getPlayer().getInventory().addItem(Rod);
		}
		//Bukkit.createBossBar("Fishing Competition", BarColor.GREEN, BarStyle.SEGMENTED_10);
	}
	
	@EventHandler
	private void DropItem(PlayerDropItemEvent ev) {
		ev.setCancelled(true);
	}
	
	@EventHandler
	public void PlayerFishing(PlayerFishEvent ev) {
		fishing.PlayerFishing(ev);
	}
	
}
