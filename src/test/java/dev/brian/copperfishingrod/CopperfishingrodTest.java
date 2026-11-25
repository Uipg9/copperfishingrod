package dev.brian.copperfishingrod;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CopperfishingrodTest {
	@Test
	void idUsesModNamespace() {
		var id = Copperfishingrod.id("example");
		assertEquals(Copperfishingrod.MOD_ID, id.getNamespace());
		assertEquals("example", id.getPath());
	}

	@Test
	void langKeyFollowsMinecraftConvention() {
		assertEquals("item.copperfishingrod.example", Copperfishingrod.langKey("item", "example"));
	}
}
