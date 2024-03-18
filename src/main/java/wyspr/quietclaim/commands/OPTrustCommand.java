package wyspr.quietclaim.commands;

import net.minecraft.core.net.command.Command;
import net.minecraft.core.net.command.CommandHandler;
import net.minecraft.core.net.command.CommandSender;

import wyspr.quietclaim.QuietClaim;
import wyspr.quietclaim.utils.ChunkPos;
import wyspr.quietclaim.utils.GetPlayer;

import java.util.List;

public class OPTrustCommand extends Command {
	public OPTrustCommand() {
		super("optrust","opclaimtrust");
	}
//
	public boolean execute(CommandHandler handler, CommandSender sender, String[] args) {
		GetPlayer.PlayerInfo info = GetPlayer.info(sender);
		ChunkPos pos = info.pos;
		String username = info.name;


		if (QuietClaim.isClaimed(pos)) {
			for (String newPlayer : args) {
				if (QuietClaim.trustPlayer(pos, username, newPlayer)) {
					sender.sendMessage("§4"+ newPlayer + "§3 is already trusted here");
				} else {
					sender.sendMessage("§ePlayer §r"+ newPlayer + " §etrusted via Operator.");
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
