# South African Numbers

<h4> In questa repository si può trovare il risultato finale al test "South African Numbers" </h4>

***

<p> L'esercizio è stato sviluppato utilzzando due linguaggi di programmazione: Javascript per la parte di creazione di un semplice form per testare la correttezza di un dato numero sud-africano, Java per la generazione del file contenente i numeri corretti, del file contenente i numeri modificati e del file contenente i numeri errati. </p>

<p> Per la correzzione dei numeri si è deciso di utilizzare un test molto semplice facendo uso delle REGEX: </p>

![coorectable function](https://github.com/MinusLee/southAfricanNumbers/blob/master/correctable.png "REGEX per il programma Java")

![correctable function js](https://github.com/MinusLee/southAfricanNumbers/blob/master/js-correctable.png "REGEX per il programma JavaScript")

***

<p> Per quanto riguarda la creazione della form, oltre a JavaScript e HTML si è deciso di utilizzare Bulma come CSS framework. Il risultato è direttamente visualizzabile recandosi all'interno della cartella simple-html-page-sanumbers e aprire direttamente nel browser il file index.html. </p>

![anteprima form](https://github.com/MinusLee/southAfricanNumbers/blob/master/anteprima-form.png "Anteprima della pagina index.html")

***

<p> Il programma scritto in Java, invece, si occupa di fare un semplice parsing del file csv dato in input. A tal proposito sono state utlizzate due classi particolari: SouthAfricanNumbersFileParser, per la gestione del parsing, e SouthAfricanNumberHandler, per una gestione delle stringhe del file <em>orientata agli oggetti</em>. Sarebbe stato opportuno inserire quest'ultima come nested class di SouthAfricanNumbersFileParser, ma si è preferito inserirle all'interno di uno stesso package (sanchecker) e dichiarare il costruttore di SouthAfricanNumberHandler package-private. </p>

<p> Come richiesto è stato poi implementata una semplice classe di testing attraverso il framework open-source JUnit: </p>

![anteprima JUnit](https://github.com/MinusLee/southAfricanNumbers/blob/master/test.png "Classe Test con utilizzo del framework JUnit")

<p> Per poter avviare l'applicazione è poi necessario installare le varie dipendenze elencate nel file SouthAfricanNumbers.iml. A tal proposito si consiglia di utlizzare l'IDE IntelliJ IDEA Community, e successivamente scaricare una versione adeguata del JDK (questa mini applicazione è stata sviluppata con la versione 15 del JDK) e di JUnit. Attraverso l'IDE IntelliJ tutti questi passaggi sono automaticamente inferiti e richiesti automaticamente. </p>
