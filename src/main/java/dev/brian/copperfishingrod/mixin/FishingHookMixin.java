package dev.brian.copperfishingrod.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.brian.copperfishingrod.ModItems;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/** Vanilla shouldStopFishing checks is(Items.FISHING_ROD) by identity; accept the copper rod too. */
@Mixin(FishingHook.class)
public abstract class FishingHookMixin {
	@WrapOperation(method = "shouldStopFishing", at = @At(value = "INVOKE",
		target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z"))
	private boolean copperfishingrod$acceptCopperRod(ItemStack stack, Object item, Operation<Boolean> original) {
		return original.call(stack, item) || stack.is(ModItems.COPPER_FISHING_ROD);
	}
}
