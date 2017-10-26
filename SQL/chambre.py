
for i in range(100):
	if i < 50:
		cat = 2
	elif i < 75:
		cat = 1
	else:
		cat = 3

	print("INSERT INTO IHM_Chambre VALUES("+str(i+1)+","+str(i+1)+","+str(cat)+");")