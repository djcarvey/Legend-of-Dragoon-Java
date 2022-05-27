package legend.game.types;

import legend.core.memory.Value;
import legend.core.memory.types.BiFunctionRef;
import legend.core.memory.types.ByteRef;
import legend.core.memory.types.MemoryRef;
import legend.core.memory.types.Pointer;
import legend.core.memory.types.UnsignedByteRef;
import legend.core.memory.types.UnsignedShortRef;

public class BttlScriptData6cSubBase implements MemoryRef {
  private final Value ref;

  public final Pointer<BttlScriptData6cSubBase> _00;
  public final UnsignedByteRef size_04;
  public final ByteRef _05;
  public final UnsignedShortRef _06;
  public final Pointer<BiFunctionRef<BttlScriptData6c, ? extends BttlScriptData6cSubBase, Long>> _08;

  public BttlScriptData6cSubBase(final Value ref) {
    this.ref = ref;

    this._00 = ref.offset(4, 0x00L).cast(Pointer.deferred(4, BttlScriptData6cSubBase::new));
    this.size_04 = ref.offset(1, 0x04L).cast(UnsignedByteRef::new);
    this._05 = ref.offset(1, 0x05L).cast(ByteRef::new);
    this._06 = ref.offset(2, 0x06L).cast(UnsignedShortRef::new);
    this._08 = ref.offset(4, 0x08L).cast(Pointer.deferred(4, BiFunctionRef::new));
  }

  @Override
  public long getAddress() {
    return this.ref.getAddress();
  }
}
