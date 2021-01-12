package commands;

import commands.interfaces.Aliases;
import commands.interfaces.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ThreadLocalRandom;

public class CommandFunCommands implements Command, Aliases {

    private Map<String, String> map = new HashMap<>();

    private void refreshMap() {
        map.clear();
        // @formatter:off
        map.put("fish", "https://media.discordapp.net/attachments/625373674286874665/628653269790490665/618747043854155776.gif");
        map.put("bee", "https://media.discordapp.net/attachments/494702234744127498/634442208291979285/aaaaaaaaaaa.gif");
        map.put("duck", "https://i.gifer.com/XOsX.gif");
        map.put("train", "https://cdn.discordapp.com/attachments/314814038662053888/712807142931038239/ezgif.com-video-to-gif_1.gif");
        map.put("tram", getTram());
        map.put("cat", "https://media.discordapp.net/attachments/669158942139613186/740009211391377432/45452.gif");
        map.put("cat2", "https://media.discordapp.net/attachments/730561686590717964/740596417390706788/5f2ac03410bd0496934562.gif");
        map.put("fuck", "https://tenor.com/view/wtf-haha-flirty-fuck-smile-gif-15931510");
        map.put("isboton", "Yes");
        map.put("beef", "https://tenor.com/view/beef-meat-food-gif-15154168");
        map.put("dackel", "https://tenor.com/vdH5.gif");
        map.put("dog", "Cats are better than dogs. Change my mind.");
        map.put("yosano", "https://media1.tenor.com/images/98526a8f4b41eb6fd4c8c8501903f257/tenor.gif?itemid=16708577");
        map.put("doggo", "https://media.discordapp.net/attachments/749622297534398505/776042111455854632/dsad.gif");
        map.put("banana", "https://di.phncdn.com/www-static/images/pornhubAwards/banana.gif?cache=2020121204");
        map.put("redfish", "https://media.discordapp.net/attachments/532648338391040031/634736034743058462/618626677995733009.gif");
        map.put("steamlocomotive", getSteamLoco());
        map.put("thomas", "https://tenor.com/view/thomas-in-hell-thomas-the-train-fire-gif-15403634");
        map.put("nugget", "https://media.discordapp.net/attachments/505765732840243211/783755095962681348/image0-1-2.gif");
        map.put("tiger", "https://cdn.discordapp.com/attachments/581602258102517760/798280602641367050/Ausbilder_geht_Gassi.gif");
        // @formatter:on
    }

    @Override
    public String[] getAliases() {
        refreshMap();
        return map.keySet().toArray(new String[map.size()]);
    }

    @Override
    public String getName() {
        return "fish";
    }

    @Override
    public void onCall(MessageReceivedEvent event) {

        // Eventuell bessere Methode aussuchen? :/
        refreshMap();

        String messageTextRaw = event.getMessage().getContentRaw();
        String cmd = messageTextRaw.substring(1);

        for (Entry<String, String> entry : map.entrySet()) {
            if (cmd.matches("^" + entry.getKey() + "(\\s.*|$)"))
                event.getGuild().getTextChannelById(797945706819223572L).sendMessage(entry.getValue()).queue();
        }

    }

    private String getTram() {
        // @formatter:off
        switch (ThreadLocalRandom.current().nextInt(1, 4 + 1)) {
            case 1:
                return "https://media1.tenor.com/images/6ae61031c768f8e2980908bd1a67b850/tenor.gif?itemid=14228705";
            case 2:
                return "https://media.giphy.com/media/Tl2u4yXA90vQs/giphy.gif";
            case 3:
                return "https://media.giphy.com/media/80QxoQtZb4Gli/giphy.gif";
            case 4:
                return "https://iruntheinternet.com/lulzdump/images/gifs/gandalf-stopping-tram-train-shall-not-pass-1387281258e.gif";
            default:
                return "Error Random Tram";
        }
        // @formatter:on
    }

    private String getSteamLoco() {
        // @formatter:off
        switch (ThreadLocalRandom.current().nextInt(1, 2 + 1)) {
            case 1:
                return "https://tenor.com/view/steam-train-im-coming-gif-7189638";
            case 2:
                return "https://tenor.com/view/thomas-in-hell-thomas-the-train-fire-gif-15403634";
            default:
                return "Error Random Steam Locomotive";
        }
        // @formatter:on
    }
}
