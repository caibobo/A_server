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
		
		//���е�¼�жϣ�����ȡ���
		JSONObject json_login=new HqlUsers().UserLogin(account, password);
		json_login.put("SERVICE_ID",SERVICE_ID);
		
		//����socket
		try {
			OutputStream os=null;
			os = socket.getOutputStream();
			PrintWriter pw=new PrintWriter(os);//��װ�ɴ�ӡ��
			pw.write(json_login.toString());
			pw.flush();//���������
			
			os.close();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
