package com.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.entity.Users;

public class PictureUtil {//将图片文件的二进制流转换成字符串
	public String HeadportraitUp(String account,String picture_name,String geshi){
		String path="head_portrait/"+account+""+geshi+"";
		File file = new File(path); 
		String url=null;
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		//判断用户头像是否存在
		Users user=(Users)session.get(Users.class,account);
		url=user.getHead_portrait();
		System.out.println("url"+url);
		
		//删除之前头像;
		if(url!=null){
			File old_file=new File(url);
			old_file.delete();
		}
		
		byte[] in = Base64.getDecoder().decode(picture_name);
		int BUFFER_SIZE=20480;
		try {
			//上传头像
			OutputStream out =new BufferedOutputStream(new FileOutputStream(file),BUFFER_SIZE);  
            out.write(in); 
            out.close();
            
            //向数据库中插入头像的url
            user.setHead_portrait(path);
            session.save(user);
            tx.commit();
    		HibernateUtil.closeSession(session);
            return "头像上传成功";
		} catch (Exception e) {
			e.printStackTrace();
			return "头像上传失败";
		}
	}
}
