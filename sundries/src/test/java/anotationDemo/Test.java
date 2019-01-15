package anotationDemo;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.management.Query;

import com.sun.jndi.url.corbaname.corbanameURLContextFactory;
import com.sun.org.apache.xpath.internal.operations.And;

/***
 * 自定义注解测试类
 * @author liaot
 *
 */
public class Test {
    public static void main(String[] args) {
    	

    	
        Filter f1 = new Filter();
        f1.setId(1); //查询id为1的用户

        Filter f2 = new Filter();
        f1.setUsername("lili"); //查询用户名为lili的用户

        Filter f3 = new Filter();
        f3.setCity("衡阳,长沙,永州"); //查询地点在这三个城市之间的

        String sql1 = query(f1);
        String sql2 = query(f2);
        String sql3 = query(f3);

        System.out.println(sql1);
        System.out.println(sql2);
        System.out.println(sql3);

    }

    private static String query(Object f) {
        StringBuilder sql = new StringBuilder();
        //获取到class
        Class c1 = f.getClass();
        //获取table的名字
        boolean exists = c1.isAnnotationPresent(Table.class);
        if(!exists) {
            return null;
        }
        Table t = (Table)c1.getAnnotation(Table.class);
        //定义表名
        String tableName = t.value();
        sql.append("select * from ").append(tableName).append(" where 1=1 ");
        //遍历所有的字段
        Field[] fields = c1.getDeclaredFields();
        for (Field field : fields) {
            //判断是否存在这个注解
            boolean Fexists = field.isAnnotationPresent(Column.class);
            if(!Fexists) {
                continue;
            }
            //获取注解
            Column column = field.getAnnotation(Column.class);
            String ColumuName = column.value();
            //获取字段的值
            String fieldName = field.getName();
            String getMethodName = "get" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
            Method method = null;
            Object ColumuValue = null;
            try {
                method = c1.getMethod(getMethodName);
                ColumuValue = method.invoke(f);

            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
            }
            //int型不需要加单引号，String型需要加单引号
            if(ColumuValue instanceof Integer && (int)ColumuValue != 0){
                sql.append("and " + ColumuName + " = " + ColumuValue + " ");
            }else if(ColumuValue instanceof String){
                if( ((String) ColumuValue).contains(",") ){
                    String[] values = ((String) ColumuValue).split(",");
                    sql.append("and " + ColumuName + " in (");
                    for(int i=0; i<values.length; i++) {
                        sql.append("'").append(values[i]).append(" ',");
                    }
                    sql.deleteCharAt(sql.length() -1);
                    sql.append(")"); 
                }else{
                    sql.append("and " + ColumuName + " = '" + ColumuValue + "' ");
                }
            }
        }
        return sql.toString();
    }
}