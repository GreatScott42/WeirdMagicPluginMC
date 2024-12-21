package me.GreatScott42.WeirdMagick;

import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin {

    FileConfiguration config = this.getConfig();

    @Override
    public void onEnable() {
        //change config version!
        String v = Bukkit.getBukkitVersion();
        getLogger().info("[WM] Running version: "+v);
        if(!config.getString("config-version").equalsIgnoreCase("1.2")){
            config.set("config-version","1.2");
            saveConfig();
        }

        new CheckUpdates(this, 101357).getVersion(version -> {
            if (this.getDescription().getVersion().equals(version)) {
                getLogger().info("There is not a new update available.");

            } else {
                getLogger().info("New update available.");
                for (Player player:
                        this.getServer().getOnlinePlayers()) {
                    if(player.hasPermission("WeirdMagick.checkupdate")){
                        //player.sendMessage(ChatColor.GREEN+"["+ChatColor.BLUE+"WeirdMagick"+ChatColor.GREEN+"]"+ChatColor.YELLOW+" New update available!");
                        player.sendMessage("[WeirdMagick] Hey "+player.getName()+", New update available!");
                    }
                }
            }
        });
        //for update config, change

        /*if(!config.contains("dropchanceweirdorio",true)&&config.contains("canmakewand",true)){
            Bukkit.getLogger().info("[WeirdMagick] Updating Config");
            List<String> comments = new ArrayList<>();

            List<String> header = new ArrayList<>();
            List<String> footer = new ArrayList<>();
            header.add("WeirdMagick Config file\n\n#do not change this\n" + "config-version: 1.2");
            config.options().setHeader(header);
            footer.add("\n#drop chances of break a bookshelf and get a weirdorio (n/100);\n" +
                    "dropchanceweirdorio: 10\n" +
                    "\n" +
                    "# if false bookshelfs wont drop weirdorios\n" +
                    "bookshelfdropsweirdorio: true\n" +
                    "\n" +
                    "# set false if you do not want warped wart block drop dimensional apple item\n" +
                    "warpedwartblockdropdimensionalapple: true\n" +
                    "\n" +
                    "# set the chances of a warped wart block to drop a dimensional apple from 100%\n" +
                    "dropchancedimensionalapple: 1");
            config.options().setFooter(footer);
            saveConfig();
            Bukkit.getLogger().info("[WeirdMagick] Config updated");
        }else{
            getConfig().options().copyDefaults(true);
            saveDefaultConfig();
        }*/

        /*if(getConfig().getBoolean("babyrecipe")){
            recipeBabySpell();
        }
        if(getConfig().getBoolean("adultrecipe")){
            recipeAdultSpell();
        }*/

        /*getServer().getPluginManager().registerEvents(new AgeSpell(),this);
        getServer().getPluginManager().registerEvents(new Weirdorio(this),this);
        getServer().getPluginManager().registerEvents(new DimensionalApple(this),this);
        getServer().getPluginManager().registerEvents(new FloralBoots(),this);
        getServer().getPluginManager().registerEvents(new HeliumPants(),this);
        getServer().getPluginManager().registerEvents(new CursedHelmet(this),this);
        getServer().getPluginManager().registerEvents(new LoveChestplate(),this);
        getServer().getPluginManager().registerEvents(new VuduBook(this),this);
        getServer().getPluginManager().registerEvents(new AtackWand(this),this);
        getServer().getPluginManager().registerEvents(new EtherealSaddle(this),this);*/

        /*
        getCommand("giveSpell").setExecutor(new giveSpell());
        getCommand("giveSpell").setTabCompleter(new TabSuggest());*/

        Bukkit.getServer().getConsoleSender().sendMessage("[WeirdMagick] Enabled");
    }

    @Override
    public void onDisable() {
        Bukkit.getServer().getConsoleSender().sendMessage("[WeirdMagick] Disabled");
    }



}

