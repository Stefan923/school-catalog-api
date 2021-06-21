package me.stefan923.schoolcatalog.dto;

import java.util.List;
import java.util.Objects;

public class JwtResponse {

    private String token;
    private long id;
    private String username;
    private String email;
    private List<String> roles;

    public JwtResponse(String token, long id, String username, String email, List<String> roles) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JwtResponse that = (JwtResponse) o;
        return getId() == that.getId()
                && getToken().equals(that.getToken())
                && Objects.equals(getUsername(), that.getUsername())
                && Objects.equals(getEmail(), that.getEmail())
                && Objects.equals(getRoles(), that.getRoles());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getToken(), getId(), getUsername(), getEmail(), getRoles());
    }

    @Override
    public String toString() {
        return "JWTResponse{" +
                "token='" + token + '\'' +
                ", id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                '}';
    }

}
