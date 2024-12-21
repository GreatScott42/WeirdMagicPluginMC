package me.GreatScott42.WeirdMagick;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Lightable;
import org.bukkit.block.data.type.Candle;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockDataMeta;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

public class AgeSpell implements Listener {



    @EventHandler
    public void onProjectileHit(ProjectileHitEvent e){
        if(e.getEntity().getShooter() instanceof Player) {

            if(e.getEntityType().equals(EntityType.SNOWBALL)){

                ThrowableProjectile projectile = (ThrowableProjectile) e.getEntity();
                ItemStack snowball = projectile.getItem();
                ItemMeta snoballmeta = snowball.getItemMeta();
                if(snoballmeta.getDisplayName().equalsIgnoreCase(ChatColor.AQUA+"Baby spell")&&snoballmeta.hasEnchant(Enchantment.VANISHING_CURSE)){

                    if(e.getHitBlock()==null){
                        Entity entity = e.getHitEntity();

                        if(!(entity instanceof Ageable)) {

                            if(entity.getType()==EntityType.GIANT){
                                Location location = entity.getLocation();
                                e.getEntity().getWorld().spawnEntity(location,EntityType.ZOMBIE);
                                location.setY(-67.0);
                                e.getEntity().getWorld().playSound(e.getEntity().getLocation(),Sound.ENTITY_ILLUSIONER_CAST_SPELL,100,1);
                                e.getEntity().getWorld().spawnParticle(Particle.CLOUD,e.getHitEntity().getLocation(),200,0.1,6,0.1,0.1);
                                entity.teleport(location);
                                return;
                            }else if(entity.getType()==EntityType.CREEPER){
                                Creeper creeper = (Creeper) entity;
                                creeper.ignite();
                                return;
                            }else if(entity.getType()==EntityType.SLIME||entity.getType()==EntityType.MAGMA_CUBE){
                                if(entity.getType()==EntityType.SLIME){
                                    Slime slime = (Slime) entity;
                                    if(slime.getSize()>=0){
                                        slime.setSize(slime.getSize()-1);
                                        e.getEntity().getWorld().playSound(e.getEntity().getLocation(), Sound.ENTITY_ILLUSIONER_CAST_SPELL,100,1);
                                        e.getEntity().getWorld().spawnParticle(Particle.CLOUD,e.getHitEntity().getLocation(),100,0.1*slime.getSize(),0.1*slime.getSize(),0.1*slime.getSize(),0.1);
                                    }
                                    return;
                                }else if(entity.getType()==EntityType.MAGMA_CUBE){
                                    MagmaCube cube = (MagmaCube) entity;
                                    if(cube.getSize()>=0){
                                        cube.setSize(cube.getSize()-1);
                                        e.getEntity().getWorld().playSound(e.getEntity().getLocation(), Sound.ENTITY_ILLUSIONER_CAST_SPELL,100,1);
                                        e.getEntity().getWorld().spawnParticle(Particle.CLOUD,e.getHitEntity().getLocation(),100,0.1*cube.getSize(),0.1*cube.getSize(),0.1*cube.getSize(),0.1);
                                    }
                                    return;
                                }
                            }
                        }
                        if(!(entity instanceof Ageable)||(((Ageable) entity).getAge()<0)){
                            return;
                        }
                        Ageable ageableE = (Ageable) entity;
                        ageableE.setAge(-9999);
                        e.getEntity().getWorld().playSound(e.getEntity().getLocation(), Sound.ENTITY_ILLUSIONER_CAST_SPELL,100,1);
                        e.getEntity().getWorld().spawnParticle(Particle.CLOUD,e.getHitEntity().getLocation(),100,0.1,0.1,0.1,0.1);
                    }
                }
            }else if(e.getEntityType().equals(EntityType.EGG)){

                ThrowableProjectile projectile = (ThrowableProjectile) e.getEntity();
                ItemStack egg = projectile.getItem();
                ItemMeta eggmeta = egg.getItemMeta();
                if(eggmeta.getDisplayName().equalsIgnoreCase(ChatColor.YELLOW+"Adult spell")&&eggmeta.hasEnchant(Enchantment.VANISHING_CURSE)){
                    if(e.getHitBlock()==null){
                        Entity entity = e.getHitEntity();


                        if(entity.getType()==EntityType.ZOMBIE){
                            Ageable zombie = (Ageable) entity;
                            if(zombie.isAdult()){
                                Location location = entity.getLocation();
                                e.getEntity().getWorld().spawnEntity(location,EntityType.GIANT);
                                location.setY(-67.0);
                                e.getEntity().getWorld().playSound(e.getEntity().getLocation(),Sound.ENTITY_ILLUSIONER_CAST_SPELL,100,1);
                                e.getEntity().getWorld().spawnParticle(Particle.CLOUD,e.getHitEntity().getLocation(),200,0.1,6,0.1,0.1);
                                entity.teleport(location);
                                return;
                            }

                        }

                        if(!(entity instanceof Ageable)) {

                            if(entity.getType()==EntityType.CREEPER){
                                Creeper creeper = (Creeper) entity;
                                creeper.ignite();
                            }else if(entity.getType()==EntityType.SLIME||entity.getType()==EntityType.MAGMA_CUBE){
                                if(entity.getType()==EntityType.SLIME){
                                    Slime slime = (Slime) entity;
                                    if(slime.getSize()<50){
                                        slime.setSize(slime.getSize()+1);
                                        e.getEntity().getWorld().playSound(e.getEntity().getLocation(), Sound.ENTITY_ILLUSIONER_CAST_SPELL,100,1);
                                        e.getEntity().getWorld().spawnParticle(Particle.CLOUD,e.getHitEntity().getLocation(),100,0.1*slime.getSize(),0.1*slime.getSize(),0.1*slime.getSize(),0.1);
                                    }
                                    return;
                                }else if(entity.getType()==EntityType.MAGMA_CUBE){
                                    MagmaCube cube = (MagmaCube) entity;
                                    if(cube.getSize()>=50){
                                        cube.setSize(cube.getSize()+1);
                                        e.getEntity().getWorld().playSound(e.getEntity().getLocation(), Sound.ENTITY_ILLUSIONER_CAST_SPELL,100,1);
                                        e.getEntity().getWorld().spawnParticle(Particle.CLOUD,e.getHitEntity().getLocation(),100,0.1*cube.getSize(),0.1*cube.getSize(),0.1*cube.getSize(),0.1);
                                    }
                                    return;
                                }
                            }

                        }
                        if(!(entity instanceof Ageable)||(((Ageable) entity).getAge()>=0)){
                            return;
                        }
                        Ageable ageableE = (Ageable) entity;
                        ageableE.setAge(0);
                        e.getEntity().getWorld().playSound(e.getEntity().getLocation(), Sound.ENTITY_ILLUSIONER_CAST_SPELL,100,1);
                        e.getEntity().getWorld().spawnParticle(Particle.CLOUD,e.getHitEntity().getLocation(),100,0.1,0.1,0.1,0.1);
                    }
                }
            }

        }
    }

}
