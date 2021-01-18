package main;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{

	private String fishOneName = null;
	private float fishOneSize = -1f;
	
	private float sizeMin = 0.1f;
	private float sizeMax = 180f;
	
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "���� ��ȸ �÷������� ���� �Ǿ����ϴ�.");
	}
	
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "���� ��ȸ �÷������� ���� �Ǿ����ϴ�.");
	}
	
	
	@EventHandler
	public void PlayerJoin(PlayerJoinEvent ev) {
		ev.getPlayer().sendMessage(ChatColor.AQUA + ev.getPlayer().getName() + "���� ���� ��ȸ�� �����ϼ̽��ϴ�.");
		ev.getPlayer().setItemInHand(new ItemStack(Material.FISHING_ROD, 1));
	}
	
	@EventHandler
	public void PlayerFishing(PlayerFishEvent ev) {
		
		Player _player = ev.getPlayer();
		
		if(ev.getState().equals(PlayerFishEvent.State.CAUGHT_FISH)) {

            float _size = 0f;
            /* ������� ����� �������� ������ */

            Random r = new Random();
            _size = sizeMin + r.nextFloat() * (sizeMax - sizeMin);

            if (_size > fishOneSize) {
            	
            	for(Player _p : Bukkit.getOnlinePlayers()) {
            		_p.sendTitle("1�� " + _player.getName(), Float.toString(_size) + "kg");
            	}
                Bukkit.broadcastMessage(ChatColor.GREEN + _player.getName() + ChatColor.AQUA + "���� " + Float.toString(_size) + "kg�� ����⸦ ���� ���ΰ� �Ǿ����ϴ�");
                
                /* 1���� �÷��̾� �̸��� 1�� ����� ����� �ٲٱ� */
                fishOneSize = _size;
                fishOneName = _player.getName();
            }
            else {
            	Bukkit.broadcastMessage(_player.getName() + "���� " + Float.toString(_size) + "kg�� ����⸦ ���ҽ��ϴ�.");
            }
        }
		
		
		if(ev.getState() == PlayerFishEvent.State.REEL_IN) {
            _player.sendMessage(ChatColor.AQUA + "REEL_IN");
        }
        else if(ev.getState() == PlayerFishEvent.State.IN_GROUND) {
            _player.sendMessage(ChatColor.AQUA + "IN_GROUND");
        }
        else if(ev.getState() == PlayerFishEvent.State.FISHING) {
            _player.sendMessage(ChatColor.AQUA + "FISHING");
        }
        else if(ev.getState() == PlayerFishEvent.State.FAILED_ATTEMPT) {
            _player.sendMessage(ChatColor.AQUA + "FAILED_ATTEMPT");
        }
        else if(ev.getState() == PlayerFishEvent.State.BITE) {
            _player.sendMessage(ChatColor.AQUA + "BITE");
        }
			
	}
	
	
}
