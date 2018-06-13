import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Member {

    private final UUID ID;

    private String name;

    public Member(String name) {
        this.ID = UUID.randomUUID();
        this.name = name;

    }

    public Member(UUID ID, String name) {
        this.ID = ID;
        this.name = name;
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



    @Override
    public String toString() {
        return "Member{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                '}';
    }


}
