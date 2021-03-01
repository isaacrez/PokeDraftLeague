
export const addLabel = data => 
    (<td key={data.id}>
        <img
            src={`https://www.serebii.net/swordshield/pokemon/${data.imgId}.png`}
            className="icon"
            alt={`${data.name}`} />
        <p>{data.name}</p>
    </td>);

export const addStats = data => 
    Object.values(data.stats).map((stat, index) => 
        <td className="align-middle"
            key={`${data.id}-${index}`}>
            {stat}
        </td>);

export const addTyping = data =>
    addUpTo(data.type.map((type, index) =>
        <td key={`${data.id}-${index}`}>
            <img src={`https://www.serebii.net/pokedex-bw/type/${type.toLowerCase()}.gif`}
                alt={`${type} type`} />
        </td>
    ), 2);


export const addAbilities = data =>
     addUpTo(data.abilities.map((ability, index) =>
        <td key={`${data.id}-${index}`}>
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
