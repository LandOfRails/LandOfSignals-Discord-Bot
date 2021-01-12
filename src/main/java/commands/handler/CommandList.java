package commands.handler;

import commands.CommandFunCommands;
import commands.CommandHelp;
import commands.CommandIdea;
import commands.CommandInfo;
import commands.interfaces.Command;

import java.util.ArrayList;
import java.util.List;

public class CommandList {

    private List<Command> commands = new ArrayList<>();

    public CommandList() {
        commands.add(new CommandFunCommands());
        commands.add(new CommandHelp());
        commands.add(new CommandIdea());
        commands.add(new CommandInfo());
    }

    public List<Command> getCommands() {
        return commands;
    }

}
