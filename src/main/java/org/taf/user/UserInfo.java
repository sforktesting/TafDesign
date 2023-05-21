package org.taf.user;

import org.taf.utils.PropertiesReader;

public class UserInfo {
    private final String userName;
    private final String password;
    private final String baseUrl;
    private final String signedInText;
    private final String signedOutText;

    public UserInfo() {
        PropertiesReader repo = new PropertiesReader("src/main/resources/Locators.properties");
        this.userName = repo.getBy("default.user.name");
        this.password = repo.getBy("default.user.password");
        this.baseUrl = repo.getBy("url");
        this.signedInText = repo.getBy("signed.in.successfully");
        this.signedOutText = repo.getBy("logged.out.successfully");
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getSignedInText() {
        return signedInText;
    }

    public String getSignedOutText() {
        return signedOutText;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
