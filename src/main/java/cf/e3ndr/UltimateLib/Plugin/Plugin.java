package cf.e3ndr.UltimateLib.Plugin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Plugin {
	boolean needsClassLoader() default true;
	String pluginName();
	String colorCode();
	String version();
	boolean disallowReload();
	
}
