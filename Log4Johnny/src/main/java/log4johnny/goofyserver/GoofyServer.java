package log4johnny.goofyserver;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class GoofyServer extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getCommand("goof").setExecutor(new CommandGoof());
        getCommand("changemotd").setExecutor(new CommandMotd());
        getLogger().info("GoofyServer has been enabled!");

        MessageServer messageServer = new MessageServer(36000);
        getServer().getScheduler().runTaskAsynchronously(this, messageServer);
    }

    @Override
    public void onDisable() {
        getLogger().info("GoofyServer has been disabled!");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Server server = getServer();

        String johnnyMsg = Utils.HEADER + ChatColor.GREEN + "THE KING HAS ARRIVED" + ChatColor.RESET;
        String alexMsg = Utils.HEADER + ChatColor.GREEN + "IN THIS ECONOMY" + ChatColor.RESET;
        String emmaMsg = Utils.HEADER + ChatColor.GREEN + "A beautiful lady has entered the server " +ChatColor.RED+ "<3" + ChatColor.RESET;
        String beckyMsg = Utils.HEADER + ChatColor.GREEN + "Becky get back to work!" + ChatColor.RESET;

        String defaultMsg = Utils.HEADER + ChatColor.GREEN + "Welcome to the Minecraft server!" + ChatColor.RESET;

        Player p = event.getPlayer();
        String name = p.getName();

        switch (name) {
            case(Utils.JOHNNYNAME):
                server.broadcastMessage(johnnyMsg);
                break;
            case(Utils.EMMANAME):
                server.broadcastMessage(emmaMsg);
                break;
            case(Utils.ALEXNAME):
                server.broadcastMessage(alexMsg);
                break;
            case(Utils.BECKYNAME):
                server.broadcastMessage(beckyMsg);
                break;
            default:
                server.broadcastMessage(defaultMsg);
                break;
        }
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        for (String substring : Utils.johnnyNickNames) {
            if (message.toLowerCase().contains(substring.toLowerCase())) {
                goofyMessage("WHY ARE YOU TALKING ABOUT ME "
                        + player.getName().toUpperCase(), ChatColor.RED);
                break;
            }
        }

        if (message.toLowerCase().contains("god")) {
            ItemStack itemStack = new ItemStack(Material.GOLDEN_HOE);
            itemStack.setAmount(1);
            player.getInventory().addItem(itemStack);
            goofyMessage(player.getName() + " has just received a gold hoe..."
                    + player.getName().toUpperCase(), ChatColor.RED);
            new BukkitRunnable() {
                int count = 0;
                @Override
                public void run() {
                    if (count < 5) {
                        player.getWorld().strikeLightningEffect(player.getLocation());
                        count++;
                    } else {
                        this.cancel();
                    }
                }
            }.runTaskTimer(this, 0L, 20L);
        }
    }

    public void goofyMessage(String message) {
        Server server = getServer();
         server.broadcastMessage(Utils.HEADER + ChatColor.GREEN + message + ChatColor.RESET);
    }

    public void goofyMessage(String message, ChatColor messageColor) {
        Server server = getServer();
        server.broadcastMessage(Utils.HEADER + messageColor + message + ChatColor.RESET);
    }

    @EventHandler
    public void onBedEnter(PlayerBedEnterEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();
        if(world.getTime() > 12500 ) {
            goofyMessage( player.getName() + " went to bed");
            world.setTime(0);
        }

    }
}
