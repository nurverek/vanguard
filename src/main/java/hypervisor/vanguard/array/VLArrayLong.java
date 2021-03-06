package hypervisor.vanguard.array;

import hypervisor.vanguard.utils.VLCopyable;
import hypervisor.vanguard.utils.VLLog;

import java.util.Arrays;

public class VLArrayLong extends VLArray<Long, long[]>{

    public VLArrayLong(long[] s) {
        super(s);
    }

    public VLArrayLong(int size) {
        super(new long[size]);
    }

    public VLArrayLong(VLArrayLong src, long flags){
        copy(src, flags);
    }

    protected VLArrayLong(){

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
    public void copy(VLArray<Long, long[]> src, long flags){
        if((flags & FLAG_REFERENCE) == FLAG_REFERENCE){
            array = src.array;

        }else if((flags & FLAG_DUPLICATE) == FLAG_DUPLICATE){
            array = src.array.clone();

        }else{
            VLCopyable.Helper.throwMissingDefaultFlags();
        }
    }

    @Override
    public VLArrayLong duplicate(long flags){
        return new VLArrayLong(this, flags);
    }

    @Override
    public void log(VLLog log, Object data){
        super.log(log, data);

        log.append(" content[");
        log.append(Arrays.toString(array));
        log.append("]");
    }
}