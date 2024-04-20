package com.hakimen.wandrous.common.spell;

import com.hakimen.wandrous.common.utils.CastingUtils;
import com.hakimen.wandrous.common.utils.WandUtils;
import com.hakimen.wandrous.common.utils.data.Node;
import jdk.jshell.Snippet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.intellij.lang.annotations.MagicConstant;

public class SpellEffect {

    public static final int TRIGGER = 1;
    public static final int MODIFIER = 2;
    public static final int SPELL = 4;
    int kind;
    SpellStatus status;
    public void cast(SpellContext context) {
    }

    public int getKind() {
        return kind;
    }

    public boolean hasKind(int kind){
        return (this.getKind() & kind) == kind;
    }


    public SpellEffect setKind(int kind) {
        this.kind = kind;
        return this;
    }

    public SpellStatus getStatus() {
        return status;
    }

    public SpellEffect setStatus(SpellStatus status) {
        this.status = status;
        return this;
    }
}
