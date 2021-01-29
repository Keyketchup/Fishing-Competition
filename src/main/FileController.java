package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class FileController {
	
	final public static String bestFishInfoFileName = "/bestFishInfo.txt" ;
	final public static String fishingLogFileName = "/fishingLog.txt";
	public static String serverBestFishInfoPath;
	
	public static void CreateDefaultData() {
		
		String path = "plugins/" + "Fishing Competition";
		File folder = new File(path);
		
		String userPath = path + "/Users";
		String serverPath = path + "/Server";
		serverBestFishInfoPath = serverPath + bestFishInfoFileName;
		
		if (!folder.exists()) {
			try {
				folder.mkdir();
				Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "Fishing Competition Plugin Loading...");
				
				File userFolder = new File(userPath);
				File serverFolder = new File(serverPath);
				
				userFolder.mkdir();
				serverFolder.mkdir();		
				
				BufferedWriter _writer = new BufferedWriter(new FileWriter(serverBestFishInfoPath, true));
				_writer.write("Player\nEmpty\n0");
				_writer.close();
				
			}
			catch(Exception ex) {
				Bukkit.getConsoleSender().sendMessage(ex.getMessage());
			}
		}
		
	}
	
	public static void CreateUserData(Player player) {
		
		String dirPath = "plugins/" + "Fishing Competition" + "/Users/";
		CreateDefaultData();
		
		String userDirPath = dirPath + player.getName();
		File userDir = new File(userDirPath);
		
		if (!userDir.exists()) {
			
			try {
				userDir.mkdir();
				
				String bestFishInfoPath = userDirPath + bestFishInfoFileName;
				String fishingLogPath = userDirPath + fishingLogFileName;
							
				BufferedWriter fw = new BufferedWriter(new FileWriter(bestFishInfoPath, true));
				fw.write("Empty\n0");
				fw.flush();
				fw.close();
				
				fw = new BufferedWriter(new FileWriter(fishingLogPath, true));
				fw.close();
				
			}
			catch(Exception ex) {
				
			}
			
			
		}
		else {
			//Todo Make Users Directory
		}
		
	}
	
	public static void WriteUserData(Player _player, BestFishInfo _info) {
		
		try {
			String dirPath = "plugins/" + "Fishing Competition" + "/Users/";
			String userDirPath = dirPath + _player.getName();
			
			String bestFishInfoPath = userDirPath + bestFishInfoFileName;
			String fishingLogPath = userDirPath + fishingLogFileName;
			
			User _user = ReadUserData(_player);
			
			BufferedWriter _writer = new BufferedWriter(new FileWriter(fishingLogPath, true));
			
			_writer.append(_info.name + " " + Float.toString(_info.size) + " " + (new SimpleDateFormat("yyyy-MM-dd HH:mm")).format(new Date()) + "\n");
			
			_writer.close();
			
			if(_user.info.size >= _info.size)
				return;
			
			_writer = new BufferedWriter(new FileWriter(bestFishInfoPath, false));
			
			_writer.append(_info.name + "\n" + _info.size);
			
			_writer.close();
			
			WriteServerData(_player, _info);
			
		}
		catch(Exception ex) {
			
		}
	}
	
	public static User ReadUserData(Player player) {
		User res = new User(player, new BestFishInfo("Empty", 0), null);
		
		String dirPath = "plugins/" + "Fishing Competition" + "/Users/";
		String userDirPath = dirPath + player.getName();
		
		String bestFishInfoPath = userDirPath + bestFishInfoFileName;
		String fishingLogPath = userDirPath + fishingLogFileName;
		
		File bestFishInfo = new File(bestFishInfoPath);
		File fishingLog = new File(fishingLogPath);
		
		try {
			
			Scanner scan = new Scanner(bestFishInfo);
			String bestFishName = scan.nextLine();
			float bestFishSize = scan.nextFloat();
			
			res.info.name = bestFishName;
			res.info.size = bestFishSize;
			
			scan.close();
			
		}
		catch(Exception Ex) {
		}
		
		return res;
	}
	
	public static void WriteServerData(Player _player, BestFishInfo _info) {
		
		ServerBestFishInfo _serverInfo = ReadServerData();
		
		try {
			
			if(_serverInfo.fishSize >= _info.size)
				return;
			
			BufferedWriter _writer = new BufferedWriter(new FileWriter(serverBestFishInfoPath, false));
			
			_writer.append(_player.getName() + "\n" + _info.name + "\n" + Float.toString(_info.size));
			
			Fishing.Change1stPlayer(_info.size, _player, _info.name);
			
			_writer.close();
			
		}
		catch(Exception ex) {
		}
	}
	
	public static ServerBestFishInfo ReadServerData() {
		ServerBestFishInfo _res = new ServerBestFishInfo();
		
		try {
			
			File bestFishInfo = new File(serverBestFishInfoPath);
			
			Scanner scan = new Scanner(bestFishInfo);
			String bestPlayerName = scan.nextLine();
			String bestFishName = scan.nextLine();
			float bestFishSize = scan.nextFloat();
			
			scan.close();
			
			_res.playerName = bestPlayerName;
			_res.fishName = bestFishName;
			_res.fishSize = bestFishSize;
		}
		catch(Exception Ex) {
		}
		
		return _res;
	}
	
}
