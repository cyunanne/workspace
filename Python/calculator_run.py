#import calculator as c
from calculator import add as a # 모듈에서 필요한 함수만 가져오기 + 별칭
from calculator import add, sub  # 모듈에서 필요한 함수만 가져오기(여러개), 별칭사용불가

#print(calculator.add(10, 20))

# 별칭 사용하기
#print(c.sub(10, 2))

print(a(10, 5))

print(add(2, 3))
print(sub(3, 2))