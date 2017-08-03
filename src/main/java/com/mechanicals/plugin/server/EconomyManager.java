package com.mechanicals.plugin.server;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import com.mechanicals.plugin.MechMain;

import net.milkbowl.vault.economy.Economy;

public class EconomyManager {

	boolean economyEnabled = false;
	private Economy economy;
	
	public EconomyManager() {
		economyEnabled = setupEconomy();
		if (!economyEnabled) MechMain.plugin.logger.severe(MechMain.plugin.texts.economyNotAvailable);
	}
	
	/**
	 * Code Snippet taken from Vault-API
	 * @return <b>true</b> if the economy is successfully setup
	 */
	private boolean setupEconomy() {
		if (MechMain.plugin.getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        economy = rsp.getProvider();
        return economy != null;
	}
	
	public double getBalanceForUser(Player player) {
		if (economyEnabled) return economy.getBalance(player);
		else return 0;
	}
	
	public boolean depositUserBalance(Player player, double amount) {
		if (economyEnabled) {
			return economy.depositPlayer(player, amount).transactionSuccess();
		}
		return false;
	}
	
	public boolean withdrawUserBalance(Player player, double amount) {
		if (economyEnabled) {
			return economy.withdrawPlayer(player, amount).transactionSuccess();
		}
		return false;
	}
	
	public boolean withdrawUserBalance(Player player, double amount, boolean preventDebt) {
		if (economyEnabled && preventDebt ? amount <= getBalanceForUser(player) : true) {
			return economy.withdrawPlayer(player, amount).transactionSuccess();
		}
		return false;
	}
}
