package com.general_hello.commands.commands.Commands;

import com.general_hello.commands.Bot;
import com.general_hello.commands.commands.ButtonPaginator;
import com.general_hello.commands.commands.Commands.Items.Initializer;
import com.general_hello.commands.commands.Commands.Objects.Objects;
import com.general_hello.commands.commands.Commands.RpgUser.ResourcesDataUtils;
import com.general_hello.commands.commands.Commands.RpgUser.ResourcesUser;
import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.text.NumberFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class ResourcesCommand extends SlashCommand {
    public ResourcesCommand() {
        this.name = "resources";
        this.help = "Displays your resources";
        this.cooldown = 10;
        this.options = Collections.singletonList(new OptionData(OptionType.USER, "user", "The user to show the resources.").setRequired(false));
    }

    @Override
    protected void execute(SlashCommandEvent event) {
        User author = event.getUser();
        if (event.getOption("user") != null) {
            author = event.getOption("user").getAsUser();
        }
        Set<String> categories = Initializer.categoryToResources.keySet();
        long authorId = author.getIdLong();
        int x;
        int y = 0;

        ArrayList<String> inventories = new ArrayList<>();
        StringBuilder inventory = new StringBuilder();

        for (String category : categories) {
            LinkedList<String> itemsInCategory = Initializer.categoryToResources.get(category);
            inventory.append("***").append(category).append("***").append("\n");
            x=0;
            while (x < itemsInCategory.size()) {
                long itemCount = ResourcesUser.getItemCount(authorId, itemsInCategory.get(x));
                Objects objects = Initializer.allItems.get(ResourcesDataUtils.filter(itemsInCategory.get(x)));
                inventory.append(objects.getEmojiOfItem()).append(" **")
                        .append(objects.getName()).append("** ─ ")
                        .append(NumberFormat.getNumberInstance(Locale.US).format(itemCount)).append("\n")
                        .append("**Description:** `").append(objects.getDescription()).append("`").append("\n\n");
                y++;

                if (y % 8 == 0) {
                    if (!inventory.toString().equals("")) {
                        inventories.add(inventory.toString());
                    }
                    inventory = new StringBuilder();
                    inventory.append("***").append(category).append("***\n");
                }
                x++;
            }
        }

        if (!inventory.toString().equals("")) {
            inventories.add(inventory.toString());
        }

        if (inventories.isEmpty()) {
            event.reply("You do not have anything in your resource").queue();
            return;
        }

        event.reply("Loading resources...").queue((interactionHook -> {
            interactionHook.deleteOriginal().queueAfter(3, TimeUnit.SECONDS);
        }));
        ButtonPaginator.Builder builder = new ButtonPaginator.Builder(event.getJDA())
                .setEventWaiter(Bot.getBot().getEventWaiter())
                .setItemsPerPage(1)
                .setTimeout(1, TimeUnit.MINUTES)
                .useNumberedItems(false);

        builder.setTitle("**" + author.getName() + "**'s resources")
                .setItems(inventories)
                .addAllowedUsers(event.getUser().getIdLong())
                .setColor(event.getGuild().getSelfMember().getColor());

        builder.build().paginate(event.getChannel().asTextChannel(), 1);
    }
}
