# DebugCrosshair [![CircleCI](https://circleci.com/gh/cooper-anderson-mc/DebugCrosshair.svg?style=shield)](https://circleci.com/gh/cooper-anderson-mc/DebugCrosshair) [![CurseForge](http://cf.way2muchnoise.eu/full_debugcrosshair_downloads.svg)](https://minecraft.curseforge.com/projects/debugcrosshair)
DebugCrosshair is a mod for Minecraft `1.12.2+`. It forces the F3-screen crosshairs to render even when the F3-screen is not active.

In the config screen of the mod you can:
 * quickly enable/disable the mod
 * change the colors of the axes
 * change the size of the axes
 * change the thickness of the axes

This mod is entirely clientside, which means you can use it on a server that does not have the mod.

## Requirements
 * Minecraft with Forge version `14.23.4.2726` or higher

## Installation
 * It is recommended to install via the [Twitch Desktop App](https://app.twitch.tv/download)'s Minecraft mod manager
 * If for some reason you must install it manually, just drop the .jar file in the mods folder in your minecraft directory as usual

## Contributing
You are welcome to contribute in any way, whether it be bug reporting or pull requests. Here are the instructions on settings up a development environment for the mod:

```bash
# Clone the repository:
git clone https://github.com/cooper-anderson-mc/DebugCrosshair && cd DebugCrosshair;
# Setup Forge and decompile Minecraft:
./gradlew setupDecompWorkspace;
# Run Client when desired:
./gradlew runClient;
```

## Images
![Config Screen](docs/example.png?raw=true)

## Modpacks
You are free to include this mod in any modpack you wish.
