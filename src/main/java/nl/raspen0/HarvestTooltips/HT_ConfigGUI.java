package nl.raspen0.HarvestTooltips;

import cpw.mods.fml.client.config.GuiConfig;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;

public class HT_ConfigGUI extends GuiConfig {
    public HT_ConfigGUI(GuiScreen parent) {
        super(parent,
                new ConfigElement(HarvestTooltips.configFile.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(),
                "HarvestTooltips", false, false, GuiConfig.getAbridgedConfigPath(HarvestTooltips.configFile.toString()));
    
        	       
        
       
    }
}