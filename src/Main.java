import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;


public class Main {
	
	
	/**
	 * Populate a project representation with file path and file name
	 * @param fileList
	 * @return ArrayList<FileRepresentation> fileRepresentation
	 */
	public static ArrayList<FileRepresentation> populateProject(List<File> fileList){
		
		ArrayList<FileRepresentation> project = new ArrayList<FileRepresentation>();
		
		for (int i = 0; i < fileList.size(); i++) {
			
			if(fileList.get(i).getName().contains("Activity") && !fileList.get(i).getName().contains("$")){
				FileRepresentation fileRepresentation = new FileRepresentation(fileList.get(i).getName(), 
						fileList.get(i).getAbsolutePath());
				project.add(fileRepresentation);
			}
		}
		
		return project;
		
	}
	
	public static ArrayList<FileRepresentation> fileRepresentationAddContents(ArrayList<FileRepresentation> fileRepresentation){
		
		
		if(!fileRepresentation.isEmpty() && fileRepresentation != null) {
			
			System.err.println("checking file representation information");
			for (int i = 0; i < fileRepresentation.size(); i++) {
				
				fileRepresentation.get(i).fileContents = fileReading(fileRepresentation.get(i).getFilePath());
				
			}	
		}
		
		return fileRepresentation;
	}
	
	/**
	 * Populates a project representation (ArrayList<FileRepresentation>) with actions
	 * @param fileRepresentation
	 * @return fileRepresentation
	 */
	public static ArrayList<FileRepresentation> fileRepresentationAddAction(ArrayList<FileRepresentation> fileRepresentation){
		
		try {
			
			if(!fileRepresentation.isEmpty() && fileRepresentation != null) {
				
				ArrayList<FileRepresentation> rFileRepresentation = fileRepresentation;
				for (int i = 0; i < rFileRepresentation.size(); i++) {
					
					rFileRepresentation.get(i).actions = findAction(rFileRepresentation.get(i).fileContents);
					
				}
				
				return rFileRepresentation;
			
			} else {
				
				System.out.println("FindFRAction " + "array null or empty");
				return null;
				
			}		
		
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		return null;
	}
	
	/**
	 * Generates an array with the absolute path of the files in the project's directory
	 * @param fileList
	 * @return arrayList
	 */
	public static ArrayList<String> generateFileArray(List<File> fileList) {
		
		ArrayList<String> listF = new ArrayList<String>();
		
		for (int i = 0; i < fileList.size(); i++) {
			listF.add(fileList.get(i).getAbsolutePath());
		}
		
		return listF;
		
	}
	
	/**
	 * This method looks for Intent actions on files
	 * @param fileRepresentation
	 * @return ArrayList<String> actions
	 */
	public static ArrayList<String> findAction(ArrayList<String> fileRepresentation) {
		
		ArrayList<String> actions = new ArrayList<String>();
		
		String smaliActionRepresentation = "Landroid/content/Intent;->getAction()Ljava/lang/String;";
		String actionPredicate = "android.intent.action";
		boolean flag = false;
		
		for (int i = 0; i < fileRepresentation.size(); i++) {
		
			if(fileRepresentation.get(i).contains(smaliActionRepresentation)) {
				
				flag = true;
				
			} 
			
			else if(flag && fileRepresentation.get(i).contains("const-string")) {
				
				if(fileRepresentation.get(i).contains(actionPredicate)) {
					
					String line = fileRepresentation.get(i);
					try {
					
						if(line.contains("\"")) {
							
							int index = line.indexOf("\"");
							line = line.substring(index + 1, line.length());
							index = line.indexOf("\"");
							line = line.substring(0, index);
							actions.add(line);
							
							flag = false;
						} 
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		return actions;
		
	}
	
	public static ArrayList<String> fileReading(String pathContents) {
		
		String fileContents = "";
		ArrayList<String> fileRepresentation = new ArrayList<String>();
		File file = new File(pathContents);
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			while ((fileContents = br.readLine()) != null) {
				fileRepresentation.add(fileContents);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return fileRepresentation;
	}
	
	public static void printFileRepresentation(ArrayList<String> fileRepresentation) {
		
		for(int i = 0; i < fileRepresentation.size(); i++){
			System.out.println(fileRepresentation.get(i));
		}
	}
	
	public static List<File> listf(String directoryName) {
		//System.out.println(directoryName);
		File directory = new File(directoryName);
		List<File> resultList = new ArrayList<File>();
		
	    // get all the files from a directory
	    File[] fList = directory.listFiles();
	    resultList.addAll(Arrays.asList(fList));
	    for (File file : fList) {
	    	if (file.isFile()) {
	    		//System.out.println(file.getAbsolutePath());
	        } else if (file.isDirectory()) {
	            resultList.addAll(listf(file.getAbsolutePath()));
	        }
	    }
	    //System.out.println(fList);
	    return resultList;
	} 
	
	/**
	 * Identifies files with the call getIntent
	 * @param fileRepresentation
	 * @return boolean
	 */
	public static boolean containsGetIntent(ArrayList<String> fileRepresentation){
		
		for(int i = 0; i < fileRepresentation.size(); i++){
			if(fileRepresentation.get(i).contains("getIntent")) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Searches for the files that have "Activity" in the name
	 * @param fileList
	 * @return activityFileList
	 */
	public static ArrayList<String> listOfActivityFiles(ArrayList<String> fileList) {
		
		ArrayList<String> activityFileList = new ArrayList<String>();
		for(int i = 0; i < fileList.size(); i++){
			
			if(fileList.get(i).contains("Activity")){
				activityFileList.add(fileList.get(i));
			}
		}
		
		return activityFileList;
	}
	
	/**
	 * Search for activities that contain the call "getIntent()"
	 * @param activityFileList
	 * @return listOfSelectedFiles
	 */
	public static ArrayList<String> listOfActivitiesWithGetIntent(ArrayList<String> activityFileList){
		
		ArrayList<String> listOfSelectedFiles = new ArrayList<String>();
		for (int i = 0; i < activityFileList.size(); i++) {
			
			if(containsGetIntent(fileReading(activityFileList.get(i)))){
				listOfSelectedFiles.add(activityFileList.get(i));
			}
			
		}
		
		return listOfSelectedFiles;
		
	}
	/**
	 * Landroid/content/Intent;->getBooleanExtra(Ljava/lang/String;Z)Z
	 * @param args
	 */
	
	public static boolean hasGetExtras(FileRepresentation fileRepresentation) {
		
		ArrayList<String> fileContents = fileRepresentation.fileContents;
		ArrayList<Integer> lines = new ArrayList<Integer>();
		for (int i = 0; i < fileContents.size(); i++) {
			String string = fileContents.get(i);
			if(string.contains("Extra") && string.contains("get") 
					&& string.contains("Landroid/content/Intent;->")) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean hasGetData(FileRepresentation fileRepresentation) {
		
		ArrayList<String> fileContents = fileRepresentation.fileContents;
		ArrayList<Integer> lines = new ArrayList<Integer>();
		for (int i = 0; i < fileContents.size(); i++) {
			String string = fileContents.get(i);
			if(string.contains("getData") && string.contains("Landroid/content/Intent;->")) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean hasGetStringExtra(FileRepresentation fileRepresentation) {
		
		ArrayList<String> fileContents = fileRepresentation.fileContents;
		ArrayList<Integer> lines = new ArrayList<Integer>();
		for (int i = 0; i < fileContents.size(); i++) {
			String string = fileContents.get(i);
			if(string.contains("getStringExtra") && string.contains("Landroid/content/Intent;->")) {
				return true;
			}
		}
		return false;
	}
	
	public static ArrayList<String> getKeyStringExtra(FileRepresentation fileRepresentation) {
		
		ArrayList<String> fileContents = fileRepresentation.fileContents;
		ArrayList<String> returnArray = new ArrayList<String>();
		ArrayList<Integer> lines = new ArrayList<Integer>();
		//const-string v1, "itemType"
		for (int i = 0; i < fileContents.size(); i++) {
			String string = fileContents.get(i);
			if(string.contains("getStringExtra") && string.contains("Landroid/content/Intent;->")) {
				if(fileContents.get(i-2).contains("const-string")){
					String pLine = fileContents.get(i-2);
					int begin = pLine.indexOf("\"");
					int end = pLine.lastIndexOf("\"");
					pLine = pLine.substring(begin + 1, end);
					returnArray.add(pLine);
				}
			}
		}
		return returnArray;
	}
	
	public static ArrayList<String> getKeyIntExtra(FileRepresentation fileRepresentation) {
		
		ArrayList<String> fileContents = fileRepresentation.fileContents;
		ArrayList<String> returnArray = new ArrayList<String>();
		ArrayList<Integer> lines = new ArrayList<Integer>();
		for (int i = 0; i < fileContents.size(); i++) {
			String string = fileContents.get(i);
			if(string.contains("getIntExtra") && string.contains("Landroid/content/Intent;->")) {
				System.out.println("String " + string);
				if(fileContents.get(i-2).contains("const-string")){
					
					String pLine = fileContents.get(i-2);
					System.out.println("pString " + pLine);
					int begin = pLine.indexOf("\"");
					int end = pLine.lastIndexOf("\"");
					pLine = pLine.substring(begin + 1, end);
					returnArray.add(pLine);
				}
			}
		}
		return returnArray;
	}
	
	public static boolean hasPutExtra(FileRepresentation fileRepresentation) {
		
		ArrayList<String> fileContents = fileRepresentation.fileContents;
		ArrayList<Integer> lines = new ArrayList<Integer>();
		for (int i = 0; i < fileContents.size(); i++) {
			String string = fileContents.get(i);
			if(string.contains("putExtra") && string.contains("Landroid/content/Intent;->")) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean hasGetIntExtra(FileRepresentation fileRepresentation) {
		
		ArrayList<String> fileContents = fileRepresentation.fileContents;
		ArrayList<Integer> lines = new ArrayList<Integer>();
		for (int i = 0; i < fileContents.size(); i++) {
			String string = fileContents.get(i);
			if(string.contains("getIntExtra") && string.contains("Landroid/content/Intent;->")) {
				return true;
			}
		}
		return false;
	}
	
	public static String[] getDirectoryList(String pathToFolder){
		
		File file = new File(pathToFolder);
		String[] directories = file.list(new FilenameFilter() {
		  public boolean accept(File current, String name) {
		    return new File(current, name).isDirectory();
		  }
		});
		return directories;
	}
	
	public static void main(String[] args) {
		//String filePath = "/home/breno/workspace2/SmaliAnalyzer/src/MainActivity.smali";
		//String folderPath = "/home/breno/workspace2/SmaliAnalyzer/src";
		
		
		String folderPath = "/home/breno/Downloads/smali/out/";
		String[] projects = getDirectoryList(folderPath); 
		ArrayList<Project> projectList = new ArrayList<Project>();
		
		ArrayList<String> stringKeyLines = new ArrayList<String>();
		ArrayList<String> intKeyLines = new ArrayList<String>();
		
		for (int i = 0; i < projects.length; i++) {
			
			Project project = new Project(projects[i]);
			//System.out.println(project.name);
			ArrayList<FileRepresentation> fileRepresentation = new ArrayList<FileRepresentation>();
			
			try {
				
				String temp = folderPath + project.name;
				fileRepresentation = populateProject(listf(temp));
				
				if(!fileRepresentation.isEmpty()) {
					
					project.files = fileRepresentation;
					fileRepresentation = fileRepresentationAddContents(fileRepresentation);
					fileRepresentation = fileRepresentationAddAction(fileRepresentation);
					
					for (int j = 0; j < fileRepresentation.size(); j++) {
						
						if(containsGetIntent(fileRepresentation.get(j).getFileContents())){
							
							//System.out.println(fileRepresentation.get(j).getName() + " path: " + fileRepresentation.get(j).getFilePath());
							//System.out.println(fileRepresentation.get(i).getFilePath());
							if(hasGetData(fileRepresentation.get(j)) || hasGetExtras(fileRepresentation.get(j))) {
								project.readsIntent = true;
								//System.out.println(true);
								
							}
							
							if(hasPutExtra(fileRepresentation.get(j))) {
								
								project.putsExtra = true;
								
							}
							
							if(hasGetIntExtra(fileRepresentation.get(j))){
								
								ArrayList<String> tempArray = getKeyIntExtra(fileRepresentation.get(j));
								
								for (int j2 = 0; j2 < tempArray.size(); j2++) {
									
									if(!stringKeyLines.contains(tempArray.get(j2))) {
										stringKeyLines.add(tempArray.get(j2));
									}
									
								}
							}
							
							if(hasGetStringExtra(fileRepresentation.get(j))){						
								
								ArrayList<String> tempArray = getKeyStringExtra(fileRepresentation.get(j));
								
								for (int j2 = 0; j2 < tempArray.size(); j2++) {
									
									if(!stringKeyLines.contains(tempArray.get(j2))) {
										intKeyLines.add(tempArray.get(j2));
									}
								}						
							}					
						}
					}
					
				} else {
					System.err.println("project representation is empty");
				}
				
				projectList.add(project);
			
			} catch (Exception e) {
				
				e.printStackTrace();
				
			}
			
			
		}
		
		
		
		try {
		    
			//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("/home/breno/Downloads/virusshare/out/analysis_results2.txt", true)));
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("/home/breno/Downloads/smali/out/analysis_results2.txt", true)));
			
			
			int counterGets = 0;
			int counterPuts = 0;
			for (int i = 0; i < projectList.size(); i++) {
				
				if(projectList.get(i).readsIntent){
					//System.out.println(true);
					counterGets ++;
				}
				if(projectList.get(i).putsExtra){
					//System.out.println(true);
					counterPuts ++;
				}
			}
			
			System.out.println("Number of projects that call getIntent(): " + counterGets);
			System.out.println("Number of projects that call putExtra(): " + counterPuts);
			System.out.println("Number of projects: " + projectList.size());
			
			out.println("Number of projects that call getIntent(): " + counterGets);
			out.println("Number of projects that call putExtra(): " + counterPuts);
			out.println("Number of projects: " + projectList.size());
			
			System.out.println("Percentage of project using getIntent(): " + (((float)counterGets/(float)projectList.size()) * 100) + "%");
			System.out.println("Percentage of project using putExtra(): " + (((float)counterPuts/(float)projectList.size()) * 100) + "%");
			
			out.println("Percentage of project using getIntent(): " + (((float)counterGets/(float)projectList.size()) * 100) + "%");
			out.println("Percentage of project using putExtra(): " + (((float)counterPuts/(float)projectList.size()) * 100) + "%");
			
			System.out.println("int keys: ");
			out.println("int keys: ");
			for (int i = 0; i < intKeyLines.size(); i++) {
				
				System.out.println(intKeyLines.get(i));
				out.println(intKeyLines.get(i));
				
			}
			
			System.out.println("string keys: ");
			out.println("string keys: ");
			for (int i = 0; i < stringKeyLines.size(); i++) {
				
				System.out.println(stringKeyLines.get(i));
				out.println(stringKeyLines.get(i));
				
			}
		
		    out.close();
			
		} catch (IOException e) {
		    //exception handling left as an exercise for the reader
			e.printStackTrace();
		}
		
		
		//ArrayList<String> getIntentFiles = listOfActivitiesWithGetIntent(listOfActivityFiles(generateFileArray(listf(folderPath))));
		//System.out.println(getIntentFiles.toString());
		/*for (int i = 0; i < getIntentFiles.size(); i++) {
			//printFileRepresentation(fileReading(getIntentFiles.get(i)));
			printFileRepresentation(findAction(fileReading(getIntentFiles.get(i))));
		
		}
		
		/*
		ArrayList<String> fileRepresentation = fileReading(filePath);
		printFileRepresentation(fileRepresentation);
		System.out.println(containsGetIntent(fileRepresentation));
		*/
	}
	
}
