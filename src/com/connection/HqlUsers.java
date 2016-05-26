package com.connection;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.entity.Users;
import com.util.HibernateUtil;

import net.sf.json.JSONObject;

public class HqlUsers {
	
	//用户注册	
	public JSONObject UsersRegist(String nickname, String account, String password, String gender, String password_question,
			String password_answer){
		Users user=new Users(nickname,account,password,gender,password_question,password_answer);
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		Boolean register_success=false;//判断是否注册成功
		String reference=null;//备注信息
		
		//查询数据库
		List list=session.createSQLQuery("select * from Users where account='"+account+"'").addEntity(Users.class).list();

		if(list.isEmpty()==false){
			register_success=false;
			reference="用户已被注册";
		}else{
			session.save(user);
			register_success=true;
			reference="注册成功";
		}
		
		tx.commit();
		try {
			HibernateUtil.closeSession(session);
		} catch (IOException e) {
			e.printStackTrace();
			register_success=false;
			reference="服务器出错";
		}
		
		//封装返回json
		JSONObject json_regist = new JSONObject();
		json_regist.put("register_success", register_success);
		json_regist.put("reference", reference);
		
		return json_regist;
	}
	
	
	//用户登录
	public JSONObject UserLogin(String account,String password){
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		String pass=null;
		Boolean login_success=false;
		String nickname=null;
		String gender=null;
		String reference=null;
		
		List list=session.createSQLQuery("select * from Users where account='"+account+"'").addEntity(Users.class).list();
		Iterator it = list.iterator();
		if (it.hasNext()){//获取查找结果
	         Users s = (Users)it.next();
	         pass = s.getPassword();
	         nickname=s.getNickname();
	         gender=s.getGender();
		}
		if(list.isEmpty()==true){//用户登录账号和密码判断
			login_success=false;
			reference="用户不存在";
		}else if(!pass.equals(password)){
			login_success=false;
			reference="密码错误";
		}else{
			login_success=true;
			reference="登录成功";
		}
		tx.commit();
		try {
			HibernateUtil.closeSession(session);
		} catch (IOException e) {
			e.printStackTrace();
			login_success=false;
			reference="服务器出错";
		}
		
		//封装返回json
		JSONObject json_login = new JSONObject();
		json_login.put("login_success", login_success);
		json_login.put("reference", reference);
		json_login.put("nickname", nickname);
		json_login.put("gender", gender);
		
		return json_login;
	}
}
