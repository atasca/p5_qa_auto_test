# p5_qa_auto_test
---------------------------------------------------------------------------------------------------------------------------------------------
BD errors :

	Visits :
		coloanele visit_date si description nu trebuie sa fie nule
	Pets : 
		coloanele birth_date si name nu trebuie sa fie nule
	Owners:
		coloanele last_name, first_name, address, city nu trebuie sa fie nule
	Specialties si Types:
		coloana name nu trebuie sa fie nula
	Types:
		coloanele last_name, first_name, address, city nu trebuie sa fie nule
	vets:
		coloanele last_name, first_name nu trebuie sa fie nule

---------------------------------------------------------------------------------------------------------------------------------------------
Endpoint Bugs & stufs:

	Mai toate endpoints:
	"Daca dam body pe care il vede bun cu orice id va returna status code 201 cu body de raspuns unde primim id-ul dat de noi, dar nu este cel dim bd"

	PetType si Specialty:
		"Minimum body expected: 
			{
				"name":"something"
			}"
		 "Minimum body actual: 
			{
				"name":"something"
			}"
	Vet:
		"Minimum body de create expected: 
				"{
			  "firstName": "string",
			  "lastName": "string",
			  "specialties": [
				{
				  "id": 1
				}
			  ]
			}"
		"Minimum body de create actual 
				"{
			  "firstName": "string",
			  "lastName": "string",
			  "specialties": [
				{
				  "id": 1
				}
			  ]
			}"
		Bugs: 
			"Daca dam specialties cu id :x putem pune orice nume. Nu verifica corelanta intre specialties.id si specialties.name "


	Pets:
		Errors:
		"Restassured get Pet path -> 400 status code. In swagger returneaza."
		"Restassured delete Pet path id -> 400 status code. In swagger returneaza."

		Bugs : 
		"Desi id este autoincrement suntem nu se poate face post fara un id"
		"Daca dam body pe care il vede bun cu orice id in afara de 0 ne da 201 dar nu creaza nimic, nu conteaza daca exista sau nu id"
		"Daca dam body pe care il vede bun cu id 0 se creeaza in baza de date un nou pet cu id autoincrement, nu 0, raspunsul aratand
		 cu id corect 201"
		 "Body minim de create vazut ca si corect : {
				  "birthDate": "2021/09/14",
				  "id": 1,
				  "name": "string",
				  "owner": {
					"address": "",
					"city": "",
					"firstName": "",
					"id": 1,
					"lastName": "",
					"telephone": ""
				  },
				  "type": {
					"id": 1
				  }
				 
				}"
		 "Body minim de create considerat corect de catre 'client' : 
			{
			"birthDate": "2021/09/14",
			"name": "string",
			"owner": {
				"id": 1,
			},
			"type": {
				"id": 1
			}	 
			}"
		"acelasi lucru legat de body si la update" 
		"Ca si la Vet, ii pasa doar sa aibe in body toate campurile la Owner, nu conteaza daca e inexistent atat timp cat se pune un id existent"
			
	Owner:
		Bugs:
			"Body minim asteptat : 
				{
				"address": "string",
				"city": "string",
				"firstName": "string",
				"lastName": "string",
				"pets": [
					{
					"id": 0
					},...
				  ],
				  "telephone": "38819923"
				}
				
				
			Body minim momentan : 
				{
				"address": "string",
				"city": "string",
				"firstName": "string",
				"lastName": "string",
				"pets": [
					{
					"birthDate": "2021/09/15",
					"id": 3,
					"name": "string"
					}
				  ],
				  "telephone": "38819923"
				}
				"
	Visits: 
		Bugs :
			"Nu este bine implementat in swagger -> status 400 field "id" is too ambiguous"
			
			Body minim asteptat : 
				{
				"date": "yyyy/MM/dd",
				"description": "string",
				"pet": {
					"id": 1
					},
					"type": {
						"id": 1
					}
				}
			Body minim momentan : 
				{
				"date": "2021/02/02",
				"description": "string",
				"id": 0,
				"pet": {
					"birthDate": "2021/02/02",
					"id": 2,
					"name": "string",
					"owner": {
						"address": "string",
						"city": "string",
						"firstName": "string",
						"id": 1,
						"lastName": "string",
						"telephone": "string"
					},
					"type": {
						"id": 1
					}
				  }
				}
