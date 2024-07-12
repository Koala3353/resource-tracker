package com.general_hello.commands.commands.Commands;

import com.general_hello.commands.commands.Commands.Items.Initializer;
import com.general_hello.commands.commands.Commands.Objects.Objects;
import com.general_hello.commands.commands.Commands.RpgUser.ResourcesDataUtils;
import com.general_hello.commands.commands.Commands.RpgUser.ResourcesUser;
import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;
import me.xdrop.fuzzywuzzy.FuzzySearch;
import me.xdrop.fuzzywuzzy.model.ExtractedResult;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class AddResourceCommand extends SlashCommand {
    public AddResourceCommand() {
        this.name = "addresource";
        this.ownerCommand = true;
        this.help = "Adds resource to a user";
        List<OptionData> options = new ArrayList<>();
        options.add(new OptionData(OptionType.STRING, "resource", "The resource to give").setRequired(true).setAutoComplete(true));
        options.add(new OptionData(OptionType.USER, "user", "The user to give the resource").setRequired(true));
        options.add(new OptionData(OptionType.INTEGER, "amount", "The amount of that resource to give (Default is 1)").setRequired(false).setMinValue(1));
        this.options = options;
    }

    @Override
    public void onAutoComplete(CommandAutoCompleteInteractionEvent event) {
        if (event.getFocusedOption().getName().equals("resource")) {
            List<ExtractedResult> item = FuzzySearch.extractTop(event.getOption("resource").getAsString(), Initializer.allNames, 2);
            Collection<Command.Choice> choices = new ArrayList<>();
            int x = 0;
            while (x < item.size()) {
                choices.add(new Command.Choice(item.get(x).getString(), item.get(x).getString()));
                x++;
            }
            event.replyChoices(choices).queue();
        }
    }

    @Override
    protected void execute(SlashCommandEvent event) {
        long count = 1;
        String item;
        User users = event.getOption("user").getAsUser();

        if (event.getOption("amount") != null) {
            count = event.getOption("amount").getAsLong();
        }

        item = event.getOption("resource").getAsString();
        String itemName = ResourcesDataUtils.filter(item);
        itemName = FuzzySearch.extractOne(itemName, Initializer.allNames).getString();
        Objects object = Initializer.allItems.get(ResourcesDataUtils.filter(itemName));
        ResourcesUser.addItem(users.getIdLong(), count, ResourcesDataUtils.filter(itemName));
        event.reply("Successfully added " + NumberFormat.getNumberInstance(Locale.US).format(count) + " " + (object.getEmojiOfItem() == null ? "" : object.getEmojiOfItem()) + " " + object.getName() + " to " + users.getName()).setEphemeral(true).queue();
    }
}
