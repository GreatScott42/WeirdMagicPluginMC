package me.GreatScott42.WeirdMagick;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class CursedHelmet implements Listener {

    private Main plugin;

    public CursedHelmet(Main plugin){
        this.plugin=plugin;
    }



    @EventHandler
    public void putHelmet(PlayerMoveEvent e){
        if(e.getPlayer().getInventory().getHelmet()==null){
            return;
        }
        if(e.getPlayer().getInventory().getHelmet().getType()==Material.GOLDEN_HELMET&&!e.getPlayer().getInventory().getHelmet().getItemMeta().hasEnchant(Enchantment.BINDING_CURSE)){
            ItemStack helmet = e.getPlayer().getInventory().getHelmet();
            ItemMeta helmetmeta = helmet.getItemMeta();
            helmetmeta.addEnchant(Enchantment.BINDING_CURSE,1,true);
            helmet.setItemMeta(helmetmeta);
            e.getPlayer().getInventory().setHelmet(helmet);

            e.getPlayer().getWorld().spawnEntity(e.getPlayer().getLocation(),EntityType.LIGHTNING);
            e.getPlayer().sendMessage("HI!");
        }

    }

    @EventHandler
    public void madness1(PlayerDropItemEvent e){
        if(e.getPlayer().getInventory().getHelmet()==null){
            return;
        }
        if(e.getPlayer().getInventory().getHelmet().getType()==Material.GOLDEN_HELMET&&e.getPlayer().getInventory().getHelmet().getItemMeta().hasEnchant(Enchantment.BINDING_CURSE)&&e.getPlayer().getInventory().getHelmet().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.LIGHT_PURPLE+"Normal Helmet")){
            e.getPlayer().sendMessage("DO NOT, this can be useful later, trust me");
            e.setCancelled(true);
        }


    }
    @EventHandler
    public void madness4(PlayerMoveEvent e){
        if(e.getPlayer().getInventory().getHelmet()==null){
            return;
        }
        if(e.getPlayer().getInventory().getHelmet().getType()==Material.GOLDEN_HELMET&&e.getPlayer().getInventory().getHelmet().getItemMeta().hasEnchant(Enchantment.BINDING_CURSE)&&e.getPlayer().getInventory().getHelmet().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.LIGHT_PURPLE+"Normal Helmet")){
            Location l = e.getPlayer().getLocation();
            l.setY(l.getY());
            Block b = l.getBlock();
            if(b.getType()==Material.WATER){
                e.getPlayer().sendTitle(ChatColor.DARK_RED+"GET OUT NOW","",0,20*5,0);
                e.getPlayer().playSound(e.getPlayer().getLocation(),Sound.ENTITY_SHULKER_HURT,100, (float) 0.1);
                e.getPlayer().playSound(e.getPlayer().getLocation(),Sound.ENTITY_GHAST_HURT,100, (float) 0.1);
                b.setType(Material.SPONGE);
                b.setType(Material.AIR);
                Entity en = (Entity) e.getPlayer().getWorld().spawnEntity(e.getPlayer().getLocation(),EntityType.ENDERMAN);
                e.getPlayer().setGameMode(GameMode.SPECTATOR);
                e.getPlayer().setSpectatorTarget(en);
                new BukkitRunnable() {

                    @Override
                    public void run() {
                        // What you want to schedule goes here
                        en.remove();
                        e.getPlayer().setGameMode(GameMode.SURVIVAL);
                    }

                }.runTaskLater(this.plugin, 10);
            }
        }
    }
    /*if(e.getPlayer().getInventory().getHelmet()==null){
            return;
        }
        if(e.getPlayer().getInventory().getHelmet().getType()==Material.GOLDEN_HELMET&&e.getPlayer().getInventory().getHelmet().getItemMeta().hasEnchant(Enchantment.BINDING_CURSE)){


        }*/
    @EventHandler
    public void madness3(PlayerBedEnterEvent e){
        if(e.getPlayer().getInventory().getHelmet()==null){
            return;
        }
        if(e.getPlayer().getInventory().getHelmet().getType()==Material.GOLDEN_HELMET&&e.getPlayer().getInventory().getHelmet().getItemMeta().hasEnchant(Enchantment.BINDING_CURSE)&&e.getPlayer().getInventory().getHelmet().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.LIGHT_PURPLE+"Normal Helmet")){
            e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR,new TextComponent("you may not rest now; there are ALWAYS monsters nearby"));
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void Inmortality(EntityDamageEvent e){
        if(!(e.getEntity() instanceof Player)){
            return;
        }
        if(e.getEntity() instanceof Player){
            Player player = (Player) e.getEntity();
            if(player.getInventory().getHelmet()==null){
                return;
            }
            if(player.getInventory().getHelmet().getType()==Material.GOLDEN_HELMET&&player.getInventory().getHelmet().getItemMeta().hasEnchant(Enchantment.BINDING_CURSE)&&player.getInventory().getHelmet().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.LIGHT_PURPLE+"Normal Helmet")){
                if(e.getDamage()>20){
                    //player.getInventory().setItemInOffHand(new ItemStack(Material.TOTEM_OF_UNDYING));
                    e.setCancelled(true);
                }
                PotionEffect heal = new PotionEffect(PotionEffectType.REGENERATION,20*3,30);
                player.addPotionEffect(heal);
            }
        }

    }

    @EventHandler
    public void eatbeetroot(PlayerItemConsumeEvent e){
        if(e.getPlayer().getInventory().getHelmet()==null){
            return;
        }
        if(e.getPlayer().getInventory().getHelmet().getType()==Material.GOLDEN_HELMET&&e.getPlayer().getInventory().getHelmet().getItemMeta().hasEnchant(Enchantment.BINDING_CURSE)&&e.getPlayer().getInventory().getHelmet().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.LIGHT_PURPLE+"Normal Helmet")){
            if(e.getItem().getType()==Material.BEETROOT){
                e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.MUSIC_DISC_STRAD,100,1);
                e.getPlayer().getWorld().setStorm(true);
                if(e.getPlayer().getInventory().contains(Material.BEETROOT)){
                    e.getPlayer().getInventory().remove(Material.BEETROOT);
                    e.getPlayer().sendMessage("i LoVE beETrOOTs");

                }
            }
        }
    }

    @EventHandler
    public void madness2(FoodLevelChangeEvent e){

        if(e.getEntity() instanceof Player){
            if(e.getEntity().getInventory().getHelmet()==null){
                return;
            }
            Player player = (Player) e.getEntity();
            if(player.getInventory().getHelmet().getType()==Material.GOLDEN_HELMET&&player.getInventory().getHelmet().getItemMeta().hasEnchant(Enchantment.BINDING_CURSE)&&player.getInventory().getHelmet().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.LIGHT_PURPLE+"Normal Helmet")){
                if(player.getFoodLevel()==6){
                    player.setFoodLevel(7);
                    player.sendMessage("HUNGRY!, get food NOW");
                    player.sendTitle("EAT SOMETHING","",0,20*2,0);
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR,new TextComponent("MAYBE BEETROOTS?!"));

                }
            }

        }
    }
}
