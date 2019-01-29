package com.stdio2016.icehard.blocks;

import com.stdio2016.icehard.IceHardMod;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by User on 2019/1/28.
 */
public class BlockIceHardHardLog extends BlockIceHardLog {
    public ItemBlock item;
    public static PropertyInteger HAS_TAP = PropertyInteger.create("has_tap", 0, 1);

    public BlockIceHardHardLog(String name) {
        super(name);
        this.setDefaultState(this.blockState.getBaseState().withProperty(HAS_TAP, 1).withProperty(LOG_AXIS, EnumAxis.Y));
        this.setTickRandomly(true);
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rnd) {
        super.updateTick(world, pos, state, rnd);
        if (!world.isRemote && state.getValue(HAS_TAP) == 0 && rnd.nextInt(7) == 0) {
            // add tap
            world.setBlockState(pos, state.withProperty(HAS_TAP, 1));
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        IBlockState state = this.getDefaultState();
        switch (meta & 12) {
            case 0: state = state.withProperty(LOG_AXIS, EnumAxis.Y); break;
            case 4: state = state.withProperty(LOG_AXIS, EnumAxis.X); break;
            case 8: state = state.withProperty(LOG_AXIS, EnumAxis.Z); break;
            case 12: state = state.withProperty(LOG_AXIS, EnumAxis.NONE); break;
        }
        state = state.withProperty(HAS_TAP, meta&1);
        return state;
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int i = state.getValue(HAS_TAP);
        switch (state.getValue(LOG_AXIS)) {
            case Y: i |= 0; break;
            case X: i |= 4; break;
            case Z: i |= 8; break;
            case NONE: i |= 12; break;
        }
        return i;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, LOG_AXIS, HAS_TAP);
    }

}
