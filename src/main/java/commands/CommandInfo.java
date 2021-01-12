package commands;

import commands.interfaces.Aliases;
import commands.interfaces.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;

public class CommandInfo implements Command, Aliases {

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public void onCall(MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();
        Member member = event.getMember();

        final EmbedBuilder infoEmbed = new EmbedBuilder();
        infoEmbed.setColor(Color.ORANGE);
        infoEmbed.setTitle("LandOfSignals Info page");
        infoEmbed.setDescription("LandOfSignals adds many different signals from different countries.");
        infoEmbed.addBlankField(false);
        infoEmbed.addField("CurseForge:", "https://www.curseforge.com/minecraft/mc-mods/landofsignals", true);
        infoEmbed.addField("UniversalModCore:", "https://www.curseforge.com/minecraft/mc-mods/universal-mod-core", true);
        infoEmbed.addField("Discord:", "https://discord.gg/ykAqHKYjVM", true);
        infoEmbed.addField("GitHub:", "https://github.com/LandOfRails/LandOfSignals", true);
        infoEmbed.addBlankField(false);
        infoEmbed.addField("For more information check", "#information", true);
        infoEmbed.addField("If you have any questions check", "#faq", true);

        channel.sendMessage(infoEmbed.build()).queue();
    }

    @Override
    public String[] getAliases() {
        return new String[]{"los", "landofsignals"};
    }

}
