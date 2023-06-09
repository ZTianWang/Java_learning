package advanced;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@TypeAnnotation
public class Test {
	@MyAnnotation(description = "This is a field")
	int count = 0;
	
	public static void main(String[] args) throws Exception {
		
		Class<?> clz = Test.class;
		Annotation[] anns = clz.getAnnotations();
		System.out.println(anns.length);
		for (Annotation annotation : anns) {
			System.out.println(annotation);
		}
		TypeAnnotation ann = clz.getAnnotation(TypeAnnotation.class);
		System.out.println(ann.value());
		
		Method method = clz.getDeclaredMethod("method");
		MyAnnotation mann = method.getAnnotation(MyAnnotation.class);
		System.out.println(mann.description());
		
		Field field = clz.getDeclaredField("count");
		MyAnnotation fann = field.getAnnotation(MyAnnotation.class);
		System.out.println(fann.description());
		
	}
	
	@MyAnnotation(description = "This is a method")
	public void method() {
		
	}
}
