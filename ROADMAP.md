# Roadmap

## Done (verified by `.\scripts\verify.ps1`, 2026-11-25)
- 1.0.1: recipe unlock advancement so the recipe shows in the recipe book (unit test guards every recipe).
- `copperfishingrod:copper_fishing_rod`: subclass of `FishingRodItem`, 64 durability, enchantable, repairable with copper.
- Built-in Lure I (`LureBonus`, unit-tested): +5 s on top of real Lure, capped at Lure III (15 s).
  Gametest confirms an unenchanted cast has `lureSpeed == 100` ticks.
- Hook stays out: mixin on `FishingHook.shouldStopFishing` (vanilla checks `Items.FISHING_ROD` by identity).
  Gametest: real right-click cast into water, hook alive and in water after 60 ticks, reel-in removes it.
- Cast model via `minecraft:fishing_rod/cast` condition; screenshot `0000_cast.png` shows the cast texture.
- Recipe (3 copper ingots diagonal + 2 string), Tools & Utilities tab after the fishing rod, lang.
- Tags: `#minecraft:enchantable/fishing` (Lure, Luck of the Sea) and `#minecraft:enchantable/durability`
  (Unbreaking, Mending; Curse of Vanishing via `enchantable/vanishing`).

## Untested in game
- Actually catching a fish / loot, hooking and pulling a mob, enchanting-table offers, anvil repair,
  Lure II/III stacking in game (only the pure math is tested), creative tab position.

## Not done
- No GitHub repo / CI run yet. Dispensers and other mods that check `Items.FISHING_ROD` by identity
  (e.g. some vanilla advancement/loot predicates) are not audited.
