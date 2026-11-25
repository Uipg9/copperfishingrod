package dev.brian.copperfishingrod;

/** Built-in Lure I for the copper rod. Pure math on vanilla's fishing time reduction (seconds). */
public final class LureBonus {
	/** Vanilla Lure gives 5 s per level. */
	public static final float BUILT_IN_SECONDS = 5.0F;
	/** Lure III, vanilla's max effect. */
	public static final float MAX_SECONDS = 15.0F;

	private LureBonus() {
	}

	/** Adds Lure I to the enchanted reduction, capped at Lure III; never lowers an existing reduction. */
	public static float withBuiltInLure(float enchantSeconds) {
		return Math.max(enchantSeconds, Math.min(enchantSeconds + BUILT_IN_SECONDS, MAX_SECONDS));
	}
}
