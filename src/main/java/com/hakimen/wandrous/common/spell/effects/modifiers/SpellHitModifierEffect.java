package com.hakimen.wandrous.common.spell.effects.modifiers;

import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.SpellEffect;
import com.hakimen.wandrous.common.spell.SpellHit;
import com.hakimen.wandrous.common.spell.SpellStatus;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.logging.log4j.util.TriConsumer;

import java.util.function.Consumer;

public class SpellHitModifierEffect extends SpellEffect {
    Consumer<Entity> entityHitEffect;
    TriConsumer<Level, BlockPos, BlockState> blockHitEffect;
    public SpellHitModifierEffect(Consumer<Entity> entityHit, TriConsumer<Level, BlockPos, BlockState> blockHit, int cost) {
        this.setKind(MODIFIER);
        this.setStatus(
                new SpellStatus()
                        .setManaDrain(cost)
        );
        this.entityHitEffect = entityHit;
        this.blockHitEffect = blockHit;
    }


    @Override
    public void cast(SpellContext context) {
        context.mergeStatus(this.getStatus());
        context.getHitEffects().add(new SpellHit() {
            @Override
            public void onHitEntity(Entity entityHit) {
                entityHitEffect.accept(entityHit);
            }

            @Override
            public void onHitBlock(Level level, BlockPos blockPos, BlockState blockState) {
                blockHitEffect.accept(level, blockPos, blockState);
            }
        });
        context.getNode().getChildren().forEach((child) -> {
            child.getData().cast(context.setNode(child));
        });
    }
}
