package hypervisor.vanguard.variable;

import hypervisor.vanguard.math.VLMath;

public class VLVRangedSimple extends VLV{

    private static final float[] CACHE = new float[2];

    protected float low, high;

    public VLVRangedSimple(float value, float low, float high){
        super(value);

        this.low = low;
        this.high = high;
    }

    public VLVRangedSimple(VLVRangedSimple src, long flags){
        copy(src, flags);
    }

    protected VLVRangedSimple(){

    }

    @Override
    public void set(float value){
        VLMath.wrapOverRange(CACHE, 0, value, low, high);
        this.value = CACHE[0];
    }

    @Override
    public void copy(VLVTypeRunnable src, long flags){
        super.copy(src, flags);

        VLVRangedSimple target = (VLVRangedSimple)src;
        low = target.low;
        high = target.high;
    }

    @Override
    public VLVRangedSimple duplicate(long flags) {
        return new VLVRangedSimple(this, flags);
    }

    public void push(float amount, int cycles){
        push(amount);
    }

    public void push(float amount){
        set(value + amount);
    }

    public float getLow(){
        return low;
    }

    public float getHigh(){
        return high;
    }
}
