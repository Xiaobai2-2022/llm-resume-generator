# Large Language Model Resume Generator

## Server Prerequisite

1.

## Server Installation

### Prerequisite

1. Git

2. PostgreSQL

3. IntelliJ IDEA

4. OpenJDK 21

5. Maven

### Installation

1. Make a new project directory

   `mkdir <project-directory>`

   `cd <project-directory>`

2. Clone the project repository

   `git clone https://github.com/Xiaobai2-2022/llm-resume-generator/`

   `cd llm-resume-generator`

3. Setup database password

   `export DB_DEV_PASSWORD=<your-password>'`

4. Initialize database and user

   `sudo -u postgres psql -f server/src/main/resources/postgreSQL/init-user-db.sql`

5. Open the server folder in IntelliJ IDEA

6. Call the maven install command

   `./mvnw clean install`

7. Specify a JDK

   In **File** $\rightarrow$ **Project Structure** $\rightarrow$ **Project Settings** $\rightarrow$ **Project**

    1. Set **SDK** to Any Java 21.0.8 JDK
    2. Set **Language Level** to 21

8. On the right sidebar, find **Maven**

    1. Select **Reload All Maven Projects**
    2. Select **Download Sources and Documentation**

9. Go to **File** $\rightarrow$ **Settings** $\rightarrow$ **Plugins** download **Lombok** by *JetBrains s.r.o.*

10. Go to **File** $\rightarrow$ **Settings** $\rightarrow$ **Build, Execution. Deployment** $\rightarrow$ **Compiler** $\rightarrow$ **Annotation Processors** turn on **Enable Annotation Processing**

11. Go to **Run/Debug Configurations**

    1. Press the **+** button to **Add New Configuration**
    2. Select **Spring Boot**
    3. Run on: **Project Default**
    4. **Build and run** select **Java 21** and `dev.fxtech.llm_resume_generator.LlmResumeGeneratorApplication`

12. You can now run the project!

## Contribute

### Prerequisite

1. GPG

### Commit

1. Set up the GPG key for signed commit

    1. `gpg --full-generate-key`
    2. select `(1) RSA and RSA`
    3. select `4096`
    4. select `0 = key does not expire`, `Y`
    5. enter your real name and your GitHub email, `O`
    6. select a passphrase

2. Export your GPG key

   `gpg --armor --export <your-github-email>`

3. Copy the entire GPG key from `-----BEGIN PGP PUBLIC KEY BLOCK-----`

4. Go to **GitHub** $\rightarrow$ **Settings** $\rightarrow$ **SSH and GPG keys**$\rightarrow$ **New GPG key** and enter your copied block as Key

5. List all GPG keys

   `gpg --list-secret-keys --keyid-format=long `

   You should see `sec   rsa4096/ABCDEF1234567890 2025-10-14 [SC]`

6. Use the part after the `/`(`ABCDEF1234567890`)

   `git config --global user.signingkey ABCDEF1234567890`
   `git config --global commit.gpgsign true`

7. Now add all files

   `git add <files>`

8. Commit with your signature

   `git commit -S -m "<commit-message>"`
