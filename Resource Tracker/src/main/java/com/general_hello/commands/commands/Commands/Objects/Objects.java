package com.general_hello.commands.commands.Commands.Objects;

import com.general_hello.commands.commands.Commands.Types.Rarity;
import net.dv8tion.jda.api.entities.emoji.CustomEmoji;
import net.dv8tion.jda.api.entities.emoji.Emoji;

public class Objects {
    private final String name;
    private final String emojiOfItem;
    private final String description;
    private final Rarity rarity;

    private final String category;

    public Objects(String name, Rarity rarity, String emojiOfItem, String description, String category) {
        this.name = name;
        this.rarity = rarity;
        this.category = category;
        this.emojiOfItem = emojiOfItem;
        this.description = description;
    }

    public String getEmojiOfItem() {
        return emojiOfItem == null ? "" : emojiOfItem;
    }

    public String getDescription() {
        return description;
    }


    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public Long getEmojiId() {
        CustomEmoji emoji = Emoji.fromFormatted(this.emojiOfItem).asCustom();
        return emoji.getIdLong();
    }

    public String getEmojiUrl() {
        if (getEmojiOfItem().equals("")) {
            return "";
        }
        CustomEmoji emoji = Emoji.fromFormatted(getEmojiOfItem()).asCustom();
        if (emoji.isAnimated()) {
            return "https://cdn.discordapp.com/emojis/" + emoji.getId() + ".gif";
        }
        return "https://cdn.discordapp.com/emojis/" + emoji.getId() + ".png";

    }
}
