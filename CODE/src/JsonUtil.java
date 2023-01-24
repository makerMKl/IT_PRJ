import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;


public class JsonUtil {

    private static JsonUtil util = new JsonUtil();

    public static String toJsonStr(Object obj) {
        return util.parseObj(obj, new StringBuilder());
    }

    private String parseObj(Object obj, StringBuilder builder) {
        if (builder == null) 
        {
            throw new UnsupportedOperationException("builder can not be null");
        }
        if (obj == null) 
        {
            return builder.append("\"null\"").toString();
        }
        Class clazz = obj.getClass();
        if (Collection.class.isAssignableFrom(clazz)) {
            Iterator iterator = ((Collection) obj).iterator();
            builder.append("[");
            while (iterator.hasNext()) {
                parseObj(iterator.next(), builder);
                if (iterator.hasNext())
                    builder.append(",");
            }
            builder.append("]");
        } else if (Map.class.isAssignableFrom(clazz)) {
            //如果是map，key一定且必须是String类型，如果是对象，给他toString()
            Map map = ((Map) obj);
            Iterator iterator = map.keySet().iterator();
            builder.append("{");
            while (iterator.hasNext()) {
                Object key = iterator.next();
                builder.append("\"");
                builder.append(key);
                builder.append("\":");
                parseObj(map.get(key), builder);
                if (iterator.hasNext())
                    builder.append(",");
            }
            builder.append("}");
        } else if (clazz.isArray()) {
            //数组
            /**
             * 虽然int[]类型的数组可以通过：Arrays.stream((int[]) obj).boxed().collect(Collectors.toList());
             * 转化为集合，但Arrays.stream()方法不支持char[] byte[] boolean[] float[]
             */
            builder.append("[");
            if (obj instanceof int[]) {
            	func_ArrayHandleInt((int[]) obj, builder);
            } else if (obj instanceof byte[]) {
            	func_ArrayHandleByte((byte[]) obj, builder);
            } else if (obj instanceof char[]) {
            	func_ArrayHandleChar((char[]) obj, builder);
            } else if (obj instanceof long[]) {
            	func_ArrayHandleLong((long[]) obj, builder);
            } else if (obj instanceof float[]) {
            	func_ArrayHandleFloat((float[]) obj, builder);
            } else if (obj instanceof short[]) {
            	func_ArrayHandleShort((short[]) obj, builder);
            } else if (obj instanceof double[]) {
            	func_ArrayHandleDouble((double[]) obj, builder);
            } else if (obj instanceof boolean[]) {
            	func_ArrayHandleBoolean((boolean[]) obj, builder);
            } else if (obj instanceof Object[]) {
                List<Object> list = Arrays.asList((Object[]) obj);
                for (int i = 0; i < list.size(); i++) {
                    parseObj(list.get(i), builder);
                    if (i < list.size() - 1) {
                        builder.append(",");
                    }
                }
            } else {
                throw new UnsupportedOperationException(obj.getClass().toString());
            }
            builder.append("]");
        } else if (String.class.isAssignableFrom(clazz) || Date.class.isAssignableFrom(clazz)) {
            builder.append("\"");
            builder.append(obj);
            builder.append("\"");
        } else if (Number.class.isAssignableFrom(clazz) || Boolean.class.isAssignableFrom(clazz)) {
            builder.append(obj);
        } else {
            //自定义的实体类
            Field[] fields = clazz.getDeclaredFields();
            builder.append("{");
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                if (Modifier.isPrivate(field.getModifiers())) {
                    field.setAccessible(true);
                }
                builder.append("\"");
                builder.append(field.getName());
                builder.append("\":");
                Object value = null;
                try {
                    value = field.get(obj);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                parseObj(value, builder);
                if (i < fields.length - 1) {
                    builder.append(",");
                }
            }
            builder.append("}");
        }
        return builder.toString();
    }

    private void func_ArrayHandleInt(int[] arr, StringBuilder builder) {
        for (int i = 0; i < arr.length; i++) 
        {
            parseObj(arr[i], builder);
            if (i < arr.length - 1) 
            {
                builder.append(",");
            }
        }
    }

    private void func_ArrayHandleByte(byte[] arrays, StringBuilder builder) {
        for (int i = 0; i < arrays.length; i++) 
        {
            parseObj(arrays[i], builder);
            if (i < arrays.length - 1) 
            {
                builder.append(",");
            }
        }
    }

    private void func_ArrayHandleChar(char[] arrays, StringBuilder builder) {
        for (int i = 0; i < arrays.length; i++) 
        {
            parseObj(arrays[i], builder);
            if (i < arrays.length - 1) 
            {
                builder.append(",");
            }
        }
    }

    private void func_ArrayHandleLong(long[] arrays, StringBuilder builder) {
        for (int i = 0; i < arrays.length; i++) 
        {
            parseObj(arrays[i], builder);
            if (i < arrays.length - 1) 
            {
                builder.append(",");
            }
        }
    }

    private void func_ArrayHandleFloat(float[] arrays, StringBuilder builder) {
        for (int i = 0; i < arrays.length; i++) 
        {
            parseObj(arrays[i], builder);
            if (i < arrays.length - 1) 
            {
                builder.append(",");
            }
        }
    }

    private void func_ArrayHandleShort(short[] arrays, StringBuilder builder) {
        for (int i = 0; i < arrays.length; i++) 
        {
            parseObj(arrays[i], builder);
            if (i < arrays.length - 1) 
            {
                builder.append(",");
            }
        }
    }

    private void func_ArrayHandleDouble(double[] arrays, StringBuilder builder) {
        for (int i = 0; i < arrays.length; i++) 
        {
            parseObj(arrays[i], builder);
            if (i < arrays.length - 1) 
            {
                builder.append(",");
            }
        }
    }

    private void func_ArrayHandleBoolean(boolean[] arrays, StringBuilder builder) {
        for (int i = 0; i < arrays.length; i++) 
        {
            parseObj(arrays[i], builder);
            if (i < arrays.length - 1) 
            {
                builder.append(",");
            }
        }
    }
}

