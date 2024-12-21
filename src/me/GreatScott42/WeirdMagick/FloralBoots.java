package me.GreatScott42.WeirdMagick;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.HumanEntity;
//import org.bukkit.entity.Piglin;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
/*import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.inventory.meta.Damageable;*/
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;


public class FloralBoots implements Listener {

    ///
    /*@EventHandler
    public void etst(PlayerInteractEntityEvent e){
        if(e.getRightClicked() instanceof Piglin){
            //(Merchant) e.getRightClicked();
            Merchant m = (Villager) e.getPlayer().getWorld().spawnEntity(e.getPlayer().getLocation(), EntityType.VILLAGER);
            Villager m3 = (Villager) m;
            m3.setProfession(Villager.Profession.ARMORER);
            m3.setVillagerLevel(5);
            m3.setInvisible(true);
            m3.setInvulnerable(true);
            m3.setAI(false);
            m3.setGravity(false);
            m3.teleport(m3.getLocation().add(0,300,0));
            MerchantRecipe m2 = new MerchantRecipe(new ItemStack(Material.APPLE),10);
            m2.addIngredient(new ItemStack(Material.CARROT));
            m.setRecipe(0,m2);

            e.getPlayer().openMerchant(m,true);
        }

    }*/

    ///

    @EventHandler
    public void growFlower(PlayerMoveEvent e){

        if ((int) e.getFrom().getX() != (int) e.getTo().getX() || (int) e.getFrom().getY() != (int) e.getTo().getY() || (int) e.getFrom().getZ() != (int) e.getTo().getZ()) { // Check if the X, Y or Z has changed as an integer, meaning they've changed blocks.
            // They moved a block.
            if(e.getPlayer().getInventory().getBoots()==null){
                return;
            }
            if(e.getPlayer().getInventory().getBoots().getType()==Material.LEATHER_BOOTS&&e.getPlayer().getInventory().getBoots().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.LIGHT_PURPLE+"Floral Boots")){
                ItemStack boots = e.getPlayer().getInventory().getBoots();
                Location l = e.getPlayer().getLocation();
                l.setY(l.getY()-1);
                Block b = l.getBlock();
                //!
                if(!Tag.CROPS.isTagged(l.getBlock().getType())){
                    //?make damage to boots

                    b.applyBoneMeal(BlockFace.DOWN);
                }
            }
        }




    }
}
