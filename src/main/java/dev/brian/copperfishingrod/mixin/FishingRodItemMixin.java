package dev.brian.copperfishingrod.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import dev.brian.copperfishingrod.CopperFishingRodItem;
import dev.brian.copperfishingrod.LureBonus;
import net.minecraft.world.item.FishingRodItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/** Adds the copper rod's built-in Lure I where vanilla computes the lure speed on cast. */
@Mixin(FishingRodItem.class)
public abstract class FishingRodItemMixin {
	@ModifyExpressionValue(method = "use", at = @At(value = "INVOKE",
		target = "Lnet/minecraft/world/item/enchantment/EnchantmentHelper;getFishingTimeReduction(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/Entity;)F"))
	private float copperfishingrod$builtInLure(float seconds) {
		return (Object) this instanceof CopperFishingRodItem ? LureBonus.withBuiltInLure(seconds) : seconds;
	}
}
