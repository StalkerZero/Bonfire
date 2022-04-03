package ru.stalkernidus.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.*;

import static ru.stalkernidus.setup.Registration.BONFIRE_ENTITY;

public class BonfireEntity extends BlockEntity {
    private String name = "";
    private String dimension = "";
    private BlockPos tpPos;

    private static List<BonfireEntity> over = new ArrayList<>();
    private static List<BonfireEntity> end = new ArrayList<>();
    private static List<BonfireEntity> nether = new ArrayList<>();

    public BonfireEntity(BlockPos pos, BlockState state) {
        super(BONFIRE_ENTITY.get(), pos, state);
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        getBonfires().remove(this);
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putString("name", this.name);
        tag.putLong("tpPos", this.tpPos.asLong());
        tag.putString("dimension", this.dimension);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.name = tag.getString("name");
        this.tpPos = BlockPos.of(tag.getLong("tpPos"));
        this.dimension = tag.getString("dimension");
        boolean check = true;
        List<BonfireEntity> bonfires = this.getBonfires();
        if (bonfires.size()!=0) {
            for (BonfireEntity e : bonfires) {
                if (e.getLongPos().equals(this.getLongPos())) {
                    check = false;
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
                ", dimension='" + dimension + '\'' +
                ", tpPos=" + tpPos +
                ", level=" + level +
                ", worldPosition=" + worldPosition +
                ", remove=" + remove +
                '}';
    }

    public Long getLongPos(){
        return this.getBlockPos().asLong();
    }

    public List<BonfireEntity> getBonfires(){
        switch (this.getDimension()) {
            case "overworld" -> {
                return getOver();
            }
            case "the_end" -> {
                return getEnd();
            }
            case "the_nether" -> {
                return getNether();
            }
            default -> {
                System.out.println("ERROR: "+this.toString());
                return new ArrayList<>();
            }
        }
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

    public static List<BonfireEntity> getOver() {
        return over;
    }

    public static void setOver(List<BonfireEntity> over) {
        BonfireEntity.over = over;
    }

    public static List<BonfireEntity> getEnd() {
        return end;
    }

    public static void setEnd(List<BonfireEntity> end) {
        BonfireEntity.end = end;
    }

    public static List<BonfireEntity> getNether() {
        return nether;
    }

    public static void setNether(List<BonfireEntity> nether) {
        BonfireEntity.nether = nether;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }
}
