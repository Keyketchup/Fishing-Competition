package main;

import java.text.SimpleDateFormat;

import org.bukkit.entity.Player;

class FishingLog {
	public String name;
	public float size;
	public SimpleDateFormat time;
}
class BestFishInfo {
	public String name;
	public float size;
}

public class User {
	public Player player;
	public FishingLog[] log;
	public BestFishInfo info;
	
	public User(Player _player, BestFishInfo _info, FishingLog[] _log) {
		player = _player;
		info = _info;
		log = _log;
	}
}
