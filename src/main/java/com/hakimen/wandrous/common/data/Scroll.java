package com.hakimen.wandrous.common.data;

import java.util.List;

public class Scroll {
    String name;
    List<String> spells;
    List<String> lore;

    public Scroll(String name, List<String> spells, List<String> lore) {
        this.name = name;
        this.spells = spells;
        this.lore = lore;
    }

    public String getName() {
        return name;
    }

    public Scroll setName(String name) {
        this.name = name;
        return this;
    }

    public List<String> getSpells() {
        return spells;
    }

    public Scroll setSpells(List<String> spells) {
        this.spells = spells;
        return this;
    }

    public List<String> getLore() {
        return lore;
    }

    public Scroll setLore(List<String> lore) {
        this.lore = lore;
        return this;
    }
}
