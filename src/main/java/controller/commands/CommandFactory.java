package controller.commands;

public class CommandFactory {

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
}
