
export const cleanText = (string) => replaceDashes(capitalize(string));

function replaceDashes(string) {
    return capitalize(string.replace(/-/g, ' '));
}

function capitalize(string) {
    return string.replace(/(\b[a-z](?!\s))/g, (c) => c.toUpperCase());
}


export const colorizeBy = (string) => intToRGB(hashCode(string));

function hashCode(str) {
    let hash = 0;
    for (let i = 0; i < str.length; i++) {
        hash = str.charCodeAt(i) + ((hash << 12));
    }
    return hash;
}

function intToRGB(i) {
    let c = (i & 0x00FFFFFF)
        .toString(16)
        .toUpperCase();

    return "#00000".substring(0, 7 - c.length) + c;
}

