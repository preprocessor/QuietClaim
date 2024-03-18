package wyspr.quietclaim.utils;

import net.minecraft.core.net.command.CommandSender;

public class GetPlayer {
	public static class PlayerInfo {
		public ChunkPos pos;
		public String name;

		public PlayerInfo(ChunkPos pos, String name) {
			this.pos = pos;
			this.name = name;
		}
	}
	public static PlayerInfo info(CommandSender sender) {
		int x = sender.getPlayer().chunkCoordX;
		int z = sender.getPlayer().chunkCoordZ;

		ChunkPos pos = new ChunkPos(x,z);
		String name = sender.getPlayer().username;

		return new PlayerInfo(pos, name);
	}
}
