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
		
	private String[] fishTypes1 = {"Haddock", "John Dory", "Brill", "Lobstar", "Blue Cod", "Bluenose", "Bluefish", "Northern Pike", "Sea Perch", "Squid", "Rainbow Trout", "Bluegill", "Zander", "Europian Bass", "Largemouth Bass", "Northern Red Snapper", "Pollack", "Atlantic Mackerel", "Common Carp", "Chum Salmon", "Guppy", "Goldfish", "Blue Tang", "Swordfish", "Neon Tetra", "Creeperfish", "Common Molly", "Freshwater Angelfish"};
	private String[] fishTypes2 = {"Stripped Bass", "Freshwater Eal", "Monkfish", "Hoki", "Angler", "Atlantic Cod", "Garfish", "Europian Perch", "Zombiefish", "Wels Catfish", "Atlantic Salmon", "Starfish"};
	private String[] fishTypes3 = {"Ocean Sunfish", "Ghost Shark", "Southern Bluefin Tuna","Atlantic Bluefin Tuna", "Skeletonfish", "Great White Shark", "Blue Shark", "Salmon Shark", "Giant Oceanic Manta Ray", "Bull Shark"};
	private String[] fishTypes4 = {"Whale Shark", "White Shark", "Red Shark", "Blue Shark", "Withered Shark", "Green Shark", "A Legendary Pufferfish", "A Legendary Starfish", "A Legendary Salmon", "A Legendary Cod" ,"A Legendary Snapper"};
	private String[] treasureTypes1 = {"Toy Car", "Broken Toy Robot", "Diary of Wimpy Potato", "Terminator DVD", "Finding the Nemo DVD", "Dumbo DVD", "Bambi DVD", "Phone Case", "Toy Boat", "Mario Figure", "Computer-Written Letter from India", "Donkey Kong Figure", "Empty Treasure Chest", "Statue Of Llama", "Statue Of Rabbit", "Statue of Creeper", "Statue of Zombie", "Statue of Skeleton", "Fake Diamond"};
	private String[] treasureTypes2 = {"Viking Boat Fragment", "Egyptian Diamond", "Steve's Missing Jewlery", "Pirate's Eyepatch", "Chest full of gold", "Piece of Long-written Music", "Egyptian Clay Doll", "Limited Version of Monopoly", "Diary of a Everlasting Creeper", "Limited Toy Car", "Miniature Christmas Tree", "Statue of Spider Jokey", "Statue of Wither", "Statue of Ender Dragon", "Broken Piece of an Everlasting Mana Pool"};
	
	private int[] fishPercentage = {20, 30, 35, 40, 42, 43};
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "The Fishing Competition Plugin Started");
		Bukkit.getConsoleSender().sendMessage("The number is.. " + fishPercentage.length);
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
			
			String[] _fishTypes;
			
			String _fishName = null;
			ChatColor _fishColor = ChatColor.BLUE;
			int Num = 0;
            float _size = 0f;
            String _sizeRounded = null;
            
            //물고기를 없앰
            ev.getCaught().remove();
            
            Random r = new Random();
            Material _fishMaterial = null;
            
            int _numLimit = fishPercentage[fishPercentage.length-1];
            
            Num = r.nextInt(_numLimit);
            if(Num<fishPercentage[0]){
            	sizeMax = 2f;
            	sizeMin = 40f;
            	_fishTypes = new String[fishTypes1.length];
            	for(int i=0;i<fishTypes1.length;i++) {
            		_fishTypes[i] = fishTypes1[i];
            	}
            	_fishMaterial = Material.SALMON;
            	_fishColor = ChatColor.WHITE;
            }
            else if(Num<fishPercentage[1]){
            	sizeMax = 20f;
            	sizeMin = 140f;
            	_fishTypes = new String[fishTypes2.length];
            	for(int i=0;i<fishTypes2.length;i++) {
            		_fishTypes[i] = fishTypes2[i];
            	}
            	_fishMaterial = Material.COD;
            	_fishColor = ChatColor.GREEN;
            }
            else if(Num<fishPercentage[2]){
            	sizeMax = 90f;
            	sizeMin = 200f;
            	_fishTypes = new String[fishTypes3.length];
            	for(int i=0;i<fishTypes3.length;i++) {
            		_fishTypes[i] = fishTypes3[i];
            	}
            	_fishMaterial = Material.TROPICAL_FISH;
            	_fishColor = ChatColor.AQUA;
            }
            else if(Num<fishPercentage[3]){
            	sizeMax = 1f;
            	sizeMin = 2f;
            	_fishTypes = new String[treasureTypes1.length];
            	for(int i=0;i<treasureTypes1.length;i++) {
            		_fishTypes[i] = treasureTypes1[i];
            	}
            	_fishMaterial = Material.MINECART;
            	_fishColor = ChatColor.YELLOW;
            }
            else if(Num<fishPercentage[4]){
            	sizeMax = 1f;
            	sizeMin = 2f;
            	_fishTypes = new String[treasureTypes2.length];
            	for(int i=0;i<treasureTypes2.length;i++) {
            		_fishTypes[i] = treasureTypes2[i];
            	}
            	_fishMaterial = Material.CHEST_MINECART;
            	_fishColor = ChatColor.RED;
            }
            else{
            	sizeMax = 160f;
            	sizeMin = 300f;
            	_fishTypes = new String[fishTypes4.length];
            	for(int i=0;i<fishTypes4.length;i++) {
            		_fishTypes[i] = fishTypes4[i];
            	}
            	_fishMaterial = Material.PUFFERFISH;
            	_fishColor = ChatColor.LIGHT_PURPLE;
            }
            
            _fishName = _fishTypes[r.nextInt(_fishTypes.length)];
            _size = sizeMin + r.nextFloat() * (sizeMax - sizeMin);
            _size = (float)Math.round(_size*100f)/100f;            
            
            Bukkit.broadcastMessage("You Caught " + _fishName + "!");
            
            ItemStack _fish = new ItemStack(_fishMaterial, 1);
            ItemMeta _fishMeta = _fish.getItemMeta();
            _fishMeta.setDisplayName(_fishColor + _fishName + " (" + _size + "kg)");
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
