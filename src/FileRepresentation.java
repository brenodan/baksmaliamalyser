import java.util.ArrayList;


public class FileRepresentation {

	public String name;
	public String filePath;
	public ArrayList<String> actions = new ArrayList<String>();
	public ArrayList<String> fileContents = new ArrayList<String>();
	
	public FileRepresentation(){}
	
	public FileRepresentation(String name, String filePath){
		
		this.name = name;
		this.filePath = filePath;
	}
	
	public FileRepresentation(String name, ArrayList<String> fileContents, String filePath, ArrayList<String> actions){
		
		this.name = name;
		this.fileContents = fileContents;
		this.filePath = filePath;
		this.actions = actions;
		
	}
	
	public void addAction(String action) {
		
		this.actions.add(action);
		
	}
	
	public void addLineToFileContents(String line) {
		
		this.fileContents.add(line);
		
	}
	
	public ArrayList<String> getActions() {
		return actions;
	}

	public void setActions(ArrayList<String> actions) {
		this.actions = actions;
	}

	public String getFilePath(){
		return filePath;
	}
	
	public void setFilePath(String filePath){
		this.filePath = filePath;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<String> getFileContents() {
		return fileContents;
	}
	
	public void setFileContents(ArrayList<String> fileContents) {
		this.fileContents = fileContents;
	}
	
}
