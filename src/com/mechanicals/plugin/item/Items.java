package com.mechanicals.plugin.item;

import java.util.Random;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import com.mechanicals.plugin.server.ServerVersion;

/**
 * Collection of all block and item IDs for the plugin to decode items from
 * @author IballisticBoss
 * @since 1.0
 *
 */
public enum Items {
	
	  air ("AIR"),
	  stone ("STONE"),
	  granite ("STONE", 1),
	  polished_granite ("STONE", 2),
	  diorite ("STONE", 3), 
	  polished_diorite ("STONE", 4), 
	  andesite ("STONE", 5), 
	  polished_andesite ("STONE", 6), 
	  grass ("GRASS"), 
	  dirt ("DIRT"), 
	  coarse_dirt ("DIRT", 1), 
	  podzol ("DIRT", 2), 
	  cobblestone ("COBBLESTONE"),
	  oak_planks ("WOOD"),
	  spruce_planks ("WOOD", 1),
	  birch_planks ("WOOD", 2),
	  jungle_planks ("WOOD", 3),
	  acacia_planks ("WOOD", 4),
	  dark_oak_planks ("WOOD", 5),
	  oak_sapling ("SAPLING"), 
	  spruce_sapling ("SAPLING", 1), 
	  birch_sapling ("SAPLING", 2), 
	  jungle_sapling ("SAPLING", 3), 
	  acacia_sapling ("SAPLING", 4), 
	  darkoak_sapling ("SAPLING", 5), 
	  bedrock ("BEDROCK"), 
	  sand ("SAND"), 
	  red_sand ("SAND", 1), 
	  gravel ("GRAVEL"), 
	  gold_ore ("GOLD_ORE"), 
	  iron_ore ("IRON_ORE"), 
	  coal_ore ("COAL_ORE"), 
	  oak_log ("LOG"), 
	  spruce_log ("LOG", 1), 
	  birch_log ("LOG", 2), 
	  jungle_log ("LOG", 3),
	  oak_leaves ("LEAVES"),
	  spruce_leaves ("LEAVES", 1), 
	  birch_leaves ("LEAVES", 2), 
	  jungle_leaves ("LEAVES", 3), 
	  sponge ("SPONGE"), 
	  glass ("GLASS"), 
	  lapis_ore ("LAPIS_ORE"), 
	  lapis_block ("LAPIS_BLOCK"), 
	  dispenser ("DISPENSER"), 
	  sandstone ("SANDSTONE"), 
	  noteblock ("NOTE_BLOCK"), 
	  bed ("BED"), 
	  powered_rail ("POWERED_RAIL"), 
	  detector_rail ("DETECTOR_RAIL"), 
	  sticky_piston ("PISTON_STICKY_BASE"), 
	  cobweb ("WEB"), 
	  dead_bush ("DEAD_BUSH"), 
	  piston ("PISTON_BASE"), 
	  wool ("WOOL"), 
	  dandelion ("YELLOW_FLOWER"), 
	  poppy ("RED_ROSE"), 
	  blue_orchid ("RED_ROSE", 1), 
	  allium ("RED_ROSE", 2), 
	  azure_bluet ("RED_ROSE", 3), 
	  red_tulip ("RED_ROSE", 4), 
	  orange_tulip ("RED_ROSE", 5), 
	  white_tulip ("RED_ROSE", 6), 
	  pink_tulip ("RED_ROSE", 7),
	  oxeye_daisy ("RED_ROSE", 8),
	  brown_mushroom ("BROWN_MUSHROOM"),
	  red_mushroom ("RED_MUSHROOM"),
	  gold_block ("GOLD_BLOCK"),
	  iron_block ("IRON_BLOCK"),
	  bricks ("BRICK"),
	  tnt ("TNT"),
	  bookshelf ("BOOKSHELF"),
	  mossy_cobblestone ("MOSSY_COBBLESTONE"),
	  obsidian ("OBSIDIAN"),
	  torch ("TORCH"),
	  chest ("CHEST"),
	  diamond_ore ("DIAMOND_ORE"),
	  diamond_block ("DIAMOND_BLOCK"),
	  crafting_table ("WORKBENCH"),
	  furnace ("FURNACE"), 
	  ladder ("LADDER"), 
	  rail ("RAILS"), 
	  lever ("LEVER"), 
	  stone_pressure_plate ("STONE_PLATE"), 
	  wooden_pressure_plate ("WOOD_PLATE"),
	  redstone_ore ("REDSTONE_ORE"),
	  redstone_torch ("REDSTONE_TORCH_ON"),
	  stone_button ("STONE_BUTTON"),
	  snow_layer ("SNOW"),
	  ice ("ICE"), 
	  snow_block ("SNOW_BLOCK"),
	  cactus ("CACTUS"), 
	  clay ("CLAY"),
	  sugar_cane ("SUGAR_CANE"),
	  jukebox ("JUKEBOX"),
	  pumpkin ("PUMPKIN"),
	  netherrack ("NETHERRACK"),
	  soul_sand ("SOUL_SAND"),
	  glowstone ("GLOWSTONE"),
	  jack_o_lantern ("JACK_O_LANTERN"),
	  wooden_trapdoor ("TRAP_DOOR"),
	  stone_monster_egg ("MONSTER_EGGS"),
	  cobblestone_monster_egg ("MONSTER_EGGS", 1),
	  stone_brick_monster_egg ("MONSTER_EGGS", 2),
	  mossy_stone_brick_monster_egg ("MONSTER_EGGS", 3),
	  cracked_stone_brick_monster_egg ("MONSTER_EGGS", 4),
	  chiseled_stone_brick_monster_egg ("MONSTER_EGGS", 5),
	  stone_brick ("SMOOTH_BRICK"), 
	  mossy_stone_brick ("SMOOTH_BRICK", 1),
	  cracked_stone_brick ("SMOOTH_BRICK", 2),
	  chiseled_stone_brick ("SMOOTH_BRICK", 3),
	  brown_mushroom_block ("HUGE_MUSHROOM_1"), 
	  red_mushroom_block ("HUGE_MUSHROOM_2"),
	  iron_bars ("IRON_FENCE"),
	  melon_block ("MELON_BLOCK"),
	  vines ("VINE"),
	  mycelium ("MYCEL"),
	  lily_pad ("WATER_LILY"),
	  nether_brick ("NETHER_BRICK"),
	  nether_wart ("NETHER_STALK"),
	  enchantment_table ("ENCHANTMENT_TABLE"),
	  brewing_stand ("BREWING_STAND_ITEM"),
	  caludron ("CAULDRON_ITEM"),
	  end_portal_frame ("ENDER_PORTAL_FRAME"),
	  end_stone ("ENDER_STONE"),
	  dragon_egg ("DRAGON_EGG"),
	  redstone_lamp ("REDSTONE_LAMP_OFF"),
	  emerald_ore ("EMERALD_ORE"),
	  ender_chest ("ENDER_CHEST"),
	  emerald_block ("EMERALD_BLOCK"),
	  tripwire_hook ("TRIPWIRE_HOOK"),
	  command_block ("COMMAND"),
	  beacon ("BEACON"),
	  flower_pot ("FLOWER_POT"),
	  wooden_button ("WOOD_BUTTON"),
	  mob_head_skeleton ("SKULL_ITEM"),
	  mob_head_wither ("SKULL_ITEM", 1),
	  mob_head_zombie ("SKULL_ITEM", 2),
	  mob_head_human ("SKULL_ITEM", 3),
	  mob_head_creeper ("SKULL_ITEM", 4),
	  mob_head_dragon ("SKULL_ITEM", 5),
	  anvil ("ANVIL"),
	  trapped_chest ("TRAPPED_CHEST"),
	  weighted_pressure_plate_l ("GOLD_PLATE"),
	  weighted_pressure_plate_h ("IRON_PLATE"),
	  daylight_sensor ("DAYLIGHT_DETECTOR"),
	  redstone_block ("REDSTONE_BLOCK"),
	  nether_quartz_ore ("QUARTZ_ORE"),
	  hopper ("HOPPER"),
	  quartz_block ("QUARTZ_BLOCK"),
	  activator_rail ("ACTIVATOR_RAIL"),
	  dropper ("DROPPER"),
	  hardened_clay ("HARD_CLAY"),
	  acacia_leaves ("LEAVES_2"),
	  darkoak_leaves ("LEAVES_2", 1),
	  acacia_log ("LOG_2"),
	  darkoak_log ("LOG_2", 1),
	  slime_block ("SLIME_BLOCK"),
	  barrier ("BARRIER"),
	  iron_trapdoor ("IRON_TRAPDOOR"),
	  prismarine ("PRISMARINE"),
	  dark_prismarine ("PRISMARINE", 2),
	  sea_lantern ("SEA_LANTERN"),
	  hay_bale ("HAY_BLOCK"),
	  block_of_coal ("COAL_BLOCK"),
	  packed_ice ("PACKED_ICE"),
	  sunflower ("DOUBLE_PLANT"),
	  lilac ("DOUBLE_PLANT", 1),
	  double_tallgrass ("DOUBLE_PLANT", 2),
	  large_fern ("DOUBLE_PLANT", 3),
	  rose_bush ("DOUBLE_PLANT", 4),
	  peony ("DOUBLE_PLANT", 5),
	  red_sandstone ("RED_SANDSTONE"),
	  end_rod ("END_ROD"),
	  chorus_plant ("CHORUS_PLANT"),
	  chorus_flower ("CHORUS_FLOWER"),
	  purpur_block ("PURPUR_BLOCK"),
	  purpur_pillar ("PURPUR_PILLAR"),
	  end_stone_bricks ("END_BRICKS"),
	  
	  //1.10 Stuff
	  magma_block ("MAGMA"),
	  nether_wart_block ("NETHER_WART_BLOCK"),
	  red_nether_brick ("RED_NETHER_BRICK"),
	  bone_block ("BONE_BLOCK"),
	  structure_block ("STRUCTURE_BLOCK"),
	  
	  //1.11 Stuff
	  observer ("OBSERVER"),
	  shulker_shell ("SHULKER_SHELL"),
	  white_shulker_box ("WHITE_SHULKER_BOX"),
	  orange_shulker_box ("ORANGE_SHULKER_BOX"),
	  magenta_shulker_box ("MAGENTA_SHULKER_BOX"),
	  light_blue_shulker_box ("LIGHT_BLUE_SHULKER_BOX"),
	  yellow_shulker_box ("YELLOW_SHULKER_BOX"),
	  lime_shulker_box ("LIME_SHULKER_BOX"),
	  pink_shulker_box ("PINK_SHULKER_BOX"),
	  gray_shulker_box ("GRAY_SHULKER_BOX"),
	  silver_shulker_box ("SILVER_SHULKER_BOX"),
	  cyan_shulker_box ("CYAN_SHULKER_BOX"),
	  purple_shulker_box ("PURPLE_SHULKER_BOX"),
	  blue_shulker_box ("BLUE_SHULKER_BOX"),
	  brown_shulker_box ("BROWN_SHULKER_BOX"),
	  green_shulker_box ("GREEN_SHULKER_BOX"),
	  red_shulker_box ("RED_SHULKER_BOX"),
	  black_shulker_box ("BLACK_SHULKER_BOX"),
	  iron_nugget ("IRON_NUGGET"),
	  
	  //1.12 Stuff
	  knowledge_book ("KNOWLEDGE_BOOK"),
	  
	  white_concrete ("CONCRETE"),
	  orange_concrete ("CONCRETE", 1),
	  magenta_concrete ("CONCRETE", 2),
	  light_blue_concrete ("CONCRETE", 3),
	  yellow_concrete ("CONCRETE", 4),
	  lime_concrete ("CONCRETE", 5),
	  pink_concrete ("CONCRETE", 6),
	  gray_concrete ("CONCRETE", 7),
	  silver_concrete ("CONCRETE", 8),
	  cyan_concrete ("CONCRETE", 9),
	  purple_concrete ("CONCRETE", 10),
	  blue_concrete ("CONCRETE", 11),
	  brown_concrete ("CONCRETE", 12),
	  green_concrete ("CONCRETE", 13),
	  red_concrete ("CONCRETE", 14),
	  black_concrete ("CONCRETE", 15),
	  
	  white_concrete_powder ("CONCRETE_POWDER"),
	  orange_concrete_powder ("CONCRETE_POWDER", 1),
	  magenta_concrete_powder ("CONCRETE_POWDER", 2),
	  light_blue_concrete_powder ("CONCRETE_POWDER", 3),
	  yellow_concrete_powder ("CONCRETE_POWDER", 4),
	  lime_concrete_powder ("CONCRETE_POWDER", 5),
	  pink_concrete_powder ("CONCRETE_POWDER", 6),
	  gray_concrete_powder ("CONCRETE_POWDER", 7),
	  silver_concrete_powder ("CONCRETE_POWDER", 8),
	  cyan_concrete_powder ("CONCRETE_POWDER", 9),
	  purple_concrete_powder ("CONCRETE_POWDER", 10),
	  blue_concrete_powder ("CONCRETE_POWDER", 11),
	  brown_concrete_powder ("CONCRETE_POWDER", 12),
	  green_concrete_powder ("CONCRETE_POWDER", 13),
	  red_concrete_powder ("CONCRETE_POWDER", 14),
	  black_concrete_powder ("CONCRETE_POWDER", 15),
	  
	  white_glazed_terracotta ("WHITE_GLAZED_TERRACOTTA"),
	  orange_glazed_terracotta ("ORANGE_GLAZED_TERRACOTTA"),
	  magenta_glazed_terracotta ("MAGENTA_GLAZED_TERRACOTTA"),
	  light_blue_glazed_terracotta ("LIGHT_BLUE_GLAZED_TERRACOTTA"),
	  yellow_glazed_terracotta ("YELLOW_GLAZED_TERRACOTTA"),
	  lime_glazed_terracotta ("LIME_GLAZED_TERRACOTTA"),
	  pink_glazed_terracotta ("PINK_GLAZED_TERRACOTTA"),
	  gray_glazed_terracotta ("GRAY_GLAZED_TERRACOTTA"),
	  silver_glazed_terracotta ("SILVER_GLAZED_TERRACOTTA"),
	  cyan_glazed_terracotta ("CYAN_GLAZED_TERRACOTTA"),
	  purple_glazed_terracotta ("PURPLE_GLAZED_TERRACOTTA"),
	  blue_glazed_terracotta ("BLUE_GLAZED_TERRACOTTA"),
	  brown_glazed_terracotta ("BROWN_GLAZED_TERRACOTTA"),
	  green_glazed_terracotta ("GREEN_GLAZED_TERRACOTTA"),
	  red_glazed_terracotta ("RED_GLAZED_TERRACOTTA"),
	  black_glazed_terracotta ("BLACK_GLAZED_TERRACOTTA"),
	  
	  white_stained_clay ("STAINED_CLAY"),
	  orange_stained_clay ("STAINED_CLAY", 1),
	  magenta_stained_clay ("STAINED_CLAY", 2),
	  light_blue_stained_clay ("STAINED_CLAY", 3),
	  yellow_stained_clay ("STAINED_CLAY", 4),
	  lime_stained_clay ("STAINED_CLAY", 5),
	  pink_stained_clay ("STAINED_CLAY", 6),
	  gray_stained_clay ("STAINED_CLAY", 7),
	  silver_stained_clay ("STAINED_CLAY", 8),
	  cyan_stained_clay ("STAINED_CLAY", 9),
	  purple_stained_clay ("STAINED_CLAY", 10),
	  blue_stained_clay ("STAINED_CLAY", 11),
	  brown_stained_clay ("STAINED_CLAY", 12),
	  green_stained_clay ("STAINED_CLAY", 13),
	  red_stained_clay ("STAINED_CLAY", 14),
	  black_stained_clay ("STAINED_CLAY", 15),
	  white_terracotta ("STAINED_CLAY"),
	  orange_terracotta ("STAINED_CLAY", 1),
	  magenta_terracotta ("STAINED_CLAY", 2),
	  light_blue_terracotta ("STAINED_CLAY", 3),
	  yellow_terracotta ("STAINED_CLAY", 4),
	  lime_terracotta ("STAINED_CLAY", 5),
	  pink_terracotta ("STAINED_CLAY", 6),
	  gray_terracotta ("STAINED_CLAY", 7),
	  silver_terracotta ("STAINED_CLAY", 8),
	  cyan_terracotta ("STAINED_CLAY", 9),
	  purple_terracotta ("STAINED_CLAY", 10),
	  blue_terracotta ("STAINED_CLAY", 11),
	  brown_terracotta ("STAINED_CLAY", 12),
	  green_terracotta ("STAINED_CLAY", 13),
	  red_terracotta ("STAINED_CLAY", 14),
	  black_terracotta ("STAINED_CLAY", 15),

	  white_bed ("BED"),
	  orange_bed ("BED", 1),
	  magenta_bed ("BED", 2),
	  light_blue_bed ("BED", 3),
	  yellow_bed ("BED", 4),
	  lime_bed ("BED", 5),
	  pink_bed ("BED", 6),
	  gray_bed ("BED", 7),
	  silver_bed ("BED", 8),
	  cyan_bed ("BED", 9),
	  purple_bed ("BED", 10),
	  blue_bed ("BED", 11),
	  brown_bed ("BED", 12),
	  green_bed ("BED", 13),
	  red_bed ("BED", 14),
	  black_bed ("BED", 15),
	  
	  white_carpet ("CARPET"),
	  orange_carpet ("CARPET", 1),
	  magenta_carpet ("CARPET", 2),
	  light_blue_carpet ("CARPET", 3),
	  yellow_carpet ("CARPET", 4),
	  lime_carpet ("CARPET", 5),
	  pink_carpet ("CARPET", 6),
	  gray_carpet ("CARPET", 7),
	  silver_carpet ("CARPET", 8),
	  cyan_carpet ("CARPET", 9),
	  purple_carpet ("CARPET", 10),
	  blue_carpet ("CARPET", 11),
	  brown_carpet ("CARPET", 12),
	  green_carpet ("CARPET", 13),
	  red_carpet ("CARPET", 14),
	  black_carpet ("CARPET", 15),
	  
	  //Items
	  iron_shovel ("IRON_SPADE"),
	  iron_pickaxe ("IRON_PICKAXE"),
	  iron_axe ("IRON_AXE"),
	  flint_and_steel ("FLINT_AND_STEEL"),
	  apple ("APPLE"),
	  bow ("BOW"),
	  arrow ("ARROW"),
	  coal ("COAL"),
	  charcoal ("COAL", 1),
	  diamond ("DIAMOND"),
	  iron_ingot ("IRON_INGOT"),
	  gold_ingot ("GOLD_INGOT"),
	  iron_sword ("IRON_SWORD"),
	  diamond_sword ("DIAMOND_SWORD"),
	  diamond_shovel ("DIAMOND_SPADE"),
	  diamond_pickaxe ("DIAMOND_PICKAXE"), 
	  diamond_axe ("DIAMOND_AXE"), 
	  stick ("STICK"),
	  bowl ("BOWL"),
	  mushroom_stew ("MUSHROOM_SOUP"),
	  string ("STRING"),
	  feather ("FEATHER"),
	  gunpowder ("SULPHUR"),
	  iron_hoe ("IRON_HOE"),
	  diamond_hoe ("DIAMOND_HOE"),
	  wheat_seeds ("SEEDS"),
	  wheat ("WHEAT"),
	  bread ("BREAD"),
	  chainmail_helmet ("CHAINMAIL_HELMET"),
	  chainmail_chestplate ("CHAINMAIL_CHESTPLATE"),
	  chainmail_leggings ("CHAINMAIL_LEGGINGS"),
	  chainmail_boots ("CHAINMAIL_BOOTS"),
	  iron_helmet ("IRON_HELMET"),
	  iron_chestplate ("IRON_CHESTPLATE"),
	  iron_leggings ("IRON_LEGGINGS"),
	  iron_boots ("IRON_BOOTS"),
	  diamond_helmet ("DIAMOND_HELMET"),
	  diamond_chestplate ("DIAMOND_CHESTPLATE"),
	  diamond_leggings ("DIAMOND_LEGGINGS"),
	  diamond_boots ("DIAMOND_BOOTS"),
	  flint ("FLINT"),
	  raw_porkchop ("PORK"),
	  cooked_porkchop ("GRILLED_PORK"),
	  painting ("PAINTING"),
	  golden_apple ("GOLDEN_APPLE"),
	  enchanted_golden_apple ("GOLDEN_APPLE", 1),
	  sign ("SIGN"),
	  bucket ("BUCKET"),
	  water_bucket ("WATER_BUCKET"),
	  lava_bucket ("LAVA_BUCKET"),
	  minecart ("MINECART"),
	  saddle ("SADDLE"),
	  iron_door ("IRON_DOOR"),
	  redstone ("REDSTONE"),
	  snowball ("SNOW_BALL"),
	  leather ("LEATHER"),
	  milk_bucket ("MILK_BUCKET"),
	  brick ("CLAY_BRICK"),
	  clay_ball ("CLAY_BALL"),
	  paper ("PAPER"),
	  book ("BOOK"),
	  slimeball ("SLIME_BALL"),
	  egg ("EGG"),
	  compass ("COMPASS"),
	  fishing_rod ("FISHING_ROD"),
	  clock ("WATCH"),
	  glowstone_dust ("GLOWSTONE_DUST"),
	  raw_fish ("RAW_FISH"),
	  cooked_fish ("COOKED_FISH"),
	  raw_salmon ("RAW_FISH", 1),
	  cooked_salmon ("COOKED_FISH", 1),
	  clownfish ("RAW_FISH", 2),
	  pufferfish ("RAW_FISH", 3),
	  ink_sack ("INK_SACK"),
	  rose_red ("INK_SACK", 1),
	  cactus_green ("INK_SACK", 2),
	  cocoa ("INK_SACK", 3),
	  lapis_lazuli ("INK_SACK", 4),
	  purple_dye ("INK_SACK", 5),
	  cyan_dye ("INK_SACK", 6),
	  light_gray_dye ("INK_SACK", 7),
	  gray_dye ("INK_SACK", 8),
	  pink_dye ("INK_SACK", 9),
	  lime_dye ("INK_SACK", 10),
	  dandelion_yellow ("INK_SACK", 11),
	  light_blue_dye ("INK_SACK", 12),
	  magenta_dye ("INK_SACK", 13),
	  orange_dye ("INK_SACK", 14),
	  bone_meal ("INK_SACK", 15),
	  bone ("BONE"),
	  cake ("CAKE"),
	  redstone_repeater ("DIODE"),
	  cookie ("COOKIE"),
	  map ("MAP"),
	  shears ("SHEARS"),
	  melon_slice ("MELON"),
	  pumpkin_seeds ("PUMPKIN_SEEDS"),
	  melon_seeds ("MELON_SEEDS"),
	  raw_beef ("RAW_BEEF"),
	  cooked_beef ("COOKED_BEEF"),
	  raw_chicken ("RAW_CHICKEN"),
	  cooked_chicken ("COOKED_CHICKEN"),
	  rotten_flesh ("ROTTEN_FLESH"),
	  ender_pearl ("ENDER_PEARL"),
	  blaze_rod ("BLAZE_ROD"),
	  ghast_tear ("GHAST_TEAR"),
	  gold_nugget ("GOLD_NUGGET"),
	  spider_eye ("SPIDER_EYE"),
	  blaze_powder ("BLAZE_POWDER"),
	  magma_cream ("MAGMA_CREAM"),
	  glistering_melon ("SPECKLED_MELON"),
	  bottle_of_enchanting ("EXP_BOTTLE"),
	  fire_charge ("FIREBALL"),
	  book_and_quill ("BOOK_AND_QUILL"),
	  emerald ("EMERALD"),
	  item_frame ("ITEM_FRAME"),
	  carrot ("CARROT_ITEM"),
	  potato ("POTATO_ITEM"),
	  baked_potato ("BAKED_POTATO"),
	  poisonous_potato ("POISONOUS_POTATO"),
	  golden_carrot ("GOLDEN_CARROT"),
	  nether_star ("NETHER_STAR"),
	  pumpkin_pie ("PUMPKIN_PIE"),
	  comparator ("REDSTONE_COMPARATOR"),
	  nether_quartz ("QUARTZ"),
	  prismarine_shard ("PRISMARINE_SHARD"),
	  prismarine_crystals ("PRISMARINE_CRYSTALS"),
	  raw_rabbit ("RABBIT"),
	  cooked_rabbit ("COOKED_RABBIT"),
	  rabbit_stew ("RABBIT_STEW"),
	  rabbit_foot ("RABBIT_FOOT"),
	  rabbit_hide ("RABBIT_HIDE"),
	  armor_stand ("ARMOR_STAND"),
	  iron_horse_armor ("IRON_BARDING"),
	  gold_horse_armor ("GOLD_BARDING"),
	  diamond_horse_armor ("DIAMOND_BARDING"),
	  lead ("LEASH"),
	  name_tag ("NAME_TAG"),
	  raw_mutton ("MUTTON"),
	  cooked_mutton ("COOKED_MUTTON"),
	  banner ("BANNER", 15),
	  end_crystal ("END_CRYSTAL"),
	  chorus_fruit ("CHORUS_FRUIT"),
	  popped_chorus_fruit ("CHORUS_FRUIT_POPPED"),
	  beetroot ("BEETROOT"),
	  beetroot_seeds ("BEETROOT_SEEDS"),
	  beetroot_soup ("BEETROOT_SOUP"),
	  dragons_breath ("DRAGONS_BREATH"),
	  spectral_arrow ("SPECTRAL_ARROW"),
	  shield ("SHIELD"),
	  elytra ("ELYTRA"),
	  thirteen_disc ("GOLD_RECORD"),
	  cat_disc ("GREEN_RECORD"),
	  blocks_disc ("RECORD_3"),
	  chirp_disc ("RECORD_4"),
	  far_disc ("RECORD_5"),
	  mall_disc ("RECORD_6"),
	  mellohi_disc ("RECORD_7"),
	  stal_disc ("RECORD_8"),
	  strad_disc ("RECORD_9"),
	  ward_disc ("RECORD_10"),
	  eleven_disc ("RECORD_11"),
	  wait_disc ("RECORD_12");
	
	private final String id;
	private final int data;
	
	private Items(String id, int data) {
		this.id = id;
		this.data = data;
	}
	private Items(String id) {
		this.id = id;
		this.data = 0;
	}
	
	public static ItemStack getMaterial(final String in) throws Exception {
		try {
			byte data = (byte) valueOf(in).data;
			String itemId = valueOf(in).id;
			ItemStack item = new ItemStack(Material.getMaterial(itemId), 1, data);
			return item;
		} catch (Exception e) {
			return new ItemStack(Material.getMaterial(in));
		}
	}
	
	public static short getData(ItemStack item) {
		return item.getDurability();
	}
	
	public static boolean isColourable(Block block) {
		return block.getType().equals(Material.STAINED_CLAY) || block.getType().equals(Material.HARD_CLAY)
				|| block.getType().equals(Material.STAINED_GLASS) || block.getType().equals(Material.GLASS)
				|| block.getType().equals(Material.STAINED_GLASS_PANE) || block.getType().equals(Material.THIN_GLASS)
				|| block.getType().equals(Material.WOOL) || ((block.getType().equals(Material.CONCRETE)
				|| block.getType().equals(Material.CONCRETE_POWDER)/* || block.getType().equals(Material.BED)
				|| block.getType().equals(Material.BED_BLOCK)*/)
						&& ServerVersion.isAtOrHigherThan("1.12"));
	}
	
	public static DyeColor getDyeColor(short data) {
		switch (data) {
		case 0: return DyeColor.BLACK;
		case 1: return DyeColor.RED;
		case 2: return DyeColor.GREEN;
		case 3: return DyeColor.BROWN;
		case 4: return DyeColor.BLUE;
		case 5: return DyeColor.PURPLE;
		case 6: return DyeColor.CYAN;
		case 7: return DyeColor.SILVER;
		case 8: return DyeColor.GRAY;
		case 9: return DyeColor.PINK;
		case 10: return DyeColor.LIME;
		case 11: return DyeColor.YELLOW;
		case 12: return DyeColor.LIGHT_BLUE;
		case 13: return DyeColor.MAGENTA;
		case 14: return DyeColor.ORANGE;
		case 15:
		default: return DyeColor.WHITE;
		}
	}
	
	public static short getReverseDyeColor(short dye) {
		switch (dye) {
		case 15: return 0;
		case 14: return 1;
		case 13: return 2;
		case 12: return 3;
		case 11: return 4;
		case 10: return 5;
		case 9: return 6;
		case 8: return 7;
		case 7: return 8;
		case 6: return 9;
		case 5: return 10;
		case 4: return 11;
		case 3: return 12;
		case 2: return 13;
		case 1: return 14;
		case 0:
		default: return 15;
		}
	}
	
	public static ItemStack[] getPlankVariants() {
		return new ItemStack[] {new ItemStack(Material.WOOD), new ItemStack(Material.WOOD, 1, (short) 1),
				new ItemStack(Material.WOOD, 1, (short) 2), new ItemStack(Material.WOOD, 1, (short) 3),
				new ItemStack(Material.WOOD, 1, (short) 4), new ItemStack(Material.WOOD, 1, (short) 5)};
	}
	
	public static ItemStack[] getLogVariants() {
		return new ItemStack[] {new ItemStack(Material.LOG), new ItemStack(Material.LOG, 1, (short) 1),
				new ItemStack(Material.LOG, 1, (short) 2), new ItemStack(Material.LOG, 1, (short) 3),
				new ItemStack(Material.LOG_2), new ItemStack(Material.LOG_2, 1, (short) 1)};
	}
	
	public static ItemStack[] getSaplingVariants() {
		return new ItemStack[] {new ItemStack(Material.SAPLING), new ItemStack(Material.SAPLING, 1, (short) 1),
				new ItemStack(Material.SAPLING, 1, (short) 2), new ItemStack(Material.SAPLING, 1, (short) 3),
				new ItemStack(Material.SAPLING, 1, (short) 4), new ItemStack(Material.SAPLING, 1, (short) 5)};
	}
	
	public static ItemStack[] getDiscVariants() {
		return new ItemStack[] {new ItemStack(Material.GOLD_RECORD), new ItemStack(Material.GREEN_RECORD),
				new ItemStack(Material.RECORD_3), new ItemStack(Material.RECORD_4),
				new ItemStack(Material.RECORD_5), new ItemStack(Material.RECORD_6),
				new ItemStack(Material.RECORD_7), new ItemStack(Material.RECORD_8),
				new ItemStack(Material.RECORD_9), new ItemStack(Material.RECORD_10),
				new ItemStack(Material.RECORD_11), new ItemStack(Material.RECORD_12)};
	}
	
	public static ItemStack getRandomItem(ItemStack[] selection) {
		if (selection == null) return null;
		int r = new Random().nextInt(selection.length);
		return selection[r];
	}
}