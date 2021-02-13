import React from 'react';

function Entry(props) {

    function addLabel(data) {
        return (
            <td className="d-flex flex-column justify-content-center align-items-center"
                key={data.id}>
                {data.sprites.other["official-artwork"].front_default && 
                <img
                    src={data.sprites.other["official-artwork"].front_default}
                    className="icon"
                    alt={`${data.name}`} />
                }
                <p>{cleanText(data.name)}</p>
            </td>
        )
    }

    function addStats(data) {
        return data.stats.map((stat, index) => 
            <td className="align-middle"
                key={`${data.id}-${index}`}>
                {stat.base_stat}
            </td>
        );
    }

    function addTyping(data) {
        return addUpTo(data.types.map((typeObj, index) =>
            <td key={`${data.id}-${index}`}>
                <img src={`https://www.serebii.net/pokedex-bw/type/${typeObj.type.name}.gif`}
                    alt={`${typeObj.type.name} type`} />
            </td>
        ), 2);
    }

    function addAbilities(data) {
        return addUpTo(data.abilities.map((abilityObj, index) =>
            <td key={`${data.id}-${index}`}>
                {cleanText(abilityObj.ability.name)}
            </td>
        ), 4);
    }

    function addUpTo(columns, limit) {
        while (columns.length < limit) {
            columns.push(<td key={columns.length} />)
        }
        return columns
    }

    function cleanText(string) {
        return capitalize(string.replace(/-/g, ' '));
    }
    
    function capitalize(string) {
        return string.replace(/(\b[a-z](?!\s))/g, (c) => c.toUpperCase());
    }

    return (
        <tr>
            {addLabel(props.data)}
            {props.display["Base Stats"] && addStats(props.data)}
            {props.display["Typing"] && addTyping(props.data)}
            {props.display["Abilities"] && addAbilities(props.data)}
        </tr>
    )
}

export default Entry;