package log4johnny.goofyserver;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getServer;

public class CommandGoof implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        Server server = getServer();
        String alt_header = ChatColor.BLUE + "[" +
                ChatColor.RED + "GoofyMessage" + ChatColor.BLUE + "]"
                + ChatColor.RESET;

        if(commandSender.isOp()) {
            if (args.length >= 1) {
                String argument = "";
                for(int i=0; i<args.length; i++) {
                    argument += args[i] + " ";
                }
                server.broadcastMessage(Utils.HEADER + ChatColor.GREEN + argument + ChatColor.RESET);
            }
            else {
                commandSender.sendMessage("Usage: /goof <message>");
            }
            return true;
        }
        else {

            if (args.length >= 1) {
                String argument = "";
                for(int i=0; i<args.length; i++) {
                    argument += args[i] + " ";
                }
                server.broadcastMessage(alt_header + ChatColor.GREEN + argument +
                        argument + ChatColor.RESET);
            }
            else {
                commandSender.sendMessage("Usage: /goof <message>");
            }
            return true;
        }

    }
}
