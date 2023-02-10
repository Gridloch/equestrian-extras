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
            double h = Math.random();
            double hBonus = (Math.random()*(config.maxIncrease()-config.minIncrease())+config.minIncrease())*30/100; // random bonus between max and min percent of 30
            double health = (this.getAttributeBaseValue(EntityAttributes.GENERIC_MAX_HEALTH)*h) + (mate.getAttributeBaseValue(EntityAttributes.GENERIC_MAX_HEALTH)*(1-h)) + hBonus;
            
            if (health > config.maxBreedHealth()) {
                health = config.maxBreedHealth(); // Prevents health exceeding max
            }
            else if (health < config.minBreedHealth()) {
                health =  config.minBreedHealth(); // Prevents health less than min
            }

            child.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(health);


            //Jump
            double j = Math.random();
            double jBonus = (Math.random()*(config.maxIncrease()-config.minIncrease())+config.minIncrease())*1.0/100; // random bonus between max and min percent of 1.0
            double jump = (this.getAttributeBaseValue(EntityAttributes.HORSE_JUMP_STRENGTH)*j) + (mate.getAttributeBaseValue(EntityAttributes.HORSE_JUMP_STRENGTH)*(1-j)) + jBonus;
            
            if (jump > config.maxBreedJump()) {
                jump = config.maxBreedJump(); // Prevents jump exceeding max
            }
            else if (jump < config.minBreedJump()) {
                jump = config.minBreedJump(); // Prevents jump less than min
            }
            
            child.getAttributeInstance(EntityAttributes.HORSE_JUMP_STRENGTH).setBaseValue(jump);


            //Speed
            double s = Math.random();
            double sBonus = (Math.random()*(config.maxIncrease()-config.minIncrease())+config.minIncrease())*0.3375/100; // random bonus between max and min percent of 0.3375
            double speed = (this.getAttributeBaseValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)*s) + (mate.getAttributeBaseValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)*(1-s)) + sBonus;
            
            if (speed > config.maxBreedSpeed()) {
                speed = config.maxBreedSpeed(); // Prevents speed exceeding max
            }
            else if (speed < config.minBreedSpeed()) {
                speed = config.minBreedSpeed(); // Prevents speed less than min
            }
            
            child.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(speed);

        }
    }
}
