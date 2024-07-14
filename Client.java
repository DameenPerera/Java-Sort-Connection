import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        String hostname = "localhost"; // Use the server's IP address here if not running on the same machine
        int port = 12345;

        try (Socket socket = new Socket(hostname, port)) {
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));

            String message;
            while (true) {
                System.out.print("Enter message: ");
                message = consoleInput.readLine();
                if (message.equalsIgnoreCase("exit")) {
                    break;
                }
                output.println(message);
                System.out.println("Server reply: " + input.readLine());
            }
        } catch (UnknownHostException e) {
            System.err.println("Unknown host: " + hostname);
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("I/O error with the connection to: " + hostname);
            e.printStackTrace();
        }
    }
}
