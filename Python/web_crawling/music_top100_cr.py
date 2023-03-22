from selenium import webdriver
from bs4 import BeautifulSoup

driver = webdriver.Chrome('C:\Webdriver\chromedriver.exe')
url = 'https://vibe.naver.com/chart/total'
res = driver.get(url)
html = driver.page_source
bs = BeautifulSoup(html, 'html.parser')

ss = bs.select('td.song > div.title_badge_wrap > span.inner_cell > a')
aa = bs.select('td.song > div > span > span > a > span.text')
#test = bs.find_all('p')

#print('0 : ', url)
#print('1 : ', song)
#print('2 : ', test)

songs = []
artists = []

for s in ss:
    songs.append(s.text)
for a in aa:
    artists.append(a.text)

for i in range(len(songs)):
    print(f"{i+1}위 : {artists[i]} - {songs[i]}")