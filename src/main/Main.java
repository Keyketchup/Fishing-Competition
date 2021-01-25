package main;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
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

	private float fishOneSize = -1f;
	private Constants ConstData = new Constants();
	
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "The Fishing Competition Plugin Started");
	}
	
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "The Fishing Competition Plugin Ended");
	}
	
	@EventHandler
	public void PlayerJoin(PlayerJoinEvent ev) {
		ev.getPlayer().sendMessage("Welcome to the Fishing Competition, a place to enjoy and relax also with competing with the others\n(Rejoin the game if you lost your fishing rod)");
		Bukkit.broadcastMessage(ChatColor.AQUA + ev.getPlayer().getName() + " joined the Fishing Competition!");
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
	public void PlayerFishing(PlayerFishEvent ev) {
		
		Player _player = ev.getPlayer();
		
		if(ev.getState().equals(PlayerFishEvent.State.CAUGHT_FISH)) {
			
			ev.getCaught().remove();
			
			int Num = 0;
            float _size = 0f;
            Random r = new Random();
            
            int _numLimit = ConstData.fishPercentage[ConstData.fishPercentage.length-1];
            
            Num = r.nextInt(_numLimit);
            FishData fishData = new FishData(null, 0, 0, Material.AIR, ChatColor.WHITE);
            
            for(int i=0; i<ConstData.fishPercentage.length; i++) {
	            if(Num<ConstData.fishPercentage[i]){
	            	fishData = new FishData((ConstData.fishTypes[i][r.nextInt(ConstData.fishTypes[i].length)]), ConstData.minSizeArr[i], ConstData.maxSizeArr[i], ConstData.fishMaterialArr[i], ConstData.fishColorArr[i]);
	            	break;
	            }
            }
            
            _size = fishData.minSize + r.nextFloat() * (fishData.maxSize - fishData.minSize);
            _size = (float)Math.round(_size*100f)/100f;            
            
            Bukkit.broadcastMessage("You Caught " + fishData.name + "!");
            
            ItemStack _fish = new ItemStack(fishData.fishMaterial, 1);
            ItemMeta _fishMeta = _fish.getItemMeta();
            _fishMeta.setDisplayName(fishData.chatColor + fishData.name + " (" + _size + "kg)");
            _fishMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, isEnabled());
            _fish.setItemMeta(_fishMeta);
            _player.getInventory().addItem(_fish);

            if (_size > fishOneSize) {
            	Change1stPlayer(_size, _player, fishData.name);
            }
            else {
            	Bukkit.broadcastMessage(ChatColor.GRAY + _player.getName() + "caught a " + fishData.name + " (" + Float.toString(_size) + "kg)");
            }
        }
		
	}
	
	private void Change1stPlayer(float _size, Player _player, String _fishName) {
		Bukkit.broadcastMessage(ChatColor.GREEN + _player.getName() + ChatColor.AQUA + " caught a fish weighting " + Float.toString(_size) + "kg and became the first!");
		/* 1등의 플레이어 이름과 1등 물고기 사이즈를 바꾸기 */
		
		ItemStack _item = new ItemStack(Material.BOOK);
		ItemMeta _itemMeta = _item.getItemMeta();
		_itemMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Catched " + _fishName + " (" + _size + ") and broke the record");
		_itemMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
		_item.setItemMeta(_itemMeta);
		_player.getInventory().addItem(_item);
		
		for(Player _p : getServer().getOnlinePlayers()) {
			_p.playSound(_p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST, 1, 1);
		}
		
        fishOneSize = _size;
	}
	
}
