weather = "맑음"
temperature = 20

print("오늘의 날씨는", weather, "기온은", temperature, "도 입니다.")
print("오늘의 날씨는 %s 기온은 %d 도 입니다."%(weather, temperature))   # 권장(X)
print("오늘의 날씨는 {} 기온은 {} 도 입니다.".format(weather, temperature))

chance_shower = 33.5

print("오늘의 날씨는 %s 기온은 %d 도 비가 내릴 확률은 %.1f%% 입니다."%(weather, temperature, chance_shower))
print("오늘의 날씨는 {} 기온은 {} 도 비가 내릴 확률은 {}% 입니다.".format(weather, temperature, chance_shower)) # 자료형에 따른 포맷 지정 필요 없음
print("오늘의 날씨는 {0} 기온은 {2} 도 비가 내릴 확률은 {1}% 입니다.".format(weather, temperature, chance_shower)) # 출력 순서 바꾸기

print("{}, {}".format(weather, temperature))
print("{:10}, {:10}".format(weather, temperature)) # 출력 자리수 10칸 설정 (문자열은 왼쪽 정렬, 숫자는 오른쪽 정렬이 기본값)
print("{:10s}, {:10d}".format(weather, temperature)) # 자리수 + 출력 포맷 지정 (문자열:s, 정수:d)
print("{0:s}, {1:d}, {1:f}, {1:o}, {1:x}".format(weather, temperature)) # 출력 순서 + 포맷 지정 (문자열:s, 정수:d, 실수:f, 8진수:o, 16진수:x)
print("{0:}, {1:d}, {1:f}, {1:o}, {1:x}".format(weather, temperature)) # 문자열 포맷(s) 생략 가능

# 정렬 : <>^
left = "left"
right = "right"
middle = "middle"

print("({2:>10}), ({1:^10s}), ({0:<10s})".format(left, middle, right))
print("({2:!>10}), ({1:@^10s}), ({0:#<10s})".format(left, middle, right)) # 공백에 문자 채우기(!, @, *)
print("({:!>10.3})".format(right)) # 10.3 : 10칸 내에서 출력하되, 값을 3자리만 출력

# f-string : 속도가 빠르고 편리하지만, 파이썬3.6버전 이상부터 지원
print(f"오늘의 날씨는 {weather}이며, 기온은 {temperature} 도 입니다.") # 대문자 F도 가능하지만 권장은 소문자 f
print(f"2 곱하기 30의 결과값 = {2*30}") # 수식의 결과값고 출력할 수 있음