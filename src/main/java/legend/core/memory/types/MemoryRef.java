package legend.core.memory.types;

import legend.core.GameEngine;
import legend.core.memory.Value;

import java.util.function.Function;

public interface MemoryRef {
  long getAddress();

  default <T extends MemoryRef> T reinterpret(final Function<Value, T> constructor) {
    return this.reinterpret(1, constructor);
  }

  default <T extends MemoryRef> T reinterpret(final int size, final Function<Value, T> constructor) {
    return constructor.apply(GameEngine.MEMORY.ref(size, this.getAddress()));
  }
}
