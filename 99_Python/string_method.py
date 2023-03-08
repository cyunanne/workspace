text = "www.GOOGLE.com"

print(len(text)) # len(문자열) : 문자열의 길이 반환

print(text.capitalize()) # 첫번째 글자는 대문자, 나머지 문자는 소문자로 변경 / 결과 : Www.google.com
txt_upper = text.upper() # 문자열 전체를 대문자로 변경
txt_lower = text.lower() # 문자열 전체를 소문자로 변경
print(txt_upper, txt_lower)

g_cnt = text.count('G') # 결과 : 2