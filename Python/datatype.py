###### int(정수) 형
a = 5
b = -5
c = 0

print(type(a), type(b), type(c))

###### float(실수) 형
d = 5.5
e = -5.5
f = 0.0

print(type(d), type(e), type(f))

# 과학적 표기법
# 123456.7 = 1.234567 x 10^5
g = 1.234567e5

print(g)

###### complex 자료형
h =  1 + 2j # 복소수

print(h)

###### string(문자(열)) 형
text = 'String Data Type' # 작은 따옴표
text2 = "String Data Type2" # 쌍따옴표
text3 = 'String "Data" Type' # 따옴표 종류를 다르게
text4 = "String \"Data\" Type" # escape character 사용

print(text)
print(text2)
print(text3)
print(text4)

# 문자열 코드 상에서 개행
text6 = 'Python\
    is\
    Easy'

print(text6)

# 문자열 출력 상에서 개행
text7 = """Python
is
Easy"""

text8 = '''Python
is
Easy2'''

text9 = 'Python\nis\nEasy3'

print(text7)
print(text8)
print(text9)

# 문자열 합치기
text10 = 'Python'
text11 = ' is'
text12 = ' Easy4'

print(text10 + text11 + text12)

# 반복출력
text13 = 'Python is Easy5 (repeat)\n'

print(text13 * 5)

###### boolean 형
is_true = True
is_false = False # False = 0 = 빈 문자 = []/()/{} = Null

print(is_true)
print(is_false)

###### escape chararcter
# \', \", \\, \n, \r, \t
text5 = "P123456789123456789\rPython is Easy" # 커서를 맨 앞으로 이동

print(text5)

###### int() 정수형
# 실수형, 논리형, 문자열
print(int(123.92))
print(int(123.924654))
print(int(123.0))

print(int(True))
print(int(False))

print(type(int('28988')))
#print(type(int('28988.45'))) # 에러 발생

###### float() 실수형
# 정수형, 논리형, 문자열
print(float(200))
print(float(3))

print(float(True))
print(float(False))

print(type(float('28988')))
print(type(float('28988.45')))

###### str() 문자열
# 모든 자료형
print(str(12389))
print(str(339.293))
print(str(True))
print(str(False))

print(type(str(12389)))
print(type(str(339.293)))
print(type(str(True)))
print(type(str(False)))

###### bool()
# 모든 자료형

# True
print(bool(0))
print(bool(0.0))
print(bool(''))

# False
print(bool(1))
print(bool(1.0))
print(bool('str'))
print(bool('123'))