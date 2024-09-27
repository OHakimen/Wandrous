package com.hakimen.wandrous.common.spell.effects.spells.static_projectiles;

import com.hakimen.wandrous.common.entity.static_spell.TriggeringGlyphEntity;
import com.hakimen.wandrous.common.registers.EntityRegister;
import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.SpellEffect;
import com.hakimen.wandrous.common.spell.SpellStatus;
import com.hakimen.wandrous.common.utils.CastingUtils;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class GlyphOfTriggeringSpellEffect extends SpellEffect {

    public GlyphOfTriggeringSpellEffect() {
        setKind(TRIGGER);
        setStatus(new SpellStatus()
                .setManaDrain(100)
                .setLifeTime(60 * 20)
                .setRadius(5)
        );
    }

    @Override
    public void cast(SpellContext context) {
        context.mergeStatus(this.getStatus());

        Vec3 location = context.getLocation();
        Level level = context.getLevel();
        TriggeringGlyphEntity triggeringGlyph = new TriggeringGlyphEntity(EntityRegister.TRIGGER_GLYPH.get(),level, CastingUtils.getSpellsFromTree(context.getNode().getChildren().get(0)),
                context.getStatus().getLifeTime(),
                context.getStatus().getRadius(),
                context.getOriginalCaster().getUUID(),
                context.getWand()
        );

        triggeringGlyph.setPos(location);

        level.addFreshEntity(triggeringGlyph);
    }
}