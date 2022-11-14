# BlockBlocks
A Minecraft Spigot Plugin that can block the placement of certain Blocks in certain Worlds!

With this configurable Plugin, you can set two lists of blocks. A global blacklist, which includes blocks that can't be placed anywhere on the server unless the Player has a specific bypass permission. The second list is more specialized and includes blocks, that can't be placed on specific worlds, unless a bypass permission is given.

This plugin can be useful to let users play around in Creative Mode but still manage a certain level of control over the blocks that can be used. Like Light-Blocks or Barriers. Another use can be the control of some kind of backwards compatibility, when disallowing newer blocks (the default config includes this).

## Usage
Most of the Plugin works through editing the config.yml. Although there some command options:

`/blockblocks reload` to reload the config, and
`/blockblocks toggle` to add the current world to the world-blacklist.

You can also use the alias `/bb`, but since it's so short, it might interfere with another plugin. Keep that in mind.  

## Configurability
### Permissions
You can change the permissions as you wish. An admin permission is used for the commands, and two bypass permissions for the global and the special blacklist. They dafault to:
```
permission:
  admin: "zf.bb.admin"
  bypass_global: "zf.bb.bypass.global"
  bypass_blacklist: "zf.bb.bypass.blacklist"
```
### Language
You can also fully control the output phrases. Edit the prefix and more to your liking. Feel free the use colors with the colorcode `&`. They default to:
```
lang:
  prefix: "&9BB &8>> &f"
  no_perms: "&cNo permission!"
  world_added: "&aWorld added!"
  world_removed: "&cWorld removed!"
  reload_done: "&aReload done!"
  unable_to_place: "&cYou cannot place that block!"
  wrong_use: "Wrong usage! Try /blockblocks toggle"
```
### Global Block Blacklist
This blacklist should contain all block materials (in upper case), that should be blocked everywhere on your server! The default list includes some blocks you probably don't want users to place at all. See the default here:
```
global_blacklist_blocks:
  - LIGHT
  - BARRIER
  - COMMAND_BLOCK
  - CHAIN_COMMAND_BLOCK
  - REPEATING_COMMAND_BLOCK
  - SPAWNER
  - JIGSAW
  - STRUCTURE_BLOCK
  - STRUCTURE_VOID
```
### Special Block Blacklist
This list is similar to the global one, but should include all blocks you don't want to have on the worlds which are toggled on (see World Blacklist below). By default it contains the new blocks of the 1.19 Update. I used them, to let users build, but remain backwards compatibility to 1.18. See the defaults:
```
blacklist_blocks:
  - OCHRE_FROGLIGHT
  - VERDANT_FROGLIGHT
  - PEARLESCENT_FROGLIGHT
  - FROGSPAWN
  - MANGROVE_PLANKS
  - MANGROVE_PROPAGULE
  - MANGROVE_LOG
  - MANGROVE_ROOTS
  - MUDDY_MANGROVE_ROOTS
  - STRIPPED_MANGROVE_LOG
  - STRIPPED_MANGROVE_WOOD
  - MANGROVE_WOOD
  - MANGROVE_LEAVES
  - MANGROVE_SLAB
  - MANGROVE_FENCE
  - MANGROVE_STAIRS
  - MANGROVE_BUTTON
  - MANGROVE_PRESSURE_PLATE
  - MANGROVE_DOOR
  - MANGROVE_TRAPDOOR
  - MANGROVE_FENCE_GATE
  - MANGROVE_SIGN
  - MUD
  - MUD_BRICK_SLAB
  - PACKED_MUD
  - MUD_BRICKS
  - MUD_BRICK_STAIRS
  - MUD_BRICK_WALL
  - REINFORCED_DEEPSLATE
  - SCULK
  - SCULK_VEIN
  - SCULK_CATALYST
  - SCULK_SHRIEKER
  - SCULK_SENSOR
```
### World Blacklist
The last list in the config includes the worlds, in which the special blacklist is active. You can set the world names here manual, or just use the toggle command in that world. When adding manual, keep the scheme of the other lists. I addad a default value, you probably want to change:
```
blacklist_worlds:
  - world_mm_test
```
