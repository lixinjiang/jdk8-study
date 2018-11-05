package cn.lxj.data_structure.course06;

/**
 * SinglyLinkedList
 * description
 * （1）单链表的插入、删除、查找操作
 * （2）链表中存储的是int类型的数据
 * create class by lxj 2018/11/5
 **/
public class SinglyLinkedList {
    private NodeV1 head = null;

    public NodeV1 findByValue(int value) {
        NodeV1 p = head;
        while (p != null && p.getData() != value) {
            p = p.getNext();
        }
        return p;
    }

    public NodeV1 findByIndex(int index) {
        NodeV1 p = head;
        int pos = 0;
        while (p != null && pos != index) {
            p = p.getNext();
            ++pos;
        }
        return p;
    }

    public void insertToHead(int value) {
        NodeV1 newNode = new NodeV1(value, null);
        insertToHead(newNode);
    }

    public void insertToHead(NodeV1 newNode) {
        if (head == null) {
            head = newNode;
        } else {
            newNode.setNext(head);
            head = newNode;
        }
    }

    public void insertAfter(NodeV1 p, NodeV1 newNode) {
        if (p == null) return;
        newNode.setNext(p.getNext());
        p.setNext(newNode);
    }

    public void insertBefore(NodeV1 p, NodeV1 newNode) {
        if (p == null) return;
        if (head == p) {
            insertToHead(newNode);
            return;
        }
        NodeV1 q = head;
        while (q != null && q.getNext() != p) {
            q = q.getNext();
        }
        if (q == null) {
            return;
        }
        newNode.setNext(p);
        q.setNext(newNode);
    }

    public void deleteByNode(NodeV1 p) {
        if (p == null || head == null) return;
        if (p == head) {
            head = head.getNext();
            return;
        }
        NodeV1 q = head;
        while (q != null && q.getNext() != p) {
            q = q.getNext();
        }
        if (q == null) {
            return;
        }
        q.setNext(q.getNext().getNext());
    }

    public void deleteByValue(int value) {
        if (head == null) return;
        NodeV1 p = head;
        NodeV1 q = null;
        while (p != null && p.getData() != value) {
            q = p;
            p = p.getNext();
        }

        if (p == null) return;

        if (q == null) {
            head = head.getNext();
        } else {
            q.setNext(q.getNext().getNext());
        }
    }

    public void printAll() {
        NodeV1 p = head;
        while (p != null) {
            System.out.print(p.getData() + " ");
            p = p.getNext();
        }
        System.out.println();
    }

    public static NodeV1 createNode(int value) {
        return new NodeV1(value, null);
    }
}