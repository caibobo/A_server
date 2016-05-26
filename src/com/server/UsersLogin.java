package com.server;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import com.connection.HqlUsers;

import net.sf.json.JSONObject;

public class UsersLogin {
	public UsersLogin(Socket socket,JSONObject json){
		int SERVICE_ID=json.getInt("SERVICE_ID");
		String account=json.getString("account");
		String password=json.getString("password");
		
		//进行登录判断，并获取结果
		JSONObject json_login=new HqlUsers().UserLogin(account, password);
		json_login.put("SERVICE_ID",SERVICE_ID);
		
		//发送socket
		try {
			OutputStream os=null;
			os = socket.getOutputStream();
			PrintWriter pw=new PrintWriter(os);//包装成打印流
			pw.write(json_login.toString());
			pw.flush();//将缓冲输出
			
			os.close();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
