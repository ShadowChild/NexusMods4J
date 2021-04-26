package uk.co.innoxium.nexusmods4j.response;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class User {

    // We do not currently include the references to "is_premium?" and "is_supporter?" due to the ? in the key
    // we may look at a custom solution in the future, as GSON does not have a @KeyName equivalent

    public long user_id;
    public String key;
    public String name;
    public boolean is_premium, is_supporter;
    public String email;
    public String profile_url; // This is actually a link to your profile avatar, not profile
}
