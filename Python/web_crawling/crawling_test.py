# requests, BeautifulSoup : 라이브러리..
# pip install requests : 웹페이지 정보 긁어오는 라이브러리
# pip install BeautifulSoup : 긁어온 정보를 파싱해주는 라이브러리

import requests
from bs4 import BeautifulSoup

r = requests.get("https://www.naver.com")
bs = BeautifulSoup(r.content, "html.parser")

#print(bs)

h3 = bs.select("h3") # h3이라는 요소만 골라서 리스트로 출력
print(h3[1].text) # 리스트이므로 하나를 특정해줘야 함

h3 = bs.select_one("h3") # h3 요소 중에 제일 처음 요소만 출력
print(h3.text) # 태그 사이에 텍스트만 반환
print(h3.name) # 태그 이름 반환
print(h3.attrs) # 태그 속성 반환

# 자식요소가 필요할 때
h3 = bs.select_one("h3 > a") # h3 요소 안에 있는 자식 요소 a 선택
print(h3) # 결과 : <a href="https://www.naver.com/NOTICE">공지사항</a>

# 속성 탐색
selector = bs.select_one("div.current_box") # div 속성의 class=current_box 인 첫번째 요소 반환
print(selector)

selector = bs.select(".title") # 속성 상관 없이 class=title 인 모든 요소 반환
print(selector)

selector = bs.select("div#u_skip") # div 속성의 id=u_skip 인 요소 출력
selector = bs.select("#u_skip") # id=u_skip인 요소 출력(id는 유일하므로 속성 없어도 검색 가능)
print(selector)

# find(), find_all() : 태그명, 속성명, 값으로 요소 찾기
#selector = bs.find_all("태그명", {"속성명":"값"})
selector = bs.find_all("div", {"class":"partner_box"}) # 모두 찾기
selector = bs.find("div", {"class":"partner_box"}) # 최초의 하나만 찾기
print(selector)