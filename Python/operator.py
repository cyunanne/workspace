print(10 + 2)
print(10 - 2)
print(10 / 2)
print(10 * 2)
print(10 ** 2) # 제곱

# 나눗셈 결과는 float 형
print(type(10 / 10))

# 몫 연산, 나머지 연산
print(10 // 3)
print(10 % 3)

# 비교 연산자
a = 10
b = 20

print(a < b)
print(a <= b)
print(a > b)
print(a >= b)
print(a == b)
print(a != b)

is_true = True
is_false = False

print(is_true > is_false)
print(is_false > is_true)

print('Python' > 'python')
print('123' > '124')

# 복합 대입 연산자
# +=, -=, *=, /=, **=, ...

# 논리 연산자
# and, or
print(True and True)
print(True and False)
print(True or False)
print((10>3 or False) and '참') # 참인 경우 마지막 요소 출력