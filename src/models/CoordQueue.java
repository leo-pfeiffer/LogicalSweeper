package models;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class CoordQueue {

    private final LinkedList<Coord> queue = new LinkedList<>();
    private final HashSet<Coord> uniqueCoords = new HashSet<>();

    public Iterator<Coord> iterator() {
        return queue.iterator();
    }

    public HashSet<Coord> toSet() {
        return uniqueCoords;
    }

    public void insert(Coord node) {
        // only add unique nodes
        if (!uniqueCoords.contains(node)) {
            uniqueCoords.add(node);
            queue.add(node);
        }
    }

    public Coord remove() {
        Coord toRemove = queue.remove();
        uniqueCoords.remove(toRemove);
        return toRemove;
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public boolean contains(Coord node) {
        return uniqueCoords.contains(node);
    }

    public int size() {
        return queue.size();
    }

    public String toString() {
        return queue.toString();
    }
}