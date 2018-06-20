package pl.library.users;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum UserRole {
    ANONYMOUS(Feature.LOGIN, Feature.REGISTER),
    USER(Feature.BORROW, Feature.RETURN_BOOK, Feature.BROWSE, Feature.LOGOUT),
    LIBRARIAN(Feature.ADD_BOOK, Feature.REMOVE_BOOK, Feature.LOGOUT),
    ADMIN(Feature.ADD_BOOK, Feature.REMOVE_BOOK, Feature.ADD_MEMBER, Feature.REMOVE_MEMBER, Feature.LOGOUT);

    private Set<Feature> features;

    UserRole(Feature... features) {
        this.features = new HashSet<>(Arrays.asList(features));
    }


    public static UserRole findByName(String roleName) {
        for (UserRole userRole : UserRole.values()) {
            if(userRole.toString().equals(roleName)) {
                return userRole;
            }
        }
        throw new IllegalArgumentException("Couldn't find role for rolename = " + roleName);
    }

    public Set<Feature> getFeatures() {
        return features;
    }
}
