package listener;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import storage.Container;

import javax.annotation.Nonnull;
import java.util.HashMap;

public class ReactionListener extends ListenerAdapter {

    private static final HashMap<String, Long> rolesList = new HashMap<String, Long>() {{
        //EN
        put("U+1f1faU+1f1f8", 797850691657793536L);
        //DE
        put("U+1f1e9U+1f1ea", 797850514561302528L);
        //PL
        put("U+1f1f5U+1f1f1", 798328722125357116L);
        //FR
        put("U+1f1ebU+1f1f7", 798328750558150706L);
    }};

    private static final long messageID = 798325250685272145L;
    private static final long channelID = 798322513562042408L;

    @Override
    public void onMessageReactionAdd(@Nonnull MessageReactionAddEvent event) {

//        if (event.getTextChannel().equals(event.getGuild().getTextChannelById("726490100082409492"))) {
//        event.getTextChannel().sendMessage(event.getReaction() + event.getReactionEmote().getAsCodepoints()).queue();
//        }

        //Roles Management
        Member member = event.getMember();
        if (member != null && event.getMessageIdLong() == messageID && !event.getUser().isBot()) {
            String emoteCodepoints = event.getReactionEmote().getAsCodepoints();
            if (rolesList.containsKey(emoteCodepoints)) {
                event.getGuild().addRoleToMember(member, event.getGuild().getRoleById(rolesList.get(emoteCodepoints))).complete();
            }
        }
    }

    @Override
    public void onMessageReactionRemove(@Nonnull MessageReactionRemoveEvent event) {

        //Roles Management
        Member member = event.getMember();
        if (member != null && event.getMessageIdLong() == messageID && !event.getUser().isBot()) {
            String emoteCodepoints = event.getReactionEmote().getAsCodepoints();
            if (rolesList.containsKey(emoteCodepoints)) {
                event.getGuild().removeRoleFromMember(member, event.getGuild().getRoleById(rolesList.get(emoteCodepoints))).complete();
            }
        }
    }

    public static void checkIfReacted() {
        Message m = Container.getGuild().getTextChannelById(channelID).retrieveMessageById(messageID).complete();
        for (String s : rolesList.keySet()) {
            m.addReaction(s).complete();
        }
    }
}
