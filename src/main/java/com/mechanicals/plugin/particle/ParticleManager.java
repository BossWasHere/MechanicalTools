package com.mechanicals.plugin.particle;

import org.bukkit.Particle;
import org.bukkit.World;

/**
 * Basic Particle Manager Class
 *
 */
public class ParticleManager {

	/**
	 * Shows particles centred at a block position
	 * @param particle the type of particle to display
	 * @param world the world in which the particles will be displayed
	 * @param x the x coordinate where the particles will be displayed
	 * @param y the y coordinate where the particles will be displayed
	 * @param z the z coordinate where the particles will be displayed
	 * @since 1.3.1
	 * @author IballisticBoss
	 */
	public static void showBaseParticles(Particle particle, World world, int x, int y, int z) {
		world.spawnParticle(particle, x + 0.5, y + 0.5, z + 0.5, 1, 0.01, 0.01, 0.01);
	}
	
	/**
	 * Shows particles centred at a block position
	 * @param particle the type of particle to display
	 * @param world the world in which the particles will be displayed
	 * @param x the x coordinate where the particles will be displayed
	 * @param y the y coordinate where the particles will be displayed
	 * @param z the z coordinate where the particles will be displayed
	 * @param dx the maximum x random offset
	 * @param dy the maximum y random offset
	 * @param dz the maximum z random offset
	 * @param speed the speed of the particle effect
	 * @since 2.1
	 * @author IballisticBoss
	 */
	public static void showParticle(Particle particle, World world, double x, double y, double z, double dx, double dy, double dz, int speed) {
		world.spawnParticle(particle, x, y, z, speed, dx, dy, dz);
	}
	
	//TODO Particles and update to Github
	/**
	 * Shows particles centred at a block position
	 * @param particle the type of particle to display
	 * @param world the world in which the particles will be displayed
	 * @param x the x coordinate where the particles will be displayed
	 * @param y the y coordinate where the particles will be displayed
	 * @param z the z coordinate where the particles will be displayed
	 * @param dx the maximum x random offset
	 * @param dy the maximum y random offset
	 * @param dz the maximum z random offset
	 * @param speed the speed of the particle effect
	 * @since 2.1
	 * @author IballisticBoss
	 */
	public static void showParticleCentral(Particle particle, World world, int x, int y, int z, double dx, double dy, double dz, int speed) {
		showParticle(particle, world, x + 0.5, y + 0.5, z + 0.5, dx, dy, dz, speed);
	}
}