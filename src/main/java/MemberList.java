import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberList {
    private LibraryDao libraryDao;

    private Scanner scanner = new Scanner(System.in);

    public MemberList(LibraryDao libraryDao) {
        this.libraryDao = libraryDao;
    }

    public void addMember() {
        String newMemberName = null;
        do {
            System.out.println("Podaj swoja godnosc: ");
            newMemberName = scanner.nextLine();
        } while (!isValidName(newMemberName));

        List<Member> member1 = libraryDao.findMember(newMemberName);
        if(!member1.isEmpty()){
            System.out.println("Member with this name already exist.");
            return;
        }
        Member newMember = new Member(newMemberName);
        libraryDao.addMember(newMember);
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

//    public void showMembers() {
//        if (members.size() > 0) {
//            for (Member member : members) {
//                System.out.println("Pani/Pan " + member.getName() + " numer ID " + member.getID());
//            }
//        }
//    }

    public Member findMember() {
        System.out.println("Podaj swoje imie i nazwisko: ");
        String member = scanner.nextLine();
        List<Member> member2 = libraryDao.findMember(member);
        if(member2.isEmpty()){
            return null;
        }
        return member2.get(0);
    }
}

