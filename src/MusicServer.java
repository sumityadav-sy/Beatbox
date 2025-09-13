import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
public class MusicServer {
 final List<ObjectOutputStream> clientOutputStreams = new ArrayList<>();
 public static void main(String[] args) {
   new MusicServer().go(args);
 }
 public void go(String[] args) {
   try {

     int port = 4242;
if (args.length >= 1) port = Integer.parseInt(args[0]);
ServerSocket serverSock = new ServerSocket(port);
System.out.println("Server started on port " + port);

     ExecutorService threadPool = Executors.newCachedThreadPool();

          while (!serverSock.isClosed()) {
            Socket clientSocket = serverSock.accept();
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            clientOutputStreams.add(out);
            ClientHandler clientHandler = new ClientHandler(clientSocket);
        threadPool.execute(clientHandler);
        System.out.println("Got a connection");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  public void tellEveryone(Object one, Object two) {
    for (ObjectOutputStream clientOutputStream : clientOutputStreams) {
      try {
        clientOutputStream.writeObject(one);
        clientOutputStream.writeObject(two);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
    public class ClientHandler implements Runnable {
        private ObjectInputStream in;
        public ClientHandler(Socket socket) {
          try {
            in = new ObjectInputStream(socket.getInputStream());
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
        public void run() {
            Object userName;
            Object beatSequence;
            try {
              while ((userName = in.readObject()) != null) {
                beatSequence = in.readObject();
                System.out.println("read two objects");
                tellEveryone(userName, beatSequence);
            }
        } catch (IOException | ClassNotFoundException e) {
          e.printStackTrace();
        }
      }
    }
   }