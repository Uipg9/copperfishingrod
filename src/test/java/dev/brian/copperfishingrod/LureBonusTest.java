package dev.brian.copperfishingrod;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class LureBonusTest {
	@Test
	void noEnchantGivesLureOne() {
		assertEquals(5.0F, LureBonus.withBuiltInLure(0.0F));
	}

	@Test
	void stacksWithRealLure() {
		assertEquals(10.0F, LureBonus.withBuiltInLure(5.0F));
		assertEquals(15.0F, LureBonus.withBuiltInLure(10.0F));
	}

	@Test
	void capsAtVanillaMaxLure() {
		assertEquals(15.0F, LureBonus.withBuiltInLure(15.0F));
		
	}

	@Test
	void neverLowersAnExistingReduction() {
		// A modded enchantment above vanilla's cap is left alone rather than reduced.
		assertEquals(25.0F, LureBonus.withBuiltInLure(25.0F));
	}
}
