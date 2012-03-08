def reaction_factory_template(line):
	return "case ReactionId."+line.rstrip().upper().replace(" ","_")+": return new "+line.rstrip().replace(" ","")+"Reaction();\n"

def reaction_impl_template(line):
	return "public class "+line.rstrip().replace(" ","")+"Reaction:IReaction\n{\n\tpublic void Affect(ICreature target)\n\t{\n\t}\n}\n"

def reaction_dic_template(line):
	id,name = line.rstrip().split('_')
	return "{"+id+",ReactionId."+name.upper().replace(' ','_')+"},\n"

def stat_def_template(line):
	p,s,i,name,e,v = line.rstrip().split(' ')
	return name+",\n"
	
def stat_factory_template(line):
	name = line.rstrip().split(' ')[0]
	return "case Status."+name+": target.AddStatus(new "+name+"Status(target));break;\n"

def stat_impl_template(line):
	name = line.rstrip().split(' ')[0]
	return "public class "+name+"Status : IStatus\n{\n\tpublic "+name+"Status(ICreature target): base(false, false, target)\n\t{\n\t\tthrow new NotImplementedException();\n\t}\n}\n"

rFile = open("c:\z\in.txt","r")
oFile = open("c:\z\out.txt","w")	
c = 0
for line in rFile:
	oFile.write(stat_impl_template(line))
oFile.close()
rFile.close()
