####################################################################################
# list
####################################################################################

# list 생성
list_a = [1, 2, 3, 4]
print(list_a) # 결과 : [1, 2, 3, 4]
list_b = ['a', 'b', 'c']
print(list_b) # 결과 : ['a', 'b', 'c']
list_c = [True, False]
print(list_c) # 결과 : [True, False]
list_d = [1, 'a', True] # 여러 데이터타입이 섞여있어도 가능
print(list_d) # 결과 : [1, 'a', True]

# index를 사용하여 list 활용
numbers = [0,1,2,3,4,5,6,7,8,9]
print(numbers[0])     # 결과 : 0
print(numbers[3:5])   # 결과 : [3, 4]
print(numbers[1:])    # 결과 : [1, 2, 3, 4, 5, 6, 7, 8, 9]
print(numbers[-3:-1]) # 결과 : [7, 8]

list_lang = ["JAVA", "C", "Python", "Go"]
print(list_lang[0])      # 결과 : JAVA
print(list_lang[0][1])   # 결과 : A
print(list_lang[2][2:4]) # 결과 : th

list_lang[1] = "C++"
print(list_lang) # 결과 : ['JAVA', 'C++', 'Python', 'Go']

list_lang[1:3] = "C#", "Python3"
print(list_lang) # 결과 : ['JAVA', 'C#', 'Python3', 'Go']

list_lang[1:3] =  "C" # 인덱싱 범위와 갯수가 달라도 실행가능 BUT 리스트 개수가 달라짐
print(list_lang) # 결과 : ['JAVA', 'C', 'Go']

list_lang[1:3] =  "CPP", "C#", "HTML" # 인덱싱 범위와 갯수가 달라도 실행가능 BUT 리스트 개수가 달라짐
print(list_lang) # 결과 : ['JAVA', 'CPP', 'C#', 'Ruby']

# len() 함수
print(len(list_lang)) # 결과 : 4

# append() : 리스트 맨 뒤(제일 마지막 인덱스(-1))에 요소 추가
list_lang.append("Ruby")
print(list_lang) # 결과 : ['JAVA', 'CPP', 'C#', 'HTML', 'Ruby']

list_a = [1, 2, 3]
#list_lang.append(list_a) # 요소로 리스트가 추가됨
print(list_lang) # 결과 : ['JAVA', 'CPP', 'C#', 'HTML', 'Ruby', [1, 2, 3]]

# extend() : 리스트의 요소 하나하나를 추가
#list_lang.extend("JavaScript")
print(list_lang) # 결과 : ['JAVA', 'CPP', 'C#', 'HTML', 'Ruby', [1, 2, 3], 'J', 'a', 'v', 'a', 'S', 'c', 'r', 'i', 'p', 't']

# insert(index, data) : 리스트 특정 위치에 요소 추가
list_lang.insert(0, "R")
print(list_lang)

# pop() : 해당 위치의 데이터를 반환하고 리스트에서 제거
print(list_lang.pop(0))
print(list_lang)

# remove() : 해당 데이터를 리스트에서 제거
list_lang.remove("HTML")
print(list_lang)

# del
del list_lang[1]
print(list_lang)

# 정렬(숫자, 알파벳, 한글)
numbers = [100, 500, 60, 1, 20, 3450]

numbers.sort()               # 오름차순으로 정렬
print(numbers)

numbers.sort(reverse = True) # 내림차순으로 정렬
print(numbers)

numbers.reverse() # 리스트를 역순으로 변경
print(numbers)

# ASCII 활용 : ord, chr
# ord : 문자 -> 10진수 아스키 코드
print(ord("A"))
print(ord("a"))
print(ord("ㄱ"))
print(ord("ㅎ"))

# 10진수 아스키 코드 -> 문자
print(chr(65))
print(chr(97))
print(chr(12593))
print(chr(12622))

# in / not in
print(60 in numbers)            # True
print("JAVA" in list_lang)      # True
print(50 not in numbers)        # True
print("JAVA" not in list_lang)  # False

# 2차원 리스트
list2 = [[1, 2, 3], [10, 20, 30]];
print(list2)         # [[1, 2, 3], [10, 20, 30]]
print(list2[1][0])   # 10
print(list2[0][0:2]) # [1, 2]



####################################################################################
# tuple
# - immutable (수정불가)
####################################################################################

# list[] tuble()
members = (1, 2, 3, 4)
print(type(members)) # <class 'tuple'>

# 괄호 생략 가능
members = 1, 2, 3, 4
print(type(members)) # <class 'tuple'>

# 저장하려는 tubple 요소가 하나일 경우 ,를 꼭 넣어줘야 Tuple 타입으로 생성됨
members = (1)
print(type(members)) # <class 'int'>
members = (1,)
print(type(members)) # <class 'tuple'>
members = 1,
print(type(members)) # <class 'tuple'>

# 2차원 튜플
numbers = (1, 2, 3, (10, 20, 30))
print(numbers)

# index(data) : 요소의 위치 반환
numbers = (1, 2, 3)
print(numbers.index(2)) # 1

# Tuple[index] : 인덱스의 요소 값 반환
print(numbers[2]) # 3

# Unpacking
numbers = 1, 2, 3
number1, number2, number3 = numbers
print(number1, number2, number3)

# Unpacking 하려는 튜플의 원소의 개수 > 변수의 개수 일 경우
numbers = 1, 2, 3, 4
#number1, number2, number3 = numbers # ValueError: too many values to unpack (expected 3)

# 1) 더미변수 생성
number1, number2, number3, _ = numbers 
print(number1, number2, number3)

# 2) 남은 변수는 number3에 list로 생성
number1, number2, *number3 = numbers 
print(number1, number2, number3)

# 튜플에 값 추가(하는 것 처럼 보이게 만드는 방법)
# id() : 변수의 메모리 주소값
numbers = 1, 2, 3, 4
print(numbers, id(numbers)) # (1, 2, 3, 4) 2872403440352

numbers += 5, 6
print(numbers, id(numbers)) # (1, 2, 3, 4, 5, 6) 2872401749248



####################################################################################
# Dictionary
####################################################################################

# 딕셔너리 생성 및 요소 접근
people = {
    "name": "김개똥",
    "phone": "010-1234-5678"
}
print(people["name"], people["phone"]) # 김개똥 010-1234-5678

books = {"Daniel Pink":["파는 것이 인간이다", "언제 할 것인가"], "Eric Schidt":"새로운 디지털 시대"}
print(books["Daniel Pink"]) # ['파는 것이 인간이다', '언제 할 것인가']
print(books.get("Eric Schidt")) # 새로운 디지털 시대
print(books.keys()) # dict_keys(['Daniel Pink', 'Eric Schidt'])
print(books.values()) # dict_values([['파는 것이 인간이다', '언제 할 것인가'], '새로운 디지털 시대'])

# 1과 True 또는 0과 False를 동시에 키로 사용할 때 문제점
# 인덱스로 전달된 0/1을 True/False로 생각함
dic = {1:"One", True:"True"}
print(dic[1]) # True
dic = {0:"Zero", False:"False"}
print(dic[0]) # False

# 요소 값 변경
coffee = {"Java":2500, "Americano":2500, "Latte":3000}
coffee["Java"] = 3000
print(coffee["Java"]) # 3000

# 요소 추가
coffee["Mocha"] = 2000
print(coffee["Mocha"]) # 2000

# 요소 삭제
del coffee["Java"]
print(coffee) # {'Americano': 2500, 'Latte': 3000, 'Mocha': 2000}
coffee.pop("Mocha")
print(coffee) # {'Americano': 2500, 'Latte': 3000}

# items: (키, 값) 쌍이 Tuple로 이루어진 List 반환
print(coffee.items()) # dict_items([('Americano', 2500), ('Latte', 3000)])

# in / out
print("Latte" in coffee) # True
print("Latte" not in coffee) # False



####################################################################################
# Set(집합)
# - 순서 x , 중복 허용 x
# - 딕셔너리, 리스트 추가 불가능 (튜플 가능)
# - 1=True, 0=False 같은 요소로 취급
####################################################################################

# Set 생성
week = {"월요일", "화요일", "수요일", "목요일", "금요일", "토요일", "일요일"}
print(type(week)) # <class 'set'>
print(week) # {'수요일', '일요일', '월요일', '토요일', '화요일', '금요일', '목요일'}

# add() : 요소 추가
week.add("월요일") # 중복자료 허용 안함
print(week) # {'수요일', '일요일', '월요일', '토요일', '화요일', '금요일', '목요일'}

# update() : 튜플, 리스트, 딕셔너리(키값만)를 분해하여 요소 추가
week.add(("일주일",))
print(week) # {'수요일', '금요일', '목요일', '월요일', '일요일', ('일주일',), '토요일', '화요일'}
week.update(("일주일",))
print(week) # {'금요일', '목요일', '월요일', '화요일', '일요일', ('일주일',), '토요일', '수요일', '일주일'}
week.update(("일주일",))
print(week) # {'금요일', '목요일', '월요일', '화요일', '일요일', ('일주일',), '토요일', '수요일', '일주일'}

# set() : list로 set 만들기
week = set(["월", "화", "수", "목", "금", "토", "일", "월"])
print(week) # {'목', '금', '화', '수', '토', '월', '일'}

# 연산
a = {1, 2, 3, 4, 5}
b = {3, 4, 5, 6, 7}

# 합집합(|)
print(a|b) # {1, 2, 3, 4, 5, 6, 7}

# 교집합(&)
print(a&b) # {3, 4, 5}

# 차집합(-)
print(a-b) # {1, 2}

# 원소 삭제
a.remove(4)
print(a) # {1, 2, 3, 5}
a.remove(True) # True = 1
print(a) # {2, 3, 5}
 
