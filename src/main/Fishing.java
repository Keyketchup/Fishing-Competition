package main;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Fishing{
	
	private float fishOneSize = -1f;
	
	public void PlayerFishing(PlayerFishEvent ev) {
		
		Player _player = ev.getPlayer();
		
		if(ev.getState().equals(PlayerFishEvent.State.CAUGHT_FISH)) {
			
			ev.getCaught().remove();
			
			int Num = 0;
            float _size = 0f;
            Random r = new Random();
            
            int _numLimit = Constants.fishPercentage[Constants.fishPercentage.length-1];
            
            Num = r.nextInt(_numLimit);
            FishData fishData = new FishData(null, 0, 0, Material.AIR, ChatColor.WHITE);
            
            for(int i=0; i<Constants.fishPercentage.length; i++) {
	            if(Num<Constants.fishPercentage[i]){
	            	fishData = new FishData((Constants.fishTypes[i][r.nextInt(Constants.fishTypes[i].length)]), Constants.minSizeArr[i], Constants.maxSizeArr[i], Constants.fishMaterialArr[i], Constants.fishColorArr[i]);
	            	break;
	            }
            }
            
            _size = fishData.minSize + r.nextFloat() * (fishData.maxSize - fishData.minSize);
            _size = (float)Math.round(_size*100f)/100f;            
            
            Bukkit.broadcastMessage("You Caught " + fishData.name + "!");
            
            ItemStack _fish = new ItemStack(fishData.fishMaterial, 1);
            ItemMeta _fishMeta = _fish.getItemMeta();
            _fishMeta.setDisplayName(fishData.chatColor + fishData.name + " (" + _size + "kg)");
            _fishMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
            _fish.setItemMeta(_fishMeta);
            _player.getInventory().addItem(_fish);
            
            User _user = FileController.ReadUserData(_player);
            _player.sendMessage(_user.info.name + "   " + _user.info.size);

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
		_itemMeta.setDisplayName(ChatColor.LIGHT_PURPLE + _player.getName() + " catched " + _fishName + " (" + _size + ") and broke the record");
		_itemMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
		_item.setItemMeta(_itemMeta);
		_player.getInventory().addItem(_item);
		
		for(Player _p : Bukkit.getServer().getOnlinePlayers()) {
			_p.playSound(_p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST, 1, 1);
		}
		
        fishOneSize = _size;
	}
}
