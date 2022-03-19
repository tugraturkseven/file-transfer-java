package fileTransfer;


import java.awt.FileDialog;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;

import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;


public class Server {
	private static ServerSocket server;
	private static int port = 9876;
	static JFrame yourJFrame = new JFrame();
	
	public static void main(String[] args) throws IOException {
		server = new ServerSocket(port);
		
		
		FileDialog fd = new FileDialog(yourJFrame, "Choose a file", FileDialog.LOAD);
		
		fd.setDirectory("C:\\");
		
		fd.setVisible(true);
		
		String fileName = fd.getDirectory()+fd.getFile();
		System.out.println(fd.getDirectory()==null);
		
		FileInputStream newFile = new FileInputStream(fileName);

		BufferedInputStream in = new BufferedInputStream(newFile);
		
		Socket socket = server.accept();
		System.out.println("baðlandý");
		
		DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());
		dataOut.writeUTF(fd.getFile());
		dataOut.flush();
		
		
		BufferedOutputStream out = new BufferedOutputStream(socket.getOutputStream());

		
		int nRead;
		byte[] buffer = new byte[4096];
		while((nRead = in.read(buffer, 0, buffer.length))!= -1) {
			out.write(buffer,0,nRead);
		}
		System.out.println(nRead);
		
		out.flush();
		out.close();
		in.close();
		System.out.println("Yazýldý.");
		
	}
}
	

