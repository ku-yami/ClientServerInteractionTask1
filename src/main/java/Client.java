import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static final int PORT = 12345;
    public static void main(String[] args) throws IOException {

        // Start fibonacciServer thread
        Server fibonacciServer = new Server(PORT);
        Thread serverThread = new Thread(fibonacciServer);
        serverThread.start();

        try (Socket socket = new Socket("localhost", PORT);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter writer =    new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
             Scanner scanner = new Scanner(System.in)) {
            String inputString;
            while (true) {
                System.out.print("Введите номер ряда Фибоначи или exit для выхода: ");
                inputString = scanner.nextLine();
                if ("exit".equals(inputString)) {
                    break;
                }
                writer.println(inputString);
                System.out.println(reader.readLine());
            }
        }
    }
}
