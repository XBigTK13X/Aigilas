import os,shutil

newImport = 'import com.badlogic.gdx.graphics.Texture;'

def fix(path):
    for root,dirs,files in os.walk(path):
        for file in files:
            if ".java" in file:
                convert_file = os.path.join(root,file)
                print convert_file
                shutil.copyfile(convert_file,convert_file+'b')
                w = open(convert_file,'w')
                for line in open(convert_file+'b','r').read().splitlines():
                    if 'package ' in line:
			line = line + '\r '+newImport + ' \r'
                    w.write(line+'\r')
                w.close()
                os.remove(convert_file+'b')

fix('/home/kretst/dev/aigilas/code')
