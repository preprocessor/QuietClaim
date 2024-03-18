package wyspr.quietclaim.utils;

import java.io.Serializable;

public class ChunkPos implements Serializable{
	private static final long serialVersionUID = 1L; // Ensures version compatibility during deserialization
	private final int x;
	private final int y;

	public ChunkPos(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// Override hashCode and equals methods for proper functioning in HashMap
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChunkPos other = (ChunkPos) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
}
