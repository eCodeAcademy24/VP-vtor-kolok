package mk.ecode.events.service;

import mk.ecode.events.model.User;

public interface AuthService {

    User login(String username, String password);
}
