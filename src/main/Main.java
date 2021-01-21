package main;

import java.security.PublicKey;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{

	private String fishOneName = null;
	private float fishOneSize = -1f;
	
	private float sizeMin = 0.1f;
	private float sizeMax = 180f;
	
	private String[] fishTypes1 = {"Haddock", "Bluefish", "Northern Pike", "Rainbow Trout", "Europian Bass", "Largemouth Bass", "Northern Red Snapper", "Atlantic Mackerel", "Common Carp", "Chum Salmon"};
	private String[] fishTypes2 = {"Stripped Bass", "Angler", "Atlantic Cod", "Europian Perch"};
	private String[] fishTypes3 = {"Ocean Sunfish", "Atlantic Bluefin Tuna", "Great White Shark", "Blue Shark", "Salmon Shark", "Giant Oceanic Manta Ray", "Bull Shark"};
	private String[] fishTypes4 = {"White Shark", "Red Shark", "Blue Shark", "Green Shark", "A Legendary Pufferfish"};
	
	private int[] fishPercentage = {55, 80, 98, 2};
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
			ev.getPlayer().getInventory().addItem(new ItemStack(Material.FISHING_ROD, 1));
		}
		//Bukkit.createBossBar("Fishing Competition", BarColor.GREEN, BarStyle.SEGMENTED_10);
	}
	
	@EventHandler
	public void PlayerFishing(PlayerFishEvent ev) {
		
		Player _player = ev.getPlayer();
		
		if(ev.getState().equals(PlayerFishEvent.State.CAUGHT_FISH)) {
			
			String[] _fishTypes;
			
			String _fishName = null;
			int Num = 0;
            float _size = 0f;
            String _sizeRounded = null;
            /* 물고기의 사이즈를 랜덤으로 정해줌 */
            ev.getCaught().remove();
            
            Random r = new Random();
            Material _fishMaterial = null;
            
            int _numLimit = 100;
            
            Num = r.nextInt(_numLimit);
            if(Num<fishPercentage[1]){
            	sizeMax = 2f;
            	sizeMin = 40f;
            	_fishTypes = new String[fishTypes1.length];
            	for(int i=0;i<fishTypes1.length;i++) {
            		_fishTypes[i] = fishTypes1[i];
            	}
            	_fishMaterial = Material.SALMON;
            }
            else if(Num<fishPercentage[2]){
            	sizeMax = 20f;
            	sizeMin = 140f;
            	_fishTypes = new String[fishTypes2.length];
            	for(int i=0;i<fishTypes2.length;i++) {
            		_fishTypes[i] = fishTypes2[i];
            	}
            	_fishMaterial = Material.COD;
            }
            else if(Num<fishPercentage[3]){
            	sizeMax = 90f;
            	sizeMin = 200f;
            	_fishTypes = new String[fishTypes3.length];
            	for(int i=0;i<fishTypes3.length;i++) {
            		_fishTypes[i] = fishTypes3[i];
            	}
            	_fishMaterial = Material.TROPICAL_FISH;
            }
            else{
            	sizeMax = 160f;
            	sizeMin = 300f;
            	_fishTypes = new String[fishTypes4.length];
            	for(int i=0;i<fishTypes4.length;i++) {
            		_fishTypes[i] = fishTypes4[i];
            	}
            	_fishMaterial = Material.PUFFERFISH;
            }
            
            _fishName = _fishTypes[r.nextInt(_fishTypes.length)];
            _size = sizeMin + r.nextFloat() * (sizeMax - sizeMin);
            _size = (float)Math.round(_size*100f)/100f;            
            
            Bukkit.broadcastMessage("You Caught " + _fishName + "!");
            
            ItemStack _fish = new ItemStack(_fishMaterial, 1);
            ItemMeta _fishMeta = _fish.getItemMeta();
            _fishMeta.setDisplayName(ChatColor.BLUE + _fishName + " (" + _size + "kg)");
            _fishMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, isEnabled());
            _fish.setItemMeta(_fishMeta);
            _player.getInventory().addItem(_fish);

            if (_size > fishOneSize) {
            	
                Bukkit.broadcastMessage(ChatColor.GREEN + _player.getName() + ChatColor.AQUA + " caught a fish weighting " + Float.toString(_size) + "kg and became the first!");
                
                /* 1등의 플레이어 이름과 1등 물고기 사이즈를 바꾸기 */
                fishOneSize = _size;
                fishOneName = _player.getName();
            }
            else {
            	Bukkit.broadcastMessage(ChatColor.GRAY + _player.getName() + "caught a " + _fishName + " (" + Float.toString(_size) + "kg)");
            }
        }
			
	}
	
	
}
