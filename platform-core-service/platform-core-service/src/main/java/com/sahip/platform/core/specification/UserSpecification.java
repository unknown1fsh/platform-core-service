package com.sahip.platform.core.specification;

import com.sahip.platform.core.entity.UserAccount;
import com.sahip.platform.core.enums.UserStatus;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {

    public static Specification<UserAccount> usernameContains(String username) {
        return (root, query, cb) ->
                (username == null || username.trim().isEmpty())
                        ? cb.conjunction()
                        : cb.like(cb.lower(root.get("username")), "%" + username.toLowerCase() + "%");
    }

    public static Specification<UserAccount> statusEquals(UserStatus status) {
        return (root, query, cb) ->
                status == null ? cb.conjunction() : cb.equal(root.get("status"), status);
    }
}
