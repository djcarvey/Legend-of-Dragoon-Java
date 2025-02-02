package legend.game.combat.types;

import legend.core.memory.Value;
import legend.core.memory.types.ShortRef;
import legend.core.memory.types.UnsignedByteRef;
import legend.core.memory.types.UnsignedShortRef;

public class GuardEffect06 extends BttlScriptData6cSubBase1 {
  public final UnsignedByteRef _00;
  public final UnsignedShortRef _02;
  public final ShortRef _04;

  public GuardEffect06(final Value ref) {
    super(ref);

    this._00 = ref.offset(1, 0x00L).cast(UnsignedByteRef::new);
    this._02 = ref.offset(2, 0x02L).cast(UnsignedShortRef::new);
    this._04 = ref.offset(2, 0x04L).cast(ShortRef::new);
  }
}
