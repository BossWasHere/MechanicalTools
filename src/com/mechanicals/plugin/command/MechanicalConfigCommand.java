package com.mechanicals.plugin.command;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.bukkit.command.CommandSender;

import com.mechanicals.plugin.RegisteredCommand;
import com.mechanicals.plugin.configuration.ConfigurationUnit;
import com.mechanicals.plugin.task.extra.ConfirmCooldown;
import com.mechanicals.plugin.utils.StringUtils;

import net.md_5.bungee.api.ChatColor;

/**
 * Sub-handler of the {@link CommandHandler} class
 *
 */
public class MechanicalConfigCommand extends RegisteredCommand {
	
	public MechanicalConfigCommand() {
		super();
	}
	
	public void run(CommandSender sender, String[] args) {
		if (sender.hasPermission("mechanical.config")) {
			if (args.length < 1) {
				sender.sendMessage(ChatColor.RED + "Usage: /mechanicalconfig <view/set/reset/confirm> <root> [value]");
			} else {
				if (args[0].equalsIgnoreCase("view")) {
					if (args.length < 2) {
						sender.sendMessage(ChatColor.RED + "Please specify a configuration root (e.g. text or blockdata.treeCutter.trigger)");
					} else {
						String fileRoot = args[1];
						if (fileRoot.toLowerCase().startsWith("text") || fileRoot.toLowerCase().startsWith("blockdata") || fileRoot.toLowerCase().startsWith("itemdata") || fileRoot.toLowerCase().startsWith("config")) {
							String[] conf = getConfigurationInfo(fileRoot);
							sender.sendMessage(ChatColor.DARK_AQUA + ">> Root: " + fileRoot);
							for (String c : conf) {
								sender.sendMessage(ChatColor.AQUA + c);
							}
						} else {
							sender.sendMessage(ChatColor.RED + "Please specify a configuration root (valid starters are 'text', 'blockdata', 'itemdata', 'config')");
						}
					}
				} else if (args[0].equalsIgnoreCase("set")) {
					if (sender.hasPermission("mechanical.config.change")) {
						if (args.length < 2) {
							sender.sendMessage(ChatColor.RED + "Please specify a configuration root (e.g. text.noPermission or blockdata.particle)");
						} else {
							String fileRoot = args[1];
							if (fileRoot.toLowerCase().startsWith("text") || fileRoot.toLowerCase().startsWith("blockdata") || fileRoot.toLowerCase().startsWith("textdata") || fileRoot.toLowerCase().startsWith("config")) {
								if (args.length < 3) {
									sender.sendMessage(ChatColor.RED + "Please specify a replacement value\nExample Value Types: text=string, 0=int, [\"list\", \"element\"]=stringlist, true=boolean");
								} else {
									
									String replacement = "";
									for (int i = 2; i < args.length; i++) {
										replacement += args[i] + " ";
									}
									if (replacement == "") {
										sender.sendMessage(ChatColor.RED + "Please specify a replacement value\nExample Value Types: text=string, 0=int, [\"list\", \"element\"]=stringlist, true=boolean");
										return;
									}
									setConfiguration(sender, fileRoot, replacement.substring(0, replacement.length() - 1));
								}
							} else {
								sender.sendMessage(ChatColor.RED + "Please specify a configuration root (valid starters are 'text', 'blockdata', 'itemdata', 'config')");
							}
						}
					} else {
						sender.sendMessage(plugin.texts.noPermission);
					}
				} else if (args[0].equalsIgnoreCase("confirm")) {
					for (ConfirmCooldown c : plugin.cooldowns) {
						if (c.checkID(sender.getName(), 1)) {
							if (c.isCooled()) {
								sender.sendMessage(ChatColor.RED + "Your task has already cooled down! (You may have multiple items in your queue - try again)");
								removeCooldown(c);
								return;
							} else {
								String data = c.getData();
								if (data.toLowerCase().startsWith("text.") && data.contains(":")) {
									data = data.substring(5);
									if (StringUtils.isNumeric(data.split(":")[1])) {
										plugin.textData.set(data.split(":")[0], StringUtils.getNumber(data.split(":")[1]));
									} else if (StringUtils.isStringList(data.split(":")[1])) {
										plugin.textData.set(data.split(":")[0], StringUtils.getStringList(data.split(":")[1]));
									} else {
										if (data.split(":")[1].equalsIgnoreCase("null")) plugin.textData.set(data.split(":")[0], null);
										else plugin.textData.set(data.split(":")[0], data.split(":")[1]);
									}
									plugin.textData.saveAndReload();
									sender.sendMessage(ChatColor.GREEN + "Successfully updated text.yml/" + data.split(":")[0] + " to " + (data.split(":")[1].equalsIgnoreCase("null") ? "NULL (reset)" : data.split(":")[1]));
									sender.sendMessage(ChatColor.DARK_AQUA + "It is recommended that you restart or /reload the server now for changes to work!");
								} else if (data.toLowerCase().startsWith("blockdata.") && data.contains(":")) {
									data = data.substring(10);
									if (StringUtils.isNumeric(data.split(":")[1])) {
										plugin.blockData.set(data.split(":")[0], StringUtils.getNumber(data.split(":")[1]));
									} else if (StringUtils.isStringList(data.split(":")[1])) {
										plugin.blockData.set(data.split(":")[0], StringUtils.getStringList(data.split(":")[1]));
									} else {
										if (data.split(":")[1].equalsIgnoreCase("null")) plugin.blockData.set(data.split(":")[0], null);
										else plugin.blockData.set(data.split(":")[0], data.split(":")[1]);
									}
									plugin.blockData.saveAndReload();
									sender.sendMessage(ChatColor.GREEN + "Successfully updated blockdata.yml/" + data.split(":")[0] + " to " + (data.split(":")[1].equalsIgnoreCase("null") ? "NULL (reset)" : data.split(":")[1]));
									sender.sendMessage(ChatColor.DARK_AQUA + "It is recommended that you restart or /reload the server now for changes to work!");
								} else if (data.toLowerCase().startsWith("itemdata.") && data.contains(":")) {
									data = data.substring(9);
									if (StringUtils.isNumeric(data.split(":")[1])) {
										plugin.itemData.set(data.split(":")[0], StringUtils.getNumber(data.split(":")[1]));
									} else if (StringUtils.isStringList(data.split(":")[1])) {
										plugin.itemData.set(data.split(":")[0], StringUtils.getStringList(data.split(":")[1]));
									} else {
										if (data.split(":")[1].equalsIgnoreCase("null")) plugin.itemData.set(data.split(":")[0], null);
										else plugin.itemData.set(data.split(":")[0], data.split(":")[1]);
									}
									plugin.itemData.saveAndReload();
									sender.sendMessage(ChatColor.GREEN + "Successfully updated itemdata.yml/" + data.split(":")[0] + " to " + (data.split(":")[1].equalsIgnoreCase("null") ? "NULL (reset)" : data.split(":")[1]));
									sender.sendMessage(ChatColor.DARK_AQUA + "It is recommended that you restart or /reload the server now for changes to work!");
								} else if (data.toLowerCase().startsWith("config.") && data.contains(":")) {
									data = data.substring(7);
									if (StringUtils.isNumeric(data.split(":")[1])) {
										plugin.config.set(data.split(":")[0], StringUtils.getNumber(data.split(":")[1]));
									} else if (StringUtils.isStringList(data.split(":")[1])) {
										plugin.config.set(data.split(":")[0], StringUtils.getStringList(data.split(":")[1]));
									} else {
										if (data.split(":")[1].equalsIgnoreCase("null")) plugin.config.set(data.split(":")[0], null);
										else plugin.config.set(data.split(":")[0], data.split(":")[1]);
									}
									plugin.config.saveAndReload();
									sender.sendMessage(ChatColor.GREEN + "Successfully updated config.yml/" + data.split(":")[0] + " to " + (data.split(":")[1].equalsIgnoreCase("null") ? "NULL (reset)" : data.split(":")[1]));
									sender.sendMessage(ChatColor.DARK_AQUA + "It is recommended that you restart or /reload the server now for changes to work!");
								} else {
									sender.sendMessage(ChatColor.RED + "Invalid confirmation (something went wrong)");
								}
								removeCooldown(c);
								return;
							}
						} else if (c.checkID(sender.getName(), 2)) {
							if (c.isCooled()) {
								sender.sendMessage(ChatColor.RED + "Your task has already cooled down! (You may have multiple items in your queue - try again)");
								removeCooldown(c);
								return;
							} else {
								if (plugin.textData.reset()) {
									sender.sendMessage(ChatColor.GREEN + "Successfully reset text.yml!");
									sender.sendMessage(ChatColor.DARK_AQUA + "It is recommended that you restart or /reload the server now for changes to work!");
								} else sender.sendMessage(ChatColor.DARK_RED + "An error has occurred while trying to reset the configuration for text.yml");
								removeCooldown(c);
								return;
							}
						} else if (c.checkID(sender.getName(), 3)) {
							if (c.isCooled()) {
								sender.sendMessage(ChatColor.RED + "Your task has already cooled down! (You may have multiple items in your queue - try again)");
								removeCooldown(c);
								return;
							} else {
								if (plugin.blockData.reset()) {
									sender.sendMessage(ChatColor.GREEN + "Successfully reset blockdata.yml!");
									sender.sendMessage(ChatColor.DARK_AQUA + "It is recommended that you restart or /reload the server now for changes to work!");
								} else sender.sendMessage(ChatColor.DARK_RED + "An error has occurred while trying to reset the configuration for blockdata.yml");
								removeCooldown(c);
								return;
							}
						} else if (c.checkID(sender.getName(), 4)) {
							if (c.isCooled()) {
								sender.sendMessage(ChatColor.RED + "Your task has already cooled down! (You may have multiple items in your queue - try again)");
								removeCooldown(c);
								return;
							} else {
								if (plugin.blockData.reset()) {
									sender.sendMessage(ChatColor.GREEN + "Successfully reset itemdata.yml!");
									sender.sendMessage(ChatColor.DARK_AQUA + "It is recommended that you restart or /reload the server now for changes to work!");
								} else sender.sendMessage(ChatColor.DARK_RED + "An error has occurred while trying to reset the configuration for itemdata.yml");
								removeCooldown(c);
								return;
							}
						} else if (c.checkID(sender.getName(), 5)) {
							if (c.isCooled()) {
								sender.sendMessage(ChatColor.RED + "Your task has already cooled down! (You may have multiple items in your queue - try again)");
								removeCooldown(c);
								return;
							} else {
								if (plugin.blockData.reset()) {
									sender.sendMessage(ChatColor.GREEN + "Successfully reset config.yml!");
									sender.sendMessage(ChatColor.DARK_AQUA + "It is recommended that you restart or /reload the server now for changes to work!");
								} else sender.sendMessage(ChatColor.DARK_RED + "An error has occurred while trying to reset the configuration for config.yml");
								removeCooldown(c);
								return;
							}
						}
					}
				} else if (args[0].equalsIgnoreCase("reset")) {
					if (sender.hasPermission("mechanical.config.change")) {
						if (args.length < 2) {
							sender.sendMessage(ChatColor.RED + "Usage: /mechanicalconfig reset <text/blockdata/itemdata/config>");
						} else {
							if (args[1].equalsIgnoreCase("text")) {
								sender.sendMessage(ChatColor.AQUA + "Please enter /mechanicalconfig confirm to reset text.yml (10s left)");
								ConfirmCooldown cool = new ConfirmCooldown(sender.getName(), "text:reset", 2, 10);
								cool.runTaskTimerAsynchronously(plugin, 0, 20);
								addCooldown(cool);
							} else if (args[1].equalsIgnoreCase("blockdata")) {
								sender.sendMessage(ChatColor.AQUA + "Please enter /mechanicalconfig confirm to reset blockdata.yml (10s left)");
								ConfirmCooldown cool = new ConfirmCooldown(sender.getName(), "blockdata:reset", 3, 10);
								cool.runTaskTimerAsynchronously(plugin, 0, 20);
								addCooldown(cool);
								
							} else if (args[1].equalsIgnoreCase("itemdata")) {
								sender.sendMessage(ChatColor.AQUA + "Please enter /mechanicalconfig confirm to reset itemdata.yml (10s left)");
								ConfirmCooldown cool = new ConfirmCooldown(sender.getName(), "itemdata:reset", 4, 10);
								cool.runTaskTimerAsynchronously(plugin, 0, 20);
								addCooldown(cool);
								
							} else if (args[1].equalsIgnoreCase("config")) {
								sender.sendMessage(ChatColor.AQUA + "Please enter /mechanicalconfig confirm to reset config.yml (10s left)");
								ConfirmCooldown cool = new ConfirmCooldown(sender.getName(), "config:reset", 5, 10);
								cool.runTaskTimerAsynchronously(plugin, 0, 20);
								addCooldown(cool);
								
							} else {
								sender.sendMessage(ChatColor.RED + "Usage: /mechanicalconfig reset <text/blockdata/itemdata/config>");
							}
						}
					} else {
						sender.sendMessage(plugin.texts.noPermission);
					}
				} else {
					sender.sendMessage(ChatColor.RED + "Usage: /mechanicalconfig <view/set/reset/confirm> <root> [value]");
				}
			}
		} else {
			sender.sendMessage(plugin.texts.noPermission);
		}
	}
	
	/**
	 * Updates configuration from raw data types
	 * @param plugin the main class of the plugin
	 * @param sender the object which sent the command
	 * @param path the path of the YAML file, to which it should be updated
	 * @param value the value to set or replace at the path
	 * @since 1.4
	 * @author IballisticBoss
	 * @see ConfigurationUnit
	 */
	protected void setConfiguration(CommandSender sender, String path, String value) {
		if (value == null) value = "";
		if (path.toLowerCase().startsWith("text")) {
			path = path.substring(4);
			if (!path.equals("")) {
				path = path.substring(1);
				if (plugin.textData.isConfigurationSection(path)) {
					sender.sendMessage(ChatColor.RED + "The specified path is a configuration section! (Please specify an absolute path)");
				} else {
					sender.sendMessage(ChatColor.AQUA + "Please enter /mechanicalconfig confirm to save changes (10s left)");
					ConfirmCooldown cool = new ConfirmCooldown(sender.getName(), "text." + path + ":" + value, 1, 10);
					cool.runTaskTimerAsynchronously(plugin, 0, 20);
					addCooldown(cool);
				}
			} else {
				sender.sendMessage(ChatColor.RED + "Invalid path!");
			}
		} else if (path.toLowerCase().startsWith("blockdata")) {
			path = path.substring(9);
			if (!path.equals("")) {
				path = path.substring(1);
				if (plugin.blockData.isConfigurationSection(path)) {
					sender.sendMessage(ChatColor.RED + "The specified path is a configuration section! (Please specify an absolute path)");
				} else {
					sender.sendMessage(ChatColor.AQUA + "Please enter /mechanicalconfig confirm to save changes (10s left)");
					ConfirmCooldown cool = new ConfirmCooldown(sender.getName(), "blockdata." + path + ":" + value, 1, 10);
					cool.runTaskTimerAsynchronously(plugin, 0, 20);
					addCooldown(cool);
				}
			} else {
				sender.sendMessage(ChatColor.RED + "Invalid path!");
			}
		} else if (path.toLowerCase().startsWith("itemdata")) {
			path = path.substring(8);
			if (!path.equals("")) {
				path = path.substring(1);
				if (plugin.itemData.isConfigurationSection(path)) {
					sender.sendMessage(ChatColor.RED + "The specified path is a configuration section! (Please specify an absolute path)");
				} else {
					sender.sendMessage(ChatColor.AQUA + "Please enter /mechanicalconfig confirm to save changes (10s left)");
					ConfirmCooldown cool = new ConfirmCooldown(sender.getName(), "itemdata." + path + ":" + value, 1, 10);
					cool.runTaskTimerAsynchronously(plugin, 0, 20);
					addCooldown(cool);
				}
			} else {
				sender.sendMessage(ChatColor.RED + "Invalid path!");
			}
		} else if (path.toLowerCase().startsWith("config")) {
			path = path.substring(6);
			if (!path.equals("")) {
				path = path.substring(1);
				if (plugin.config.isConfigurationSection(path)) {
					sender.sendMessage(ChatColor.RED + "The specified path is a configuration section! (Please specify an absolute path)");
				} else {
					sender.sendMessage(ChatColor.AQUA + "Please enter /mechanicalconfig confirm to save changes (10s left)");
					ConfirmCooldown cool = new ConfirmCooldown(sender.getName(), "config." + path + ":" + value, 1, 10);
					cool.runTaskTimerAsynchronously(plugin, 0, 20);
					addCooldown(cool);
				}
			} else {
				sender.sendMessage(ChatColor.RED + "Invalid path!");
			}
		}
	}
	
	/**
	 * Retrieves configuration from a raw path from a YAML file
	 * @param plugin the main class of the plugin
	 * @param path the path of the YAML file to the requested value
	 * @return The data retrieved in String[], where the key is getConfigurationInfo().split(":")[0] and the value is [1]
	 * @since 1.4
	 * @author IballisticBoss
	 * @see ConfigurationUnit
	 */
	protected String[] getConfigurationInfo(String path) {
		if (path.toLowerCase().startsWith("text")) {
			path = path.substring(4);
			if (path.equals("")) {
				Set<String> collect =  plugin.textData.getKeys(true);
				Map<String, String> data = new HashMap<>();
				for (String key : collect) {
					data.put(key, plugin.textData.getString(key));
				}
				return StringUtils.putKeysWithValues(data);
			} else {
				path = path.substring(1);
				if (plugin.textData.getString(path) != null) {
					if (plugin.textData.isConfigurationSection(path)){
						Set<String> collect = plugin.textData.getConfigurationSection(path).getKeys(true);
						Map<String, String> data = new HashMap<>();
						for (String key : collect) {
							if (plugin.textData.getString(path + "." + key).toLowerCase().startsWith("memorysection[path")) continue;
							data.put(key, plugin.textData.getString(path + "." + key));
						}
						return StringUtils.putKeysWithValues(data);
					}
					return new String[] {path + ": " + plugin.textData.getString(path)};
				} else {
					return new String[] {path + ": Unrecognised value"};
				}
			}
		} else if (path.toLowerCase().startsWith("blockdata")) {
			path = path.substring(9);
			if (path.equals("")) {
				Set<String> collect =  plugin.blockData.getKeys(true);
				Map<String, String> data = new HashMap<>();
				for (String key : collect) {
					data.put(key, plugin.blockData.getString(key));
				}
				return StringUtils.putKeysWithValues(data);
			} else {
				path = path.substring(1);
				if (plugin.blockData.getString(path) != null) {
					if (plugin.blockData.isConfigurationSection(path)){
						Set<String> collect = plugin.blockData.getConfigurationSection(path).getKeys(true);
						Map<String, String> data = new HashMap<>();
						for (String key : collect) {
							if (plugin.blockData.getString(path + "." + key).toLowerCase().startsWith("memorysection[path")) continue;
							data.put(key, plugin.blockData.getString(path + "." + key));
						}
						return StringUtils.putKeysWithValues(data);
					}
					return new String[] {path + ": " + plugin.blockData.getString(path)};
				} else {
					return new String[] {path + ": Unrecognised value"};
				}
			}
		} else if (path.toLowerCase().startsWith("itemdata")) {
			path = path.substring(8);
			if (path.equals("")) {
				Set<String> collect =  plugin.itemData.getKeys(true);
				Map<String, String> data = new HashMap<>();
				for (String key : collect) {
					data.put(key, plugin.itemData.getString(key));
				}
				return StringUtils.putKeysWithValues(data);
			} else {
				path = path.substring(1);
				if (plugin.itemData.getString(path) != null) {
					if (plugin.itemData.isConfigurationSection(path)){
						Set<String> collect = plugin.itemData.getConfigurationSection(path).getKeys(true);
						Map<String, String> data = new HashMap<>();
						for (String key : collect) {
							if (plugin.itemData.getString(path + "." + key).toLowerCase().startsWith("memorysection[path")) continue;
							data.put(key, plugin.itemData.getString(path + "." + key));
						}
						return StringUtils.putKeysWithValues(data);
					}
					return new String[] {path + ": " + plugin.itemData.getString(path)};
				} else {
					return new String[] {path + ": Unrecognised value"};
				}
			}
		} else if (path.toLowerCase().startsWith("config")) {
			path = path.substring(6);
			if (path.equals("")) {
				Set<String> collect =  plugin.config.getKeys(true);
				Map<String, String> data = new HashMap<>();
				for (String key : collect) {
					data.put(key, plugin.config.getString(key));
				}
				return StringUtils.putKeysWithValues(data);
			} else {
				path = path.substring(1);
				if (plugin.config.getString(path) != null) {
					if (plugin.config.isConfigurationSection(path)){
						Set<String> collect = plugin.config.getConfigurationSection(path).getKeys(true);
						Map<String, String> data = new HashMap<>();
						for (String key : collect) {
							if (plugin.config.getString(path + "." + key).toLowerCase().startsWith("memorysection[path")) continue;
							data.put(key, plugin.config.getString(path + "." + key));
						}
						return StringUtils.putKeysWithValues(data);
					}
					return new String[] {path + ": " + plugin.config.getString(path)};
				} else {
					return new String[] {path + ": Unrecognised value"};
				}
			}
		}
		return new String[] {"Invalid Entry"};
	}
	
	public synchronized void removeCooldown(ConfirmCooldown c) {
		synchronized (plugin.cooldowns) {
			plugin.cooldowns.remove(c);
		}
	}
	
	public synchronized void addCooldown(ConfirmCooldown c) {
		synchronized (plugin.cooldowns) {
			plugin.cooldowns.add(c);
		}
	}
}
