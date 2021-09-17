package model;

import java.util.Objects;

public class Ordine {
    int id;
    String isbn, cliente;

    public Ordine() {
    }

    public Ordine(int id, String isbn, String cliente) {
        this.id = id;
        this.isbn = isbn;
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Ordine{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", cliente='" + cliente + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ordine)) return false;
        Ordine ordine = (Ordine) o;
        return getId() == ordine.getId() && Objects.equals(getIsbn(), ordine.getIsbn()) && Objects.equals(getCliente(), ordine.getCliente());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getIsbn(), getCliente());
    }
}
