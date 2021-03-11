package tasks.first;

import java.util.ArrayList;

public class Vertex {
    private int index;
    private ArrayList<Vertex> inheritedVertexes;

    public Vertex(int index) {
        this.index = index;
//        this.inheritedVertexes = inheritedVertexes;
    }



    public int getIndex() {
        return index;
    }

    public ArrayList<Vertex> getInheritedVertexes() {
        return inheritedVertexes;
    }

    public void setInheritedVertexes(ArrayList<Vertex> inheritedVertexes) {
        this.inheritedVertexes = inheritedVertexes;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "index=" + index +
                '}';
    }
}
