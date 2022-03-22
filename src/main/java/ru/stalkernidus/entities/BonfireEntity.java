package ru.stalkernidus.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.*;

import static ru.stalkernidus.setup.Registration.BONFIRE_ENTITY;

public class BonfireEntity extends BlockEntity {
    private String name="";
    private BlockPos tpPos;

    private static List<BonfireEntity> bonfires = new ArrayList<>();
    private static BonfireEntity last;

    public BonfireEntity(BlockPos pos, BlockState state) {
        super(BONFIRE_ENTITY.get(), pos, state);
        last = this;
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
        boolean check = true;
        if (bonfires.size()!=0){
            for (BonfireEntity e : bonfires){
                if (e.getLongPos().equals(this.getLongPos())) {
                    check=false;
                    break;
                }
            }
        }
        if (check) bonfires.add(this);
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

    public static BonfireEntity getBonfireWithPos(BlockPos pos){
        for (BonfireEntity e : bonfires){
            if (e.getLongPos().equals(pos.asLong())) return e;
        }
        return null;
    }

    public Long getLongPos(){
        return this.getBlockPos().asLong();
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

    public static List<BonfireEntity> getBonfires() {
        return bonfires;
    }

    public static void setBonfires(List<BonfireEntity> bonfires) {
        BonfireEntity.bonfires = bonfires;
    }

    public static BonfireEntity getLast() {
        return last;
    }

    public static void setLast(BonfireEntity last) {
        BonfireEntity.last = last;
    }
}
