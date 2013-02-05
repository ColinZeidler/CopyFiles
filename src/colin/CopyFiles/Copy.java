package colin.CopyFiles;

import static java.nio.file.FileVisitResult.CONTINUE;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class Copy extends SimpleFileVisitor<Path> {

	// fields
	private Path copyToNew;
	private Path copyToNewDir;
	private int dirNum = 0;
	private Path fullPath;
	private Path copyPath;

	// constructs

	public void setFullPath(Path anyPath) {
		fullPath = anyPath;
	}

	public void setcopyPath(Path anyPath) {
		copyPath = anyPath;
	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
		if (!file.toString().contains(
				"dynmap" + System.getProperty("file.separator") + "web")) {
			try {

				String sFile = file.toString();
				copyToNew = Paths.get(fullPath
						+ System.getProperty("file.separator")
						+ sFile.substring(copyPath.toString().length()));

				Files.copy(file, copyToNew);

				System.out.println("Copying file " + file + " to " + copyToNew);
			} catch (IOException e) {
				System.out.println("Copying file " + file
						+ " failed to copy to " + copyToNew);
			}
		}
		return CONTINUE;
	}

	@Override
	public FileVisitResult preVisitDirectory(Path pDir, BasicFileAttributes attr) {
		if (!pDir.toString().contains(
				"dynmap" + System.getProperty("file.separator") + "web")) {
			try {
				dirNum++;
				if (dirNum > 1) {
					String sDir = pDir.toString();
					copyToNewDir = Paths.get(fullPath
							+ System.getProperty("file.separator")
							+ sDir.substring(copyPath.toString().length()));
				} else {
					copyToNewDir = fullPath;
				}

				Files.copy(pDir, copyToNewDir);

				System.out.println("Copying folder " + pDir + " to "
						+ copyToNewDir);
			} catch (IOException e) {
				System.out.println("Copying folder " + pDir
						+ " failed to copy to " + copyToNewDir);
				System.out.println(e);
			}
		}
		return CONTINUE;
	}

}
