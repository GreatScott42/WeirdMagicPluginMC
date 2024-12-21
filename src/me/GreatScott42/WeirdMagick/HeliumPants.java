package me.GreatScott42.WeirdMagick;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;



public class HeliumPants implements Listener {


    @EventHandler
    public void floating(PlayerMoveEvent e){
        Location l = e.getPlayer().getLocation();
        l.setY(l.getY()-2);
        Block minLimit = l.getBlock();
        if(e.getPlayer().getInventory().getLeggings()==null){
            return;
        }

        if(e.getPlayer().getInventory().getLeggings().getType()==Material.CHAINMAIL_LEGGINGS){
            ItemStack pants = new ItemStack(Material.CHAINMAIL_LEGGINGS);
            ItemMeta metapants = pants.getItemMeta();
            metapants.setDisplayName(ChatColor.LIGHT_PURPLE+"Helium Leggings");
            pants.setItemMeta(metapants);
            if(e.getPlayer().getInventory().getLeggings().getItemMeta().getDisplayName().equalsIgnoreCase(pants.getItemMeta().getDisplayName())){
                if(minLimit.getType()!=Material.AIR){
                    PotionEffect levitation = new PotionEffect(PotionEffectType.LEVITATION,20*1,1,false,false);
                    e.getPlayer().addPotionEffect(levitation);
                }else{
                    PotionEffect slowfalling = new PotionEffect(PotionEffectType.SLOW_FALLING,20*1,1,false,false);
                    e.getPlayer().addPotionEffect(slowfalling);
                }
            }

        }
    }
}
