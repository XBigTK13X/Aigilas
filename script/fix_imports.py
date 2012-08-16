import os

impls = ['.impl','.behaviors','.animations']
targetPath = 'c:/_z/dev/git/aigilas/code/'
reactionsPath = targetPath + '/aigilas-ported/src/com/aigilas/skills/impl'

def isCodeFile(file):
	return '.java' in file

def fix_impl_imports(path):
	for root,dirs,files in os.walk(path):
		for file in files:
			if isCodeFile(file):
				print file
				source = os.path.join(root,file)
				result = os.path.join(root,file+"b")
				r = open(source,'r')
				w = open(result,'w')
				for line in r.read().splitlines():
					if 'package c' in line:
						line = line + "\rimport com.aigilas.statuses.StatusFactory;"
					w.write(line+'\r')
				w.close()
				r.close()
				os.remove(source)
				os.rename(result,source)

fix_impl_imports(reactionsPath)