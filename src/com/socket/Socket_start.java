package com.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import com.server.Server;

public class Socket_start {
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket=new ServerSocket(8884);
			Socket socket=null;
			int count=0;//用于记录当前连接数量
			System.out.println("服务器已启动......");
			
			while(true){
				socket=serverSocket.accept();//等待用户连接
				Server server=new Server(socket);//创建线程
				server.start();//启动线程
				System.out.println("服务器当前连接数量："+(++count));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
