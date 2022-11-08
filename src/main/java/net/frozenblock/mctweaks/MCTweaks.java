package net.frozenblock.mctweaks;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.frozenblock.mctweaks.config.MCTweaksConfig;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;

public class MCTweaks implements ModInitializer {
	public static final String MOD_ID = "mctweaks";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static boolean DEV_LOGGING = false;
	/**
	 * Used for features that may be unstable and crash in public builds.
	 * <p>
	 * It's smart to use this for at least registries.
	 */
	public static boolean UNSTABLE_LOGGING = FabricLoader.getInstance().isDevelopmentEnvironment();

	public static boolean areConfigsInit = false;

	@Override
	public void onInitialize() {
		startMeasuring(this);

		MCTweaksConfig.get();

		stopMeasuring(this);
	}

	//MEASURING
	public static Map<Object, Long> instantMap = new HashMap<>();

	public static void startMeasuring(Object object) {
		long started = System.nanoTime();
		String name = object.getClass().getName();
		LOGGER.error("Started measuring {}", name.substring(name.lastIndexOf(".") + 1));
		instantMap.put(object, started);
	}

	public static void stopMeasuring(Object object) {
		if (instantMap.containsKey(object)) {
			String name = object.getClass().getName();
			LOGGER.error("{} took {} nanoseconds", name.substring(name.lastIndexOf(".") + 1), System.nanoTime() - instantMap.get(object));
			instantMap.remove(object);
		}
	}

	public static ResourceLocation id(String path) {
		return new ResourceLocation(MOD_ID, path);
	}

	public static ResourceLocation vanillaId(String path) {
		return new ResourceLocation("minecraft", path);
	}

	public static String string(String path) {
		return id(path).toString();
	}
}
