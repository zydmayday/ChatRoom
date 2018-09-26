# Project Summer

### Folder Structure

```reStructuredText
summer
|
+--frontend
|  |
|  +--...(nuxt directory structure)
|  +--pom.xml
+--backend
|  |
|  +--src
|  +--pom.xml
+--pom.xml
```

### How to build 

1. Install the **latest** `npm` and `nodejs` !!!

2. Install the `npm-check-updates` (https://www.npmjs.com/package/npm-check-updates).

   ```shell
   npm install -g npm-check-updates
   ```

3. Go to  `summer/frontend`, run the command below to check the latest version of the dependencies in `package.json`.

   ```shell
   ncu -u
   ```

   If there is any, you can run the command below to update your `package.json`.

   ```shell 
   ncu -a
   ```

4. Go to root directory `summer`, run the command below to install all the dependencies for both `frontend` and `backend`.

   ```shell
   sudo mvn clean install
   ```

   This command will eventually invoke the `pom.xml` of `frontend` and `backend`. You can also build `frontend` and `backend` separately. Just refer to their `pom.xml`.

   If you have an error here like

   ```shell
   Error: JAVA_HOME is not defined correctly.
      We cannot execute /usr/lib/jvm/java-**/bin/java
   ```

   you should creating a file `.mavenrc` on your home folder and adding the code below

   ```
   export JAVA_HOME=/usr/lib/jvm/java-**
   ```

5. Go to `summer/backend` to run the app in **production mode**.

   ```shell
   mvn spring-boot:run
   ```

   The default  **port number** is `8080`, but it can be configured in `backend/src/main/resources/application.yml`. 

6. If you want to develop with `hot reloading` technique, go to  `summer/frontend` to run the app in **development mode**. 

   ```
   npm run dev
   ```

   The default URL is `http://localhost:3000`.