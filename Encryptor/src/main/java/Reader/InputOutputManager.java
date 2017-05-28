package Reader;

import java.nio.file.Path;

public interface InputOutputManager {

	public byte[] readFile(Path filePath);
	
	public void saveFile(Path filePath, byte[] data);
	
	public byte[] read(int numberOfBytesToRead);
	
	public byte readByte();
	
	public String readString(int numberOfBytesToRead);
	
	public void write (String s);
	
	public void write (byte[] data);
	
	public void write (byte data);
}
