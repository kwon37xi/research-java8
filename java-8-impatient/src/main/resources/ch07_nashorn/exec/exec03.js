var b = new java.math.BigInteger('1234567890987654321');
print("with b " + b); // b가 javascript 기본 숫자타입인 Double 로 변경돼 보이는 것같음.
print("with js number : " + 1234567890987654321);
print("mod result : " + b.mod(java.math.BigInteger.TEN));
// b 를 b 자체로 유지시키려면
java.lang.System.out.printf("%d", b);
