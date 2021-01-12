package commands;

import commands.interfaces.Command;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import javax.annotation.Nonnull;

public class CommandIdea implements Command {

    @Override
    public String getName() {
        return "idea";
    }

    @Override
    public void onCall(@Nonnull MessageReceivedEvent event) {

        MessageChannel channel = event.getChannel();
        String messageTextRaw = event.getMessage().getContentRaw();

        String[] args = messageTextRaw.split(" ", 3);
        if (args.length < 2) {
            channel.sendMessage("Please enter your idea after !idea :)").queue();
            return;
        }

        //TODO Add database

        event.getGuild().getTextChannelById(798317520300802100L)
                .sendMessage("**Idea from:** " + event.getAuthor().getAsMention() + "\n \n" + args[1] + "\n \n Link: " + event.getMessage().getJumpUrl())
                .queue(message -> {
                    message.addReaction("U+1F44D").queue();
                    message.addReaction("U+1F44E").queue();
                });
        event.getChannel()
                .sendMessage("Thank you very much for your suggestion " + event.getAuthor().getAsMention()
                        + "! It was successfully forwarded to the development team.")
                .queue();
    }
}
