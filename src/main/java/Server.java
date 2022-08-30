import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{

    private final int port;

    public Server(int port) {
        this.port = port;
    }

    // Get nth fibonacci number
    private long fib(long n) {
        if (n <= 1) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port);
             Socket socket = serverSocket.accept();
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                long nthFibonacci;
                long result = -1;
                try {
                    nthFibonacci = Long.parseLong(inputLine);
                    result = fib(nthFibonacci);
                } catch (NumberFormatException e) {
                    // Do nothing
                }
                out.println(result);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
