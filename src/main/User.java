package main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.bukkit.entity.Player;

class FishingLog {
	public String name;
	public float size;
	public SimpleDateFormat time;
	
	public FishingLog(String _name, float _size, SimpleDateFormat _time) {
		name = _name;
		size = _size;
		time = _time;
	}
}

class ServerBestFishInfo {
	public String playerName;
	public String fishName;
	public float fishSize;
	
	public ServerBestFishInfo() {
		playerName = null;
		fishName = null;
		fishSize = 0;
	}
}

class BestFishInfo {
	public String name;
	public float size;
	
	public BestFishInfo(String _name, float _size) {
		name = _name;
		size = _size;
	}
}

public class User {
	public Player player;
	public ArrayList<FishingLog> log;
	public BestFishInfo info;
	
	public User(Player _player, BestFishInfo _info, ArrayList<FishingLog> _log) {
		player = _player;
		info = _info;
		log = _log;
	}
}
