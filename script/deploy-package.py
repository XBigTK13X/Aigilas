import requests, json, os, sys, collections, getpass

url = 'https://api.github.com/repos/XBigTK13X/Aigilas/downloads'
username = "XBigTK13X"
password = ""
def deleteOld():
	print "=== Delete any previous uploads"
	r = requests.get(url, auth=(username, password))
	if(len(r.json) > 0):
		dID =  r.json[0]['id']
		requests.delete(url + '/' + str(dID) , auth=(username , password))
def createNewHandle():
	print "=== Create a new GitHub Download"
	size = os.path.getsize('aigilas/target/aigilas.zip')
	payload = {'name':'aigilas.zip','size':size,'description':'Alpha development snapshot of Aigilas','content-type':'text/plain'}
	created = requests.post(url,auth=(username,password),data=json.dumps(payload))
	return created.json

def uploadFile(res):
	print "=== Use GHD handle to upload file to S3"
	p = collections.OrderedDict()
	p['key'] = res['path']
	p['acl'] = res['acl']
	p['success_action_status'] = '201'
	p['Filename'] = res['name']
	p['AWSAccessKeyId'] = res['accesskeyid']
	p['Policy'] = res['policy']
	p['Signature'] = res['signature']
	p['Content-Type'] = res['mime_type']
	p['file'] = '@aigilas.zip'

	cmd = 'cd /home/kretst/dev/aigilas/aigilas/target/ && curl'
	for key in p.keys():
		cmd = cmd + ' -F ' + str(key) + '=' + str(p[key])
	cmd += ' https://github.s3.amazonaws.com/'
	os.system(cmd)
	print ""

def update_package():
	deleteOld()	
	response = createNewHandle()
	uploadFile(response)

password = getpass.getpass()
update_package()
