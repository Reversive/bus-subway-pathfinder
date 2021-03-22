package utils;

import java.util.HashSet;
import java.util.Set;

public class NodeSet {
    private final Set<Node> nodeSet;

    public NodeSet() {
        this.nodeSet = new HashSet<>();
    }

    public Set<Node> getNodeSet() {
        return nodeSet;
    }
}
