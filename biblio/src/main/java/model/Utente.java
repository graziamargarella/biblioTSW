package model;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class Utente {
    String mail, password, nome, cognome;
    boolean isAdmin;

    public Utente(String mail, String password, String nome, String cognome, boolean isAdmin){
        this.mail = mail;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.isAdmin = isAdmin;
    }

    public Utente() {}

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public boolean checkIsAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "mail='" + mail + '\'' +
                ", passwordHash='" + password + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Utente)) return false;
        Utente utente = (Utente) o;
        return isAdmin == utente.isAdmin && getMail().equals(utente.getMail()) && getPassword().equals(utente.getPassword()) && Objects.equals(getNome(), utente.getNome()) && Objects.equals(getCognome(), utente.getCognome());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMail(), getPassword(), getNome(), getCognome(), isAdmin);
    }
}
