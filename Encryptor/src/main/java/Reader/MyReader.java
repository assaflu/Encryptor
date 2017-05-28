package Reader;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class MyReader implements InputOutputManager{

	private InputStream is;
	private OutputStream os;
	
	public MyReader (InputStream is, OutputStream os){
		this.is = is;
		this.os = os;
	}
	
	public void SetInputStream(InputStream is){
		this.is = is;
	}
	
	public void SetOutputStream (OutputStream os){
		this.os = os;
	}

	@Override
	public void saveFile(Path filePath, byte[] data){
		try {
			FileOutputStream out = new FileOutputStream(filePath.toString());
			out.write(data);
			out.close();
		} catch (IOException e) {
			System.err.println("ERROR - either your Input Stream or "
					+ "got IO exception or FileNotFoundException"
					+ ". the program will now treminate");
			e.printStackTrace();
		}
	}

	@Override
	public byte[] readFile(Path filePath) {
		try {
			return Files.readAllBytes(filePath);
		} catch (IOException e) {
			System.err.println("ERROR - got IO exception"
					+ ". the program will now treminate");
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * assume that the user end each inputLine with \n(non-Javadoc)
	 * @see Reader.InputOutputManager#read(int)
	 */
	@Override
	public byte[] read(int numberOfBytesToRead){
		byte returnArray[] = new byte [numberOfBytesToRead+2];
		try {
			is.read(returnArray);
		} catch (IOException e) {
			System.err.println("ERROR - got IO exception"
					+ "in the OutputStream. the program will now treminate");
			e.printStackTrace();
		}
		return returnArray;
	}

	@Override
	public byte readByte() {
		byte returnArray[] = new byte [1];
		try {
			is.read(returnArray);
		} catch (IOException e) {
			System.err.println("ERROR - got IO exception"
					+ "in the OutputStream. the program will now treminate");
			e.printStackTrace();
		}
		return returnArray[0];
	}

	/*
	 * assume that the user end the input with \n(non-Javadoc)
	 * @see Reader.InputOutputManager#readString(int)
	 */
	@Override
	public String readString(int numberOfBytesToRead) {
		byte tempArray[] = new byte [numberOfBytesToRead+2];
		byte returnArray[] = new byte [numberOfBytesToRead];
		try {
			is.read(tempArray);
		} catch (IOException e) {
			System.err.println("ERROR - got IO exception"
					+ "in the OutputStream. the program will now treminate");
			e.printStackTrace();
		}
		for(int i=0; i<numberOfBytesToRead; i++){
			returnArray[i] = tempArray[i];
		}
		return new String(returnArray);
	}
	
	@Override
	public void write(String s){
		try {
			os.write(s.getBytes());
		} catch (IOException e) {
			System.err.println("ERROR - got IO exception"
					+ "in the OutputStream. the program will now treminate");
			e.printStackTrace();
		}
	}

	
	@Override
	public void write(byte[] data) {
		try {
			os.write(data);
		} catch (IOException e) {
			System.err.println("ERROR - got IO exception"
					+ "in the OutputStream. the program will now treminate");
			e.printStackTrace();
		}
		
	}

	
	@Override
	public void write(byte data) {
		try {
			os.write(data);
		} catch (IOException e) {
			System.err.println("ERROR - got IO exception"
					+ "in the OutputStream. the program will now treminate");
			e.printStackTrace();
		}		
	}


}
