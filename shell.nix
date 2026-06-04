{ pkgs ? import <nixpkgs> {} }:

pkgs.mkShell {
  buildInputs = with pkgs; [
    maven
    jdk8
    git
  ];

  shellHook = ''
    echo "Maven $(mvn --version | head -n1) ready"
    echo "Java $(java -version 2>&1 | head -n1)"
    export JAVA_HOME="${pkgs.jdk8.home}"
  '';
}
