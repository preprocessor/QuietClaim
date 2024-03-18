package wyspr.quietclaim.commands;

import wyspr.quietclaim.QuietClaim;
import wyspr.quietclaim.utils.GetPlayer;
import wyspr.quietclaim.utils.GetPlayer.*;
import wyspr.quietclaim.utils.ChunkPos;
import net.minecraft.core.net.command.Command;
import net.minecraft.core.net.command.CommandHandler;
import net.minecraft.core.net.command.CommandSender;

public class ClaimCommand extends Command {
	public ClaimCommand() {
		super("claim");
	}
//
	public boolean execute(CommandHandler handler, CommandSender sender, String[] args) {
		if (sender.getPlayer().score < QuietClaim.cost) {
			sender.sendMessage("§eInsufficient funds!");
			return true;
		}

		PlayerInfo info = GetPlayer.info(sender);
		ChunkPos pos = info.pos;
		String username = info.name;

		if (QuietClaim.isClaimed(pos)) {
			String owner = QuietClaim.getOwner(pos);
			sender.sendMessage("§eThis chunk is already claimed by §r" + owner +"§e!");
		} else {
			QuietClaim.claimChunk(pos, username);
			QuietClaim.save();
			sender.sendMessage("§4Chunk claimed!");
			sender.getPlayer().score -= QuietClaim.cost;
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
