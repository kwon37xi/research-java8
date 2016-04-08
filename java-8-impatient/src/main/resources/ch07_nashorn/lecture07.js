var intArray = Java.type('int[]');
var StringArray = Java.type('java.lang.String[]');

var numbers = new intArray(10); // int[10]{}
var names = new StringArray(10); // new String[10] {}

numbers[0] = 42;
print(numbers[0]);

for each (var elem in names) {
    print(elem);
}

for (var i in names) {
    print(i + " : " + names[i]);
}

java.lang.System.out.println(java.util.Arrays.toString(Java.to([1,2,3], Java.type('int[]'))));
