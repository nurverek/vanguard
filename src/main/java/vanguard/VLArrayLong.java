package vanguard;

import java.util.Arrays;

public class VLArrayLong extends VLArray<Long, long[]>{

    public VLArrayLong(long[] s) {
        super(s);
    }

    public VLArrayLong(int size) {
        super(new long[size]);
    }

    public VLArrayLong(VLArrayLong src, int depth){
        copy(src, depth);
    }

    @Override
    public void set(int index, Long element) {
        array[index] = element;
    }

    @Override
    public Long get(int index) {
        return array[index];
    }

    @Override
    public void resize(int size) {
        array = VLArrayUtils.resize(array, size);
    }

    @Override
    public int size() {
        return array.length;
    }

    @Override
    public void copy(VLArray<Long, long[]> src, int depth){
        if(depth == DEPTH_MIN){
            this.array = src.array;

        }else if(depth == DEPTH_MAX){
            array = src.array.clone();

        }else{
            throw new RuntimeException("Invalid depth : " + depth);
        }
    }

    @Override
    public VLArrayLong duplicate(int depth){
        return new VLArrayLong(this, depth);
    }

    @Override
    public void stringify(StringBuilder src, Object hint){
        super.stringify(src, hint);

        src.append(" content[");
        src.append(Arrays.toString(array));
        src.append("]");
    }
}