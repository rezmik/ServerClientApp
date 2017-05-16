import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {	

	private final static String host = "localhost";
	
	private static Socket MyClient;
	private static InetAddress hostAddress;
	private static int port = 9090;
	private static OutputStream os;
	private static OutputStreamWriter osw;
	private static BufferedWriter bw;
	
	private static InputStream is;
	private static InputStreamReader isr;
	private static BufferedReader br;

	public static void main(String[] args) {
		try {
			hostAddress = InetAddress.getByName(host);
			MyClient = new Socket(hostAddress, port);
			
			//Send message to the server
			os = MyClient.getOutputStream();
			osw = new OutputStreamWriter(os);
			bw = new BufferedWriter(osw);
			
			BufferedReader we = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Co chcesz się dowiedzieć o Robercie Lewandowskim?");
			String question = we.readLine();
			question = question + "\n";
			bw.write(question);
			bw.flush();
			System.out.println("Question sent to server: " + question);
			
			//Get the return message from server
			is = MyClient.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			String message = br.readLine();
			System.out.println("Message received from the server: " + message);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				MyClient.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
