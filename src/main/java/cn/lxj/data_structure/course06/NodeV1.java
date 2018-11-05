package cn.lxj.data_structure.course06;

/**
 * NodeV1
 * description 节点
 * create class by lxj 2018/11/5
 **/
public class NodeV1 {
    private int data;
    private NodeV1 next;

    public NodeV1(int data, NodeV1 next) {
        this.data = data;
        this.next = next;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public NodeV1 getNext() {
        return next;
    }

    public void setNext(NodeV1 next) {
        this.next = next;
    }
}