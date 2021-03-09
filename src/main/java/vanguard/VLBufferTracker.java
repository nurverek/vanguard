package vanguard;

public class VLBufferTracker<BUFFER extends VLBuffer<?, ?>> extends VLBufferTrackerType{

    protected BUFFER buffer;

    public VLBufferTracker(BUFFER buffer, int offset, int count){
        super(offset, count);
        this.buffer = buffer;
    }

    public VLBufferTracker(){

    }

    public void buffer(BUFFER buffer){
        this.buffer = buffer;
    }

    public BUFFER buffer(){
        return buffer;
    }

    @Override
    public void stringify(StringBuilder src, Object hint){
        src.append("[");
        src.append(getClass().getSimpleName());
        src.append("] buffer[");
        src.append(buffer.getClass().getSimpleName());
        src.append("] offset[");
        src.append(offset);
        src.append("] count[");
        src.append(count);
        src.append("] content[ ");

        buffer.stringify(src, hint);

        src.append(" ]");
    }
}

