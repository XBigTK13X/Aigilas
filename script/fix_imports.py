import os

impls = ['.impl','.behaviors','.animations']
targetPath = 'c:/_z/dev/git/aigilas/code/'

def isCodeFile(file):
	return '.java' in file

def fix_impl_imports(path):
	for root,dirs,files in os.walk(path):
		for dir in dirs:
			print dir
			fix_impl_imports(os.path.join(root,dir))
		for file in files:
			if isCodeFile(file):
				source = os.path.join(root,file)
				result = os.path.join(root,file+"b")
				r = open(source,'r')
				w = open(result,'w')
				for line in r.read().splitlines():
					for impl in impls:
						if impl in line and not 'package' in line:
							missing = line.split(' ')[1].replace(impl,'')
							line = line + '\r' + missing
					w.write(line+'\r')
				w.close()
				r.close()
				os.remove(source)
				os.rename(result,source)

fix_impl_imports(targetPath)