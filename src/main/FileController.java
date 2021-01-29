package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class FileController {
	
	final public static String bestFishInfoFileName = "/bestFishInfo.txt" ;
	final public static String fishingLogFileName = "/fishingLog.txt";
	
	public static void CreateDefaultData() {
		
		String path = "plugins/" + "Fishing Competition";
		File folder = new File(path);
		
		String userPath = path + "/Users";
		String serverPath = path + "/Users";
		
		if (!folder.exists()) {
			try {
				folder.mkdir();
				Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "Fishing Competition Plugin Loading...");
				
				File userFolder = new File(userPath);
				File serverFolder = new File(serverPath);
				
				userFolder.mkdir();
				serverFolder.mkdir();
				
				/* Todo server code script */
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
	
	public static void WriteUserData(Player player) {
		
	}
	
	public static void WriteUserData(User userData) {
		try {
			String dirPath = "plugins/" + "Fishing Competition" + "/Users/";
			String userDirPath = dirPath + userData.player.getName();
			
			String fishingLogPath = userDirPath + fishingLogFileName;
			BufferedWriter _writer = new BufferedWriter(new FileWriter(fishingLogPath));
			
			for(int i=0;i<userData.log.size();i++) {
				if(i!=0) {
					_writer.newLine();
				}
				_writer.write(userData.log.get(i).name);
				_writer.newLine();
				_writer.write(Float.toString(userData.log.get(i).size));
				_writer.newLine();
				_writer.write(userData.log.get(i).time.toString());
			}
			
			_writer.close();
		}
		catch(Exception ex) {
			Bukkit.getConsoleSender().sendMessage(ex.getMessage());
		}
	}
	
	public static User ReadUserData(Player player) {
		User res = new User(player, null, null);
		
		String dirPath = "plugins/" + "Fishing Competition" + "/Users/";
		String userDirPath = dirPath + player.getName();
		
		String bestFishInfoPath = userDirPath + bestFishInfoFileName;
		String fishingLogPath = userDirPath + fishingLogFileName;
		
		File bestFishInfo = new File(bestFishInfoPath);
		File fishingLog = new File(fishingLogPath);
		
		try {
			
			Scanner scan = new Scanner(bestFishInfo);
			String bestFishName = scan.nextLine();
			Bukkit.getConsoleSender().sendMessage(bestFishName);
			float bestFishSize = scan.nextFloat();
			Bukkit.getConsoleSender().sendMessage(Float.toString(bestFishSize));
			
			res.info.name = bestFishName;
			res.info.size = bestFishSize;
			
			Bukkit.getConsoleSender().sendMessage("1");
			
			scan.close();
			Bukkit.getConsoleSender().sendMessage("1");
			
			scan = new Scanner(fishingLog);
			
			Bukkit.getConsoleSender().sendMessage("1");
			
			while(scan.hasNext()) {
				String name = scan.nextLine();
				float size = scan.nextFloat();
				Bukkit.getConsoleSender().sendMessage(Float.toString(size));
				SimpleDateFormat time = new SimpleDateFormat(scan.nextLine());
				
				res.log.add(new FishingLog(name, size, time));						
			}
						
			scan.close();
			
		}
		catch(Exception Ex) {
			//Bukkit.getConsoleSender().sendMessage(Ex.getMessage());
		}
		//Bukkit.getConsoleSender().sendMessage(res.info.name);
		return res;
	}
	
}
