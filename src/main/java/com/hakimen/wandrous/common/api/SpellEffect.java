package com.hakimen.wandrous.common.api;

/**
 * <h1>Spell Effect</h1>
 * This class defines what a spell does when its cast, along with the status of the spell, and its kind(s)
 * <br><br>
 * The spell status (see {@link SpellStatus}) defines costs of mana and other stats like damage, speed, crit chance, range, etc...
 * <br><br>
 * The kind defines what behaviour it serves into a casting context (see {@link SpellContext}):
 * <br><br>
 * {@code TRIGGER} = Spell that casts something once it hits the next one<br>
 * {@code MODIFIER} = A spell that modifies the status of the other spells<br>
 * {@code TIMER} = Inherits the behaviour of a trigger spell and also casts the spell it carries once the spell leaves<br>
 * {@code SPELL} = Just a spell that does something, might it be break a block, or literally remove half ot the world<br>
 * {@code GREEK_LETTER} = Special handler for Greek Letter Spells as they are kinda unique<br>
 * <br><br>
 * A kind(s) dont really execute or add any behaviour directly, but they flag what the spells is are going to do when the casting tree is built.
 */
public class SpellEffect {

    public static final int TRIGGER = 1;
    public static final int MODIFIER = 2;
    public static final int TIMER = 4;
    public static final int SPELL = 8;
    public static final int GREEK_LETTER = 16;
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
