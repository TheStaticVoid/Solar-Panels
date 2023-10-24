package dev.thestaticvoid.solar_panels.block;

import dev.thestaticvoid.solar_panels.block.entity.SolarPanelBlockEntity;
import net.minecraft.core.BlockPos;
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
import org.jetbrains.annotations.Nullable;

public class SolarPanelBlock extends Block implements EntityBlock {
    public static final BooleanProperty ACTIVE = BooleanProperty.create("active");
    public long capacity;
    public int maxTransfer;
    public int generationRate;

    public SolarPanelBlock(Properties properties, int generationRate, int maxTransfer, long capacity) {
        super(properties);

        this.registerDefaultState(this.defaultBlockState().setValue(ACTIVE, false));
        this.generationRate = generationRate;
        this.maxTransfer = maxTransfer;
        this.capacity = capacity;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ACTIVE);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new SolarPanelBlockEntity(pos, state);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return (world1, pos, state1, blockEntity) -> {
            if (blockEntity instanceof BlockEntityTicker) {
                ((BlockEntityTicker) blockEntity).tick(world1, pos, state1, blockEntity);
            }
        };
    }
}
