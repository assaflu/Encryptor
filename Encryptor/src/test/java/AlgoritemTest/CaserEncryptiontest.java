package AlgoritemTest;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import org.junit.Test;

public class CaserEncryptiontest {

	@SuppressWarnings("resource")
	@Test
	public void test() throws IOException{
		String path = CaserEncryptiontest.class.getClassLoader().getResource("a.txt").getPath();
		String pathEnc = path.concat(".encrypted");
		System.out.println(pathEnc);
		System.setIn(new ByteArrayInputStream("1".getBytes())); 
		//EncryptionAlgoritems.instance.chooseAlgoritem();
		//EncryptionAlgoritems.instance.executeMethod(100, path);
		//byte[] b = Files.readAllBytes(Paths.get(pathEnc));
		FileInputStream  fileinputstream =new FileInputStream(pathEnc);
		System.out.println(fileinputstream.read());
		/*assert data [0] == 197;
		assert data [1] == 198;
		assert data [2] == 199;
		assert data [3] == 200;*/
		
		
		 
	}

}
