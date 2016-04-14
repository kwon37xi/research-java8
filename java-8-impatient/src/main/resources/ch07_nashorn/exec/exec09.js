var ageString = arguments[0];

if (!ageString) {
    ageString = $ENV['AGE']
}

if (!ageString) {
    ageString = readLine("current age :");
}

var age = Number(ageString);

print("next year, you will be ${age+1}...");
