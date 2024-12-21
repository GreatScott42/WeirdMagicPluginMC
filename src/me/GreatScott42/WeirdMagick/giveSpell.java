package me.GreatScott42.WeirdMagick;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
//import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
//import org.bukkit.inventory.meta.KnowledgeBookMeta;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class giveSpell implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;
            if(strings.length==0){
                player.sendMessage("[WM] not enough arguments");
                return true;
            }
            if(player.hasPermission("WeirdMagick.givespell")){
                switch (strings[0]){
                    /*case "Baby_Spell":
                        ItemStack snowball = new ItemStack(Material.SNOWBALL);
                        ItemMeta snowdata = snowball.getItemMeta();

                        snowdata.setDisplayName(ChatColor.AQUA+"Baby spell");
                        snowdata.addEnchant(Enchantment.VANISHING_CURSE,1,true);
                        snowdata.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        snowdata.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                        snowball.setItemMeta(snowdata);
                        player.getInventory().addItem(snowball);
                        break;
                    case "Adult_Spell":
                        ItemStack egg = new ItemStack(Material.EGG);
                        ItemMeta eggdata = egg.getItemMeta();

                        eggdata.setDisplayName(ChatColor.YELLOW+"Adult spell");
                        eggdata.addEnchant(Enchantment.VANISHING_CURSE,1,true);
                        eggdata.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        eggdata.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                        egg.setItemMeta(eggdata);
                        player.getInventory().addItem(egg);
                        break;
                    case "Ethereal_Saddle":
                        ItemStack saddle = new ItemStack(Material.SADDLE);
                        ItemMeta saddlemeta = saddle.getItemMeta();

                        saddlemeta.setDisplayName(ChatColor.GOLD+"Ethereal Saddle");
                        List<String> lore = new ArrayList<>();
                        lore.add("Summon a horse soul");
                        saddlemeta.setLore(lore);
                        saddle.setItemMeta(saddlemeta);
                        player.getInventory().addItem(saddle);
                        break;
                    case "Wand":
                        ItemStack wand = new ItemStack(Material.STICK);
                        ItemMeta wandmeta = wand.getItemMeta();

                        wandmeta.setDisplayName(ChatColor.GRAY+"Wand"+ChatColor.RED+" (Fireball)");
                        wand.setItemMeta(wandmeta);
                        player.getInventory().addItem(wand);
                        break;
                    case "Voodoo_Book":
                        ItemStack book = new ItemStack(Material.WRITABLE_BOOK);
                        ItemMeta bookmeta = book.getItemMeta();
                        List<String> lores = new ArrayList<>();
                        lores.add("Voodoo book");
                        bookmeta.setLore(lores);

                        bookmeta.setUnbreakable(true);
                        bookmeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                        book.setItemMeta(bookmeta);
                        player.getInventory().addItem(book);
                        break;
                    case "Weirdorio":
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
                        player.getInventory().addItem(weirdorio);
                        break;
                    case "Dimensional_Apple":
                        ItemStack apple = new ItemStack(Material.APPLE);
                        ItemMeta applemeta = apple.getItemMeta();
                        applemeta.setDisplayName(ChatColor.GREEN+"Dimensional apple");
                        apple.setItemMeta(applemeta);
                        player.getInventory().addItem(apple);
                        break;*/
                    case "Floral_Boots":
                        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
                        ItemMeta bootsmeta = boots.getItemMeta();
                        //bootsmeta.setUnbreakable(true); 1.18

                        bootsmeta.setDisplayName(ChatColor.LIGHT_PURPLE+"Floral Boots");
                        List<String> bootslore = new ArrayList<>();
                        bootslore.add("Flower boots!");
                        bootsmeta.setLore(bootslore);
                        boots.setItemMeta(bootsmeta);
                        player.getInventory().addItem(boots);
                        break;
                    /*case "Helium_Leggings":
                        ItemStack pants = new ItemStack(Material.CHAINMAIL_LEGGINGS);
                        ItemMeta metapants = pants.getItemMeta();
                        metapants.setDisplayName(ChatColor.LIGHT_PURPLE+"Helium Leggings");

                        List<String> pantslore = new ArrayList<>();
                        pantslore.add("From lumpy space");
                        metapants.setLore(pantslore);
                        pants.setItemMeta(metapants);
                        player.getInventory().addItem(pants);
                        break;
                    case "Normal_Helmet":
                        ItemStack helmet = new ItemStack(Material.GOLDEN_HELMET);
                        ItemMeta metahelmet = helmet.getItemMeta();
                        metahelmet.setDisplayName(ChatColor.LIGHT_PURPLE+"Normal Helmet");
                        metahelmet.setUnbreakable(true);

                        List<String> helmetlore = new ArrayList<>();
                        helmetlore.add("Not cursed");
                        metahelmet.setLore(helmetlore);
                        helmet.setItemMeta(metahelmet);
                        player.getInventory().addItem(helmet);
                        break;
                    case "Brain_Melter":
                        ItemStack chest = new ItemStack(Material.IRON_CHESTPLATE);
                        ItemMeta metachest = chest.getItemMeta();
                        metachest.setDisplayName(ChatColor.LIGHT_PURPLE+"Brain Melter");

                        List<String> chestlore = new ArrayList<>();
                        chestlore.add("Too Op");
                        metachest.setLore(chestlore);
                        chest.setItemMeta(metachest);
                        player.getInventory().addItem(chest);
                        break;*/
                    default:
                        player.sendMessage("[WeirdMagick] select an item or spell");
                        break;
                }
            }else{
                player.sendMessage("[WeirdMagick] you do not have permission to use this command");
            }

        }else{
            Bukkit.getServer().getConsoleSender().sendMessage("[WeirdMagick] Command only for players");
        }
        return true;
    }

}
