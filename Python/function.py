# def 함수이름():
#
#     수행할 코드
#     return 반환값;

######################################

# 전역변수, 지역변수

potato = "감자"

# 방법1
def jeju_potato():
    '''
        함수 설명
        ㅇㅇㅇ
    '''
    global potato
    print(potato)
    potato = "고구마"
    print(potato)

jeju_potato()

# 방법2
def jeju_potato2():
    potato = "고구마"
    print(potato)

print(potato)
jeju_potato2()

######################################

# 함수 기본값

a = 20

def add(num1, num2=10, num3=a): # 이 이후에 a 변수의 값이 바뀌어도 num3의 초기값(20)은 변하지 않는다.
    return num1 + num2 + num3

print(add(10)) # 40

a = 5

print(add(10)) # 40

#############################################

# 가변 매개변수
def add(*args):
    sum = 0
    for i in args:
        sum += i
    print(sum)

add(1, 2, 3) # 6
add(10, 20, 30, 40, 50) # 150
add() # 0

# args는 tuple 타입이다.
def args_type(*args):
    print(type(args)) 

args_type() # <class 'tuple'>

# 키워드(딕셔너리) 가변 매개변수
def star_player(**kwargs):
    for i, j in kwargs.items():
        if "서장훈" in kwargs.values():
            print("서장훈 들어옴")
        else:
            print(f"{i}는 역시 {j}지!")

star_player(축구="손흥민", 야구="박용택", 농구="허재")
star_player(야구="서장훈")