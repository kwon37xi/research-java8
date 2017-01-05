package lombokpractice.gettersetteraccessor;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

/*
* chain = true 일 때, setter 메소드를 인식하지 못함.
*
name : birthYear, setter? : null, getter? : public int lombokpractice.gettersetteraccessor.ChainedSetterBeanExample.getBirthYear()
name : class, setter? : null, getter? : public final native java.lang.Class java.lang.Object.getClass()
name : name, setter? : null, getter? : public java.lang.String lombokpractice.gettersetteraccessor.ChainedSetterBeanExample.getName()
 */
@Accessors(chain = true)
@Getter
@Setter
@ToString
public class ChainedSetterBeanExample {
    private int birthYear;
    private String name;

    public static void main(String[] args) throws IntrospectionException {
        ChainedSetterBeanExample example = new ChainedSetterBeanExample();
        example
            .setBirthYear(1977)
            .setName("KwonNam");

        System.out.println(example);

        PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(ChainedSetterBeanExample.class).getPropertyDescriptors();
        for (PropertyDescriptor pd: propertyDescriptors) {
            System.out.printf("name : %s, setter? : %s, getter? : %s%n", pd.getName(), pd.getWriteMethod(), pd.getReadMethod());
        }

    }
}
