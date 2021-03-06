package hypervisor.vanguard.sync;

import hypervisor.vanguard.utils.VLCopyable;
import hypervisor.vanguard.list.VLListType;

public class VLSyncParallel<SOURCE> extends VLSyncMap<SOURCE, VLListType<VLSyncType<SOURCE>>>{

    public static final long FLAG_resizerREFERENCE_ENTRIES = 0x1L;
    public static final long FLAG_resizerDUPLICATE_ENTRIES = 0x2L;

    public VLSyncParallel(int capacity, int resizer){
        super(new VLListType<>(capacity, resizer));
    }

    public VLSyncParallel(VLSyncType<SOURCE>[] array){
        super(new VLListType<>(array, 0));
    }

    public VLSyncParallel(VLSyncParallel<SOURCE> src, long flags){
        copy(src, flags);
    }

    protected VLSyncParallel(){

    }

    @Override
    public void sync(SOURCE source){
        int size = target.size();

        for(int i = 0; i < size; i++){
            target.get(i).sync(source);
        }
    }

    @Override
    public void copy(VLSyncType<SOURCE> src, long flags){
        VLSyncParallel<SOURCE> syncer = (VLSyncParallel<SOURCE>)src;

        if((flags & VLCopyable.FLAG_REFERENCE) == VLCopyable.FLAG_REFERENCE){
            target = syncer.target;

        }else if((flags & VLCopyable.FLAG_DUPLICATE) == VLCopyable.FLAG_DUPLICATE){
            target = syncer.target.duplicate(VLListType.FLAG_DUPLICATE);

        }else if((flags & VLCopyable.FLAG_CUSTOM) == VLCopyable.FLAG_CUSTOM){
            if((flags & FLAG_resizerREFERENCE_ENTRIES) == FLAG_resizerREFERENCE_ENTRIES){
                target = syncer.target.duplicate(VLCopyable.FLAG_CUSTOM | VLListType.FLAG_DUPLICATE_ARRAY_BUT_REFERENCE_ELEMENTS);

            }else if((flags & FLAG_resizerDUPLICATE_ENTRIES) == FLAG_resizerDUPLICATE_ENTRIES){
                target = syncer.target.duplicate(VLCopyable.FLAG_CUSTOM | VLListType.FLAG_DUPLICATE_ARRAY_FULLY);

            }else{
                VLCopyable.Helper.throwMissingSubFlags("FLAG_CUSTOM", "FLAG_resizerREFERENCE_ENTRIES", "FLAG_resizerDUPLICATE_ENTRIES");
            }

        }else{
            VLCopyable.Helper.throwMissingAllFlags();
        }
    }

    @Override
    public VLSyncParallel<SOURCE> duplicate(long flags){
        return new VLSyncParallel<>(this, flags);
    }
}
