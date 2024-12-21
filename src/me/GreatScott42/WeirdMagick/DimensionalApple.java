package me.GreatScott42.WeirdMagick;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.KnowledgeBookMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DimensionalApple implements Listener {

    Main plugin;
    public DimensionalApple(Main plugin){
        this.plugin=plugin;
    }

    @EventHandler
    public void useApple(PlayerItemConsumeEvent e){
        //got hungry for apples?
        if(e.getItem().getType()==Material.APPLE&&e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN+"Dimensional apple")){
            if(e.getPlayer().getWorld()==Bukkit.getWorld("world")){
                Location location = e.getPlayer().getLocation();
                location.setWorld(Bukkit.getWorld("world_nether"));
                e.getPlayer().getWorld().spawnParticle(Particle.PORTAL,e.getPlayer().getLocation(),300,0.5,0.5,0.5,1);
                e.getPlayer().playSound(e.getPlayer().getLocation(),Sound.ENTITY_ENDERMAN_TELEPORT,100,1);
                e.getPlayer().teleport(location);
                e.getPlayer().playSound(e.getPlayer().getLocation(),Sound.ENTITY_ENDERMAN_TELEPORT,100,1);
                e.getPlayer().getWorld().spawnParticle(Particle.PORTAL,e.getPlayer().getLocation(),300,0.5,0.5,0.5,1);
                PotionEffect slowfalling = new PotionEffect(PotionEffectType.SLOW_FALLING,20*10,1);
                PotionEffect fireresistance = new PotionEffect(PotionEffectType.FIRE_RESISTANCE,20*10,1);
                e.getPlayer().addPotionEffect(fireresistance);
                e.getPlayer().addPotionEffect(slowfalling);
                Block tobreak = location.getBlock();
                for(int i=0;i<3;i++){
                    tobreak.getRelative(0,i,0).setType(Material.AIR);
                }
            }else if(e.getPlayer().getWorld()==Bukkit.getWorld("world_nether")){
                Location location = e.getPlayer().getLocation();
                location.setWorld(Bukkit.getWorld("world"));
                e.getPlayer().getWorld().spawnParticle(Particle.PORTAL,e.getPlayer().getLocation(),300,0.5,0.5,0.5,1);
                e.getPlayer().playSound(e.getPlayer().getLocation(),Sound.ENTITY_ENDERMAN_TELEPORT,100,1);
                e.getPlayer().teleport(location);
                e.getPlayer().playSound(e.getPlayer().getLocation(),Sound.ENTITY_ENDERMAN_TELEPORT,100,1);
                e.getPlayer().getWorld().spawnParticle(Particle.PORTAL,e.getPlayer().getLocation(),300,0.5,0.5,0.5,1);
                PotionEffect slowfalling = new PotionEffect(PotionEffectType.SLOW_FALLING,20*10,1);
                PotionEffect fireresistance = new PotionEffect(PotionEffectType.FIRE_RESISTANCE,20*10,1);
                e.getPlayer().addPotionEffect(fireresistance);
                e.getPlayer().addPotionEffect(slowfalling);
                Block tobreak = location.getBlock();
                for(int i=0;i<3;i++){
                    tobreak.getRelative(0,i,0).setType(Material.AIR);
                }
            }
        }
    }

    @EventHandler
    public void dropDA(BlockBreakEvent e){
        if(!plugin.getConfig().getBoolean("warpedwartblockdropdimensionalapple"))
        {
            return;
        }
        if(e.getBlock().getType()==Material.WARPED_WART_BLOCK){

            Random random = new Random();
            int prob = random.nextInt(100);
            if(prob<plugin.getConfig().getInt("dropchancedimensionalapple")){
                e.setDropItems(false);
                ItemStack apple = new ItemStack(Material.APPLE);
                ItemMeta applemeta = apple.getItemMeta();
                applemeta.setDisplayName(ChatColor.GREEN+"Dimensional apple");
                apple.setItemMeta(applemeta);

                e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),apple);
            }
        }

    }
}
