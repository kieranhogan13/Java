import java.io.*;
import java.net.*;


public class MulticastClient {

	private MulticastSocket socket;
	private InetAddress address;
	private DatagramPacket packet;
	int port;

	public MulticastClient(int port) {
		this.port = port;
		try {
			socket = new MulticastSocket(port);
			address = InetAddress.getByName("230.0.0.1");
			socket.joinGroup(address);
		} catch (UnknownHostException uhe) {
			uhe.printStackTrace();
			System.exit(1);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.exit(2);
		}

		while(true) {
			System.out.println(getTime());
		}
	}

	private String getTime() {

		byte[] buffer = new byte[32];
		packet = new DatagramPacket(buffer, buffer.length);

		try {
			socket.receive(packet);
		} catch (IOException ioe) {
			return ioe.getMessage();
		}
		return new String(packet.getData());
	}

	public static void main(String[] args) {
		new MulticastClient(Integer.parseInt(args[0]));
	}
}
