# Coffee Clicker

A Java 21 Swing game created as a university group project. Players click to earn in-game currency, purchase generators and upgrades, and view game statistics. The code is organized into presentation, business, and persistence layers, with MySQL for accounts and saved games.

This is shared team work. Everyone on the team had to understand and defend the complete application; no individual module attribution is claimed here.

## Game theme

Coffee Clicker is themed around **student life and studying**. The generators are deliberately based on things a student might use to keep studying or make studying easier:

- **Redbull** represents the basic energy boost: more energy and concentration to keep studying.
- **Notes** represent classmates' notes and study material that help you understand the subjects more efficiently.
- **CEUS** represents a well-known tutoring academy focused on students from **La Salle**, the university where the project was developed.

In the game logic these correspond to the Basic, Mid and High generator tiers respectively. The names are part of the original university-project theme rather than generic resource-generator labels.

## Run locally

1. Install JDK 21, Maven, and MySQL.
2. Import `schema.sql` into a local MySQL server. It contains table definitions only, with no exported user records.
3. Create a local database user with access to `coffee_clicker`.
4. Copy `files/config.example.json` to `files/config.json` and fill in your local database credentials. The real config is ignored by Git.
5. From the repository root run `mvn compile exec:java`.

The Java dependencies are Gson, MySQL Connector/J, and JFreeChart, declared in `pom.xml`. The game interface uses visual resources under `files/Resources/`. Those assets are not included in this curated repository yet, so a fresh clone can compile but the complete graphical interface requires the original project resources.

## Scope and current limitations

This portfolio copy starts with the latest local source, including uncommitted work from the original project, and excludes the original repository history because it contained real configuration and OAuth credential files. The supplied SQL schema excludes sample users and their passwords. This is an early university project built while learning desktop application architecture and persistence. The persistence layer uses simple string-built SQL and stores account passwords directly, which would not be appropriate for a real deployed application. For this portfolio copy, those choices are kept as part of the original learning context rather than presented as production-ready security practices. A local Java compilation succeeded; MySQL integration and the graphical flow have not been fully re-tested here.

## Academic report

The [team report](report.pdf) contains the original UI mockups, class diagram, and development process.
