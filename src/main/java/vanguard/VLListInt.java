package vanguard;

import java.util.Arrays;

public final class VLListInt extends VLList<int[]>{

    public VLListInt(int initialsize, int resizercount){
        super(resizercount, 0);
        array = new int[initialsize];
    }

    public VLListInt(int[] data, int resizercount){
        super(resizercount, data.length);
        array = data;
    }

    public VLListInt(VLListInt src, int depth){
        copy(src, depth);
    }

    public void add(int item){
        if(currentsize >= array.length){
            resize(array.length + resizercount);
        }

        array[currentsize++] = item;
    }

    public void add(int[] items){
        int target = currentsize + items.length;

        if(target >= array.length){
            resize(target + resizercount);
        }

        for(int i = 0; i < items.length; i++){
            array[currentsize++] = items[i];
        }
    }

    public void add(VLListInt items){
        int target = currentsize + items.size();

        if(target >= array.length){
            resize(target + resizercount);
        }

        for(int i = 0; i < items.size(); i++){
            array[currentsize++] = items.get(i);
        }
    }

    public void add(int index, int item){
        if(currentsize >= array.length){
            resize(array.length + resizercount);
        }

        VLArrayUtils.addInPlace(index, currentsize, array, item);
        currentsize++;
    }

    public void set(int index, int item){
        checkIndex(index, 1);
        array[index] = item;
    }

    public int get(int index){
        checkIndex(index, 1);
        return array[index];
    }

    public int indexOf(int item){
        int size = size();

        for(int i = 0; i < size; i++){
            if(array[i] == item){
                return i;
            }
        }

        return -1;
    }

    public void remove(Integer item){
        int index = VLArrayUtils.indexOf(array, item);

        if(index != -1){
            remove(index);
        }
    }

    @Override
    public int realSize(){
        return array.length;
    }

    @Override
    public void reverse(){
        int cap = array.length - 1;

        for(int i = 0, i2 = cap; i < i2; i++, i2--){
            array[i] = array[i2];
        }
    }

    @Override
    public void resize(int size){
        if(currentsize > size){
            currentsize = size;
        }

        int[] newarray = new int[size];
        System.arraycopy(array, 0, newarray, 0, currentsize);
        array = newarray;
    }

    @Override
    public void clear(){
        array = new int[resizercount];
        currentsize = 0;
    }

    @Override
    public void clear(int capacity){
        array = new int[capacity];
        currentsize = 0;
    }

    @Override
    public void nullify(){
        for(int i = 0; i < currentsize; i++){
            array[i] = 0;
        }
    }

    @Override
    public void nullify(int index, int count){
        for(; index < count; index++){
            array[index] = 0;
        }
    }

    @Override
    public VLListInt duplicate(int depth){
        return new VLListInt(this, depth);
    }

    @Override
    public void stringify(StringBuilder src, Object hint){
        super.stringify(src, hint);

        src.append(" content[");
        src.append(Arrays.toString(array));
        src.append("]");
    }
}