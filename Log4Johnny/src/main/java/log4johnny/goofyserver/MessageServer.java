package log4johnny.goofyserver;

import org.bukkit.Bukkit;
import org.bukkit.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MessageServer implements Runnable {
    private final int port;

    public MessageServer(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        Server server = Bukkit.getServer();
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Bukkit.getLogger().info("MessageServer listening on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept(); 

                Bukkit.getServer().getScheduler().runTaskAsynchronously(Bukkit.getPluginManager().getPlugin("GoofyServer"), () -> {
                    try {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                        String receivedMessage = reader.readLine();

                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                        String message = "The time is: " + String.valueOf(server.getWorlds().get(0).getTime());
                        writer.write(message);

                        Bukkit.broadcastMessage(Utils.HEADER + receivedMessage);

                        reader.close();
                        writer.close();
                        clientSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
