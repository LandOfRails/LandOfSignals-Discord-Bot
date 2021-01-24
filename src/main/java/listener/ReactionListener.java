package listener;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import storage.Container;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Set;

public class ReactionListener extends ListenerAdapter {

    /**
     * <MessageID, HashMap<EmojiCodepoints, RoleID>>
     */
    private static final HashMap<Long, HashMap<String, Long>> rolesList = new HashMap<Long, HashMap<String, Long>>() {{
        //L A N G U A G E S
        put(798325250685272145L, new HashMap<String, Long>() {{
            //EN
            put("U+1f1faU+1f1f8", 797850691657793536L);
            //DE
            put("U+1f1e9U+1f1ea", 797850514561302528L);
            //PL
            put("U+1f1f5U+1f1f1", 798328722125357116L);
            //FR
            put("U+1f1ebU+1f1f7", 798328750558150706L);
            //DK
            put("U+1f1e9U+1f1f0", 798817993722822656L);
        }});

        //O T H E R
        //Stay informed
        put(802698297470091274L, new HashMap<String, Long>() {{
            put("U+1f514", 802685442310930455L);
        }});
    }};

    /**
     * Static channelID as there is only one channel for rules
     */
    private static final long channelID = 798322513562042408L;

    @Override
    public void onMessageReactionAdd(@Nonnull MessageReactionAddEvent event) {

        if (event.getMessageIdLong() == 798820386761867304L) {
            event.getTextChannel().sendMessage(event.getReaction() + event.getReactionEmote().getAsCodepoints()).queue();
        }

        //Roles Management
        Member member = event.getMember();
        Long messageID = event.getMessageIdLong();
        if (member != null && rolesList.containsKey(messageID) && !event.getUser().isBot()) {
            String codepoints = event.getReactionEmote().getAsCodepoints();
            HashMap<String, Long> roles = rolesList.get(messageID);
            if (roles.containsKey(codepoints)) {
                event.getGuild().addRoleToMember(member, event.getGuild().getRoleById(roles.get(codepoints))).complete();
            }
        }
    }

    @Override
    public void onMessageReactionRemove(@Nonnull MessageReactionRemoveEvent event) {

        Member member = event.getMember();
        Long messageID = event.getMessageIdLong();
        if (member != null && rolesList.containsKey(messageID) && !event.getUser().isBot()) {
            String codepoints = event.getReactionEmote().getAsCodepoints();
            HashMap<String, Long> roles = rolesList.get(messageID);
            if (roles.containsKey(codepoints)) {
                event.getGuild().removeRoleFromMember(member, event.getGuild().getRoleById(roles.get(codepoints))).complete();
            }
        }
    }

    public static void checkIfReacted() {
        for (Long l : rolesList.keySet()) {
            Message m = Container.getGuild().getTextChannelById(channelID).retrieveMessageById(l).complete();
            Set<String> roles = rolesList.get(l).keySet();
            for (String s : roles) {
                m.addReaction(s).complete();
            }
        }
    }
}
