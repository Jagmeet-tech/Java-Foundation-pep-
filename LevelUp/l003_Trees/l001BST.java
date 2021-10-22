public class BST{

    public static TreeNode getLeftMost(TreeNode node) {
        if (node == null)
            return node;

        while (node.left != null)
            node = node.left;

        return node;
    }

    public static TreeNode getRightMost(TreeNode node) {
        if (node == null)
            return node;

        while (node.right != null)
            node = node.right;
        return node;
    }

    public static void successorAndPredcessor(TreeNode node, int data) {

        TreeNode curr = node, pred = null, succ = null;

        while (curr != null) {
            if (curr.val == data) {

                TreeNode leftMost = getLeftMost(node.right);
                succ = leftMost != null ? leftMost : succ;

                TreeNode rightMost = getRightMost(node.left);
                pred = rightMost != null ? rightMost : pred;

                break;

            } else if (data < curr.val) {
                succ = curr;
                curr = curr.left;
            } else {
                pred = curr;
                curr = curr.right;
            }
        }
    }

    // S: logn, T : o(n)
    public static int kthLargestEle(TreeNode node, int k) {

    }
}