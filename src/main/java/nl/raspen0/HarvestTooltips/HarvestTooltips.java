package nl.raspen0.HarvestTooltips;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemTool;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = HarvestTooltips.MODID, version = HarvestTooltips.VERSION, guiFactory = "nl.raspen0.HarvestTooltips.HT_GuiFactory", acceptableRemoteVersions = "*")
public class HarvestTooltips {
	public static final String MODID = "HarvestTooltips";
	public static final String VERSION = "1.2";
	public static Configuration configFile;
	public static boolean enableTooltips = true;

	public static final String CATEGORY_MISC = "general";
	public static final String CATEGORY_NAMES = "level names";
	public static final String CATEGORY_COLORS = "level colors";

	// Mining Level Names
	public static String Level0;
	public static String Level1;
	public static String Level2;
	public static String Level3;
	public static String Level4;
	public static String Level5;

	// Mining Level Colors
	public static String Color0;
	public static String Color1;
	public static String Color2;
	public static String Color3;
	public static String Color4;
	public static String Color5;

	@Mod.Instance("HarvestTooltips")
	public static HarvestTooltips instance;

	@SideOnly(Side.CLIENT)
	@EventHandler
	public void preinit(FMLPreInitializationEvent event) {
		configFile = new Configuration(event.getSuggestedConfigurationFile());
		syncConfig();
	}

	@SideOnly(Side.CLIENT)
	@EventHandler
	public void init(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new HarvestTooltips());
		FMLCommonHandler.instance().bus().register(instance);
	}

	public static void syncConfig() {
		enableTooltips = configFile.get(CATEGORY_MISC, "Enable Tooltips", true).getBoolean(true);

		Level0 = configFile.get(CATEGORY_NAMES, "Mining Level 0", "Stone").getString();
		Level1 = configFile.get(CATEGORY_NAMES, "Mining Level 1", "Iron").getString();
		Level2 = configFile.get(CATEGORY_NAMES, "Mining Level 2", "Redstone").getString();
		Level3 = configFile.get(CATEGORY_NAMES, "Mining Level 3", "Obsidian").getString();
		Level4 = configFile.get(CATEGORY_NAMES, "Mining Level 4", "Cobalt").getString();
		Level5 = configFile.get(CATEGORY_NAMES, "Mining Level 5", "Atlarus").getString();

		Color0 = configFile.get(CATEGORY_COLORS, "Mining Level 0", "GRAY").getString();
		Color1 = configFile.get(CATEGORY_COLORS, "Mining Level 1", "DARK_GRAY").getString();
		Color2 = configFile.get(CATEGORY_COLORS, "Mining Level 2", "RED").getString();
		Color3 = configFile.get(CATEGORY_COLORS, "Mining Level 3", "LIGHT_PURPLE").getString();
		Color4 = configFile.get(CATEGORY_COLORS, "Mining Level 4", "BLUE").getString();
		Color5 = configFile.get(CATEGORY_COLORS, "Mining Level 5", "YELLOW").getString();
		if (configFile.hasChanged())
			configFile.save();
	}

	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
		if (eventArgs.modID.equals("HarvestTooltips"))
			syncConfig();
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onItemToolTip(ItemTooltipEvent event) {
		if (event.entityPlayer == null)
			return;
		if (!(event.itemStack.getItem() instanceof ItemTool))
			return;
		if (!enableTooltips) {
			return;
		}

		int picklevel = event.itemStack.getItem().getHarvestLevel(event.itemStack, "pickaxe");
		int shovellevel = event.itemStack.getItem().getHarvestLevel(event.itemStack, "shovel");
		int axelevel = event.itemStack.getItem().getHarvestLevel(event.itemStack, "axe");

		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
			if (picklevel == 0 || shovellevel == 0 || axelevel == 0)
				event.toolTip.add("Mining Level: " + (EnumChatFormatting.getValueByName(Color0) + this.Level0));
			if (picklevel == 1 || shovellevel == 1 || axelevel == 1)
				event.toolTip.add("Mining Level: " + (EnumChatFormatting.getValueByName(Color1) + this.Level1));
			if (picklevel == 2 || shovellevel == 2 || axelevel == 2)
				event.toolTip.add("Mining Level: " + (EnumChatFormatting.getValueByName(Color2) + this.Level2));
			if (picklevel == 3 || shovellevel == 3 || axelevel == 3)
				event.toolTip.add("Mining Level: " + (EnumChatFormatting.getValueByName(Color3) + this.Level3));
			if (picklevel == 4 || shovellevel == 4 || axelevel == 4)
				event.toolTip.add("Mining Level: " + (EnumChatFormatting.getValueByName(Color4) + this.Level4));
			if (picklevel == 5 || shovellevel == 5 || axelevel == 5)
				event.toolTip.add("Mining Level: " + (EnumChatFormatting.getValueByName(Color5) + this.Level5));
			if (picklevel > 5)
				event.toolTip.add("Mining Level: " + picklevel);
			if (shovellevel > 5)
				event.toolTip.add("Mining Level: " + shovellevel);
			if (axelevel > 5)
				event.toolTip.add("Mining Level: " + axelevel);
		}
	}

}
