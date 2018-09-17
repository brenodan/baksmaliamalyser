import java.util.ArrayList;


public class Project {
	
	public ArrayList<FileRepresentation> files = new ArrayList<FileRepresentation>();
	public boolean readsIntent = false;
	public String name;
	public boolean putsExtra = false;
	
	public Project() {
		// TODO Auto-generated constructor stub
	}
	
	public Project(String name) {
		
		this.name = name;
	
	}
	
	public Project(String name, ArrayList<FileRepresentation> files){
		this.name = name;
		this.files = files;
	}
	
	
	
	public boolean isPutsExtra() {
		return putsExtra;
	}

	public void setPutsExtra(boolean putsExtra) {
		this.putsExtra = putsExtra;
	}

	public Project(ArrayList<FileRepresentation> files){
		this.files = files;
	}

	public boolean isReadsIntent() {
		return readsIntent;
	}

	public void setReadsIntent(boolean readsIntent) {
		this.readsIntent = readsIntent;
	}

	public ArrayList<FileRepresentation> getFiles() {
		return files;
	}

	public void setFiles(ArrayList<FileRepresentation> files) {
		this.files = files;
	} 
	
	

}
