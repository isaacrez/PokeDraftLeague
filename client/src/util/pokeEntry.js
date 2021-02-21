
export const addLabel = data => 
    (<td key={data.id}>
        <img
            src={`https://www.serebii.net/swordshield/pokemon/${data.pokemon.imgId}.png`}
            className="icon"
            alt={`${data.pokemon.name}`} />
        <p>{data.pokemon.name}</p>
    </td>);

export const addStats = data => 
    Object.values(data.stats).map((stat, index) => 
        <td className="align-middle"
            key={`${data.pokemon.id}-${index}`}>
            {stat}
        </td>);

export const addTyping = data =>
    addUpTo(data.type.map((type, index) =>
        <td key={`${data.pokemon.id}-${index}`}>
            <img src={`https://www.serebii.net/pokedex-bw/type/${type.toLowerCase()}.gif`}
                alt={`${type} type`} />
        </td>
    ), 2);


export const addAbilities = data =>
     addUpTo(data.abilities.map((ability, index) =>
        <td key={`${data.pokemon.id}-${index}`}>
            {ability}
        </td>
    ), 4);

export const addLeagueStats = data =>
    [
        <td key={"team"}>{data.team ? data.team.acronym : "FREE"}</td>,
        <td key={"directKOs"}>{data.directKOs}</td>,
        <td key={"indirectKOs"}>{data.indirectKOs}</td>,
        <td key={"deaths"}>{data.deaths}</td>
    ];


function addUpTo(columns, limit) {
    while (columns.length < limit) {
        columns.push(<td key={columns.length} />)
    }
    return columns
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

