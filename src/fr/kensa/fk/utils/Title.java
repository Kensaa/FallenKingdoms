package fr.kensa.fk.utils;


import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.PacketPlayOutTitle;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Title {

	public static void sendTitle(Player player, String title, String subtitle, int ticks) {
		
		IChatBaseComponent basetitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + title + "\"}");
		IChatBaseComponent basesubtitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + subtitle + "\"}");
		PacketPlayOutTitle titlepacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, basetitle);
		PacketPlayOutTitle titlesubpacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, basesubtitle);
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(titlepacket);
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(titlesubpacket);
		sendTime(player, ticks);
	}
	
	
	

	
	private static void sendTime(Player player, int ticks) {
		PacketPlayOutTitle titlepacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, null, 20, ticks, 20);
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(titlepacket);
	}




}
