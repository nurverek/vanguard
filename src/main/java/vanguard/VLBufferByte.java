package vanguard;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public abstract class VLBufferByte extends VLBuffer<Byte, ByteBuffer>{

    public VLBufferByte(VLBufferByte src, long flags){
        copy(src, flags);
    }

    public VLBufferByte(){

    }

    @Override
    public void initialize(ByteBuffer buffer){
        this.buffer = buffer;
        buffer.position(0);
    }

    @Override
    public void put(byte data){
        buffer.put(data);
    }

    @Override
    public void put(VLVTypeVariable data){
        buffer.put((byte)data.get());
    }

    @Override
    public void put(VLListType<VLVTypeVariable> data, int offset, int count){
        int limit = offset + count;

        for(int i = offset; i < limit; i++){
            buffer.put((byte)data.get(i).get());
        }
    }

    @Override
    public void put(byte[] data, int offset, int count){
        buffer.put(data, offset, count);
    }

    @Override
    public Byte read(int index){
        return buffer.get(index);
    }

    @Override
    public void read(byte[] data, int offset, int count){
        buffer.get(data, offset, count);
    }

    @Override
    public void remove(int offset, int size){
        ByteBuffer b = buffer;
        initialize(buffer.capacity() - size, buffer.order());
        int cap = b.capacity();

        for(int i = 0; i < offset; i++){
            buffer.put(b.get(i));
        }
        for(int i = offset + size; i < cap; i++){
            buffer.put(b.get(i));
        }
    }

    @Override
    public void removeInterleaved(int offset, int unitsize, int stride, int size){
        ByteBuffer b = buffer;
        initialize(buffer.capacity() - size, buffer.order());

        int max = offset + ((size / unitsize) * stride);
        int chunksize = stride - unitsize;

        for(int i = 0; i < offset; i++){
            buffer.put(b.get(i));
        }
        for(int i = offset + unitsize; i < max; i += stride){
            for(int i2 = 0; i2 < chunksize; i2++){
                buffer.put(b.get(i + i2));
            }
        }
        for(int i = max; i < b.capacity(); i++){
            buffer.put(b.get(i));
        }
    }

    @Override
    public void resize(int size){
        ByteBuffer b = buffer;
        initialize(size, buffer.order());
        b.position(0);

        if(b.hasArray()){
            if(b.capacity() <= buffer.capacity()){
                buffer.put(b.array());

            }else{
                buffer.put(b.array(), 0, buffer.capacity());
            }

        }else{
            byte[] data;

            if(b.capacity() <= buffer.capacity()){
                data = new byte[b.capacity()];

            }else{
                data = new byte[buffer.capacity()];
            }

            b.get(data);
            buffer.put(data);
        }

        buffer.position(0);
    }

    @Override
    public int getTypeBytes(){
        return 1;
    }

    @Override
    public int sizeBytes(){
        return buffer.capacity() * getTypeBytes();
    }

    @Override
    public void copy(VLBuffer<Byte, ByteBuffer> src, long flags){
        ByteBuffer target = src.buffer;

        if((flags & FLAG_REFERENCE) == FLAG_REFERENCE){
            initialize(target);

        }else if((flags & FLAG_DUPLICATE) == FLAG_DUPLICATE){
            initialize(target.capacity(), target.order());

            if(target.hasArray()){
                buffer.put(target.array());

            }else{
                int size = target.capacity();

                for(int i = 0; i < size; i++){
                    buffer.put(target.get(i));
                }
            }

            buffer.position(0);

        }else if((flags & FLAG_CUSTOM) == FLAG_CUSTOM){
            Helper.throwCustomCopyNotSupported(flags);

        }else{
            Helper.throwMissingBaseFlags();
        }

        preInitCapacity = src.preInitCapacity;
    }

    public static class Normal extends VLBufferByte{

        public Normal(Normal src, long flags){
            super(src, flags);
        }

        public Normal(){

        }

        @Override
        public ByteBuffer initialize(int capacity, ByteOrder order){
            buffer = ByteBuffer.allocate(capacity);
            buffer.order(order);
            buffer.position(0);

            return null;
        }

        @Override
        public Normal duplicate(long flags){
            return new Normal(this, flags);
        }
    }

    public static class Direct extends VLBufferByte{

        public Direct(Direct src, long flags){
            super(src, flags);
        }

        public Direct(){

        }

        @Override
        public ByteBuffer initialize(int capacity, ByteOrder order){
            buffer = ByteBuffer.allocateDirect(capacity * getTypeBytes());
            buffer.order(order);
            buffer.position(0);

            return buffer;
        }

        @Override
        public Direct duplicate(long flags){
            return new Direct(this, flags);
        }
    }
}

