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
			//建立serversocket监听客户端传送的消息
			InputStream in=socket.getInputStream();
			InputStreamReader inr=new InputStreamReader(in);
			BufferedReader br=new BufferedReader(inr);//添加缓存
			String info=null;
			info=br.readLine();//读取客户端消息
			System.out.println("客户端消息:"+info);
			JSONObject result = JSONObject.fromObject(info);
			int SERVICE_ID=result.getInt("SERVICE_ID");//判断请求类型
			socket.shutdownInput();//关闭输入流

			switch(SERVICE_ID){//根据不同的请求进行不同业务处理
				case 0x80000001:new UsersRegist(socket,result);
				case 0x80000002:new UsersLogin(socket,result);
				case 0x80000003:new HeadportraitUp(socket,result);
			}
			
			//关闭相关参数
			br.close();
			inr.close();
			in.close();
			socket.close();
		} catch (IOException e) {
			//e.printStackTrace();
		}
	}
}
