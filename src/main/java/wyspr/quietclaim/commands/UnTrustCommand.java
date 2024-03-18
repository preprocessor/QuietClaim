package wyspr.quietclaim.commands;

import wyspr.quietclaim.QuietClaim;
import wyspr.quietclaim.utils.ChunkPos;
import wyspr.quietclaim.utils.GetPlayer;
import net.minecraft.core.net.command.Command;
import net.minecraft.core.net.command.CommandHandler;
import net.minecraft.core.net.command.CommandSender;

public class UnTrustCommand extends Command {
	public UnTrustCommand() {
		super("untrust","claimuntrust");
	}
//
	public boolean execute(CommandHandler handler, CommandSender sender, String[] args) {
		GetPlayer.PlayerInfo info = GetPlayer.info(sender);
		ChunkPos pos = info.pos;
		String username = info.name;

		if (QuietClaim.isOwnedBy(pos, username)) {
			for (String newPlayer : args) {
				if (QuietClaim.untrustPlayer(pos, username, newPlayer)) {
					sender.sendMessage("§4"+ newPlayer + "§3 is already not trusted here");
				} else {
					sender.sendMessage("§3Player §r"+ newPlayer + " §3untrusted.");
				}
			}

			QuietClaim.save();

		} else if (QuietClaim.isClaimed(pos)) {
			sender.sendMessage("§eYou do not own this chunk!");
		} else {
			sender.sendMessage("§3No one owns this chunk!");

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
