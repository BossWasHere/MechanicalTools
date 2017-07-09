package com.mechanicals.plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import com.mechanicals.plugin.blocks.AnimalGrowth;
import com.mechanicals.plugin.blocks.BlockBreaker;
import com.mechanicals.plugin.blocks.BlockPlacer;
import com.mechanicals.plugin.blocks.ChunkLoader;
import com.mechanicals.plugin.blocks.Elevator;
import com.mechanicals.plugin.blocks.EntityTeleporter;
import com.mechanicals.plugin.blocks.Generator;
import com.mechanicals.plugin.blocks.Grinder;
import com.mechanicals.plugin.blocks.ItemTeleporter;
import com.mechanicals.plugin.blocks.LargeTeleporter;
import com.mechanicals.plugin.blocks.PlantFarmer;
import com.mechanicals.plugin.blocks.TreeCutter;
import com.mechanicals.plugin.command.CommandHandler;
import com.mechanicals.plugin.configuration.ConfigurationUnit;
import com.mechanicals.plugin.configuration.PermissionIndex;
import com.mechanicals.plugin.configuration.TextIndex;
import com.mechanicals.plugin.event.BlockEvent;
import com.mechanicals.plugin.event.InventoryEvent;
import com.mechanicals.plugin.event.PlayerEvent;
import com.mechanicals.plugin.items.DyeWand;
import com.mechanicals.plugin.items.Flamethrower;
import com.mechanicals.plugin.items.ITool;
import com.mechanicals.plugin.items.Radio;
import com.mechanicals.plugin.items.SpawnPointTeleporter;
import com.mechanicals.plugin.server.EconomyManager;
import com.mechanicals.plugin.server.MechanicalPluginManager;
import com.mechanicals.plugin.server.ServerVersion;
import com.mechanicals.plugin.task.AnimalGrowthTaskTimer;
import com.mechanicals.plugin.task.BlockBreakTaskTimer;
import com.mechanicals.plugin.task.BlockPlaceTaskTimer;
import com.mechanicals.plugin.task.ChunkLoadTaskTimer;
import com.mechanicals.plugin.task.EntityTeleporterTaskTimer;
import com.mechanicals.plugin.task.GeneratorTaskTimer;
import com.mechanicals.plugin.task.GrinderTaskTimer;
import com.mechanicals.plugin.task.ItemTeleporterTaskTimer;
import com.mechanicals.plugin.task.ParticleSpawnerTaskTimer;
import com.mechanicals.plugin.task.PlantFarmerTaskTimer;
import com.mechanicals.plugin.task.TreeCutterTaskTimer;
import com.mechanicals.plugin.task.extra.ConfirmCooldown;
import com.mechanicals.plugin.world.WorldEditManager;

public class MechMain extends JavaPlugin {
	
	private boolean disable = false;
	public boolean debug = false;
	public boolean nbapi = false, weapi = false, vault = false;
	
	public static MechMain plugin;
	
	public PermissionIndex permissions;
	public TextIndex texts;
	public ConfigurationUnit blockData;						public ConfigurationUnit itemData;
	public ConfigurationUnit textData;						public ConfigurationUnit placed;
	public ConfigurationUnit config;						public ConfigurationUnit remoteStorage;
	
	public File resourceLocation;
	public Logger logger;
	
	public BlockPlacer blockPlacer;							public BlockBreaker blockBreaker;
	public TreeCutter treeCutter;							public EntityTeleporter entityTeleporter;
	public ItemTeleporter itemTeleporter;					public Grinder grinder;
	public LargeTeleporter largeTeleporter;					public ChunkLoader chunkLoader;
	public Elevator elevator;								public AnimalGrowth animalGrowth;
	public PlantFarmer plantFarmer;							public Generator generator;
	
	public SpawnPointTeleporter bedTeleporter;				public Radio radio;
	public ITool iTool;										public DyeWand dyeWand;
	public Flamethrower flamethrower;
	
	public BlockEvent placementListener;
	public InventoryEvent inventoryListener;
	public PlayerEvent playerListener;
	public Set<ConfirmCooldown> cooldowns = Collections.synchronizedSet(new HashSet<>());
	public MechanicalPluginManager mechPluginManager;
	public WorldEditManager worldEditManager;
	public EconomyManager economyManager;
	
	public BlockPlaceTaskTimer blockPlaceTask;				public BlockBreakTaskTimer blockBreakTask;
	public TreeCutterTaskTimer treeCutterTask;				public EntityTeleporterTaskTimer entityTeleporterTask;
	public ItemTeleporterTaskTimer itemTeleporterTask;		public GrinderTaskTimer grinderTask;
	public ChunkLoadTaskTimer chunkLoadTask;				public AnimalGrowthTaskTimer animalGrowthTask;
	public PlantFarmerTaskTimer plantFarmerTask;			public GeneratorTaskTimer generatorTask;
	public ParticleSpawnerTaskTimer particleTask;
	
	
	
	public boolean blockBreakerEnabled = false, blockPlacerEnabled = false, treeCutterEnabled = false, entityTeleporterEnabled = false,
			itemTeleporterEnabled = false, grinderEnabled = false, largeTeleporterEnabled = false, chunkLoaderEnabled = false, elevatorEnabled = false,
			animalGrowthEnabled = false, plantFarmerEnabled = false, generatorEnabled = false;
	public boolean spawnPointTeleporterEnabled = false, radioEnabled = false, iToolEnabled = false, dyeWandEnabled = false, flamethrowerEnabled = false;

	@Override
	public void onEnable() {
		
		if (disable) {
			getServer().getPluginManager().disablePlugin(this);
			return;
		}
		
		mechPluginManager = new MechanicalPluginManager();
		
		if (config.getBoolean("useNoteBlockAPI")) {
			nbapi = mechPluginManager.enableNoteBlockAPI();
		}
		if (config.getBoolean("useWorldEditAPI")) {
			weapi = mechPluginManager.enableWorldEdit();
		}
		
		String comp = "";
		for (String in : blockData.getStringList("enabled")) {
			switch (in.toLowerCase()) {
			case "blockbreaker":
				blockBreakerEnabled = true;
				comp += "BlockBreaker, ";
				break;
			case "blockplacer":
				blockPlacerEnabled = true;
				comp += "BlockPlacer, ";
				break;
			case "treecutter":
				treeCutterEnabled = true;
				comp += "TreeCutter, ";
				break;
			case "entityteleporter":
				entityTeleporterEnabled = true;
				comp += "EntityTeleporter, ";
				break;
			case "itemteleporter":
				itemTeleporterEnabled = true;
				comp += "ItemTeleporter, ";
				break;
			case "grinder":
				grinderEnabled = true;
				comp += "Grinder, ";
				break;
			case "largeteleporter":
				largeTeleporterEnabled = true;
				comp += "LargeTeleporter, ";
				break;
			case "chunkloader":
				chunkLoaderEnabled = true;
				comp += "ChunkLoader, ";
				break;
			case "elevator":
				elevatorEnabled = true;
				comp += "Elevator, ";
				break;
			case "animalgrowth":
				animalGrowthEnabled = true;
				comp += "AnimalGrowth, ";
				break;
			case "plantfarmer":
				plantFarmerEnabled = true;
				comp += "PlantFarmer, ";
				break;
			case "generator":
				generatorEnabled = true;
				comp += "Generator, ";
				break;
			}
		}
		for (String in : itemData.getStringList("enabled")) {
			switch (in.toLowerCase()) {
			case "spawnpointteleporter":
				spawnPointTeleporterEnabled = true;
				comp += "SpawnPointTeleporter, ";
				break;
			case "radio":
				if (nbapi) {
					radioEnabled = true;
					comp += "Radio, ";
				}
				break;
			case "itool":
				iToolEnabled = true;
				comp += "ITool, ";
				break;
			case "dyewand":
				dyeWandEnabled = true;
				comp += "DyeWand, ";
				break;
			case "flamethrower":
				flamethrowerEnabled = true;
				comp += "Flamethrower, ";
				break;
			}
		}
		
		logger.info("Starting Plugin with components enabled: " + (comp == "" ? "None" : comp.substring(0, comp.length() - 2)));
		
		permissions = new PermissionIndex(config, blockData, itemData);
		texts = new TextIndex(textData);
		
		blockPlacer = new BlockPlacer();						blockBreaker = new BlockBreaker();
		treeCutter = new TreeCutter();							entityTeleporter = new EntityTeleporter();
		itemTeleporter = new ItemTeleporter();					grinder = new Grinder();
		largeTeleporter = new LargeTeleporter();				chunkLoader = new ChunkLoader();
		elevator = new Elevator();								animalGrowth = new AnimalGrowth();
		plantFarmer = new PlantFarmer();						generator = new Generator();
		
		bedTeleporter = new SpawnPointTeleporter();				radio = new Radio();
		iTool = new ITool();									dyeWand = new DyeWand();
		flamethrower = new Flamethrower();
		
		
		placementListener = new BlockEvent();					inventoryListener = new InventoryEvent();
		playerListener = new PlayerEvent();
		
		blockPlaceTask = new BlockPlaceTaskTimer();				blockBreakTask = new BlockBreakTaskTimer();
		treeCutterTask = new TreeCutterTaskTimer();				entityTeleporterTask = new EntityTeleporterTaskTimer();
		itemTeleporterTask = new ItemTeleporterTaskTimer();		chunkLoadTask = new ChunkLoadTaskTimer();
		grinderTask = new GrinderTaskTimer();					animalGrowthTask = new AnimalGrowthTaskTimer();
		plantFarmerTask = new PlantFarmerTaskTimer();			generatorTask = new GeneratorTaskTimer();
		particleTask = new ParticleSpawnerTaskTimer();
		
		registerRunnables();
		registerRecipes();
		
	}
	
	private void registerRunnables() {
		//Synchronised
		if (blockPlacerEnabled) blockPlaceTask.runTaskTimer(this, 200, blockData.getLong("block.blockPlacer.trigger", 10) * 20);
		if (blockBreakerEnabled) blockBreakTask.runTaskTimer(this, 200, blockData.getLong("block.blockBreaker.trigger", 10) * 20);
		if (treeCutterEnabled) treeCutterTask.runTaskTimer(this, 200, blockData.getLong("block.treeCutter.trigger", 10) * 20);
		if (entityTeleporterEnabled) entityTeleporterTask.runTaskTimer(this, 200, blockData.getLong("block.entityTeleporter.trigger", 10) * 20);
		if (itemTeleporterEnabled) itemTeleporterTask.runTaskTimer(this, 200, blockData.getLong("block.itemTeleporter.trigger", 10) * 20);
		if (grinderEnabled) grinderTask.runTaskTimer(this, 200, blockData.getLong("block.grinder.trigger", 10) * 20);
		if (chunkLoaderEnabled) chunkLoadTask.runTaskTimer(this, 200, 60);
		if (animalGrowthEnabled) animalGrowthTask.runTaskTimer(this, 200, blockData.getLong("block.animalGrowth.trigger", 55) * 20);
		if (plantFarmerEnabled) plantFarmerTask.runTaskTimer(this, 200, blockData.getLong("block.plantFarmer.trigger", 18) * 20);
		if (generatorEnabled) generatorTask.runTaskTimer(this, 200, (long)(blockData.getDouble("block.generator.trigger") * 20));
		
		//Asynchronised
		if (blockData.getBoolean("particle.enabled")) particleTask.runTaskTimerAsynchronously(this, 100, blockData.getLong("particle.ticks", 5));
	}
	
	private void registerRecipes() {
		RecipeRegistry.registerRecipes(this);
	}

	@Override
	public void onDisable() {
		HandlerList.unregisterAll(inventoryListener);
		HandlerList.unregisterAll(placementListener);
		HandlerList.unregisterAll(playerListener);
	}
	
	@Override
	public void onLoad() {
		plugin = this;
		ServerVersion.findVersion();
		resourceLocation = this.getDataFolder();
		logger = getLogger();
		try {
			
			if (!resourceLocation.exists()) {
				resourceLocation.mkdir();
			}
			
			blockData = new ConfigurationUnit(new File(resourceLocation, "blockdata.yml"), "blockdata.yml");
			itemData = new ConfigurationUnit(new File(resourceLocation, "itemdata.yml"), "itemdata.yml");
			textData = new ConfigurationUnit(new File(resourceLocation, "text.yml"), "text.yml");
			placed = new ConfigurationUnit(new File(resourceLocation, "placed.yml"));
			remoteStorage = new ConfigurationUnit(new File(resourceLocation, "extrainventory.yml"));
			config = new ConfigurationUnit(new File(resourceLocation, "config.yml"));
			
			saveDefaultConfig();
			debug = config.getBoolean("debug");
			
		} catch (IOException e) {
			disable = true;
		}
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		return CommandHandler.parseIncomingCommand(sender, cmd.getName().toLowerCase(), args);
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
		List<String> ret = CommandHandler.parseTabComplete(sender, cmd.getName().toLowerCase(), args);
		return ret == null ? super.onTabComplete(sender, cmd, alias, args) : ret;
	}
	
	@Override
	public void saveConfig() {
		blockData.save();
		textData.save();
		placed.save();
	}
	
	@Override
	public void reloadConfig() {
		blockData.saveAndReload();
		textData.saveAndReload();
		placed.saveAndReload();
	}
	
	@Override
	public void saveDefaultConfig() {
		boolean change = false;
		if (!config.contains("useNoteBlockAPI")) {
			config.set("useNoteBlockAPI", false);
			change = true;
		}
		if (!config.contains("useWorldEditAPI")) {
			config.set("useWorldEditAPI", false);
			change = true;
		}
		if (!config.contains("songLocation")) {
			config.set("songLocation", "~pluginfolder~/noteblocksongs");
			change = true;
		}
		if (!config.contains("schematicLocation")) {
			config.set("schematicLocation", "~pluginfolder~/MechanicalTools/schematics");
			change = true;
		}
		if (!config.contains("debug")) {
			config.set("debug", false);
			change = true;
		}
		if (!config.contains("remoteStorageEnabled")) {
			config.set("remoteStorageEnabled", true);
			change = true;
		}
		if (!config.contains("remoteInventoryCommand.see")) {
			config.set("remoteInventoryCommand.see", "mechanical.remotebackpack.see");
			change = true;
		}
		if (!config.contains("remoteInventoryCommand.edit")) {
			config.set("remoteInventoryCommand.edit", "mechanical.remotebackpack.edit");
			change = true;
		}
		if (change) config.saveAndReload();
	}
	
	public InputStream getResourceAsStream(String resource) {
		return getClass().getResourceAsStream("/" + resource);
	}

}
