# -*- coding:utf-8 -*-


import MySQLdb
from bs4 import BeautifulSoup
import urllib2
import time

conn= MySQLdb.connect(
        host='127.0.0.1',
        port = 3306,
        user='root',
        passwd='09388296',
        db ='crawler',
	charset='utf8'
        )
cur = conn.cursor()


url="http://id.8684.cn/"
req_header = {
'User-Agent':'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36',
'Accept':'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8',
#'Accept-Encoding':'gzip,deflate,sdch',
'Accept-Language':'zh-CN,zh;q=0.8,en;q=0.6',
'Connection':'keep-alive',
'Referer':url,
'Host':'id.8684.cn'
}
req_timeout = 60
req = urllib2.Request(url,None,req_header)


count=0
while True:
	count+=1
	print "count:",count
	time.sleep(1)
	resp = urllib2.urlopen(req,None,req_timeout)
	html = resp.read()
	soup = BeautifulSoup(html)

	print "man:"
	mans_html = soup.find_all(id="msfz")
	man_list = mans_html[0].find_all("li")
	for man_li in man_list:
		name_num = man_li.text.split()
		print name_num[0],name_num[1]
		sql="replace into  chinese_name_gender (id_card_num,name,gender) values ('{0}','{1}','male')".format(name_num[1],name_num[0].encode('utf-8'))
		#print sql
		cur.execute(sql)


	print "woman:"
	woman_html = soup.find_all(id="wsfz")
	woman_list = woman_html[0].find_all("li")
	for woman_li in woman_list:
		name_num = woman_li.text.split()
		sql="replace into  chinese_name_gender (id_card_num,name,gender) values ('{0}','{1}','female')".format(name_num[1],name_num[0].encode('utf-8'))
		print name_num[0],name_num[1]
		cur.execute(sql)	
	conn.commit()

cur.close()
conn.close()



