package controller.commands;


/**
 * Factory for providing instances of commands
 */
public class CommandFactory {

    //should not be created as it's factory;
    private CommandFactory() {
    }

    public static LoginCommand getLoginCommand() {
        return new LoginCommand();
    }

    public static RegisterCommand getRegisterCommand() {
        return new RegisterCommand();
    }

    public static UnknownCommand getUnknownCommand() {
        return new UnknownCommand();
    }

    public static AdminProfileCommand getAdminProfileCommand() {
        return new AdminProfileCommand();
    }

    public static LoginCommand getLodoutCommand() {
        return new LoginCommand();
    }

    public static UserProfileCommand getUseProfileCommand() {
        return new UserProfileCommand();
    }
}
