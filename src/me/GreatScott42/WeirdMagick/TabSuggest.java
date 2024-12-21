package me.GreatScott42.WeirdMagick;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TabSuggest implements TabCompleter{
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {

        List<String> commands = new ArrayList<>();
        if(strings.length==1){
            List<String> suggestions = new ArrayList<>();
            suggestions.add("Baby_Spell");
            suggestions.add("Adult_Spell");
            suggestions.add("Ethereal_Saddle");
            suggestions.add("Wand");
            suggestions.add("Voodoo_Book");
            suggestions.add("Dimensional_Apple");
            suggestions.add("Weirdorio");
            suggestions.add("Floral_Boots");
            suggestions.add("Helium_Leggings");
            suggestions.add("Normal_Helmet");
            suggestions.add("Brain_Melter");
            for (String c : suggestions){
                if(c.toLowerCase().startsWith(strings[0].toLowerCase())){
                    commands.add(c);
                }
            }
            return commands;
        }

        return null;
    }
}
