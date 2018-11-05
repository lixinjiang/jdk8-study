package cn.lxj.data_structure.course06;

/**
 * ReserveNode
 * description 链表反转——迭代法
 * create class by lxj 2018/11/5
 **/
public class ReserveNode {

    /**
     * 有问题
     *
     * @param node
     * @return
     */
    public static Node reserveV1(Node node) {
        Node prev = null;
        Node now = node;
        while (node != null) {
            Node next = node.next;
            now.next = prev;
            prev = now;
            now = next;
        }
        return prev;
    }

    public static Node reserveV2(Node node) {
        if (node == null || node.next == null) {
            return node;
        } else {
            Node headNode = reserveV2(node.next);
            node.next.next = node;
            node.next = null;
            return headNode;
        }
    }

    public static Node reserveV3(Node node) {
        Node previousNode = null;
        Node currentNode = node;
        Node headNode = null;
        while (currentNode != null) {
            Node nextNode = currentNode.next;
            if (nextNode == null) {
                headNode = currentNode;
            }
            currentNode.next = previousNode;
            previousNode = currentNode;
            currentNode = nextNode;
        }
        return headNode;
    }


    public static void main(String[] args) {
        Node node = new Node(1, null);
        node.next = new Node(2, null);
        node.next.next = new Node(3, null);
        node.next.next.next = new Node(4, null);
        node.next.next.next.next = new Node(5, null);
//        while (node != null) {
//            System.out.print(node.index+",");
//            node = node.next;
//        }
        Node node1 = reserveV3(node);
        System.out.println("-------------------");
        while (node1 != null) {
            System.out.print(node1.index + ",");
            node1 = node1.next;
        }
    }
}
