import java.util.ArrayList;
import java.util.Scanner;

public class MemberList {
    private ArrayList<Member> members;

    private Scanner scanner = new Scanner(System.in);

    public MemberList() {
        members = new ArrayList<Member>();
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public void addMember() {
        String newMemberName = null;
        do {
            System.out.println("Podaj swoja godnosc: ");
            newMemberName = scanner.nextLine();
        } while (!isValidName(newMemberName));

        for (Member member : members) {
            if (member.getName().equals(newMemberName)) {
                System.out.println("Taka osoba juz istnieje.");
                return;
            }
        }
        Member newMember = new Member(newMemberName);
        members.add(newMember);
        System.out.println("Dodano " + newMember.getName() + " do rejestru.");
    }


    public boolean isValidName(String name) {
        for (int i = 0; i < name.length(); i++) {
            char ch = name.charAt(i);
            if (Character.isDigit(ch)) {
                return false;
            }
        }
        return true;
    }

    public void showMembers() {
        if (members.size() > 0) {
            for (Member member : members) {
                System.out.println("Pani/Pan " + member.getName() + " numer ID " + member.getID());
            }
        }
    }

    public Member findMember() {
        System.out.println("Podaj swoje imie i nazwisko: ");
        String member = scanner.nextLine();
        int size = members.size();
        for (Member member1 : members) {
            if (member1.getName().equals(member)) {
                return member1;
            }
        }
        return null;
    }
}

