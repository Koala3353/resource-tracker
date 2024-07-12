package com.general_hello.commands;

import com.general_hello.commands.OtherEvents.OnReadyEvent;
import com.general_hello.commands.commands.Commands.*;
import com.general_hello.commands.commands.Commands.Items.Initializer;
import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;

public class Bot {
    public static JDA jda;
    private static Bot bot;
    private final EventWaiter eventWaiter;

    private static final Logger LOGGER = LoggerFactory.getLogger(Bot.class);
    public EventWaiter getEventWaiter() {
        return eventWaiter;
    }

    public static Bot getBot() {
        return bot;
    }

    public Bot() throws LoginException, InterruptedException {
        bot = this;
        // Initialize the waiter and client
        CommandClientBuilder client = new CommandClientBuilder();


        // Set the client settings
        client.setOwnerId(Config.get("owner_id"));
        client.setCoOwnerIds(Config.get("owner_id_partner"));
        client.setPrefix(Config.get("prefix"));
        client.useHelpBuilder(false);
        client.addSlashCommands(new AddResourceCommand() , new ResourcesCommand());
        client.setActivity(Activity.playing("Type /resources"));
        addCommands(client);
        eventWaiter = new EventWaiter();
        // Finalize the command client
        CommandClient commandClient = client.build();

        jda = JDABuilder.createDefault(Config.get("token"),
                GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.GUILD_PRESENCES,
                GatewayIntent.GUILD_EMOJIS_AND_STICKERS,
                GatewayIntent.DIRECT_MESSAGES,
                GatewayIntent.MESSAGE_CONTENT,
                GatewayIntent.GUILD_MEMBERS,
                GatewayIntent.GUILD_VOICE_STATES)
                .addEventListeners(eventWaiter, commandClient,
                        new OnReadyEvent())
                .setStatus(OnlineStatus.IDLE)
                .setChunkingFilter(ChunkingFilter.ALL) // enable member chunking for all guilds
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .build().awaitReady();
    }

    public static void main(String[] args) throws LoginException {
        Initializer.initializer();
        try {
            new Bot();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void addCommands(CommandClientBuilder clientBuilder) {
        // commands here
    }
}