package me.GreatScott42.WeirdMagick;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AtackWand implements Listener {
    Main plugin;
    public AtackWand(Main canmake){
        plugin=canmake;
    }

    @EventHandler
    public void changeSpell(PlayerToggleSneakEvent e){
        if(e.getPlayer().getInventory().getItemInMainHand()==null||e.getPlayer().getInventory().getItemInMainHand().getType()!=Material.STICK){
            return;
        }
        if(e.isSneaking()){
            String fireball, freeze, lightning;
            fireball=ChatColor.GRAY+"Wand"+ChatColor.RED+" (Fireball)";
            freeze=ChatColor.GRAY+"Wand"+ChatColor.BLUE+" (Freeze)";
            lightning=ChatColor.GRAY+"Wand"+ChatColor.AQUA+" (Lightning)";

            ItemStack wand = e.getPlayer().getInventory().getItemInMainHand();
            ItemMeta wandMeta = wand.getItemMeta();

            if(wandMeta.getDisplayName().equalsIgnoreCase(fireball)){
                wandMeta.setDisplayName(freeze);
                wand.setItemMeta(wandMeta);
            }else if(wandMeta.getDisplayName().equalsIgnoreCase(freeze)){
                wandMeta.setDisplayName(lightning);
                wand.setItemMeta(wandMeta);
            }else if(wandMeta.getDisplayName().equalsIgnoreCase(lightning)){
                wandMeta.setDisplayName(fireball);
                wand.setItemMeta(wandMeta);
            }
        }
    }
    @EventHandler
    public void castSpell(PlayerInteractEvent e){
        if(e.getPlayer().getFoodLevel()<0||e.getItem()==null){
            return;
        }
        String fireball, freeze, lightning;
        fireball=ChatColor.GRAY+"Wand"+ChatColor.RED+" (Fireball)";
        freeze=ChatColor.GRAY+"Wand"+ChatColor.BLUE+" (Freeze)";
        lightning=ChatColor.GRAY+"Wand"+ChatColor.AQUA+" (Lightning)";
        ItemStack wand = e.getItem();
        if(wand.getType()!=Material.STICK){
            return;
        }
        if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(fireball)){
            if(e.getAction()==Action.RIGHT_CLICK_AIR){
                if(e.getPlayer().getGameMode()== GameMode.SURVIVAL){
                    e.getPlayer().setFoodLevel(e.getPlayer().getFoodLevel()-2);
                }
                Location playerLoc = e.getPlayer().getLocation();
                Location firePoint = playerLoc;
                firePoint.setY(firePoint.getBlockY()+1);
                Entity spell = e.getPlayer().getWorld().spawnEntity(firePoint, EntityType.FIREBALL);
                Fireball entityfb = (Fireball) spell;
                entityfb.setVelocity(playerLoc.getDirection().multiply(2));
            }else if(e.getAction()==Action.RIGHT_CLICK_BLOCK){
                if(e.getBlockFace()==BlockFace.UP){
                    Location block = e.getClickedBlock().getLocation();
                    block.setY(block.getBlockY()+1);
                    block.getBlock().setType(Material.FIRE);
                }
            }
        }else if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(freeze)){
            if(e.getPlayer().getGameMode()==GameMode.SURVIVAL){
                e.getPlayer().setFoodLevel(e.getPlayer().getFoodLevel()-1);
            }
            Location playerLoc = e.getPlayer().getLocation();
            Location firePoint = playerLoc;
            firePoint.setY(firePoint.getBlockY()+2);
            Entity spell = e.getPlayer().getPlayer().getWorld().spawnEntity(firePoint,EntityType.SNOWBALL);
            Snowball snowball = (Snowball) spell;
            snowball.setCustomName(ChatColor.GREEN+"freezeball");
            snowball.setVelocity(playerLoc.getDirection().multiply(4));
        }else if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(lightning)){
            if(e.getPlayer().getGameMode()==GameMode.SURVIVAL){
                e.getPlayer().setFoodLevel(e.getPlayer().getFoodLevel()-3);
            }
            Location playerLoc = e.getPlayer().getLocation();
            Location firePoint = playerLoc;
            firePoint.setY(firePoint.getBlockY()+1);
            Entity lightningBolt = e.getPlayer().getPlayer().getWorld().spawnEntity(firePoint,EntityType.LLAMA_SPIT);
            LlamaSpit llamaSpit = (LlamaSpit) lightningBolt;
            llamaSpit.setCustomName(ChatColor.GREEN+"spitbolt");
            llamaSpit.setVelocity(playerLoc.getDirection().multiply(8));
        }
    }
    @EventHandler
    public void spellProjectiles(ProjectileHitEvent e){
        if(e.getEntity().getCustomName()==null){
            return;
        }
        if(e.getEntity().getCustomName().equalsIgnoreCase(ChatColor.GREEN+"freezeball")){
            Entity freezedentity = e.getHitEntity();
            if(freezedentity==null){
                return;
            }
            freezedentity.setFreezeTicks(1000);
        }else if((e.getEntity().getCustomName().equalsIgnoreCase(ChatColor.GREEN+"spitbolt")&&(e.getHitBlock()!=null))){
            e.getHitBlock().getWorld().spawnEntity(e.getHitBlock().getLocation(),EntityType.LIGHTNING);
        }
    }
    @EventHandler
    public void wandCreation(EntityDeathEvent e){
        if(!plugin.getConfig().getBoolean("canmakewand")){
            return;
        }
        if(e.getEntityType()==EntityType.WITHER){
            if(e.getEntity().getKiller().getInventory().getItemInMainHand().getType()==Material.STICK){
                if(e.getEntity().getKiller().getInventory().getItemInMainHand().getAmount()==1){
                    ItemStack wand = e.getEntity().getKiller().getInventory().getItemInMainHand();
                    ItemMeta wandmeta = wand.getItemMeta();

                    wandmeta.setDisplayName(ChatColor.GRAY+"Wand"+ChatColor.RED+" (Fireball)");
                    wand.setItemMeta(wandmeta);
                    e.getEntity().getKiller().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(18);
                }else if(e.getEntity().getKiller().getInventory().getItemInMainHand().getAmount()>1){
                    e.getEntity().getKiller().getInventory().getItemInMainHand().setAmount(e.getEntity().getKiller().getInventory().getItemInMainHand().getAmount()-1);
                    ItemStack wand = new ItemStack(Material.STICK);
                    ItemMeta wandmeta = wand.getItemMeta();

                    wandmeta.setDisplayName(ChatColor.GRAY+"Wand"+ChatColor.RED+" (Fireball)");
                    wand.setItemMeta(wandmeta);
                    e.getEntity().getKiller().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(18);
                    e.getEntity().getKiller().getInventory().addItem(wand);
                }

            }
        }
    }
}
