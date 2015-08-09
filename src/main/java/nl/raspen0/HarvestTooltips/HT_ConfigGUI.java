package nl.raspen0.HarvestTooltips;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.client.config.DummyConfigElement;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.GuiConfigEntries;
import cpw.mods.fml.client.config.IConfigElement;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;

public class HT_ConfigGUI extends GuiConfig {
	public HT_ConfigGUI(GuiScreen guiScreen) {

		super(guiScreen, getConfigElements(), HarvestTooltips.MODID, false, false,
				GuiConfig.getAbridgedConfigPath("HarvestTooltips Configuration"));

	}

	private static List<IConfigElement> getConfigElements() {
		List<IConfigElement> configElements = new ArrayList<IConfigElement>();
		configElements.add(new DummyConfigElement.DummyCategoryElement("General", "harvesttooltips.config.general",
				GeneralSettingsEntry.class));

		configElements.add(new DummyConfigElement.DummyCategoryElement("Level Names",
				"harvesttooltips.config.levelnames", LevelNameEntry.class));

		configElements.add(new DummyConfigElement.DummyCategoryElement("Level Colors",
				"harvesttooltips.config.levelcolors", LevelColorsEntry.class));
		return configElements;
	}

	//General
	public static class GeneralSettingsEntry extends GuiConfigEntries.CategoryEntry {

		public GeneralSettingsEntry(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement prop) {
			super(owningScreen, owningEntryList, prop);
		}

		@Override
		protected GuiScreen buildChildScreen() {
			return new GuiConfig(this.owningScreen,
					new ConfigElement(HarvestTooltips.configFile.getCategory(HarvestTooltips.CATEGORY_MISC))
							.getChildElements(),
					this.owningScreen.modID, HarvestTooltips.CATEGORY_MISC, false, false,
					GuiConfig.getAbridgedConfigPath("General Configuration"));
		}
	}

	//Level Names
	public static class LevelNameEntry extends GuiConfigEntries.CategoryEntry {

		public LevelNameEntry(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement prop) {
			super(owningScreen, owningEntryList, prop);
		}

		@Override
		protected GuiScreen buildChildScreen() {
			return new GuiConfig(this.owningScreen,
					new ConfigElement(HarvestTooltips.configFile.getCategory(HarvestTooltips.CATEGORY_NAMES))
							.getChildElements(),
					this.owningScreen.modID, HarvestTooltips.CATEGORY_NAMES, false, false,
					GuiConfig.getAbridgedConfigPath("Level Name Configuration"));
		}
	}

	//Level Colors
	public static class LevelColorsEntry extends GuiConfigEntries.CategoryEntry {

		public LevelColorsEntry(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement prop) {
			super(owningScreen, owningEntryList, prop);
		}

		@Override
		protected GuiScreen buildChildScreen() {
			return new GuiConfig(this.owningScreen,
					new ConfigElement(HarvestTooltips.configFile.getCategory(HarvestTooltips.CATEGORY_COLORS))
							.getChildElements(),
					this.owningScreen.modID, HarvestTooltips.CATEGORY_COLORS, false, false,
					GuiConfig.getAbridgedConfigPath("Level Colors Configuration"));
		}
	}
}