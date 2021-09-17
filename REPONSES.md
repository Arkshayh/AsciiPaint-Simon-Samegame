**Réponses aux questions des TDs**

**TD1**


Q1:

	1. Les coordonnées du point crée (0,0) puis on va ajouter/déplacer ce point de 2 en x/y
	   et réaffiché ses coordonnées (2,2)

	2. Le compilateur va nous dire que la class TestPoint ne devrait pas être public, et si on veut que ce soit le cas
	   qu'il faut qu'elle soit déclarée dans un fichier du même nom (TestPoint.java)

	3. même erreur qu'au point précédent.
	
Q2:

	1. L'attribut étant privé, on ne peut pas y avoir accès directement en dehors de la classe, il faut passer par une méthode de la classe point pour y avoir accès.

	2. (0.0, 0.0)méthode move(int, int)(2.0, 2.0)

	3. On ne peut pas l'ajouter car elle recoit un nombre identique de paramètre qui sont aussi de même type que ceux de la méthode 
	   move(double x, double y)
	
Q3:

	1. il faut que this(0,0) soit la première ligne du constructeur.

	2. Non plus d'erreur car un constructeur par défaut est fourni. Le programme affiche (0.0, 0.0) (2.0, 2.0)

	3. (10.0, 10.0) (12.0, 12.0)

Q4:

	1. Les coordonnées du centre du cercle ainsi que son rayon [(0.0, 0.0), 5.0],
	   puis on va déplacer le cercle, réafficher ses infos [(2.0, 5.0), 5.0]   
	   et finalement multiplié son rayon par 2 (doubler sa taille) [(2.0, 5.0), 10.0]

	2. Une seule instance Point p = new Point();

Q5:

	1. Les coordonnées et rayon initial du cercle (on déplace les coord du point p ce qui déplace le cercle vu mais on
	   le redéplace à sa position d'origine gràce au point p2 qui fait référence à p)
	   On obtient :
       Circle :[(0.0, 0.0), 5.0]
       Circle :[(2.0, 5.0), 5.0]
       Circle :[(0.0, 0.0), 5.0]

	2. 1 instances de la classe point est crée et une de la classe cercle
	   P est une instance créer et center fait référence à l'instance p (modifier p = modifer le centre du coup) et p2 fait référence à p
	   L'attribut center du cercle référence l'instance p

	3. Circle :[(0.0, 0.0), 5.0]
       Circle :[(0.0, 0.0), 5.0]
       Circle :[(-2.0, -5.0), 5.0]
       p ne fait plus référence à l'attribut center grâce au chagement fait dans le constructeur mais par contre p2
       y fait référence.

    4. Circle :[(0.0, 0.0), 5.0]
       Circle :[(0.0, 0.0), 5.0]
       Circle :[(0.0, 0.0), 5.0]
       p2 ne fait plus référence au centre grace aux changement effectués

    5. 1 instance de la classe cercle c, 2 instances de la classe point (p et p2)
       0 instance sont référencés par p et p2 (ils sont tous les 2 un point distinct)
       Aucune instance ne référence l'attribut center

