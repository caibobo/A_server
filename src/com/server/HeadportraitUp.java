package com.server;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import com.util.PictureUtil;
import net.sf.json.JSONObject;

public class HeadportraitUp {
	public HeadportraitUp(Socket socket,JSONObject json){
		int SERVICE_ID=json.getInt("SERVICE_ID");
		String account=json.getString("account");
		String headportrait=json.getString("headportrait");
		String geshi=json.getString("geshi");
		
		String reference=new PictureUtil().HeadportraitUp(account, headportrait,geshi);
		
		//封装返回json
		JSONObject json_hpUp = new JSONObject();
		json_hpUp.put("SERVICE_ID", SERVICE_ID);
		json_hpUp.put("reference", reference);
		json_hpUp.put("headportraitUp_success", reference.equals("头像上传成功")?true:false);
		
		//发送socket
		try {
			OutputStream os=null;
			os = socket.getOutputStream();
			PrintWriter pw=new PrintWriter(os);//包装成打印流
			pw.write(json_hpUp.toString());
			pw.flush();//将缓冲输出
			
			os.close();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
