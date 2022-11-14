package cn.pxl.dataStructure.demo01;

public interface MyArrayListInterface <E> {
    //向数组尾部添加一个元素
    void add(E element);
    //指定的位置插入一个元素
    void add(int index,E element);
    //删除指定所引处一个元素
    E remove(int index);
    //查看数组长度
    int length();
    //查看当前数组中的元素个数
    int size();
    //获取指定索引处的元素
    E get(int index);
    //指定的位置更新元素信息
    E set(int index,E element);
    // 查看元素的位置
    int indexOf(E element);
    // 清除所有元素
    void clear();
    // 是否为空
    boolean isEmpty();
    // 是否包含某个元素
    boolean contains(E element);
}
