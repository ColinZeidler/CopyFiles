package colin.CopyFiles;

import static java.nio.file.FileVisitResult.CONTINUE;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class Delete extends SimpleFileVisitor<Path> {
	
	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attr) throws IOException {
		Files.delete(file);
		return CONTINUE;
	}
	
	@Override
	public FileVisitResult postVisitDirectory(Path pDir, IOException exc) throws IOException {
		Files.delete(pDir);
		return CONTINUE;
	}

}
