package net.frozenblock.mctweaks.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Category;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.TransitiveObject;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.frozenblock.mctweaks.MCTweaks;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

@Config(name = MCTweaks.MOD_ID)
public class MCTweaksConfig extends PartitioningSerializer.GlobalData {
	@Category("general")
	@TransitiveObject
	public GeneralConfig general = new GeneralConfig();

	public static MCTweaksConfig get() {
		if (!MCTweaks.areConfigsInit) {
			AutoConfig.register(MCTweaksConfig.class, PartitioningSerializer.wrap(GsonConfigSerializer::new));
			MCTweaks.areConfigsInit = true;
		}
		return AutoConfig.getConfigHolder(MCTweaksConfig.class).getConfig();
	}

	public static Component text(String key) {
		return Component.translatable("option." + MCTweaks.MOD_ID + "." + key);
	}

	public static Component tooltip(String key) {
		return Component.translatable("tooltip." + MCTweaks.MOD_ID + "." + key);
	}

	@Environment(EnvType.CLIENT)
	public static Screen buildScreen(Screen parent) {
		var configBuilder = ConfigBuilder.create().setParentScreen(parent).setTitle(text("component.title"));
		configBuilder.setSavingRunnable(() -> AutoConfig.getConfigHolder(MCTweaksConfig.class).save());
		var general = configBuilder.getOrCreateCategory(text("general"));
		ConfigEntryBuilder entryBuilder = configBuilder.entryBuilder();
		GeneralConfig.setupEntries(general, entryBuilder);
		return configBuilder.build();
	}
}
