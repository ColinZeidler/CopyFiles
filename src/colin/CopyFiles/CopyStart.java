package colin.CopyFiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class CopyStart {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		//setting the date
		DateFormat day = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        DateFormat time = new SimpleDateFormat("HH_mm_ss");
		String theDay = day.format(date);
		String theTime = time.format(date);
		String theDate = theDay + " at " + theTime;
        
		//getting directory to start copying from
		Path p2 = Paths.get(args[0]);
				
		//creating the full copy path with date/creating date folder / getting dir to copy to(args[2])
		FullPath fp1 = new FullPath();
		fp1.setcopyTo(args[1]);
		fp1.setDate(theDate);
		fp1.createDate();
		Path p1 = Paths.get(fp1.getFPath());
		String p4 = args[1];
		
		//Initializing Copy
		Copy c1 = new Copy();
		c1.setFullPath(p1);
		c1.setcopyPath(p2);
		//starting the copy
		Files.walkFileTree(p2, c1);
		
		System.out.println("Done Copying");
		
		//checking the number of folders, and removing the oldest if there are more than the max
		int maxFolder = java.lang.Integer.parseInt(args[2]);
		FolderCount fc1 = new FolderCount();
		fc1.setMax(maxFolder);
		fc1.setPath(p4);
		fc1.countFolders();
		
		Scanner keyIn = new Scanner(System.in);

		System.out.print("Press the enter key to continue");
		keyIn.nextLine();
		keyIn.close();

	}

}
