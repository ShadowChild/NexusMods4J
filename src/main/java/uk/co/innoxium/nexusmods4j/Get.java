package uk.co.innoxium.nexusmods4j;


public class Get extends RequestSender {

    public Get(String apiKey) {

        super(apiKey);
    }

    public Users user() {

        return new Users(apiKey);
    }

    public Games games() {

        return new Games(apiKey);
    }

    public Misc misc() {

        return new Misc(apiKey);
    }
}
