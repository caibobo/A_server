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
			int count=0;//���ڼ�¼��ǰ��������
			System.out.println("������������......");
			
			while(true){
				socket=serverSocket.accept();//�ȴ��û�����
				Server server=new Server(socket);//�����߳�
				server.start();//�����߳�
				System.out.println("��������ǰ����������"+(++count));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
