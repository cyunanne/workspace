# while 조건식:

###############################################################################

# range(n, m) : n부터 m-1까지 숫자 생성
numbers = list(range(0, 10))
print(numbers)
numbers = list(range(-10, 0, 2))
print(numbers)

###############################################################################

# for 변수 in 나열_가능한_자료:
for i in range(1, 10+1):
    print(i)

for i in "일이삼사오":
    print(i)

# enumerate()
fruits = ["사과", "딸기", "바나나"]
for i in enumerate(fruits):
    print(f"{i[0]+1}번째 과일은 {i[1]}입니다.")
for i, j in enumerate(fruits):
    print(f"{i+1}번째 과일은 {j}입니다.")
# 결과
# (0, '사과')
# (1, '딸기')
# (2, '바나나')

# 중첩포문
list_2nd = [[1, 2, 3],["a", "b", "c"]]

for i in list_2nd:
    for j in i:
        print(j)

# 구구단
for i in range(1, 10):
    for j in range(2, 10):
        print(f"{j} X {i} = {i*j}", end = '\t')
    print()