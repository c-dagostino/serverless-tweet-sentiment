# Touch Designer - Web Application
TD-Webapp is a react web application for email editing.

## Getting Started - Run TD-Webapp
Even though TD-Webapp is a [NodeJS](https://nodejs.org) + [NPM](https://npmjs.com) based project that can be run on a computer, a Dockerized version is also available for development. Any of these options can be used to run and keep developing the editor.

### NPM
1. Download NodeJS 8.9.1 from [NodeJS](https://nodejs.org).
2. Clone this repo `git clone git@gitlab.buehner-fry.com:touch-designer/td-webapp.git` and open its folder `cd td-webapp`
3. Add Navis NPM Registry to @navis scope as `npm config set @navis:registry https://artifacts.buehner-fry.com/artifactory/api/npm/navis-npm/`
4. Run `npm install` to install project's dependencies.
5. Use `npm start` to run the application locally, which will be available on http://localhost:3000
6. Optional: The application can also be started with `npm run start:local-services` and will consume TD-Services from http://localhost:8080/td-api

#### Update to the latest version
After pulling the latest changes from gitlab (`git pull`), project dependencies might have changed. Use `npm prune` to remove any obsolete and unused dependency, followed by `npm install` to install the latest ones and `npm start` to start the TD-Webapp demo server again. Updating and rebasing from develop is recommended before start working every day to avoid major conflicts (A rebase a day keeps Santi away).

### Docker
1. Be sure [Homebrew](https://brew.sh/) (on Mac) and [Docker Community Edition](https://www.docker.com/get-docker) are installed and running on your local environment
2. Clone this repo `git clone git@gitlab.buehner-fry.com:touch-designer/td-webapp.git` and open its folder `cd td-webapp`
3. Install Docker-Sync gem by running `gem install docker-sync`
4. Use `docker-sync-stack start` to initialize and run the application locally. This will start sync services, watcher and then the docker-compose defined stack, resulting in a new TD-Webapp instance available on http://localhost:3000, and the project directory synchronized with the docker container where the TD-Webapp is being compiled and served, this way any changes to the source code will be automatically updated (Changes to Webpack configuration files require to restart the container to make effect).

#### Stop the Application
To stop the container, simply cancel the `docker-sync-stack start` process in the terminal using `Ctrl+C`. This will stop the editor and sync containers.

#### (re) Start the Application
If the TD-Webapp instance is stopped, it can be brought back to execution using `docker-sync-stack start`.

#### Removing containers
`docker-sync-stack clean` will compose down the app stack, stop and clean up all sync endpoints. This will stop and remove the TD-Webapp and sync containers.

#### Update to the latest version
After pulling the latest changes from gitlab (`git pull`), project dependencies might have changed, so a new docker image needs to be built. Use `docker-compose build --no-cache` to rebuild the docker image using the latest dependencies' versions and `docker-sync-stack start` to start the container again. Updating and rebasing from develop is recommended before start working every day to avoid major conflicts (A rebase a day keeps Santi away).

### Code styles
This project follows [Airbnb's JavaScript Style Guide](https://github.com/airbnb/javascript) with a couple of twitches for certain plugins as described:
- [jsx-a11y/*](https://github.com/evcohen/eslint-plugin-jsx-a11y)
- [react/*](https://github.com/yannickcr/eslint-plugin-react)
- [redux-saga/*](https://github.com/pke/eslint-plugin-redux-saga)
```javascript
{
  "arrow-parens": [
    "error",
    "always"
  ],
  "arrow-body-style": [
    2,
    "as-needed"
  ],
  "class-methods-use-this": 0,
  "comma-dangle": [
    2,
    "always-multiline"
  ],
  "import/imports-first": 0,
  "import/newline-after-import": 0,
  "import/no-dynamic-require": 0,
  "import/no-extraneous-dependencies": 0,
  "import/no-named-as-default": 0,
  "import/no-unresolved": 2,
  "import/no-webpack-loader-syntax": 0,
  "import/prefer-default-export": 0,
  "indent": [
    2,
    2,
    {
      "SwitchCase": 1
    }
  ],
  "jsx-a11y/aria-props": 2,
  "jsx-a11y/heading-has-content": 0,
  "jsx-a11y/href-no-hash": 2,
  "jsx-a11y/label-has-for": 2,
  "jsx-a11y/mouse-events-have-key-events": 2,
  "jsx-a11y/role-has-required-aria-props": 2,
  "jsx-a11y/role-supports-aria-props": 2,
  "jsx-quotes": 0,
  "max-len": 0,
  "newline-per-chained-call": 0,
  "no-confusing-arrow": 0,
  "no-console": 1,
  "no-use-before-define": 0,
  "prefer-template": 2,
  "react/forbid-prop-types": 0,
  "react/jsx-first-prop-new-line": [
    2,
    "multiline"
  ],
  "react/jsx-filename-extension": 0,
  "react/jsx-no-target-blank": 0,
  "react/require-default-props": 0,
  "react/require-extension": 0,
  "react/self-closing-comp": 0,
  "redux-saga/no-yield-in-race": 2,
  "redux-saga/yield-effects": 2,
  "require-yield": 0
}
```

### Unit Testing
Ideally, all changes should be covered and validated with unit testing
For unit testing, [Facebook's Jest](https://facebook.github.io/jest/) framework is being used. Coverage threshold is currently not enforced, but will be soon, so be alert!

The commands to run unit tests depend on the way the project has been set up.
#### NPM
Open a terminal and run `npm run test` to perform styles validations on your source code.
#### Docker
First, with the application running, you need to connect to the Docker Container by running `docker-compose exec td_app /bin/bash` (This will execute a Bash process in the container and link your terminal to it). Once inside, validate the current directory is `/home/td-editor` and execute `npm run test`.

### How to's & Troubleshooting
