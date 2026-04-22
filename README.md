# 🚒 CodeCafe | Application Web SDIS

Application web Java EE de gestion des pompiers et des interventions du SDIS (Service Départemental d'Incendie et de Secours).

---

## 📋 Description

CodeCafe est une application web développée dans le cadre d'un projet BTS SIO SLAM. Elle permet de gérer les informations relatives aux pompiers (professionnels et volontaires), aux casernes, aux engins et aux interventions du SDIS 14 (Calvados).

---

## 🛠️ Technologies

| Technologie     | Version           |
|-----------------|-------------------|
| Java EE         | Jakarta EE        |
| Serveur         | Tomcat 10         |
| Base de données | MariaDB           |
| Front-end       | JSP / Bootstrap 5 |
| Versionning     | Git / GitHub      |

---

## ⚙️ Installation

### Prérequis

- Java JDK 17+
- Apache Tomcat 10
- MariaDB
- Maven

### Base de données

1. Créer la base de données
2. Exécuter le script de création des tables :
```bash
mysql -u root -p < codecafe_create.sql
```
3. Insérer les données de test :
```bash
mysql -u root -p < codecafe_data.sql
```

### Lancement

1. Cloner le dépôt :
```bash
git clone https://github.com/ZakinaA/26codecafe.git
```
2. Configurer la connexion à la base de données dans le fichier de contexte Tomcat
3. Déployer l'application sur Tomcat 10
4. Accéder à l'application : `http://localhost:8080/26CodeCafe`

---

## 📁 Structure du projet

```
src/
  main/
    java/
      bts/sio/codecafe/
        dao/          ← Accès aux données
        form/         ← Validation des formulaires
        model/        ← Classes métier
        servlet/      ← Servlets Jakarta
        utils/        ← Utilitaires
    webapp/
      vues/
        components/   ← Fragments réutilisables (.jspf)
        situation/    ← Vues situation
        intervention/ ← Vues intervention
      WEB-INF/
        web.xml
```

---

## ✅ Fonctionnalités

- Gestion des **pompiers** (professionnels et volontaires)
- Gestion des **casernes**
- Gestion des **interventions**
- Gestion des **situations**
- Gestion des **engins**
- Archivage / désarchivage des entités
- Authentification et gestion des droits

---

## 👥 Équipe

Projet réalisé par des étudiants BTS SIO SLAM — Lycée Jean Rostand, Caen (2025).

---

## 📄 Contribuer

Consulter le fichier [CONTRIBUTING.md](CONTRIBUTING.md) pour les normes de commit, le workflow Git et les conventions de nommage des branches.