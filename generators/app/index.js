'use strict';

const Generator = require('yeoman-generator');
const chalk = require('chalk');
const yosay = require('yosay');

const { version } = require('../../package.json');

const defaultProjectName = 'app';
const defaultProjectType = 'java';

const replaceSpacesWithDash = input =>
  (input || defaultProjectName).trim()
    .replace(/\./g, '_')
    .replace(/\W+/g, '-');

module.exports = class extends Generator {

  // note: arguments and options should be defined in the constructor.
  constructor(args, opts) {
    super(args, opts);

    this.projectTypes = [
      'java',
      'java-akka-actor',
      'java-ee',
      'java-ee-cdi-full-multi-project',
      'java-ee-ejb-full-multi-project',
      'java-ee-faces',
      'java-ee-thymeleaf',
      'java-parent-multi-project',
      'java-payara-micro',
      'java-spring-boot',
      'java-spring-boot-1.x',
      'java-spring-cloud-function-web',
      'java-vertx',
      'java-wildfly-swarm',
      'kotlin',
      'kotlin-parent-multi-project',
      'kotlin-spring-boot',
      'kotlin-spring-boot-1.x',
      'kotlin-spring-cloud-function-web',
      'kotlin-vertx',
      'kotlin-wildfly-swarm',
      'kotlin-ee',
      'scala',
      'scala-2.11',
      'scala-gradle-akka-actor',
      'scala-gradle-akka-persistence',
      'scala-sbt',
      'scala-sbt-akka-actor',
    ];

    this.props = {
      projectDirectory: defaultProjectName,
      projectType: defaultProjectType,
    };

    this.option('name', {
      alias: 'n',
      hide: false,
      type: String,
      required: false,
      desc: `Name of project directory / application name.
                        # Will be prompted if no values was specify.
                        # Non alphanumeric symbols of project directory will be replaced with hyphen.`,
      default: undefined,
    });

    this.option('type', {
      alias: 't',
      hide: false,
      type: String,
      required: false,
      desc: `Project type.
                        # Will be prompted if no values was specify. 
                        # Possible values are:
               ` + this.projectTypes.join(`\n               `),
      default: undefined,
    });

    this.prompts = [];

    const name = this.options['name'];
    const type = this.options['type'];
    const typeOptionFound = !!type && this.projectTypes
      .indexOf(type.trim().toLowerCase()) !== -1;

    if (typeOptionFound) this.props.projectType = type;
    else this.prompts.push({
      type: 'list',
      name: 'projectType',
      message: 'What kind of project do you wanna build today, my friend?',
      choices: this.projectTypes,
      default: defaultProjectType,
    });

    if (!!name) this.props.projectDirectory = name;
    else if (typeOptionFound) this.props.projectDirectory = defaultProjectName;
    else this.prompts.push({
      type: 'input',
      name: 'projectDirectory',
      message: 'Enter application project directory name',
      default: defaultProjectName
    });
  }

  prompting() {

    const generatorName = chalk.red('jvm');
    const generatorVersion = chalk.blue('v' + version);

    this.log(yosay(
      `Welcome to the terrific ${generatorName} generator ${generatorVersion}`
    ));

    return this.prompt(this.prompts).then(props => {
      this.props = Object.assign({}, this.props, props);
    });
  }

  writing() {

    /* safety */

    this.props.projectDirectory = replaceSpacesWithDash(this.props.projectDirectory);
    this.props.projectType = this.props.projectType.trim().toLowerCase();

    /**
     * RAW files
     *
     * Using: copy functionality as is
     */

    /* copy commons */

    [
      'docs',
      'LICENSE',
      'gradle',
      'gradlew',
      'gradlew.bat',
      'gradle.properties',
      'mvnw',
      'mvnw.cmd',
      'idea.xml',

    ].forEach(suffix => this.fs.copy(
      this.templatePath(`_common/${suffix}`),
      this.destinationPath(`${this.props.projectDirectory}/${suffix}`)
    ));

    /* copy dotted files / dirs, like: .mvn, .gitignore, ... */

    [
      'mvn',
      'gitignore',
      'hgignore',
      'gitlab-ci.yml',

    ].forEach(suffix => this.fs.copy(
      this.templatePath(`_dotted/${suffix}`),
      this.destinationPath(`${this.props.projectDirectory}/.${suffix}`),
    ));

    /* copy project files by type */

    [
      '**/*',
      '**/.*',

    ].forEach(pattern => this.fs.copy(
      this.templatePath(`${this.props.projectType}/${pattern}`),
      this.destinationPath(this.props.projectDirectory),
    ));

    /**
     * Template files.
     *
     * Using: copyTpl functionality for substitution
     */

    /* apply common template substitutions */

    [
      '.mvn/redeploy.sh',
      'bitbucket-pipelines.yml',
      'docs/docinfo.html',
      'gradle/redeploy.sh',
      'settings.gradle',

    ].forEach(suffix => this.fs.copyTpl(
      this.templatePath(`_common/${suffix}`),
      this.destinationPath(`${this.props.projectDirectory}/${suffix}`),
      { projectDirectory: this.props.projectDirectory }
    ));

    /* project templates */

    [
      '.travis.yml',
      'README.adoc',

    ].forEach(suffix => this.fs.copyTpl(
      this.templatePath(`${this.props.projectType}/${suffix}`),
      this.destinationPath(`${this.props.projectDirectory}/${suffix}`),
      { projectDirectory: this.props.projectDirectory }
    ));

    /* apply template substitutions */

    switch (this.props.projectType) {

      // specific Scala Akka project (sbt only):
      case 'scala-sbt':
      case 'scala-sbt-akka-actor':

        [
          'pom.xml',
          'build.sbt',
          'Dockerfile',
          'docker-compose-sbt.yaml',

        ].forEach(path => this.fs.copyTpl(
          this.templatePath(`${this.props.projectType}/${path}`),
          this.destinationPath(`${this.props.projectDirectory}/${path}`),
          { projectDirectory: this.props.projectDirectory }
        ));

        break;

      // specific Scala Akka project (gradle only):
      case 'scala-gradle-akka-actor':
      case 'scala-gradle-akka-persistence':

        [
          'pom.xml',
          'gradle/Dockerfile',
          'docker-compose-gradle.yaml',

        ].forEach(path => this.fs.copyTpl(
          this.templatePath(`${this.props.projectType}/${path}`),
          this.destinationPath(`${this.props.projectDirectory}/${path}`),
          { projectDirectory: this.props.projectDirectory }
        ));

        break;

      // JavaEE projects with specific structure:
      case 'java-ee-ejb-full-multi-project':
      case 'java-ee-cdi-full-multi-project':

        [
          'pom.xml',
          'ejb-data/pom.xml',
          'ejb-services/pom.xml',
          'jsp/pom.xml',
          'rest/pom.xml',
          'servlet/pom.xml',
          'web/pom.xml',
          'ear/pom.xml',
          'settings.gradle',
          'ear/.mvn/Dockerfile',
          'ear/gradle/Dockerfile',
          'docker-compose-maven.yaml',
          'docker-compose-gradle.yaml',

        ].forEach(path => this.fs.copyTpl(
          this.templatePath(`${this.props.projectType}/${path}`),
          this.destinationPath(`${this.props.projectDirectory}/${path}`),
          { projectDirectory: this.props.projectDirectory, }
        ));

        break;

      //// any other projects by standard:
      //case: 'java':
      //case: 'java-akka':
      //case: 'java-ee':
      //case: 'java-ee-faces':
      //case: 'java-ee-thymeleaf':
      //case: 'java-parent-multi-project':
      //case: 'java-payara-micro':
      //case: 'java-spring-boot':
      //case: 'java-spring-boot-1.x':
      //case: 'java-spring-cloud-function-web':
      //case: 'java-vertx':
      //case: 'java-wildfly-swarm':
      //case: 'kotlin':
      //case: 'kotlin-ee':
      //case: 'kotlin-parent-multi-project':
      //case: 'kotlin-spring-boot':
      //case: 'kotlin-spring-boot-1.x':
      //case: 'kotlin-spring-cloud-function-web':
      //case: 'kotlin-vertx':
      //case: 'kotlin-wildfly-swarm':
      //case: 'scala-2.11':
      //case: 'scala':
      default:

        [
          'pom.xml',
          '.mvn/Dockerfile',
          'gradle/Dockerfile',
          'docker-compose-maven.yaml',
          'docker-compose-gradle.yaml',

        ].forEach(path => this.fs.copyTpl(
          this.templatePath(`${this.props.projectType}/${path}`),
          this.destinationPath(`${this.props.projectDirectory}/${path}`),
          { projectDirectory: this.props.projectDirectory, }
        ));

        break;
    }
  }

  install() {
    this.log(`\nDone!`);
    this.log(`Project ${this.props.projectType} located in ./${this.props.projectDirectory}`);
    this.log(`Let's start hacking! ^_^`);
    this.log(`idea ./${this.props.projectDirectory}/build.gradle`);
  }
};
