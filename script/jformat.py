import os,shutil,re,sys

start_path = '.'

def isCodeOnly(file):
	if ".java" in file:
		return True
	return False

def isMethod(line,source):
	ism = False
	sigs = ['public','private','static']
	for sig in sigs:
		if sig in line:
			ism = True
	if not ism:
		return False

	excludes = ['=']
	for ex in excludes:
		if ex in line:
			return False

	includes = ['(']
	for inc in includes:
		if not inc in line:
			return False

	segm = line[0:line.index('(')].lstrip()
	name = segm.split(' ')[len(segm.split(' '))-1]

	if name == source.replace('.java',''):
		return False
	
	if not name in methods:
		methods.append(name)
	return True

camel = lambda s: s[:1].lower() + s[1:] if s else ''

def fixCase(line):
	for method in methods:
		if method in line:
			line = line.replace(method,camel(method))		
	return line	

methods = []
def transform(path):
	for root,dirs,files in os.walk(path):
		for fil in files:
			if isCodeOnly(fil):
				for line in open(os.path.join(root,fil),"r").read().splitlines():
					isMethod(line,fil)
	methods.sort(lambda x,y:cmp(len(x),len(y)))
	methods.reverse()
	
	for root,dirs,files in os.walk(path):
		for file in files:
			if isCodeOnly(file):	
				convert_file = os.path.join(root,file)
				target_file = convert_file+'b'
				shutil.copyfile(convert_file,target_file)				
				w = open(convert_file,'w')
				for line in open(target_file,'r').read().splitlines():
					res = fixCase(line)
					w.write(res+'\r')
				w.close()
				os.remove(target_file)
						

if 'method' in sys.argv[1]:
	print '== Converting method names =='
	transform(start_path)
else:
	print 'Unrecognized option: ' + sys.argv[0]



