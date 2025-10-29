function findConstantFromJSON(jsonData) {
    // ---- STEP 1: Parse JSON into (x, y) pairs ----
    const points = [];

    for (const key in jsonData) {
        if (key !== "keys") {
            const base = parseInt(jsonData[key].base);
            const val = parseInt(jsonData[key].value, base); // Convert to decimal
            const x = parseInt(key);
            const y = val;
            points.push([x, y]);
        }
    }

    if (points.length < 2) {
        console.log("Need at least 2 points to interpolate.");
        return null;
    }

    console.log("Parsed points (x, y):", points);

    // ---- STEP 2: Lagrange Interpolation to find constant term ----
    function lagrangeConstant(points) {
        let constantTerm = 0;

        for (let i = 0; i < points.length; i++) {
            const [xi, yi] = points[i];
            let term = yi;

            for (let j = 0; j < points.length; j++) {
                if (i !== j) {
                    const [xj, _] = points[j];
                    term *= -xj / (xi - xj);
                }
            }

            constantTerm += term;
        }

        return constantTerm;
    }

    // ---- STEP 3: Compute constant term (c = P(0)) ----
    const c = lagrangeConstant(points);
    console.log( Math.round(c));
    return Math.round(c);
}

// Example JSON Inputs
const data = {
    "keys": { "n": 4, "k": 3 },
    "1": { "base": "10", "value": "4" },
    "2": { "base": "2", "value": "111" },
    "3": { "base": "10", "value": "12" },
    "6": { "base": "4", "value": "213" }
};

const data2 = {
    "keys": { "n": 10, "k": 7 },
    "1": { "base": "6", "value": "13444211440455345511" },
    "2": { "base": "15", "value": "aed7015a346d635" },
    "3": { "base": "15", "value": "6aeeb69631c227c" },
    "4": { "base": "16", "value": "e1b5e05623d881f" },
    "5": { "base": "8", "value": "316034514573652620673" },
    "6": { "base": "3", "value": "2122212201122002221120200210011020220200" },
    "7": { "base": "3", "value": "20120221122211000100210021102001201112121" },
    "8": { "base": "6", "value": "20220554335330240002224253" },
    "9": { "base": "12", "value": "45153788322a1255483" },
    "10": { "base": "7", "value": "1101613130313526312514143" }
};

// ---- Run on different datasets ----
findConstantFromJSON(data);
findConstantFromJSON(data2);
