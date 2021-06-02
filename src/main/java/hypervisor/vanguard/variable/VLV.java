package hypervisor.vanguard.variable;

import hypervisor.vanguard.utils.VLLog;

public class VLV implements VLVTypeVariable{

    private static final class VLVSTATIC extends VLV{

        private VLVSTATIC(int value){
            super(value);
        }

        @Override
        public VLV duplicate(long flags){
            return this;
        }
    };

    public static final VLV ZERO = new VLVSTATIC(0);
    public static final VLV ONE = new VLVSTATIC(1);
    public static final VLV NEGATIVE_ONE = new VLVSTATIC(-1);

    protected float value;

    public VLV(float value){
        this.value = value;
    }

    public VLV(VLV src, long flags){
        copy(src, flags);
    }

    protected VLV(){

    }

    @Override
    public void set(float s){
        value = s;
    }

    @Override
    public float get(){
        return value;
    }

    @Override
    public void changeRate(float s){

    }

    @Override
    public void initialize(int cycles){

    }

    @Override
    public void initialize(float changerate){

    }

    @Override
    public void initializeFixedDirection(int cycles){

    }

    @Override
    public void initializeFixedDirection(float changerate){

    }

    @Override
    public void activate(){

    }

    @Override
    public void deactivate(){

    }

    @Override
    public int next(){
        return 0;
    }

    @Override
    public void fastForward(int count){

    }

    @Override
    public void chain(int cycles, float to){

    }

    @Override
    public void reverse(){

    }

    @Override
    public void reset(){

    }

    @Override
    public void finish(){

    }

    @Override
    public float changeRate(){
        return 0F;
    }

    @Override
    public float length(){
        return 0F;
    }

    @Override
    public boolean active(){
        return false;
    }

    @Override
    public void copy(VLVTypeRunnable src, long flags){
        value = ((VLV)src).value;
    }

    @Override
    public VLV duplicate(long flags){
        return new VLV(this, flags);
    }

    @Override
    public void log(VLLog log, Object data){
        log.append("[");
        log.append(getClass().getSimpleName());
        log.append("] value[");
        log.append(get());
        log.append("]");
    }
}