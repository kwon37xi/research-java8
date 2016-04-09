var list = new java.util.ArrayList()
print(list)

try {
    list.get(0);
} catch (ex) {
    if (ex instanceof java.lang.IndexOutOfBoundsException) {
        print('list is empty')
    }
}
