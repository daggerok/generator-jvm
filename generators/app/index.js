'use strict';

const Generator = require('yeoman-generator');
const chalk = require('chalk');
const yosay = require('yosay');

const { version } = require('../../package.json');

const _defaultProjectName = 'app';
const _defaultProjectType = 'java';

const _replaceSpacesWithDash = input =>
  (input || _defaultProjectName).trim()
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
      'java-spring-boot-2.0.x',
      'java-spring-boot-1.x',
      'java-spring-cloud-function-web',
      'java-vertx',
      'java-thorntail',
      'java-wildfly-swarm',
      'kotlin',
      'kotlin-parent-multi-project',
      'kotlin-spring-boot',
      'kotlin-spring-boot-2.0.x',
      'kotlin-spring-boot-1.x',
      'kotlin-spring-cloud-function-web',
      'kotlin-vertx',
      'kotlin-thorntail',
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
      projectDirectory: _defaultProjectName,
      projectType: _defaultProjectType,
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
      default: _defaultProjectType,
    });

    if (!!name) this.props.projectDirectory = name;
    else if (typeOptionFound) this.props.projectDirectory = _defaultProjectName;
    else this.prompts.push({
      type: 'input',
      name: 'projectDirectory',
      message: 'Enter application project directory name',
      default: _defaultProjectName
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
    this.props.projectDirectory = _replaceSpacesWithDash(this.props.projectDirectory);
    this.props.projectType = this.props.projectType.trim().toLowerCase();

    /* RAW files. Using: copy functionality as is */
    this._copyCommons();
    this._copyDottedFilesAndFolders();
    this._copyMavenWrapper();

    /* copy project files by type */
    // WARNING: this block of code must be inside writing block!
    // **/*, **/.*
    [
      '**/*',
      '**/.*',

    ].forEach(pattern => this.fs.copy(
      this.templatePath(`${this.props.projectType}/${pattern}`),
      this.destinationPath(this.props.projectDirectory),
    ));

    /* Template files. Using: copyTpl functionality for substitution */
    this._applyCommonTemplatesSubstitutions();
    this._applyAllProjectTemplatesSubstitutions();
    this._applyTemplatesSubstitutions();
  }

  _copyCommons() {
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
  }

  _copyDottedFilesAndFolders() {

    [
      'gitignore',
      // // disable for now:
      // 'hgignore',
      // 'gitlab-ci.yml',

    ].forEach(suffix => this.fs.copy(
      this.templatePath(`_dotted/${suffix}`),
      this.destinationPath(`${this.props.projectDirectory}/.${suffix}`),
    ));
  }

  _copyMavenWrapper() {

    /* copy dotted files / dirs, like: .mvn, .gitignore, ... */
    switch (this.props.projectType) {

      // specific JavaEE project (maven only):
      case 'java-thorntail':
      case 'java-wildfly-swarm':
      case 'kotlin-thorntail':
      case 'kotlin-wildfly-swarm':

        this.fs.copy(
          this.templatePath(`_dotted/mvn-3.5.4`),
          this.destinationPath(`${this.props.projectDirectory}/.mvn`),
        );

        break;

      default:

        this.fs.copy(
          this.templatePath(`_dotted/mvn`),
          this.destinationPath(`${this.props.projectDirectory}/.mvn`),
        );

        break;
    }
  }

  _applyCommonTemplatesSubstitutions() {
    /* apply common template substitutions */
    [
      // // disable fo now:
      // '.mvn/redeploy.sh',
      // 'bitbucket-pipelines.yml',
      // 'gradle/redeploy.sh',
      'settings.gradle',
      'docs/docinfo.html',

    ].forEach(suffix => this.fs.copyTpl(
      this.templatePath(`_common/${suffix}`),
      this.destinationPath(`${this.props.projectDirectory}/${suffix}`),
      { projectDirectory: this.props.projectDirectory }
    ));
  }

  _applyAllProjectTemplatesSubstitutions() {
    /* all project templates substitutions */
    [
      '.travis.yml',
      'README.adoc',

    ].forEach(suffix => this.fs.copyTpl(
      this.templatePath(`${this.props.projectType}/${suffix}`),
      this.destinationPath(`${this.props.projectDirectory}/${suffix}`),
      { projectDirectory: this.props.projectDirectory }
    ));
  }

  _applyTemplatesSubstitutions() {
    /* apply template substitutions */
    switch (this.props.projectType) {

      // specific JavaEE project (maven only):
      case 'java-thorntail':
      case 'kotlin-thorntail':

        [
          'docker-compose-maven.yaml',
          'pom.xml',

        ].forEach(path => this.fs.copyTpl(
          this.templatePath(`${this.props.projectType}/${path}`),
          this.destinationPath(`${this.props.projectDirectory}/${path}`),
          { projectDirectory: this.props.projectDirectory }
        ));

        break;

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
      //case: 'java-spring-boot-2.0.x':
      //case: 'java-spring-boot-1.x':
      //case: 'java-spring-cloud-function-web':
      //case: 'java-vertx':
      //case: 'java-wildfly-swarm':
      //case: 'kotlin':
      //case: 'kotlin-ee':
      //case: 'kotlin-parent-multi-project':
      //case: 'kotlin-spring-boot':
      //case: 'kotlin-spring-boot-2.0.x':
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
