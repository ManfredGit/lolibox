package io.loli.box.social;

import io.loli.box.entity.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.social.security.SocialUser;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class SocialUserDetails extends SocialUser {

    private Long id;


    private String email;


    private Role role;

    private SocialMediaService socialSignInProvider;

    public SocialUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public static Builder getBuilder() {
        return new Builder();
    }
    //Getters are omitted for the sake of clarity.

    public static class Builder {

        private Long id;

        private String username;

        private String email;

        private String password;

        private Role role;

        private SocialMediaService socialSignInProvider;

        private Set<GrantedAuthority> authorities;

        public Builder() {
            this.authorities = new HashSet<>();
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder password(String password) {
            if (password == null) {
                password = "SocialUser";
            }

            this.password = password;
            return this;
        }

        public Builder role(Role role) {
            this.role = role;

            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.toString());
            this.authorities.add(authority);

            return this;
        }

        public Builder socialSignInProvider(SocialMediaService socialSignInProvider) {
            this.socialSignInProvider = socialSignInProvider;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public SocialUserDetails build() {
            SocialUserDetails user = new SocialUserDetails(username, password, authorities);
            user.id = id;
            user.role = role;
            user.socialSignInProvider = socialSignInProvider;
            user.email = email;
            return user;
        }
    }


    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}