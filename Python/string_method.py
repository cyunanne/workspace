text = "         www.GOOGLE.com"

print(len(text)) # len(문자열) : 문자열의 길이 반환

print(text.capitalize()) # 첫번째 글자는 대문자, 나머지 문자는 소문자로 변경 / 결과 : Www.google.com
txt_upper = text.upper() # 문자열 전체를 대문자로 변경
txt_lower = text.lower() # 문자열 전체를 소문자로 변경
print(txt_upper, txt_lower)

g_cnt = text.count('G') # 결과 : 2
print(g_cnt)

# find() = idx()
g_find = text.find('G')     # 결과 : 4
print(g_find)
g_find = text.find('G', 5)  # 5번 인덱스부터 검색 시작 / 결과 : 7
print(g_find)

# 차이점
g_find = text.find('X')      # 문자열에 없는 값 검색 / 결과 : -1
#g_idx = text.index('X')     # 문자열에 없는 값 검색 / 결과 : ValueError: substring not found

# 끝에서부터 검색 : rfind(), ridx()
g_find = text.rfind('G')    # 결과 : 7
g_idx = text.rindex('G')    # 결과 : 7
print(g_find, g_idx)

# 문자열 치환 : replace()
text_naver = text.replace("GOOGLE", "NAVER")
print(text_naver)

# 문자열 분할 : split() ... 기본값 : ' '
print(text.split())     # 결과 : ['www.GOOGLE.com']
print(text.split('.'))  # 결과 : ['www', 'GOOGLE', 'com']
print(text.split('OO')) # 결과 : ['www.G', 'GLE.com']

# 문자열 앞뒤 공백 제거 : strip()
# 문자열 앞(left)/뒤(right) 공백 제거 : lstrip() / rstrip()
print(text)
print(text.strip())