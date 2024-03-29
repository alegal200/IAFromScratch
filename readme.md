# Introduction aux réseaux de neurones

##Remarques :
 Que l'on réalise de la
classification ou de la régression, la méthode de descente du gradient et ADALINE fournissent, au terme de l'apprentissage, des poids synaptiques tout
à fait similaires.

La méthode de descente du gradient nécessite une connaissance globale de l'erreur. 
En effet, au cours d’une itération
, aucun justement des poids synaptiques ne sera réalisé tant que tous les exemples n'auront pas été traités, et cela quel que soit l'ordre de traitement des exemples du jeu d'apprentissage. 
Le processus d'apprentissage est donc « figé » dans le sens où la trajectoire vers la solution est unique. Ceci peut paraître comme un défaut de la méthode.

Contrairement à la méthode de descente du gradient, ADALINE modifie les poids synaptiques à chaque exemple traité (connaissance locale de l'erreur). 
Elle ne constitue don qu'une approximation de la méthode de descente du gradient mais elle laisse l'opportunité de « contrôler » le processus d'apprentissage en choisissant l'ordre des exemples que l'on présente au Perceptron

Dans de nombreux cas de classification, il a été remarqué que ADALINE présente un apprentissage plus rapide, c'est-à-dire présentant moins d'itérations.

La méthode de descente du gradient nécessite de mettre à jour les corrections des poids synaptiques ∆wi à chaque exemple traité. Si le nombre d'exemples du jeu d'apprentissage est très élevé, cela peut conduire à une a cumulation d'erreurs d'arrondis (voir même de capacité machine par dépassement) avant de pouvoir appliquer les corrections aux poids synaptiques.

Toutes ces considérations ont fait que ADALINE est devenue la méthode de prédilection pour l'apprentissage du Perceptron et a de plus en plus supplanté la méthode de des ente du gradient. Elle deviendra même incontournable pour les réseaux de neurones multi-couches.

# The deposit

*Master en architecture des systèmes informatiques (MI11)
Master en sciences industrielles – orientation informatique (M18)
Traitement du signal 1D et 2D – Introduction aux réseaux de neurones
Année académique 2021-2022
Professeur : Jean-Marc Wagner
Projet de laboratoire
Etude et implémentation du Perceptron et de sa mise en réseau*
### Ce projet a pour but de vous familiariser avec les réseaux de neurones artificiels et leurapprentissage :
* Perceptron simple
* Perceptron utilisant la technique d’apprentissage de la descente du gradient
* Perceptron utilisant la technique d’apprentissage ADALINE
* Perceptron mono-couche (il s’agit d’un premier réseau de neurones travaillant en parallèle
mais n’ayant aucune influence les uns sur les autres).
* Perceptron multi-couches (algorithme de rétro-propagation de l’erreur)

Ce projet consiste donc à implémenter ces différentes techniques. Il vous est loisible de choisir le 
langage et le paradigme de programmation de votre choix. Il vous est néanmoins interdit d’utiliser 
une librairie dédiée aux réseaux de neurones. Vous devez donc tout implémenter « from scratch ».
Vous pouvez par contre utiliser une librairie si vous souhaitez afficher des graphiques.
Les données d’apprentissage devront être lues dans un fichier texte du type csv. Vous devez donc 
également réaliser l’implémentation de cette partie.
#### Mise au point
En vue de sa mise au point, vous pourrez entrainer votre Perceptron sur des données simples 
(comme la porte logique ET). 
#### Validation
Une fois mis au point, on vous demande de tester votre implémentation sur les exemples du cours 
théorique :
#### Perceptron :
- Opérateur logique ET (table 2.1 ou table 2.3) → mise au point
- Classification de données linéaires séparables (table 2.9)
- Classification de données non linéairement séparables (table 2.10)
- Régression linéaire (table 2.11)
#### Perceptron monocouche :
- Classification à 3 classes (table 3.1)
- Classification à 4 classes (table 3.5)
#### Perceptron multicouche :
- Opérateur logique XOR (table 4.3)
- Classification à 2 classes non linéairement séparables (table 4.12)
- Classification à 3 classes non linéairement séparables (table 4.14)
- Régression non-linéaire (table 4.17)
#### D’autres exemples
Finalement, on vous demande de trouver/imaginer plusieurs exemples concrets :
• de classification
• de régression
Soyez imaginatif. A vous donc de créer des jeux de données adaptés ou de trouver des jeux de 
données qui pourraient correspondre à l’utilisation d’un Perceptron ou d’un réseau de neurones 
simple.
#### |Consignes du projet|

Ce projet est à réaliser par équipe de 2 étudiants.
Pour ce projet, vous devez me rendre votre projet complet (sous forme d’une archive) que vous 
m’enverrez par e-mail (jean-marc.wagner@hepl.be) à la date convenue. 
Une évaluation orale sera alors réalisée. Vous devrez être capable d’expliquer vos implémentations, 
les exemples d’apprentissage choisis et les comportements obtenus.