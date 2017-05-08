import gov.nasa.jpf.vm.NativePeer;
import gov.nasa.jpf.vm.MJIEnv;
import nhandler.conversion.ConversionException;
import nhandler.conversion.ConverterBase;
import nhandler.conversion.jvm2jpf.JVM2JPFConverter;
import nhandler.conversion.jpf2jvm.JPF2JVMConverter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;

public class OTF_JPF_java_lang_StrictMath extends NativePeer {

  public OTF_JPF_java_lang_StrictMath() {
  }

  public static double log__D__D (MJIEnv env, int rcls, double arg0)
        throws java.lang.IllegalArgumentException,
            SecurityException, NoSuchMethodException, IllegalAccessException,
                 ClassNotFoundException, ConversionException, InvocationTargetException {

    ConverterBase.reset(env);

    Class<?> caller = JPF2JVMConverter.obtainJVMCls(rcls, env);

    Object argValue[] = new Object[1];
    argValue[0] = new Double(arg0);

    Class<?> argType[] = new Class[1];
    argType[0] = Double.TYPE;

    Method method = caller.getDeclaredMethod("log", argType);

    method.setAccessible(true);

    Object returnValue = method.invoke(null, argValue);

    JVM2JPFConverter.obtainJPFCls(caller, env);

    return ((Double)returnValue).doubleValue();
  }
}
