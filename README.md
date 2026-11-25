# Copper Fishing Rod

A fishing rod made of copper. It fishes exactly like the vanilla rod (casting, catching, hooking mobs,
Lure / Luck of the Sea / Unbreaking / Mending, 64 durability), but has **built-in Lure I**: fish bite
about 5 seconds sooner. It stacks with real Lure, up to vanilla's max effect (Lure III).

Fabric mod for Minecraft 26.3 (Java 25, Mojang names). Requires Fabric API only. Singleplayer and server.

## Recipe

The vanilla fishing rod recipe with copper ingots instead of sticks (3 copper ingots on the diagonal,
2 string). Found in the Tools & Utilities tab next to the fishing rod. Repair with copper ingots.

## Development

Build and test: `.\gradlew.bat build`. Full check (build, unit tests, in-game client gametest):
`.\scripts\verify.ps1`. The lure math lives in `LureBonus` (pure Java, unit-tested).

License: CC0-1.0.
