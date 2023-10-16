package dev.soizx.util;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;

import java.awt.*;
import java.util.Objects;

public class _Logger {
    public static void start(MessageContextInteractionEvent event, String channelID, User messageUser, Integer danger) {
        EmbedBuilder embedBuilder = new EmbedBuilder();

        TextChannel textChannel  = Objects.requireNonNull(event.getGuild()).getTextChannelById(channelID);

        switch (danger) {
            case 0:
                embedBuilder.setColor(Color.decode("#E81F63"));
                break;
            case 1:
                embedBuilder.setColor(Color.decode("#FFD700"));
                break;
            case 2:
                embedBuilder.setColor(Color.decode("#A52A2A"));
                break;
        }

        embedBuilder.setThumbnail(messageUser.getAvatarUrl());
        embedBuilder.setTitle("Message LOGGED at ` "+ _Date.now(_Date.allFormat)+" `");
        embedBuilder.addField("Created at","` "+event.getTimeCreated()+" `",true);
        embedBuilder.addField("Author ", messageUser.getAsMention()+" ",true);
        embedBuilder.addField("Malicious links ", "` "+ _Formatter.isLinkInBlacklist(event.getInteraction().getTarget().getContentDisplay())+" `",true);
        embedBuilder.addField("","",false);
        embedBuilder.addField("MessageContent","```"+event.getInteraction().getTarget().getContentDisplay()+"```",false);
        embedBuilder.addField("","",false);
        embedBuilder.addField("MessageLinks", _Formatter.getLinks(event.getInteraction().getTarget().getContentDisplay()),false);


        assert textChannel != null;
        textChannel.sendMessageEmbeds(embedBuilder.build()).queue();
    }
}
