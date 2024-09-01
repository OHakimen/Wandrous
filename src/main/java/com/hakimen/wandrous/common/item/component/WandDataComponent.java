package com.hakimen.wandrous.common.item.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ExtraCodecs;

import java.util.Objects;


public class WandDataComponent {

    public static WandStat DEFAULT_STAT = new WandDataComponent.WandStat(
            0,0,0,0f,0f,0,0,0,0,0, new CompoundTag(), "", 0,0, false
    );

    public static class WandStatBuilder {
        int capacity;
        int maxMana;
        int mana;
        float castDelay;
        int wandCooldown;
        int maxCooldown;
        float rechargeSpeed;
        int manaChargeSpeed;
        int castableSize;
        int currentIdx;
        int gem;
        int wand;
        boolean fromCastDelay;
        CompoundTag inventory;
        String wandName;

        public WandStatBuilder() {
        }

        public WandStatBuilder(WandStat stat) {
            this.capacity = stat.capacity;
            this.maxMana = stat.maxMana;
            this.mana = stat.mana;
            this.castDelay = stat.castDelay;
            this.rechargeSpeed = stat.rechargeSpeed;
            this.manaChargeSpeed = stat.manaChargeSpeed;
            this.castableSize = stat.castableSize;
            this.currentIdx = stat.currentIdx;
            this.gem = stat.gem;
            this.wand = stat.wand;
            this.wandCooldown = stat.wandCooldown;
            this.inventory = stat.inventory;
            this.wandName = stat.name;
            this.fromCastDelay = stat.fromCastDelay;
            this.maxCooldown = stat.maxCooldown;
        }

        public WandStatBuilder setCapacity(int capacity) {
            this.capacity = capacity;
            return this;
        }

        public WandStatBuilder setMaxMana(int maxMana) {
            this.maxMana = maxMana;
            return this;
        }

        public WandStatBuilder setMana(int mana) {
            this.mana = mana;
            return this;
        }

        public WandStatBuilder setCastDelay(float castDelay) {
            this.castDelay = castDelay;
            return this;
        }

        public WandStatBuilder setRechargeSpeed(float rechargeSpeed) {
            this.rechargeSpeed = rechargeSpeed;
            return this;
        }

        public WandStatBuilder setManaChargeSpeed(int manaChargeSpeed) {
            this.manaChargeSpeed = manaChargeSpeed;
            return this;
        }

        public WandStatBuilder setCastableSize(int castableSize) {
            this.castableSize = castableSize;
            return this;
        }

        public WandStatBuilder setCurrentIdx(int currentIdx) {
            this.currentIdx = currentIdx;
            return this;
        }

        public WandStatBuilder setGem(int gem) {
            this.gem = gem;
            return this;
        }

        public WandStatBuilder setWand(int wand) {
            this.wand = wand;
            return this;
        }

        public WandStatBuilder setInventory(CompoundTag inventory) {
            this.inventory = inventory;
            return this;
        }

        public WandStatBuilder setWandName(String wandName) {
            this.wandName = wandName;
            return this;
        }

        public int getCapacity() {
            return capacity;
        }

        public int getMaxMana() {
            return maxMana;
        }

        public int getMana() {
            return mana;
        }

        public float getCastDelay() {
            return castDelay;
        }

        public float getRechargeSpeed() {
            return rechargeSpeed;
        }

        public int getManaChargeSpeed() {
            return manaChargeSpeed;
        }

        public int getCastableSize() {
            return castableSize;
        }

        public int getCurrentIdx() {
            return currentIdx;
        }

        public int getGem() {
            return gem;
        }

        public int getWand() {
            return wand;
        }

        public CompoundTag getInventory() {
            return inventory;
        }

        public String getWandName() {
            return wandName;
        }

        public int getWandCooldown() {
            return wandCooldown;
        }

        public WandStatBuilder setWandCooldown(int wandCooldown) {
            this.wandCooldown = wandCooldown;
            return this;
        }

        public boolean isFromCastDelay() {
            return fromCastDelay;
        }

        public WandStatBuilder setFromCastDelay(boolean fromCastDelay) {
            this.fromCastDelay = fromCastDelay;
            return this;
        }


        public int getMaxCooldown() {
            return maxCooldown;
        }

        public WandStatBuilder setMaxCooldown(int maxCooldown) {
            this.maxCooldown = maxCooldown;
            return this;
        }

        public WandStat build() {
            return new WandStat(
                    this.capacity,
                    this.maxMana,
                    this.mana,
                    this.castDelay,
                    this.rechargeSpeed,
                    this.manaChargeSpeed,
                    this.castableSize,
                    this.currentIdx,
                    this.gem,
                    this.wand,
                    this.inventory,
                    this.wandName,
                    this.wandCooldown,
                    this.maxCooldown,
                    this.fromCastDelay
            );
        }
    }

    public static class WandStat {
        final int capacity;
        final int maxMana;
        final int mana;
        final float castDelay;
        final float rechargeSpeed;
        final int manaChargeSpeed;
        final int castableSize;
        final int currentIdx;
        final int gem;
        final int wand;
        final int wandCooldown;
        final CompoundTag inventory;
        final String name;
        final boolean fromCastDelay;
        final int maxCooldown;


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            WandStat stat = (WandStat) o;
            return capacity == stat.capacity && maxMana == stat.maxMana && mana == stat.mana && Float.compare(castDelay, stat.castDelay) == 0 && Float.compare(rechargeSpeed, stat.rechargeSpeed) == 0 && manaChargeSpeed == stat.manaChargeSpeed && castableSize == stat.castableSize && currentIdx == stat.currentIdx && gem == stat.gem && wand == stat.wand && wandCooldown == stat.wandCooldown && fromCastDelay == stat.fromCastDelay && Objects.equals(inventory, stat.inventory) && Objects.equals(name, stat.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(capacity, maxMana, mana, castDelay, rechargeSpeed, manaChargeSpeed, castableSize, currentIdx, gem, wand, wandCooldown, inventory, name, fromCastDelay);
        }

        public WandStat(Integer capacity, Integer maxMana, Integer mana, Float castDelay, Float rechargeSpeed, Integer manaChargeSpeed, Integer castableSize, Integer currentIdx, Integer gem, Integer wand, CompoundTag inventory, String name, int wandCooldown, int maxCooldown, boolean fromCastDelay) {
            this.capacity = capacity;
            this.maxMana = maxMana;
            this.mana = mana;
            this.castDelay = castDelay;
            this.rechargeSpeed = rechargeSpeed;
            this.manaChargeSpeed = manaChargeSpeed;
            this.castableSize = castableSize;
            this.currentIdx = currentIdx;
            this.gem = gem;
            this.wand = wand;
            this.inventory = inventory;
            this.name = name;
            this.wandCooldown = wandCooldown;
            this.maxCooldown = maxCooldown;
            this.fromCastDelay = fromCastDelay;
        }

        public int getCapacity() {
            return capacity;
        }


        public int getMaxMana() {
            return maxMana;
        }

        public int getMana() {
            return mana;
        }

        public float getCastDelay() {
            return castDelay;
        }

        public float getRechargeSpeed() {
            return rechargeSpeed;
        }


        public int getManaChargeSpeed() {
            return manaChargeSpeed;
        }

        public int getCastableSize() {
            return castableSize;
        }

        public int getCurrentIdx() {
            return currentIdx;
        }

        public int getGem() {
            return gem;
        }


        public int getWand() {
            return wand;
        }

        public CompoundTag getInventory() {
            return inventory;
        }

        public String getName() {
            return name;
        }

        public int getWandCooldown() {
            return wandCooldown;
        }

        public int getMaxCooldown() {
            return maxCooldown;
        }

        public boolean isFromCastDelay() {
            return fromCastDelay;
        }
    }

    public static Codec<WandStat> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.INT.fieldOf("Capacity").forGetter(wandStat -> wandStat.capacity),
                    Codec.INT.fieldOf("MaxMana").forGetter(wandStat -> wandStat.maxMana),
                    Codec.INT.fieldOf("Mana").forGetter(wandStat -> wandStat.mana),
                    Codec.FLOAT.fieldOf("CastDelay").forGetter(wandStat -> wandStat.castDelay),
                    Codec.FLOAT.fieldOf("RechargeSpeed").forGetter(wandStat -> wandStat.rechargeSpeed),
                    Codec.INT.fieldOf("ManaChargeSpeed").forGetter(wandStat -> wandStat.manaChargeSpeed),
                    Codec.INT.fieldOf("CastableSize").forGetter(wandStat -> wandStat.castableSize),
                    Codec.INT.fieldOf("CurrentIdx").forGetter(wandStat -> wandStat.currentIdx),
                    Codec.INT.fieldOf("Gem").forGetter(wandStat -> wandStat.gem),
                    Codec.INT.fieldOf("Wand").forGetter(wandStat -> wandStat.wand),
                    CompoundTag.CODEC.fieldOf("Inventory").forGetter(wandStat -> wandStat.inventory),
                    ExtraCodecs.NON_EMPTY_STRING.fieldOf("WandName").forGetter(wandStat -> wandStat.name),
                    Codec.INT.fieldOf("Cooldown").forGetter(wandStat -> wandStat.wandCooldown),
                    Codec.INT.fieldOf("MaxCooldown").forGetter(wandStat -> wandStat.maxCooldown),
                    Codec.BOOL.fieldOf("FromCastDelay").forGetter(wandStat -> wandStat.fromCastDelay)
            ).apply(instance, WandStat::new));

    public static StreamCodec<RegistryFriendlyByteBuf, WandStat> STREAM_CODEC = new StreamCodec<RegistryFriendlyByteBuf, WandStat>() {
        @Override
        public WandStat decode(RegistryFriendlyByteBuf pBuffer) {

            int capacity = pBuffer.readInt();
            int maxMana = pBuffer.readInt();
            int mana = pBuffer.readInt();
            float castDelay = pBuffer.readFloat();
            float rechargeSpeed = pBuffer.readFloat();
            int manaChargeSpeed = pBuffer.readInt();
            int castableSize = pBuffer.readInt();
            int currentIdx = pBuffer.readInt();
            int gem = pBuffer.readInt();
            int wand = pBuffer.readInt();
            CompoundTag inventory = pBuffer.readNbt();
            String name = pBuffer.readUtf();
            int wandCooldown = pBuffer.readInt();
            int maxCooldown = pBuffer.readInt();
            boolean fromCastDelay = pBuffer.readBoolean();

            WandStat stat = new WandStat(
                    capacity,
                    maxMana,
                    mana,
                    castDelay,
                    rechargeSpeed,
                    manaChargeSpeed,
                    castableSize,
                    currentIdx,
                    gem,
                    wand,
                    inventory,
                    name,
                    wandCooldown,
                    maxCooldown,
                    fromCastDelay
            );
            return stat;
        }

        @Override
        public void encode(RegistryFriendlyByteBuf pBuffer, WandStat pValue) {

            pBuffer.writeInt(pValue.getCapacity());
            pBuffer.writeInt(pValue.getMaxMana());
            pBuffer.writeInt(pValue.getMana());
            pBuffer.writeFloat(pValue.getCastDelay());
            pBuffer.writeFloat(pValue.getRechargeSpeed());
            pBuffer.writeInt(pValue.getManaChargeSpeed());
            pBuffer.writeInt(pValue.getCastableSize());
            pBuffer.writeInt(pValue.getCurrentIdx());
            pBuffer.writeInt(pValue.getGem());
            pBuffer.writeInt(pValue.getWand());
            pBuffer.writeNbt(pValue.getInventory());
            pBuffer.writeUtf(pValue.getName());
            pBuffer.writeInt(pValue.getWandCooldown());
            pBuffer.writeInt(pValue.getMaxCooldown());
            pBuffer.writeBoolean(pValue.isFromCastDelay());
        }
    };
}
