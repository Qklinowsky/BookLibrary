import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Member {

    private final UUID ID;
    private String name;

    private List<Book> membersBook;

    public Member(String name) {
        this.ID = UUID.randomUUID();
        this.name = name;
        this.membersBook = new ArrayList<Book>();
    }

    public String getName() {
        return name;
    }

    public UUID getID() {
        return ID;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Book> getMembersBook() {
        return membersBook;
    }


}
