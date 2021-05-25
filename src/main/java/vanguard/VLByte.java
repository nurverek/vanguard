package vanguard;

public class VLByte implements VLPrimitive{

    protected byte field;

    public VLByte(byte v){
        field = v;
    }

    public VLByte(VLByte src, long flags){
        copy(src, flags);
    }

    protected VLByte(){

    }

    public void set(byte field){
        this.field = field;
    }

    public byte get(){
        return field;
    }

    @Override
    public void copy(VLPrimitive src, long flags){
        field = ((VLByte)src).field;
    }

    @Override
    public VLByte duplicate(long flags){
        return new VLByte(this, flags);
    }

    @Override
    public void log(VLLog log, Object data){
        log.append("byte[");
        log.append(field);
        log.append("]");
    }
}