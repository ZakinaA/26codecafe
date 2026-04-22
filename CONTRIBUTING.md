# Contributing

---

## Normes de commit

| Type                | Usage                                           |
|---------------------|-------------------------------------------------|
| `chore: message`    | Structure du projet                             |
| `feat: message`     | Ajout d'une fonctionnalité                      |
| `fix: message`      | Correction d'un bug                             |
| `refactor: message` | Refonte du code sans changement de comportement |
| `docs: message`     | Rédaction ou mise à jour de documentation       |

> **Exemple :** `feat: ajout du model Pompier`

---

## Workflow Git

1. Créer une branche depuis `dev`
2. Travailler sur la branche
3. Push la branche sur origin
4. Créer une Pull Request vers `dev` sur GitHub
5. Merger la PR puis supprimer la branche

---

## Branches

| Type           | Usage                   |
|----------------|-------------------------|
| `feature/nom`  | Nouvelle fonctionnalité |
| `fix/nom`      | Correction de bug       |
| `refactor/nom` | Refonte du code         |
| `docs/nom`     | Documentation           |

> **Exemple :** `feature/ajout_model_pompier`

---

## Exemple de Pull Request

### Titre
Suit la même convention que les commits.
> `feat: ajout du model Pompier`

### Description

**Pourquoi**

Expliquer la raison de la modification.

**Quoi**
- Élément modifié ou ajouté
- Élément modifié ou ajouté

**Comment tester** *(pour `feat` ou `fix` uniquement)*

Expliquer comment vérifier que la modification fonctionne.