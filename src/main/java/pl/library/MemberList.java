package pl.library;

import pl.library.db.LibraryDao;

import java.util.List;
import java.util.Scanner;

public class MemberList {
    private LibraryDao libraryDao;

    private Scanner scanner = new Scanner(System.in);

    public MemberList(LibraryDao libraryDao) {
        this.libraryDao = libraryDao;
    }


    public Member findMember() {
        System.out.println("Type your name: ");
        String member = scanner.nextLine();
        List<Member> member2 = libraryDao.findMember(member);
        if(member2.isEmpty()){
            return null;
        }
        return member2.get(0);
    }

    public void showMembers() {
        List<Member> members = libraryDao.showMembers();
        for (Member member : members) {
            System.out.println(member.toString());
        }
    }
}

