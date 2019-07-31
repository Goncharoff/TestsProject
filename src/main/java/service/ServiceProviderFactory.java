package service;

public class ServiceProviderFactory {

    public static UserService provideUserService() {
        return new UserService();
    }
}
