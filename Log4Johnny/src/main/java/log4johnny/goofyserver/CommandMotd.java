package log4johnny.goofyserver;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static org.bukkit.Bukkit.getServer;

public class CommandMotd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        Server server = getServer();

        if (args.length >= 1) {
            String argument = "";
            for(int i=0; i<args.length; i++)
                argument += args[i] + " ";
            server.setMotd(argument);
        }
        else {
            commandSender.sendMessage("Usage: /changemotd <value>");
            commandSender.sendMessage(Utils.HEADER + ChatColor.GREEN + "THE MOTD HAS BEEN CHANGED" + ChatColor.RED);
        }
        return true;
    }
}
