package wyspr.quietclaim.commands;

import wyspr.quietclaim.QuietClaim;
import wyspr.quietclaim.utils.ChunkPos;
import wyspr.quietclaim.utils.GetPlayer;
import net.minecraft.core.net.command.Command;
import net.minecraft.core.net.command.CommandHandler;
import net.minecraft.core.net.command.CommandSender;

import java.util.ArrayList;
import java.util.Objects;

public class TrustCommand extends Command {
	public TrustCommand() {
		super("trust","claimtrust");
	}
//
	public boolean execute(CommandHandler handler, CommandSender sender, String[] args) {
		GetPlayer.PlayerInfo info = GetPlayer.info(sender);
		ChunkPos pos = info.pos;
		String username = info.name;


		if (QuietClaim.isOwnedBy(pos, username)) {
			for (String newPlayer : args) {
				if (QuietClaim.trustPlayer(pos, username, newPlayer)) {
					sender.sendMessage("§4"+ newPlayer + "§3 is already trusted here");
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
