package com.server;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import com.connection.HqlUsers;

import net.sf.json.JSONObject;

public class UsersRegist {//用户注册
	public UsersRegist(Socket socket,JSONObject json){
		int SERVICE_ID=json.getInt("SERVICE_ID");
		String account=json.getString("account");
		String password=json.getString("password");
		String password_question=json.getString("password_question");
		String password_answer=json.getString("password_answer");
		String nickname=json.getString("nickname");
		String gender=json.getString("gender");
		
		//进行注册，并获取结果
		JSONObject json_regist=new HqlUsers().UsersRegist(nickname, account, password, gender, password_question, password_answer);
		json_regist.put("SERVICE_ID",SERVICE_ID);
		
		//发送socket
		try {
			OutputStream os=null;
			os = socket.getOutputStream();
			PrintWriter pw=new PrintWriter(os);//包装成打印流
			pw.write(json_regist.toString());
			pw.flush();//将缓冲输出
			
			os.close();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
