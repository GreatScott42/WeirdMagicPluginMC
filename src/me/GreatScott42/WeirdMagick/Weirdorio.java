package me.GreatScott42.WeirdMagick;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.KnowledgeBookMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Weirdorio implements Listener {

    Main plugin;
    public Weirdorio(Main plugin){
        this.plugin=plugin;
    }

    @EventHandler
    public void openWeirdorio(PlayerInteractEvent e){
        if(e.getItem()==null)
        {
            return;
        }
        if(e.getAction()==Action.LEFT_CLICK_AIR||e.getAction()==Action.LEFT_CLICK_BLOCK||e.getItem().getType()!=Material.KNOWLEDGE_BOOK){
            return;
        }
        e.setUseItemInHand(Event.Result.DENY);
        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        ItemMeta bookItemMeta = book.getItemMeta();
        BookMeta bookMeta = (BookMeta) bookItemMeta;
        bookMeta.setAuthor("the great scott");

        List<String> weirdoriolore = new ArrayList<>();
        weirdoriolore.add("Forgotten Knowledge");
        bookItemMeta.setLore(weirdoriolore);

        bookMeta.setTitle("Weirdorio");

        bookMeta.addPage("\n" +
                "        §l§oWeirdorio§r \n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "   by The §mGreat§r Scott");
        bookMeta.addPage("\n" +
                "Contents\n" +
                "-Spells....................................3\n" +
                "-Artifacts............................5\n"+
                "-Rare objects......................9");
        bookMeta.addPage("§lSpells§r\n" +
                "\n" +
                "§8the baby spell§r:\n" +
                "created with a snowball, a blue orchid, a white tulip and a lilly of the valley can shrink creatures to their baby form if it has\n" +
                "\n" +
                "note: do not use with creepers");
        bookMeta.addPage("§8the adult spell§r:\n" +
                "contrary to the baby spell this make grow up the creature, and for one reason i dont know yet, make zombies become giants. is created with coal, brown mooshroom, eggs and bone meal\n" +
                "\n" +
                "note: do not use with creepers");
        bookMeta.addPage("§0§lArtifacts§r\n" +
                "\n" +
                "§6ethereal saddle§r: trying to mix objects with souls, i find out that mixing a saddle with a soul lantern in an anvil will capture the soul of a horse in the saddle, summoning a horse each time i use the saddle, also taking part of my health");
        bookMeta.addPage("§7Attack wand§r: after keep trying capturing souls in objects i manage to catch the soul of a wither in a stick, with its power i can summon three forms: a fireball, a freezing snowball and a lighting. but doing this took part of my vitality for ever");
        bookMeta.addPage("my most recent discovery is that some villager clerics can curse books with some sort of voodoo spell, after some \"chat\" i got one and before the villager mysteriously died, he told me that write my enemy's name in the title of the book will unite that person's ");
        bookMeta.addPage("soul with the book, and destroying that book will kill that soul as well");
        bookMeta.addPage("§lRare objects§r\n"+
                "\n"+
                "§aDimensional apple§r:\n"+
                "is an apple that can change the consumer of dimension, i found it in the trees from the habitat of endermans in the nether\nis very risky to use it, you can appear in middle of a pool of lava or buried");
        bookMeta.addPage("§kgreetingS Curious wizard, here the future and past are One and soon The presenT too\n§k42424242\n§kWatermelonMoonLightningTeleportation");
        book.setItemMeta(bookItemMeta);
        e.getPlayer().openBook(book);
    }

    @EventHandler
    public void createWeirdorio(BlockBreakEvent e){
        if(!plugin.getConfig().getBoolean("bookshelfdropsweirdorio"))
        {
            return;
        }
        if(e.getBlock().getType()==Material.BOOKSHELF){
            Random random = new Random();
            int prob = random.nextInt(100);
            if(prob<plugin.getConfig().getInt("dropchanceweirdorio")){
                ItemStack weirdorio = new ItemStack(Material.KNOWLEDGE_BOOK);
                ItemMeta weirdoriometa = weirdorio.getItemMeta();
                KnowledgeBookMeta knowledgeBookMeta = (KnowledgeBookMeta) weirdoriometa;
                //!!!!:D
                knowledgeBookMeta.addRecipe(Material.CRAFTING_TABLE.getKey());

                List<String> weirdoriolore = new ArrayList<>();
                weirdoriolore.add("Forgotten Knowledge");
                weirdoriometa.setLore(weirdoriolore);
                weirdoriometa.setDisplayName(ChatColor.GOLD+"Weirdorio");

                weirdorio.setItemMeta(weirdoriometa);
                e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),weirdorio);
            }
        }
    }

}
