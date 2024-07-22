package com.hakimen.wandrous.common.spell.effects.modifiers;

import com.hakimen.wandrous.common.entity.static_spell.TimerEntity;
import com.hakimen.wandrous.common.registers.EntityRegister;
import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.SpellEffect;
import com.hakimen.wandrous.common.spell.SpellStatus;
import net.minecraft.world.entity.player.Player;

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
                super.tick();
                if (tickedEnough()) {
                    context.getNode().getChildren().forEach((child)-> {
                        this.setDeltaMovement(context.getCaster().getDeltaMovement());
                        child.getData().getEffect().cast(context.setCaster(this).setNode(child));
                    });

                    discard();
                }
            }
        };
        if(context.getCaster() instanceof Player player){
            timer.setPos(player.getPosition(0));
        }else{
            timer.setPos(context.getLocation());
        }
        context.getLevel().addFreshEntity(timer);
    }
}
