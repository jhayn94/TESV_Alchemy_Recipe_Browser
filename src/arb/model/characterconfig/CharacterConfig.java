package arb.model.characterconfig;

import arb.factories.ModelFactory;
import arb.model.entity.Effect;
import arb.model.entity.Potion;

/**
 * This class acts as a facade for all other character config classes. This
 * avoids the need to instantiate one of each.
 */
public class CharacterConfig {

	private final BaseGameCharacterConfig baseGameCharacterConfig;

	private final OrdinatorCharacterConfig ordinatorCharacterConfig;

	private final SharedCharacterConfig sharedCharacterConfig;

	public CharacterConfig() {
		this.baseGameCharacterConfig = ModelFactory.getInstance().createBaseGameCharacterConfig();
		this.ordinatorCharacterConfig = ModelFactory.getInstance().createOrdinatorCharacterConfig();
		this.sharedCharacterConfig = ModelFactory.getInstance().createSharedCharacterConfig();
	}

	public void resetBaseGameCharacterConfig() {
		this.baseGameCharacterConfig.reset();
	}

	public void resetOrdinatorCharacterConfig() {
		this.ordinatorCharacterConfig.reset();
	}

	public void resetSharedCharacterConfig() {
		this.sharedCharacterConfig.reset();
	}

	public double getAlchemySkillLevelMultiplier() {
		return this.sharedCharacterConfig.getAlchemySkillLevelMultiplier();
	}

	public double getCharacterConfigMultiplier(final Potion potion, final Effect effect) {
		return this.getBaseGameTotalMultiplier(effect) * this.getOrdinatorTotalMultiplier(potion, effect)
				* this.getSharedTotalMultiplier();
	}

	private double getBaseGameTotalMultiplier(final Effect effect) {
		return this.baseGameCharacterConfig.getAlchemistMultiplier()
				* this.baseGameCharacterConfig.getPhysicianMultiplier(effect)
				* this.baseGameCharacterConfig.getBenefactorMultiplier() * this.baseGameCharacterConfig.getPoisonerMultiplier();
	}

	private double getOrdinatorTotalMultiplier(final Potion potion, final Effect effect) {
		final int skillLevel = this.sharedCharacterConfig.getSkillLevel();
		return this.ordinatorCharacterConfig.getAlchemyMasteryMultiplier()
				* this.ordinatorCharacterConfig.getPhysicianMultiplier(effect)
				* this.ordinatorCharacterConfig.getAdvancedLabMultiplier()
				* this.ordinatorCharacterConfig.getPoisonerMultiplier(potion, effect, skillLevel)
				* this.ordinatorCharacterConfig.getTWDNKYMultiplier();
	}

	private double getSharedTotalMultiplier() {
		return this.sharedCharacterConfig.getTotalEnchantmentMultiplier()
				* this.sharedCharacterConfig.getSeekerOfShadowsMultiplier();
	}

}
