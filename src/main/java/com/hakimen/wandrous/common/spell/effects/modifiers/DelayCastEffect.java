package com.hakimen.wandrous.common.spell.effects.modifiers;

import com.hakimen.wandrous.common.entity.TimerEntity;
import com.hakimen.wandrous.common.registers.EntityRegister;
import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.SpellEffect;
import com.hakimen.wandrous.common.spell.SpellStatus;
import com.hakimen.wandrous.common.utils.CastingUtils;
import com.hakimen.wandrous.common.utils.data.Node;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.TickRateManager;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.phys.Vec3;

import java.util.Timer;
import java.util.TimerTask;

public class DelayCastEffect extends SpellEffect {
    int time;

    public DelayCastEffect(int time) {
        this.time = time * 20;
        this.setKind(MODIFIER);
        this.setStatus(new SpellStatus()
                .setManaDrain(5 * time)
        );
    }


    @Override
    public void cast(SpellContext context) {
        context.mergeStatus(this.getStatus());
        TimerEntity timer = new TimerEntity(EntityRegister.TIMER_ENTITY.get(), context.getLevel(), time) {
            @Override
            public void tick() {
                tickTime++;
                if (this.tickedEnough()) {
                    context.getNode().getChildren().forEach((child)-> {
                        this.setDeltaMovement(context.getCaster().getDeltaMovement());
                        child.getData().cast(context.clone().setCaster(this).setNode(child));
                    });
                    discard();
                }
            }
        };

        timer.setPos(context.getLocation());

        context.getLevel().addFreshEntity(timer);
    }
}
