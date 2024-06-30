package com.hakimen.wandrous.common.spell.effects.spells.static_projectiles;

import com.hakimen.wandrous.common.entity.static_spell.NukeEntity;
import com.hakimen.wandrous.common.registers.EntityRegister;
import com.hakimen.wandrous.common.registers.SoundRegister;
import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.SpellEffect;
import com.hakimen.wandrous.common.spell.SpellStatus;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class NukeSpellEffect extends SpellEffect {


    public NukeSpellEffect() {
        setKind(SPELL);
        setStatus(new SpellStatus()
                .setManaDrain(200)
                .setDamage(100f)
        );
    }

    @Override
    public void cast(SpellContext context) {
        context.mergeStatus(this.getStatus());

        Vec3 location = context.getLocation();
        Level level = context.getLevel();
        level.playSound(null, context.getCaster().getOnPos(), SoundRegister.NUKE.get(), SoundSource.PLAYERS, 2f,1f);
        NukeEntity nuke = new NukeEntity(EntityRegister.NUKE_ENTITY.get(), level, 200, location);
        level.addFreshEntity(nuke);
    }
}
