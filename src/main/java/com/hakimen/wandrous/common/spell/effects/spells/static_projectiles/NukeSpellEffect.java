package com.hakimen.wandrous.common.spell.effects.spells.static_projectiles;

import com.hakimen.wandrous.common.entity.static_spell.NukeEntity;
import com.hakimen.wandrous.common.events.payloads.PositionalScreenShakePacket;
import com.hakimen.wandrous.common.registers.EntityRegister;
import com.hakimen.wandrous.common.registers.SoundRegister;
import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.SpellEffect;
import com.hakimen.wandrous.common.spell.SpellStatus;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.PacketDistributor;

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
        PacketDistributor.sendToPlayersNear((ServerLevel) level, null, location.x, location.y, location.z, 150, new PositionalScreenShakePacket(
                2.5f,
                20,
                350,
                location.toVector3f(),
                90
        ));
        level.addFreshEntity(nuke);
    }
}
