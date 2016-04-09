var RandomIterator = Java.extend(java.util.Iterator, {
    next: function() Math.random(),
    hasNext: function() true
});

/*
var randomIter = new java.util.Iterator {
    next: function() Math.random(),
    hasNext: function() true
}
*/

var randomIter = new RandomIterator();
print(randomIter.next())
print(randomIter.next())
print(randomIter.next())

var task = new java.lang.Runnable(function() { print('Hello') })
task.run();

var tenIter = new java.util.Iterator {
    count: 10,
    next: function() { this.count--; return Math.random() },
    hasNext: function() this.count > 0
}

while (tenIter.hasNext()) {
    print("next random : " + tenIter.next())
}

var arr = new (Java.extend(java.util.ArrayList)) {
    add: function(x) {
        print('Adding ' + x);
        return Java.super(arr).add(x);
    }
}
arr.add(1)
arr.add(2)
arr.add(3)

for each (var i in arr) {
    print('Arr ' + i);
}
