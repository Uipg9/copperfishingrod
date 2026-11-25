package dev.brian.copperfishingrod;

import net.minecraft.world.item.FishingRodItem;

/**
 * A fishing rod with built-in Lure I. Subclassing {@link FishingRodItem} keeps the client's
 * instanceof checks (cast model, fishing line hand) working; the lure bonus and the hook's
 * "still holding a rod" check are handled by mixins.
 */
public class CopperFishingRodItem extends FishingRodItem {
	public CopperFishingRodItem(Properties properties) {
		super(properties);
	}
}
