# wumpus4ever
Automatically exported from code.google.com/p/wumpus4ever

Die Wumpus-Welt

Ein Agent erforscht eine Höhle auf der Suche nach einem Goldschatz. Die Höhle besteht aus mehreren miteinander verbundenen Räumen. Irgendwo in der Höhle lauert der Wumpus, ein Ungeheuer, das jeden frisst, der in seine Nähe kommt. Obendrein gibt es Fallgruben (PITs), in die der Agent stürzen kann. Die Höhle besteht aus 4x4 Feldern (Räumen). Auf dem Feld, auf dem sich der Wumpus befindet, und in den unmittelbar benachbarten Feldern nimmt man einen unangenehmen Geruch wahr (Stench). Auf Feldern, die unmittelbar neben einer Fallgrube liegen, spürt man einen Luftzug (Breeze).
Ziel

Hole das Gold ohne in eines der Löcher zu fallen ohne auf den Wumpus zu stoßen.

    Das Gold erkennt man am Glitzern (Glitter)
    Falls der Agent gegen eine Wand läuft, spürt er einen Stoß
    Der Agent besitzt (genau) einen Pfeil, mit dem er den Wumpus töten kann
    Wird der Wumpus getötet, so ist sein Todesschrei überall in der Höhle zu hören
    Der Agent stirbt, wenn er in eine Fallgrube fällt oder dem lebenden Wum-pus begegnet
    Perzeptionen werden als 5-Tupel dargestellt
    z.B. Geruch, Luftzug, Glitzern, kein Stoß, kein Schrei
    [Stench, Breeze, Glitter, None, None] 

Die Regeln

    Gold +1000, gefressen -1000
    1 pro step, -10 für Pfeilschuss
    Quadrate in direkter Nachbarschaft zum Wumpus sind “smelly”
    Quadrate in direkter Nachbarschaft zu Pits sind “breezy”
    Quadrate in direkter Nachbarschaft zum Gold sind "glitter"
    Pfeilschuss tötet den Wumpus bei direktem Gegenüberstehen
    Nur ein Pfeil steht zur Verfügung 

Sensoren

Der Agent hat 5 Sensoren, die ihm unterschiedliche Informationen bereitstellen:

    In Feldern direkt neben dem Wumpus (nicht diagonal) nimmt der Agent einen üblen Geruch wahr (Stench)
    In Feldern direkt neben einer Falltür (nicht diagonal) spürt der Agent einen Luftzug (Breeze)
    In Feldern direkt neben dem Gold (nicht diagonal) nimmt der Agent ein Glitzern wahr (Glitter)
    Läuft der Agent gegen eine Wand, so nimmt er einen Stoß wahr (Bump)
    Wenn das Wumpus getötet wird, stößt es einen Schrei aus (Scream), den jeder in der Höhle hören kann 

Aktuatoren

    Nach oben gehen
    Nach unten gehen
    Nach links gehen
    Nach rechts gehen
    Einen Pfeil abschießen
    Das Gold aufheben 

Ziel des Projektes

Umsetzung einer lauffähigen Umgebung, in der man die Entscheidungen und Vorgehensweise des Agenten nachvollziehen kann.
KI des Agenten – Vorgehensweise

Die Vorgehensweise des Agenten lässt sich in 4 Schritte einteilen

    Der Agent sucht nach Gold.
    Der Agent sucht nach einem naheliegenden sicheren Feld, da er das Gold nicht finden kann. Hier ist auch wichtig die Länge der Route(Weges) zu beachten. Priorisiert werden die naheliegenden Felder.
    Der Agent sucht nach dem Wumpus, um ihm zu töten, da dieser vielleicht das Vorrankom-men verhindert. Dieser Fall wird nur dann ausgeführt, wenn die oberen zwei kein positives Ergebnis liefern. Dies bedeutet, dass der Agent die Position des Wumpus schon früher be-stimmen kann, diese Information aber noch nicht benötigt.
    Handeln unter Unsicherheit. D.h. der Agent entscheidet sich für ein (sehr wahrschein-lich)tödliches(naheliegendes) Feld. Entscheidung wird per Random ausgewählt. 

Nach jedem einzelnen Schritt wird die Wissensbasis des Agenten aktualisiert. 
