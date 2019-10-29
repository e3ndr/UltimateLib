/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.World;

import java.util.ArrayList;
import java.util.List;

import cf.e3ndr.UltimateLib.Wrappers.Misc.WrappedParticle;
import cf.e3ndr.UltimateLib.Wrappers.Player.NukkitPlayer;
import cf.e3ndr.UltimateLib.Wrappers.Player.WrappedPlayer;
import cn.nukkit.level.Level;
import cn.nukkit.level.Sound;
import cn.nukkit.level.particle.AngryVillagerParticle;
import cn.nukkit.level.particle.BlockForceFieldParticle;
import cn.nukkit.level.particle.BubbleParticle;
import cn.nukkit.level.particle.CriticalParticle;
import cn.nukkit.level.particle.EnchantmentTableParticle;
import cn.nukkit.level.particle.ExplodeParticle;
import cn.nukkit.level.particle.FlameParticle;
import cn.nukkit.level.particle.HappyVillagerParticle;
import cn.nukkit.level.particle.HeartParticle;
import cn.nukkit.level.particle.HugeExplodeParticle;
import cn.nukkit.level.particle.LavaParticle;
import cn.nukkit.level.particle.Particle;
import cn.nukkit.level.particle.PortalParticle;
import cn.nukkit.level.particle.RainSplashParticle;
import cn.nukkit.level.particle.RedstoneParticle;
import cn.nukkit.level.particle.SmokeParticle;
import cn.nukkit.level.particle.WaterParticle;
import cn.nukkit.math.Vector3;
import cn.nukkit.math.Vector3f;

public class NukkitWorld implements WrappedWorld {
	private Level world;
	
	public NukkitWorld(Level level) {
		this.world = level;
	}

	@Override
	public String getName() {
		return this.world.getName();
	}

	@Override
	public List<WrappedPlayer<?>> getPlayers() {
		ArrayList<WrappedPlayer<?>> ret = new ArrayList<WrappedPlayer<?>>();
		
		for (Long p : this.world.getPlayers().keySet()) ret.add(new NukkitPlayer(this.world.getPlayers().get(p)));
		
		return ret;
	}

	@Override
	public void playSound(WorldLocation loc, String sound, float volume, float pitch) {
		this.world.addSound(new Vector3f(Double.valueOf(loc.getX()).floatValue(), Double.valueOf(loc.getY()).floatValue(), Double.valueOf(loc.getX()).floatValue()).asVector3(), Sound.valueOf(sound), volume, pitch);
	}

	@Override
	public void addParticle(WorldLocation loc, WrappedParticle particle) {
		this.world.addParticle(getParticle(particle, loc));
	}
	
	public static Particle getParticle(WrappedParticle particle, WorldLocation loc) {
		Vector3 v = new Vector3f(Double.valueOf(loc.getX()).floatValue(), Double.valueOf(loc.getY()).floatValue(), Double.valueOf(loc.getX()).floatValue()).asVector3();
		switch (particle) {
			case BUBBLE: return new BubbleParticle(v); // Is there an easier way?
			case CRITICAL: return new CriticalParticle(v);
			case BLOCK_FORCE_FIELD: return new BlockForceFieldParticle(v);
			case SMOKE: return new SmokeParticle(v);
			case EXPLODE: return new ExplodeParticle(v);
			case FLAME: return new FlameParticle(v);
			case LAVA: return new LavaParticle(v);
			case REDSTONE: return new RedstoneParticle(v);
			case HUGE_EXPLODE: return new HugeExplodeParticle(v);
			case HEART: return new HeartParticle(v);
			case PORTAL: return new PortalParticle(v);
			case WATER_SPLASH: return new WaterParticle(v);
			case DRIP_WATER: return new WaterParticle(v);
			case DRIP_LAVA: return new LavaParticle(v);
			case RAIN_SPLASH: return new RainSplashParticle(v);
			case VILLAGER_ANGRY: return new AngryVillagerParticle(v);
			case VILLAGER_HAPPY: return new HappyVillagerParticle(v);
			case ENCHANTMENT_TABLE: return new EnchantmentTableParticle(v);
			
			default: return null;
		}
	}
}
