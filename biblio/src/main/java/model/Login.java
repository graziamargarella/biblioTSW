package model;

import java.sql.Timestamp;

public class Login {
    private String id;
    private String email;
    private String token;
    private Timestamp time;

    public String getId(){return id;}
    public void setId(String id){this.id = id;}

    public String getEmail() {return email; }
    public void setEmail(String email) {this.email = email; }

    public String getToken() {return token;}
    public void setToken(String token) {this.token = token; }

    public Timestamp getTime() {return time;}
    public void setTime(Timestamp time) {this.time = time; }

    @Override
    public String toString() {
        return "Login{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", token='" + token + '\'' +
                ", time=" + time +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Login other = (Login) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (!email.equals(other.email))
            return false;
        if (time == null) {
            if (other.time != null)
                return false;
        } else if (!time.equals(other.time))
            return false;
        if (token == null) {
            if (other.token != null)
                return false;
        } else if (!token.equals(other.token))
            return false;
        return true;
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((time == null) ? 0 : time.hashCode());
        result = prime * result + ((token == null) ? 0 : token.hashCode());
        return result;
    }
}

