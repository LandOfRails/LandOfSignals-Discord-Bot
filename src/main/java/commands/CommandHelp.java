package commands;

import commands.interfaces.Aliases;
import commands.interfaces.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;

public class CommandHelp implements Command, Aliases {

    @Override
    public String[] getAliases() {
        return new String[]{"commands", "hilfe"};
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public void onCall(MessageReceivedEvent event) {

        MessageChannel channel = event.getChannel();
        Member member = event.getMember();

        final EmbedBuilder helpEmbed = new EmbedBuilder();
        helpEmbed.setColor(Color.RED);
        helpEmbed.setTitle("LandOfSignals Help page");
        helpEmbed.setDescription("All commands of the LandOfSignals discord bot.");
        helpEmbed.addBlankField(false);
        helpEmbed.addField("General commands:", "", false);
        helpEmbed.addField("!idea <message>", "Submit an idea", true);
        helpEmbed.addField("!info", "Information about LandOfSignals", true);
        helpEmbed.addField("!help", "This help page :)", true);

        /*
         * if(canStartPoll) { helpEmbed.addBlankField(false);
         * helpEmbed.addField("Poll Command(s):", "", false);
         * helpEmbed.addField("abstimmung", "Keine ahnung wie der krams geht lul",
         * true); // @MarkenJaden Musst du machen }
         */

        channel.sendMessage(helpEmbed.build()).queue();

    }

}
