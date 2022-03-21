package ru.stalkernidus.datagen;

import ru.stalkernidus.setup.Registration;
import ru.stalkernidus.setup.BonfireMod;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class Items extends ItemModelProvider {

    public Items(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, BonfireMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
//        singleTexture(  Registration.MySword.get().getRegistryName().getPath(),
//                        new ResourceLocation("item/handheld"),
//                        "layer0",
//                        new ResourceLocation(TestMod.mod_Id, "item/mini_sword"));
        getBuilder(Registration.COILED_SWORD.get().getRegistryName().getPath())
                .parent(getExistingFile(mcLoc("item/handheld")))
                .texture("layer0", "item/mini_sword");
    }
}
