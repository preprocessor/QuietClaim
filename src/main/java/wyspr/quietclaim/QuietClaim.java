package wyspr.quietclaim;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;
import turniplabs.halplibe.util.TomlConfigHandler;
import turniplabs.halplibe.util.toml.Toml;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import wyspr.quietclaim.utils.ChunkPos;

public class QuietClaim implements ModInitializer, GameStartEntrypoint, RecipeEntrypoint {
    public static final String MOD_ID = "QuietClaim";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static HashMap<ChunkPos, ArrayList<String>> map = new HashMap<>();

	public static final TomlConfigHandler CONFIG;
	static {
		Toml toml = new Toml();
		toml.addCategory("ClaimUtil");
		toml.addEntry("ClaimUtil.cost", "Cost per chunk (In points)", 0);
		CONFIG = new TomlConfigHandler(MOD_ID, toml);
		cost = CONFIG.getInt("ClaimUtil.cost");
	}
	public static int cost;

    @Override
    public void onInitialize() {

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("LusiiChunksClaim.ser"))) {
			// File exists, so we open and update the map in memory from the disk
            map = (HashMap<ChunkPos, ArrayList<String>>) ois.readObject();
			System.out.println("LusiiChunksClaim reopened from disk:");
//			System.out.println(reopenedMap);
		} catch (IOException | ClassNotFoundException e) {
			// Create blank empty map and write to disk here
		}
        LOGGER.info("LusiiClaimChunks initialized.");
    }

	@Override
	public void beforeGameStart() {

	}

	@Override
	public void afterGameStart() {

	}

	@Override
	public void onRecipesReady() {

	}

	public static void save() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("LusiiChunksClaim.ser"))) {
			oos.writeObject(map);
			//System.out.println("HashMap saved to disk.");
		} catch (IOException e) {

		}
	}

	public static boolean isClaimed(ChunkPos pos) {
		return map.containsKey(pos);
	}

	public static String getOwner(ChunkPos pos) {
		if (isClaimed(pos)) {
			String adminHash = "84346135c711cc270809193d47c522030e39963184346135c711cc270809193d47c522030e399631";
			String owner = map.get(pos).get(0);
			if (Objects.equals(owner, adminHash)) {
				return "Server owned";
			} else {
				return owner;
			}
		}
		return "Unowned";
	}

	public static ArrayList<String> getTrusted(ChunkPos pos) {
		return map.get(pos);
	}

	public static boolean isOwnedBy(ChunkPos pos, String username) {
        return Objects.equals(getOwner(pos), username);
    }

	public static void claimChunk(ChunkPos pos, String username) {
		ArrayList<String> arr = new ArrayList<String>(1);
		arr.add(username);
		map.put(pos, arr);
	}

	public static void unclaimChunk(ChunkPos pos) {
		map.remove(pos);
	}

	/// Returns false if player is added to trusted, true if already present
	public static boolean trustPlayer(ChunkPos pos, String owner, String newPlayer) {
		ArrayList<String> currentTrusted = map.get(pos);

		// Return early if owner already trusts newPlayer
		if (currentTrusted.contains(newPlayer)) {
			return true;
		}

		currentTrusted.add(newPlayer);
		map.put(pos, currentTrusted);
		return false;
	}

	/// Returns false if player is removed from trusted, true if already absent
	public static boolean untrustPlayer(ChunkPos pos, String owner, String newPlayer) {
		ArrayList<String> currentTrusted = map.get(pos);

		if (currentTrusted.contains(newPlayer)) {
			currentTrusted.remove(newPlayer);
			map.put(pos, currentTrusted);
			return false;
		}

		return true;
	}

}
