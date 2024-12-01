package com.hakimen.wandrous.common.entity.static_spell;

import com.hakimen.wandrous.common.spell.SpellStack;
import com.hakimen.wandrous.common.utils.CastingUtils;
import com.hakimen.wandrous.common.utils.data.Node;
import com.mojang.datafixers.util.Pair;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TriggeringGlyphEntity extends Entity {

    List<ItemStack> effects;
    public static final EntityDataAccessor<Integer> MAX_TICK_TIME = SynchedEntityData.defineId(TriggeringGlyphEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Float> RADIUS = SynchedEntityData.defineId(TriggeringGlyphEntity.class, EntityDataSerializers.FLOAT);
    public static final EntityDataAccessor<Optional<UUID>> OWNER = SynchedEntityData.defineId(TriggeringGlyphEntity.class, EntityDataSerializers.OPTIONAL_UUID);
    public int maxTickTime;
    public float radius;
    public UUID owner;
    public ItemStack wand;

    public TriggeringGlyphEntity(EntityType<?> pEntityType, Level pLevel, List<SpellStack> spells, int maxTickTime, float radius, UUID owner, ItemStack wand) {
        super(pEntityType, pLevel);
        this.effects = spells.stream().map(SpellStack::getReferenceStack).toList();
        this.maxTickTime = maxTickTime;
        this.radius = radius;
        this.owner = owner;
        this.wand = wand;
        this.entityData.set(MAX_TICK_TIME, maxTickTime);
        this.entityData.set(RADIUS, radius);
        this.entityData.set(OWNER, Optional.ofNullable(owner));
    }


    public TriggeringGlyphEntity(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        effects = List.of();
        maxTickTime = Integer.MAX_VALUE;
    }

    @Override
    public void tick() {
        super.tick();

        if (tickCount % 2 == 0 && !this.level().isClientSide) {
            ServerLevel serverLevel = (ServerLevel) this.level();
            Entity owner = serverLevel.getEntity(this.owner);
            AABB trigger = AABB.ofSize(this.getPosition(0), 1, 1, 1).inflate(radius, 1, radius);
            List<LivingEntity> didTrigger = serverLevel.getEntitiesOfClass(LivingEntity.class, trigger).stream()
                    .filter(livingEntity -> livingEntity != owner).toList();

            if (!didTrigger.isEmpty()) {
                for (int i = 0; i < didTrigger.size(); i++) {
                    LivingEntity entity = didTrigger.get(i);
                    if (entity.distanceTo(this) <= radius) {
                        CastingUtils utils = new CastingUtils();
                        List<SpellStack> stackList = effects.stream().map(SpellStack::fromItemStack).toList();
                        Node<SpellStack> cast = utils.makeCastingTree(stackList, stackList);

                        if(cast.getData() != null) {
                            CastingUtils.castSpells(owner, wand, level(), didTrigger.get(i).getPosition(0), cast, context ->
                                    context.setCastPositionModified(true)
                                            .getHit().add(entity)
                            );
                        }
                        discard();
                        break;
                    }
                }

            }
        }

        if (tickCount >= maxTickTime) {
            discard();
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
        pBuilder.define(MAX_TICK_TIME, maxTickTime);
        pBuilder.define(RADIUS, radius);
        pBuilder.define(OWNER, Optional.ofNullable(owner));
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {
        List<ItemStack> spellEffects = new ArrayList<>();
        for (Tag t : pCompound.getList("effects", Tag.TAG_STRING)) {
            Pair<ItemStack, Tag> tag = ItemStack.SIMPLE_ITEM_CODEC.decode(NbtOps.INSTANCE, t).getOrThrow();
            spellEffects.add(tag.getFirst());
        }
        this.effects = spellEffects;
        this.owner = pCompound.getUUID("owner");
        this.wand = ItemStack.SIMPLE_ITEM_CODEC.decode(NbtOps.INSTANCE, pCompound.get("wand")).getOrThrow().getFirst();
        this.maxTickTime = pCompound.getInt("maxTickTime");
        this.radius = pCompound.getFloat("radius");

        this.entityData.set(MAX_TICK_TIME, maxTickTime);
        this.entityData.set(RADIUS, radius);
        this.entityData.set(OWNER, Optional.ofNullable(owner));
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {
        ListTag tag = new ListTag();
        for (ItemStack effect : effects) {
            tag.add(ItemStack.SIMPLE_ITEM_CODEC.encodeStart(NbtOps.INSTANCE, effect).getOrThrow());
        }
        pCompound.put("effects", tag);
        pCompound.putUUID("owner", owner);
        pCompound.put("wand", ItemStack.SIMPLE_ITEM_CODEC.encodeStart(NbtOps.INSTANCE, wand).getOrThrow());
        pCompound.putInt("maxTickTime", maxTickTime);
        pCompound.putFloat("radius", radius);
    }
}
