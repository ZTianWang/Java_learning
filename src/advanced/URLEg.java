package advanced;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class URLEg {

	public static void main(String[] args) {

		String urlString = "http://tieba.baidu.com/f";
		HttpURLConnection connection = null;

		try {

			URL reqUrl = new URL(urlString);
			connection = (HttpURLConnection) reqUrl.openConnection();
//			connection.setRequestMethod("GET");
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);

			//发送POST请求的参数
			DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
			String param = String.format("kw=%s", "%B0%CD%C8%FB%C2%DE%C4%C7");
			dos.writeBytes(param);
			if (dos != null) {
				dos.close();
			}

			try (InputStream is = connection.getInputStream();
					InputStreamReader ir = new InputStreamReader(is, "UTF-8");
					BufferedReader br = new BufferedReader(ir);
//					BufferedInputStream bi = new BufferedInputStream(is);

					FileOutputStream fo = new FileOutputStream("./files/index1.html");
					OutputStreamWriter ow = new OutputStreamWriter(fo, "UTF-8");
					BufferedWriter bw = new BufferedWriter(ow);
//					BufferedOutputStream bo = new BufferedOutputStream(fo);
			) {
//				StringBuffer content = new StringBuffer();
				String contentLine = br.readLine();

//				byte[] b = new byte[128];
//				int len = bi.read(b);
				while (contentLine != null) {
//					content.append(contentLine+'\n');
					System.out.println(contentLine);
					bw.write(contentLine);
					bw.newLine();
					contentLine = br.readLine();
//					bo.write(b, 0, len);
//					len = bi.read(b);
				}
			} catch (IOException e) {
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}

	}

}
