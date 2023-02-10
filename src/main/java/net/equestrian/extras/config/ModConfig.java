package net.equestrian.extras.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.equestrian.extras.EquestrianExtras;

@Config(name = EquestrianExtras.MOD_ID)
public class ModConfig implements ConfigData {

    @ConfigEntry.Gui.Tooltip
    private boolean doubleDoorsSlideTogether = false;

    @ConfigEntry.Gui.Tooltip
    private Integer barrelBreakChance = 15;

    @ConfigEntry.Gui.Tooltip
    private Integer poleBreakChance = 10;

    @ConfigEntry.Gui.Tooltip
    private boolean useImprovedBreeding = true;

    @ConfigEntry.Gui.Tooltip
    private double maxPercentIncrease = 5;

    @ConfigEntry.Gui.Tooltip
    private double minPercentIncrease = -2.5;

    @ConfigEntry.Gui.Tooltip
    private double maxBreedHealth = 30;

    @ConfigEntry.Gui.Tooltip
    private double minBreedHealth = 15;

    @ConfigEntry.Gui.Tooltip
    private double maxBreedJump = 1.0;

    @ConfigEntry.Gui.Tooltip
    private double minBreedJump = 0.4;

    @ConfigEntry.Gui.Tooltip
    private double maxBreedSpeed = 0.3375;

    @ConfigEntry.Gui.Tooltip
    private double minBreedSpeed = 0.1125;


    public boolean doubleDoorsSlideTogether() {
        return doubleDoorsSlideTogether;
    }

    public Integer barrelBreakChance() {
        return barrelBreakChance;
    }

    public Integer poleBreakChance() {
        return poleBreakChance;
    }

    public boolean useImprovedBreeding() {
        return useImprovedBreeding;
    }

    public double maxIncrease() {
        return maxPercentIncrease;
    }
    public double minIncrease() {
        return minPercentIncrease;
    }

    public double maxBreedHealth() {
        return maxBreedHealth;
    }
    public double minBreedHealth() {
        return minBreedHealth;
    }
    public double maxBreedJump() {
        return maxBreedJump;
    }
    public double minBreedJump() {
        return minBreedJump;
    }
    public double maxBreedSpeed() {
        return maxBreedSpeed;
    }
    public double minBreedSpeed() {
        return minBreedSpeed;
    }
}