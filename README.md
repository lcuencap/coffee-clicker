# Coffee Clicker

A Java 21 Swing game created as a university group project. Players click to earn in-game currency, purchase generators and upgrades, and view game statistics. The code is organized into presentation, business, and persistence layers, with MySQL for accounts and saved games.

This is shared team work. Everyone on the team had to understand and defend the complete application; no individual module attribution is claimed here.

## Run locally

1. Install JDK 21, Maven, and MySQL.
2. Import `schema.sql` into a local MySQL server. It contains table definitions only, with no exported user records.
3. Create a local database user with access to `coffee_clicker`.
4. Copy `files/config.example.json` to `files/config.json` and fill in your local database credentials. The real config is ignored by Git.
5. From the repository root run `mvn compile exec:java`.

The Java dependencies are Gson, MySQL Connector/J, and JFreeChart, declared in `pom.xml`. The original project loaded visual resources from `files/Resources/`; these files remain local for now because their authorship and redistribution rights have not been established. A fresh clone can compile, but the game interface needs replacement artwork before this repository is suitable for a public release.

## Scope and current limitations

This portfolio copy starts with the latest local source, including uncommitted work from the original project, and excludes the original repository history because it contained real configuration and OAuth credential files. The supplied SQL schema excludes sample users and their passwords. The desktop client currently stores account passwords without password hashing and uses string-built SQL queries in its persistence layer; those parts need refactoring and database tests before public presentation. A local Java compilation succeeded; MySQL integration and the graphical flow have not been tested here.

## Academic report

The [team report](report.pdf) contains the original UI mockups, class diagram, and development process. It documents the course submission, including visual resources that are not redistributed in this repository.
