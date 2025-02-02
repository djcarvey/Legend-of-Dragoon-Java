package legend.game;

import legend.core.gpu.Bpp;
import legend.core.gpu.RECT;
import legend.core.gpu.TimHeader;
import legend.core.gte.Tmd;
import legend.core.memory.Method;
import legend.core.memory.types.ArrayRef;
import legend.core.memory.types.FunctionRef;
import legend.core.memory.types.Pointer;
import legend.game.combat.Bttl_800c;
import legend.game.combat.Bttl_800d;
import legend.game.combat.Bttl_800e;
import legend.game.combat.Bttl_800f;
import legend.game.combat.SEffe;
import legend.game.types.ExtendedTmd;
import legend.game.types.Model124;
import legend.game.types.RunningScript;
import legend.game.types.TexPageY;
import legend.game.types.TmdAnimationFile;
import legend.game.types.Translucency;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static legend.core.GameEngine.GPU;
import static legend.core.GameEngine.MEMORY;
import static legend.game.Scus94491BpeSegment.FUN_80019500;
import static legend.game.Scus94491BpeSegment._1f8003fc;
import static legend.game.Scus94491BpeSegment.allocateHeap;
import static legend.game.Scus94491BpeSegment.extendedTmd_800103d0;
import static legend.game.Scus94491BpeSegment.heap_8011e210;
import static legend.game.Scus94491BpeSegment.loadMenuSounds;
import static legend.game.Scus94491BpeSegment.memcpy;
import static legend.game.Scus94491BpeSegment.orderingTableBits_1f8003c0;
import static legend.game.Scus94491BpeSegment.orderingTableSize_1f8003c8;
import static legend.game.Scus94491BpeSegment.ovalBlobTimHeader_80010548;
import static legend.game.Scus94491BpeSegment.setWidthAndFlags;
import static legend.game.Scus94491BpeSegment.tmdAnimFile_8001051c;
import static legend.game.Scus94491BpeSegment.zMax_1f8003cc;
import static legend.game.Scus94491BpeSegment.zShift_1f8003c4;
import static legend.game.Scus94491BpeSegment_8002.FUN_80021584;
import static legend.game.Scus94491BpeSegment_8002.FUN_8002246c;
import static legend.game.Scus94491BpeSegment_8002.initObjTable2;
import static legend.game.Scus94491BpeSegment_8002.loadBasicUiTexturesAndSomethingElse;
import static legend.game.Scus94491BpeSegment_8002.prepareObjTable2;
import static legend.game.Scus94491BpeSegment_8002.setCdMix;
import static legend.game.Scus94491BpeSegment_8003.FUN_8003c5e0;
import static legend.game.Scus94491BpeSegment_8003.GetTPage;
import static legend.game.Scus94491BpeSegment_8003.GsDefDispBuff;
import static legend.game.Scus94491BpeSegment_8003.GsInitCoordinate2;
import static legend.game.Scus94491BpeSegment_8003.GsInitGraph;
import static legend.game.Scus94491BpeSegment_8003.InitGeom;
import static legend.game.Scus94491BpeSegment_8003.LoadImage;
import static legend.game.Scus94491BpeSegment_8003.ResetGraph;
import static legend.game.Scus94491BpeSegment_8003.SetGraphDebug;
import static legend.game.Scus94491BpeSegment_8003.adjustTmdPointers;
import static legend.game.Scus94491BpeSegment_8003.parseTimHeader;
import static legend.game.Scus94491BpeSegment_8003.setProjectionPlaneDistance;
import static legend.game.Scus94491BpeSegment_8004._8004dd30;
import static legend.game.Scus94491BpeSegment_8004.enableAudioSource;
import static legend.game.Scus94491BpeSegment_8004.mainCallbackIndexOnceLoaded_8004dd24;
import static legend.game.Scus94491BpeSegment_8004.scriptSubFunctions_8004e29c;
import static legend.game.Scus94491BpeSegment_8004.setCdVolume;
import static legend.game.Scus94491BpeSegment_8007._8007a3a8;
import static legend.game.Scus94491BpeSegment_8007.vsyncMode_8007a3b8;
import static legend.game.Scus94491BpeSegment_800b._800babc0;
import static legend.game.Scus94491BpeSegment_800b._800bb104;
import static legend.game.Scus94491BpeSegment_800b._800bb228;
import static legend.game.Scus94491BpeSegment_800b._800bb348;
import static legend.game.Scus94491BpeSegment_800b._800bd7c0;
import static legend.game.Scus94491BpeSegment_800b._800bd9f8;
import static legend.game.Scus94491BpeSegment_800b._800bdb38;
import static legend.game.Scus94491BpeSegment_800b._800bdb90;
import static legend.game.Scus94491BpeSegment_800b._800bdc24;
import static legend.game.Scus94491BpeSegment_800b._800bf0cf;
import static legend.game.Scus94491BpeSegment_800b._800bf0d0;
import static legend.game.Scus94491BpeSegment_800b.afterFmvLoadingStage_800bf0ec;
import static legend.game.Scus94491BpeSegment_800b.array_800bb198;
import static legend.game.Scus94491BpeSegment_800b.drgnBinIndex_800bc058;
import static legend.game.Scus94491BpeSegment_800b.fmvIndex_800bf0dc;
import static legend.game.Scus94491BpeSegment_800b.fmvStage_800bf0d8;
import static legend.game.Scus94491BpeSegment_800b.model_800bda10;
import static legend.game.Scus94491BpeSegment_800b.pregameLoadingStage_800bb10c;
import static legend.game.Scus94491BpeSegment_800b.renderablePtr_800bdc5c;
import static legend.game.Scus94491BpeSegment_800b.scriptStatePtrArr_800bc1c0;
import static legend.game.Scus94491BpeSegment_800b.submapIndex_800bd808;
import static legend.game.Scus94491BpeSegment_800b.texPages_800bb110;
import static legend.game.Scus94491BpeSegment_800b.tickCount_800bb0fc;
import static legend.game.Scus94491BpeSegment_800b.unusedScriptState_800bc0c0;
import static legend.game.Scus94491BpeSegment_800c.scriptSubFunction_800ca734;
import static legend.game.Scus94491BpeSegment_800c.timHeader_800c6748;

public final class Scus94491BpeSegment_800e {
  private Scus94491BpeSegment_800e() { }

  private static final Logger LOGGER = LogManager.getFormatterLogger(Scus94491BpeSegment_800e.class);

  /**
   * <p>Copied to {@link Scus94491BpeSegment_8004#scriptSubFunctions_8004e29c} at index 736</p>
   *
   * <ol start="0">
   *   <li>{@link Bttl_800d#FUN_800d3090}</li>
   *   <li>{@link Bttl_800c#FUN_800cec8c}</li>
   *   <li>{@link Bttl_800c#FUN_800cee50}</li>
   *   <li>{@link Bttl_800c#FUN_800ceecc}</li>
   *   <li>{@link Bttl_800d#FUN_800d3098}</li>
   *   <li>{@link Bttl_800d#FUN_800d30a0}</li>
   *   <li>{@link Bttl_800d#FUN_800d30a8}</li>
   *   <li>{@link Bttl_800d#FUN_800d30b0}</li>
   *   <li>{@link Bttl_800c#FUN_800cef00}</li>
   *   <li>{@link Bttl_800c#FUN_800cf0b4}</li>
   *   <li>{@link SEffe#FUN_80102088}</li>
   *   <li>{@link SEffe#FUN_80102364}</li>
   *   <li>{@link Bttl_800d#FUN_800d30b8}</li>
   *   <li>{@link Bttl_800d#allocateProjectileHitEffect}</li>
   *   <li>{@link Bttl_800d#FUN_800d09b8}</li>
   *   <li>{@link Bttl_800d#allocateAdditionSparksEffect}</li>
   *   <li>{@link SEffe#FUN_80102608}</li>
   *   <li>{@link SEffe#allocateAdditionOverlaysEffect}</li>
   *   <li>{@link SEffe#FUN_801077bc}</li>
   *   <li>{@link SEffe#FUN_80108de8}</li>
   *   <li>{@link Bttl_800d#allocateAdditionStarburstEffect}</li>
   *   <li>{@link Bttl_800d#FUN_800d1cac}</li>
   *   <li>{@link Bttl_800d#FUN_800d1cf4}</li>
   *   <li>{@link SEffe#FUN_801078c0}</li>
   *   <li>{@link SEffe#FUN_80108df0}</li>
   *   <li>{@link Bttl_800d#allocateGuardEffect}</li>
   *   <li>{@link Bttl_800c#allocateWeaponTrailEffect}</li>
   *   <li>{@link Bttl_800d#allocatePotionEffect}</li>
   *   <li>{@link Bttl_800d#scriptAllocateAdditionScript}</li>
   *   <li>{@link Bttl_800c#FUN_800cfccc}</li>
   *   <li>{@link Bttl_800d#FUN_800d4338}</li>
   *   <li>{@link Bttl_800d#FUN_800d4580}</li>
   * </ol>
   */
  public static final ArrayRef<Pointer<FunctionRef<RunningScript, Long>>> scriptSubFunctions_800e6f64 = MEMORY.ref(4, 0x800e6f64L, ArrayRef.of(Pointer.classFor(FunctionRef.classFor(RunningScript.class, Long.class)), 32, 4, Pointer.deferred(4, FunctionRef::new)));
  /**
   * <p>Copied to {@link Scus94491BpeSegment_8004#scriptSubFunctions_8004e29c} at index 832</p>
   *
   * <ol start="0">
   *   <li>{@link Bttl_800d#FUN_800d46d4}</li>
   *   <li>{@link SEffe#FUN_80108df8}</li>
   *   <li>{@link SEffe#FUN_80102610}</li>
   *   <li>{@link Bttl_800c#scriptGetBobjDimension}</li>
   *   <li>{@link SEffe#FUN_80109158}</li>
   *   <li>{@link Bttl_800c#FUN_800ce9b0}</li>
   *   <li>{@link SEffe#FUN_801052dc}</li>
   *   <li>{@link SEffe#FUN_80105604}</li>
   *   <li>{@link SEffe#allocateDragoonAdditionScript}</li>
   *   <li>{@link SEffe#FUN_80105c38}</li>
   *   <li>{@link Bttl_800c#FUN_800c6968}</li>
   *   <li>{@link SEffe#allocateScreenDistortionEffect}</li>
   *   <li>{@link SEffe#FUN_801089cc}</li>
   *   <li>{@link SEffe#FUN_801023f4}</li>
   *   <li>{@link Bttl_800c#FUN_800cfec8}</li>
   *   <li>{@link SEffe#FUN_801023fc}</li>
   *   <li>{@link SEffe#FUN_8010246c}</li>
   *   <li>{@link Bttl_800c#scriptSetMtSeed}</li>
   *   <li>{@link SEffe#FUN_80109d30}</li>
   *   <li>{@link SEffe#FUN_8010a3fc}</li>
   *   <li>{@link Bttl_800d#allocateMonsterDeathEffect}</li>
   *   <li>{@link Bttl_800d#FUN_800d0124}</li>
   *   <li>{@link SEffe#FUN_801079a4}</li>
   * </ol>
   */
  public static final ArrayRef<Pointer<FunctionRef<RunningScript, Long>>> scriptSubFunctions_800e6fe4 = MEMORY.ref(4, 0x800e6fe4L, ArrayRef.of(Pointer.classFor(FunctionRef.classFor(RunningScript.class, Long.class)), 23, 4, Pointer.deferred(4, FunctionRef::new)));
  /**
   * <p>Copied to {@link Scus94491BpeSegment_8004#scriptSubFunctions_8004e29c} at index 896</p>
   *
   * <ol start="0">
   *   <li>{@link SEffe#FUN_8010a610}</li>
   *   <li>{@link SEffe#allocateDeathDimensionEffect}</li>
   * </ol>
   */
  public static final ArrayRef<Pointer<FunctionRef<RunningScript, Long>>> scriptSubFunctions_800e7040 = MEMORY.ref(4, 0x800e7040L, ArrayRef.of(Pointer.classFor(FunctionRef.classFor(RunningScript.class, Long.class)), 2, 4, Pointer.deferred(4, FunctionRef::new)));
  /**
   * <p>Copied to {@link Scus94491BpeSegment_8004#scriptSubFunctions_8004e29c} at index 32</p>
   *
   * <ol start="0">
   *   <li>{@link Bttl_800d#FUN_800dabcc}</li>
   *   <li>{@link Bttl_800d#FUN_800dac20}</li>
   *   <li>{@link Bttl_800d#FUN_800db034}</li>
   *   <li>{@link Bttl_800d#FUN_800db460}</li>
   *   <li>{@link Bttl_800d#FUN_800db574}</li>
   *   <li>{@link Bttl_800d#FUN_800db688}</li>
   *   <li>{@link Bttl_800d#FUN_800db79c}</li>
   *   <li>{@link Bttl_800d#FUN_800db8b0}</li>
   *   <li>{@link Bttl_800d#FUN_800db9e0}</li>
   *   <li>{@link Bttl_800d#FUN_800dbb10}</li>
   *   <li>{@link Bttl_800d#FUN_800dc2d8}</li>
   *   <li>{@link Bttl_800d#FUN_800dbb9c}</li>
   *   <li>{@link Bttl_800d#FUN_800dcbec}</li>
   *   <li>{@link Bttl_800d#FUN_800dcb84}</li>
   *   <li>{@link Bttl_800d#scriptSetViewportTwist}</li>
   *   <li>{@link Bttl_800d#FUN_800dbc80}</li>
   *   <li>{@link Bttl_800d#FUN_800dbcc8}</li>
   *   <li>{@link Bttl_800d#scriptGetProjectionPlaneDistance}</li>
   *   <li>{@link Bttl_800d#FUN_800d8dec}</li>
   * </ol>
   */
  public static final ArrayRef<Pointer<FunctionRef<RunningScript, Long>>> scriptSubFunctions_800e7048 = MEMORY.ref(4, 0x800e7048L, ArrayRef.of(Pointer.classFor(FunctionRef.classFor(RunningScript.class, Long.class)), 19, 4, Pointer.deferred(4, FunctionRef::new)));
  /**
   * <p>Copied to {@link Scus94491BpeSegment_8004#scriptSubFunctions_8004e29c} at index 224</p>
   *
   * <ol start="0">
   *   <li>{@link Scus94491BpeSegment#FUN_8001e640}</li>
   *   <li>{@link Scus94491BpeSegment#FUN_8001e918}</li>
   *   <li>{@link Scus94491BpeSegment#FUN_8001e920}</li>
   *   <li>{@link Scus94491BpeSegment#FUN_8001eb30}</li>
   *   <li>{@link Scus94491BpeSegment#FUN_8001eccc}</li>
   *   <li>{@link Scus94491BpeSegment#FUN_8001f070}</li>
   *   <li>{@link Scus94491BpeSegment#scriptLoadMusicPackage}</li>
   *   <li>{@link Scus94491BpeSegment#FUN_8001fe28}</li>
   *   <li>{@link Scus94491BpeSegment#FUN_8001ffdc}</li>
   *   <li>{@link Scus94491BpeSegment#FUN_8002013c}</li>
   *   <li>{@link Scus94491BpeSegment#FUN_80020230}</li>
   *   <li>{@link Scus94491BpeSegment#FUN_800202a4}</li>
   *   <li>{@link Scus94491BpeSegment#scriptPlaySound}</li>
   *   <li>{@link Scus94491BpeSegment#FUN_8001ab98}</li>
   *   <li>{@link Scus94491BpeSegment#scriptPlayBobjSound}</li>
   *   <li>{@link Scus94491BpeSegment#FUN_8001ac48}</li>
   *   <li>{@link Scus94491BpeSegment#FUN_8001ad5c}</li>
   *   <li>{@link Scus94491BpeSegment#FUN_8001adc8}</li>
   *   <li>{@link Scus94491BpeSegment#FUN_8001ae18}</li>
   *   <li>{@link Scus94491BpeSegment#FUN_8001ae68}</li>
   *   <li>{@link Scus94491BpeSegment#FUN_8001aec8}</li>
   *   <li>{@link Scus94491BpeSegment#FUN_8001af34}</li>
   *   <li>{@link Scus94491BpeSegment#FUN_8001afa4}</li>
   *   <li>{@link Scus94491BpeSegment#FUN_8001b014}</li>
   *   <li>{@link Scus94491BpeSegment#FUN_8001b094}</li>
   *   <li>{@link Scus94491BpeSegment#FUN_8001b134}</li>
   *   <li>{@link Scus94491BpeSegment#FUN_8001b13c}</li>
   *   <li>{@link Scus94491BpeSegment#FUN_8001b144}</li>
   *   <li>{@link Scus94491BpeSegment#scriptSetMainVolume}</li>
   *   <li>{@link Scus94491BpeSegment#FUN_8001b17c}</li>
   *   <li>{@link Scus94491BpeSegment#FUN_8001b208}</li>
   *   <li>{@link Scus94491BpeSegment#scriptSssqFadeIn}</li>
   * </ol>
   */
  public static final ArrayRef<Pointer<FunctionRef<RunningScript, Long>>> scriptSubFunctions_800e7094 = MEMORY.ref(4, 0x800e7094L, ArrayRef.of(Pointer.classFor(FunctionRef.classFor(RunningScript.class, Long.class)), 32, 4, Pointer.deferred(4, FunctionRef::new)));
  /**
   * <p>Copied to {@link Scus94491BpeSegment_8004#scriptSubFunctions_8004e29c} at index 704</p>
   *
   * <ol start="0">
   *   <li>{@link Scus94491BpeSegment#FUN_8001b2ac}</li>
   *   <li>{@link Scus94491BpeSegment#scriptSssqFadeOut}</li>
   *   <li>{@link Scus94491BpeSegment#FUN_8001b33c}</li>
   *   <li>{@link Scus94491BpeSegment#FUN_8001b3a0}</li>
   *   <li>{@link Scus94491BpeSegment#scriptGetSssqTempoScale}</li>
   *   <li>{@link Scus94491BpeSegment#scriptSetSssqTempoScale}</li>
   *   <li>{@link Scus94491BpeSegment#FUN_8001ffc0}</li>
   *   <li>{@link Scus94491BpeSegment#FUN_8001b1ec}</li>
   *   <li>{@link Scus94491BpeSegment#scriptPlayCombatantSound}</li>
   *   <li>{@link Scus94491BpeSegment#FUN_8001acd8}</li>
   *   <li>{@link Scus94491BpeSegment_8002#FUN_80020060}</li>
   *   <li>{@link Scus94491BpeSegment#FUN_8001f250}</li>
   *   <li>{@link Scus94491BpeSegment_8002#FUN_800203f0}</li>
   *   <li>{@link Scus94491BpeSegment#FUN_8001f674}</li>
   *   <li>{@link Scus94491BpeSegment#FUN_8001f560}</li>
   * </ol>
   */
  public static final ArrayRef<Pointer<FunctionRef<RunningScript, Long>>> scriptSubFunctions_800e7114 = MEMORY.ref(4, 0x800e7114L, ArrayRef.of(Pointer.classFor(FunctionRef.classFor(RunningScript.class, Long.class)), 15, 4, Pointer.deferred(4, FunctionRef::new)));
  /**
   * <p>Copied to {@link Scus94491BpeSegment_8004#scriptSubFunctions_8004e29c} at index 800</p>
   *
   * <ol start="0">
   *   <li>{@link SEffe#FUN_8010c378}</li>
   *   <li>{@link SEffe#FUN_8010d1dc}</li>
   *   <li>{@link SEffe#allocateGoldDragoonTransformEffect}</li>
   *   <li>{@link SEffe#FUN_8010e04c}</li>
   *   <li>{@link SEffe#FUN_8010edc8}</li>
   *   <li>{@link SEffe#FUN_8010e89c}</li>
   *   <li>{@link Scus94491BpeSegment#FUN_8001c5fc}</li>
   *   <li>{@link Scus94491BpeSegment#FUN_8001c604}</li>
   * </ol>
   */
  public static final ArrayRef<Pointer<FunctionRef<RunningScript, Long>>> scriptSubFunctions_800e7150 = MEMORY.ref(4, 0x800e7150L, ArrayRef.of(Pointer.classFor(FunctionRef.classFor(RunningScript.class, Long.class)), 8, 4, Pointer.deferred(4, FunctionRef::new)));
  /**
   * <p>Copied to {@link Scus94491BpeSegment_8004#scriptSubFunctions_8004e29c} at index 416</p>
   *
   * <ol start="0">
   *   <li>{@link Bttl_800e#FUN_800e6fb4}</li>
   *   <li>null</li>
   *   <li>null</li>
   *   <li>{@link Bttl_800e#scriptResetLights}</li>
   *   <li>{@link Bttl_800e#scriptSetLightDirection}</li>
   *   <li>{@link Bttl_800e#scriptGetLightDirection}</li>
   *   <li>{@link Bttl_800e#FUN_800e48a8}</li>
   *   <li>{@link Bttl_800e#FUN_800e48e8}</li>
   *   <li>{@link Bttl_800e#FUN_800e4964}</li>
   *   <li>{@link Bttl_800e#FUN_800e4abc}</li>
   *   <li>{@link Bttl_800e#FUN_800e4c10}</li>
   *   <li>{@link Bttl_800e#FUN_800e4c90}</li>
   *   <li>{@link Bttl_800e#FUN_800e4d2c}</li>
   *   <li>{@link Bttl_800e#scriptGetLightColour}</li>
   *   <li>{@link Bttl_800e#FUN_800e4dfc}</li>
   *   <li>{@link Bttl_800e#FUN_800e4e2c}</li>
   *   <li>{@link Bttl_800e#FUN_800e4e64}</li>
   *   <li>{@link Bttl_800e#FUN_800e4ea0}</li>
   *   <li>{@link Bttl_800e#FUN_800e4fa0}</li>
   *   <li>{@link Bttl_800e#FUN_800e50e8}</li>
   *   <li>{@link Bttl_800e#FUN_800e52f8}</li>
   *   <li>{@link Bttl_800e#FUN_800e540c}</li>
   *   <li>{@link Bttl_800e#FUN_800e54f8}</li>
   *   <li>{@link Bttl_800e#FUN_800e5528}</li>
   *   <li>{@link Bttl_800e#FUN_800e5560}</li>
   *   <li>{@link Bttl_800e#FUN_800e559c}</li>
   *   <li>{@link Bttl_800e#FUN_800e569c}</li>
   *   <li>{@link Bttl_800e#FUN_800e596c}</li>
   *   <li>{@link Bttl_800e#FUN_800e59d8}</li>
   *   <li>null</li>
   *   <li>null</li>
   *   <li>null</li>
   * </ol>
   */
  public static final ArrayRef<Pointer<FunctionRef<RunningScript, Long>>> scriptSubFunctions_800e7170 = MEMORY.ref(4, 0x800e7170L, ArrayRef.of(Pointer.classFor(FunctionRef.classFor(RunningScript.class, Long.class)), 32, 4, Pointer.deferred(4, FunctionRef::new)));
  /**
   * <p>Copied to {@link Scus94491BpeSegment_8004#scriptSubFunctions_8004e29c} at index 544</p>
   *
   * <ol start="0">
   *   <li>{@link SEffe#FUN_801115ec}</li>
   *   <li>{@link SEffe#FUN_80111ae4}</li>
   *   <li>{@link SEffe#FUN_80112704}</li>
   *   <li>{@link SEffe#FUN_80112770}</li>
   *   <li>{@link SEffe#FUN_80113964}</li>
   *   <li>{@link SEffe#FUN_801139d0}</li>
   *   <li>{@link SEffe#FUN_8011452c}</li>
   *   <li>{@link SEffe#FUN_80114598}</li>
   *   <li>{@link SEffe#FUN_80114e0c}</li>
   *   <li>{@link SEffe#FUN_80114e60}</li>
   *   <li>{@link SEffe#FUN_80111b60}</li>
   *   <li>{@link SEffe#FUN_8011554c}</li>
   *   <li>{@link SEffe#FUN_801155a0}</li>
   *   <li>{@link SEffe#FUN_80111be8}</li>
   *   <li>{@link SEffe#FUN_80111c2c}</li>
   *   <li>{@link SEffe#FUN_80112184}</li>
   *   <li>{@link SEffe#FUN_80112274}</li>
   *   <li>{@link SEffe#FUN_80112364}</li>
   *   <li>{@link SEffe#FUN_801155f8}</li>
   *   <li>{@link SEffe#FUN_80112900}</li>
   *   <li>{@link SEffe#FUN_8011299c}</li>
   *   <li>{@link SEffe#FUN_80115440}</li>
   *   <li>{@link SEffe#FUN_80111658}</li>
   *   <li>{@link SEffe#FUN_80112aa4}</li>
   *   <li>{@link SEffe#FUN_80112bf0}</li>
   *   <li>{@link SEffe#FUN_80112e00}</li>
   *   <li>{@link SEffe#FUN_8011306c}</li>
   *   <li>{@link SEffe#FUN_801132c8}</li>
   *   <li>{@link SEffe#FUN_80115600}</li>
   *   <li>{@link Bttl_800e#FUN_800e9f68}</li>
   *   <li>{@link SEffe#FUN_80118984}</li>
   *   <li>{@link SEffe#FUN_80113c6c}</li>
   * </ol>
   */
  public static final ArrayRef<Pointer<FunctionRef<RunningScript, Long>>> scriptSubFunctions_800e71f0 = MEMORY.ref(4, 0x800e71f0L, ArrayRef.of(Pointer.classFor(FunctionRef.classFor(RunningScript.class, Long.class)), 32, 4, Pointer.deferred(4, FunctionRef::new)));
  /**
   * <p>Copied to {@link Scus94491BpeSegment_8004#scriptSubFunctions_8004e29c} at index 576</p>
   *
   * <ol start="0">
   *   <li>{@link SEffe#FUN_80114094}</li>
   *   <li>{@link SEffe#FUN_801143f8}</li>
   *   <li>{@link SEffe#FUN_80115608}</li>
   *   <li>{@link SEffe#FUN_80114070}</li>
   *   <li>{@link SEffe#FUN_801147c8}</li>
   *   <li>{@link SEffe#FUN_80114920}</li>
   *   <li>{@link SEffe#FUN_80114b00}</li>
   *   <li>{@link SEffe#FUN_80114eb4}</li>
   *   <li>{@link SEffe#FUN_80114f34}</li>
   *   <li>{@link SEffe#FUN_80115014}</li>
   *   <li>{@link SEffe#FUN_80115058}</li>
   *   <li>{@link SEffe#FUN_80115168}</li>
   *   <li>{@link SEffe#FUN_801152b0}</li>
   *   <li>{@link SEffe#FUN_80115324}</li>
   *   <li>{@link SEffe#FUN_80115388}</li>
   *   <li>{@link SEffe#FUN_801153e4}</li>
   *   <li>{@link Bttl_800e#FUN_800e74ac}</li>
   *   <li>{@link SEffe#FUN_80112398}</li>
   *   <li>{@link Bttl_800e#FUN_800eb518}</li>
   *   <li>{@link SEffe#FUN_8011549c}</li>
   *   <li>{@link SEffe#FUN_801122ec}</li>
   *   <li>{@link SEffe#FUN_801121fc}</li>
   *   <li>{@link SEffe#FUN_80111cc4}</li>
   *   <li>{@link SEffe#FUN_80111ed4}</li>
   *   <li>{@link Bttl_800e#FUN_800e93e0}</li>
   *   <li>{@link Bttl_800e#allocateAttackHitFlashEffect}</li>
   *   <li>{@link Bttl_800e#FUN_800e9854}</li>
   *   <li>{@link Temp#FUN_800ca648}</li>
   *   <li>null</li>
   *   <li>{@link SEffe#FUN_80117eb0}</li>
   *   <li>{@link SEffe#allocateGuardHealEffect}</li>
   *   <li>{@link Bttl_800e#FUN_800e99bc}</li>
   * </ol>
   */
  public static final ArrayRef<Pointer<FunctionRef<RunningScript, Long>>> scriptSubFunctions_800e7270 = MEMORY.ref(4, 0x800e7270L, ArrayRef.of(Pointer.classFor(FunctionRef.classFor(RunningScript.class, Long.class)), 32, 4, Pointer.deferred(4, FunctionRef::new)));
  /**
   * <p>Copied to {@link Scus94491BpeSegment_8004#scriptSubFunctions_8004e29c} at index 608</p>
   *
   * <ol start="0">
   *   <li>{@link SEffe#FUN_801181a8}</li>
   *   <li>null</li>
   *   <li>{@link Bttl_800e#FUN_800ea200}</li>
   *   <li>{@link SEffe#FUN_801156f8}</li>
   *   <li>{@link Bttl_800e#FUN_800ea13c}</li>
   *   <li>{@link Bttl_800e#FUN_800ea19c}</li>
   *   <li>{@link Bttl_800e#FUN_800eb84c}</li>
   *   <li>{@link Bttl_800e#FUN_800eb188}</li>
   *   <li>{@link Bttl_800e#FUN_800eb01c}</li>
   *   <li>{@link Temp#FUN_800caae4}</li>
   *   <li>{@link SEffe#scriptLoadSameScriptAndJump}</li>
   *   <li>{@link SEffe#FUN_80118df4}</li>
   *   <li>{@link SEffe#FUN_80111a58}</li>
   *   <li>{@link Bttl_800e#FUN_800ea384}</li>
   *   <li>{@link SEffe#FUN_80119484}</li>
   *   <li>{@link Bttl_800e#FUN_800e73ac}</li>
   *   <li>{@link Bttl_800e#FUN_800e6db4}</li>
   *   <li>{@link Bttl_800e#FUN_800e7490}</li>
   *   <li>{@link SEffe#scriptGetEffectZ}</li>
   *   <li>{@link SEffe#scriptSetEffectZ}</li>
   *   <li>{@link SEffe#FUN_801184e4}</li>
   *   <li>{@link SEffe#FUN_801157d0}</li>
   *   <li>{@link SEffe#FUN_801127e0}</li>
   *   <li>{@link SEffe#FUN_801181f0}</li>
   *   <li>{@link SEffe#FUN_801114b8}</li>
   *   <li>null</li>
   *   <li>{@link SEffe#FUN_80115ab0}</li>
   *   <li>{@link SEffe#FUN_80115a94}</li>
   *   <li>{@link SEffe#scriptPlayXaAudio}</li>
   *   <li>{@link Bttl_800e#FUN_800e7314}</li>
   *   <li>{@link Bttl_800e#FUN_800e71e4}</li>
   *   <li>{@link Bttl_800e#FUN_800e727c}</li>
   * </ol>
   */
  public static final ArrayRef<Pointer<FunctionRef<RunningScript, Long>>> scriptSubFunctions_800e72f0 = MEMORY.ref(4, 0x800e72f0L, ArrayRef.of(Pointer.classFor(FunctionRef.classFor(RunningScript.class, Long.class)), 32, 4, Pointer.deferred(4, FunctionRef::new)));
  /**
   * <p>Copied to {@link Scus94491BpeSegment_8004#scriptSubFunctions_8004e29c} at index 640</p>
   *
   * <ol start="0">
   *   <li>{@link SEffe#FUN_8011357c}</li>
   *   <li>{@link SEffe#FUN_80115a28}</li>
   *   <li>{@link SEffe#FUN_8011287c}</li>
   *   <li>{@link Bttl_800e#FUN_800e9798}</li>
   *   <li>{@link Bttl_800e#FUN_800ea2a0}</li>
   *   <li>{@link Bttl_800e#FUN_800ea30c}</li>
   *   <li>{@link SEffe#FUN_801188ec}</li>
   *   <li>{@link SEffe#FUN_80115ad8}</li>
   *   <li>{@link SEffe#FUN_80115ea4}</li>
   *   <li>{@link SEffe#FUN_80115ed4}</li>
   *   <li>{@link SEffe#FUN_801154f4}</li>
   *   <li>{@link SEffe#FUN_80116160}</li>
   *   <li>null</li>
   *   <li>null</li>
   *   <li>null</li>
   *   <li>null</li>
   *   <li>null</li>
   *   <li>null</li>
   *   <li>null</li>
   *   <li>null</li>
   *   <li>null</li>
   *   <li>null</li>
   *   <li>null</li>
   *   <li>null</li>
   *   <li>null</li>
   *   <li>null</li>
   *   <li>null</li>
   *   <li>null</li>
   *   <li>null</li>
   *   <li>null</li>
   *   <li>null</li>
   *   <li>null</li>
   * </ol>
   */
  public static final ArrayRef<Pointer<FunctionRef<RunningScript, Long>>> scriptSubFunctions_800e7370 = MEMORY.ref(4, 0x800e7370L, ArrayRef.of(Pointer.classFor(FunctionRef.classFor(RunningScript.class, Long.class)), 32, 4, Pointer.deferred(4, FunctionRef::new)));
  /**
   * <p>Copied to {@link Scus94491BpeSegment_8004#scriptSubFunctions_8004e29c} at index 96</p>
   *
   * <ol start="0">
   *   <li>{@link SMap#FUN_800df168}</li>
   *   <li>{@link SMap#FUN_800df198}</li>
   *   <li>{@link SMap#FUN_800df1c8}</li>
   *   <li>{@link SMap#FUN_800df1f8}</li>
   *   <li>{@link SMap#FUN_800df228}</li>
   *   <li>{@link SMap#FUN_800df258}</li>
   *   <li>{@link SMap#FUN_800df2b8}</li>
   *   <li>{@link SMap#FUN_800df314}</li>
   *   <li>{@link SMap#FUN_800df374}</li>
   *   <li>{@link SMap#FUN_800df3d0}</li>
   *   <li>{@link SMap#FUN_800df410}</li>
   *   <li>{@link SMap#FUN_800df440}</li>
   *   <li>{@link SMap#FUN_800df488}</li>
   *   <li>{@link SMap#FUN_800df4d0}</li>
   *   <li>{@link SMap#FUN_800df500}</li>
   *   <li>{@link SMap#FUN_800df530}</li>
   *   <li>{@link SMap#FUN_800df560}</li>
   *   <li>{@link SMap#FUN_800df5c0}</li>
   *   <li>{@link SMap#FUN_800df590}</li>
   *   <li>{@link SMap#FUN_800df5f0}</li>
   *   <li>{@link SMap#FUN_800df620}</li>
   *   <li>{@link SMap#FUN_800df650}</li>
   *   <li>{@link SMap#FUN_800df680}</li>
   *   <li>{@link SMap#FUN_800df6a4}</li>
   *   <li>{@link SMap#scriptRotateWobj}</li>
   *   <li>{@link SMap#scriptRotateWobjAbsolute}</li>
   *   <li>{@link SMap#FUN_800df904}</li>
   *   <li>{@link SMap#FUN_800de1d0}</li>
   *   <li>{@link SMap#scriptFacePlayer}</li>
   *   <li>{@link SMap#FUN_800df9a8}</li>
   *   <li>{@link SMap#FUN_800dfb28}</li>
   *   <li>{@link SMap#FUN_800dfb44}</li>
   * </ol>
   */
  public static final ArrayRef<Pointer<FunctionRef<RunningScript, Long>>> scriptSubFunctions_800e73f0 = MEMORY.ref(4, 0x800e73f0L, ArrayRef.of(Pointer.classFor(FunctionRef.classFor(RunningScript.class, Long.class)), 32, 4, Pointer.deferred(4, FunctionRef::new)));
  /**
   * <p>Copied to {@link Scus94491BpeSegment_8004#scriptSubFunctions_8004e29c} at index 288</p>
   *
   * <ol start="0">
   *   <li>{@link SMap#FUN_800dfb74}</li>
   *   <li>{@link SMap#FUN_800dfba4}</li>
   *   <li>{@link SMap#FUN_800dfbd4}</li>
   *   <li>{@link SMap#scriptScaleXyz}</li>
   *   <li>{@link SMap#scriptScaleUniform}</li>
   *   <li>{@link SMap#FUN_800dfca0}</li>
   *   <li>{@link SMap#FUN_800dfcd8}</li>
   *   <li>{@link SMap#FUN_800dfd10}</li>
   *   <li>{@link SMap#FUN_800de334}</li>
   *   <li>{@link SMap#FUN_800de4b4}</li>
   *   <li>{@link SMap#scriptShowAlertIndicator}</li>
   *   <li>{@link SMap#scriptHideAlertIndicator}</li>
   *   <li>{@link SMap#FUN_800dfd48}</li>
   *   <li>{@link SMap#FUN_800e05c8}</li>
   *   <li>{@link SMap#FUN_800e05f0}</li>
   *   <li>{@link SMap#FUN_800e0614}</li>
   *   <li>{@link SMap#FUN_800e0684}</li>
   *   <li>{@link SMap#FUN_800e06c4}</li>
   *   <li>{@link SMap#FUN_800e0710}</li>
   *   <li>{@link SMap#FUN_800e0894}</li>
   *   <li>{@link SMap#FUN_800e08f4}</li>
   *   <li>{@link SMap#scriptSetAmbientColour}</li>
   *   <li>{@link SMap#scriptResetAmbientColour}</li>
   *   <li>{@link SMap#FUN_800e09e0}</li>
   *   <li>{@link SMap#FUN_800e0a14}</li>
   *   <li>{@link SMap#FUN_800e0a48}</li>
   *   <li>{@link SMap#FUN_800e0a94}</li>
   *   <li>{@link SMap#scriptCheckPlayerCollision}</li>
   *   <li>{@link SMap#FUN_800e0af4}</li>
   *   <li>{@link SMap#FUN_800e0b34}</li>
   *   <li>{@link SMap#FUN_800e0ba0}</li>
   *   <li>{@link SMap#FUN_800e0cb8}</li>
   * </ol>
   */
  public static final ArrayRef<Pointer<FunctionRef<RunningScript, Long>>> scriptSubFunctions_800e7470 = MEMORY.ref(4, 0x800e7470L, ArrayRef.of(Pointer.classFor(FunctionRef.classFor(RunningScript.class, Long.class)), 32, 4, Pointer.deferred(4, FunctionRef::new)));
  /**
   * <p>Copied to {@link Scus94491BpeSegment_8004#scriptSubFunctions_8004e29c} at index 672</p>
   *
   * <ol start="0">
   *   <li>{@link SMap#FUN_800dfe0c}</li>
   *   <li>{@link SMap#FUN_800dfec8}</li>
   *   <li>{@link SMap#FUN_800dff68}</li>
   *   <li>{@link SMap#FUN_800dffa4}</li>
   *   <li>{@link SMap#FUN_800dffdc}</li>
   *   <li>{@link SMap#scriptFacePoint}</li>
   *   <li>{@link SMap#FUN_800e0094}</li>
   *   <li>{@link SMap#FUN_800de668}</li>
   *   <li>{@link SMap#FUN_800de944}</li>
   *   <li>{@link SMap#FUN_800e00cc}</li>
   *   <li>{@link SMap#FUN_800e0148}</li>
   *   <li>{@link SMap#FUN_800e01bc}</li>
   *   <li>{@link SMap#FUN_800e0244}</li>
   *   <li>{@link SMap#FUN_800e0204}</li>
   *   <li>{@link SMap#FUN_800e0284}</li>
   *   <li>{@link SMap#FUN_800e02c0}</li>
   *   <li>{@link SMap#FUN_800e02fc}</li>
   *   <li>{@link SMap#FUN_800deba0}</li>
   *   <li>{@link SMap#scriptGetWobjNobj}</li>
   *   <li>{@link SMap#FUN_800e03e4}</li>
   *   <li>{@link SMap#FUN_800e0448}</li>
   *   <li>{@link SMap#scriptFaceCamera}</li>
   *   <li>{@link SMap#FUN_800e0520}</li>
   *   <li>{@link SMap#FUN_800e057c}</li>
   *   <li>{@link SMap#FUN_800e074c}</li>
   *   <li>{@link SMap#FUN_800e07f0}</li>
   *   <li>{@link SMap#FUN_800e0184}</li>
   *   <li>{@link SMap#FUN_800e0c40}</li>
   *   <li>{@link SMap#FUN_800e0c80}</li>
   *   <li>{@link SMap#scriptLoadChapterTitleCard}</li>
   *   <li>{@link SMap#scriptIsChapterTitleCardLoaded}</li>
   *   <li>{@link SMap#FUN_800e0c9c}</li>
   * </ol>
   */
  public static final ArrayRef<Pointer<FunctionRef<RunningScript, Long>>> scriptSubFunctions_800e74f0 = MEMORY.ref(4, 0x800e74f0L, ArrayRef.of(Pointer.classFor(FunctionRef.classFor(RunningScript.class, Long.class)), 32, 4, Pointer.deferred(4, FunctionRef::new)));
  /**
   * <p>Copied to {@link Scus94491BpeSegment_8004#scriptSubFunctions_8004e29c} at index 512</p>
   *
   * <ol start="0">
   *   <li>{@link Bttl_800e#scriptSetBobjZOffset}</li>
   *   <li>{@link Bttl_800e#scriptSetBobjScaleUniform}</li>
   *   <li>{@link Bttl_800e#scriptSetBobjScale}</li>
   *   <li>{@link Bttl_800e#FUN_800ee384}</li>
   *   <li>{@link Bttl_800e#FUN_800ee468}</li>
   *   <li>{@link Bttl_800e#FUN_800ee49c}</li>
   *   <li>{@link Bttl_800e#FUN_800ee4e8}</li>
   *   <li>{@link Bttl_800e#scriptApplyScreenDarkening}</li>
   *   <li>{@link Bttl_800e#FUN_800ee384}</li>
   *   <li>{@link Bttl_800e#scriptGetStageNobj}</li>
   *   <li>{@link Bttl_800e#FUN_800ee594}</li>
   *   <li>{@link Bttl_800e#FUN_800ee5c0}</li>
   *   <li>{@link Bttl_800e#FUN_800ee3c0}</li>
   *   <li>{@link Bttl_800e#FUN_800ee408}</li>
   *   <li>{@link Bttl_800e#scriptSetStageZ}</li>
   * </ol>
   */
  public static final ArrayRef<Pointer<FunctionRef<RunningScript, Long>>> scriptSubFunctions_800e7570 = MEMORY.ref(4, 0x800e7570L, ArrayRef.of(Pointer.classFor(FunctionRef.classFor(RunningScript.class, Long.class)), 15, 4, Pointer.deferred(4, FunctionRef::new)));
  /**
   * <p>Copied to {@link Scus94491BpeSegment_8004#scriptSubFunctions_8004e29c} at index 864</p>
   *
   * <ol start="0">
   *   <li>{@link Scus94491BpeSegment_8002#scriptGiveChestContents}</li>
   *   <li>{@link Scus94491BpeSegment_8002#scriptTakeItem}</li>
   *   <li>{@link Scus94491BpeSegment_8002#scriptGiveGold}</li>
   * </ol>
   */
  public static final ArrayRef<Pointer<FunctionRef<RunningScript, Long>>> scriptSubFunctions_800e75ac = MEMORY.ref(4, 0x800e75acL, ArrayRef.of(Pointer.classFor(FunctionRef.classFor(RunningScript.class, Long.class)), 3, 4, Pointer.deferred(4, FunctionRef::new)));
  /**
   * <p>Copied to {@link Scus94491BpeSegment_8004#scriptSubFunctions_8004e29c} at index 192</p>
   *
   * <ol start="0">
   *   <li>{@link Scus94491BpeSegment_8002#FUN_80029b68}</li>
   *   <li>{@link Scus94491BpeSegment_8002#FUN_80029bd4}</li>
   *   <li>{@link Scus94491BpeSegment_8002#FUN_80025158}</li>
   *   <li>{@link Scus94491BpeSegment_8002#FUN_80029c98}</li>
   *   <li>{@link Scus94491BpeSegment_8002#FUN_80029cf4}</li>
   *   <li>{@link Scus94491BpeSegment_8002#FUN_80029d34}</li>
   *   <li>{@link Scus94491BpeSegment_8002#scriptAddWobjTextbox}</li>
   *   <li>{@link Scus94491BpeSegment_8002#FUN_80029e8c}</li>
   *   <li>{@link Scus94491BpeSegment_8002#FUN_800254bc}</li>
   *   <li>{@link Scus94491BpeSegment_8002#FUN_80029d6c}</li>
   *   <li>{@link Scus94491BpeSegment_8002#FUN_80029e04}</li>
   *   <li>{@link Scus94491BpeSegment_8002#FUN_80029ecc}</li>
   *   <li>{@link Scus94491BpeSegment_8002#FUN_80028ff8}</li>
   *   <li>{@link Scus94491BpeSegment_8002#FUN_80029f48}</li>
   *   <li>{@link Scus94491BpeSegment_8002#FUN_80029f80}</li>
   *   <li>{@link Scus94491BpeSegment_8002#FUN_80025718}</li>
   * </ol>
   */
  public static final ArrayRef<Pointer<FunctionRef<RunningScript, Long>>> scriptSubFunctions_800e75b8 = MEMORY.ref(4, 0x800e75b8L, ArrayRef.of(Pointer.classFor(FunctionRef.classFor(RunningScript.class, Long.class)), 16, 4, Pointer.deferred(4, FunctionRef::new)));
  /**
   * <p>Copied to {@link Scus94491BpeSegment_8004#scriptSubFunctions_8004e29c} at index 480</p>
   *
   * <ol start="0">
   *   <li>{@link Bttl_800f#FUN_800f95d0}</li>
   *   <li>{@link Bttl_800f#FUN_800f2500}</li>
   *   <li>{@link Bttl_800f#FUN_800f96d4}</li>
   *   <li>{@link Bttl_800f#FUN_800f9730}</li>
   *   <li>{@link Bttl_800f#FUN_800f95d0}</li>
   *   <li>{@link Bttl_800f#FUN_800f95d0}</li>
   *   <li>{@link Bttl_800f#FUN_800f95d0}</li>
   *   <li>{@link Bttl_800f#FUN_800f43dc}</li>
   *   <li>{@link Bttl_800f#FUN_800f4518}</li>
   *   <li>{@link Bttl_800f#FUN_800f97d8}</li>
   *   <li>{@link Bttl_800f#FUN_800f4600}</li>
   *   <li>{@link Bttl_800f#FUN_800f480c}</li>
   *   <li>{@link Bttl_800f#FUN_800f2694}</li>
   *   <li>{@link Bttl_800f#FUN_800f96a8}</li>
   *   <li>{@link Bttl_800f#scriptRenderRecover}</li>
   *   <li>{@link Bttl_800f#FUN_800f2838}</li>
   *   <li>{@link Bttl_800f#FUN_800f9884}</li>
   *   <li>{@link Bttl_800f#FUN_800f98b0}</li>
   *   <li>{@link Bttl_800f#FUN_800f99ec}</li>
   *   <li>{@link Bttl_800f#FUN_800f9a50}</li>
   *   <li>{@link Bttl_800f#scriptIsFloatingNumberOnScreen}</li>
   *   <li>{@link Bttl_800f#FUN_800f9b78}</li>
   *   <li>{@link Bttl_800f#FUN_800f9b94}</li>
   *   <li>{@link Bttl_800f#FUN_800f9bd4}</li>
   *   <li>{@link Bttl_800f#FUN_800f9c00}</li>
   *   <li>{@link Bttl_800f#FUN_800f9c2c}</li>
   *   <li>{@link Bttl_800f#FUN_800f9cac}</li>
   *   <li>{@link Bttl_800f#FUN_800f9618}</li>
   *   <li>{@link Bttl_800f#FUN_800f9660}</li>
   *   <li>{@link Bttl_800f#FUN_800f9d7c}</li>
   * </ol>
   */
  public static final ArrayRef<Pointer<FunctionRef<RunningScript, Long>>> scriptSubFunctions_800e75f8 = MEMORY.ref(4, 0x800e75f8L, ArrayRef.of(Pointer.classFor(FunctionRef.classFor(RunningScript.class, Long.class)), 30, 4, Pointer.deferred(4, FunctionRef::new)));
  /**
   * <p>Copied to {@link Scus94491BpeSegment_8004#scriptSubFunctions_8004e29c} at index 256</p>
   *
   * <ol start="0">
   *   <li>{@link SMap#FUN_800e67d4}</li>
   *   <li>{@link SMap#FUN_800e68b4}</li>
   *   <li>{@link SMap#FUN_800e6904}</li>
   *   <li>{@link SMap#FUN_800e69a4}</li>
   *   <li>{@link SMap#FUN_800e69e8}</li>
   *   <li>{@link SMap#FUN_800e69f0}</li>
   *   <li>{@link SMap#FUN_800e6a28}</li>
   *   <li>{@link SMap#FUN_800e6a64}</li>
   *   <li>{@link SMap#FUN_800e683c}</li>
   *   <li>{@link SMap#FUN_800e6af0}</li>
   *   <li>{@link SMap#FUN_800e6aa0}</li>
   *   <li>{@link SMap#FUN_800e6b64}</li>
   *   <li>{@link SMap#FUN_800e6bd8}</li>
   *   <li>{@link SMap#FUN_800e6be0}</li>
   *   <li>{@link SMap#FUN_800e6cac}</li>
   *   <li>{@link SMap#FUN_800e6ce0}</li>
   * </ol>
   */
  public static final ArrayRef<Pointer<FunctionRef<RunningScript, Long>>> scriptSubFunctions_800e7670 = MEMORY.ref(4, 0x800e7670L, ArrayRef.of(Pointer.classFor(FunctionRef.classFor(RunningScript.class, Long.class)), 16, 4, Pointer.deferred(4, FunctionRef::new)));
  /**
   * <p>Copied to {@link Scus94491BpeSegment_8004#scriptSubFunctions_8004e29c} at index 768</p>
   *
   * <ol start="0">
   *   <li>{@link SMap#FUN_800f2048}</li>
   *   <li>{@link SMap#FUN_800f1f9c}</li>
   *   <li>{@link SMap#FUN_800f1060}</li>
   *   <li>{@link SMap#FUN_800f2264}</li>
   *   <li>{@link SMap#scriptAddSavePoint}</li>
   *   <li>{@link SMap#FUN_800f23ec}</li>
   *   <li>{@link SMap#FUN_800f2780}</li>
   *   <li>{@link SMap#FUN_800f2090}</li>
   *   <li>{@link SMap#FUN_800f2198}</li>
   *   <li>{@link SMap#FUN_800f1eb8}</li>
   *   <li>{@link SMap#FUN_800f2618}</li>
   *   <li>{@link SMap#FUN_800f1b64}</li>
   *   <li>{@link SMap#FUN_800f26c8}</li>
   *   <li>{@link SMap#FUN_800f1d0c}</li>
   *   <li>{@link SMap#FUN_800f14f0}</li>
   *   <li>{@link SMap#FUN_800f24d8}</li>
   *   <li>{@link SMap#FUN_800f24b0}</li>
   *   <li>{@link SMap#FUN_800f23a0}</li>
   *   <li>{@link SMap#FUN_800f1634}</li>
   *   <li>{@link SMap#FUN_800f22c4}</li>
   *   <li>{@link SMap#FUN_800f2554}</li>
   *   <li>{@link SMap#FUN_800f25a8}</li>
   *   <li>{@link SMap#FUN_800f1274}</li>
   * </ol>
   */
  public static final ArrayRef<Pointer<FunctionRef<RunningScript, Long>>> scriptSubFunctions_800e76b0 = MEMORY.ref(4, 0x800e76b0L, ArrayRef.of(Pointer.classFor(FunctionRef.classFor(RunningScript.class, Long.class)), 23, 4, Pointer.deferred(4, FunctionRef::new)));

  @Method(0x800e5d44L)
  public static void main() {
    gameInit();
    preload();
  }

  @Method(0x800e5d64L) //TODO can rename most of these functions
  public static void gameInit() {
    ResetGraph(0);
    SetGraphDebug(2);

    GsInitGraph((short)640, (short)480, 0b110100);
    GsDefDispBuff((short)0, (short)16, (short)0, (short)16);

    orderingTableBits_1f8003c0.set(14);
    zShift_1f8003c4.set(0);
    orderingTableSize_1f8003c8.set(0x4000);
    zMax_1f8003cc.set(0x3ffe);
    GPU.updateOrderingTableSize(orderingTableSize_1f8003c8.get());

    FUN_8003c5e0();

    _8007a3a8.setu(0);
    _800bb104.setu(0);
    _800babc0.setu(0);

    InitGeom();
    setProjectionPlaneDistance(640);
    FUN_80019500();

    mainCallbackIndexOnceLoaded_8004dd24.setu(0);
    pregameLoadingStage_800bb10c.setu(0);
    vsyncMode_8007a3b8.set(2);
    tickCount_800bb0fc.setu(0);

    precalculateTpages();
    loadSystemFont();
    clearScriptStates();
    allocateHeap(heap_8011e210.getAddress(), 0x3d_edf0L);
    loadOvalBlobTexture();
    FUN_800e6dd4();
    FUN_800e6e3c();
    copyScriptSubFunctions_800e67ac();
    FUN_800e6888();
    FUN_800e6d60();
    FUN_800e670c();
    FUN_800e6ecc();
    FUN_800e6774();
    initFmvs();
  }

  @Method(0x800e5fc0L)
  public static void finalizePregameLoading() {
    throw new RuntimeException("No longer used");
  }

  @Method(0x800e60d8L)
  public static void precalculateTpages() {
    for(final Bpp bpp : Bpp.values()) {
      for(final Translucency trans : Translucency.values()) {
        texPages_800bb110.get(bpp).get(trans).get(TexPageY.Y_0).set(GetTPage(bpp, trans, 0, 0));
        texPages_800bb110.get(bpp).get(trans).get(TexPageY.Y_256).set(GetTPage(bpp, trans, 0, 256));
      }
    }
  }

  @Method(0x800e6184L)
  public static void preload() {
    drgnBinIndex_800bc058.set(1);

    loadMenuSounds();
    setWidthAndFlags(320);
    vsyncMode_8007a3b8.set(2);

    //LAB_800e600c
    loadBasicUiTexturesAndSomethingElse();

    //LAB_800e6040
    _8004dd30.setu(0);
    fmvIndex_800bf0dc.setu(0);
    afterFmvLoadingStage_800bf0ec.setu(0x2L);
  }

  @Method(0x800e6524L)
  public static void loadSystemFont() {
    final TimHeader header = parseTimHeader(timHeader_800c6748);

    final RECT imageRect = new RECT((short)832, (short)424, (short)64, (short)56);
    LoadImage(imageRect, header.getImageAddress());

    _800bb348.setu(texPages_800bb110.get(Bpp.BITS_4).get(Translucency.HALF_B_PLUS_HALF_F).get(TexPageY.Y_256).get()).oru(0xdL);

    if(header.hasClut()) {
      final RECT clutRect = new RECT((short)832, (short)422, (short)32, (short)1);
      LoadImage(clutRect, header.getClutAddress());
    }

    //LAB_800e65c4
    _1f8003fc.setu(_800bb228.getAddress());

    //LAB_800e65e8
    for(int i = 2; i < 37; i++) {
      long v1 = 0xffff_ffffL;
      long a1 = 0x1L;

      //LAB_800e65fc
      while(v1 >= i) {
        a1 *= i;
        v1 /= i;
      }

      //LAB_800e6620
      array_800bb198.get(i - 2).set(a1);
    }
  }

  @Method(0x800e6654L)
  public static void clearScriptStates() {
    //LAB_800e666c
    for(int i = 0; i < 0x48; i++) {
      scriptStatePtrArr_800bc1c0.get(i).set(unusedScriptState_800bc0c0);
    }
  }

  @Method(0x800e670cL)
  public static void FUN_800e670c() {
    //LAB_800e6720
    for(int i = 0; i < 32; i++) {
      scriptSubFunctions_8004e29c.get(736 + i).set(scriptSubFunctions_800e6f64.get(i).deref());
    }

    //LAB_800e6750
    for(int i = 0; i < 23; i++) {
      scriptSubFunctions_8004e29c.get(832 + i).set(scriptSubFunctions_800e6fe4.get(i).deref());
    }
  }

  @Method(0x800e6774L)
  public static void FUN_800e6774() {
    //LAB_800e6788
    for(int i = 0; i < 2; i++) {
      scriptSubFunctions_8004e29c.get(896 + i).set(scriptSubFunctions_800e7040.get(i).deref());
    }
  }

  @Method(0x800e67acL)
  private static void copyScriptSubFunctions_800e67ac() {
    //LAB_800e67c0
    for(int i = 0; i < 19; i++) {
      scriptSubFunctions_8004e29c.get(32 + i).set(scriptSubFunctions_800e7048.get(i).deref());
    }

    //LAB_800e67f0
    for(int i = 0; i < 32; i++) {
      scriptSubFunctions_8004e29c.get(224 + i).set(scriptSubFunctions_800e7094.get(i).deref());
    }

    //LAB_800e6820
    for(int i = 0; i < 15; i++) {
      scriptSubFunctions_8004e29c.get(704 + i).set(scriptSubFunctions_800e7114.get(i).deref());
    }

    //LAB_800e683c
    //LAB_800e6850
    for(int i = 0; i < 8; i++) {
      scriptSubFunctions_8004e29c.get(800 + i).set(scriptSubFunctions_800e7150.get(i).deref());
    }
  }

  @Method(0x800e6874L)
  public static void FUN_800e6874() {
    scriptSubFunctions_8004e29c.get(445).set(scriptSubFunction_800ca734);
  }

  @Method(0x800e6888L)
  public static void FUN_800e6888() {
    //LAB_800e68a4
    for(int i = 0; i < 32; i++) {
      scriptSubFunctions_8004e29c.get(416 + i).setNullable(scriptSubFunctions_800e7170.get(i).derefNullable());
    }

    //LAB_800e68d4
    for(int i = 0; i < 32; i++) {
      scriptSubFunctions_8004e29c.get(544 + i).set(scriptSubFunctions_800e71f0.get(i).deref());
    }

    //LAB_800e6904
    for(int i = 0; i < 32; i++) {
      scriptSubFunctions_8004e29c.get(576 + i).setNullable(scriptSubFunctions_800e7270.get(i).derefNullable());
    }

    //LAB_800e6934
    for(int i = 0; i < 32; i++) {
      scriptSubFunctions_8004e29c.get(608 + i).setNullable(scriptSubFunctions_800e72f0.get(i).derefNullable());
    }

    //LAB_800e6964
    for(int i = 0; i < 32; i++) {
      scriptSubFunctions_8004e29c.get(640 + i).setNullable(scriptSubFunctions_800e7370.get(i).derefNullable());
    }

    FUN_800e6874();
  }

  @Method(0x800e6998L)
  public static void loadOvalBlobTexture() {
    //LAB_800e69b8
    for(int i = 0; i < 32; i++) {
      scriptSubFunctions_8004e29c.get(96 + i).set(scriptSubFunctions_800e73f0.get(i).deref());
    }

    //LAB_800e69e8
    for(int i = 0; i < 32; i++) {
      scriptSubFunctions_8004e29c.get(288 + i).set(scriptSubFunctions_800e7470.get(i).deref());
    }

    //LAB_800e6a18
    for(int i = 0; i < 15; i++) {
      scriptSubFunctions_8004e29c.get(512 + i).set(scriptSubFunctions_800e7570.get(i).deref());
    }

    //LAB_800e6a48
    for(int i = 0; i < 32; i++) {
      scriptSubFunctions_8004e29c.get(672 + i).set(scriptSubFunctions_800e74f0.get(i).deref());
    }

    submapIndex_800bd808.set(0);

    final TimHeader header = parseTimHeader(ovalBlobTimHeader_80010548);
    LoadImage(header.getImageRect(), header.getImageAddress());

    if(header.hasClut()) {
      LoadImage(header.getClutRect(), header.getClutAddress());
    }

    //LAB_800e6af0
    FUN_800e6b3c(model_800bda10, extendedTmd_800103d0, tmdAnimFile_8001051c);

    model_800bda10.coord2Param_64.rotate.x.set((short)0);
    model_800bda10.coord2Param_64.rotate.y.set((short)0);
    model_800bda10.coord2Param_64.rotate.z.set((short)0);
    model_800bda10.colourMap_9d.set(0);
    model_800bda10.b_cc.set(0);
  }

  /** Very similar to {@link Scus94491BpeSegment_8002#FUN_80020718(legend.game.types.Model124, legend.game.types.ExtendedTmd, TmdAnimationFile)} */
  @Method(0x800e6b3cL)
  public static void FUN_800e6b3c(final Model124 model, final ExtendedTmd extendedTmd, final TmdAnimationFile tmdAnimFile) {
    final int x = model.coord2_14.coord.transfer.getX();
    final int y = model.coord2_14.coord.transfer.getY();
    final int z = model.coord2_14.coord.transfer.getZ();

    //LAB_800e6b7c
    for(int i = 0; i < 7; i++) {
      model.aub_ec.get(i).set(0);
    }

    model.dobj2ArrPtr_00.set(_800bd9f8);
    model.coord2ArrPtr_04.set(_800bdb38);
    model.coord2ParamArrPtr_08.set(_800bd7c0);
    model.count_c8.set((short)tmdAnimFile.count_0c.get());

    final Tmd tmd = extendedTmd.tmdPtr_00.deref().tmd;
    model.tmd_8c.set(tmd);
    model.tmdNobj_ca.set(tmd.header.nobj.get());
    model.scaleVector_fc.setPad((int)((extendedTmd.tmdPtr_00.deref().id.get() & 0xffff0000L) >>> 11));

    final long v0 = extendedTmd.ptr_08.get();
    if(v0 == 0) {
      //LAB_800e6c44
      model.ptr_a8.set(extendedTmd.ptr_08.getAddress());

      //LAB_800e6c54
      for(int i = 0; i < 7; i++) {
        model.aui_d0.get(i).set(0);
      }
    } else {
      model.ptr_a8.set(extendedTmd.getAddress() + v0 / 4 * 4);

      //LAB_800e6c00
      for(int i = 0; i < 7; i++) {
        model.aui_d0.get(i).set(model.ptr_a8.get() + MEMORY.ref(4, model.ptr_a8.get()).offset(i * 0x4L).get() / 4 * 4);
        FUN_8002246c(model, i);
      }
    }

    //LAB_800e6c64
    adjustTmdPointers(model.tmd_8c.deref());
    initObjTable2(model.ObjTable_0c, model.dobj2ArrPtr_00.deref(), model.coord2ArrPtr_04.deref(), model.coord2ParamArrPtr_08.deref(), model.count_c8.get());
    model.coord2_14.param.set(model.coord2Param_64);
    GsInitCoordinate2(null, model.coord2_14);
    prepareObjTable2(model.ObjTable_0c, model.tmd_8c.deref(), model.coord2_14, model.count_c8.get(), (short)(model.tmdNobj_ca.get() + 0x1L));

    model.zOffset_a0.set((short)0);
    model.ub_a2.set(0);
    model.ub_a3.set(0);
    model.ui_f4.set(0);
    model.ui_f8.set(0);

    FUN_80021584(model, tmdAnimFile);

    model.coord2_14.coord.transfer.setX(x);
    model.coord2_14.coord.transfer.setY(y);
    model.coord2_14.coord.transfer.setZ(z);
    model.b_cc.set(0);
    model.scaleVector_fc.setX(0x1000);
    model.scaleVector_fc.setY(0x1000);
    model.scaleVector_fc.setZ(0x1000);
    model.vector_10c.setX(0x1000);
    model.vector_10c.setY(0x1000);
    model.vector_10c.setZ(0x1000);
    model.vector_118.setX(0);
    model.vector_118.setY(0);
    model.vector_118.setZ(0);
  }

  @Method(0x800e6d60L)
  public static void FUN_800e6d60() {
    _800bdb90.setu(0);
    _800bdc24.setu(0);
    renderablePtr_800bdc5c = null;
    FUN_800e6d9c();
  }

  @Method(0x800e6d9cL)
  public static void FUN_800e6d9c() {
    for(int i = 0; i < 3; i++) {
      scriptSubFunctions_8004e29c.get(864 + i).set(scriptSubFunctions_800e75ac.get(i).deref());
    }
  }

  @Method(0x800e6dd4L)
  public static void FUN_800e6dd4() {
    //LAB_800e6de8
    for(int i = 0; i < 16; i++) {
      scriptSubFunctions_8004e29c.get(192 + i).set(scriptSubFunctions_800e75b8.get(i).deref());
    }

    //LAB_800e6e18
    for(int i = 0; i < 30; i++) {
      scriptSubFunctions_8004e29c.get(480 + i).set(scriptSubFunctions_800e75f8.get(i).deref());
    }
  }

  @Method(0x800e6e3cL)
  public static void FUN_800e6e3c() {
    memcpy(scriptSubFunctions_8004e29c.get(256).getAddress(), scriptSubFunctions_800e7670.getAddress(), 0x40);
  }

  @Method(0x800e6e6cL)
  public static void initFmvs() {
    enableAudioSource(0x1L, 0x1L);
    setCdVolume(0x7f, 0x7f);
    setCdMix(0x3f);

    _800bf0cf.setu(0);
    _800bf0d0.setu(0);
    fmvStage_800bf0d8.setu(0);
  }

  @Method(0x800e6eccL)
  public static void FUN_800e6ecc() {
    //LAB_800e6ee0
    for(int i = 0; i < 23; i++) {
      scriptSubFunctions_8004e29c.get(768 + i).set(scriptSubFunctions_800e76b0.get(i).deref());
    }
  }
}
