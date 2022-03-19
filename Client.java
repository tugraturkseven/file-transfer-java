package fileTransfer;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
public static void main(String[] args) throws UnknownHostException, IOException {
	Socket socket = new Socket("Tuðra", 9876);
	
	DataInputStream dataIn = new DataInputStream(socket.getInputStream());

	String file = dataIn.readUTF();
	
	String filePath = System.getProperty("user.home")+"\\Desktop\\" + file;
	File newFile = new File(filePath);
	
	BufferedInputStream in = new BufferedInputStream(socket.getInputStream());
	
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	
	int nRead;
	byte[] buffer = new byte[4096];
	while((nRead = in.read(buffer, 0, buffer.length))!= -1) {
		baos.write(buffer,0,nRead);
	}
	
	System.out.println(nRead+" Bytes readed.");
	
	
	FileOutputStream fileOut = new FileOutputStream(newFile);
	fileOut.write(baos.toByteArray());
	System.out.println("yazýldý...");
	fileOut.flush();
	fileOut.close();
	socket.close();	
}
}
