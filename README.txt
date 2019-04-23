Fazakas Alexandru
321CC


1. Implementarea temei

Toate task-urile temei au fost realizate. Totul porneste de la clasa Authentication.
Aceasta deschide o fereastra continand un JTextField si un JPasswordField, 2 label-uri
si un buton de login. __Credentialele de logare__ sunt "student" cu parola "student".
Odata logati, va fi deschis meniul principal continand butoanele de "Administratie",
"Load Data" si "Statistici". 
Apasand butonul "load data", vom incarca continuturile fisierelor in memorie 
si vom obtine fisierul de output. Acesta devine inactiv dupa
ce a fost apasat. Butonul de "Administrare" va deschide un meniu continand un
JList(creat peste un DefaultListModel) continand produsele. Avem la dispozitie butoanele
de "Sort", "Adauga produs", "Sterge produs", "Edit produs", "Search" si 2 radio buttons
corespunzatoare modului in care sortam produsele.
Odata apasat butonul "Adauga produs", se deschide o fereastra care cere date
pentru noul produs. Se testeaza* daca exista deja acest produs nou si se adauga
in caz negativ.
Butonul "Sterge produs" va sterge din lista elementul curent selectat si va dezactiva
butonul daca tocmai a golit lista.
Butonul "Edit produs", in mod similar, editeaza produsul curent selectat stergandu-l
si adaugandu-l la loc cu noile informatii.
Butonul de "Sort" va sorta lista de produse in functie de obtiunea aleasa din cele 2
butoane "Alfabetic" sau "Dupa tara".
Pentru a cauta un produs, avem butonul de "Search" care accepta un String printr-un
JTextField si cauta case-insensitive toate produsele care contin in nume datele
introduse.
Inapoi in meniul principal, butonul de "Statistici" deschide o fereastra continand
toate informatiile cerute la acest subpunct sub forma unui text pus intr-un
JTextArea needitabil.

* mi-am definit propria metoda pentru cautarea produsului in lista, deoarece
Vector.contains nu parea sa functioneze mereu.

2. Timpul alocat temei si dificultatea temei

Acestei teme cred ca am alocat undeva la 36 de ore. Dificultatea a fost una moderata,
destul de potrivita (in opinia mea) cat sa ne oblige sa invatam/incercam chestii noi
dar nu atat de grea cat sa nu putem rezolva intr-o fereastra de timp rezonabila.
Ca dificultate, as spune ca este un 6/10.

3. Feedback

Consider ca a fost o tema destul de interesanta, imbinand putin atat din elementele
de "backend" cat si din cele de "frontend".
Singura remarca negativa pe care as vrea sa o fac ar fi neclaritatea exprimarii
din cerinta(probleme rezolvate destul de rapid pe forum, totusi).
Overall, o experienta placuta.