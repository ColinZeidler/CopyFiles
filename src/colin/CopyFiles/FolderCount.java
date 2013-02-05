package colin.CopyFiles;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

public class FolderCount {
	
	//fields
	private int folderMax;
	private int folderNum =0;
	private String backupDir;
	private Path oldest;
	private FileTime oldestTime;
	private Path newFile;
	private FileTime newFileTime;
	private int result;
	private Path finalPath;
	private int folderNumber =0;
	
	//constructs
	public void setMax(int anyMax) {
		folderMax=anyMax;
	}
	
	public void countFolders() throws IOException {
		System.out.println("counting Folders");
		finalPath = Paths.get(backupDir.substring(0,backupDir.lastIndexOf(System.getProperty("file.separator"))+ 1));
		
			try {DirectoryStream<Path> stream = Files.newDirectoryStream(finalPath); {
				for (@SuppressWarnings("unused") Path file: stream) {
					folderNum++;
					System.out.println(folderNum);
				}
								
			}}
		
		
			catch (IOException x) {
				System.out.println(x);
			}
		
			if(folderNum > folderMax) {
				//run delete filetree with oldest as the path
				for (int i=0; i<folderNum-folderMax;i++){
					try {DirectoryStream<Path> stream = Files.newDirectoryStream(finalPath); {
						for (Path file: stream) {
							folderNumber++;
								if(oldest == null) {
									oldest=file;
									BasicFileAttributes attrs = Files.readAttributes(oldest, BasicFileAttributes.class);
									oldestTime = attrs.creationTime();
								}
								else {
									newFile = file;
									BasicFileAttributes attrs = Files.readAttributes(newFile, BasicFileAttributes.class);
									newFileTime = attrs.creationTime();
									result = oldestTime.compareTo(newFileTime);
									if(result>0) {
										oldest=newFile;
									}
								}
						}
						
					}}
				
					catch (IOException x) {
						System.out.println(x);
					}

					System.out.print("num over max = ");
					System.out.println(folderNumber-folderMax);
					folderNumber=0;
					Delete d1 = new Delete();
					System.out.println("Deleting folder " + oldest);
					Files.walkFileTree(oldest, d1);
					oldest = null;
					newFile = null;
				}
			}
	}
	
	public void setPath(String anyPath) {
		backupDir = anyPath;
	}

}
