package me.GreatScott42.WeirdMagick;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Breedable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class LoveChestplate implements Listener {

    @EventHandler
    public void love(PlayerMoveEvent e){
        if(e.getPlayer().getInventory().getChestplate()==null){
            return;
        }

        if(e.getPlayer().getInventory().getChestplate().getType()==Material.IRON_CHESTPLATE&&e.getPlayer().getInventory().getChestplate().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.LIGHT_PURPLE+"Brain Melter")){
            for(Entity entity : e.getPlayer().getWorld().getNearbyEntities(e.getPlayer().getLocation(),5,5,5) ){
                if(entity instanceof LivingEntity){
                    ((LivingEntity) entity).setAI(false);
                }
            }
        }
    }
}
