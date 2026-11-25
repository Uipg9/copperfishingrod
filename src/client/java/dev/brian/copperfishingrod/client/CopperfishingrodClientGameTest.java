package dev.brian.copperfishingrod.client;

import dev.brian.copperfishingrod.Copperfishingrod;
import dev.brian.copperfishingrod.ModItems;
import java.lang.reflect.Field;
import net.fabricmc.fabric.api.client.gametest.v1.FabricClientGameTest;
import net.fabricmc.fabric.api.client.gametest.v1.context.ClientGameTestContext;
import net.fabricmc.fabric.api.client.gametest.v1.context.TestSingleplayerContext;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.projectile.FishingHook;

/**
 * In-game test: casts the copper rod into water with a real right-click and checks the hook stays
 * out (vanilla would despawn it for a non-vanilla rod) and carries the built-in Lure I speed.
 * Screenshots land in {@code build\run\clientGameTest\screenshots}.
 */
public class CopperfishingrodClientGameTest implements FabricClientGameTest {
	private static final BlockPos WATER_TARGET = new BlockPos(5, 100, 8);

	@Override
	public void runTest(ClientGameTestContext context) {
		expect(FabricLoader.getInstance().isModLoaded(Copperfishingrod.MOD_ID), Copperfishingrod.MOD_ID + " is not loaded");

		context.waitForScreen(TitleScreen.class);
		context.waitTicks(60);

		try (TestSingleplayerContext world = context.worldBuilder().create()) {
			context.waitTicks(100);

			boolean hasRecipe = world.getServer().computeOnServer(server -> server.getRecipeManager()
				.byKey(ResourceKey.create(Registries.RECIPE, Copperfishingrod.id("copper_fishing_rod"))).isPresent());
			expect(hasRecipe, "recipe copperfishingrod:copper_fishing_rod missing");
			pass("recipe");

			// Stage: stone floor, a water pool in front of the player, survival, holding the copper rod.
			world.getServer().runCommand("gamemode survival @p");
			world.getServer().runCommand("time set day");
			world.getServer().runCommand("fill 0 99 0 10 99 14 stone");
			world.getServer().runCommand("fill 0 100 0 10 104 14 air");
			world.getServer().runCommand("fill 0 100 0 10 100 3 stone");
			world.getServer().runCommand("fill 0 100 4 10 100 14 water");
			world.getServer().runCommand("tp @p 5.5 101 1.5 0 20");
			world.getServer().runCommand("item replace entity @p weapon.mainhand with copperfishingrod:copper_fishing_rod");
			context.waitTicks(10);
			context.getInput().lookAt(WATER_TARGET);
			context.waitTicks(2);

			// Cast with a real right-click and let the bobber land and settle.
			context.getInput().pressKey(options -> options.keyUse);
			context.waitTicks(60);

			int lureSpeed = world.getServer().computeOnServer(server -> {
				ServerPlayer player = server.getPlayerList().getPlayers().getFirst();
				FishingHook hook = player.fishing;
				if (hook == null || hook.isRemoved()) {
					return -1;
				}
				expect(player.getMainHandItem().is(ModItems.COPPER_FISHING_ROD), "player should still hold the copper rod");
				return readLureSpeed(hook);
			});
			expect(lureSpeed != -1, "fishing hook despawned or was never cast");
			expect(lureSpeed == 100, "expected built-in Lure I (100 ticks), lureSpeed was " + lureSpeed);
			pass("cast stays out with Lure I");
			context.takeScreenshot("cast");

			boolean inWater = world.getServer().computeOnServer(server -> {
				FishingHook hook = server.getPlayerList().getPlayers().getFirst().fishing;
				return hook != null && hook.isInWater();
			});
			expect(inWater, "hook should be floating in water");
			pass("hook in water");

			// Reel in: hook gone.
			context.getInput().pressKey(options -> options.keyUse);
			context.waitTicks(10);
			boolean reeled = world.getServer().computeOnServer(server ->
				server.getPlayerList().getPlayers().getFirst().fishing == null);
			expect(reeled, "hook should be gone after reeling in");
			pass("reel in");
			context.takeScreenshot("reeled");
		}
	}

	private static int readLureSpeed(FishingHook hook) {
		try {
			Field field = FishingHook.class.getDeclaredField("lureSpeed");
			field.setAccessible(true);
			return field.getInt(hook);
		} catch (ReflectiveOperationException e) {
			throw new AssertionError("could not read FishingHook.lureSpeed", e);
		}
	}

	private static void pass(String step) {
		System.out.println("[copperfishingrod-gametest] PASS " + step);
	}

	private static void expect(boolean condition, String message) {
		if (!condition) {
			throw new AssertionError(message);
		}
	}
}
