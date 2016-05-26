import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import net.sf.json.JSONObject;

public class client_test {

	public static void main(String[] args) {
		try {
			//向服务器传输数据
			Socket socket=new Socket("localhost",8884);
			OutputStream os=socket.getOutputStream();
			PrintWriter pw=new PrintWriter(os);
			JSONObject login = new JSONObject();
			login.put("SERVICE_ID", 0x80000001);
			login.put("account", "2978831555");
			login.put("password", "123");
			login.put("password_question", "handsome?");
			login.put("password_answer", "yep");
			login.put("nickname", "gosh");
			login.put("gender", "m");
			/*login.put("SERVICE_ID", 0x80000002);
			login.put("account", 297883155);
			login.put("password", 123);*/
			pw.write(login.toString());
			pw.flush();
			socket.shutdownOutput();
			
			//接受服务器传回的数据
			InputStream in=socket.getInputStream();
			InputStreamReader inr=new InputStreamReader(in);
			BufferedReader br=new BufferedReader(inr);
			String info=null;
			while((info=br.readLine())!=null){
				System.out.println("服务器端返回:"+info);
			}
			
			//关闭资源
			br.close();
			in.close();
			pw.close();
			os.close();
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
