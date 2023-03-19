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
list_lang.append(list_a) # 요소로 리스트가 추가됨
print(list_lang) # 결과 : ['JAVA', 'CPP', 'C#', 'HTML', 'Ruby', [1, 2, 3]]

# extend() : 리스트의 요소 하나하나를 추가
list_lang.extend("JavaScript")
print(list_lang) # 결과 : ['JAVA', 'CPP', 'C#', 'HTML', 'Ruby', [1, 2, 3], 'J', 'a', 'v', 'a', 'S', 'c', 'r', 'i', 'p', 't']

# insert()