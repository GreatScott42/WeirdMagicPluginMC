package me.GreatScott42.WeirdMagick;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VuduBook implements Listener {
    Main plugin;
    public VuduBook(Main canmake){
        plugin=canmake;
    }

    @EventHandler
    public void Curse(EntityDamageEvent e){
        if(e.getEntityType()!= EntityType.DROPPED_ITEM){
            return;
        }
        ItemStack i = ((Item)e.getEntity()).getItemStack();
        ItemMeta itemMeta = i.getItemMeta();
        if(i.getType()!= Material.WRITTEN_BOOK){
            return;
        }
        if(!itemMeta.isUnbreakable()){
            return;
        }
        ItemMeta met = i.getItemMeta();
        BookMeta book = (BookMeta) met;

        Player player = e.getEntity().getServer().getPlayerExact(book.getTitle());
        if(player==null||player.hasPermission("WeirdMagick.voodooinmune")){
            return;
        }
        player.setHealth(0);
        Bukkit.broadcastMessage(player.getDisplayName()+" was killed by a voodoo curse");
        e.getEntity().remove();

    }
    @EventHandler
    public void VoodooBook(EntityDeathEvent e){
        if(!plugin.getConfig().getBoolean("dropvoodoobook")){
            return;
        }
        if(e.getEntityType()!=EntityType.VILLAGER){
            return;
        }
        if(e.getEntity().getKiller() instanceof Player){
            Villager villager = (Villager) e.getEntity();
            if(villager.getProfession()== Villager.Profession.CLERIC){
                Random random = new Random();
                int prob = random.nextInt(100);
                if(prob<plugin.getConfig().getInt("dropchancevoodoo")){
                    ItemStack voodoobook = new ItemStack(Material.WRITABLE_BOOK);
                    ItemMeta bookmeta = voodoobook.getItemMeta();
                    List<String> lores = new ArrayList<>();
                    lores.add("Voodoo book");
                    bookmeta.setLore(lores);

                    bookmeta.setUnbreakable(true);
                    bookmeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                    voodoobook.setItemMeta(bookmeta);
                    e.getEntity().getWorld().dropItem(e.getEntity().getLocation(),voodoobook);
                }

            }
        }
    }

}
