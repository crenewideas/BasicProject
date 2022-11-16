package cn.pxl.dataStructure.demo01Learning;
import cn.pxl.dataStructure.common.AbstractList;

public class MyArrayListLearning<E> extends AbstractList<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private E[] elements;

    public MyArrayListLearning() {
        this(DEFAULT_CAPACITY);
    }

    public MyArrayListLearning(int capacity) {
        this.elements = (E[])new Object[Math.max(capacity, DEFAULT_CAPACITY)];
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public E get(int index) {
        rangeCheck(index);
        return elements[index];
    }

    @Override
    public E set(int index, E element) {
        rangeCheck(index);
        E oldElement = elements[index];
        elements[index] = element;
        return oldElement;
    }

    @Override
    public void add(int index, E element) {
        //检查index是否合法。
        rangeCheckForAdd(index);
        //检查数组是否需要扩容。
        addCapacityIfNecessary();
        //从index处开始，到数组结尾处的数据，都要向后移动一位。也支持向末尾添加元素。因为末尾不需要移动元素，直接添加即可。
        for (int i = size ; i > index ; i--) {
            elements[i] = elements[i -1];
        }
        elements[index] = element;
        size++;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        //要删除的节点元素值。
        E oldElement = elements[index];
        // 从index + 1 处开始移动，到size() - 1处移动结束。
        for (int i = index + 1; i < size(); i++) {
            //将 index + i处的元素移动到 index 处
            elements[i - 1] = elements[i];
        }
        size --;
        //清空 index = size 处的元素地址，其引用的对象也会一并销毁。
        elements[size] = null;
        return oldElement;
    }

    @Override
    public int indexOf(E element) {
        if(element == null){
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    return i;
                }
            }
            return ELEMENT_NOT_FOUND;
        }
        for (int i = 0; i < size; i++) {
            if (element.equals(elements[i])) {
                return i;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    @Override
    public String toString() {
        StringBuilder elementStr = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if(i == size -1){
                elementStr.append(elements[i]);
            }else {
                elementStr.append(elements[i] + ",");
            }
        }
        return "MyArrayListLearning{" +
                "size=" + size +
                ", elements=[" + elementStr +
                ']';
    }

    private void addCapacityIfNecessary(){
        if(size == elements.length){
            System.out.println("扩容前初始容量为：" + size);
            int newCapacity = size + (size >> 1);
            E[] newElements = (E[])new Object[newCapacity];
            System.out.println("扩容后初始容量为：" + newCapacity);
            for (int i = 0; i < elements.length; i++) {
                newElements[i] = elements[i];
            }
            elements = newElements;
        }
    }
}
