package com.hakimen.wandrous.common.utils;

import com.hakimen.wandrous.common.item.WandItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.ItemStackHandler;

import java.util.List;
import java.util.Random;

public class WandUtils {

    private static final List<String> WIZARD_NAMES = List.of("Alaric",
            "Balthazar",
            "Cedric",
            "Darius",
            "Eldon",
            "Fenwick",
            "Gaius",
            "Horatio",
            "Ignatius",
            "Jareth",
            "Kendrick",
            "Lysander",
            "Merrick",
            "Nolan",
            "Orion",
            "Percival",
            "Quintus",
            "Roderick",
            "Silas",
            "Thaddeus",
            "Ulysses",
            "Vance",
            "Warrick",
            "Xavier",
            "Yorick",
            "Zephyrus",
            "Alden",
            "Brogan",
            "Cyrus",
            "Draven",
            "Elric",
            "Fergus",
            "Galen",
            "Hadrian",
            "Ivor",
            "Jericho",
            "Kaius",
            "Leander",
            "Magnus",
            "Nestor",
            "Oswin",
            "Phineas",
            "Quillon",
            "Reynard",
            "Soren",
            "Tiberius",
            "Ulric",
            "Vaughn",
            "Wilfred",
            "Xenos",
            "Yates",
            "Zane"
    );
    private static final List<String> STAFFS = List.of(
            "Wand",
            "Scepter",
            "Rod",
            "Staff"
    );
    private static final List<String> ADJECTIVES = List.of(
            "Ancient",
            "Mystical",
            "Enchanted",
            "Glowing",
            "Arcane",
            "Celestial",
            "Ethereal",
            "Fiery",
            "Icy",
            "Tempestuous",
            "Radiant",
            "Shadowy",
            "Divine",
            "Infernal",
            "Prismatic",
            "Elemental",
            "Spectral",
            "Bewitched",
            "Illusory",
            "Vibrant",
            "Whispering",
            "Thunderous",
            "Starry",
            "Moonlit",
            "Astral",
            "Harmonic",
            "Stormforged",
            "Draconic",
            "Phantom",
            "Runic",
            "Shimmering",
            "Tidal",
            "Wind-carved",
            "Flame-touched",
            "Frostbound",
            "Stone-wrought",
            "Skyward",
            "Nature-bound",
            "Fate-woven",
            "Starforged",
            "Void-cast",
            "Time-lost",
            "Oceanic",
            "Cosmic",
            "Dreamwoven",
            "Royal",
            "Sovereign"
    );

    private static final List<String> NOUNS = List.of(
            "Enchantment",
            "Arcanum",
            "Phantasm",
            "Talisman",
            "Ward",
            "Zephyr",
            "Nebula",
            "Mystique",
            "Oracle",
            "Vortex",
            "Chronicle",
            "Aether",
            "Catalyst",
            "Divination",
            "Ethereal",
            "Hex",
            "Jinx",
            "Lore",
            "Mirage",
            "Nimbus",
            "Omen",
            "Prism",
            "Revelation",
            "Spirit",
            "Umbra",
            "Vision",
            "Whisper",
            "Xenon",
            "Yggdrasil",
            "Zenith",
            "§k????????§r"
    );

    public static void createWand(ItemStack stack) {
        Random random = new Random();

        CompoundTag tag = new CompoundTag();

        tag.putInt(WandItem.CAPACITY, random.nextInt(2,27));
        tag.putInt(WandItem.MAX_MANA, random.nextInt(128,1024));
        tag.putInt(WandItem.MANA_CHARGE_SPEED, random.nextInt(128,1024));
        tag.putFloat(WandItem.CAST_DELAY, random.nextFloat(-1f,5f));
        tag.putFloat(WandItem.RECHARGE_SPEED, random.nextFloat(-1f,5f));


        tag.putString("name", buildWandName());

        tag.put("Inventory", new ItemStackHandler(tag.getInt(WandItem.CAPACITY)).serializeNBT());

        stack.setTag(tag);
    }

    public static String buildWandName() {
        Random random = new Random();

        String toReturn = "";
        boolean the = random.nextFloat() < 0.5;

        String wizard = WIZARD_NAMES.get(random.nextInt(0, WIZARD_NAMES.size()));
        toReturn += wizard + (wizard.endsWith("s") ? "' " : "'s ");

        if (the) {
            String adjective = ADJECTIVES.get(random.nextInt(0, ADJECTIVES.size()));
            toReturn += adjective + " ";

            String wand = STAFFS.get(random.nextInt(0, STAFFS.size()));
            toReturn += wand + " of ";

            String noun = NOUNS.get(random.nextInt(0, NOUNS.size()));
            toReturn += noun;

        } else {
            String adjective = ADJECTIVES.get(random.nextInt(0, ADJECTIVES.size()));
            toReturn += adjective + " ";

            String wand = STAFFS.get(random.nextInt(0, STAFFS.size()));
            toReturn += wand;
        }

        return toReturn;
    }

}
