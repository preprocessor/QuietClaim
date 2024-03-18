package wyspr.quietclaim.commands;

import net.minecraft.core.net.command.Command;
import net.minecraft.core.net.command.CommandHandler;
import net.minecraft.core.net.command.CommandSender;
import wyspr.quietclaim.QuietClaim;
import wyspr.quietclaim.utils.ChunkPos;
import wyspr.quietclaim.utils.GetPlayer;

public class OPUnTrustCommand extends Command {
	public OPUnTrustCommand() {
		super("opuntrust","opclaimuntrust");
	}
//
	public boolean execute(CommandHandler handler, CommandSender sender, String[] args) {
		GetPlayer.PlayerInfo info = GetPlayer.info(sender);
		ChunkPos pos = info.pos;
		String username = info.name;


		if (QuietClaim.isClaimed(pos)) {
			for (String newPlayer : args) {
				if (QuietClaim.untrustPlayer(pos, username, newPlayer)) {
					sender.sendMessage("§4"+ newPlayer + "§3 is already not trusted here");
				} else {
					sender.sendMessage("§ePlayer §r"+ newPlayer + " §euntrusted via Operator.");
				}
			}

			QuietClaim.save();
		} else {
			sender.sendMessage("§3No one owns this chunk!");
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
