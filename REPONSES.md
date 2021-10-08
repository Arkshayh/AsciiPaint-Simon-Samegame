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

Q6:

    1. Rectangle : [(0.0, 0.0), (5.0, 3.0)]
       Périmètre : 16.0
       Rectangle : [(2.0, 5.0), (7.0, 8.0)]
       Périmètre : 16.0
       Les coordonnées du Point bl et ur sont affichées ainsi que le périmètre du reclangle.
       Les points sont déplacés mais le périmètre du rectangle reste inchangé (comme prévu)
       
    2. br et ur sont 2 instances de la classe Point, r est une instance de classe rectangle dont ses 2 attributs
       bottomLeft et upperRight référence les point br et ur.   

    3. L'invariant n'est plus respecté car le point bl ne respecte plus les conditions que nous avion imposées dans le
       le constructeur, ses attributs x et y doivent être strictement inférieur aux attribus du point ur or
       ce n'est plus le cas.
       Rectangle : [(10.0, 10.0), (5.0, 3.0)]
       Périmètre : -24.0
       Rectangle : [(12.0, 15.0), (7.0, 8.0)]
       Périmètre :-24.0 
       Le périmètre du rectangle est négatif ce qui n'est pas censé être possible.
       Cette valeur s'affiche car car le point bl ne respecte plus les conditions que nous avion imposées dans le
       le constructeur, ses attributs x et y doivent être strictement inférieur aux attribus du point ur or
       ce n'est plus le cas. Pour éviter cela -> copie défensive ! 

    4. Il faut modifier le constructeur pour qu'il fasse un copie défensive des points placé en paramètre : 
       this.bl = new Point(bottomLeft.getX(), bottomLeft.getY());
       this.ur = new Point(upperRight.getX(), upperRight.getY());
       Le résultat affiché est désormais correct : 
       Rectangle : [(0.0, 0.0), (5.0, 3.0)]
       Périmètre : 16.0
       Rectangle : [(2.0, 5.0), (7.0, 8.0)]
       Périmètre :16.0


**TD2**

Q1:

    1.1 Le programme affiche : 
          (2.0, 4.0) - FF0000FF
          x : 2.0
          color FF0000FF

    1.2   Si on modifie la ligne : ColoredPoint p = new ColoredPoint(1,2,0xFF0000FF); on obtient une erreur 
          incompatible type, parce qu'un objet Point n'a pas tous les attributs d'un coloredPont. On obtient donc une
          à cette ligne et une autre à la ligne où l'on veut faire une getColor car un objet Point n'a pas d'attribut color

          On ne peut pas faire Point = new ColoredPoint mais par contre on peut faire ColoredPoint = new Point 
          car la classe ColoredPoint hérite de tous les attributs et des méthodes de Point.
          Il faut donc remette le code dans son état de base supprimé la ligne qui appelle la méthode getColor
          ne suffit pas.

    1.3   Oui on peut rajouter la ligne ColoredPoint p2 = new Point(2, 4); parce que Colored point hérite de la classe
          Point et de tous ses attributs et méthodes. 
    
    1.4   Non ne peut pas remplacer la méthode le code de la méthode toString() de ColoredPoint par :
          return this.x +" - "+ this.y +" - "+ this.color; car les attributs de la classe héritée sont private, pour y 
          avoir accès il faut donc faire appel aux méthodes de celles-ci (getter/toString() )
        
    1.5   Cyclic inheritance involving, 2 classes héritent l'une de l'autre, ça n'a pas de sens. 

    1.6   Si on déclare notre class Point final, on obtient une erreur car une classe final ne peut être dérivée. 
          L'erreur vient du fait que ColoredPoint hérite de celle-ci.  Une classee final ne peut pas être extend.

    
Q2 :


    2.1  Oui car toutes les classes héritent de la classe Objet. 

    2.2  Oui car toutes les classes héritent de la classe Objet, les classes qui héritent du classe hérite aussi de la
         classe que leur parents héritent. 

    2.3  Oui car la méthode hashcode est définie dans la classe Objet.
         car coloredPoint hérite de la classe Objet. 

Q3: 
    
    3.1  Super() doit être la 1ère ligne de notre constructeur, sinon notre compilateur pense qu'il n'y a pas de 
         constructeur. 

    3.2 super(x,y) fait appelle au constructeur de la classe parent. Si on l'enlève on n'a pas de constructeur pour nos 
        attributs hérités. 

    3.3 Non, car on définit un constructeur par défaut pour notre classe Point. 


Q4: 

    4.1 on obtient : 
        constructor of A
        constructor of B
        constructor of C

    4.2 : constructor of A
          constructor of B

    4.4 public Object(){};


Q5:

    5.1 (0.0, 0.0) - not pinned
        (1.0, 1.0) - not pinned
        (1.0, 1.0) - pinned

    5.2 celle de PinnablePoint car l’objet référencé par la variable à ce moment-là est de type PinnablePoint.

    5.3 Il faut que la méthode de classe parent déclare aussi dans son entête le fait que la méthode puisse renvoyer une
        exception. 

    5.4 Non, car ce n'est pas une exception qui n'est plus gérer par le compilateur.

    5.5 Non pas d'erreur.

    5.6 Non pas d'erreur. 

    5.7 Oui, car Objet n'est pas un Point

    5.8 Une erreur d'accès car on veut modifier l'accèssibilité de cette méthode, alors que celle-ci avait été définie 
        comme public dans la classe parent

    5.9 ça appel fait appel à la méthode move de la classe parent. 


**TD3**

Q1:

    1.1 Les dimensions de la fenêtre de notre application 

    1.2 initStyle modifie visuellement la fenêtre de notre application.
       Valeur possible : DECORATED, UNDECORATED, UTILITY, TRANSPARENT

    1.3 Notre objet text sera affiché à une position différente. 

Q2:

    2.1 la 1ère à comme état de base "Séléctionné" et celle-ci peut être désélectionnée.
        la 2ème à comme état de base "indéterminé" et peut choisir peut être séléctionnée ou désélectionnée (une fois son état changer
        impossible de revenir à l'état indéterminé)
        la 3ème à comme état de base désélectionnée et peut choisir entre séléctionnée, indéterminée et désélectionnée

    2.2 leur position est différente, elles ne sont plus centrées 

Q3:

    3.1 Le texte entré est représenté par ******* (il est caché)

    3.2 Le texte entré ne peut plus être modifer 

Q4:

    Lorsque l'on appuye sur le bouton, le texte entré dans notre textaera sera affiché dans la console. (On a ajouté une action lorsque 
    l'on appuye sur le bouton qui va chercher le texte entré dans textarea pour l'affiché dans la console)

Q5:
    
    getChildren renvoie tous les éléments d'un conteneur. Par exemple si on crée 
    une scene root et qu'on y ajoute un textarea et un bouton, la 
    la méthode getChildren reverra une liste contenant ces 2 éléments 

Q6:

    6.1 remplacer hbox par vbox

    6.2 Oui

    6.3 Des éléments peuvent être coupés de l'affichage, on ne les verra donc 
    pas complètement. fillWidthProperty()
