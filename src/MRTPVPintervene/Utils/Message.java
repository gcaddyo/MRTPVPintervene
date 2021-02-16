package MRTPVPintervene.Utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Message {
    public static void bc(String message)
    {
        Bukkit.broadcastMessage(message);
    }
    public static void send(Player pl,String message)
    {
        pl.sendMessage(message);
    }
    public static void send(String PName,String message)
    {
        Player pl = Bukkit.getPlayer(PName);
        pl.sendMessage(message);
    }
    public static void sendToConsole(String message)
    {
        Bukkit.getConsoleSender().sendMessage(message);
    }
}
