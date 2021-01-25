package main;

import org.bukkit.ChatColor;
import org.bukkit.Material;

public class Constants {
	
	final public static String pluginName = "Fishing Competition";
	
	final public static String[][] fishTypes = {
			{"Haddock", "John Dory", "Brill", "Lobstar", "Blue Cod", "Bluenose", "Bluefish", "Northern Pike", "Sea Perch", "Squid", "Rainbow Trout", "Bluegill", "Zander", "Europian Bass", "Largemouth Bass", "Bala Shark", "Atlantic Silverside", "Bitterling", "Oregon Chub", "Northern Red Snapper", "Pollack", "Atlantic Mackerel", "Common Carp", "Chum Salmon", "Guppy", "Goldfish", "Blue Tang", "Swordfish", "Neon Tetra", "Creeperfish", "Common Molly", "Freshwater Angelfish"},
			{"Stripped Bass", "Freshwater Eal", "Monkfish", "Hoki", "Angler", "Atlantic Cod", "Garfish", "Europian Perch", "Zombiefish", "Wels Catfish", "Atlantic Salmon", "Starfish"},
			{"Ocean Sunfish", "Ghost Shark", "Southern Bluefin Tuna","Atlantic Bluefin Tuna", "Skeletonfish", "Great White Shark", "Blue Shark", "Salmon Shark", "Giant Oceanic Manta Ray", "Bull Shark"},
			{"Toy Car", "Broken Toy Robot", "Diary of Wimpy Potato", "Terminator DVD", "Finding the Nemo DVD", "Dumbo DVD", "Bambi DVD", "Phone Case", "Toy Boat", "Mario Figure", "Computer-Written Letter from India", "Donkey Kong Figure", "Empty Treasure Chest", "Statue Of Llama", "Statue Of Rabbit", "Statue of Creeper", "Statue of Zombie", "Statue of Skeleton", "Fake Diamond", "Fake Trophy", "Rugby Ball", "Chess Piece", "Scrap of Willy Wonka's Chocolate", "Unlucky Rabbit's Foot", "Photo of a White Sheep"},
			{"Viking Boat Fragment", "Egyptian Diamond", "Steve's Missing Jewlery", "Pirate's Eyepatch", "Chest full of gold", "Piece of Long-written Music", "Egyptian Clay Doll", "Limited Version of Monopoly", "Diary of an Everlasting Creeper", "Limited Toy Car", "Miniature Christmas Tree", "Statue of Wither", "Statue of Ender Dragon", "Broken Piece of an Everlasting Mana Pool", "Willy Wonka's Golden Ticket", "Xbox Limited Controller", "Playstation 5", "Ender Pearl Neckglace", "Photo of a Brown Sheep"},
			{"Whale Shark", "White Shark", "Red Shark", "Blue Shark", "Withered Shark", "Green Shark", "A Legendary Pufferfish", "A Legendary Starfish", "A Legendary Salmon", "A Legendary Cod" ,"A Legendary Snapper"}
	};
	
	final public static int[] fishPercentage = {20, 30, 35, 40, 42, 43};
	
	final public static int[] minSizeArr = {2, 20, 90, 1, 2, 160};
	final public static int[] maxSizeArr = {40, 140, 200, 2, 3, 300};
	final public static Material[] fishMaterialArr = {Material.SALMON, Material.COD, Material.TROPICAL_FISH, Material.MINECART, Material.CHEST_MINECART, Material.PUFFERFISH};
	final public static ChatColor[] fishColorArr = {ChatColor.WHITE, ChatColor.GREEN, ChatColor.AQUA, ChatColor.YELLOW, ChatColor.RED, ChatColor.LIGHT_PURPLE};
}
