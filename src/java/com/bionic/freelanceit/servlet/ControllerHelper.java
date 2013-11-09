
package com.bionic.freelanceit.servlet;

import com.bionic.freelanceit.commands.*;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

public class ControllerHelper {

    private static ControllerHelper instance = null;
    HashMap<String, ICommand> commands = new HashMap<String, ICommand>();

    public ControllerHelper() {
        commands.put("login", new CommandLogin());
        commands.put("updateProfile", new CommandChangeProfile());
        commands.put("createTask", new CommandCreateTask());
        commands.put("viewMyProfile", new CommandViewMyProfile());
        commands.put("viewMyTasks", new CommandViewMyTasks());
    }

    public ICommand getCommand(HttpServletRequest request) {
        ICommand command = commands.get(request.getParameter("command"));
        if (command == null) {
            command = new CommandMissing();
        }
        return command;
    }

    public static ControllerHelper getInstance() {
        if (instance == null) {
            instance = new ControllerHelper();
        }
        return instance;
    }
}
