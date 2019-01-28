package com.stdio2016.icehard.blocks;

import com.stdio2016.icehard.IceHardMod;
import net.minecraft.block.BlockLog;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

/**
 * Created by User on 2019/1/27.
 */
public class BlockIceHardLog extends BlockLog implements IBlockIceHard {
    public ItemBlock item;
    public BlockIceHardLog(String name) {
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        this.setCreativeTab(IceHardMod.ourTab);
        this.setSoundType(SoundType.GLASS);
        this.setDefaultState(this.blockState.getBaseState().withProperty(LOG_AXIS, EnumAxis.Y));
        this.item = new ItemBlock(this);
        this.item.setRegistryName(name).setUnlocalizedName(name);
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
        return state;
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        switch (state.getValue(LOG_AXIS)) {
            case Y: return 0;
            case X: return 4;
            case Z: return 8;
            case NONE: return 12;
        }
        return 0;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, LOG_AXIS);
    }

    public Item itemBlock() {
        return item;
    }
}
