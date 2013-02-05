package colin.CopyFiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FullPath {
	
	//Fields
	private String date;
	private String copyTo;
	private String finalPath;
	private Path datePath;
	
	//constructs
	public void setDate(String anyDate) {
		date=anyDate;
	}
	
	public void setcopyTo(String anyPath) {
		copyTo = anyPath;
	}
	
	public String getFPath() {
		finalPath = copyTo.substring(0,copyTo.lastIndexOf(System.getProperty("file.separator"))) + System.getProperty("file.separator") + date + 
			copyTo.substring(copyTo.lastIndexOf(System.getProperty("file.separator"))) ;
		return finalPath;
	}
	
	public void createDate() throws IOException {
		datePath = Paths.get(copyTo.substring(0,copyTo.lastIndexOf(System.getProperty("file.separator"))) +
				System.getProperty("file.separator") + date);
		if(Files.notExists(datePath)) {
			System.out.println("creating Dir " + datePath);
			Files.createDirectories(datePath);
		}
	}

}
