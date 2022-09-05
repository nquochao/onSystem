package oliviaproject.util.file;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipUtil {
	public static void main(String[] args) throws IOException {
	}

	public void unzip() throws IOException {
		ZipFile zipFile = new ZipFile("C:/test.zip");

		Enumeration<? extends ZipEntry> entries = zipFile.entries();

		while (entries.hasMoreElements()) {
			ZipEntry entry = entries.nextElement();
			InputStream stream = zipFile.getInputStream(entry);
		}

	}

	private void extractAll(URI fromZip, Path toDirectory) throws Exception {
		FileSystems.newFileSystem(fromZip, Collections.emptyMap()).getRootDirectories().forEach(root -> {
			// in a full implementation, you'd have to
			// handle directories
			try {
				Files.walk(root).forEach(path -> {
					try {
						Files.copy(path, toDirectory);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
}
