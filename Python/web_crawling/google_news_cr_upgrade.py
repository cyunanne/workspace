# (개선) 웹페이지에 들어가지 않고 뉴스 검색, 기사제목, 본문, 링크, 게시날짜를 엑셀에 담아 저장
# (구) 뉴스 검색, 기사제목, 링크를 리스트에 담아서 출력
# padas : 데이터를 테이블 형식으로 출력하기 위한 라이브러리
import pandas as pd
import requests
from bs4 import BeautifulSoup

keyword = input("검색하고자 하는 키워드를 입력해주세요 : ")

r = requests.get("https://news.google.com/search?q=" + keyword + "&hl=ko&gl=KR&ceid=KR%3Ako")
bs = BeautifulSoup(r.text, "html.parser")

#titles = bs.select('div.xrnccd > article > h3 > a')
titles = bs.find_all("div", {"class":"xrnccd"})

news = []

for i in titles:
    title = i.find("h3").text
    link = "https://news.google.com" + i.find("a")["href"][1:]
    #desc = i.find("span", {"class":"xBbh9"}).text
    date = i.find("time")["datetime"][:11]

    news.append([title, link, date])
    google_news_df = pd.DataFrame(news, columns=["기사제목", "링크주소", "게시날짜"])

# pip install openpyxl
# 엑셀파일로 저장
google_news_df.to_excel("뉴스크롤링 결과("+keyword+").xlsx")
print("저장성공!")



