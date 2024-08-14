package com.hakimen.wandrous.client.ber;

import com.hakimen.wandrous.common.block_entity.ArcaneInscriberBlockEntity;
import com.hakimen.wandrous.common.particle.ArcaneKnowledgeParticle;
import com.hakimen.wandrous.common.registers.BlockEntityRegister;
import com.hakimen.wandrous.common.utils.GlyphUtils;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class ArcaneInscriberRenderer implements BlockEntityRenderer<ArcaneInscriberBlockEntity> {

    BlockEntityRendererProvider.Context context;

    public ArcaneInscriberRenderer(BlockEntityRendererProvider.Context context) {
        this.context = context;
    }

    @Override
    public void render(ArcaneInscriberBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        ItemStack stack = pBlockEntity.getInventory().getStackInSlot(0);
        pPoseStack.pushPose();

        pPoseStack.translate(0.5, 0.55 - Math.cos((pBlockEntity.getLevel().getGameTime() + pPartialTick) / 20f) / 20f, 0.5);
        pPoseStack.scale(0.65f, 0.65f, 0.65f);
        pPoseStack.rotateAround(new Quaternionf().rotateXYZ(
                        (float) (Math.cos((pBlockEntity.getLevel().getGameTime() + pPartialTick) / 10f) / 20f),
                        0,
                        (float) (Math.sin((pBlockEntity.getLevel().getGameTime() + pPartialTick) / 10f) / 20f)
                ),
                0, 0, 0);
        pPoseStack.mulPose(Axis.XP.rotationDegrees(90));


        pPoseStack.mulPose(Axis.ZP.rotationDegrees(-90));

        Vec3 thingToMe = pBlockEntity.getBlockPos().getCenter()
                .subtract(
                        Minecraft.getInstance().player.getPosition(pPartialTick)
                );

        pPoseStack.mulPose(Axis.ZP.rotation((float) Math.atan2(thingToMe.z, thingToMe.x)));


        context.getItemRenderer().renderStatic(stack,
                ItemDisplayContext.FIXED,
                0xff00e0,
                pPackedOverlay,
                pPoseStack,
                pBuffer,
                pBlockEntity.getLevel(),
                0);

        pPoseStack.popPose();

        BlockPos pPos = pBlockEntity.getBlockPos();
        Level pLevel = pBlockEntity.getLevel();


        if(pLevel.getRandom().nextFloat() < 0.5f && !pBlockEntity.getInventory().getStackInSlot(0).isEmpty() && !Minecraft.getInstance().isPaused()){
            pLevel.addParticle(ParticleTypes.ENCHANT,
                    0.5 + pPos.getX() + 0.5 + (pLevel.getRandom().triangle(-0.5f,0.5f)),
                    pPos.getY() + 0.45,
                    0.5 + pPos.getZ() + 0.5 + (pLevel.getRandom().triangle(-0.5f,0.5f)),
                    0,
                    0.1,
                    0
            );
        }

        if (pBlockEntity.getProgress() != 0 && !pBlockEntity.getInventory().getStackInSlot(0).isEmpty()) {
            for (BlockPos offset : pBlockEntity.getOffsets()) {
                BlockPos pos = pPos.offset(offset);
                pLevel.getBlockEntity(pos, BlockEntityRegister.GLYPH_PROJECTOR_ENTITY.get()).ifPresent(
                        glyphProjectorBlockEntity -> {
                            if (!glyphProjectorBlockEntity.getInventory().getStackInSlot(0).isEmpty()) {
                                float[] color = GlyphUtils.getColorFromGlyph(glyphProjectorBlockEntity.getInventory().getStackInSlot(0));
                                if(pBlockEntity.getProgress() < 4){
                                    pLevel.addParticle(new DustParticleOptions(new Vector3f(color), 2),
                                            glyphProjectorBlockEntity.getBlockPos().getX() + 0.5 + 1 + (pLevel.getRandom().triangle(-1f,1f)),
                                            glyphProjectorBlockEntity.getBlockPos().getY() + 1.1,
                                            glyphProjectorBlockEntity.getBlockPos().getZ() + 0.5 + 1 + (pLevel.getRandom().triangle(-1f,1f)),
                                            0,0,0
                                    );
                                }
                                if(glyphProjectorBlockEntity.getLevel().getRandom().nextFloat() < 0.25f && !Minecraft.getInstance().isPaused()  ) {
                                    pLevel.addParticle(new ArcaneKnowledgeParticle.ArcaneKnowledgeParticleOptions(color[0], color[1], color[2]),
                                            glyphProjectorBlockEntity.getBlockPos().getX() + 0.5,
                                            glyphProjectorBlockEntity.getBlockPos().getY() + 2.25,
                                            glyphProjectorBlockEntity.getBlockPos().getZ() + 0.5,
                                            (pPos.getX() - glyphProjectorBlockEntity.getBlockPos().getX()) / 2.5f,
                                            (pPos.getY() - glyphProjectorBlockEntity.getBlockPos().getY() - 3f) / 2.5f,
                                            (pPos.getZ() - glyphProjectorBlockEntity.getBlockPos().getZ()) / 2.5f
                                    );
                                }

                            }
                        }
                );
            }
        }
    }
}
