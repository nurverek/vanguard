package vanguard;

public class VLInt implements VLPrimitive{

    private int field;

    public VLInt(int v){
        field = v;
    }

    public VLInt(VLInt src, int depth){
        copy(src, depth);
    }

    public VLInt(){

    }

    public void set(int field){
        this.field = field;
    }

    public int get(){
        return field;
    }

    @Override
    public void copy(VLPrimitive src, int depth){
        field = ((VLInt)src).field;
    }

    @Override
    public VLInt duplicate(int depth){
        return new VLInt(this, depth);
    }

    @Override
    public void stringify(StringBuilder src, Object hint){
        src.append("int[");
        src.append(field);
        src.append("]");
    }
}
