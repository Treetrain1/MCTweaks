package net.frozenblock.mctweaks.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.frozenblock.mctweaks.MCTweaks;

@Config(name = "general")
public class GeneralConfig implements ConfigData {

	public boolean screenShaking = true;

	@Environment(EnvType.CLIENT)
	static void setupEntries(ConfigCategory category, ConfigEntryBuilder entryBuilder) {
		var config = MCTweaksConfig.get().general;
		category.setBackground(MCTweaks.id("textures/config/general.png"));

	}

}
