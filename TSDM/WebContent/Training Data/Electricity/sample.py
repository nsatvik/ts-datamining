'''
Created on Jan 25, 2013

@author: nsatvik
'''
import re
f = open('ElectricityDemand.txt')

data = f.read()
values = re.findall(r'([\d]+)', data)

fout = open('electricity-demand.txt','w')
for value in values:
    print value
    fout.write(value+'\n')
fout.close()
f.close()


