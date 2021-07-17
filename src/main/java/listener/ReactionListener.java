package listener;

import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import storage.Container;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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
            //TR
            put("U+1f1f9U+1f1f7", 817429927594688542L);
            //CZ
            put("U+1f1e8U+1f1ff", 817430070200107018L);
            //NL
            put("U+1f1f3U+1f1f1", 865940454591168512L);
            //IT
            put("U+1f1eeU+1f1f9", 865940570651361280L);
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

        if (event.getMessageIdLong() == 798820386761867304L)
            event.getTextChannel().sendMessage(event.getReaction() + event.getReactionEmote().getAsCodepoints()).queue();

        //Roles Management
        Member member = event.getMember();
        Long messageID = event.getMessageIdLong();
        if (member != null && rolesList.containsKey(messageID) && !event.getUser().isBot()) {
            String codepoints = event.getReactionEmote().getAsCodepoints();
            HashMap<String, Long> roles = rolesList.get(messageID);
            if (roles.containsKey(codepoints))
                event.getGuild().addRoleToMember(member, event.getGuild().getRoleById(roles.get(codepoints))).complete();
            else {
                Container.getGuild().getTextChannelById(797854275325001738L).sendMessage(User.fromId(222733101770604545L).getAsMention() + " check " + event.retrieveMessage().complete().getJumpUrl() + ". Someone has responded with a reaction that has not yet been added.").queue();
                event.getChannel().sendMessage(member.getAsMention() + " The developers have been informed and will add your language soon :)").complete().delete().queueAfter(10, TimeUnit.SECONDS);
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
            if (roles.containsKey(codepoints))
                event.getGuild().removeRoleFromMember(member, event.getGuild().getRoleById(roles.get(codepoints))).complete();
        }
    }

    public static void checkIfReacted() {
        for (Long l : rolesList.keySet()) {
            Message m = Container.getGuild().getTextChannelById(channelID).retrieveMessageById(l).complete();
            Set<String> roles = rolesList.get(l).keySet();
            for (String s : roles) m.addReaction(s).complete();
        }
    }

    public static void checkIfUsersGotRole() {
        for (Long l : rolesList.keySet()) { //Go through all messages
            Message m = Container.getGuild().getTextChannelById(channelID).retrieveMessageById(l).complete();
            List<MessageReaction> lmr = m.getReactions();
            for (MessageReaction mr : lmr) { //Go through all the reactions of the message
                Long roleID = rolesList.get(l).get(mr.getReactionEmote().getAsCodepoints());
                if (roleID != null) {
                    List<User> lu = mr.retrieveUsers().complete();
                    for (User u : lu) { //All users who have responded to this go through
                        if (!u.isBot()) {
                            Guild g = mr.getGuild();
                            try {
                                Member mb = g.retrieveMember(u).complete();
                                List<Role> lr = mb.getRoles();
                                boolean roleFound = false;
                                for (Role r : lr) {
                                    if (r.getIdLong() == roleID) {
                                        roleFound = true;
                                        break;
                                    }
                                }
                                if (!roleFound) g.addRoleToMember(mb, g.getRoleById(roleID)).queue();
                            } catch (Exception e) {

                            }
                        }
                    }
                } else {
                    Container.getGuild().getTextChannelById(797854275325001738L).sendMessage(User.fromId(222733101770604545L).getAsMention() + " check " + m.getJumpUrl() + ". Someone has responded with a reaction that has not yet been added.").queue();
                }
            }
        }
    }
}
