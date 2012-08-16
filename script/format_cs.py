import shutil,os

w = open("formatted.cs",'w')
for line in open("raw_skills.cs",'r').read().splitlines():
	w.write(line.replace(';',';\r').replace('{','\r{\r').replace('}','\r}\r'))
w.close()