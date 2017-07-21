package com.mechanicals.plugin.configuration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import com.mechanicals.plugin.MechMain;

/**
 * Main configuration unit for all configuration files for this plugin
 * <b>NOTE: </b>This class extends org.bukkit.configuration.file.YamlConfiguration, not all methods have been overridden
 * Super-class methods and variables may cause an issue if not used with caution!
 *
 */
public class ConfigurationUnit extends YamlConfiguration {

	private YamlConfiguration config;
	private File configFile;
	private final String extractor;
	
	/**
	 * Generates configuration without intentionally loading the configuration within this class
	 * @param configFile the output file to save the configuration to
	 * @param config the pre-loaded configuration
	 * @throws IOException if the file cannot be loaded, extracted, saved to or created
	 * @since 1.1
	 * @author IballisticBoss
	 * @see YamlConfiguration
	 */
	public ConfigurationUnit(File configFile, YamlConfiguration config) throws IOException {
		this.config = config;
		this.configFile = configFile;
		extractor = null;
		if (!checkFile()) throw new IOException("A configuration file failed to initialise!");
		super.loadConfiguration(configFile);
	}
	
	/**
	 * Generates configuration by generating configuration from the specified file
	 * @param configFile the output file to save the configuration to and load from
	 * @throws IOException if the file cannot be loaded, extracted, saved to or created
	 * @since 1.1
	 * @author IballisticBoss
	 * @see YamlConfiguration
	 */
	public ConfigurationUnit(File configFile) throws IOException {
		this.configFile = configFile;
		extractor = null;
		if (!checkFile()) throw new IOException("A configuration file failed to initialise!");
		config = loadConfiguration(configFile);
		super.loadConfiguration(configFile);
	}
	
	/**
	 * Generates configuration by intentionally extracting an internal file within the JAR
	 * @param configFile the output file to save the configuration to
	 * @param extract the name of the file within the JAR file
	 * @throws IOException if the file cannot be loaded, extracted, saved to or created
	 * @since 1.1
	 * @author IballisticBoss
	 * @see YamlConfiguration
	 */
	public ConfigurationUnit(File configFile, String extract) throws IOException {
		this.configFile = configFile;
		extractor = extract;
		if (!checkFile()) throw new IOException("A configuration file failed to initialise!");
		config = loadConfiguration(configFile);
		super.loadConfiguration(configFile);
	}
	
	private boolean checkFile() {
		if (configFile == null) return false;
		if (configFile.exists()) return true;
		return reset();
	}
	
	/**
	 * Resets this file by either extracting an older version over it or emptying it
	 * @return <b>true</b> if the file was updated, <b>false</b> otherwise
	 * @since 1.5
	 * @author IballisticBoss
	 */
	public boolean reset() {
		configFile.delete();
		if (extractor != null) {
			try {
				Files.copy(MechMain.plugin.getResourceAsStream(extractor), configFile.getAbsoluteFile().toPath());
				return true;
			} catch (IOException e) { return false; }
		}
		try {
			configFile.createNewFile();
			return true;
		} catch (IOException e) { return false; }
	}
	
	/**
	 * Overridden Super-Type Method
	 * Gets a string list from the configuration at the specified key path
	 * @param key the key path to search for values
	 * @return a list of strings found at the key path
	 * @since 1.1
	 * @author IballisticBoss
	 */
	@Override
	public List<String> getStringList(String key) {
		return config.getStringList(key);
	}
	
	/**
	 * Gets a string list from the configuration at the specified key path with a maximum number of elements
	 * @param key the key path to search for values
	 * @param maxLength the maximum number of elements in the list
	 * @return a list of strings found at the key path
	 * @since 1.1
	 * @author IballisticBoss
	 */
	public List<String> getStringList(String key, int maxLength) {
		List<String> newList = new ArrayList<>();
		int i = 0;
		for (String in : getStringList(key)) {
			if (i < maxLength) {
				newList.add(in);
			}
			i++;
		}
		return newList;
	}
	
	/**
	 * Overridden Super-Type Method
	 * Sets a value at the specified path inside the configuration
	 * @param key the key path to put the specified value
	 * @param value the data to put with the key path
	 * @since 1.1
	 * @author IballisticBoss
	 */
	@Override
	public void set(String key, Object value) {
		config.set(key, value);
	}
	
	/**
	 * Overridden Super-Type Method
	 * Gets a string from the configuration at the specified key path
	 * @param key the key path to search for the value
	 * @return The string found at the key path, or null if path doesn't exist
	 * @since 1.1
	 * @author IballisticBoss
	 */
	@Override
	public String getString(String key) {
		return config.getString(key);
	}
	
	/**
	 * Overridden Super-Type Method
	 * Gets a string from the configuration at the specified key path
	 * @param key the key path to search for the value
	 * @param i the value to return if null
	 * @return The string found at the key path, or the value of i if null
	 * @since 2.1
	 * @author IballisticBoss
	 */
	@Override
	public String getString(String key, String i) {
		return config.getString(key, i);
	}
	
	/**
	 * Overridden Super-Type Method
	 * Gets an integer from the configuration at the specified key path
	 * @param path the key path to search for the value
	 * @return The integer found at the key path
	 * @since 1.1
	 * @author IballisticBoss
	 */
	@Override
	public int getInt(String key) {
		return config.getInt(key);
	}
	
	/**
	 * Overridden Super-Type Method
	 * Gets an integer from the configuration at the specified key path, and returns the value of 'i' if null
	 * @param key the key path to search for the value
	 * @param i the integer value to return if value is not found
	 * @return The integer found at the key path, or the value of 'i'
	 * @since 2.0
	 * @author IballisticBoss
	 */
	@Override
	public int getInt(String path, int i) {
		return config.getInt(path, i);
	}
	
	/**
	 * Overridden Super-Type Method
	 * Gets a boolean from the configuration at the specified key path
	 * @param key the key path to search for the value
	 * @return The boolean found at the key path
	 * @since 1.1
	 * @author IballisticBoss
	 */
	@Override
	public boolean getBoolean(String key) {
		return config.getBoolean(key);
	}
	
	/**
	 * Overridden Super-Type Method
	 * Gets a boolean from the configuration at the specified key path, or returns value of i if null
	 * @param key the key path to search for the value
	 * @param i the value to return if a key is not found
	 * @return The boolean found at the key path, or i if null
	 * @since 2.2
	 * @author IballisticBoss
	 */
	@Override
	public boolean getBoolean(String key, boolean i) {
		return config.getBoolean(key, i);
	}
	
	/**
	 * Overridden Super-Type Method
	 * Gets a double from the configuration at the specified key path
	 * @param key the key path to search for the value
	 * @return The double found at the key path
	 * @since 1.1
	 * @author IballisticBoss
	 */
	@Override
	public double getDouble(String key) {
		return config.getDouble(key);
	}
	
	/**
	 * Overridden Super-Type Method
	 * Gets a long from the configuration at the specified key path
	 * @param key the key path to search for the value
	 * @return The long found at the key path
	 * @since 1.1
	 * @author IballisticBoss
	 */
	@Override
	public long getLong(String key) {
		return config.getLong(key);
	}
	
	/**
	 * Overridden Super-Type Method
	 * Gets a long value from the configuration at the specified key path, and returns the value of 'i' if null
	 * @param key the key path to search for the value
	 * @param i the long value to return if value is not found
	 * @return The long value found at the key path, or the value of 'i'
	 * @since 2.0
	 * @author IballisticBoss
	 */
	@Override
	public long getLong(String path, long i) {
		return config.getLong(path, i);
	}
	
	/**
	 * Overridden Super-Type Method
	 * Gets an unspecified type list (List<?>) from the configuration at the specified key path
	 * @param key the key path to search for the values
	 * @return The List object of objects found at the key path
	 * @since 1.1
	 * @author IballisticBoss
	 */
	@Override
	public List<?> getList(String key) {
		return config.getList(key);
	}
	
	/**
	 * Overridden Super-Type Method
	 * Gets an {@link ItemStack} from the configuration at the specified key path
	 * @param key the key path to search for the value
	 * @return The {@link ItemStack} which was found there, or null if doesn't exist
	 * @since 2.1
	 * @author IballisticBoss
	 */
	@Override
	public ItemStack getItemStack(String key) {
		return config.getItemStack(key);
	}
	
	/**
	 * Overridden Super-Type Method
	 * Gets keys from the configuration
	 * @param deep if all keys, even if lower down in hierarchy, should be retrieved
	 * @return A Set<String> of keys found in the configuration section of the file
	 * @since 1.1
	 * @author IballisticBoss
	 */
	@Override
	public Set<String> getKeys(boolean deep) {
		return config.getKeys(deep);
	}
	
	/**
	 * Overridden Super-Type Method
	 * Saves this configuration data to the specified file
	 * @param file the file object to write data to
	 * @throws IOException If the file encountered an error while saving
	 * @since 1.1
	 * @author IballisticBoss
	 */
	@Override
	public void save(File file) throws IOException {
		config.save(file);
	}
	
	/**
	 * Overridden Super-Type Method
	 * Checks if the configuration contains a value at the specified key path, or in the default values
	 * @param key the key path to search for the values
	 * @return <b>true</b> if the configuration contains an associated value, <b>false</b> otherwise
	 * @since 1.1
	 * @author IballisticBoss
	 */
	@Override
	public boolean contains(String key) {
		return config.contains(key);
	}
	
	/**
	 * Overridden Super-Type Method
	 * Gets the {@link ConfigurationSection} object from a specified key path
	 * @param key the key path to retrieve the configuration from
	 * @return The Configuration found at the key path
	 * @since 1.1
	 * @author IballisticBoss
	 * @see ConfigurationSection
	 */
	@Override
	public ConfigurationSection getConfigurationSection(String key) {
		return config.getConfigurationSection(key);
	}
	
	/**
	 * Overridden Super-Type Method
	 * Checks if the data at the specified key path is a {@link ConfigurationSection} 
	 * @param key the key path to check
	 * @return <b>true</b> if the data at the path is a {@link ConfigurationSection}, <b>false</b> otherwise
	 * @since 1.1
	 * @author IballisticBoss
	 * @see ConfigurationSection
	 */
	@Override
	public boolean isConfigurationSection(String key) {
		return config.isConfigurationSection(key);
	}
	
	/**
	 * Overridden Super-Type Method
	 * Checks if the data at the specified key path is a {@link Item}
	 * @param key the key path to check
	 * @return <b>true</b> if the data at the path is an {@link Item}, <b>false</b> otherwise
	 * @since 2.1
	 * @author IballisticBoss
	 * @see ConfigurationSection
	 */
	@Override
	public boolean isItemStack(String key) {
		return config.isItemStack(key);
	}
	
	/**
	 * Saves data from the internal configuration to the internal file
	 * <b>Note:</b> This will not notify anything if it fails to save successfully
	 * @since 1.1
	 * @author IballisticBoss
	 * @see YamlConfiguration
	 */
	public void save() {
		try {
			config.save(configFile);
		} catch (IOException e) {
		}
	}
	
	/**
	 * Retrieves configuration from the internal file and loads it into the internal configuration
	 * @since 1.1
	 * @author IballisticBoss
	 * @see YamlConfiguration
	 */
	public void reload() {
		config = loadConfiguration(configFile);
	}
	
	/**
	 * Saves and reloads the configuration internally
	 * @since 1.1
	 * @author IballisticBoss
	 * @see YamlConfiguration
	 */
	public void saveAndReload() {
		save();
		reload();
	}
	
}
