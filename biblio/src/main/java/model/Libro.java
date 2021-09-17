package model;

import java.util.Objects;

public class Libro {
    String isbn, titolo, autore, editore, descrizione, nomeCategoria, prezzoStr;
    int sconto;
    float prezzo;

    public Libro() {}

    public Libro(String isbn, String titolo, String autore, String editore, float prezzo, String descrizione, String nomeCategoria, int sconto){
        this.isbn = isbn;
        this.titolo = titolo;
        this.autore = autore;
        this.editore = editore;
        this.prezzo = prezzo;
        this.descrizione = descrizione;
        this.nomeCategoria = nomeCategoria;
        this.sconto = sconto;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getEditore() {
        return editore;
    }

    public void setEditore(String editore) {
        this.editore = editore;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzoStr(String prezzoStr) {
        this.prezzoStr = prezzoStr;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public int getSconto() {
        return sconto;
    }

    public void setSconto(int sconto) {
        this.sconto = sconto;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "isbn='" + isbn + '\'' +
                ", titolo='" + titolo + '\'' +
                ", autore='" + autore + '\'' +
                ", editore='" + editore + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", nomeCategoria='" + nomeCategoria + '\'' +
                ", sconto=" + sconto +
                ", prezzo=" + prezzo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Libro)) return false;
        Libro libro = (Libro) o;
        return getSconto() == libro.getSconto() && Float.compare(libro.getPrezzo(), getPrezzo()) == 0 && Objects.equals(getIsbn(), libro.getIsbn()) && Objects.equals(getTitolo(), libro.getTitolo()) && Objects.equals(getAutore(), libro.getAutore()) && Objects.equals(getEditore(), libro.getEditore()) && Objects.equals(getDescrizione(), libro.getDescrizione()) && Objects.equals(getNomeCategoria(), libro.getNomeCategoria());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIsbn(), getTitolo(), getAutore(), getEditore(), getDescrizione(), getNomeCategoria(), getSconto(), getPrezzo());
    }
}
