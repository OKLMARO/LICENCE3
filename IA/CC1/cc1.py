from sklearn.datasets import load_wine

# Question 1
wine_data = load_wine()

# Question 2
print("Nombre d'attributs pour une donnée :", len(wine_data.feature_names))

# Question 3
print("Le 5ème attribut représente :", wine_data.feature_names[4])

# Question 4
print("L'étiquette de la 50ème donnée est :", wine_data.target[49])

# Question 5 & 6
from sklearn.model_selection import train_test_split

X = wine_data.data

y = wine_data.target

X_train, X_test, y_train, y_test = train_test_split(X, y, train_size=0.7, random_state=49)

# Question 7
from sklearn.neighbors import KNeighborsClassifier
from sklearn.metrics import accuracy_score

knn = KNeighborsClassifier(n_neighbors=3)
knn.fit(X_train, y_train)

# Question 8
nouveau = [[15, 3.0, 1.50, 23.2, 218, 2.5, 2.4, 0.29, 1.50, 3.4, 1.1, 2.94, 500]]
prediction_nouveau = knn.predict(nouveau)
print("Prédiction de l'étiquette pour la nouvelle donnée :", prediction_nouveau)

# Question 9
y_pred = knn.predict(X_test)
precision = accuracy_score(y_test, y_pred)
print("Score de précision sur les données de test :", precision)

# Question 10
print("\nÉtiquettes réelles des 5 premières données de test :", y_test[:5])
print("Étiquettes estimées pour les 5 premières données de test :", y_pred[:5])

# Question 11
from sklearn.tree import DecisionTreeClassifier

tree = DecisionTreeClassifier(max_depth=2)
tree.fit(X_train, y_train)

# Question 12
nouveau = [[15, 3.0, 1.50, 23.2, 218, 2.5, 2.4, 0.29, 1.50, 3.4, 1.1, 2.94, 500]]
prediction_nouveau_arbre = tree.predict(nouveau)
print("Prédiction de l'étiquette pour la nouvelle donnée avec un arbre de décision :", prediction_nouveau_arbre)

# Question 13
y_pred_tree = tree.predict(X_test)
precision_tree = accuracy_score(y_test, y_pred_tree)
print("Score de précision sur les données de test avec un arbre de décision :", precision_tree)

# Question 14
if precision > precision_tree:
    print("La méthode des k plus proches voisins donne un meilleur résultat.")
elif precision < precision_tree:
    print("La méthode de l'Arbre de Décision donne un meilleur résultat.")
else:
    print("Les deux méthodes donnent des résultats similaires")



