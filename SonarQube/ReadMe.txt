Get Started in Two Minutes:
    1. Download and unzip the SonarQube distribution (let's say in "C:\sonarqube" or "/etc/sonarqube")

    2. Start the SonarQube server:
        # On Windows, execute:
        C:\sonarqube\bin\windows-x86-xx\StartSonar.bat

        # On other operating system, execute:
        /etc/sonarqube/bin/[OS]/sonar.sh console

    3. Download and unzip the SonarQube Scanner (let's say in "C:\sonar-scanner" or "/etc/sonar-scanner")

    4. Download and unzip some project samples (let's say in "C:\sonar-scanning-examples" or "/etc/sonar-scanning-examples")

    5. Analyze a project:
        # On Windows:
        cd C:\sonar-scanning-examples\sonarqube-scanner
        C:\sonar-scanner\bin\sonar-scanner.bat

        # On other operating system:
        cd /etc/sonar-scanning-examples/sonarqube-scanner
        /etc/sonar-scanner/bin/sonar-scanner

    6. Browse the results at http://localhost:9000 (default System administrator credentials are admin/admin)


    TODO put the actual script paths here

    Home:
        "C:\Users\David\Desktop\Intelij Workspace\Health-Tracker\SonarQube\sonarqube-6.3.1\bin\windows-x86-64\StartSonar.bat"

        cd "C:\Users\David\Desktop\Intelij Workspace\Health-Tracker\"
        "C:\Users\David\Desktop\Intelij Workspace\Health-Tracker\SonarQube\sonar-scanner-3.0.1.733-windows\bin\sonar-scanner.bat"

    Work:
        "C:\Users\dcmeade\Desktop\Intelij Workspace\Health-Tracker\SonarQube\sonarqube-6.3.1\bin\windows-x86-64\StartSonar.bat"

        cd "C:\Users\dcmeade\Desktop\Intelij Workspace\Health-Tracker\"
        "C:\Users\dcmeade\Desktop\Intelij Workspace\Health-Tracker\SonarQube\sonar-scanner-3.0.1.733-windows\bin\sonar-scanner.bat"
