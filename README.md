# Oppsett av prosjekt: Quasar og Vue 

## **Førstegangsinstallasjon**

1. **Klon repository**  
   Klon repositoryet til din lokale maskin:
   ```bash
   git clone <repository-link>
   cd <repository-folder>

2. **Installer Bun**  
   Kjør følgende kommando i terminalen for å installere Bun: 
   ```bash
   curl -fsSL https://bun.sh/install | bash

3. **Installer Quasar CLI**  
   Bruk Bun til å installere Quasar CLI globalt:
   ```bash
   bun install -g @quasar/cli

4. Installer Homebre (Kun MacOS)  
   Kjør følgende kommando for å installere Homebrew:
   ```bash
   /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"

6. Installer Node.js og Nodenv (Kun MacOS)  
   Bruk Homebrew til å installere Node.js og Nodenv:
   ```bash
   brew install node
   brew install nodenv

7. Installer prosjektavhengigheter  
   Gå til prosjektmappen og installer de nødvendige avhengighetene:
   ```bash
   bun install

## **Kjør prosjektet lokalt**  
   Etter at oppsettet er fullført, kan du kjøre prosjektet lokalt ved hjelp av Quasar sin utviklingsserver.  
   Gå til prosjektmappen og kjør:
   ```bash
   quasar dev
