package ch14.sec02;

public class Tree {
    private String key;
    private int val;
    private Tree left, right;

    public Tree(String k, int v, Tree l, Tree r) {
        key = k;
        val = v;
        left = l;
        right = r;
    }

    public static class TreeProcessor {
        public static int lookup(String k, int defaultval, Tree t) {
            if (t == null) {
                return defaultval;
            }

            if (k.equals(t.key)) {
                return t.val;
            }

            return lookup(k, defaultval, k.compareTo(t.key) < 0 ? t.left : t.right);
        }

        // 비함수형 update
        public static void update(String k, int newval, Tree t) {
            if (t == null) {
                // todo
            } else if (k.equals(t.key)) {
                t.val = newval;
            } else {
                update(k, newval, k.compareTo(t.key) < 0 ? t.left : t.right);
            }
        }

        // 함수형 update
        public static Tree fupdate(String k, int newval, Tree t) {
            return (t == null) ?
                new Tree(k, newval, null, null) :
                k.equals(t.key) ?
                    new Tree(k, newval, t.left, t.right) :
                    k.compareTo(t.key) < 0 ?
                        new Tree(t.key, t.val, fupdate(k, newval, t.left), t.right) :
                        new Tree(t.key, t.val, t.left, fupdate(k, newval, t.right));


        }
    }
}
