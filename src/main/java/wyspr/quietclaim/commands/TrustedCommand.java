package wyspr.quietclaim.commands;

import net.minecraft.core.entity.player.EntityPlayer;
import wyspr.quietclaim.QuietClaim;
import wyspr.quietclaim.utils.ChunkPos;
import net.minecraft.core.net.command.Command;
import net.minecraft.core.net.command.CommandHandler;
import net.minecraft.core.net.command.CommandSender;

import java.util.ArrayList;

public class TrustedCommand extends Command {
	public TrustedCommand() {
		super("trusted","claimtrusted","claimwho");
	}
//
	public boolean execute(CommandHandler handler, CommandSender sender, String[] args) {
		EntityPlayer player = sender.getPlayer();
		ChunkPos pos = new ChunkPos(player.chunkCoordX, player.chunkCoordZ);

		if (QuietClaim.isClaimed(pos)) {
			ArrayList<String> trusted = QuietClaim.getTrusted(pos);
			String owner = trusted.remove(0);

			String trustedTxt = trusted.toString()
				.replace("[", "")
				.replace(" , ", ", ")
				.replaceAll(", ", ",")
				.replace("]", "");

			sender.sendMessage("§3Chunk owner: §r" + owner);
			sender.sendMessage("§3Trusted players: §r" + trustedTxt);
		} else {
			sender.sendMessage("§eNo one owns this chunk!");
		}

		return true;

	}
//
	public boolean opRequired(String[] args) {
		return false;
	}
//
	public void sendCommandSyntax(CommandHandler handler, CommandSender sender) {

	}
}
