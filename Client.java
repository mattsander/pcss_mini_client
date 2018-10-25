import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
	


public class Client {
	//Username in a String
	//Scanner to receive input
	static String userName;
	static String hostName = "localhost";
	static int port = 8000;
	public static ObjectInputStream objectInput;
	public static ObjectOutputStream objectOutput;
	public static Socket socket;
	static Scanner scan = new Scanner(System.in);
	static ArrayList<String> Input = null;
	public final static Lock lock = new ReentrantLock();
	public static LobbyUI lobby = null;
	
	enum State {
		chatroom,
		login,
		lobby
	}
	
	public static State state = null;
	
	public static ArrayList<String> ReadServer() {

		synchronized (lock) {
			try {
				ArrayList<String> _input = (ArrayList<String>) Client.objectInput.readObject();
				System.out.println("Command: " + _input);
				return Input = _input;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.print("Nothing returned");
			return null;
		}
		
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		LoginUI login = new LoginUI();
		
	        while (true){
	        	ArrayList<String> Input = null;
                
                
                
                if(Client.state == Client.State.lobby)
                {
                	LobbyUI.PeopleOnline();
                	LobbyUI.chatOnline();

	        		
                }
	        		else if (Client.state == Client.State.chatroom) {
		        		//Get the input from the client
	        			System.out.println("Client reading from server");
	                    Input = Client.ReadServer();

		                //If null skip checking for stuff
		                if (Input == null)
		                    continue;
		                
		                if (Input.get(0).equals("NEW MESSAGE")) {
		             	
			 					try {
									ChatUI.getMessages(Input);
								} catch (ClassNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} //runs the method for printing the new message
			 					
			 					//Input = null; //empty input after message is recieved
			                
		                	} 
		                
		                	//getOnlineUsers(); //add to UI of chatroom
		                	
		                	
		        		}
	        		
	        		
	        	try {
	        		
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	
	        	
	        	
	        }

		
	}//end of main
	
	public static void InterpretResponse(ArrayList<String> _Input) {
		
		
				
	}
	
}
