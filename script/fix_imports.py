import os

impls = ['.impl','.behaviors','.animations']
targetPath = 'c:/_z/dev/git/aigilas/'

def fix_impl_imports(path):
	for root,dirs,files in os.walk(path):
		for dir in dirs:
			fix_impl_imports(os.path.join(root,dir))
		for file in files:
			source = os.join.path(root,file)
			result = os.join.path(root,file+"b")
			r = open(source,'r')
			w = open(result,'w')
			for line in r.read().splitlines():
				for impl in impls:
					if impl in line:
						missing = line.split(' ')[1].replace(impl,'')
						line = line + '\r' + missing
				w.write(line)
			w.close()
			r.close()
			os.remove(source)
			os.rename(result,source)

fix_impl_imports(targetPath)