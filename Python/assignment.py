file = open("sample02.txt", 'r')
myfile2 = open("result02.txt", 'w')

while True:
    line =  file.readline().strip()
    if not line: break
    
    person = line.split(",")
    data = "{}/{}/".format(person[0], person[1])
    if int(person[1]) >= 19:
        data += "성인\n"
    else:
        data += "미성년\n"
    myfile2.write(data)

file.close()
myfile2.close()