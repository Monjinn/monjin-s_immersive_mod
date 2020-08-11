package monjin.immersive_mod;

import java.util.ArrayList;
import java.util.List;

import monjin.immersive_mod.constants.BlockHardness;
import monjin.immersive_mod.constants.EnchantedBooks;
import monjin.immersive_mod.constants.FoodStats;
import monjin.immersive_mod.crafting.RecipeAdder;
import monjin.immersive_mod.crafting.RecipeRemover;
import monjin.immersive_mod.entity.EntitySettings;
import monjin.immersive_mod.generation.ChestGeneration;
import monjin.immersive_mod.generation.OreGeneration;
import monjin.immersive_mod.handler.AnvilHandler;
import monjin.immersive_mod.handler.BreakHandler;
import monjin.immersive_mod.handler.ChatHandler;
import monjin.immersive_mod.handler.ChestHandler;
import monjin.immersive_mod.handler.CombatHandler;
import monjin.immersive_mod.handler.DropHandler;
import monjin.immersive_mod.handler.EnchantingHandler;
import monjin.immersive_mod.handler.GenerationHandler;
import monjin.immersive_mod.handler.LoginHandler;
import monjin.immersive_mod.handler.PlayerArmorHandler;
import monjin.immersive_mod.handler.PlayerHealthHandler;
import monjin.immersive_mod.handler.SpawnHandler;
import monjin.immersive_mod.handler.VillageTradeHandler;
import monjin.immersive_mod.player.PlayerSettings;
import monjin.immersive_mod.proxy.CommonProxy;
import monjin.immersive_mod.tileentity.TileEntityIronSmelter;
import monjin.immersive_mod.tools.ItemImmersiveAxe;
import monjin.immersive_mod.tools.ItemImmersivePickaxe;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Mod's main source created by Monjin.
 * @author Monjin
 * @version 1.0
 */
@Mod(modid = ImmersiveMod.MODID, name = "Monjin's Immersive Mod", version = ImmersiveMod.VERSION)
public class ImmersiveMod {
	public static final String MODID = "immersivemod";
	public static final String VERSION = "1.0";
	
	@Instance(MODID)
	public static ImmersiveMod instance;
	
	@SidedProxy(clientSide = "monjin.immersive_mod.proxy.ClientProxy", serverSide = "monjin.immersive_mod.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	public static final int GUI_IMMERSIVE_INVENTORY = 11; 

	public static final int CONST_HELM = 0;
	public static final int CONST_CHEST = 1;
	public static final int CONST_LEGGINGS = 2;
	public static final int CONST_BOOTS = 3;
	
	/**
	 * Start: Defining tool materials: (name, harvestlevel, durability, efficiency, damage modifier, enchantability)
	 */
	
	
	public static final Item.ToolMaterial steelToolMaterial = EnumHelper
			.addToolMaterial("steelToolMaterial", 2, 1500, 7.0F, 2.0F, 30);

	public static final Item.ToolMaterial flintToolMaterial = EnumHelper
			.addToolMaterial("flintToolMaterial", 1, 50, 2.0F, 1.0F, 30);

	public static final Item.ToolMaterial durableShovelMaterial = EnumHelper
			.addToolMaterial("ExcellentShovelMaterial", 1, 1500, 6.0F, -1.0F, 30);

	public static final Item.ToolMaterial durableSwordMaterial = EnumHelper
		.addToolMaterial("ExcellentSwordMaterial", 1, 2000, 0.0F, 3.0F, 30);

	public static final ArmorMaterial SteelArmor = EnumHelper
			.addArmorMaterial("SteelArmor", 500, new int[] {4, 6, 4, 4}, 30);
	
	
	/**
	 * End: Defining tool materials
	 */

	/**
	 * Defining lists that are used in the mod code
	 */
	public static List<Item.ToolMaterial> materials = new ArrayList<Item.ToolMaterial>();
	public static List<String> toolTypes = new ArrayList<String>();
	
	public static Item steelIngot;
	public static Item flintPickaxe;
	public static Item flintHatchet;
	public static Item steelPickaxe;
	public static Item steelHoe;
	public static Item steelAxe;
	public static Item steelSword;
	public static Item steelShears;
	public static Item steelShovel;

	public static Block ironSmelterIdle;
	public static Block ironSmelterActive;

	public static List<Item> steelArmor = new ArrayList<Item>();
	
	public static final int guiIDIronSmelter = 0;
	
	
	public static final int HelmID = 0;
	public static final int ChestID = 1;
	public static final int LeggingsID = 2;
	public static final int BootsID = 3;

	/**
	 * Pre initialization method for the mod
	 * Defines all items and blocks used in the mod
	 * @param event
	 */
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		
		/**
		 * Sets custom hardness for blocks
		 */
		BlockHardness.setCustomHardness();
		
		/**
		 * Sets custom stack sizes for items
		 */
		//StackSizes.setCustomStackSizes();
		
		/**
		 * Adding tooltypes to list
		 */
		toolTypes.add("pickaxe");
		toolTypes.add("axe");
		toolTypes.add("hoe");
		toolTypes.add("shears");
		toolTypes.add("shovel");
		toolTypes.add("sword");
		
		/**
		 * Adding materials to a list
		 */
		materials.add(flintToolMaterial);
		
		/**
		 * Start:Adding tool items
		 */

		flintPickaxe = setItemProperties(new ItemImmersivePickaxe(flintToolMaterial, "Flint"), "itemFlintPickaxe", 1);
		flintHatchet = setItemProperties(new ItemImmersiveAxe(flintToolMaterial, "Flint"), "itemFlintHatchet", 1);
		
		/**
		 * End:Adding tool items
		 */
		
		/**
		 * Start:Adding ore blocks
		 */
		/**
		 * TODO: TO GET SHINY BLOCKS?
		 */
		//ores.add(setBlockProperties(new GoldOre(Material.iron), "blockGoldOre", 2));
		//ores.add(setBlockProperties(new DiamondOre(Material.iron), "blockDiamondOre", 2));
		
		/**
		 * Ore generation for the mod
		 */
		GameRegistry.registerWorldGenerator(new OreGeneration(), 0);
		
		ChestGeneration.setCustomChestGeneration();
	}
	
	/**
	 * Method for registering item to game
	 * @param item
	 */
	public void registerItem(Item item) {
		GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5));
	}

	/**
	 * Method for registering block to game
	 * @param block
	 */
	public Block registerBlock(Block block) {
		GameRegistry.registerBlock(block,
				block.getUnlocalizedName().substring(5));
		return block;
	}

	/**
	 * Method for setting block basic properties(textureName, etc.) and 
	 * register block automatically afterwards
	 * @param item
	 * @param name
	 * @param tab
	 */
	public Block setBlockProperties(Block item, String name, int tab) {
		item.setBlockName(name.substring(5)).setBlockTextureName(
				"immersivemod:" + name);
		// 1 = ore block, 2 = regular block
		switch (tab) {
		case 1:
			item.setCreativeTab(tabImmersiveModBlocks);
			break;
		case 2:
			item.setCreativeTab(tabImmersiveModOres);
			break;
		}
		registerBlock(item);
		return item;
	}

	/**
	 * Method for setting item basic properties(textureName etc.) and 
	 * register block automatically afterwards
	 * @param item
	 * @param name
	 * @param tab
	 * @return
	 */
	public Item setItemProperties(Item item, String name, int tab) {
		// tab: 1 = tools, 3=?, 4=?
		item.setUnlocalizedName(name.substring(4)).setTextureName(
				"immersivemod:" + name);
		switch (tab) {
		case 1:
			item.setCreativeTab(tabImmersiveModTools);
			break;
		case 2:
			item.setCreativeTab(tabImmersiveModItems);
			break;
		case 3:
			item.setCreativeTab(tabImmersiveModArmor);
			break;
		}
		registerItem(item);
		return item;
	}
	
	/**
	 * Mod initialization method
	 * @param event
	 */
	@EventHandler
	public void init(FMLInitializationEvent event) {		
		proxy.registerRenderers();
		EnchantedBooks.generateBooks();
		
		/* Registering handlers */
		MinecraftForge.EVENT_BUS.register(new DropHandler());
		MinecraftForge.EVENT_BUS.register(new PlayerHealthHandler());
		MinecraftForge.EVENT_BUS.register(new CombatHandler());		
		MinecraftForge.EVENT_BUS.register(new SpawnHandler());
		MinecraftForge.EVENT_BUS.register(new BreakHandler());
		MinecraftForge.EVENT_BUS.register(new LoginHandler());
		MinecraftForge.EVENT_BUS.register(new VillageTradeHandler());
		MinecraftForge.EVENT_BUS.register(new PlayerArmorHandler());
		MinecraftForge.EVENT_BUS.register(new FoodStats());
		MinecraftForge.EVENT_BUS.register(new ChatHandler());
		MinecraftForge.EVENT_BUS.register(new ChestHandler());
		MinecraftForge.EVENT_BUS.register(new AnvilHandler());
		MinecraftForge.EVENT_BUS.register(new EnchantingHandler());
		MinecraftForge.ORE_GEN_BUS.register(new GenerationHandler());
		//MinecraftForge.EVENT_BUS.register(new NewGuiHandler(Minecraft.getMinecraft()));
		
		
		//TODO Entity&Player settings
		MinecraftForge.EVENT_BUS.register(new EntitySettings());
		MinecraftForge.EVENT_BUS.register(new PlayerSettings());
		
		//NetworkRegistry.INSTANCE.registerGuiHandler(this, new ClientProxy());
		
		
		
		/* Removes vanilla tool recipes */
		RecipeRemover.removeRecipes();
		
		GameRegistry.registerTileEntity(TileEntityIronSmelter.class, "IronSmelter");
		
		RecipeAdder.AddCustomRecipes(toolTypes);
	}

	/**
	 * Post initialization method
	 * @param event
	 */
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}

	/**
	 * Creating tab for ores
	 */
	public CreativeTabs tabImmersiveModOres = new CreativeTabs(
			"tabImmersiveModOres") {
		@Override
		public Item getTabIconItem() {
			return new ItemStack(Blocks.diamond_ore).getItem();
		}
	};

	/**
	 * Creating tab for items
	 */
	public CreativeTabs tabImmersiveModItems = new CreativeTabs(
			"tabImmersiveModItems") {
		@Override
		public Item getTabIconItem() {
			return new ItemStack(Items.diamond).getItem();
		}
	};

	/**
	 * Creating tab for tools
	 */
	public CreativeTabs tabImmersiveModTools = new CreativeTabs(
			"tabImmersiveModTools") {

		@Override
		public Item getTabIconItem() {
			return new ItemStack(Items.wooden_pickaxe).getItem();
		}
	};

	/**
	 * Creating tab for blocks
	 */
	public CreativeTabs tabImmersiveModBlocks = new CreativeTabs(
			"tabImmersiveModBlocks") {

		@Override
		public Item getTabIconItem() {
			return new ItemStack(Blocks.diamond_block).getItem();
		}
	};
	
	/**
	 * Creating tab for miscellaneous
	 */
	public static CreativeTabs tabImmersiveModMisc = new CreativeTabs(
			"tabImmersiveModMisc") {

		@Override
		public Item getTabIconItem() {
			return new ItemStack(Items.book).getItem();
		}
	};
	
	/**
	 * Creating tab for miscellaneous
	 */
	public static CreativeTabs tabImmersiveModArmor = new CreativeTabs(
			"tabImmersiveModAmor") {

		@Override
		public Item getTabIconItem() {
			return new ItemStack(Items.diamond_chestplate).getItem();
		}
	};
}
