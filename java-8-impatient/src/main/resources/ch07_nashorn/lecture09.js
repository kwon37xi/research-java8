var square = function(x) { return x * x; };
print(square(2));

var words = Java.to(['hello', 'world', 'hi', 'good morning'], Java.type('java.lang.String[]'));
java.util.Arrays.sort(words, function(a, b) java.lang.Integer.compare(a.length, b.length));

print(java.util.Arrays.toString(words));
