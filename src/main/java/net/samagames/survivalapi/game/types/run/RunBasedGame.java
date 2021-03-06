package net.samagames.survivalapi.game.types.run;

import net.samagames.api.SamaGamesAPI;
import net.samagames.survivalapi.SurvivalAPI;
import net.samagames.survivalapi.modules.block.RandomChestModule;
import net.samagames.survivalapi.modules.block.RapidOresModule;
import net.samagames.survivalapi.modules.block.TorchThanCoalModule;
import net.samagames.survivalapi.modules.combat.AutomaticTNTModule;
import net.samagames.survivalapi.modules.combat.DropMyEffectsModule;
import net.samagames.survivalapi.modules.craft.*;
import net.samagames.survivalapi.modules.gameplay.*;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

/*
 * This file is part of SurvivalAPI.
 *
 * SurvivalAPI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SurvivalAPI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with SurvivalAPI.  If not, see <http://www.gnu.org/licenses/>.
 */
@FunctionalInterface
public interface RunBasedGame
{
    /**
     * Teleport the players to the deathmatch zone
     */
    void teleportDeathMatch();

    /**
     * Load the modules needed by the game
     */
    default void applyModules()
    {
        SurvivalAPI.get().loadModule(DisableLevelTwoPotionModule.class, null);
        SurvivalAPI.get().loadModule(DisableNotchAppleModule.class, null);
        SurvivalAPI.get().loadModule(DisableSpeckedMelonModule.class, null);

        SurvivalAPI.get().loadModule(RapidOresModule.class, new RapidOresModule.ConfigurationBuilder().addDefaults().build());
        SurvivalAPI.get().loadModule(RapidFoodModule.class, new RapidFoodModule.ConfigurationBuilder().addDefaults().build());
        SurvivalAPI.get().loadModule(RapidUsefulModule.class, new RapidUsefulModule.ConfigurationBuilder().addDefaults().build());
        SurvivalAPI.get().loadModule(RapidToolsModule.class, new RapidToolsModule.ConfigurationBuilder().build());
        SurvivalAPI.get().loadModule(RapidStackingModule.class, null);

        SurvivalAPI.get().loadModule(AutomaticTNTModule.class, null);
        SurvivalAPI.get().loadModule(FastTreeModule.class, null);
        SurvivalAPI.get().loadModule(PersonalBlocksModule.class, null);
        SurvivalAPI.get().loadModule(TorchThanCoalModule.class, new TorchThanCoalModule.ConfigurationBuilder().build());
        SurvivalAPI.get().loadModule(RemoveItemOnUseModule.class, null);

        ConstantPotionModule.ConfigurationBuilder constantPotionConfiguration = new ConstantPotionModule.ConfigurationBuilder();
        constantPotionConfiguration.addPotionEffect(PotionEffectType.SPEED, 0);
        constantPotionConfiguration.addPotionEffect(PotionEffectType.FAST_DIGGING, 0);

        SurvivalAPI.get().loadModule(ConstantPotionModule.class, constantPotionConfiguration.build());

        RandomChestModule.ConfigurationBuilder randomChestConfiguration = new RandomChestModule.ConfigurationBuilder();
        randomChestConfiguration.addItemWithPercentage(new ItemStack(Material.DIAMOND, 2), 40);
        randomChestConfiguration.addItemWithPercentage(new ItemStack(Material.REDSTONE, 4), 60);
        randomChestConfiguration.addItemWithPercentage(new ItemStack(Material.NETHER_STALK, 3), 55);
        randomChestConfiguration.addItemWithPercentage(new ItemStack(Material.GOLD_INGOT, 2), 50);

        SurvivalAPI.get().loadModule(RandomChestModule.class, randomChestConfiguration.build());

        DropMyEffectsModule.ConfigurationBuilder dropMyEffectsConfiguration = new DropMyEffectsModule.ConfigurationBuilder();
        dropMyEffectsConfiguration.blacklistPotionEffect(PotionEffectType.SPEED);
        dropMyEffectsConfiguration.blacklistPotionEffect(PotionEffectType.FAST_DIGGING);

        SurvivalAPI.get().loadModule(DropMyEffectsModule.class, dropMyEffectsConfiguration.build());

        SamaGamesAPI.get().getGameManager().setMaxReconnectTime(20);
    }

    /**
     * Remove the constants effects to a given player
     *
     * @param player Player
     */
    default void removeEffects(Player player)
    {
        player.removePotionEffect(PotionEffectType.SPEED);
        player.removePotionEffect(PotionEffectType.FAST_DIGGING);
    }
}
