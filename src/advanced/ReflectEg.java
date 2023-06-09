package advanced;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectEg {

	public static void main(String[] args) {

		//依赖注入实现
		try (FileReader in = new FileReader("./files/configuration.ini");
				BufferedReader bin = new BufferedReader(in);) {

			String clzName = bin.readLine();

			Class<?> clz = Class.forName(clzName);
//			Class<String> clz = String.class;
			System.out.println("#class: " + clz.getName());

			
			Constructor<?>[] constructors = clz.getConstructors();
			for (Constructor<?> item : constructors) {
				System.out.println("##constructor: " + item);
			}

			Constructor<?> constructor = clz.getConstructor(int.class);
			Object intParam = 1;
			Object obj = constructor.newInstance(intParam);

			
			Method[] methods = clz.getDeclaredMethods();
			for (Method item : methods) {
				System.out.println("###method: " + item);
			}

			Class<?>[] classes = {int.class, String.class};
			Method method = clz.getDeclaredMethod("setIntAndStr", classes);
//			Method method = clz.getDeclaredMethod("setIntAndStr", int.class, String.class);
			Object[] params = new Object[2];
			params[0] = 1;
			params[1] = "a";
			method.invoke(obj, params);
			
			//调用无参方法：
			Method m2 = clz.getDeclaredMethod("method1");
			m2.invoke(obj);
			
			Field[] fields = clz.getDeclaredFields();
			for (Field item : fields) {
				System.out.println("####field: " + item);
			}

			Field nameField = clz.getDeclaredField("NAME");
			nameField.setAccessible(true);
			String objName = (String) nameField.get(obj);
			Field field = clz.getDeclaredField("str");
			System.out.println(objName + " original field str = " + field.get(obj));
			field.set(obj, "b");
			System.out.println(objName + " set field str = " + field.get(obj));

		} catch (IOException | ClassNotFoundException | NoSuchMethodException | SecurityException
				| InstantiationException | IllegalAccessException | IllegalArgumentException 
				| InvocationTargetException| NoSuchFieldException e) {
			e.printStackTrace();
		}
	}
}
