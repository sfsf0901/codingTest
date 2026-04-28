package programmers.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q42892 {

    // commit test
    public static void main(String[] args) {

    }

    private static class Node {
        int index;
        int x;
        int y;
        Node left;
        Node right;

        public Node(int index, int x, int y) {
            this.index = index;
            this.x = x;
            this.y = y;
        }
    }

    private static int[][] solution(int[][] nodeinfo) {
        // 전위(왼쪽 위), 후위(왼쪽 아래)
        Node root = makeTree(nodeinfo);

        ArrayList<Integer> preOrderList = new ArrayList<>();
        preOrder(root, preOrderList);

        ArrayList<Integer> postOrderList = new ArrayList<>();
        postOrder(root, postOrderList);

        int[][] answer = new int[2][nodeinfo.length];
        answer[0] = preOrderList.stream().mapToInt(Integer::intValue).toArray();
        answer[1] = postOrderList.stream().mapToInt(Integer::intValue).toArray();

        return answer;
    }

    private static Node makeTree(int[][] nodeinfo) {
        Node[] nodes = new Node[nodeinfo.length];
        for (int i = 0; i < nodeinfo.length; i++) {
            nodes[i] = new Node(i + 1, nodeinfo[i][0], nodeinfo[i][1]);
        }

        Arrays.sort(nodes, (o1, o2) -> {
            if (o1.y == o2.y) {
                return Integer.compare(o1.x, o2.x);
            }
            return Integer.compare(o2.y, o1.y);
        });

        Node root = nodes[0];

        for (int i = 1; i < nodes.length; i++) {
            Node parent = root;
            while (true) {
                if (nodes[i].x < parent.x) {
                    if (parent.left == null) {
                        parent.left = nodes[i];
                        break;
                    } else {
                        parent = parent.left;
                    }
                } else {
                    if (parent.right == null) {
                        parent.right = nodes[i];
                        break;
                    } else {
                        parent = parent.right;
                    }
                }
            }
        }

        return nodes[0];
    }

    private static void preOrder(Node curr, ArrayList<Integer> answer) {
        if (curr == null) {
            return;
        }
        answer.add(curr.index);
        preOrder(curr.left, answer);
        preOrder(curr.right, answer);
    }

    private static void postOrder(Node curr, ArrayList<Integer> answer) {
        if (curr == null) {
            return;
        }
        postOrder(curr.left, answer);
        postOrder(curr.right, answer);
        answer.add(curr.index);
    }



    private static int[][] solution2(int[][] nodeinfo) {
        Node[] nodes = new Node[nodeinfo.length];

        for (int i = 0; i < nodeinfo.length; i++) {
            nodes[i] = new Node(i + 1, nodeinfo[i][0], nodeinfo[i][1]);
        }

        Arrays.sort(nodes, (a, b) -> {
            if (a.y == b.y) {
                return a.x - b.x;   // y 같으면 x 오름차순
            }
            return b.y - a.y;       // y 내림차순
        });

        Node root = nodes[0];

        for (int i = 1; i < nodes.length; i++) {
            insert(root, nodes[i]);
        }

        List<Integer> preorder = new ArrayList<>();
        List<Integer> postorder = new ArrayList<>();

        preorder(root, preorder);
        postorder(root, postorder);

        int[][] answer = new int[2][nodeinfo.length];
        for (int i = 0; i < nodeinfo.length; i++) {
            answer[0][i] = preorder.get(i);
            answer[1][i] = postorder.get(i);
        }

        return answer;
    }

    private static void insert(Node parent, Node child) {
        if (child.x < parent.x) {
            if (parent.left == null) {
                parent.left = child;
            } else {
                insert(parent.left, child);
            }
        } else {
            if (parent.right == null) {
                parent.right = child;
            } else {
                insert(parent.right, child);
            }
        }
    }

    private static void preorder(Node node, List<Integer> result) {
        if (node == null) return;

        result.add(node.index);
        preorder(node.left, result);
        preorder(node.right, result);
    }

    private static void postorder(Node node, List<Integer> result) {
        if (node == null) return;

        postorder(node.left, result);
        postorder(node.right, result);
        result.add(node.index);
    }
}
