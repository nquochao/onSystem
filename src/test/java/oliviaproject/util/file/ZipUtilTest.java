package oliviaproject.util.file;

import java.io.IOException;

import org.junit.Test;

public class ZipUtilTest {
	@Test
	public void testUnzipRecursively(){
		try {
			ZipUtil.extractFolder("data/zip.zip");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
