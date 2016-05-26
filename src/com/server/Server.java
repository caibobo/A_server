package com.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import net.sf.json.JSONObject;


public class Server extends Thread {
	Socket socket=null;
	
	public Server(Socket socket){
		this.socket=socket;
	}
	
	public void run(){
		try {
			//����serversocket�����ͻ��˴��͵���Ϣ
			InputStream in=socket.getInputStream();
			InputStreamReader inr=new InputStreamReader(in);
			BufferedReader br=new BufferedReader(inr);//��ӻ���
			String info=null;
			info=br.readLine();//��ȡ�ͻ�����Ϣ
			System.out.println("�ͻ�����Ϣ:"+info);
			JSONObject result = JSONObject.fromObject(info);
			int SERVICE_ID=result.getInt("SERVICE_ID");//�ж���������
			socket.shutdownInput();//�ر�������

			switch(SERVICE_ID){//���ݲ�ͬ��������в�ͬҵ����
				case 0x80000001:new UsersRegist(socket,result);
				case 0x80000002:new UsersLogin(socket,result);
				case 0x80000003:new HeadportraitUp(socket,result);
			}
			
			//�ر���ز���
			br.close();
			inr.close();
			in.close();
			socket.close();
		} catch (IOException e) {
			//e.printStackTrace();
		}
	}
}
