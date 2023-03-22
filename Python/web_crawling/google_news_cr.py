# (구) 뉴스 검색, 기사제목, 링크를 리스트에 담아서 출력
# padas : 데이터를 테이블 형식으로 출력하기 위한 라이브러리
import pandas as pd
import requests
from bs4 import BeautifulSoup

r = requests.get('https://news.google.com/search?q=%ED%95%98%EB%82%A8%EC%8B%9C&hl=ko&gl=KR&ceid=KR%3Ako')
bs = BeautifulSoup(r.text, "html.parser")

#c-wiz > div > div > div > 
titles = bs.select('div.xrnccd > article > h3 > a')

news = []

for i in titles:
    title = i.text
    link = "https://news.google.com" + i.get("href")[1:]

    news.append([title, link])
    google_news_df = pd.DataFrame(news, columns=["기사제목", "링크주소"])

# pip install openpyxl
# 엑셀파일로 저장
google_news_df.to_excel("뉴스크롤링 결과.xlsx")
print("저장성공!")



