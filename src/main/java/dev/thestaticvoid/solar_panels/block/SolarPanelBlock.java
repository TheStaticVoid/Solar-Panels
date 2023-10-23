package dev.thestaticvoid.solar_panels.block;

import dev.thestaticvoid.solar_panels.block.entity.SolarPanelBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class SolarPanelBlock extends Block implements EntityBlock {

    public static final BooleanProperty ACTIVE = BooleanProperty.create("active");
    public long capacity;
    public long transferSpeed;
    public long generationRate;

    public SolarPanelBlock(Properties properties, long capacity, long transferSpeed, long generationRate) {
        super(properties);

        this.registerDefaultState(defaultBlockState().setValue(ACTIVE, false));
        this.capacity = capacity;
        this.transferSpeed = transferSpeed;
        this.generationRate = generationRate;
    }

    public void setActive(Boolean active, Level level, BlockPos pos) {
        level.setBlockAndUpdate(pos, level.getBlockState(pos).setValue(ACTIVE, active));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new SolarPanelBlockEntity(blockPos, blockState);
    }

    @Override
    public RenderShape getRenderShape(BlockState blockState) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType) {
        return (world1, pos, state1, blockEntity) -> {
            if (blockEntity instanceof BlockEntityTicker) {
                ((BlockEntityTicker) blockEntity).tick(world1, pos, state1, blockEntity);
            }
        };
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ACTIVE);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player,
                                 InteractionHand interactionHand, BlockHitResult blockHitResult) {

        SolarPanelBlockEntity be = (SolarPanelBlockEntity) level.getBlockEntity(blockPos);
        player.sendSystemMessage(Component.literal(be.getStoredAmount()+""));

        return super.use(blockState, level, blockPos, player, interactionHand, blockHitResult);
    }
}
