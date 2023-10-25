# Equestrian-Extras
![](https://img.shields.io/badge/environment-both-4caf50?style=flat-square)
![](https://img.shields.io/badge/mod%20loader-fabric-d64541?style=flat-square)
![](https://img.shields.io/badge/minecraft%20version-1.17.1-1976d2?style=flat-square)

Equestrian-Extras is a mod for Minecraft (Fabric) that adds some horse related content. I thought Minecraft needed more horse related mods, especially for Fabric, so was my attempt at making that happen.

It is currently only available for 1.17.1 but later versions are planned if I find the time.

## Table of Contents
- [Equestrian-Extras](#equestrian-extras)
  - [Table of Contents](#table-of-contents)
  - [Installation](#installation)
  - [Dependencies](#dependencies)
  - [Features](#features)
    - [Stable and Pasture](#stable-and-pasture)
    - [Equestrian Sport](#equestrian-sport)

## Installation
Equestrian-Extras is a Fabric mod, so it requires the [Fabric Loader](https://fabricmc.net/use/installer/). Once you have installed the Fabric Loader, you can place Equestrian-Extras and its dependencies into your mods folder like any other mod.

More detailed instructions on how to install mods can be found [on the Fabric wiki](https://fabricmc.net/wiki/player:tutorials:adding_mods).

## Dependencies
 - [Fabric Loader](https://fabricmc.net/use/installer/) for minecraft 1.17.1
 - [Fabric API](https://www.curseforge.com/minecraft/mc-mods/fabric-api)
 - [Cloth Config](https://www.curseforge.com/minecraft/mc-mods/cloth-config)
 - [Mod Menu](https://www.curseforge.com/minecraft/mc-mods/modmenu)
 - [Dyed](https://www.curseforge.com/minecraft/mc-mods/dyed) _(optional)_ - enables more tack variants

## Features
- This mod adds two new creative tabs:
  - **[Stable and Pasture](#stable-and-pasture)** includes horse blankets, halters and various blocks for building stables and pastures (such as metal gates and sliding doors).
  - **[Equestrian Sport](#equestrian-sport)** includes horse tack and ribbons as well as blocks for building jumps, dressage arenas, racecourses and barrel racing courses.
-  <details>
   <summary><b> Horse breeding is also modified</b> to make foal's stats more similar to their parent's stats.</summary>

   - A random number between 0 and 1 is generated as a random weight so the initial foal stat can be anywhere between the two parent stats.
   - A random number between the max and min percents of the base vanilla stat is generated as a bonus to be added to the foal's stat.
   - The actual calculation is:
      ```
      foalStat = ((parent1Stat)*randomWeight) + (parent2Stat*(1-randomWeight)) + bonus
      ```
   - Finally, the stat is checked to make sure it is less than the max and greater than the min breedable stats (if not, it is set to the max or min value it goes past).

   </details>
- Altered horse breeding and pole/barrel break chance can be configured using the [cloth-config mod](https://modrinth.com/mod/cloth-config).

### Stable and Pasture
 - **Horse blankets and halters** - worn in the horse armor slot and can be dyed any color like normal leather horse armor.
 - **Saddle Racks** (all wood types) - can be placed on the ground or against walls and can hold saddles.
 - **Sliding and Dutch Doors** (all wood types)
 - **Stable Panels and Bars** (all wood types) - can be centered by holding the sneak key while placing or interacting with the block.
 - **Straw Bedding** - stacks like snow layers.
 - **Thatch** blocks, slabs and stairs
 - **White Gates and Fences**
 - **Iron Gates** (16 colors)
 - **Double-wide Gates** (wood and iron variants)
 
### Equestrian Sport
 - **Tack** - worn in the horse armor slot and can be dyed any color.
   - Saddle Pad (English)
   - Jumping Tack
   - Racing Tack
   - Western Tack
   - Open Barding
   - Wearable Ribbon
 - **Short fences** (all wood types + white) - for designing dressage arenas or (when extended upwards) racecources.
 - **Dressage letters** (8 letters) - these have a flat item texture so they work well in item frames as well as in block form.
 - **Jump parts**:
   - Standards (16 colors) - connect upwards for modular designs.
     - Vertical Rail
     - Horizontal Rail
     - Panel
     - Fancy Panel - these, like glazed terracotta, have connecting patterns with placement direction relative to the player.
   - Fillers (16 colors)
     - Ladder
     - Panel
   - Poles
     - Colored Poles (16 colours)
     - Log Poles (larger poles with log and stripped log variants)
     - <details>
       <summary>Both types of poles can be placed at multiple heights and may be knocked over (broken) when hit by a ridden entity.</summary>
       <ul>
        <li> Poles are placed like slabs (single or double).
        <li> Poles can be shifted up and down by right-clicking them with an empty hand for more height options.
        <li> Sneaking while placing a pole will place it in the lowest position.
        <li> Poles have a (configurable) chance to be broken when hit by a ridden entity.
        <li> Poles emit a redstone signal when they are hit by a ridden entity.
       </ul>
       </details>
 - **Flags** (16 colours) - are normally centered when placed but will move to the side when placed on jump standards.
 - **Barrels** (16 colors) - placed like slabs and have a (configurable) chance to be knocked over (broken) when hit by a ridden entity.
