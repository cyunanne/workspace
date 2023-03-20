from datetime import datetime, timedelta, date, time

# 현재시각
print(datetime.now()) # 2023-03-20 08:57:42.475170
print(datetime(2002, 10, 20, 14, 5, 2)) # 2002-10-20 14:05:02
 
# 특정날짜 지정
new_date = date(1993, 6, 3)
print(new_date) # 1993-06-03

# 특정시각 지정
print(time(9)) # 09:00:00
print(time(9, 5, 5)) # 09:05:05
print(time(9, 5, 5, 2222)) # 09:05:05.002222

# 날짜연산
today = datetime.now()
print(today - timedelta(days=10)) # 오늘부터 10일 전 날짜 출력
print(today + timedelta(days=10)) # 오늘부터 10일 후 날짜 출력

##############################################################

from time import localtime, strftime

print(localtime())