# "index"
#  01234 -- index number
print("index"[0]) # i
print("index"[1]) # n
print("index"[2]) # d
print("index"[3]) # e
print("index"[4]) # x

# 음수 인덱스: 맨 뒤에서부터 출력
print("print"[-5]) # p
print("print"[-4]) # r
print("print"[-3]) # i
print("print"[-2]) # n
print("print"[-1]) # t

# String index
str_ = "String index"

print(str_[0])
print(str_[1])
print(str_[2])
print(str_[3])
print(str_[4])
print(str_[5])
print(str_[6])
print(str_[7])
print(str_[8])
print(str_[9])
print(str_[10])
print(str_[11])
#print(str_[12]) # 인덱스 범위 초과 -> IndexError: string index out of range

# 문자열 slicing
# str[m:n] n번 째 문자는 포함하지 않음

str_slice = "0123456789"

print(str_slice[0:7])   # 결과: 0123456
print(str_slice[0:])    # 결과: 0123456789 (끝까지 출력)
print(str_slice[:10])   # 결과: 0123456789 (처음부터 출력)
print(str_slice[:])     # 결과: 0123456789 (전체출력)
print(str_slice[-8:-1]) # 결과: 2345678 ([-1]=9 미포함)
print(str_slice[:])     # 결과: 0123456789 (전체출력)
print(str_slice[:])     # 결과: 0123456789 (전체출력)
print(str_slice[:])     # 결과: 0123456789 (전체출력)

str_slice2 = "Python is easy"

print(str_slice2[-14:]) # 결과: Python is easy (전체출력)
print(str_slice2[:100]) # 결과: Python is easy (전체출력)

# 문자열 step

str_slice3 = "0123456789"

print(str_slice3[0:10:2])  # 결과: 02468
print(str_slice3[::-3])    # 결과: 9630
print(str_slice3[9::-3])   # 결과: 9630
print(str_slice3[-1::-3])  # 결과: 9630

str_slice4 = "Python"

print(str_slice4[0:] + str_slice4[::-1]) # PythonnohtyP
print(str_slice4[1:5] + str_slice4[0] + str_slice4[5]) # ythoPn