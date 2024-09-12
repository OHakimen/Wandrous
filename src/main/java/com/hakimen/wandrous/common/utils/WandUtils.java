package com.hakimen.wandrous.common.utils;

import com.hakimen.wandrous.common.item.component.WandDataComponent;
import com.hakimen.wandrous.common.registers.DataComponentsRegister;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

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


    public static void makeWand(ItemStack stack) {

        Random r = new Random(stack.hashCode() ^ System.nanoTime());

        WandDataComponent.WandStatBuilder builder = new WandDataComponent.WandStatBuilder();

        builder.setCapacity(r.nextInt(3, 54));
        builder.setMaxMana(r.nextInt(64, 2048));
        builder.setManaChargeSpeed(r.nextInt(64, 2048));
        builder.setCastDelay(r.nextFloat(-2f, 5f));
        builder.setRechargeSpeed(r.nextFloat(-2f, 5f));
        builder.setMana(builder.getMaxMana());

        builder.setWand(r.nextInt(0, 6));
        builder.setGem(r.nextInt(0, 8));

        builder.setWandName(buildWandName());

        builder.setInventory(new CompoundTag());
        stack.set(DataComponentsRegister.WAND_COMPONENT.get(), builder.build());
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