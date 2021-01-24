package listener;

import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.ShutdownEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import storage.Container;

import javax.annotation.Nonnull;

public class StartStopListener extends ListenerAdapter {

    @Override
    public void onShutdown(@Nonnull ShutdownEvent event) {
        System.exit(event.getCode());
    }

    @Override
    public void onReady(@Nonnull ReadyEvent event) {
        Container.setGuild(event.getJDA().getGuildById(797514319410495503L));
        ReactionListener.checkIfReacted();
    }
}
