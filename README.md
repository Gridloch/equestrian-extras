# Equestrian-Extras

Equestrian-Extras is a mod for Minecraft (Fabric) that adds some horse related content

## Dependencies
 - [Fabric API](https://www.curseforge.com/minecraft/mc-mods/fabric-api)
 - [Cloth Config](https://www.curseforge.com/minecraft/mc-mods/cloth-config)
 - [Mod Menu](https://www.curseforge.com/minecraft/mc-mods/modmenu)
 - [Dyed](https://www.curseforge.com/minecraft/mc-mods/dyed) _(optional)_ - allows for more tack variants

## Features

### Stable and Pasture
 - Horse blankets and halters (worn in the armor slot and can be dyed any color like normal leather horse armor)
 - Saddle Racks - saddles can be placed on saddle racks
 - Sliding and Dutch doors
 - Stable panels and bars (can be centered by holding the sneak key while placing)
 - Straw bedding (stacks like snow layers)
 - Thatch blocks, slabs and stairs
 - White gates and fences
 - Iron gates
 - Double-wide variants of wooden and iron gates
 
### Equestrian Sport
 - Ribbon item (which can be dyed any color)
 - Tack (worn in the armor slot and can be dyed any color)
   - Saddle Pad
   - Jumping Tack
   - Racing Tack
   - Western Tack
   - Open Barding
 - Short fences (all wood types + white) - these fences extend upwards and no do not connect to the sides when another short fence is placed above them so they should allow for some interesting fence designs
 - Dressage letters (8 letter variants) - have a flat item texture so they can look nice as letters in item frames as well as in block form
 - Jump parts
   - Standards (16 colors, all standards can extend upwards when placed on top of each other)
     - Vertical Rail
     - Horizontal Rail
     - Panel
     - Fancy Panel (these have connecting patterns a bit like glazed terracotta)
   - Fillers (16 colors)
     - Ladder
     - Panel
   - Poles - placed like slabs, have adjustable heights and may be knocked over (broken) when hit by a ridden entity
     - Colored Poles (16 colours)
     - Log Poles (larger poles with log and stripped log variants)
     - Note:
       - Poles can be placed at different heights by right-clicking them with an empty hand
       - Sneaking while placing a pole will place it in the down position
       - Poles emit a redstone signal when they are hit by a ridden entity
 - Flags (16 colours) - are normally centered when placed but will move to the side when placed on jump standards
 - Barrels (16 colors) - placed like slabs and may be knocked over (broken) when hit by a ridden entity
 
 ### Other
  - Horse breeding is altered to allow foals to be more similar to their parents
     <details>
     <summary>How the Foal Stats are Generated</summary>

     - A random number between 0 and 1 is generated as a random weight so the initial foal stat can be anywhere between the two parent stats
     - A random number between the max and min percents of the base vanilla stat is generated as a bonus to be added to the foal's stat
     - The actual calculation is:
     ```math
     foalStat = (parent1Stat)*randomWeight) + (parent2Stat*(1-randomWeight)) + bonus
     ```
     - Finally, the stat is checked to make sure it is less than the max and greater than the min breedable stats (if not, it is set to the max or min value it goes past)

     </details>
  - Altered horse breeding and pole/barrel break chance can be configured with the [cloth-config mod](https://modrinth.com/mod/cloth-config)
