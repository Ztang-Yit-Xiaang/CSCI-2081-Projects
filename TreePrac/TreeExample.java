public class TreeExample {
    public static void main(String[] args) {
        TreeNode<String> root = new TreeNode<>("Root");
        TreeNode<String> child1 = new TreeNode<>("Child 1");
        TreeNode<String> child2 = new TreeNode<>("Child 2");

        root.addChild(child1);
        root.addChild(child2);

        TreeNode<String> grandChild = new TreeNode<>("Grandchild");
        TreeNode<String> grandChild2 = new TreeNode<>("Grandchild2");
        child1.addChild(grandChild);
        child1.addChild(grandChild2);
        // Traversing the tree
        traverse(root, 0);
    }

    private static void traverse(TreeNode<?> node, int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("--");
        }
        System.out.println(node.getValue());
        int m = 1;
        for (TreeNode<?> child : node.getChildren()) {
            System.out.println(m);
            m++;
            traverse(child, depth + 1);
        }
    }
}
