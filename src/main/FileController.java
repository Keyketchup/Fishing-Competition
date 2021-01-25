package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
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
		File dir = new File(dirPath);
		
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
				/*Input Default Data*/
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
			
			/* Todo Read bestFishReader */
			
			Scanner scan = new Scanner(bestFishInfo);
			String bestFishName = scan.nextLine();
			float bestFishSize = scan.nextFloat();
			
			res.info.name = bestFishName;
			res.info.size = bestFishSize;
			
			/*Code*/
			
			scan.close();
			
			/* Todo Read fishingLogReader */
			
			scan = new Scanner(fishingLog);
			while(scan.hasNext()) {
				String name = scan.nextLine();
				float size = scan.nextFloat();
				SimpleDateFormat time = new SimpleDateFormat(scan.nextLine());
				
				/* Todo Make list and Save */
				
			}
			
			/*Code*/
			
			scan.close();
			
		}
		catch(Exception Ex) {
			
		}
		
		return res;
	}
	
}
