package zad1;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardOpenOption;

public class Futil extends SimpleFileVisitor<Path> {
	public static void processDir(String folderName, String resultText) {
		

		
		try {
			// new textDir for saving
			FileChannel filleChannel = FileChannel.open(Paths.get(resultText),
					StandardOpenOption.WRITE,
					StandardOpenOption.CREATE,
					StandardOpenOption.TRUNCATE_EXISTING);
			//file channel scanning dirs
			Files.walk(Paths.get(folderName))
			.filter(Files::isRegularFile)
			.forEach((Path filePath) -> {
				try {
					FileChannel scanningChannel = FileChannel.open(filePath);
					ByteBuffer bb  = ByteBuffer.allocate((int) scanningChannel.size());
					
					
					
					scanningChannel.read(bb);
					bb.flip();
					CharBuffer cb = Charset.forName("Cp1250").decode(bb);
					System.out.println(cb);
					bb = StandardCharsets.UTF_8.encode(cb);
					
					// savingall text froms dirs here
					filleChannel.write(bb);
					scanningChannel.close();
					
				}  catch (IOException e) {
		            e.printStackTrace();
		        }
			});
		} catch (IOException e) {
            e.printStackTrace();
        }
    }			

}
