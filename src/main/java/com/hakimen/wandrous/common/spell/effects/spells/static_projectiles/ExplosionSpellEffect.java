package com.hakimen.wandrous.common.spell.effects.spells.static_projectiles;

import com.hakimen.wandrous.common.events.payloads.PositionalScreenShakePacket;
import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.SpellEffect;
import com.hakimen.wandrous.common.spell.SpellStatus;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.PacketDistributor;

public class ExplosionSpellEffect extends SpellEffect {
    public ExplosionSpellEffect(float strength) {
        this.setKind(SpellEffect.SPELL);
        this.setStatus(new SpellStatus()
                .setDamage(strength)
                .setManaDrain(20 * (int) strength)
                .setCritChance(0.00f)
        );
    }

    @Override
    public void cast(SpellContext context) {
        context.mergeStatus(this.getStatus());

        Level level = context.getLevel();
        Vec3 location = context.getLocation();

        PacketDistributor.sendToPlayersNear((ServerLevel) level, null, location.x, location.y, location.z, 50, new PositionalScreenShakePacket(
                context.getStatus().getDamage() / 8f,
                20,
                40,
                location.toVector3f(),
                50
        ));


        level.explode(null, location.x, location.y, location.z, context.getStatus().getDamage(), Level.ExplosionInteraction.MOB);
    }
}
