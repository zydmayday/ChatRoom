# Chatroom Project

### How to build 

1. Install the **latest** `npm` and `nodejs`  on your computer.

2. Install the `npm-check-updates` (https://www.npmjs.com/package/npm-check-updates).

   ```shell
   npm install -g npm-check-updates
   ```

3. Go to your frontend directory (`summer/frontend`), run the command below to check the latest version of the dependencies in `package.json`.

   ```shell
   ncu
   ```

   If there is any, you can run the command below to update your `package.json`.

   ```shell 
   ncu -a
   ```

4. Go back to root directory (`summer`), run the run the command below to install all the dependencies for both `frontend` and `backend`.

   ```shell
   sudo mvn clean install
   ```

   If you have an error here like

   ```shell
   Error: JAVA_HOME is not defined correctly.
      We cannot execute /usr/lib/jvm/java-**/bin/java
   ```

   you should creating a file `.mavenrc` on your home folder and adding the code below

   ```
   export JAVA_HOME=/usr/lib/jvm/java-**
   ```

5. Go to your backend folder (`summer/backend`) to run the app in **production mode**.

   ```shell
   mvn spring-boot:run
   ```

   The **port number** can be configured in `backend/src/main/resources/application.yml`.

6. If you want to develop with `hot reloading` technique, go to the `summer/frontend` to run the app in **development mode**.

   ```
   npm start
   ```