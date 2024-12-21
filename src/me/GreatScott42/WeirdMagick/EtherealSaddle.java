package me.GreatScott42.WeirdMagick;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class EtherealSaddle implements Listener {
    Main plugin;
    public EtherealSaddle(Main canCraft){
        plugin=canCraft;
    }

    @EventHandler
    public void createSaddle(PrepareAnvilEvent e){
        if(!plugin.getConfig().getBoolean("cancraftsaddle")){
            return;
        }
        Inventory inventory = e.getInventory();

        List<HumanEntity> players = inventory.getViewers();
        Player player = (Player) players.get(0);

        if(e.getView().getItem(0)!=null){
            ItemStack itemI = e.getView().getItem(0);
            if(itemI.getType()!= Material.SADDLE||e.getView().getItem(1)==null){
                return;
            }
            ItemStack itemII = e.getView().getItem(1);
            if(itemII.getType()!=Material.SOUL_LANTERN){
                return;
            }
            ItemStack saddle = new ItemStack(Material.SADDLE);
            ItemMeta saddleMeta = saddle.getItemMeta();
            saddleMeta.setDisplayName(ChatColor.GOLD+"Ethereal Saddle");

            List<String> lore = new ArrayList<>();
            lore.add("Summon a horse soul");
            saddleMeta.setLore(lore);
            saddle.setItemMeta(saddleMeta);

            if(!player.getInventory().contains(saddle)){
                player.getInventory().addItem(saddle);
                PotionEffect hunger = new PotionEffect(PotionEffectType.HUNGER,20*60,2);
                PotionEffect harm = new PotionEffect(PotionEffectType.HARM,1,1);
                player.addPotionEffect(hunger);
                player.addPotionEffect(harm);
                inventory.clear();
            }
            player.playSound(e.getInventory().getLocation(), Sound.BLOCK_ANVIL_DESTROY,100,1);
            player.playSound(e.getInventory().getLocation(),Sound.ENTITY_HORSE_DEATH,100,0.5f);
            e.getInventory().getLocation().getBlock().setType(Material.AIR);

        }
    }

    @EventHandler
    public void summonHorse(PlayerInteractEvent e){
        if(e.getClickedBlock()==null||e.getAction()!=Action.RIGHT_CLICK_BLOCK||e.getItem()==null){
            return;
        }
        if(e.getAction()==Action.RIGHT_CLICK_BLOCK&&e.getHand()== EquipmentSlot.HAND){
            Player player = e.getPlayer();
            ItemStack item = e.getItem();
            ItemStack saddle = item;
            ItemMeta itemMeta = item.getItemMeta();
            if(item.getType()==Material.SADDLE&&(player.getVehicle()==null)&&itemMeta.getDisplayName().equalsIgnoreCase(ChatColor.GOLD+"Ethereal Saddle")){
                Entity mount = player.getWorld().spawnEntity(player.getLocation(), EntityType.HORSE);

                mount.setCustomName(player.getName()+" Horse");

                Tameable tameable = (Tameable) mount;
                tameable.setTamed(true);
                Horse horse = (Horse) mount;
                horse.getInventory().setSaddle(saddle);
                mount.addPassenger(player);

                horse.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(15);
                player.getInventory().removeItem(item);

                if(player.getGameMode() == GameMode.SURVIVAL){
                    if(player.getHealth()-3>0){
                        player.setHealth(player.getHealth()-3);
                    }else{
                        player.setHealth(0);
                    }
                }
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void exitMount(VehicleExitEvent e){
        Entity mount = e.getVehicle();
        if(!(e.getExited() instanceof Player)){
            return;
        }
        Player player = (Player) e.getExited();
        if(mount.getCustomName()==null){
            return;
        }
        if(mount.getCustomName().equalsIgnoreCase(player.getName()+" Horse")){
            Horse horse = (Horse) mount;
            player.playSound(e.getVehicle().getLocation(),Sound.ENTITY_HORSE_DEATH,100,0.5f);
            horse.setHealth(0);
        }
    }

    @EventHandler
    public void dropMount(EntityDeathEvent e){
        if(e.getEntityType()==EntityType.HORSE){
            Entity mount = e.getEntity();
            Horse horse = (Horse) mount;
            if(horse.getCustomName()==null){
                return;
            }
            String playerName = mount.getCustomName().split(" ",2)[0];
            Player player = e.getEntity().getServer().getPlayerExact(playerName);
            if(player==null){
                return;
            }
            if(horse.getInventory().getSaddle()==null){
                if(mount.getCustomName().equalsIgnoreCase(player.getName()+" Horse")){
                    e.getDrops().clear();
                }
                return;
            }
            ItemStack saddle = horse.getInventory().getSaddle();
            ItemStack drop = saddle;
            ItemMeta saddleMeta = saddle.getItemMeta();

            if(saddleMeta.getDisplayName().equalsIgnoreCase(ChatColor.GOLD+"Ethereal Saddle")){
                e.getDrops().clear();
                player.getInventory().addItem(drop);
                return;
            }

        }
    }

}
