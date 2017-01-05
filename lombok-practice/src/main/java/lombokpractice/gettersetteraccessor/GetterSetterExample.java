package lombokpractice.gettersetteraccessor;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

/*
class 혹은 field 에 지정하여 getter/setter 메소드를 생성한다.

AccessLevel.PUBLIC|PROTECTED|PACKAGE|PRIVATE 으로 메소드 액세스 레벨 지정가능

AccessLevel.NONE 으로 메소드 생성 안하게 할 수 있음.

onX 기능으로 어노테이션 추가 생성.

필드의 javadoc 이 getter/setter로 자동으로 옮기짐. 과정에서 @return 은 getter로 @param은 setter로 옮겨짐.
혹은 --GETTER--, --SETTER-- 섹션 아래에 javadoc 을 두면 관련 메소드로 해당 항목 이동

* Configurations

lombok.accessors.chain = [true | false] (default: false) // setter가 this 리턴
lombok.accessors.fluent = [true | false] (default: false) // get/set 없이 필드 이름을 메소드이름으로 사용.
lombok.accessors.prefix += a field prefix (default: empty list) // field 이름에 prefix 있을 경우 사용.
                                                                // private String mField; 이고 prefix=m 일때, getField(), setField() 생성.
lombok.getter.noIsPrefix = [true | false] (default: false) // boolean getter 생성시 is 대신 get 사용.
lombok.setter.flagUsage = [warning | error] (default: not set)
lombok.getter.flagUsage = [warning | error] (default: not set)
 */
public class GetterSetterExample {
    /**
     * Age of the person. Water is wet.
     *
     * @param age New value for this person's age. Sky is blue.
     * @return The current value of this person's age. Circles are round.
     */
    @Getter
    @Setter
    private int age = 10;

    /**
     * Name of the person.
     * --SETTER--
     * Changes the name of this person.
     *
     * @param name The new value
     */
    @Setter(AccessLevel.PROTECTED) private String name;

    @Setter(AccessLevel.NONE) private String address;

    @Override
    public String toString() {
        return String.format("%s (age: %d, address: %s)", name, age, address);
    }

    public static void main(String[] args) throws IntrospectionException {
        GetterSetterExample example = new GetterSetterExample();
        example.setAge(20);
        example.setName("lombok");
        example.address = "Seoul"; // no setAddress()

        System.out.println(example);

        PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(GetterSetterExample.class).getPropertyDescriptors();
        for (PropertyDescriptor pd: propertyDescriptors) {
            System.out.printf("name : %s, setter? : %s, getter? : %s%n", pd.getName(), pd.getWriteMethod(), pd.getReadMethod());
        }
    }
}
