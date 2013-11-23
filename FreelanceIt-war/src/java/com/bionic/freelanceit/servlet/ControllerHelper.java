package com.bionic.freelanceit.servlet;

import com.bionic.freelanceit.commands.CommandEditProfile;
import com.bionic.freelanceit.commands.CommandCreateTask;
import com.bionic.freelanceit.commands.CommandMissing;
import com.bionic.freelanceit.commands.CommandViewProfile;
import com.bionic.freelanceit.commands.CommandViewTasksOfUser;
import com.bionic.freelanceit.commands.ICommand;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

public class ControllerHelper {

    private static ControllerHelper instance = null;
    HashMap<String, ICommand> commands = new HashMap<String, ICommand>();

    public ControllerHelper() {
        commands.put("updateProfile", new CommandEditProfile());
        commands.put("createTask", new CommandCreateTask());
        commands.put("viewMyProfile", new CommandViewProfile());
        commands.put("viewTasksCreatedByUser", new CommandViewTasksOfUser());
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
