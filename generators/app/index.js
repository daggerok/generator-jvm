'use strict';

const Generator = require('yeoman-generator');
const chalk = require('chalk');
const yosay = require('yosay');
const { version } = require('../../package.json');

const replaceSpacesWithDash = input =>
  (input.replace(/\W+/g, '-') || 'app')
    .trim().toLocaleLowerCase();

module.exports = class extends Generator {
  prompting() {

    this.log(yosay(
      `Welcome to the terrific ${chalk.red('jvm')} generator ${chalk.blue('v' + version)}`
    ));

    const prompts = [
      {
        type: 'input',
        name: 'projectDirectory',
        message: 'Enter project directory name',
        default: 'app'
      },
      {
        type: 'list',
        name: 'projectType',
        message: 'My friend, what type of jvm project do you want to create today?',
        choices: [
          'java',
          'java-spring-boot',
          'java-spring-boot_1.x',
          'java-spring-cloud-function-web',
          'java-parent-multi-project',
          'java-akka',
          'java-ee',
          'java-ee-faces',
          'java-ee-cdi-full-multi-project',
          'java-ee-ejb-full-multi-project',
          'kotlin',
          'kotlin-spring-boot',
          'kotlin-spring-boot_1.x',
          'kotlin-spring-cloud-function-web',
          'kotlin-parent-multi-project',
          'kotlin-wildfly-swarm',
          'kotlin-ee',
          'scala',
          'scala_2.11',
          'scala-akka-persistence-gradle',
        ],
        default: 'java',
      },
    ];

    return this.prompt(prompts).then(props => {
      // To access props later use this.props.someAnswer;
      this.props = props;
    });
  }

  writing() {

    const projectDirectory = replaceSpacesWithDash(this.props.projectDirectory);
    const projectType = this.props.projectType;

    /* copy project files by type */

    [
      '**/*',
      '**/.*',

    ].forEach(pattern => this.fs.copy(
      this.templatePath(`${projectType}/${pattern}`),
      this.destinationPath(projectDirectory),
    ));

    /* copy dotted files / dirs, like: .mvn, .gitignore, ... */

    [
      'mvn',
      'gitignore',
      'hgignore',

    ].forEach(suffix => this.fs.copy(
      this.templatePath(`_dotted/${suffix}`),
      this.destinationPath(`${projectDirectory}/.${suffix}`),
    ));

    /* copy commons */

    [
      'LICENSE',
      'gradle',
      'gradlew',
      'gradlew.bat',
      'mvnw',
      'mvnw.cmd',

    ].forEach(suffix => this.fs.copy(
      this.templatePath(`_common/${suffix}`),
      this.destinationPath(`${projectDirectory}/${suffix}`),
    ));

    /* apply template substitutions */

    switch (projectType) {

      // specific Scala Akka project (gradle only):
      case 'scala-akka-persistence-gradle':

        [
          '.travis.yml',
          'README.adoc',
          'settings.gradle',
          'gradle/Dockerfile',
          'docker-compose.yaml',

        ].forEach(path => this.fs.copyTpl(
          this.templatePath(`${projectType}/${path}`),
          this.destinationPath(`${projectDirectory}/${path}`),
          { projectDirectory }
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
          '.travis.yml',
          'README.adoc',
          'settings.gradle',
          'ear/.mvn/Dockerfile',
          'ear/gradle/Dockerfile',
          'ear/docker-compose-maven.yaml',
          'ear/docker-compose-gradle.yaml',

        ].forEach(path => this.fs.copyTpl(
          this.templatePath(`${projectType}/${path}`),
          this.destinationPath(`${projectDirectory}/${path}`),
          { projectDirectory }
        ));

        break;

      default: // any other projects by standard:

        [
          'pom.xml',
          '.travis.yml',
          'README.adoc',
          'settings.gradle',
          '.mvn/Dockerfile',
          'gradle/Dockerfile',
          'docker-compose-maven.yaml',
          'docker-compose-gradle.yaml',

        ].forEach(path => this.fs.copyTpl(
          this.templatePath(`${projectType}/${path}`),
          this.destinationPath(`${projectDirectory}/${path}`),
          { projectDirectory }
        ));

        break;
    }
  }

  install() {

    const projectDirectory = replaceSpacesWithDash(this.props.projectDirectory);

    this.log(`Done!`);
    this.log(`Import project and start hacking:`);
    this.log(`idea ./${projectDirectory}/pom.xml`);
    this.log(`idea ./${projectDirectory}/build.gradle`);
  }
};
