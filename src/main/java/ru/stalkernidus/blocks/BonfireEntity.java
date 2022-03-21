package ru.stalkernidus.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.*;

import static ru.stalkernidus.setup.Registration.BONFIRE_ENTITY;

public class BonfireEntity extends BlockEntity {
    private String name;
    private BlockPos tpPos;

    private static int counter = 0;
    private static Set<BonfireEntity> bonfires = new HashSet<>();
    private static BonfireEntity last;

    public BonfireEntity(BlockPos pos, BlockState state) {
        super(BONFIRE_ENTITY.get(), pos, state);
        bonfires.add(this);
        counter++;
        this.name="#"+counter;
        last=this;
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        bonfires.remove(this);
        if (bonfires.size()!=0) last = (BonfireEntity) bonfires.toArray()[bonfires.size()-1];
        else last = null;
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putString("name", this.name);
        tag.putLong("tpPos", this.tpPos.asLong());
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.name = tag.getString("name");
        this.tpPos = BlockPos.of(tag.getLong("tpPos"));
    }

    @Override
    public CompoundTag getUpdateTag() {
        return saveWithFullMetadata();
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        load(tag);
    }

    @Override
    public String toString() {
        return "BonfireEntity{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", worldPosition=" + worldPosition +
                ", remove=" + remove +
                '}';
    }

    public BlockPos getTpPos() {
        return tpPos;
    }

    public void setTpPos(BlockPos tpPos) {
        this.tpPos = tpPos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        BonfireEntity.counter = counter;
    }

    public static Set<BonfireEntity> getBonfires() {
        return bonfires;
    }

    public static void setBonfires(Set<BonfireEntity> bonfires) {
        BonfireEntity.bonfires = bonfires;
    }

    public static BonfireEntity getLast() {
        return last;
    }

    public static void setLast(BonfireEntity last) {
        BonfireEntity.last = last;
    }
}
