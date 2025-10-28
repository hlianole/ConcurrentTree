import com.hlianole.jetbrains.internship.tree.ConcurrentAVLTree;
import com.hlianole.jetbrains.internship.tree.node.EmptyNode;
import com.hlianole.jetbrains.internship.tree.node.Node;

public class TestingTree extends ConcurrentAVLTree {

    public int getRightDepth() {
        Node current = root;
        int depth = 0;
        while (current != EmptyNode.getInstance()) {
            depth++;
            current = current.getRight();
        }
        return depth;
    }
}
