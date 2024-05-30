package com.hakimen.wandrous.common.spell.effects.spells.teleports;

import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.SpellEffect;
import com.hakimen.wandrous.common.spell.SpellStatus;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class CollectEffect extends SpellEffect {


    public CollectEffect() {
        this.setKind(SpellEffect.SPELL);
        this.setStatus(new SpellStatus()
                .setManaDrain(50)
                .setRadius(8)
        );
    }

    @Override
    public void cast(SpellContext context) {
        context.mergeStatus(this.getStatus());

        Level level = context.getLevel();
        float radius = context.getStatus().getRadius();

        AABB sized = AABB.ofSize(context.getLocation(), radius,radius,radius);

        List<ItemEntity> items = level.getEntities(EntityTypeTest.forClass(ItemEntity.class), sized, itemEntity -> true);

        Vec3 originalCasterPos = context.getOriginalCaster().position();

        for (ItemEntity item : items) {
            item.teleportTo(originalCasterPos.x, originalCasterPos.y, originalCasterPos.z);
        }
    }
}
