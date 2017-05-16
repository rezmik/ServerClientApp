import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	private static Socket MySocket;
	private static ServerSocket MyServerSocket;
	private static int port = 9090;
	
	private static InputStream is;
	private static InputStreamReader isr;
	private static BufferedReader br;
	
	private static OutputStream os;
	private static OutputStreamWriter osw;
	private static BufferedWriter bw;
	
	private static String returnMessage;

	public static void setMessage(String question) {
		if (question.equals("Ile ma lat?")) {
			returnMessage = "Robert Lewandowski ma 28 lat\n";
		} else if (question.equals("Ile bramek strzelił w ekstraklasie?")) {
			returnMessage = "Robert Lewandowski strzelił 41 bramek w Ekstraklasie\n";
		} else if (question.equals("Ile bramek strzelił w reprezentacji Polski?")) {
			returnMessage = "Robert Lewandowski strzelił 43 bramki dla reprezentacji Polski\n";
		} else if (question.equals("Jak ma na imię jego córka?")) {
			returnMessage = "Córka Roberta Lewandowskiego ma na imię Klara\n";
		} else if (question.equals("Najsłynniejszy mecz?")) {
			returnMessage = "Najsłynniejszym meczem Roberta Lewandowskiego jest mecz przeciwo Wolfsburgowi, w którym strzlił 5 bramek w 9 minut\n";
		} else {
			returnMessage = "Serwer nie zna odpowiedzi na to pytanie!\n";
		}
	}

	public static void main(String[] args) {
		try {
			MyServerSocket = new ServerSocket(port);
			System.out.println("Server started and listening to the port 9090");
			
			while(true) {
				MySocket = MyServerSocket.accept();
				is = MySocket.getInputStream();
				isr = new InputStreamReader(is);
				br = new BufferedReader(isr);
				
				String questionFromClient = br.readLine();
				System.out.println("Message received from client is: " + questionFromClient);

                setMessage(questionFromClient);
				
				//Sending the response back to the client
				os = MySocket.getOutputStream();
				osw = new OutputStreamWriter(os);
				bw = new BufferedWriter(osw);
				bw.write(returnMessage);
				System.out.println("Message sent to the client is: " + returnMessage);
				bw.flush();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				MySocket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}