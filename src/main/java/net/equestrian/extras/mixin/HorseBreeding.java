package net.equestrian.extras.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.shedaniel.autoconfig.AutoConfig;
import net.equestrian.extras.config.ModConfig;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.HorseBaseEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.world.World;


@Mixin(HorseBaseEntity.class)
public abstract class HorseBreeding extends AnimalEntity {
    
    protected HorseBreeding(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "setChildAttributes", at = @At(value = "TAIL"))
    protected void onSetChildAttributes(PassiveEntity mate, HorseBaseEntity child, CallbackInfo ci) {

        ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
        if (config.useImprovedBreeding()) {
            
            //Health
            double health = generateStat(30,
                    this.getAttributeBaseValue(EntityAttributes.GENERIC_MAX_HEALTH),
                    mate.getAttributeBaseValue(EntityAttributes.GENERIC_MAX_HEALTH),
                    config.maxBreedHealth(),
                    config.minBreedHealth());
            child.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(health);


            //Jump
            double jump = generateStat(1,
                    this.getAttributeBaseValue(EntityAttributes.HORSE_JUMP_STRENGTH),
                    mate.getAttributeBaseValue(EntityAttributes.HORSE_JUMP_STRENGTH),
                    config.maxBreedJump(),
                    config.minBreedJump());
            child.getAttributeInstance(EntityAttributes.HORSE_JUMP_STRENGTH).setBaseValue(jump);


            //Speed
            double speed = generateStat(0.3375,
                    this.getAttributeBaseValue(EntityAttributes.GENERIC_MOVEMENT_SPEED),
                    mate.getAttributeBaseValue(EntityAttributes.GENERIC_MOVEMENT_SPEED),
                    config.maxBreedSpeed(),
                    config.minBreedSpeed());
            child.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(speed);
        }
    }

    /** Generates a foal stat based on the parents stats
     * @param maxVanillaStat the max vanilla value of the stat
     * @param parent1Stat the stat from the first parent
     * @param parent2Stat the stat from the other parent
     * @param maxBreedable the maximum percent value of the vanilla stat that the foals stat can increase/decrease by
     * @param minBreedable the minimum percent value of the vanilla stat that the foals stat can increase/decrease by
     */
    private static double generateStat(double maxVanillaStat, double parent1Stat, double parent2Stat, double maxBreedable, double minBreedable) {
        ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();

        double randomWeight = Math.random();
        double bonus = (Math.random()*(config.maxIncrease()-config.minIncrease())+config.minIncrease())* maxVanillaStat /100; // random bonus between max and min percent of the max vanilla stat
        double newStat = (parent1Stat*randomWeight) + (parent2Stat*(1-randomWeight)) + bonus;

        if (newStat > maxBreedable) {
            newStat = maxBreedable; // Prevents newStat exceeding max
        }
        else if (newStat < minBreedable) {
            newStat = minBreedable; // Prevents newStat less than min
        }

        return newStat;
    }
}
