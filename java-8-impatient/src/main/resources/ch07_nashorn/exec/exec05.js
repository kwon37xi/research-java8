function newArrayList(logPrefix) {
    var arr = new (Java.extend(java.util.ArrayList)) {
        add: function(x) {
            print(logPrefix + ' : ' + x);
            return Java.super(arr).add(x);
        }
    }
    return arr;
}

var intArrList = newArrayList("integer array list");
intArrList.add(1);
intArrList.add(2);
intArrList.add(3);
intArrList.add(4);

var strArrList = newArrayList("str array list");
strArrList.add("Hello");
strArrList.add("Nashorn!");

intArrList.add(5);
intArrList.add(6);
