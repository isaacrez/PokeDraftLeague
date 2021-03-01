
export const addLabel = data => 
    (<td key={data.id}>
        <img
            src={`https://www.serebii.net/swordshield/pokemon/${data.imgId}.png`}
            className="icon"
            alt={`${data.name}`} />
        <p>{data.name}</p>
    </td>);

export const addUpTo = (columns, limit) => {
    while (columns.length < limit) {
        columns.push(<td key={columns.length} />)
    }
    return columns
}
