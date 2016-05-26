import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Base64;

import net.sf.json.JSONObject;

public class picture_test {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		File file = new File("C://1.jpg");  
		FileInputStream fis = null;  
        ByteArrayOutputStream baos = null;  
        byte[] data = null ;  
  
        try {  
            fis = new FileInputStream(file);  
            baos = new ByteArrayOutputStream((int) file.length());  
            byte[] buffer = new byte[1024];  
            int len = -1;  
            while ((len = fis.read(buffer)) != -1) {  
                baos.write(buffer, 0, len);  
            }  
             data = baos.toByteArray() ;  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                if (fis != null) {  
                    fis.close();  
                }  
                baos.close() ;  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        
        String picture = new String(Base64.getEncoder().encodeToString(data));
        
        try {
			//向服务器传输数据
			Socket socket=new Socket("localhost",8884);
			OutputStream os=socket.getOutputStream();
			PrintWriter pw=new PrintWriter(os);
			JSONObject login = new JSONObject();
			login.put("SERVICE_ID", 0x80000003);
			login.put("account", "297883155");
			login.put("headportrait",picture );
			login.put("geshi",".png" );
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
