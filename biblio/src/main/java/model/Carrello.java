package model;

import java.util.*;

public class Carrello {
    List items;

    public Carrello() {
        items = new ArrayList<Libro>();
    }

    public void addItem(Libro item) {
        if(!items.contains(item)) {
            items.add(item);
        }
        else{

        }
    }

    public void deleteItem(Libro item) {
        items.remove(item);
    }

    public List<Libro> getItems() {
        return items;
    }

    public float getTotale(){
        float tot = 0f;
        for(int i = 0; i < items.size(); i++){
            Libro libro = getItems().get(i);
            tot += libro.getPrezzo();
        }
        return tot;
    }

    public void deleteItems() {
        this.items = new ArrayList<Libro>();
    }
}