package com.test.e.commerce.e.commerce.service;

import java.util.concurrent.TimeoutException;

public interface AuthenticationService {

    boolean authenticateUser(String apiKey) throws TimeoutException;

    String login(String name);
}
