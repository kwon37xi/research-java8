print("== Printing environment variables with nashorn...")
for (var env in $ENV) {
    print("${env} : ${$ENV[env]}");
}

