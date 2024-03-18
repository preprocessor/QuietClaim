package wyspr.quietclaim.commands;

import wyspr.quietclaim.QuietClaim;
import wyspr.quietclaim.utils.ChunkPos;
import wyspr.quietclaim.utils.GetPlayer;
import net.minecraft.core.net.command.Command;
import net.minecraft.core.net.command.CommandHandler;
import net.minecraft.core.net.command.CommandSender;

public class OPDelClaimCommand extends Command {
	public OPDelClaimCommand() {
		super("opdelclaim", "opunclaim");
	}

	public boolean execute(CommandHandler handler, CommandSender sender, String[] args) {
		GetPlayer.PlayerInfo info = GetPlayer.info(sender);
		ChunkPos pos = info.pos;

		if (QuietClaim.isClaimed(pos)) {
			 String oldOwner = QuietClaim.getOwner(pos);

			QuietClaim.unclaimChunk(pos);
			QuietClaim.save();

			sender.sendMessage("§eDeleted" + oldOwner +"'s claim via Operator. ");
		} else {
			sender.sendMessage("§3Not owned!");
		}

		return true;
	}

//
	public boolean opRequired(String[] args) {
		return true;
	}
//
	public void sendCommandSyntax(CommandHandler handler, CommandSender sender) {

	}
}
