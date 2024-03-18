package wyspr.quietclaim.commands;

import wyspr.quietclaim.QuietClaim;
import wyspr.quietclaim.utils.ChunkPos;
import wyspr.quietclaim.utils.GetPlayer;
import net.minecraft.core.net.command.Command;
import net.minecraft.core.net.command.CommandHandler;
import net.minecraft.core.net.command.CommandSender;

import java.util.Collections;

public class OPClaimCommand extends Command {
	public OPClaimCommand() {
		super("opclaim");
	}
//
	public boolean execute(CommandHandler handler, CommandSender sender, String[] args) {
		GetPlayer.PlayerInfo info = GetPlayer.info(sender);
		ChunkPos pos = info.pos;

		String adminHash = "84346135c711cc270809193d47c522030e39963184346135c711cc270809193d47c522030e399631";

		QuietClaim.claimChunk(pos, adminHash);
		QuietClaim.save();

		sender.sendMessage("§eClaimed via Operator");
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
