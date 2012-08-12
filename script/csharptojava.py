import os
import shutil
import re

start_path = "c:\\_z\\dev\\git\\aigilas"
convert_path = ".\\convert\\"


def isCodeOnly(file):
	exclude = ['.txt','AssemblyInfo','.csproj','.csv']
	for ex in exclude:
		if ex in file:
			return False
	if ".cs" in file:
		return True
	return False

traversed = []
def isCodeDir(dir):
	excludes = ['Debug','Release','TempPE','bin','obj','Properties','x86','script','concept','.git','SPX.Paths','port-workspace']
	excludes.append('SPX.Util')
	excludes.append('SPX.States')
	excludes.append('SPX.Core')
	excludes.append('SPX.Text')
	excludes.append('SPX.Particles')
	excludes.append('SPX.Entities')
	excludes.append('SPX.DevTools')
	excludes.append('SPX.Sprites')
	excludes.append('SPX.IO')
	for ex in excludes:
		if ex in dir:
			return False
	if dir in traversed:
		return False
	traversed.append(dir)
	return True

def cs2java(line):
	replacements = {'const':'static final','readonly ':'','string':'String','Dictionary':'HashMap', 'bool':'boolean','Int32':'Integer','this(':'initThis(','base(':'super(', '<int':'<Integer','int>':'Integer>','IList':'List','<float':'<Float','float>':'Float>','<boolean':'<Boolean','boolean>':'Boolean>','new List':'new ArrayList','base.':'super.','.Count()':'.length'}
	replacements['Aigilas.'] = 'com.aigilas.'
	replacements['SPX.'] = 'com.spx.'
	replacements['static class'] = 'class'
	replacements['ref '] = ''
	replacements['ContainsKey '] = 'containsKey'
	replacements['ToString '] = 'toString'
	replacements['virtual '] = ''
	replacements[' in '] = ':'
	replacements['foreach'] = 'for'
	replacements['.Contains'] = '.contains'
	replacements['.Add'] = '.add'
	replacements['.Remove'] = '.remove'
	replacements['RNG.Rand.Next'] = 'RNG.Next'
	replacements['.Length'] = '.length'
	replacements['boolean?'] = 'Boolean'
	replacements['int?'] = 'Integer'
	replacements['addRange'] = 'addAll'
	replacements['.Clear'] = '.clear'
	replacements['IEnumerable'] = 'List'
	replacements['ICollection'] = 'List'
	for key in replacements.keys():
		if key in line and (not replacements[key] in line or replacements[key] == '' or replacements[key] == 'class'):
			line = line.replace(key,replacements[key])
	if 'public' in line and 'get' in line:
		line = line.split('{')[0]+";\r"
	if 'Math.' in line and not '.PI' in line:
		for kill in [m.start() for m in re.finditer('Math.', line)]:
			killLoc = kill + 5
			l = list(line)
			l[killLoc] = line[killLoc].lower()
			line = "".join(l)
	if "override" in line:
		line = line.replace('override','')
		line = '@Override\r' + line		
	return line

# Phases
#   0 - Determine the namespace and prepare a location for the converted file
#   1 - Reorganize namespace and usings to match package and import format
#   2 - Simple replacement of keywords that map between c# and java
#   3 - Simple movement of method calls

def transform(path):
	for root,dirs,files in os.walk(path):
		if isCodeDir(root):
			print root
			for dir in dirs:
				transform(os.path.join(root,dir))
			for file in files:
				if isCodeOnly(file):
					phase = 0
					namespace = ""
					package = ""
					convert_file = ""
					while phase < 4:
						read = open(os.path.join(root,file),"r")

						if phase == 0:
							for line in read:
								if "namespace" in line:
									namespace = line.split(' ')[1].lower().replace("ogur",'aigilas')

						if phase == 1:
							lib = ""
							lib2 = ""
							lib = namespace.split('.')[0].rstrip().lower()
							if len(namespace.split('.')) > 1:							
								lib2 = namespace.split('.')[1].rstrip().lower()
							package = os.path.join(os.path.join(os.path.join(convert_path,"com"),lib),lib2)
							if not os.path.exists(package):
								os.makedirs(package)
							convert_file = os.path.join(package,file.replace('.cs','.java'))

							w = open(convert_file,'w')
							w.write("package "+package.replace('\\','.').replace("..convert.",'')+';\r')
							w.write('import com.xna.wrapper.*;\r')
							w.write('import java.util.*;\r')
							braceCount = 0
							firstBraceFound = False
							for line in read:
								if '{' in line:
									braceCount += 1
								if '}' in line:
									braceCount -= 1
								if "using " in line:
									using = line.lower().replace("using ",'')
									if "SPX" in line:
										using = 'import ' + using.replace('spx','com.spx').replace(';','').rstrip() + ".*;\r"
										w.write(using)
									elif "OGUR" in line or "Aigilas" in line:
										using = 'import ' + using.replace('aigilas','com.aigilas').replace(';','').rstrip() + ".*;\r"
										w.write(using)
								else:
									excludes = ['namespace']
									allow = True
									for ex in excludes:
										if ex in line:
											allow = False
									if allow:
										if not firstBraceFound and braceCount > 0:
											firstBraceFound = True
										elif firstBraceFound and braceCount == 0:
											continue
										elif "#" in line:
											continue
										else:
											w.write(line)
							w.close()

						if phase == 2:		
							shutil.copyfile(convert_file,convert_file+'b')
							cop = open(convert_file+'b','r')					
							w = open(convert_file,'w')
							for line in cop:
								w.write(cs2java(line))
							w.close()
							cop.close()
							os.remove(convert_file+'b')
						read.close()
						phase = phase + 1
						
						if phase == 3:
							shutil.copyfile(convert_file,convert_file+'b')
							cop = open(convert_file+'b','r')					
							w = open(convert_file,'w')
							superCall = '';
							for line in cop:
								if superCall != '' and "{" in line:
									line = "{\r" + superCall.rstrip() + ';\r'
									superCall = ''
								if ":" in line:
									if 'super' in line:
										line,superCall = line.split(':')
									if 'class' in line:
										line = line.replace(":",' extends ')
								w.write(cs2java(line))
							w.close()
							cop.close()
							os.remove(convert_file+'b')

if os.path.exists(convert_path):		
	shutil.rmtree(os.path.join(convert_path,''))
transform(start_path)

