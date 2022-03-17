package com.student.datagen;

import com.student.setup.Registration;
import com.student.dsbonfire.TestMod;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class Items extends ItemModelProvider {

    public Items(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, TestMod.mod_Id, existingFileHelper);
    }

    @Override
    protected void registerModels() {
//        singleTexture(  Registration.MySword.get().getRegistryName().getPath(),
//                        new ResourceLocation("item/handheld"),
//                        "layer0",
//                        new ResourceLocation(TestMod.mod_Id, "item/mini_sword"));
        getBuilder(Registration.Coiled_Sword.get().getRegistryName().getPath())
                .parent(getExistingFile(mcLoc("item/handheld")))
                .texture("layer0", "item/mini_sword");
    }
}
