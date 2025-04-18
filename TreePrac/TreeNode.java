import java.util.ArrayList;
import java.util.List;

public class TreeNode<T> {
    private T value;
    private List<TreeNode<T>> children;

    public TreeNode(T value) {
        this.value = value;
        this.children = new ArrayList<>();
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public List<TreeNode<T>> getChildren() {
        return children;
    }

    public void addChild(TreeNode<T> child) {
        this.children.add(child);
    }

    public void removeChild(TreeNode<T> child) {
        this.children.remove(child);
    }
}
