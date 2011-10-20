e = ['fire','water','earth','air','light','dark','physical','mental']
csv = {}

def elem(i,j,k):
	if i == -1:
		return e[j-1]+','+e[k-1]
	else:
		return e[i-1]+','+e[j-1]+','+e[k-1]

def double(i,j):
	o = sorted([i,j])
	return o[0]*10+o[1],elem(-1,o[0],o[1])

def triplet(i,j,k):
	o = sorted([i,j,k])
	return o[0]*100+o[1]*10+o[2],elem(o[0],o[1],o[2])

def format(tuple):
	return str(tuple)

for ii in xrange(1,9,1):
	for jj in xrange(1,9,1):
		if ii != jj:
			dub,rep = double(ii,jj)
			if not dub in csv.keys():
				csv[dub] = format(dub) + ','+rep
for key in csv:
	print csv[key]
csv = {}	
for ii in xrange(1,9,1):
	for jj in xrange(1,9,1):
		for kk in xrange(1,9,1):
			if ii != jj and ii != kk and jj != kk:
				trip,rep = triplet(ii,jj,kk)
				if not trip in csv:
					csv[trip] = format(trip) +','+ rep

for key in csv:
	print csv[key]