package goldteam.meetup.debug;

/**
 * Created by c on 8/3/2016.
 */
public class Nietzsche {

    private String name = "Friedrich Nietzsche";
    private String email = "fred@weimar.com";
    private String title = "Some sick slam poetry";
    private String body = "That which does not kill us makes us stronger.\n" +
            "When you look into an abyss, the abyss also looks into you.\n" +
            "Without music, life would be a mistake.\n" +
            "Insanity in individuals is something rare - but in groups, parties, nations and epochs, it is the rule.\n" +
            "There are no facts, only interpretations.\n" +
            "He who has a why to live can bear almost any how.\n" +
            "The surest way to corrupt a youth is to instruct him to hold in higher esteem those who think alike than those who think differently.\n" +
            "One must still have chaos in oneself to be able to give birth to a dancing star.\n" +
            "You have your way. I have my way. As for the right way, the correct way, and the only way, it does not exist.\n" +
            "All truly great thoughts are conceived by walking.";

    private String datetime = "08/02/2016 5:30am";

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
