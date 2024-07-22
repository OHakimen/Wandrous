package com.hakimen.wandrous.common.spell;

public class SpellEffect {

    public static final int TRIGGER = 1;
    public static final int MODIFIER = 2;
    public static final int TIMER = 4;
    public static final int SPELL = 8;
    int kind;
    SpellStatus status;

    public void cast(SpellContext context) {}

    public int getKind() {
        return kind;
    }

    public boolean hasKind(int kind){
        return (this.getKind() & kind) == kind;
    }

    public boolean hasAnyOf(int... kinds){
        for (int i = 0; i < kinds.length; i++) {
            if((this.getKind() & kinds[i]) == kinds[i]){
                return true;
            }
        }
        return false;
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
