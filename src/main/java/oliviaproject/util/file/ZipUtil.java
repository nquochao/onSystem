package oliviaproject.util.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipUtil {
	public static void main(String[] args) throws IOException {
	}

	public List getInputStreamsFromZip() throws IOException {
		ZipFile zipFile = new ZipFile("C:/test.zip");
		List l= new ArrayList<>();
		Enumeration<? extends ZipEntry> entries = zipFile.entries();

		while (entries.hasMoreElements()) {
			ZipEntry entry = entries.nextElement();
			InputStream stream = zipFile.getInputStream(entry);
			l.add(stream);
		}
		return l;
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
	
	/**
	 * https://stackoverflow.com/questions/981578/how-to-unzip-files-recursively-in-java
	 * 
	 * @param inputZip
	 * @param destinationDirectory
	 * @throws IOException
	 */
	public static void extractFolder(String zipFile) throws IOException {
		int buffer = 2048;
		File file = new File(zipFile);

		try (ZipFile zip = new ZipFile(file)) {
		  String newPath = zipFile.substring(0, zipFile.length() - 4);

		  new File(newPath).mkdir();
		  Enumeration<? extends ZipEntry> zipFileEntries = zip.entries();

		  // Process each entry
		  while (zipFileEntries.hasMoreElements()) {
		    // grab a zip file entry
		    ZipEntry entry = zipFileEntries.nextElement();
		    String currentEntry = entry.getName();
		    File destFile = new File(newPath, currentEntry);
		    File destinationParent = destFile.getParentFile();

		    // create the parent directory structure if needed
		    destinationParent.mkdirs();

		    if (!entry.isDirectory()) {
		      BufferedInputStream is = new BufferedInputStream(zip.getInputStream(entry));
		      int currentByte;
		      // establish buffer for writing file
		      byte[] data = new byte[buffer];

		      // write the current file to disk
		      FileOutputStream fos = new FileOutputStream(destFile);
		      try (BufferedOutputStream dest = new BufferedOutputStream(fos, buffer)) {

		        // read and write until last byte is encountered
		        while ((currentByte = is.read(data, 0, buffer)) != -1) {
		          dest.write(data, 0, currentByte);
		        }
		        dest.flush();
		        is.close();
		      }
		    }

		    if (currentEntry.endsWith(".zip")) {
		      // found a zip file, try to open
		      extractFolder(destFile.getAbsolutePath());
		    }
		  }
		}
	}}
