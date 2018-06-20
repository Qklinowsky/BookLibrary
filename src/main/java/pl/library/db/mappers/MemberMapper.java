package pl.library.db.mappers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.library.Member;
import pl.library.users.UserRole;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MemberMapper implements ResultSetMapper<Member> {

    private static final Logger log = LoggerFactory.getLogger(MemberMapper.class);

    @Override
    public List<Member> map(ResultSet resultSet) {
        List<Member> members = new ArrayList<>();
        try {
            while (resultSet.next()) {
                UUID id = UUID.fromString(resultSet.getString("MEMBERS.ID"));
                String password = resultSet.getString("MEMBERS.PASSWORD");
                String name = resultSet.getString("MEMBERS.NAME");
                String roleName = resultSet.getString("MEMBERS.ROLE");
                UserRole userRole = UserRole.findByName(roleName);
                Member member = new Member(id, password, name, userRole);
                members.add(member);
            }
        } catch (SQLException e) {
            log.error("Failed while mapping to member list", e);
        }
        return members;
    }
}
