package me.GreatScott42.WeirdMagick;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class Recipes {

    private void recipeBabySpell(){

        ItemStack babyspell = new ItemStack(Material.SNOWBALL);
        ItemMeta babyspellmeta = babyspell.getItemMeta();
        babyspellmeta.setDisplayName(ChatColor.AQUA+"Baby Spell");
        babyspellmeta.addEnchant(Enchantment.VANISHING_CURSE,1,true);
        babyspellmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        babyspellmeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        babyspell.setItemMeta(babyspellmeta);

        ShapelessRecipe babyspellrecipe = new ShapelessRecipe(new NamespacedKey(this,this.getName()),babyspell);


        babyspellrecipe.addIngredient(Material.WHITE_TULIP);
        babyspellrecipe.addIngredient(Material.BLUE_ORCHID);
        babyspellrecipe.addIngredient(Material.LILY_OF_THE_VALLEY);
        babyspellrecipe.addIngredient(Material.SNOWBALL);

        getServer().addRecipe(babyspellrecipe);
    }
    private void recipeAdultSpell(){
        ItemStack adultspell = new ItemStack(Material.EGG);
        ItemMeta adultspellmeta = adultspell.getItemMeta();
        adultspellmeta.setDisplayName(ChatColor.YELLOW+"Adult Spell");
        adultspellmeta.addEnchant(Enchantment.VANISHING_CURSE,1,true);
        adultspellmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        adultspellmeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        adultspell.setItemMeta(adultspellmeta);

        ShapelessRecipe adultspellrecipe = new ShapelessRecipe(new NamespacedKey(this,this.getName()+"fpc"),adultspell);

        adultspellrecipe.addIngredient(Material.EGG);
        adultspellrecipe.addIngredient(Material.COAL);
        adultspellrecipe.addIngredient(Material.BROWN_MUSHROOM);
        adultspellrecipe.addIngredient(Material.BONE_MEAL);

        getServer().addRecipe(adultspellrecipe);
    }



}
